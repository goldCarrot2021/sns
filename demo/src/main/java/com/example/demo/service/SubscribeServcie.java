package com.example.demo.service;

import com.example.demo.domain.subscribe.SubscribeRepository;
import com.example.demo.handler.ex.CustomApiException;
import com.example.demo.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.awt.geom.QuadCurve2D;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeServcie {

    private final SubscribeRepository subscribeRepository;

    // Repository는 EntityManager를 구현해서 만들어져있는 구현체
    private final EntityManager em;

    @Transactional(readOnly = true)

    public List<SubscribeDto> subscribeList(Long principalId,Long pageUserId){

        StringBuffer sb = new StringBuffer();
        // 끝에 한칸씩 띄워쓰기하고 마지막에 세미콜론(;) 붙이면 안됨.
        sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
        sb.append("if ((SELECT 1 FROM subscribe WHERE fromUserId = ? AND toUserId = u.id), 1 , 0 ) subscribeState ");
        sb.append("if (( ? =u.id),1,0) equalUserState ");
        sb.append("FROM user u INNER JOIN subscribe s ");
        sb.append("ON u.id = s.toUserId ");
        sb.append("WHERE s.fromUserId = ?");

        // 첫번쨰 물음표 principalid
        // 두번쨰 물음표 principlaId
        // 마지막 물음표 pageUserId

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);
        // 퀴리 실행
        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtos = result.list(query,SubscribeDto.class);

        return subscribeDtos;
    }

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
