<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div>

    <h1>자유게시판</h1>
    <div>
        <c:choose>
            <c:when test="${fn:length(requestScope.list) == 0}">
                글이 없습니다.
            </c:when>
            <c:otherwise>
                <table>
                    <tr>
                        <th>NO</th>
                        <th>제목</th>
                        <th>조회수</th>
                        <th>작성자</th>
                        <th>작성일시</th>
                    </tr>
                    <c:forEach items="${requestScope.list}" var="item">
                        <tr class="record" data-iboard="${item.iboard}">
                            <td>${item.iboard}</td>
                            <td><c:out value="${item.title}"/></td>
                            <td>${item.hits}</td>
                            <td>${item.iuser}</td>
                            <td>${item.rdt}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
<%--        <c:if test="${sessionScope.loginUser != null}">--%>
            <input type="button" value="글쓰기" onclick="location.href='/board/write.jsp'">
<%--        </c:if>--%>
    </div>
</div>
