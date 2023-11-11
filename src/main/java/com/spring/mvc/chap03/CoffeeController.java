package com.spring.mvc.chap03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/coffee")
public class CoffeeController {

    /*
        @request - 커피 주문서 양식 페이지 열기
        @request URL - /chap03/coffee-form.jsp
        @response - /chap03/coffee-form.jsp
     */
    @GetMapping("/order")
    public String order(){
        return "chap03/coffee-form";
    }

    /*
        @request - 커피 주문을 서버에서 처리하는 요청
        @url - /coffee/result : POST
        @response - /chap03/coffee-result.jsp
     */
    @PostMapping("/result")
    public String result(String menu, @RequestParam(defaultValue = "3000") int price, Model model){

//        클라이언트가 보낸 커피 메뉴명과 가격을 읽어야 함.
        System.out.println("menu = " + menu);
        System.out.println("price = " + price);

//        jsp에게 데이터를 전달
        model.addAttribute("m", menu);
        model.addAttribute("p", price);

        return "chap03/coffee-result";
    }
}
