package com.spring.mvc.chap05.service;

import com.spring.mvc.chap05.dto.request.AutoLoginDTO;
import com.spring.mvc.chap05.dto.request.LoginRequestDTO;
import com.spring.mvc.chap05.dto.request.SignUpRequestDTO;
import com.spring.mvc.chap05.dto.response.LoginUserResponseDTO;
import com.spring.mvc.chap05.entity.Member;
import com.spring.mvc.chap05.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;

import static com.spring.mvc.chap05.service.LoginResult.*;
import static com.spring.mvc.util.LoginUtils.*;

@Service
@Slf4j
@RequiredArgsConstructor // final만 골라서 생서아 만들어줌
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    // 회원가입 처리 서비스
    public boolean join(SignUpRequestDTO dto, String savePath){

        savePath = "/local" + savePath;

        // 클라이언트가 보낸 회원가입 데이터를
        // 패스워드 인코딩하여 엔터티로 변환해서 전달
        return memberMapper.save(dto.toEntity(encoder, savePath));
    }

    // 로그인 검증 처리
    public LoginResult authenticate(
            LoginRequestDTO dto,
            HttpSession session,
            HttpServletResponse response
    ){

        Member foundMember = getMember(dto.getAccount());

        // 회원 가입을 안한 경우
        if(foundMember == null){
            log.info("{} - 회원가입이 필요합니다!!", dto.getAccount());
            return NO_ACC;
        }

        //비밀 번호 일치 검사
        String inputPassword = dto.getPassword(); // 사용자 입력 패스워드, 암호화가 안된
        String realPassword =foundMember.getPassword(); // 실제 패스워드, DB에 저장
        if(!encoder.matches(inputPassword, realPassword)){
            log.info("비밀번호가 일치하지 않습니다!!");
            return NO_PW;
        }

        // 자동 로그인 처리
        if(dto.isAutoLogin()){
            // 자동 로그인 쿠키를 생성 - 쿠키 안에 절대 중복되지 않는 값(브라우저의 세션 아이디)을 저장
            Cookie autoLoginCookie = new Cookie(AUTO_LOGIN_COOKIE, session.getId());

            // 쿠키 설정 - 사용경로, 수명, ...
            int limitTime = 60 * 60 * 24 * 90; // 자동 로그인 유지시간
            autoLoginCookie.setPath("/"); // 전체 경로
            autoLoginCookie.setMaxAge(limitTime);

            // 쿠키를 클라이언트에 전송
            response.addCookie(autoLoginCookie);

            // DB에도 쿠키에 관련된 값들(랜덤 세션키, 만료 시간)을 갱신
            memberMapper.saveAutoLogin(
                    AutoLoginDTO.builder()
                            .sessionId(session.getId())
                            .limitTime(LocalDateTime.now().plusDays(90))
                            .account(dto.getAccount())
                            .build()
            );

        }

        log.info("{}님 로그인 성공!", foundMember.getAccount());
        return SUCCESS;
    }

    private Member getMember(String account) {
        return memberMapper.findMember(account);
    }

    // 아이디, 이메일 중복 검사 서비스
    public boolean checkDuplicateValue(String type, String keyword){

        return memberMapper.isDuplicate(type, keyword);
    }

    // 세션을 사용해서 일반 로그인 유지하기
    public void maintainLoginState(HttpSession session, String account){

        // 세션은 서버에서만 유일하게 보관되는 데이터로서,
        // 로그인 유지 등 상태 유지가 필요할 때 사용되는 개념
        // 세션은 쿠키와 달리 모든 데이터를 저장할 수 있음, 즉 객체도 저장 가능(쿠키는 String만 가능)
        // 세션의 수명은 설정한 수명시간에 영향을 받고 브라우저의 수명과 함께 한다
        // 브라우저가 닫히면 session도 닫힌다는 말씀

        // 현재 로그인한 사람의 모든 정보 조회
        Member member = getMember(account);

        // DB 데이터를 보여줄 것만 정제
        LoginUserResponseDTO dto = LoginUserResponseDTO.builder()
                .account(member.getAccount())
                .email(member.getEmail())
                .nickName(member.getName())
                .auth(member.getAuth().name())
                .profile(member.getProfileImage())
                .loginMethod(member.getLoginMethod().toString())
                .build();

        log.debug("login: {}", dto);

        // member 객체는 DB 그 자체이므로 민감한 정보들이 건들 수 있으므로
        // 원하는 정보만 골라서 볼 수 있도록 한다

        // 세션에 로그인 한 회원의 정보 저장
        session.setAttribute(LOGIN_KEY, dto);
        // 세션 수명을 설정 해야 함.
        session.setMaxInactiveInterval(60*60);

    }

    public void autoLoginClear(HttpServletRequest request, HttpServletResponse response) {

        // 1. 자동로그인 쿠키를 가져온다
        Cookie c = WebUtils.getCookie(request, AUTO_LOGIN_COOKIE);

        // 2. 쿠키를 삭제한다
        // -> 쿠키의 수명을 0초로 설정하여 다시 클라이언트에 전송
        if (c != null) {
            c.setMaxAge(0);
            c.setPath("/");
            response.addCookie(c);

            // 3. 데이터베이스에서도 세션아이디와 만료시간을 제거한다.
            memberMapper.saveAutoLogin(
                    AutoLoginDTO.builder()
                            .sessionId("none")
                            .limitTime(LocalDateTime.now())
                            .account(getCurrentLoginMemberAccount(request.getSession()))
                            .build()
            );


    /*
        동기 통신 @Controller
        비동기 통신 @RestController

        동기 + 비동기 @Controller
        => 비동기가 필요한 부분에 @ResponseBody
     */

        }
    }

}
