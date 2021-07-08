package com.example.demo.service;

import com.example.demo.domain.likes.LIkesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LIkesRepository lIkesRepository;

    @Transactional
    public void likes(Long imageId,Long principalId){
        lIkesRepository.likes(imageId, principalId);
    }

    @Transactional
    public void unLikes(Long imageId,Long principalId){
        lIkesRepository.unLikes(imageId, principalId);
    }
}
