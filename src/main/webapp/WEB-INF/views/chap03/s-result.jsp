<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>jsp study</title>
</head>
<body>
<h1>
    <c:if test="${result == 'success'}">로그인 성공</c:if>
    <c:if test="${result == 'f-id'}">아이디가 존재하지 않습니다</c:if>
    <c:if test="${result == 'f-pw'}">비밀번호가 존재하지 않습니다</c:if>
</h1>

    <a href="/hw/s-login-form" >다시 로그인하기</a>
</body>
</html>