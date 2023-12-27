package com.spring.mvc.chap05.controller;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import com.spring.mvc.chap05.service.LoginResult;
import com.spring.mvc.chap05.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/members")
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 가입 양식 요청
    @GetMapping("/sign-up")
    public String signUp(){

        log.info("/members/sign-up GET : forwarding to sign-up.jsp");

        return "members/sign-up";
    }

    // 아이디, 이메일 중복체크 비동기 요청 처리
    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<?> check(String type, String keyword){

        log.info("/members/check?type={}&keyword={} ASYNC GET", type, keyword);

        boolean flag = memberService.checkDuplicateValue(type, keyword);
        log.debug("중복 체크 결과 : {}", flag);

        return ResponseEntity.ok().body(flag);
    }

    // 회원 가입 처리
    @PostMapping("/sign-up")
    public String signUp(SignUpRequestDTO dto){

        log.info("/members/sign-up POST !");
        log.debug("parameter: {}", dto);

        boolean flag = memberService.join(dto);
        return flag ? "redirect:/board/list" : "redirect:members/sign-up";
    }

    // 로그인 양식 요청
    @GetMapping("/sign-in")
    public String signIn(HttpSession session){

        /*if(session.getAttribute("login")!=null){
            return "redirect:/";
        }*/

        log.info("/members/sign-in GET - forwarding to sign-in.jsp");

        return "/members/sign-in"; // forward, jsp 경로 적음
    }

    // 로그인 검증 요청
    @PostMapping("/sign-in")
    public String signIn(
            LoginRequestDTO dto,
            // Model 에 담긴 데이터를 redirect 으로 보내면 jsp 으로 가지 않는다
            // 왜냐면 리다이렉트는 요처이 2번 들어가서 첫번째 요청 시 보관한 데이터가 소실된다
            RedirectAttributes ra,
            HttpServletResponse response,
            HttpServletRequest request
    ){

        log.info("/members/sign-in POST !");
        log.debug("parameter : {}", dto);

        LoginResult result = memberService.authenticate(dto);
        log.debug("login result : {}", result);

        ra.addFlashAttribute("msg", result); // jsp 으로 데이터 를 보낼 때

        if(result == LoginResult.SUCCESS){ // 로그인 성공 시

            // 쿠키로 로그인 유지
            // makerLoginCookie(dto, response);

            // 세션으로 로그인 유지
            memberService.maintainLoginState(request.getSession(), dto.getAccount());

            return "redirect:/";
        }

        return "redirect:/members/sign-in"; // 로그인 실패 시 //redirect, URL 을 적음
    }

    private static void makerLoginCookie(LoginRequestDTO dto, HttpServletResponse response) {
        // 쿠키에 로그인 기록 저장
        Cookie cookie = new Cookie("login", dto.getAccount());// String 이외의 저장이 힘듦
        // 쿠키 정보 세팅
        cookie.setPath("/"); // 이 쿠키는 모든 경로 에서 들고 다녀야 함
        cookie.setMaxAge(60); // 쿠키의 수명, 60초 유지

        // 쿠키를 클라이언트에게 전송, 응답 객체가 필요함(Response 객체 필요)
        response.addCookie(cookie);
    }

    // 로그아웃 요청 처리
    @GetMapping("/sign-out")
    public String signOut(
//            HttpServletRequest request // 다른 정보들 도 필요할 때
            HttpSession session // 세션만 필요할 때
    ){
        // 세션얻기
        // HttpSession session = request.getSession();

        // 세션에서 로그인 정보 기록 삭제
        session.removeAttribute("login");

        // 세션을 초기화(RESET)
        session.invalidate();
        return "redirect:/";
    }


}

