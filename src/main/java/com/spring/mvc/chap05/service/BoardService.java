package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.BoardDetailResponseDTO;
import com.spring.mvc.chap05.dto.BoardListResponseDTO;
import com.spring.mvc.chap05.dto.BoardWriteRequestDTO;
import com.spring.mvc.chap05.entity.Board;
import com.spring.mvc.chap05.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    /*@Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }*/

//  목록 조회 중간 처리
    public List<BoardListResponseDTO> getList(){

        return boardRepository.findAll()
                .stream()
                .map(BoardListResponseDTO::new)
                .collect(Collectors.toList());
    }

//    글 쓰기 중간 처리
    public void register(BoardWriteRequestDTO dto) {
//        dto 를 엔터티로 변환
        Board board = new Board(dto);
        boardRepository.save(board);

    }

    public void delete(int boardNo) {
        boardRepository.deleteByNo(boardNo);
    }

    public BoardDetailResponseDTO getDetail(int bno) {

        Board board = boardRepository.findOne(bno);

//        조회수 상승 처리
        board.upViewCount();

        return new BoardDetailResponseDTO(board);
    }
}