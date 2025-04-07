package com.jpatest.jpatest.service;

import com.jpatest.jpatest.DTO.MemberDto;
import com.jpatest.jpatest.Entity.Member;
import com.jpatest.jpatest.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public void insert(MemberDto memberDto) {


        // dto -> entity
        Member member = memberDto.createMember();
        memberRepository.save(member);
    }

    public boolean select(MemberDto memberDto) {

      Member member = memberRepository.findByMemberIdAndPassword(
                memberDto.getMemberId(), memberDto.getPassword()
        );
        if(member == null)
            return false;
        return true;


    }
}
