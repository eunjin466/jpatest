package com.jpatest.jpatest.Repository;

import com.jpatest.jpatest.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    public List<Comment> findByBoardId(int boardId);
}
