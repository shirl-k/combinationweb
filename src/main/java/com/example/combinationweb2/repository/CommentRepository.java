package com.example.combinationweb2.repository;

import com.example.combinationweb2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    //특정 게시글의 모든 댓글 조회
    @Query(value = "SELECT * FROM comment WHERE boxing WHERE boxing_id = :boxingId",
            nativeQuery = true) //value 속성에 실행하려는 쿼리 작성
    List<Comment> findByBoxingId(Long boxingId);
    //특정 닉네임의 모든 댓글 조회
    List<Comment>findByNickname(String nickname);

}
