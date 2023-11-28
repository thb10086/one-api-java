package com.tang.core.modules.config.service.impl;

import com.tang.core.modules.config.model.SysConfig;
import com.tang.core.modules.config.mapper.SysConfigMapper;
import com.tang.core.modules.config.service.ISysConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-27
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements ISysConfigService {

}
