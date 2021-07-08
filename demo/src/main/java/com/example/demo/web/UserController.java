package com.example.demo.web;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.domain.user.User;
import com.example.demo.service.UserService;
import com.example.demo.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable Long pageUserId ,
                          Model model,
                         @AuthenticationPrincipal PrincipalDetails principalDetails){
        UserProfileDto dto = userService.userProfile(pageUserId,principalDetails.getUser().getId());
        model.addAttribute("dto", dto);
        return "user/profile";
    }

  @GetMapping("/user/{id}/update")
    public String update(@PathVariable Long id,
                         @AuthenticationPrincipal PrincipalDetails principalDetails) {

      return "user/update";
  }
}

