package com.example.demo.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface SubscribeRepository extends JpaRepository<Subscribe,Long> {

    @Modifying
    @Query(value = "INSERT INTO subscribe(fromUserId,toUserId,createDate) VALUES(:fromUserId,:toUserId,now())",nativeQuery = true)
    void mSubscribe(Long fromUserId, Long toUserId); //성공하면 1, 실패하면 -1 리턴.

    @Modifying //데이터베이스에 변경이 필요한 쿼리에 붙는 어노테이션 //INSERT,DELETE,UPDATE를 네이티브 쿼리로 작성하려면 필요.
    @Query(value = "DELETE FROM subcribe WHERE fromUserId = : fromUserId AND toUserId =:toUserId",nativeQuery = true)
    void mUnSubscribe(Long fromUserId, Long toUserId);

}
