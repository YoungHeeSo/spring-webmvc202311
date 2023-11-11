package com.spring.mvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/model")
public class ControllerV2 {

    /*
        /model/hobbies : GET
        -> hobbies.jsp 파일로 사용자 이름정보와 취미목록을 뿌려주고 싶다
        == 1. Model 객체 사용
        -> 자바가 갖고 있는 데이터를 jsp로 넘겨줄 때 사용하는 바구니같은 역할
     */
    @GetMapping("/hobbies")
    public String hobbies(Model model){
        System.out.println("취미 안녕");

        String name = "짹짹이";
        List<String> hobbyList = List.of("전깃줄에 앉아있기", "좁쌀주워먹기", "하하호호");

//        데이터 보내기
        model.addAttribute("userName", name);
        model.addAttribute("hobbies", hobbyList);

        return "chap02/hobbies";
    }

    /*
        == 2. ModelAndView 객체 사용
     */
    @GetMapping("/hobbies2")
    public ModelAndView hobbies2(){
        System.out.println("취미2 안녕");

//        jsp으로 보낼 데이터
        String name = "냥냥이";
        List<String> hList = List.of("사마귀랑 놀기", "낮잠자기");

//        jsp으로 보낼 데이터 ModelAndView에 담기
        ModelAndView mv = new ModelAndView();
        mv.addObject("userName", name);
        mv.addObject("hobbies", hList);

//        view의 데이터를 따로 담아줌
        mv.setViewName("chap02/hobbies");

        return mv;
    }
}
