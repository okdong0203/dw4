<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>잘못된 요청</title>
</head>
<body>
<c:if test="${FIND_ERR eq 'FIND_ERR'}">
    <h2>찾기결과 이름이나 이메일이 중복되어 아이디를 찾을수 없습니다.</h2>
    <a href="<c:url value="/register/idfind"/>">아이디 찾기</a>
    <a href="<c:url value="/register/pwdfind"/>">비밀번호 찾기</a>
    <a href="<c:url value="/login/login"/>">로그인</a>
</c:if>
</body>
</html>
