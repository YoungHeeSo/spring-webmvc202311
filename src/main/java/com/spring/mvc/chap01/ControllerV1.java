package com.spring.mvc.chap01;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

// 컨트롤러 : 클라이언트의 요청을 받아서 처리 후 응답을 보내주는 역할(Servlet)
@Controller // 빈 등록 : 이 클래스의 객체 생성 관리는 스프링이 처리한다
public class ControllerV1 {

//    세부 요청 처리는 메서드를 통해 등록
    @RequestMapping("/")
    public String home(){
        System.out.println("Welcome to my webpage!!");

//        return 문에는 어떤 jsp로 포워딩할지 경로를 적습니다
        return "index";
    }

//    /food 요청이 오면 food.jsp 파일 열기
    @RequestMapping("/food")
    public String food(){

        return "chap01/food";
    }

//    요청 파라미터 읽기
//    1. HttpServletRequset 객체 이용하기
//    ex ) /person?name=kim&age=30
    @RequestMapping("/person")
    public String person(HttpServletRequest request){
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));

        System.out.println(name);
        System.out.println(age);
        return "";
    }

//    2. @RequestRaram 사용하기
//    ex ) major?stu=park&major=business&grade=3
    @RequestMapping("/major")
    public String major(String stu,
                        @RequestParam("major") String mj,
                        @RequestParam(defaultValue = "1") Integer grade){
        System.out.println("stu = " + stu);
        System.out.println("major = " + mj);
        System.out.println("grade = " + grade);
        return "";
    }
}
