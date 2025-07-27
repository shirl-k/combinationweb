package com.example.combinationweb2.service;

import com.example.combinationweb2.entity.Boxing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoxingServiceTest {

    @Autowired
    BoxingService boxingService; //boxingService 객체 주입
    @Test
    void index() {
        //1, 예상 데이터
        Boxing a = new Boxing (1L, "가가가가", "1111")
        Boxing b = new Boxing (2L, "가가가가", "1111")
        Boxing c = new Boxing (3L, "가가가가", "1111")
        //2. 실제 데이터
        List<Boxing> boxing = boxingService.index();
    }
}