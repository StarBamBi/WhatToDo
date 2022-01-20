<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="data" data-iboard="${data.iboard}"></div>
<div>
    <div>조회수: ${data.hits} | 등록일시: ${data.rdt} | 글쓴이: ${data.iuser}</div>
    <div id="dTitle">제목: <c:out value="${data.title}" /></div>
    <div id="pnBtns">
        <a href="/board/detail?iboard=${requestScope.prevNext.previboard}" class="${requestScope.prevNext.previboard == 0 ? 'lastPBtn' : ''}"><button class="boardBtn">이전 글</button></a>
        <a href="/board/detail?iboard=${requestScope.prevNext.nextiboard}" class="${requestScope.prevNext.nextiboard == 0 ? 'lastNBtn' : ''}"><button class="boardBtn">다음 글</button></a>
    </div>
    <div id="dtCtnt"><c:out value="${data.ctnt}" /></div>

<%--    <c:if test="${sessionScope.loginUser.iuser == data.iuser}">--%>
        <div id="mdBtns">
            <button class="boardBtn" onclick="location.href=`/board/mod?iboard=${data.iboard}`">수정</button>
            <button class="boardBtn" id="delBtn">삭제</button>
        </div>
<%--    </c:if>--%>
    <div>
        <form id="cmtFrm">
            <input type="text" name="ctnt">
            <input class="boardBtn" type="button" id="cmt_submit" value="댓글 달기">
        </form>
    </div>
</div>
