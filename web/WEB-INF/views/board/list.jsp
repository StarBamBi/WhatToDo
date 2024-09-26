<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="listContainer">

    <h1 id="listTitle">자유게시판</h1>
    <div class="form-group dropdown" id="paging">
        <select class="form-control" style="width: 20%; font-size: small">
            <option value="5">5개씩 보기</option>
            <option value=10>10개씩 보기</option>
            <option value=15>15개씩 보기</option>
            <option value=20>20개씩 보기</option>
        </select>
    </div>
    <div>
        <c:choose>
            <c:when test="${fn:length(requestScope.list) == 0}">
                글이 없습니다.
            </c:when>
            <c:otherwise>
                <table class="table">
                    <tr class="showList thead-light">
                        <th>NO</th>
                        <th id="lTitle">제목</th>
                        <th>조회수</th>
                        <th>작성자</th>
                    </tr>
                    <c:forEach items="${requestScope.list}" var="item">
                        <tr class="record showList" data-iboard="${item.iboard}">
                            <td>${item.iboard}</td>
                            <td><c:out value="${item.title}"/></td>
                            <td>${item.hits}</td>
                            <td>${item.nm}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        <div class="form-group">
            <input type="text" class="form-control searchTxt">
            <select class="form-control" id="sel1">
                <option>검색 조건</option>
                <option value="1">제목</option>
                <option value="2">작성자</option>
            </select>
            <button type="button" class="btn btn-light searchBtn">찾기</button>
        </div>
        <div id="pageIdx">
            <ul class="pagination">

            </ul>
        </div>
        <c:if test="${sessionScope.loginUser != null}">
            <div id="writeBtnContainer">
                <div class="btn btn-info" id="writeBtn" onclick="location.href='/board/write'">글쓰기</div>
            </div>
        </c:if>
    </div>
</div>
