package com.example.combinationweb2.api;

import com.example.combinationweb2.dto.CommentDto;
import com.example.combinationweb2.entity.Comment;
import com.example.combinationweb2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //REST 콘트롤러 선언
public class CommentApiController {
    @Autowired
    private CommentService commentService; //댓글 서비스 객체 주입

    //2.댓글 생성 (Create - POST 메서드 (HTTP) )
    @PostMapping("/api/boxing/{boxingId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long boxingId,
                                             @RequestBody CommentDto dto) {
        //서비스에 위임
        CommentDto createdDto = commentService.create(boxingId, dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    //1.댓글 조회 (Read - GET 메서드)
    @GetMapping("/api/boxing/{boxingId}/comments") //댓글 조회 요청 접수
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long boxingId) { //comments()메서드 생성
        //메서드 반환형 : ResponseEntity<List<CommentDto>>
        
        //서비스에 위임
        List<CommentDto> dtos = commentService.comments(boxingId);
        //결과에 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //3.댓글 수정 (Update - PATCH 메서드)
    @PatchMapping("/api/comments/{id}") //댓글 수정 요청 접수
    public ResponseEntity<CommentDto>update(@PathVariable Long id,
                                            @RequestBody CommentDto dto) {
        //서비스에 위임 (댓글 컨트롤러가 댓글 서비스에 위임)
        CommentDto updatedDto = commentService.update(id,dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }
    //4.댓글 삭제 (Delete - DELETE 메서드)
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        //서비스에 위임
        CommentDto deletedDto = commentService.delete(id);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
