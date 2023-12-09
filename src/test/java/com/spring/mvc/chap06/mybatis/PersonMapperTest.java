package com.spring.mvc.chap06.mybatis;

import com.spring.mvc.chap06.entity.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonMapperTest {

    @Autowired
    PersonMapper mapper;

    @Test
    @DisplayName("mybatis으로 DB에 사람데이터 생성한다")
    void saveTest() {
        //given
        Person p = new Person("333", "김바마", 30);
        //when
        mapper.save(p);
        //then
    }

    @Test
    @DisplayName("333번 회원을 수정한다")
    void updateTest() {
        //given
        Person p = new Person("333", "이마바수", 23);
        //when
        mapper.update(p);
        //then
    }

    @Test
    @DisplayName("333번 회원을 삭제한다")
    void deleteTest() {
        //given
        String id = "333";
        //when
        mapper.delete(id);
        //then
    }

    @Test
    @DisplayName("전체 회원을 조회한다")
    void findAllTest() {
        //given

        //when
        List<Person> people = mapper.findAll();
        //then
        people.forEach(System.out::println);
    }

    @Test
    @DisplayName("30번 회원을 조회한다")
    void findOneTest() {
        //given
        String id = "30";
        //when
        Person p = mapper.findOne(id);
        //then
        System.out.println(p);
    }

}