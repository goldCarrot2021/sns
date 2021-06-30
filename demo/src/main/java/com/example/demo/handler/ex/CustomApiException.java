package com.example.demo.handler.ex;

import java.util.Map;

public class CustomApiException extends RuntimeException{

    // 객체러를 구분할때 serialVersion사용
    private static final long serialVersionUID = 1L;

    public CustomApiException(String message) {
        super(message);
    }

}
