package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.common.Page;
import com.spring.mvc.chap05.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    //    목록 조회
    List<Board> findAll(Page page); // 게시물 정보가 들어있는 리스트

    //    상세 조회
    Board findOne(int boardNo); // 특정 글 하나를 구분하기 위해서 글 번호를 받아

    //    게시물 등록
    boolean save(Board board); // 게시물 등록의 성공 실패

    //    게시물 삭제
    boolean deleteByNo(int boardNo); // 게시물 번호를 받아서 삭제하겠다

    //    조회수 상승 기능
    void updateViewCount(int boardNo);

    //    총 게시물 수 구하기
    int count();
}
