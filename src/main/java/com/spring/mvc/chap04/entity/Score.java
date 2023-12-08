package com.spring.mvc.chap04.entity;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.spring.mvc.chap04.entity.Grade.*;

/**
 * 엔터티 클래스
 * - 데이터베이스에 저장할 데이터를 자바 클래스에 매칭
 */

@Setter @Getter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    /*
    # 성적 테이블 생성
    create table tbl_score(
    stu_num INT(10) primary key auto_increment,
    stu_name varchar(255) not null ,
    kor INT(3) not null ,
    eng INT(3) not null ,
    math INT(3) not null ,
    total INT(3) not null ,
    average float(5, 2) not null ,
    #100.00 5자리, 소수점 2번째 자리까지
    grade char(1)
);
     */

    private String name; // 학생 이름
    private int kor, eng, math; // 국영수 점수

    private int stuNum; // 학번
    private int total; // 총점
    private double average; // 평균
    private Grade grade; // 학점


    public Score(ScoreRequestDTO score) {
        convertInputData(score);

        calculateToTotalAndAverage();

        makeGrade();

    }

    public Score(ResultSet rs) throws SQLException {
        this.stuNum = rs.getInt("stu_num");
        this.name = rs.getString("stu_name");
        this.kor = rs.getInt("kor");
        this.eng = rs.getInt("eng");
        this.math = rs.getInt("math");
        this.total = rs.getInt("total");
        this.average = rs.getDouble("average");
        this.grade = Grade.valueOf(rs.getString("grade"));
    }

    private void makeGrade() {
        if(average>=90) this.grade = A;
        else if(average>=80) this.grade = B;
        else if(average>=70) this.grade = C;
        else if(average>=60) this.grade = D;
        else this.grade = F;
    }

    private void calculateToTotalAndAverage() {
        this.total = kor + eng + math;
        this.average =  total/3.0;
    }

    private void convertInputData(ScoreRequestDTO score) {
        this.name = score.getName();
        this.kor = score.getKor();
        this.eng = score.getEng();
        this.math = score.getMath();
    }


    public void changeScore(ScoreRequestDTO dto) {
        this.kor =  dto.getKor();
        this.eng = dto.getEng();
        this.math = dto.getMath();

        calculateToTotalAndAverage();;
        makeGrade();
    }
}
