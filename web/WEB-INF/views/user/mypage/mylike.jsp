<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div>
    <h3><i class="fa-solid fa-heart"></i>${sessionScope.loginUser.nm}님의 좋아요</h3>
    <select class="form-select" aria-label="Default select example" id="select_form" name="select_form"
            onchange="changeVal(this.value)">
        <option value="1" ${param.category == '1' ? 'selected="selected"' : ''}>전체</option>
        <option value="2" ${param.category == '2' ? 'selected="selected"' : ''}>웹툰</option>
        <option value="3" ${param.category == '3' ? 'selected="selected"' : ''}>게임</option>
        <option value="4" ${param.category == '4' ? 'selected="selected"' : ''}>게시글</option>
    </select>
    <div class="myLike">
        <table class="table table-hover">
            <thead>
            <tr style="background-color : lightgray">
                <th>게임 & 웹툰 & 게시판 제목</th>
                <th>누른 일자</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${pageList}">
                <tr class="likeAllList" data-iboard="${item.iboard}">
                    <td>${item.nm}</td>
                    <td>${item.rdt}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <ul class="pagination">
                <c:set var="categoryParam" value="${param.category}"/>
                <c:choose>
                <c:when test="${categoryParam > 0}">
                <c:if test="${pageMaker.prev}">
                    <li><a href="/user/mypage/mylike?category=${categoryParam}&page=${pageMaker.startPage - 1}">이전</a></li>
                </c:if>

                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                    <li id="pageIdx" <c:out value="${pageMaker.dto.page == idx ? 'class=active' : ''}"/> style="padding: 0 3px;">
                        <a href="/user/mypage/mylike?category=${categoryParam}&page=${idx}">${idx}</a>
                    </li>
                </c:forEach>
                <c:if test="${pageMaker.next && pageMaker.endPage >0}">
                    <li>
                        <a href="/user/mypage/mylike?category=${categoryParam}&page=${pageMaker.endPage + 1}">다음</a>
                    </li>
                </c:if>
                </c:when>
                    <c:otherwise>
                        <c:if test="${pageMaker.prev}">
                            <li><a href="/user/mypage/mylike?page=${pageMaker.startPage - 1}">이전</a></li>
                        </c:if>

                        <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                            <li id="pageIdx" <c:out value="${pageMaker.dto.page == idx ? 'class=active' : ''}"/> style="padding: 0 3px;">
                                <a href="/user/mypage/mylike?page=${idx}">${idx}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${pageMaker.next && pageMaker.endPage >0}">
                            <li>
                                <a href="/user/mypage/mylikepage=${pageMaker.endPage + 1}">다음</a>
                            </li>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>


</div>

<div id="modal-like" class="modal modal-overlay">
    <div class="modal-window modal">
        <div class="modalTitle">오늘의 게임</div>
        <div id="close-area" class="close text-important">X</div>
        <div class="selected-img"></div>
        <div class="modalContent"></div>
        <div class="gameLink"></div>
        <div class="heart_section">
            <div class="like_count"></div>
            <span><i id="likeBtn" class="far fa-heart fav_icon"></i></span>
        </div>
        <div class="modalContent">
            <div class="gameCmtList"></div>
            <div>
                <form id="gameCmtFrm" class="form-group">
                    <input class="form-control" id="gameCmtCtnt" type="text" name="ctnt">
                    <input style="font-weight: bolder; color: white" class="btn btn-outline-dark" type="button"
                           id="cmt_submit" value="나의 평가 쓰기">
                </form>
            </div>
        </div>
    </div>
</div>
