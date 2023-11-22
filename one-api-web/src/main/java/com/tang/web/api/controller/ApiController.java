package com.tang.web.api.controller;

import com.tang.core.modules.api.chat.ChatCompletion;
import com.tang.core.modules.api.service.ForwardHandleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class ApiController {

    @Autowired
    private ForwardHandleService forwardHandleService;

    @PostMapping("/api/openai/v1/chat/completions")
    public Object completions(@RequestBody ChatCompletion chatCompletion, HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        return forwardHandleService.completions(chatCompletion,authorization);
    }


    @PostMapping(value = "/v1/chat/completions")
    public Object ChatCompletions(@RequestBody ChatCompletion chatCompletion, HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        return forwardHandleService.completions(chatCompletion,authorization);
    }

}
