package com.example.combinationweb2.service;

import com.example.combinationweb2.dto.CommentDto;
import com.example.combinationweb2.entity.Boxing;
import com.example.combinationweb2.entity.Comment;
import com.example.combinationweb2.repository.BoxingRepository;
import com.example.combinationweb2.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoxingRepository boxingRepository;

    public List<CommentDto> comments(Long boxingId) {
        //1. 댓글 조회
       //List<Comment>comments = commentRepository.findByBoxingId((boxingId));

        //2. 엔티티 => DTO 변환
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for(int i=0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
      //  }
        //3. 결과 변환
        return commentRepository.findByBoxingId(boxingId)//댓글 엔티티 목록 조회
                .stream() //댓글 엔티티 목록을 스트림으로 변환
                .map(comment -> CommentDto.createCommentDto(comment)) //엔티티를 DTO로 매핑
                .collect(Collectors.toList());
    }
    @Transactional
    public CommentDto create(Long boxingId, CommentDto dto) {
        //1.게시글 조회 및 예외 발생
        Boxing boxing = boxingRepository.findById(boxingId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패" + "대상 게시글이 없습니다."));

        //2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto,boxing);
        //3. 댓글 엔티티를 DB에 저장 => comment 엔티티 만들기...!
        Comment created = commentRepository.save(comment);

        //4. DTO로 변환해 반환
        return CommentDto.createCommentDto(created);
    }
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        //1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("댓글 수정 실패" + "대상 댓글이 없습니다."));
        //2. 댓글 수정
        target.patch(dto); //메서드 호출해서 기존 댓글 엔티티에 수정 정보 추가
        //3. DB 내용 target으로 갱신
        Comment updated = commentRepository.save(target); //변수 updated에 갱신 결과 저장
        //4. 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }
    @Transactional
    public CommentDto delete(Long id) {
        //1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패" + "대상이 없습니다"));
        //2. 댓글 삭제
        commentRepository.delete(target);

        //3. 삭제 댓글을 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);

    }
}
