package com.example.combinationweb2.service;

import com.example.combinationweb2.dto.BoxingForm;
import com.example.combinationweb2.entity.Boxing;
import com.example.combinationweb2.repository.BoxingRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BoxingService {
    @Autowired
    private BoxingRepository boxingRepository; // 게시글 리포지토리 주입
    public List<Boxing> index() {
        return boxingRepository.findAll();
    }

    public Boxing show(Long id) { //show 메서드
        return boxingRepository.findById(id).orElse(null); //조회 결과 데이터가 없으면 null값 반환
    }

    public Boxing create(BoxingForm dto) {
        Boxing boxing = dto.toEntity();
        if(boxing.getId() != null) {
            return null;
        }
        return boxingRepository.save(boxing);
    }

    public Boxing update(Long id, BoxingForm dto) {
        Boxing boxing = dto.toEntity();
        log.info("id:{}, boxing: {}", id, boxing.toString());
        Boxing target = boxingRepository.findById(id).orElse(null);
        if (target == null || id !=boxing.getId()) {
            log.info("잘못된 요청! id: {}, boxing: {}",id,boxing.toString());
            return null;
        }  //업데이트 및 정상 응답 (200) 하기
        target.patch(boxing); //기존 데이터에 새 데이터 붙이기
        Boxing updated = boxingRepository.save(target); //
        return target;
    }

    public Boxing delete(Long id) {
        Boxing target = boxingRepository.findById(id).orElse(null);
        if(target == null) {
            return null;
        }
        boxingRepository.delete(target);
        return target;
    }
    @Transactional
    public List<Boxing> createBoxing(List<BoxingForm> dtos) {
        List<Boxing>boxingList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        boxingList.stream()
                .forEach(boxing -> boxingRepository.save(boxing));
        boxingRepository.findById(-1L)
                .orElseThrow(()-> new IllegalArgumentException("결제 실패"));
        return boxingList;
    }
}

