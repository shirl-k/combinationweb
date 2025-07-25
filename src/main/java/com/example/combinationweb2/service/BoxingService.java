package com.example.combinationweb2.service;

import com.example.combinationweb2.entity.Boxing;
import com.example.combinationweb2.repository.BoxingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

