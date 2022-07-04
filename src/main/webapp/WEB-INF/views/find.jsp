<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <title>회원정보 찾기</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value="/css/find.css"/>">
</head>
<body>
<jsp:include page="header.jsp"/>

<c:if test="${mode eq 'idFind'}">
<form action="<c:url value="/register/idfind"/>" method="post" name="frmId" onsubmit="return formCheck(frmId)">
    <h3 id="title">아이디 찾기</h3>
    <input type="text" id="name" name="name" placeholder="이름">
    <input type="text" id="email" name="email" placeholder="이메일">
    <button>아이디 찾기</button>
    <a href="<c:url value="/register/pwdfind"/>">비밀번호 찾기</a>
    </c:if>
    <c:if test="${mode ne 'idFind'}">

    <form action="<c:url value="/register/pwdfind"/>" method="post" onsubmit="return formCheck(this)">
        <h3 id="title">비밀번호 찾기</h3>
        <input type="text" id="id" name="id" placeholder="아이디">
        <input type="text" id="email" name="email" placeholder="이메일">
        <button>비밀번호 찾기</button>
        <a href="<c:url value="/register/idfind"/>">아이디 찾기</a>
        </c:if>

    </form>
    <script>
        function formCheck(frmId) {
            if (frmId.name.value.length == 0) {
                alert("이름을 입력해주세요.")
                return false;
            }
            if (frmId.email.value.length == 0) {
                alert("이메일을 입력해주세요.")
                return false;
            }
        }

        function formCheck(frm) {
            if (frm.name.value.length == 0) {
                alert("이름을 입력해주세요.")
                return false;
            }
            if (frm.email.value.length == 0) {
                alert("이메일을 입력해주세요.")
                return false;
            }
        }
    </script>
</body>
</html>
