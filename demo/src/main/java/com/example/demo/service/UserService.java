package com.example.demo.service;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.handler.ex.CustomException;
import com.example.demo.handler.ex.CustomValidationApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void userProfile(Long userId){
        // SELECT * FROM image WHERE userId = : userId;
        User userEntity = userRepository.findById(userId).orElseThrow(
                ()-> {
                    throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
                });
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
