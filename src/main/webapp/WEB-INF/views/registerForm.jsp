<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>회원가입</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value="/css/register.css/"/>">
</head>
<body>
<script>
    let msg = "${msg}";
    if (msg == "ADD_ERR") alert("아이디가 중복됩니다.")
</script>
<jsp:include page="header.jsp"/>
<%--@elvariable id="userDto" type=""--%>
<form:form modelAttribute="userDto">
    <h3 id="title">회원가입</h3>
    <div class="msg"><form:errors path="id"/></div>
    <label>아이디</label>
    <input  class="input-field" type="text" name="id" id="id" placeholder="4~12자리의 영대소문자와 숫자 조합">
    <button class="input-field" type="button" id="checkBtn">ID중복체크</button>

    <div class="msg"><form:errors path="pwd"/></div>
    <label>비밀번호</label>
    <input  class="input-field" type="password" name="pwd" placeholder="4~12자리의 영대소문자와 숫자 조합">

    <div class="msg"><form:errors path="checkPwd"/></div>
    <label>비밀번호확인</label>
    <input  class="input-field" type="password" name="checkPwd" placeholder="4~12자리의 영대소문자와 숫자 조합">

    <div class="msg"><form:errors path="name"/></div>
    <label>이름</label>
    <input  class="input-field" type="text" name="name" placeholder="홍길동">

    <div class="msg"><form:errors path="email"/></div>
    <label>이메일</label>
    <input  class="input-field" type="text" name="email" placeholder="example@email.com">

    <div class="msg"><form:errors path="birth"/></div>
    <label>생년월일</label>
    <input  class="input-field" type="text" name="birth" placeholder="2000-01-01">
    <button>회원가입</button>
</form:form>
<script>
    var rowCnt = 0;
    $(document).ready(function () {
        $("#checkBtn").click(function () {
            let id = $("#id").val();
            if(id.trim()==''){
                alert("아이디를 입력해주세요.")
                return
            }
            $.ajax({
                async: false,
                type: 'POST',
                url: '/dw4/register/idcheck',
                data: id,
                contentType: "application/json;charset=UTF-8",
                success: function (result) {
                    if (result == "T") {
                        alert("사용 가능한 아이디입니다.")
                        rowCnt = 1;
                    } else if (result == "F") {
                        alert("중복된 아이디 입니다.")
                        $("#id").focus();
                    }
                }, error: function (request, error) {
                    alert("code : " + request.status + "\n" + "message : " + request.responseText + "\n" + "error : " + error);
                }
            })
        })
    })
</script>
</body>
</html>
