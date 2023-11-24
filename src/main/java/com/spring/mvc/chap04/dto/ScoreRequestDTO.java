package com.spring.mvc.chap04.dto;

import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ScoreRequestDTO {

    private String name;
    private int kor, eng, math;
}
