package com.tang.core.modules.channel.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.common.config.RedisService;
import com.tang.common.constant.Constants;
import com.tang.common.constant.RedisConstants;
import com.tang.common.domain.BaseEntity;
import com.tang.common.domain.R;
import com.tang.common.enums.ChannelTypeEnums;
import com.tang.common.exception.ServiceException;
import com.tang.common.utils.BeanUtils;
import com.tang.core.modules.channel.model.ChannelGroup;
import com.tang.core.modules.channel.model.ChannelModel;
import com.tang.core.modules.channel.model.Channels;
import com.tang.core.modules.channel.mapper.ChannelsMapper;
import com.tang.core.modules.channel.model.dto.ChannelsDto;
import com.tang.core.modules.channel.model.dto.ChannelsReqDto;
import com.tang.core.modules.channel.model.dto.ChannelsResponseDto;
import com.tang.core.modules.channel.model.dto.ChannelsVo;
import com.tang.core.modules.channel.service.IChannelGroupService;
import com.tang.core.modules.channel.service.IChannelModelService;
import com.tang.core.modules.channel.service.IChannelsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.core.modules.groups.model.dto.GroupsDto;
import com.tang.core.modules.groups.service.IGroupsService;
import com.tang.core.modules.models.service.IModelsService;
import com.tang.core.modules.platform.model.dto.PlatformApiKeysDto;
import com.tang.core.modules.platform.service.IPlatformApiKeysService;
import com.tang.core.modules.user.model.UserGroup;
import com.tang.core.modules.user.service.IUserGroupService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 渠道表 服务实现类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-13
 */
@Service
public class ChannelsServiceImpl extends ServiceImpl<ChannelsMapper, Channels> implements IChannelsService {

    @Autowired
    IModelsService iModelsService;

    @Autowired
    IGroupsService iGroupsService;

    @Autowired
    IPlatformApiKeysService iPlatformApiKeysService;

    @Autowired
    IChannelModelService iChannelModelService;

    @Autowired
    IChannelGroupService iChannelGroupService;

    @Autowired
    IUserGroupService iUserGroupService;

    @Autowired
    RedisService redisService;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Boolean createChannel(ChannelsDto channelsDto) {
        //校验数据
        verifyData(channelsDto);

        //获取api-key
        List<String> apiKeyList = getApiKeyList(channelsDto);

        //获取模型id集合
        List<Long> modelIdList = getModelIdList(channelsDto);

        //获取分组id集合
        List<Long> groupIdList = getGroupIdList(channelsDto);

        Channels convert = BeanUtils.convert(channelsDto, Channels.class);
        //保存渠道表的数据
        save(convert);

        //保存平台api-key
        List<PlatformApiKeysDto> apiKeys = savePlatformApiKeys(convert.getChannelId(), apiKeyList);

        //保存渠道关联模型
        List<ChannelModel> models = saveChannelsModel(convert.getChannelId(), modelIdList);

        //保存渠道关联分组
        List<ChannelGroup> groups = saveChannelsGroup(convert.getChannelId(), groupIdList);

        saveRedisCache(convert,apiKeys,models,groups);
        return true;
    }


    void saveRedisCache(Channels channels,List<PlatformApiKeysDto> apiKeys,List<ChannelModel> models,List<ChannelGroup> groups){
        ChannelsVo vo = new ChannelsVo();
        vo.setChannelName(channels.getChannelName());
        vo.setChannelType(channels.getChannelType());
        vo.setApiKeys(apiKeys);
        vo.setModels(models.stream().map(ChannelModel::getModelId).collect(Collectors.toList()));
        vo.setGroupIds(groups.stream().map(ChannelGroup::getGroupId).collect(Collectors.toList()));
        redisService.setCacheObject(RedisConstants.CACHE_CHANNEL+channels.getChannelId(),vo);
    }

    //保存平台apikey
    List<PlatformApiKeysDto> savePlatformApiKeys(Long channelId,List<String> apiKeyList){
        List<PlatformApiKeysDto> list = apiKeyList.stream()
                .map(apiKey -> {
                    PlatformApiKeysDto dto = new PlatformApiKeysDto();
                    dto.setChannelId(channelId);
                    dto.setApiKey(apiKey);
                    return dto;
                }).collect(Collectors.toList());
        iPlatformApiKeysService.createPlatformApiKeysList(list);
        return list;
    }

    //保存渠道关联模型
    List<ChannelModel> saveChannelsModel(Long channelId,List<Long> modelIdList){
        List<ChannelModel> list = modelIdList.stream().map(modelId -> {
            ChannelModel channelModel = new ChannelModel();
            channelModel.setChannelId(channelId);
            channelModel.setModelId(modelId);
            return channelModel;
        }).collect(Collectors.toList());
        iChannelModelService.createChannelModelList(list);
        return list;
    }

    //保存渠道关联分组
    List<ChannelGroup> saveChannelsGroup(Long channelId,List<Long> groupIdList){
        List<ChannelGroup> list = groupIdList.stream().map(groupId -> {
            ChannelGroup channelGroup = new ChannelGroup();
            channelGroup.setChannelId(channelId);
            channelGroup.setGroupId(groupId);
            return channelGroup;
        }).collect(Collectors.toList());
        iChannelGroupService.createChannelGroupList(list);
        return list;
    }

    List<Long> getModelIdList(ChannelsDto channelsDto){
        return Arrays.stream(channelsDto.getModels().split(",")).map(i->Long.valueOf(i)).collect(Collectors.toList());
    }

    List<Long> getGroupIdList(ChannelsDto channelsDto){
        return Arrays.stream(channelsDto.getGroupId().split(",")).map(i->Long.valueOf(i)).collect(Collectors.toList());
    }

    List<String> getApiKeyList(ChannelsDto channelsDto){
        //获取字符串分割或者回车分割的api-key数据
        List<String> strApiKeys = analysisApiKeys(channelsDto.getApiKeys());
        //获取csv文件中的数据
        List<String> csvApiKeys = analysisApiKeysByCsv(channelsDto.getCsvFile());
        //合并api-key数据
        List<String> apiKeys = Stream.of(Optional.ofNullable(strApiKeys), Optional.ofNullable(csvApiKeys))
                .flatMap(opt -> opt.map(Collection::stream).orElseGet(Stream::empty))
                .collect(Collectors.toList());
        return apiKeys;
    }

    void verifyData(ChannelsDto channelsDto){
        Assert.isTrue(ChannelTypeEnums.byI(channelsDto.getChannelType()),"渠道类型不存在！");
        Assert.isTrue(StringUtils.hasText(channelsDto.getApiKeys())||channelsDto.getCsvFile()!=null,"请传入api-key");
        //检查模型列表是否正确。
        Assert.isTrue(iModelsService.checkModels(channelsDto.getModels()),"不存在的模型");
        Assert.isTrue(iGroupsService.checkGroups(channelsDto.getGroupId()),"不存在的分组");
        Assert.isTrue(channelsDto.getProxyAddress().matches(Constants.URL_PATTERN),"代理地址格式不正确");
    }

    public List<String> analysisApiKeysByCsv(MultipartFile csvFile){
        if (csvFile==null){
            return null;
        }
        LinkedList<String> list = new LinkedList<>();
        // 其他字段...
        try {
            // 转换MultipartFile为InputStream
            InputStream inputStream = csvFile.getInputStream();
            // 创建CSVParser对象
            CSVParser csvParser = new CSVParser(new InputStreamReader(inputStream), CSVFormat.DEFAULT);
            for (CSVRecord csvRecord : csvParser) {
                // 访问CSV记录的值
                for (String key : csvRecord) {
                    list.add(key);
                }
                // 其他操作...
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("csv文件读取异常！",500);
        }
        return list;
    }

    /**
     * 解析apikey
     * @param apiKeys
     * @return
     */
    public List<String> analysisApiKeys(String apiKeys){
        if (!StringUtils.hasText(apiKeys)){
            return null;
        }
        //先根据逗号分割
        List<String> apiKeyList = Arrays.stream(apiKeys.split(",")).collect(Collectors.toList());
        //不行的话再用回车进行分割
        if (CollectionUtil.isEmpty(apiKeyList)){
            apiKeyList = Arrays.stream(apiKeys.split("\\n")).collect(Collectors.toList());
        }
        return apiKeyList;
    }

    @Override
    public Boolean updateChannel() {
        return null;
    }

    @Override
    public Boolean deleteChannel() {
        return null;
    }

    @Override
    public Page<ChannelsResponseDto> queryChannel(ChannelsReqDto channelsReqDto) {
        //获取登录人的id
         Long userId = StpUtil.getLoginIdAsLong();
        //获取用户的分组
        List<UserGroup> userGroups = iUserGroupService.queryUserGroup(userId);
        if (channelsReqDto.getGroupId()!=null){
            boolean b = userGroups.stream().anyMatch(i -> i.getGroupId().equals(channelsReqDto.getGroupId()));

            if (!b){
                throw new ServiceException("您没有这个分组权限！",500);
            }
        }
        //分组id集合
        List<Long> groupIds = userGroups.stream().filter(i->channelsReqDto.getGroupId()==null?true:i.getGroupId().equals(channelsReqDto.getGroupId())).map(UserGroup::getGroupId).collect(Collectors.toList());
        //用户没有设置分组，返回空数据
        if (CollectionUtil.isEmpty(groupIds)){
            return new Page<>();
        }
        //获取分组跟渠道的关系
        List<ChannelGroup> channelGroups = iChannelGroupService.getChannelGroupByGroupId(groupIds);
        //渠道的id
        List<Long> channelIds = channelGroups.stream().map(ChannelGroup::getChannelId).collect(Collectors.toList());

        //查询渠道的条件
        LambdaQueryWrapper<Channels> queryWrapper = new LambdaQueryWrapper<Channels>()
                .in(Channels::getChannelId, channelIds)
                .like(StringUtils.hasText(channelsReqDto.getChannelName()), Channels::getChannelName, channelsReqDto.getChannelName())
                .eq(Objects.nonNull(channelsReqDto.getChannelType()), Channels::getChannelType, channelsReqDto.getChannelType())
                .eq(BaseEntity::getDelFlag, false)
                .orderByDesc(BaseEntity::getCreateTime);

        Page<Channels> page = page(channelsReqDto.startPage(), queryWrapper);
        //没有数据直接返回
        if (CollectionUtil.isEmpty(page.getRecords())){
            return new Page<>();
        }
        //对数据进行处理
        List<ChannelsResponseDto> dtoList = BeanUtils.convert(page.getRecords(), ChannelsResponseDto.class);
        //分组
        Map<Long, GroupsDto> groupsDtoMap = iGroupsService.getGroups().stream().collect(Collectors.toMap(GroupsDto::getGroupId, Function.identity()));
        //根据渠道分组，获取渠道下的所有分组
        Map<Long, List<ChannelGroup>> channelGroupMap = channelGroups.stream().collect(Collectors.groupingBy(ChannelGroup::getChannelId));

        //渠道下的api-key
        Map<Long, List<PlatformApiKeysDto>> apiKeysDtoMap = iPlatformApiKeysService.getPlatformApiKeysByChannelIds(channelIds).stream().collect(Collectors.groupingBy(PlatformApiKeysDto::getChannelId));

        for (ChannelsResponseDto dto : dtoList) {
            dto.setChannelTypeDesc(ChannelTypeEnums.getType(dto.getChannelType()));
            List<ChannelGroup> groups = channelGroupMap.get(dto.getChannelId());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < groups.size(); i++) {
                sb.append(groupsDtoMap.get(groups.get(i).getGroupId()).getGroupName());
                if (i!=groups.size()-1){
                    sb.append(",");
                }
            }
            dto.setGroups(sb.toString());
            List<PlatformApiKeysDto> apikeyList = apiKeysDtoMap.get(dto.getChannelId());
            //正常用的key
            long count = apikeyList.stream().filter(i -> !i.getIsDisabled()).count();
            long sum = apikeyList.size();
            dto.setRate(count+"/"+sum);
        }
        Page<ChannelsResponseDto> resultPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        resultPage.setRecords(dtoList);
        return resultPage;
    }

    @Override
    public List<Channels> queryChannelList(List<Long> channelIds) {
        LambdaQueryWrapper<Channels> queryWrapper = new LambdaQueryWrapper<Channels>()
                .in(Channels::getChannelId, channelIds)
                .in(BaseEntity::getDelFlag, false);
        return list(queryWrapper);
    }

}
