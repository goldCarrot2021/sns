package com.example.demo.service;

import com.example.demo.domain.subscribe.SubscribeRepository;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.handler.ex.CustomException;
import com.example.demo.handler.ex.CustomValidationApiException;
import com.example.demo.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public UserProfileDto userProfile(Long pageUserId,Long principalId){

        UserProfileDto dto = new UserProfileDto();

        // SELECT * FROM image WHERE userId = : userId;
        User userEntity = userRepository.findById(pageUserId).orElseThrow(
                ()-> {
                    throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
                });

        dto.setUser(userEntity);
        // 1은 페이지 주인 -1은 페이지 주인이 아님.
        dto.setPageOwnerState(pageUserId == principalId ? 1 : -1);
        dto.setImageCount(userEntity.getImages().size());

        int subscribeState = subscribeRepository.mSubscribeState(principalId, pageUserId);
        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);

        dto.setSubscribeState(subscribeState);
        dto.setSubscribeCount(subscribeCount);

        return dto;
    }


    @Transactional
    public User userUpdate(Long id, User user) {
        // 1. 영속화
        User userEntity = userRepository.findById(id).orElseThrow(()-> {
            return new CustomValidationApiException("찾을 수없는 id입니다");
        });

        // 1. 무조건 찾았다 -> get() 2. 못찾음 -> 입섹션 발생 orElseThrow()

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setName(user.getName());
        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());
        // 2. 영속화 된 오브젝트를 수정 - 더티체킹(업데이트 완료)

        return userEntity;
        // 더티체킹이 일어나서 업데이트가 완료됨.
    }

}
