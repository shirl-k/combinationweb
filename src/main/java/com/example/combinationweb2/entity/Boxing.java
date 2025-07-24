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


}
