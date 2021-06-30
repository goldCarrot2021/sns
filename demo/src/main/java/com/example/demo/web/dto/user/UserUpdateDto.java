package com.example.demo.web.dto.user;

import com.example.demo.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {

    private String name;

    private String password;

    private String website;
    private String bio;
    private String phone;
    private String gender;

    public User toEntity(){
        return User.builder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }

}
