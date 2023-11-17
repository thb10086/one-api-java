package com.tang.web.transfer.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.common.domain.R;
import com.tang.core.modules.transfer.model.dto.TransferApiKeysCreateDto;
import com.tang.core.modules.transfer.model.dto.TransferApiKeysDto;
import com.tang.core.modules.transfer.model.dto.TransferApiKeysReqDto;
import com.tang.core.modules.transfer.service.ITransferApiKeysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 中转平台API密钥表 前端控制器
 * </p>
 *
 * @author tanghaibin
 * @since 2023-11-14
 */
@RestController
public class TransferApiKeysController {

    @Autowired
    ITransferApiKeysService iTransferApiKeysService;

    @PostMapping("/transfer/createTransferApiKey")
    public R<Boolean> createTransferApiKey(@RequestBody @Validated TransferApiKeysCreateDto dto){
        return R.ok(iTransferApiKeysService.createTransferApiKey(dto));
    }

    @GetMapping("/transfer/queryTransferApiKeys")
    public R<Page<TransferApiKeysDto>> queryTransferApiKeys(TransferApiKeysReqDto dto){
        return R.ok(iTransferApiKeysService.queryTransferApiKeys(dto));
    }
}

