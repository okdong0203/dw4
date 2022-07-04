<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<html>
<head>
    <title>회원정보 ${mode == 'mod' ? "변경" : "삭제"}</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value="/css/remo.css/"/>">
</head>
<body>
<script>
    let msg = "${msg}"
    if(msg == "DEL_ERR") alert("패스워드가 일치하지 않습니다.")
</script>

<jsp:include page="header.jsp"/>

<c:if test="${mode eq 'mod'}">
    <form id="form" action="<c:url value="/register/modify"/>" method="post"/>
    <h3 id="title">회원정보 변경</h3>
</c:if>
<c:if test="${mode ne 'mod'}">
    <form id="form" action="<c:url value="/register/remove"/>" method="post"/>
    <h3 id="title">회원정보 삭제</h3>
</c:if>
<label>아이디</label>
<input type="text" name="id" id="id" value="${id}" readonly>

<c:if test="${mode eq 'mod'}">
    <label>비밀번호</label>
    <input type="password" name="pwd" id="pwd" placeholder="4~12자리의 영대소문자와 숫자 조합">
    <button type="button" id="modBtn">회원수정</button>
</c:if>
<c:if test="${mode ne 'mod'}">
    <label>비밀번호</label>
    <input type="password" name="pwd" id="pwd" placeholder="비밀번호 입력">
    <button type="button" id="removeBtn">회원탈퇴</button>
</c:if>
</form>
<script>
    $(document).ready(function(){
        let modFormCheck = function (){
            let form = document.getElementById("form")
            let regPwd = /^[a-z|A-Z|0-9]{4,12}$/
            if(form.pwd.value==""){
                alert("비밀번호를 입력해주세요.")
                form.pwd.focus();
                return false;
            }if(!regPwd.test(document.getElementById("pwd").value)){
                alert("비밀번호 양식에 맞춰주세요")
                form.pwd.focus();
                return false;
            }
            return true;
        }
        let removeFormCheck = function (){
            let form = document.getElementById("form")
            if(form.pwd.value=="") {
                alert("비밀번호를 입력해주세요.")
                form.pwd.focus();
                return false;
            }
            return true;
        }
        $("#modBtn").on("click", function (){
            let form = $("#form")
            if(confirm("비밀번호를 변경하시겠습니까?")){
                if(modFormCheck())
                    form.submit();
            }
        })
        $("#removeBtn").on("click", function (){
            let form = $("#form")
            if(confirm("회원 탈퇴를 진행할까요?")){
                if(removeFormCheck())
                    form.submit();
            }
        })
    })
</script>
</body>
</html>
