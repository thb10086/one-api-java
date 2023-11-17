package com.tang.common.exception;

import com.tang.common.enums.OpenAIErrorEnums;

/**
 * @projectName: Lzx-CloudPlatform
 * @package: com.lzx.cloudplatform.common.core.exception
 * @className: ServiceException
 * @author: HuangHui
 * @description: 业务异常
 * @date: 2023/5/29 11:30
 * @version: 1.0
 */
public final class ServiceException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    /**
     * 错误明细，内部调试错误
     *
     * 和 {@link CommonResult#getDetailMessage()} 一致的设计
     */
    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException()
    {
    }

    public ServiceException(String message)
    {
        this.message = message;
    }

    public ServiceException(OpenAIErrorEnums enums){
        this.code=enums.getCode();
        this.message=enums.getMessage();
    }

    public ServiceException(String message, Integer code)
    {
        this.message = message;
        this.code = code;
    }

    public String getDetailMessage()
    {
        return detailMessage;
    }

    @Override
    public String getMessage()
    {
        return message;
    }

    public Integer getCode()
    {
        return code;
    }

    public ServiceException setMessage(String message)
    {
        this.message = message;
        return this;
    }

    public ServiceException setDetailMessage(String detailMessage)
    {
        this.detailMessage = detailMessage;
        return this;
    }
}
