package com.example.demo.web.dto.user;


import com.example.demo.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
public class UserProfileDto {

    private int pageOwnerState;
    private int imageCount;
    private int subscribeState;
    private int subscribeCount;
    private User user;

}
