package com.spring.mvc.chap04.service;

import com.spring.mvc.chap04.dto.ScoreRequestDTO;
import com.spring.mvc.chap04.dto.ScoreResponseDTO;
import com.spring.mvc.chap04.entity.Score;
import com.spring.mvc.chap04.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 컨트롤러와 레파지토리에 사이 위치하여
 * 중간 로직을 처리하는 역할
 *
 * 컨트롤러 -> 서비스 -> 레파지토리
 */
@RequiredArgsConstructor
@Service
public class ScoreService {

    private final ScoreRepository repository;
//    목록 조회 중간 처리
    /**
     * 컨트롤러는 데이터베이스에서는 성적 정보 리스트를
     * 조회해 오기를 원하고 있다
     * 그런데 데이터베이스는 민감한 정보까지 모두 조회한다
     *
     * 그리고 컬럼명도 그대로 노출하기 때문에
     * 이 모든 것을 숨기는 처리를 하고 싶다
     */
    public List<ScoreResponseDTO> getList(String sort){
        return repository.findAll(sort)
                .stream()
                .map(ScoreResponseDTO::new)
                .collect(Collectors.toList());
    }

//    성적 입력 중간 처리
    public boolean insertScore(ScoreRequestDTO dto){
        return repository.save(new Score(dto));
    }

//    성적 삭제 중간 처리
    public boolean deleteScore(int stuNum){

        return  repository.delete(stuNum);
    }

//    성적 개별 상세 조회 중간 처리
    public Score retrieve(int stuNum){
//        데이터 가공이 필요할 시 사용 ex) 글제목 줄임 처리, 조회 수 상승, 등등...
        return  repository.findOne(stuNum);
    }

//    성저거 수정 완료 중간 처리
    public void updateScore(int stuNum, ScoreRequestDTO dto){
        /**
         * 수정의 흐름
         * 클라이언트가 수정할 데이터를 보냄
         * -> 서버에 저장되어 있는 기존 데이터를 조회해서 수정
         */
        Score score = repository.findOne(stuNum);
        score.changeScore(dto);

    }
}
