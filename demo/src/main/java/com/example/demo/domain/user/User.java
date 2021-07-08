package com.example.demo.domain.user;

import com.example.demo.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //번호 증가 전력이 데이터 베이스를 따라간다.
    private Long id;

    @Column(length = 20, unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String website;
    private String bio;

    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImgaeUrl;
    private String role;

    // mapped by =user => 나는 연관관계의 주인이 아니다. 그러므로 테이블에 칼럼을 만들지마.
    // User를 Select할때 해당 User id로 등록된 image들을 다 가져와.
    // FetchType.LAZY 일때는 User를 SELECT할때 해당 User id로 등록된 image들을 가져오지마
    // 대신 getImages() 함수의 image들이 호출 될떄 가져와 .
    // FetchType.Eager 일때는 User를 SELECT할떄 해당 User id로 등록된 image를 전부 join해서 가져와
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"user"})
    private List<Image> images;

    private LocalDateTime createDate;

    @PrePersist
    private void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
