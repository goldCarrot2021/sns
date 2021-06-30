package com.example.demo.handler.ex;

import java.util.Map;

public class CustomValidationException extends RuntimeException{

    // 객체러를 구분할때 serialVersion사용
    private static final long serialVersionUID = 1L;

    private Map<String, String> errorMap;

    public CustomValidationException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap(){
        return errorMap;
    }
}
