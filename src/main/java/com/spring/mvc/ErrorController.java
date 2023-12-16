package com.spring.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
@Slf4j
public class ErrorController {

    @GetMapping("/404")
    public String error404(){
        log.warn("404 Error occurred!!");
        return "error/error404";
    }

    @GetMapping("/500")
    public String error500(){
        log.warn("500 Error occurred!!");
        return "error/error500";
    }
}
