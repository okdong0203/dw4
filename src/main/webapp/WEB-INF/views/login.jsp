<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page session="false" %>
<html>
<head>
    <title>로그인</title>
    <link rel="stylesheet" href="<c:url value="/css/login.css"/>">
</head>
<body>
<jsp:include page="header.jsp"/>
<form action="<c:url value="/login/login"/>" method="post" onsubmit="return formCheck(this)">
    <h3 id="title">로그인</h3>
    <div id="msg">
        <c:if test="${not empty param.msg}">
            <i>${URLDecoder.decode(param.msg)}</i>
        </c:if>
    </div>
    <input name="id" type="text" placeholder="ID">
    <input name="pwd" type="password" placeholder="PWD">
    <input type="hidden" name="toURL" value="${param.toURL}">
    <button>로그인</button>
    <a href="<c:url value="/register/idfind"/>">아이디 찾기</a>
    <a href="<c:url value="/register/pwdfind"/>">비밀번호 찾기</a>
</form>
<script>
    function formCheck(frm){
        if(frm.id.value.length==0){
            alert("id를 입력해주세요.");
            frm.id.focus();
            return false;
        }
        if(frm.pwd.value.length==0){
            alert("비밀번호를 입력해주세요.");
            frm.pwd.focus();
            return false;
        }
    }
</script>
</body>
</html>