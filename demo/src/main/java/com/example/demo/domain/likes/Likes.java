package com.example.demo.domain.likes;

import com.example.demo.domain.image.Image;
import com.example.demo.domain.user.User;
import com.sun.javafx.beans.IDProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(//중복이 될수 없음.
                name = "likes_uk",
                columnNames = {"imageId", "userId"}
        )
})
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //하나의 이미지는 백개의 좋아요가 될수있다.
    @JoinColumn(name = "imageId")
    @ManyToOne
    private Image image;

    // 한명의 유저는 여러개를 좋아요할수있다.
    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    private LocalDateTime createDate;

    @PrePersist
    private void createDate(){
        this.createDate = LocalDateTime.now();
    }

}
