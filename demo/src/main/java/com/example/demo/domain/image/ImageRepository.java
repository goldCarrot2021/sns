package com.example.demo.domain.image;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ImageRepository extends JpaRepository<Image,Long> {

    @Query(value = "SELECT * FROM image WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromUserId = :principalId) ORDER BY id DESC", nativeQuery = true)
    Page<Image> mStroy(Long principalId, Pageable pageable);
}
