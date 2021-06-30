package com.example.demo.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션이 없어도 JpaRepository를 상속하면 Ioc 등록이 자동으로 된다.
public interface UserRepository extends JpaRepository<User,Long> {

    //JPA naming Query
    User findByUsername(String username);

}
