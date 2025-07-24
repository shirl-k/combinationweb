package com.example.combinationweb2.dto;

import com.example.combinationweb2.entity.Boxing;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor //DTO 리팩토링
@ToString
public class BoxingForm {
    private Long id;
    private String title;
    private String content;

    public Boxing toEntity() {
        return new Boxing(id, title, content);
    }
}

