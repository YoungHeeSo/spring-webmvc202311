package com.spring.mvc.chap06.spring_jdbc;

import com.spring.mvc.chap06.entity.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// 테스트 프레임 워크 : junit5 - 모든걸 default 제한자로 둘 것, null 값으로
@SpringBootTest // 스프링이 관리하는 빈을 주입받기 위한 아노테이션, 이거 안받으면 오토 못 받아옴
class SpringJdbcRepositoryTest {

    @Autowired
    SpringJdbcRepository repository;

    @Test
    @DisplayName("사람 정보를 데이터베이스에 저장한다")
    void saveTest() {
        //given
        Person p = new Person("99", "말똥이", 30);
        //when
        repository.save(p);
        //then
    }

    @Test
    @DisplayName("99번 회원의 이름과 나이를 수정한다")
    void modifyTest() {
        //given
        String id = "99";
        String newName = "수정수정이";
        int newAge = 50;

        Person p = new Person(id, newName, newAge);

        //when
        repository.modify(p);
        //then
    }

    @Test
    @DisplayName("99번 회원을 삭제한다")
    void removeTest() {
        //given
        String id = "99";
        //when
        repository.remove(id);
        //then
    }

    @Test
    @DisplayName("전체 조회를 한다")
    void findAllTest() {
        //given

        //when
        List<Person> people = repository.findAll();
        //then
        people.forEach(System.out::println);
    }

    @Test
    @DisplayName("30번 회원을 등록한 후 조회한다")
    void findOneTest() {
        //given
        String id = "30";
        Person p = new Person(id, "두껍이", 23);
        //when
        repository.save(p);
        //then
        assertEquals("두껍이", p.getPersonName());
        System.out.println("p = " + p);
    }
}