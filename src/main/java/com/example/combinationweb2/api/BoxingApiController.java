package com.example.combinationweb2.api;

import com.example.combinationweb2.dto.BoxingForm;
import com.example.combinationweb2.entity.Boxing;
import com.example.combinationweb2.repository.BoxingRepository;
import com.example.combinationweb2.service.BoxingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@Slf4j
@RestController
public class BoxingApiController {
    @Autowired
    private BoxingService boxingService;
//    @Autowired
//    private BoxingRepository boxingRepository;
//    //GET
    @GetMapping("/api/boxing")
    public List<Boxing> index() {
        return boxingService.index();
    }
    @GetMapping("/api/boxing/{id}")
    public Boxing show(@PathVariable Long id) {
        return boxingService.show(id);
    }
    @PostMapping("/api/boxing")
    public Boxing create(@RequestBody BoxingForm dto) {
        Boxing boxing = dto.toEntity();
        return boxingRepository.save(boxing);
    }
//    @PatchMapping("/api/boxing/{id}")
//    public ResponseEntity<Boxing> update(@PathVariable Long id,
//                         @RequestBody BoxingForm dto) {
//        Boxing boxing = dto.toEntity();
//        log.info("id:{}, boxing: {}", id, boxing.toString());
//        Boxing target = boxingRepository.findById(id).orElse(null);
//        if (target == null || id !=boxing.getId()) {
//            log.info("잘못된 요청! id: {}, boxing: {}",id,boxing.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }  //업데이트 및 정상 응답 (200) 하기
//            target.patch(boxing); //기존 데이터에 새 데이터 붙이기
//            Boxing updated = boxingRepository.save(target); //
//            return ResponseEntity.status(HttpStatus.OK).body(updated); //정상 응답
//    }
//    @DeleteMapping("/api/boxing/{id}")
//    public ResponseEntity<Boxing> delete(@PathVariable Long id) {
//        Boxing target = boxingRepository.findById(id).orElse(null);
//        if(target == null) {return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);}
//        boxingRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//
//    }

}