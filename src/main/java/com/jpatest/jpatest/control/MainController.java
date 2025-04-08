package com.jpatest.jpatest.control;


import com.jpatest.jpatest.DTO.BoardDto;
import com.jpatest.jpatest.DTO.BoardListDto;
import com.jpatest.jpatest.DTO.CommentDto;
import com.jpatest.jpatest.DTO.MemberDto;
import com.jpatest.jpatest.Entity.Member;
import com.jpatest.jpatest.service.BoardService;
import com.jpatest.jpatest.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String home(){

        return "index";
    }

    //회원가입
    @GetMapping("/member/signUp")
    public String signUp(Model model){


        // 회원가입 페이지에서 작성한 내용을  DTO에 담는다
        model.addAttribute("memberDto", new MemberDto());


        return "signUp";
    }
    @PostMapping("/member/signUp")
        public String signUp(MemberDto memberDto, Model model){

        memberService.insert( memberDto );

            return "redirect:/";
        }
     @GetMapping("/member/signIn")
    public String signIn(Model model){

        model.addAttribute("memberDto", new MemberDto());
        return "signIn";
     }
    @PostMapping("/member/signIn")
    public String signIn(MemberDto memberDto, HttpSession session){

        boolean isSession = memberService.select(memberDto);
        if(isSession) // 아이디 비번 일치하면 true
            session.setAttribute("user", memberDto.getMemberId() );


        return "redirect:/";
    }
    //게시판 목록 페이지 띄우기
    @GetMapping("/board/index/{page}")
    public String boardHome(@PathVariable("page") Integer pageNum,Model model){
        //BoardServcie 클래스의 메서드를 호출하여 10개의 게시글을 가져온다
        // 한페이지에 10개씩 출력할 것이다
        // 페이징을 위해 필요한 값도 가져올 것이다.(전체 게시글)
        // Optional - null pointerException 방지를 위해(주의 : 매개변수 사용안하기, 변수(필드)에서 사용안하기)
        // -> 어디에 사용하는가? : 메서드의 리턴타입으로만 사용하는 것이 좋다 ( 무조건 지키라는 건 아니지만 권장 사항 )
        // Optional을 사용해서 만든다면 @PathVariable("page") Integer pageNum,
        // -> int currentPage = pageNum.orElse(0);

        int currentPage = (pageNum != null) ? pageNum : 0;
        Pageable pageable = PageRequest.of(currentPage, 10);
        // 한페이지에 10개씩 보여주기 위해 10을 넣었고, 0 인덱스 부터 limit 들어가야한다.

        Page<BoardListDto> boardListDtos = boardService.getBoardList( pageable );

        model.addAttribute("boardListDtos", boardListDtos);
        model.addAttribute("page", pageable.getPageNumber() );
        model.addAttribute("maxPage", 10);

         return "boardList";
    }

    // 게시판 상세 보기
    @GetMapping("/board/detail")
    public String detail(@RequestParam("id") int boardId, Model model){

        BoardDto boardDto = boardService.getBoard(boardId);
        model.addAttribute("boardDetailDto", boardDto);
        model.addAttribute("commentDto", new CommentDto());

        return "detail";
    }
}
// entity, dto -> repository -> service -> control