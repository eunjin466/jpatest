package com.jpatest.jpatest.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name ="board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String title;

    private String memberId;
    private String content;
    private String fileName;
    private int hit;
    private LocalDate writeDate;
}
//@Column (unique = true)