package com.tang.common.exception;

import lombok.Data;

@Data
public class OpenaiError {
    private ErrorMessage error;
}
@Data
class ErrorMessage{
    private String message;
    private String type;
}
