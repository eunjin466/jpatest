package com.jpatest.jpatest.service;

import com.jpatest.jpatest.DTO.BoardDto;
import com.jpatest.jpatest.DTO.BoardListDto;
import com.jpatest.jpatest.DTO.CommentDto;
import com.jpatest.jpatest.Entity.Board;
import com.jpatest.jpatest.Entity.Comment;
import com.jpatest.jpatest.Repository.BoardRepository;
import com.jpatest.jpatest.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;


    //@Autowired 또는 lombok의 @RequiredArgsConstructor를 사용하여 주입. 주입 저네 반드시 빈등록 필수.
    //빈 - @Service, @Repository, @Bean 등등
    public BoardDto getBoard(int boardId){
        Board board = boardRepository.findById(boardId).get();

        List<Comment> commentList = commentRepository.findByBoardId(boardId);
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : commentList){
            CommentDto commentDto = CommentDto.from(comment);
            commentDtos.add(commentDto);
        }
        BoardDto boardDto = BoardDto.of(board,commentDtos);
        return boardDto;
    }


    //게시글 10개 가져와서 목록 출력하기 위한 메서드
    public Page<BoardListDto> getBoardList(Pageable pageable){
        List<BoardListDto> boardListDtos = new ArrayList<>();

        List<Board> boards = boardRepository.findAllByOrderByIdDesc(pageable);

        //전체 게시글 갯수
        Long total = boardRepository.count();

        //entity -> dto
        for(Board board : boards){
            BoardListDto boardListDto = BoardListDto.from(board);
            boardListDtos.add(boardListDto);
        }

        return new PageImpl<>(boardListDtos, pageable, total);
    }

}
