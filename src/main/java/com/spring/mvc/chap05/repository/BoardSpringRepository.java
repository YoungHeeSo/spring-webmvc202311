package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository @RequiredArgsConstructor
public class BoardSpringRepository implements BoardRepository{

    private final JdbcTemplate template;

    @Override
    public List<Board> findAll() {
        String sql  = "select * from tbl_board order by board_no desc";
        return template.query(sql, (rs, rn) -> new Board(rs));
    }

    @Override
    public Board findOne(int boardNo) {
        String sql = "select * from tbl_board where board_no = ?";
        return template.queryForObject(sql, (rs, rn) -> new Board(rs), boardNo);
    }

    @Override
    public boolean save(Board board) {
        String sql = "insert into tbl_board (title, content)" +
                "values (?, ?)";
        return template.update(sql, board.getTitle(), board.getContent()) == 1;
    }

    @Override
    public boolean deleteByNo(int boardNo) {
        String sql = "delete from tbl_board where board_no = ? ";
        return template.update(sql, boardNo) == 1;
    }

    @Override
    public void updateViewCount(int boardNo) {
        BoardRepository.super.updateViewCount(boardNo);
        String sql = "update tbl_board " +
                "set view_count = view_count + 1 " +
                "where board_no = ?";

        template.update(sql, boardNo);
    }
}
