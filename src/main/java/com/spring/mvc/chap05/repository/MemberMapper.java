package com.spring.mvc.chap05.repository;

import com.spring.mvc.chap05.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    // 회원 가입
    boolean save(Member member);

    // 회원 정보 단일 조회
    Member findMember(String account);

    /**
     * 중복 체크 (account, email) 기능
     * @param type - 중복을 검사할 내용(account, email)
     * @param keyword - 중복 검사 입력값(ex: abc@gmail.com...)
     * @return 중복 이면 true, 중복이 아니면 false
     */
    boolean isDuplicate(String type, String keyword);
}
