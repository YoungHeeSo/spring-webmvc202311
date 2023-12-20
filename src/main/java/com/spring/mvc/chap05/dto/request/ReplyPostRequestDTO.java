package com.spring.mvc.chap05.dto.request;

import com.spring.mvc.chap05.entity.Reply;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//    객체는 상태 불변성이 중요하므로 setter 사용 주의할 것.
@Setter @Getter @ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyPostRequestDTO {

    // NotBlank : null 안됨! 빈문자열 안됨!
    // NotNull : null 안됨! 빈문자열은 됨!
    @NotBlank
    @Size(min=1, max = 300)
    private String text;    // 댓글 내용

    @NotBlank
    @Size(min = 2, max = 8)
    private String author;  // 댓글 작성자

    @NotNull
    private Long bno;       // 원본 글번호

    // dto를 entity으로 바꾸는 변환 메서드
    public Reply toEntity(){

        return Reply.builder()
                .replyText(this.text)
                .replyWriter(this.author)
                .boardNo(this.bno)
                .build();
    }

}
