package com.spring.mvc.chap05.dto.response;

import lombok.*;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class LoginUserResponseDTO {

    private String account;
    private String nickName;
    private String email;
    private String auth;
    private String profile;
    private String loginMethod;

}
