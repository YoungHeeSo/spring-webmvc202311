package com.spring.mvc.chap06.jdbc;

import com.spring.mvc.chap06.entity.Person;
import org.mariadb.jdbc.Driver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcRepository {

//    db 연결 설정 정보
    private  String url = "jdbc:mariadb://localhost:3306/spring"; // 데이터베이스 URL
    private String username = "root";
    private String password = "mariadb";

//    JDBC 드라이버 로딩
    public JdbcRepository(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//      데이터베이스 커넥션 얻기
    public Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url, username, password);
    }

//    INSERT 기능
    public void save(Person person){
        Connection con = null;

//        1. DB 연결 하고 연결 정보 얻어와
        try {
            con = DriverManager.getConnection(url, username, password); // 연결정보

//            2. 트랜잭션 시작
            con.setAutoCommit(false); // 자동 커밋 비활성화

//            3. SQL 생성
            String sql = "insert into person " +
                    "(id, person_name, person_age ) " +
                    "values(?, ?, ?)";

//            4. SQL을 실행시캬주는 객체 얻어와
            PreparedStatement psm = con.prepareStatement(sql);

//            5. ? 파라미터 세팅
            psm.setString(1, person.getId());
            psm.setString(2, person.getPersonName());
            psm.setInt(3, person.getPersonAge());

//            6. SQL 실행 명령
//            executeUpdate - 갱신, executeQuery - 조회
            int result = psm.executeUpdate();// 성공한 쿼리의 개수 값을 반환해

//            7. 트랜잭션 처리
            if(result==1) con.commit();
            else con.rollback();

        } catch (SQLException e) {
            try {
                if(con!=null) con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }finally {
            try {
                if(con!=null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    //    UPDATE 기능
    public void update(Person person){
        Connection con = null;

//        1. DB 연결 하고 연결 정보 얻어와
        try {
            con = DriverManager.getConnection(url, username, password); // 연결정보

//            2. 트랜잭션 시작
            con.setAutoCommit(false); // 자동 커밋 비활성화

//            3. SQL 생성
            String sql = "update person " +
                    "set person_name = ?, person_age = ? "+
                    "where id = ?";

//            4. SQL을 실행시캬주는 객체 얻어와
            PreparedStatement psm = con.prepareStatement(sql);

//            5. ? 파라미터 세팅
            psm.setString(1, person.getPersonName());
            psm.setInt(2, person.getPersonAge());
            psm.setString(3, person.getId());

//            6. SQL 실행 명령
//            executeUpdate - 갱신, executeQuery - 조회
            int result = psm.executeUpdate();// 성공한 쿼리의 개수 값을 반환해

//            7. 트랜잭션 처리
            if(result==1) con.commit();
            else con.rollback();

        } catch (SQLException e) {
            try {
                if(con!=null) con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }finally {
            try {
                if(con!=null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    //    DELETE 기능
    public void delete(String id){
        Connection con = null;

//        1. DB 연결 하고 연결 정보 얻어와
        try {
            con = DriverManager.getConnection(url, username, password); // 연결정보

//            2. 트랜잭션 시작
            con.setAutoCommit(false); // 자동 커밋 비활성화

//            3. SQL 생성
            String sql = "delete from person " +
                    "where id = ?";

//            4. SQL을 실행시캬주는 객체 얻어와
            PreparedStatement psm = con.prepareStatement(sql);

//            5. ? 파라미터 세팅
            psm.setString(1, id);

//            6. SQL 실행 명령
//            executeUpdate - 갱신, executeQuery - 조회
            int result = psm.executeUpdate();// 성공한 쿼리의 개수 값을 반환해

//            7. 트랜잭션 처리
            if(result==1) con.commit();
            else con.rollback();

        } catch (SQLException e) {
            try {
                if(con!=null) con.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }finally {
            try {
                if(con!=null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    // 전체 회원 조회
    public List<Person> findAll(){

        List<Person> people = new ArrayList<>();

        try{

//            1) 데이터베이스에 연결해서 연결 정보 획든
            Connection con = DriverManager.getConnection(url, username, password);

//            2) SQL 생성
            String sql = "select * from person";

//            3) SQL 실행을 위한 객체 획득
            PreparedStatement psm = con.prepareStatement(sql);

//            4) ? 파라미터 채우기, selecr
//            5) SQL 실행 명령
            ResultSet rs = psm.executeQuery();

//            6) 결과 집합 조작하기
            while (rs.next()){ // 커서를 한 행씩 이동시키는 기능

//                 위치한 데이터 레코드
                String id = rs.getString("id");
                String personName = rs.getString("person_name");
                int personAge = rs.getInt("person_age");

                System.out.println("id = " + id);
                System.out.println("personName = " + personName);
                System.out.println("personAge = " + personAge);

                Person p = new Person(id, personName, personAge);

                people.add(p);

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return people;
    }

//    단일 조회(아이디로 호출)
        public Person findOne(String id){

            try{

//            1) 데이터베이스에 연결해서 연결 정보 획든
                Connection con = DriverManager.getConnection(url, username, password);

//            2) SQL 생성
                String sql = "select * from person where id = ?";

//            3) SQL 실행을 위한 객체 획득
                PreparedStatement psm = con.prepareStatement(sql);

//            4) ? 파라미터 채우기
                psm.setString(1, id);

//            5) SQL 실행 명령
                ResultSet rs = psm.executeQuery();

//            6) 결과 집합 조작하기
                while (rs.next()){ // 커서를 한 행씩 이동시키는 기능

//                 위치한 데이터 레코드
                    String pid = rs.getString("id");
                    String personName = rs.getString("person_name");
                    int personAge = rs.getInt("person_age");

                    System.out.println("pid = " + pid);
                    System.out.println("personName = " + personName);
                    System.out.println("personAge = " + personAge);

                    return new Person(id, personName, personAge);

                }

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }


}
