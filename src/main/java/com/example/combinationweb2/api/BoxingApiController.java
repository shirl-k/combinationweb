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
    public ResponseEntity<Boxing> create(@RequestBody BoxingForm dto) {

            Boxing created = boxingService.create(dto);
            return (created != null) ?
                    ResponseEntity.status(HttpStatus.OK).body(created) :
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PatchMapping("/api/boxing/{id}")
    public ResponseEntity<Boxing> update(@PathVariable Long id,
                         @RequestBody BoxingForm dto) {
        Boxing updated = boxingService.update(id,dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //삼항연산자로 수정되면 정상, 안되면 오류
    }
    @DeleteMapping("/api/boxing/{id}")
    public ResponseEntity<Boxing> delete(@PathVariable Long id) {
        Boxing deleted = boxingService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Boxing>> transactionTest
            (@RequestBody List<BoxingForm> dtos) {
        List<Boxing> createdList = boxingService.createBoxing(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
}