package com.example.demo.web;

import com.example.demo.domain.user.User;
import com.example.demo.handler.ex.CustomValidationException;
import com.example.demo.service.AuthService;
import com.example.demo.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor // final 붙은 필드를 DI할때 사용.
@Controller// 1.Ioc 2.파일을 리턴하는 컨트롤러
public class AuthContorller {

    private static final Logger log = LoggerFactory.getLogger(AuthContorller.class);

    private final AuthService authService;

    @GetMapping("/auth/signup")
    public String signupPage() {
        return "auth/signup";
    }

    @GetMapping("/auth/signin")
    public String signinPage() {
        return "auth/signin";
    }

    //회원가입 버튼 -> /auth/signup -> /auth/signin
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult){
    // @valid에서 에러가 터지면 에러 결과 값이 BindingResult에 다 들어감.

        // BindingResult에 에러가 있다면
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();

            //에러들을 다 담아서
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(),error.getDefaultMessage());
                System.out.println(error.getDefaultMessage());
            }

            // 에러를 강제로 생성하고 담은 에러메세지를 던짐.
            throw new CustomValidationException("유효성 검사 실패",errorMap);

        }else{

            log.info(signupDto.toString());

            User user = signupDto.toEntity();
            User userEntity = authService.signup(user);

            System.out.println(userEntity.toString());
            return "auth/signin";
        }

    }
}
