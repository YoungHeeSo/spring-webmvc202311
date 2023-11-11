<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>jsp study</title>
</head>
<body>
    <h1> 주문 내역입니다. </h1>
    <ul>
        <li># 주문하신 메뉴: ${m}</li>
        <li># 지불하실 가격: ${p}원</li>
        <li></li>
    </ul>
    <a href="/coffee/order"> 재주문하기 </a>
</body>
</html>