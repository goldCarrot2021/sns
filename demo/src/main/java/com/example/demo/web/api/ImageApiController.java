package com.example.demo.web.api;
import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.domain.image.Image;
import com.example.demo.service.ImageService;
import com.example.demo.service.LikesService;
import com.example.demo.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class ImageApiController {

    private final ImageService imageService;
    private final LikesService likesService;

    @GetMapping("/api/image")
    public ResponseEntity<?>imageStory(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                       @PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<Image> images = imageService.imageStory(principalDetails.getUser().getId(),pageable);
        return new ResponseEntity<>(new CMRespDto<>(1,"성공",images), HttpStatus.OK);
    }

    @PostMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> likes(@PageableDefault Long imageId,
                                   @AuthenticationPrincipal PrincipalDetails principalDetails){
        likesService.likes(imageId,principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"좋아요 성공",null),HttpStatus.CREATED);
    }

    @DeleteMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> unLikes(@PageableDefault Long imageId,
                                   @AuthenticationPrincipal PrincipalDetails principalDetails){
        likesService.unLikes(imageId,principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1,"좋아요 삭제성공",null),HttpStatus.CREATED);
    }

}
