package com.example.demo.service;

import com.example.demo.domain.subscribe.SubscribeRepository;
import com.example.demo.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeServcie {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void subscribe(Long fromUserId, Long toUserId){
        try {
            subscribeRepository.mSubscribe(fromUserId, toUserId);
        }catch (Exception e){
            throw new CustomApiException("이미 구독을 하였습니다.");
        }

    }

    @Transactional
    public void unSubscribe(Long fromUserId, Long toUserId){
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }
}
