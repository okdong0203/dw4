<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId == null || '' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId == null || '' ? '로그인' : '로그아웃'}"/>
<html>
<head>
    <title>Title</title>
    <style>
        * {
            box-sizing: border-box;
            margin : 0;
            padding : 0;
        }

        .topnav {
            background-color: #333;
            overflow: hidden;
            padding-left: 33%;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>
<div class="topnav">
    <a class="active" href="<c:url value="/"/>">Home</a>
    <a href="<c:url value="${loginOutLink}"/>">${loginOut}</a>
    <a href="<c:url value="/register/add"/>">회원가입</a>
    <a href="<c:url value="/board/list"/>">게시판</a>
    <a href="<c:url value="/register/modify"/>">회원정보수정</a>
    <a href="<c:url value="/register/remove"/>">회원 탈퇴</a>
</div>
</body>
</html>
