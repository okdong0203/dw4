<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link rel="stylesheet" href="<c:url value="/css/board.css/"/>">
    <title>게시판</title>
    <script>
        let msg = "${msg}"
        if (msg == "DEL_ERR") alert("게시글 삭제 실패했습니다.")
        if (msg == "WRT_ERR") alert("게시글 등록에 실패했습니다.")
        if (msg == "MOD_ERR") alert("게시글 수정에 실패했습니다.")
    </script>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="container">
    <h2 id="head" class="writing-header">게시물 ${mode == "write" ? "쓰기" : "읽기"}</h2>
    <form id="form" class="frm" action="" method="">
        <input type="hidden" name="bno" value="${boardDto.bno}">
        <input type="text" name="title" value="${boardDto.title}" ${mode == "write" ? "" : 'readonly="readonly"'} placeholder="제목">

            <textarea name="content" cols="30"rows="20" placeholder="내용을 입력해 주세요." ${mode=="write"?"":"readonly='readonly'"}><c:out value="${boardDto.content}"/></textarea>

        <c:if test="${mode eq 'write'}">
            <button type="button" id="writeBtn" class="btn">등록</button>
        </c:if>

        <c:if test="${mode ne 'write'}">
            <button type="button" id="writeNew" class="btn" onclick="location.href='<c:url value="/board/write"/>?page=${page}&pageSize=${pageSize}'">
                글쓰기
            </button>
        </c:if>

        <button type="button" id="listBtn" class="btn">목록</button>

        <c:if test="${boardDto.writer eq sessionScope.id}">
            <button type="button" id="modifyBtn" class="btn">수정</button>
            <button type="button" id="removeBtn" class="btn">삭제</button>
        </c:if>
    </form>
</div>
</body>
<script>
    $(document).ready(function () {
        $('#listBtn').on("click", function () {
            location.href = "<c:url value='/board/list${searchCondition.getQueryString}'/>";
        })
        $('#removeBtn').on("click", function () {
            let form = $('#form')
            if (confirm("게시글을 삭제하시겠습니까 ?")) {
                form.attr("action", "<c:url value='/board/remove${searchCondition.getQueryString}'/>");
                form.attr("method", "post");
                form.submit();
            }
        })
        $('#writeBtn').on("click", function () {
            let form = $('#form')
            let title = $("input[name=title]").val()
            let content = $("textarea[name=content]").val()
            if(title.trim()==''){
                alert("제목을 입력해주세요.")
                $("input[name=title]").focus()
                return;
            }if(content.trim()==''){
                alert("내용을 입력해주세요.")
                $("textarea[name=content]").focus()
                return;
            }
            if (confirm("게시글을 등록하시겠습니까?")) {
                    form.attr("action", "<c:url value="/board/write"/>")
                    form.attr("method", "post")
                    form.submit();
            }
        })
        $('#modifyBtn').on("click", function () {
            let form = $('#form')
            let readonly = $("input[name=title]").attr('readonly')
            if (readonly == 'readonly') {
                $("input[name=title]").attr('readonly', false);
                $("textarea").attr('readonly', false);
                $("#modifyBtn").html("등록");
                $("#head").html("게시글 수정");
                return;
            }
            form.attr("action", "<c:url value="/board/modify${searchCondition.getQueryString}"/>")
            form.attr("method", "post")
            form.submit();
        })
    })
</script>
</body>
</html>