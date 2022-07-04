<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>게시판</title>
    <link rel="stylesheet" href="<c:url value="/css/boardList.css/"/>">
    <script>
        let msg = "${msg}"
        if (msg == "DEL_OK") alert("게시글이 삭제되었습니다.")
        if (msg == "WRT_OK") alert("게시글이 등록되었습니다.")
        if (msg == "MOD_OK") alert("게시글이 수정되었습니다.")
    </script>
</head>
<body>
<jsp:include page="header.jsp"/>
<div style="text-align:center">
    <div class="board-container">
        <div class="search-container">
            <form action="<c:url value="/board/list"/>" class="search-form" method="get">
                <select class="search-option" name="option">
                    <option value="A" ${ph.sc.option == 'A' || ph.sc.option == '' ? "selected" : ""}>제목+내용</option>
                    <option value="T" ${ph.sc.option == 'T' || ph.sc.option == '' ? "selected" : ""}>제목만</option>
                    <option value="W" ${ph.sc.option == 'W' || ph.sc.option == '' ? "selected" : ""}>작성자</option>
                </select>

                <input type="text" name="keyword" class="search-input" type="text" value="${ph.sc.keyword}"
                       placeholder="검색어를 입력해주세요">
                <input type="submit" class="search-button" value="검색">
            </form>
            <button id="writeBtn" class="btn-write" onclick="location.href='<c:url value="/board/write"/>'">글쓰기</button>
        </div>

        <table>
            <tr>
                <th class="no">번호</th>
                <th class="title">제목</th>
                <th class="writer">이름</th>
                <th class="regdate">등록일</th>
                <th class="viewcnt">조회수</th>
            </tr>
            <c:forEach var="boardDto" items="${list}">
                <tr>
                    <td class="no"><c:out value="${boardDto.bno}"/></td>
                    <td class="title"><a
                            href="<c:url value="/board/read/${ph.sc.queryString}&bno=${boardDto.bno}"/>"><c:out
                            value="${boardDto.title}"/></a></td>
                    <td class="writer"><c:out value="${boardDto.writer}"/></td>
                    <c:choose>
                        <c:when test="${boardDto.reg_date.time >= today}">
                            <td class="regdate"><fmt:formatDate value="${boardDto.reg_date}" pattern="HH:mm" type="time"/></td>
                        </c:when>
                        <c:otherwise>
                            <td class="regdate"><fmt:formatDate value="${boardDto.reg_date}" pattern="yyyy-MM-dd"
                                                                type="date"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td class="viewcnt">${boardDto.view_cnt}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <div class="paging-container">
            <div class="paging">
                <c:if test="${totalCnt==null || totalCnt==0}">
                    <div> 게시물이 없습니다.</div>
                </c:if>
                <c:if test="${totalCnt!=null && totalCnt!=0}">
                    <c:if test="${ph.showPrev}">
                        <a class="page"
                           href="<c:url value="/board/list${ph.sc.getQueryString(ph.beginPage-1)}"/>">&lt;</a>
                    </c:if>
                    <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
                        <a class="page ${i == ph.sc.page ? "paging-active" : ""}" href="<c:url value="/board/list${ph.sc.getQueryString(i)}"/>">${i}</a>
                    </c:forEach>
                    <c:if test="${ph.showNext}">
                        <a class="page"
                           href="<c:url value="/board/list${ph.sc.getQueryString(ph.endPage+1)}"/>">&gt;</a>
                    </c:if>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>
