package com.example.demo.web.api;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.domain.user.User;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.CMRespDto;
import com.example.demo.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable Long id,
                               UserUpdateDto userUpdateDto,
                               @AuthenticationPrincipal PrincipalDetails principalDetails){


        User userEntity = userService.userUpdate(id, userUpdateDto.toEntity());
        principalDetails.setUser(userEntity); //세션 정보 변경

        return new CMRespDto<>(1,"회원수정완료",userEntity);
    }

}
