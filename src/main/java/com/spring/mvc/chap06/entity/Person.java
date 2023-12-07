package com.spring.mvc.chap06.entity;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String id;
    private String personName;
    private int personAge;

    public Person(ResultSet rs) throws SQLException {
        this.id = rs.getString("id");
        this.personName = rs.getString("person_name");
        this.personAge =  rs.getInt("person_age");
    }
}
