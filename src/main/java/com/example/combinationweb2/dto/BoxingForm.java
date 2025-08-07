package com.example.combinationweb2.dto;

import com.example.combinationweb2.entity.Boxing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor //DTO 리팩토링 - 모든 필드를 매개변수로 갖는 생성자 자동 생성
@ToString
@Getter
@Setter
public class BoxingForm {
    private Long id;
    private String title;
    private String content;

    public Boxing toEntity() {
        return new Boxing(id, title, content);
    }
}

