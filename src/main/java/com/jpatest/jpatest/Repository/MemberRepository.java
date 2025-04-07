package com.jpatest.jpatest.Repository;


import com.jpatest.jpatest.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{


    public  Member findByMemberIdAndPassword(String memberId, String password);

}
