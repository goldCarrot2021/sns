package com.example.demo.handler.ex;


public class CustomException extends RuntimeException{

    // 객체러를 구분할때 serialVersion사용
    private static final long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message);
    }
}
