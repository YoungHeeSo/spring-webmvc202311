package com.spring.mvc.chap06.mybatis;

import com.spring.mvc.chap06.entity.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// mybatis의 SQL 실행을 위한 인터페이스

@Mapper
public interface PersonMapper {

//    CRUD 를 명세
    void save(Person p); // INSERT

    boolean update(Person p); // UPDATE

    boolean delete(String id); // DELETE

    List<Person> findAll(); // SELECT

    Person findOne(String id); // SELECT
}
