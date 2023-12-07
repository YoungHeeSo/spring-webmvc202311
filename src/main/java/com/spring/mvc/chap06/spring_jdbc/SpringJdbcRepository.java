package com.spring.mvc.chap06.spring_jdbc;

import com.spring.mvc.chap06.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SpringJdbcRepository {

//    데이터 소스 설정 : 데이터베이스 접속 정보 설정( url, id, pw, ... )
//    application.properties 파일에 작성

//    JdbcTemplate 빈 의존성 주입
    private final JdbcTemplate template;

//    INSERT 기능
    public void save(Person p){
        String sql = "insert into person " +
                "(id, person_name, person_age) " +
                "values(?, ?, ?)";

        template.update(sql, p.getId(), p.getPersonName(), p.getPersonAge());
        //수정 삽입 삭제 는 updqte
    }

//    DELETE 기능
    public void remove(String id){
        String sql = "delete from person where id = ?";
        template.update(sql, id);
    }

//    UPDATE 기능
    public void modify(Person p){
        String sql = "update person " +
                "set person_name=?, person_age=? " +
                "where id=?";
        template.update(sql, p.getPersonName(), p.getPersonAge(), p.getId());
    }

//    전체 조회, SELECT 기능
    public List<Person> findAll(){
        String sql = "select * from person";

//        rowMapper : DB에서 조회한 것들을 객체로 어떻게 매칭할지 설정

//        람다식
//        return template.query(sql, (rs, rowNum) -> new Person(rs));

        return template.query(sql, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new Person(rs);
            }
        });
    }

//    단일 조회
    public Person findOne(String id){
        String sql = "select * from person where id = ?";

//        id 부분은 ? 채우기
        return template.queryForObject(sql, (rs, rowNum) -> new Person(rs), id);
    }


}
