package com.example.demo.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LIkesRepository extends JpaRepository<Likes,Long> {

    @Modifying
    @Query(value = "INSERT INTO likes(imageId,userId,createDate) VALUES (:imageId, :principalId, now())", nativeQuery = true)
    Long likes(Long imageId, Long principalId);

    @Modifying
    @Query(value = "DELETE FROM likes WHERE imageId=:imageId AND userId=:principalId", nativeQuery = true)
    Long unLikes(Long imageId, Long principalId);
}
