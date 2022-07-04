<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>회원정보 찾기</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value="/css/findResult.css"/>">
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="content">
<c:if test="${check eq 'no'}">
    <h3 class="title">아이디 찾기 결과</h3>
    <label>일치하는 정보가 없습니다.</label>
</c:if>
<c:if test="${check eq 'yes'}">
    <h3 class="title">아이디 찾기 결과</h3>
    <label>찾으신 아이디</label>
    <input type="text" value="${id}">
</c:if>
<c:if test="${FIND_ERR eq 'FIND_ERR'}">
    <h3 class="title">아이디 찾기 결과</h3>
    <label>이름이나 이메일이 중복되어 아이디를 찾기 어렵습니다.</label>
</c:if>

<c:if test="${check eq 'null'}">
    <h3 class="title">비밀번호 찾기 결과</h3>
    <label>일치하는 정보가 없습니다.</label>
</c:if>
<c:if test="${check eq 'nnull'}">
    <h3 class="title">비밀번호 찾기 결과</h3>
    <label>찾으신 비밀번호</label>
    <input type="text" value="${pwd}">
</c:if>

    <a href="<c:url value="/register/idfind"/>">아이디 찾기</a>
    <a href="<c:url value="/register/pwdfind"/>">비밀번호 찾기</a>
</div>
</body>
</html>
