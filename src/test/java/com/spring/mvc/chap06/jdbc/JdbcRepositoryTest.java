package com.spring.mvc.chap06.jdbc;

import com.spring.mvc.chap06.entity.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcRepositoryTest {

    @Autowired
    JdbcRepository repository;

    @Test
    @DisplayName("데이터베이스 접속에 성공해야 한다.")
    void connectTest() {
        try {
            Connection connection = repository.getConnection();

            assertNotNull(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    @DisplayName("사람 객체 정보를 데이터베이스에 삽입해야 한다")
    void saveTest(){
        //given
        Person p = new Person("1", "망둥이", 10);

        //when
        repository.save(p);

        //then
    }

    @Test
    @DisplayName("회원번호가 1인 회원의 이름과 나이를 수정해야 한다")
    void updateTest(){
//        given
        String id ="1";

        String newName = "개구리";
        int newAge = 15;

//        when
        Person person = new Person(id, newName, newAge);
        repository.update(person);

    }

    @Test
    @DisplayName("회원번호가 1인 회원을 삭제하도록 한다")
    void deleteTest(){
//        given
        String id = "1";

//        when
        repository.delete(id);
    }

    @Test
    @DisplayName("랜덤 회원 아이디를 가진 회원을 10명 등록하도록 한다")
    void bulkInsertTest(){

        for (int i = 0; i < 10; i++) {
            Person p = new Person(""+Math.random(), "익명회원"+i, i+10);
            repository.save(p);
        }
    }

    @Test
    @DisplayName("전체 회원을 조회하면 회원 리스트의 수가 10개 이다")
    void findAllTest(){
        List<Person> people = repository.findAll();

        people.forEach(System.out::println);
    }

    @Test
    @DisplayName("특정 아이디 회원을 조회하면 하나이 회원만 조회한다")
    void findOneTest(){
        String id = "0.40189524720239633";

        Person person = repository.findOne(id);
        System.out.println("person = " + person);
    }

}