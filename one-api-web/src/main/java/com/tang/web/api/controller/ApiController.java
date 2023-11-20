package com.tang.web.api.controller;

import com.tang.core.modules.api.chat.ChatCompletion;
import com.tang.core.modules.api.service.ForwardHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ApiController {

    @Autowired
    private ForwardHandleService forwardHandleService;

    @PostMapping("/api/openai/v1/chat/completions")
    public SseEmitter completions(@RequestBody ChatCompletion chatCompletion, HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        return forwardHandleService.completions(chatCompletion,authorization);
    }

    @PostMapping("/v1/chat/completions")
    public SseEmitter Chatcompletions(@RequestBody ChatCompletion chatCompletion, HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        return forwardHandleService.completions(chatCompletion,authorization);
    }

}
