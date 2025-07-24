package com.example.combinationweb2.api;

import com.example.combinationweb2.dto.BoxingForm;
import com.example.combinationweb2.entity.Boxing;
import com.example.combinationweb2.repository.BoxingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@Slf4j
@RestController
public class BoxingApiController {
    @Autowired
    private BoxingRepository boxingRepository;
    //GET
    @GetMapping("/api/boxing")
    public List<Boxing> index() {
        return boxingRepository.findAll();
    }
    @GetMapping("/api/boxing/{id}")
    public Boxing show(@PathVariable Long id) {
        return boxingRepository.findById(id).orElse(null);
    }
    @PostMapping("/api/boxing")
    public Boxing create(@RequestBody BoxingForm dto) {
        Boxing boxing = dto.toEntity();
        return boxingRepository.save(boxing);
    }
    @PatchMapping("/api/boxing/{id}")
    public ResponseEntity<Boxing> update(@PathVariable Long id,
                         @RequestBody BoxingForm dto) {
        Boxing boxing = dto.toEntity();
        log.info("id:{}, boxing: {}", id, boxing.toString());
        Boxing target = boxingRepository.findById(id).orElse(null);
        if (target == null || id !=boxing.getId()) {
            log.info("잘못된 요청! id: {}, boxing: {}" )
        }
    }

}
