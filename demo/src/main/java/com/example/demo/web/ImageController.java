package com.example.demo.web;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.handler.ex.CustomApiException;
import com.example.demo.handler.ex.CustomValidationException;
import com.example.demo.service.ImageService;
import com.example.demo.web.dto.imgae.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageService imageService;

    @GetMapping({"/","/image/stroy"})
    public String story(){
        return "image/story";
    }

    @GetMapping("/image/popular")
    public String popular(){
        return "image/popular";
    }

    @GetMapping("/image/upload")
    public String upload(){
        return "image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto,
                              @AuthenticationPrincipal PrincipalDetails principalDetails){

        if(imageUploadDto.getFile().isEmpty()){
            throw new CustomValidationException("이미지가 첨부되지않았습니다.", null);
        }

        imageService.imageUpload(imageUploadDto,principalDetails);

        return "redirect:/user/"+principalDetails.getUser().getId();
    }
}

