package com.spring.mvc.config;

// 미리 만들어놓은 에러페이지로 안내하는 설정

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class CustomErrorPageConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {

        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");// 404 오류 일때는
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");// 500 오류 일때는

        registry.addErrorPages(errorPage404, errorPage500);
    }

}
