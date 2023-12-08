package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap05.entity.Board;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Repository()
public class BoardRepositoryImpl implements BoardRepository{

    static private final Map<Integer, Board> boardMap;

//    글번호 자동으로 증가시키기 위한 공유필드
    private static int sequence;

    static {
        boardMap = new HashMap<>();

        Board b1 = new Board(++sequence, "오늘 수업 집중이 잘 되는 건에 대하여", "근데 배고픔");
        Board b2 = new Board(++sequence, "와플 먹고 싶다", "와플 칸 언제 열러 피자와플 먹고싶음");
        Board b3 = new Board(++sequence, "오늘 마트 가서 뭐사지", "계란, 목살, 고구마, 목살");

        boardMap.put(b1.getBoardNo(), b1);
        boardMap.put(b2.getBoardNo(), b2);
        boardMap.put(b3.getBoardNo(), b3);
    }

    @Override
    public List<Board> findAll() {

        return boardMap.values()
                .stream()
                .sorted(Comparator.comparing(Board::getBoardNo).reversed()) // 신규 게시물을 위해 내림차 정렬
                .collect(Collectors.toList());
    }

    @Override
    public Board findOne(int boardNo) {
        return boardMap.get(boardNo);
    }

    @Override
    public boolean save(Board board) {
        board.setBoardNo(++sequence);
        boardMap.put(board.getBoardNo(), board);
        return true;
    }

    @Override
    public boolean deleteByNo(int boardNo) {
        boardMap.remove(boardNo);
        return true;
    }
}
