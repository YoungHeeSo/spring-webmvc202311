package com.spring.mvc.chap05.dto.response;

import lombok.*;

import java.util.List;

@Setter @Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyListResponseDTO {

    private int count; // 총 댓글 수
    private List<ReplyDetailResponseDTO> replies; // 실제 댓글 리스트


}
