package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.common.PageMaker;
import com.spring.mvc.chap05.dto.response.ReplyDetailResponseDTO;
import com.spring.mvc.chap05.dto.response.ReplyListResponseDTO;
import com.spring.mvc.chap05.entity.Reply;
import com.spring.mvc.chap05.repository.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {

    private final ReplyMapper replyMapper;

    public ReplyListResponseDTO getList(long boardNo, Page page){

        // DB 에서 댓글 정보 조회
        List<ReplyDetailResponseDTO> replies = replyMapper.findAll(boardNo, page).stream()
                .map(ReplyDetailResponseDTO::new)
                .collect(Collectors.toList());

        // DB 에서 총 댓글 수 조회
        int count = replyMapper.count(boardNo);

        return ReplyListResponseDTO.builder()
                .replies(replies)
                .count(count)
                .pageInfo(new PageMaker(page, count))
                .build();

    }

}
