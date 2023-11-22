package com.tang.common.exception;

import com.tang.common.enums.OpenAIErrorEnums;
import lombok.Data;

import static com.tang.common.constant.Constants.ONE_API_JAVA_ERROR;

/**
 * @projectName: Lzx-CloudPlatform
 * @package: com.lzx.cloudplatform.common.core.exception
 * @className: ServiceException
 * @author: HuangHui
 * @description: 业务异常
 * @date: 2023/5/29 11:30
 * @version: 1.0
 */
@Data
public final class OpenAIRequestException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private OpenaiError error;

    /**
     * 错误明细，内部调试错误
     *
     * 和 {@link } 一致的设计
     */
    private String detailMessage;

    /**
     * 空构造方法，避免反序列化问题
     */
    public OpenAIRequestException()
    {
    }

    public OpenAIRequestException(String message)
    {
        OpenaiError error = new OpenaiError();
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(message);
        errorMessage.setType(ONE_API_JAVA_ERROR);
        error.setError(errorMessage);
        this.error = error;
    }

    public OpenAIRequestException(OpenAIErrorEnums enums){
        this.code=enums.getCode();
        OpenaiError error = new OpenaiError();
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(enums.getMessage());
        errorMessage.setType(ONE_API_JAVA_ERROR);
        error.setError(errorMessage);
        this.error = error;
    }


    public OpenAIRequestException(String message, Integer code)
    {
        this.code=code;
        OpenaiError error = new OpenaiError();
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(message);
        errorMessage.setType(ONE_API_JAVA_ERROR);
        error.setError(errorMessage);
        this.error = error;
        this.code = code;
    }

    public String getDetailMessage()
    {
        return detailMessage;
    }

    @Override
    public String getMessage()
    {
        return error.getError().getMessage();
    }

    public Integer getCode()
    {
        return code;
    }

    public OpenAIRequestException setMessage(String message)
    {
        OpenaiError error = new OpenaiError();
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setMessage(message);
        errorMessage.setType(ONE_API_JAVA_ERROR);
        error.setError(errorMessage);
        this.error = error;
        return this;
    }

    public OpenAIRequestException setDetailMessage(String detailMessage)
    {
        this.detailMessage = detailMessage;
        return this;
    }
}
