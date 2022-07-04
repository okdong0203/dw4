<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>게시판</title>
    <link rel="stylesheet" href="<c:url value="/css/index.css"/>">
</head>
<script>
    let msg = "${msg}"
    if (msg == "ADD_OK") alert("회원가입을 축하합니다.")
    if (msg == "MOD_OK") alert("비밀번호가 변경되었습니다.")
    if (msg == "DEL_OK") alert("성공적으로 탈퇴되었습니다.")
</script>
<jsp:include page="header.jsp"/>
<h2>자유 게시판</h2>

<div class="container">
    <img src="<c:url value="/img/board.jpg"/>" style="width: 100%;">
    <div class="content">
    <h1>FREE BOARD</h1>
    </div>
    </div>
</html>