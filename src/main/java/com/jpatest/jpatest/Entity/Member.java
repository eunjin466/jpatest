package com.jpatest.jpatest.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    private String memberId;
    private String password;
    private String name;
    private String email;
    private String tel;

}
// @Column( name = "user_id")
// @column( length = 50) varchar(50) 설정
// @Column( nullable = false) null을 허용하지 않겠다.


