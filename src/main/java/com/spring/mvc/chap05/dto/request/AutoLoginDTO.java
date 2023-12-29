package com.spring.mvc.chap05.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString @Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutoLoginDTO {

    private String sessionId;
    private LocalDateTime limitTime;
    private String account;

}
