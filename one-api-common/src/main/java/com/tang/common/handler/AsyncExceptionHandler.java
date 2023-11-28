package com.tang.common.handler;

import com.tang.common.evnet.ExceptionHandlerEvent;
import com.tang.common.exception.OpenAIRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class AsyncExceptionHandler {
    @EventListener(classes = ExceptionHandlerEvent.class)
    public void handleException(ExceptionHandlerEvent event) {
        log.info("异常被主线程捕获并且抛出……");
        throw event.getOpenAIRequestException();
    }

    public static void main(String[] args) {
        System.out.println("开始");
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(()->{
            System.out.println("异常执行");
        });
        System.out.println("结束");
    }
}
