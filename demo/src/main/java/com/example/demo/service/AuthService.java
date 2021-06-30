package com.example.demo.service;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service // 1. Ioc 관리 2. 트랙잭션 관리
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional //Write(insert,upate,delete)
    public User signup(User user){

        String rawPassword = user.getPassword();
        String endPassword = bCryptPasswordEncoder.encode(rawPassword);

        user.setPassword(endPassword);
        user.setRole("ROLE_USER");

        User userEntity = userRepository.save(user);

        return userEntity;
    }
}
