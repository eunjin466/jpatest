package com.jpatest.jpatest.Repository;

import com.jpatest.jpatest.Entity.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    public List<Board> findAllByOrderByIdDesc(Pageable pageable);
}
// Jpa 사용시 메서드 이름 짓는 방법
// 기본적으로 제공하는 메서드는 save(insert into), delete(delete), findxxxx(select)
/*
*   쿼리문 - select *from board
*   = 메서드 -  findByAll() : 테이블 전체의 레코드 가져오기 (정렬, 페이징 가능)
*
*   쿼리문 - select count(*) from board
*   = 메서드 - count() : 테이블 전체 레코드 갯수 가져오기
*
*   쿼리문 - select * from board where id = 4
*   = 메서드 - findById : primary key인 컬럼으로 조회한다
*
*   쿼리문 - select * from board where member_id = 'jkr123'
*   = 메서드 - findByMemberId : 테이블에서 member_id 조건으로 조회
*
*   쿼리문 - select * from board where title = '가나다' And hit = 10
*   = 메서드 - findByTitleAndHit
*
*   쿼리문 - select * from board where hit=20 or write_date =2025-04-04
*   = 메서드 - findByHitOrWriteDate(
*
*   쿼리문 - select * from board where hit >=50
*   = 메서드 - findByHitGreaterThanEqual
*
*   쿼리문 - select * from board where hit <= 100
*   = 메서드 - findByHitLessThanEqual
*
*   쿼리문 - select * from board where title like '%가나'
*   = 메서드 - findByTitleLike("%가나")
*           - findByTitleLike("가%")
*           - findByTitleLike("%가%")     => like는 % 자리도 신경쓰기
*           -> % 없이 그냥 "%가%" 이렇게 동작 되게 하려면
*               = findByTitleContaining(String title)
*           -> findByTitleStatingWith  = "%가%"
*           -> findByTitleEndingWith   = "%가"
*
*   쿼리문 - select * from board order by id desc
*   = 메서드 - findByOrderByDesc
*
*   쿼리문 - select * from board order by id desc limit 0, 10
*   = 메서드 - findTop10ThanOrderByIdDesc
*           -> id 컬럼 기준으로 내림차순 정렬하고 최대 10개 조회
*           = findAllByOrderByIdDesc( Pageable ) -> 이 방식이 좀 더 깔끔함
*
*           - 네이티브 쿼리 방식
*
*           @Query(Value = "select * from board order by id desc limit 0(매게변수로 넣으면 메게변수값이 변할때마다 변함),10" , nativeQuery = true)
*           boardlist()
*
*   쿼리문 - select * from board where hit between 10 and 100
*   = 메서드 - findByHitBetween(10,100)
*
*   조인 - select * from board b join user u on b.member_id = u.user_id where u.user_id = 'jkr123'
*   = 메서드 - findByMemberIdUserId("jkr123") (BoardRepository에 만들어야함)
*   entity 연관 관계 기반 조인 가능 @OneToOne , @OneToMany , @ManyToOne, @ManyToMany
* */