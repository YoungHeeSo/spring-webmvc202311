package com.spring.mvc.chap05.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter @ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class LoginRequestDTO {

    private String account;
    private String password;
    private boolean autoLogin; // 자동 로그인 체크 여부
}
