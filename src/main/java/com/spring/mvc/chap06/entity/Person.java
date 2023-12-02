package com.spring.mvc.chap06.entity;

import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String id;
    private String personName;
    private int personAge;

}
