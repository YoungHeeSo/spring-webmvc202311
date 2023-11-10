package com.spring.mvc.chap01;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Setter @Getter @ToString
public class OrderRequestDTO {

//    클라이언트가 보내는 파라미터의 이름을 필드로 똑같이 구성한다
//    setter와 기본 생성자가 반드시 있어야 한다
    private String orderNum; // 주문번호
    private String goodsName; // 상품이름
    private int amount; // 주문수량
    private int price; // 주문가격
}
