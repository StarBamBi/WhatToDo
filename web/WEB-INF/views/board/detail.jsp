<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="dataWithIboard" data-iboard="${data.iboard}"
     data-iuser="${sessionScope.loginUser.iuser}"></div>

<div style="width : 50%">
    <div><p>조회수: ${data.hits} | 등록일시: ${data.rdt} | 글쓴이: ${data.iuser}</p></div>
    <div id="dTitle"><c:out value="${data.title}"/></div>
    <div id="pnBtns">
        <a href="/board/detail?iboard=${requestScope.prevNext.previboard}"
           class="${requestScope.prevNext.previboard == 0 ? 'lastPBtn' : ''}">
            <button class="boardBtn btn btn-outline-success">이전 글</button>
        </a>
        <a href="/board/list">
            <button class="boardBtn btn btn-outline-primary">글 목록</button>
        </a>
        <a href="/board/detail?iboard=${requestScope.prevNext.nextiboard}"
           class="${requestScope.prevNext.nextiboard == 0 ? 'lastNBtn' : ''}">
            <button class="boardBtn btn btn-outline-success">다음 글</button>
        </a>
    </div>
    <div id="dtCtnt">
        <pre>${requestScope.data.ctnt}</pre>
    </div>
    <c:if test="${sessionScope.loginUser != null}">
        <div id="likeBtnDv">
            <div id="likeCount"></div>
            <i id="likeBtn" class="far fa-heart"></i>
        </div>
    </c:if>


    <c:if test="${sessionScope.loginUser.iuser == data.iuser}">
        <div id="mdBtns">
            <button class="boardBtn btn btn-outline-warning" onclick="location.href=`/board/mod?iboard=${data.iboard}`">
                수정
            </button>
            <button class="boardBtn btn btn-outline-danger" id="delBtn">삭제</button>
        </div>
    </c:if>

    <div id="cmt_list"></div>
    <c:if test="${sessionScope.loginUser != null}">
        <div>
            <form id="cmtFrm">
                <input id="cmtTxt" type="text" name="ctnt">
                <input class="boardBtn btn btn-outline-success" type="button" id="cmt_submit" value="댓글 달기">
            </form>
        </div>
    </c:if>
</div>
