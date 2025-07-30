package com.example.combinationweb2.entity;
//Boxing 클래스 만들기, DTO를 엔티티로 변환 ---->

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
public class Boxing {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB id 자동생성
    private Long id;
    @Column
    private String title;
    @Column
    private String content;




    public void patch(Boxing boxing) { //patch 메서드 : 수정할 내용 있는 경우에만 동작
        if(boxing.title != null)
            this.title = boxing.title;
        if(boxing.content !=null)
            this.content= boxing.content; //갱신할 값이 있으면 this(target)의 title 갱신
    }

}
