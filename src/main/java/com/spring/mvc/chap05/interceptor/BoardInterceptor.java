package com.spring.mvc.chap05.interceptor;

import com.spring.mvc.util.LoginUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.spring.mvc.util.LoginUtils.*;

/*
    - 인터셉터
        컨트롤러에 요청이 들어가기 전, 후에
        공통적으로 처리할 코드나 검사할 일들을 정의해 놓는 클래스
 */
@Slf4j
@Configuration
public class BoardInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 로그인을 안했으면 글쓰기, 글수정, 글삭제 요청을 튕겨낼 것.
        HttpSession session = request.getSession();

        if(!isLogin(session)){
            log.info("this is request ({}) is denied!!", request.getRequestURI());
            response.sendRedirect("/members/sign-in");

            return false;
        }

        return true;
    }
}
