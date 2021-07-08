package com.example.demo.service;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.domain.image.Image;
import com.example.demo.domain.image.ImageRepository;
import com.example.demo.web.dto.imgae.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional(readOnly = true) // readOnly 걸면 영속성 컨텍스트에서 변경 감지해서 더티체킹, flush(반영) 하지않음.
    public Page<Image> imageStory(Long principalId, Pageable pageable){
        Page<Image> images = imageRepository.mStroy(principalId,pageable);
        return images;
    }


    @Value("${file.path}")
    private String uploadFolder;

    public void imageUpload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails){
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename();

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        //통신 , I/O -> 예외가 발생할 수 있다.
        try {

            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }

        Image image = imageUploadDto.toEntity(imageFileName,principalDetails.getUser());
        Image imageEntity = imageRepository.save(image);

    }


}
