package com.jpatest.jpatest.DTO;

import com.jpatest.jpatest.Entity.Comment;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;


import java.time.LocalDate;

@Getter
@Setter
public class CommentDto {
    private int id;
    private int boardId;
    private String content;
    private LocalDate writeDate;
    public static ModelMapper modelMapper = new ModelMapper();

    public static CommentDto from(Comment comment){
        return modelMapper.map(comment, CommentDto.class);
    }
}
