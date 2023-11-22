package com.tang.common.exception;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tang.common.constant.Constants;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @ExceptionHandler(OpenAIRequestException.class)
    @ResponseBody
    public Object handleAsOpenAIRequestException(OpenAIRequestException e, HttpServletRequest request) {
        String acceptHeader = request.getHeader("Accept");
        if (acceptHeader.equals(Constants.DEFAULT_ACCEPT)){
            return e.getError();
        }else if (acceptHeader.equals(Constants.STREAM_ACCEPT)){
            ModelAndView mav = new ModelAndView();
            mav.setView(new MappingJackson2JsonView());
            mav.addObject(e.getError());
            return mav;
        }else {
            return "";
        }

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
