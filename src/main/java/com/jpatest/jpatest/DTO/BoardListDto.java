package com.jpatest.jpatest.DTO;


import com.jpatest.jpatest.Entity.Board;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Getter
@Setter
public class BoardListDto {
    private int id;
    private String title;
    private String memberId;
    private int hit;
    private LocalDate writeDate;

    public static ModelMapper modelMapper = new ModelMapper();

    public static BoardListDto from (Board board){
        return modelMapper.map(board , BoardListDto.class);
                              //출발지(~서부터) , 목적지(~로)


    }
}
