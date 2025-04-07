package com.jpatest.jpatest.control;


import com.jpatest.jpatest.DTO.MemberDto;
import com.jpatest.jpatest.Entity.Member;
import com.jpatest.jpatest.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
    @Autowired
    private MemberService memberService;

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
}
