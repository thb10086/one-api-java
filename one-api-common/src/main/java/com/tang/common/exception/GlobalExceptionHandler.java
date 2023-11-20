package com.tang.common.exception;

import com.tang.common.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 控制层异常拦截处理
 *
 * @author caochengbo
 * @date 2023/6/9 23:14
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     *
     * @param e 业务异常
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public R<Object> exceptionHandler(ServiceException e) {
        if (Objects.nonNull(e.getCode())) {
            return R.fail(e.getCode().intValue(), e.getMessage());
        }
        log.error(e.getMessage());
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public R<Object> exceptionHandler(IllegalArgumentException e) {
        log.error(e.getMessage());
        return R.fail(500, e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R<Object> exceptionHandler(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return R.fail(500, e.getMessage());
    }
    @ExceptionHandler(AsyncRequestTimeoutException.class)
    @ResponseBody
    public R<?> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException e) {
        return R.fail(500, "请求超时："+e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String errorMessage = bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        // 创建并返回你的响应对象，将错误信息放入其中
        return R.fail(500, errorMessage);

    }


}
