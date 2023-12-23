package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.spring.mvc.chap05.service.LoginResult.*;

@Service
@Slf4j
@RequiredArgsConstructor // final만 골라서 생서아 만들어줌
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    // 회원가입 처리 서비스
    public boolean join(SignUpRequestDTO dto){

        // 클라이언트가 보낸 회원가입 데이터를
        // 패스워드 인코딩하여 엔터티로 변환해서 전달
        return memberMapper.save(dto.toEntity(encoder));
    }

    // 로그인 검증 처리
    public LoginResult authenticate(LoginRequestDTO dto){

        Member foundMember = memberMapper.findMember(dto.getAccount());

        // 회원 가입을 안한 경우
        if(foundMember == null){
            log.info("{} - 회원가입이 필요합니다!!", dto.getAccount());
            return NO_ACC;
        }

        //비밀 번호 일치 검사
        String inputPassword = dto.getPassword(); // 사용자 입력 패스워드
        String realPassword =foundMember.getPassword(); // 실제 패스워드
        if(!encoder.matches(inputPassword, realPassword)){
            log.info("비밀번호가 일치하지 않습니다!!");
            return NO_PW;
        }

        log.info("{}님 로그인 성공!", foundMember.getAccount());
        return SUCCESS;
    }

    // 아이디, 이메일 중복 검사 서비스
    public boolean checkDuplicateValue(String type, String keyword){

        return memberMapper.isDuplicate(type, keyword);
    }

    /*
        동기 통신 @Controller
        비동기 통신 @RestController

        동기 + 비동기 @Controller
        => 비동기가 필요한 부분에 @ResponseBody
     */



}
