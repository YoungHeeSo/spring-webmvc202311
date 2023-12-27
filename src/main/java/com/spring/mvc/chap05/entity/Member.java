package com.spring.mvc.chap05.entity;

import com.spring.mvc.chap05.dto.response.LoginUserResponseDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

//    account VARCHAR(50),
//    password VARCHAR(150) NOT NULL,
//    name VARCHAR(50) NOT NULL,
//    email VARCHAR(100) NOT NULL UNIQUE,
//    auth VARCHAR(20) DEFAULT 'COMMON',
//    reg_date DATETIME DEFAULT current_timestamp,
//    CONSTRAINT pk_member PRIMARY KEY (account)

    private String account;
    private String password;
    private String name;
    private String email;
    private Auto auto;
    private LocalDateTime regDate;
    private LoginUserResponseDTO auth;

}
