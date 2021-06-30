package com.example.demo.handler;

import com.example.demo.handler.ex.CustomApiException;
import com.example.demo.handler.ex.CustomException;
import com.example.demo.handler.ex.CustomValidationException;
import com.example.demo.handler.ex.CustomValidationApiException;
import com.example.demo.util.Script;
import com.example.demo.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ControllerAdvice // 모든 exception을 낚아채감.
public class ControllerExceptionHandler {

    // exceotion이 발생하면 낚아채서 (CustomVaildationException.class) 작동.
    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){
        //CMRespDto,Script 비교
        // 1. 클라이언트에게 응답할때는 Script 좋음.
        // 2. Ajax 통신 => CMRespDto
        // 3. Android통신 => CMRespDto

        if(e.getErrorMap() ==null){
            return Script.back(e.getMessage());
        }else{
            return Script.back(e.getErrorMap().toString());
        }
    }

    // exceotion이 발생하면 낚아채서 (CustomVaildationException.class) 작동.
    @ExceptionHandler(CustomValidationApiException.class)
    public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e) {
        return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    // exceotion이 발생하면 낚아채서 (CustomVaildationException.class) 작동.
    @ExceptionHandler(CustomException.class)
    public String exception(CustomException e){
        return Script.back(e.getMessage());
    }

}
