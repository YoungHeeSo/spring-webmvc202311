package com.spring.mvc.chap04.entity;

import lombok.*;

/**
 * 엔터티 클래스
 * - 데이터베이스에 저장할 데이터를 자바 클래스에 매칭
 */

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    private String name; // 학생 이름

    private int kor, eng, math; // 국영수 점수
    private int stuNum; // 학번
    private int total; // 총점
    private double average; // 평균

    private Grade grade; // 학점



}
