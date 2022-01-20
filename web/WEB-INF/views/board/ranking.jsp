<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="mecaContainer">
    <div>
        <h1>랭킹 리스트(게임메카)</h1>
    </div>
    <div>
        <table>
            <tr>
                <th>순위</th>
                <th>게임명</th>
                <th>회사</th>
            </tr>
            <c:forEach items="${requestScope.mecaRankList}" var="item">
                <tr>
                    <td>
                            <%--eq는 문자열이나 숫자가 같다는 의미--%>
                        <c:choose>
                            <c:when test="${item.rankNum eq '1'}"><i class="fas fa-crown gold"></i></c:when>
                            <c:when test="${item.rankNum eq '2'}"><i class="fas fa-crown sliver"></i></c:when>
                            <c:when test="${item.rankNum eq '3'}"><i class="fas fa-crown bronze"></i></c:when>
                        </c:choose>
                        <c:out value="${item.rankNum}"/></td>
                    <td><c:out value="${item.rankNm}"/></td>
                    <td><c:out value="${item.company}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="steamContainer">
    <div>
        <h1>랭킹 리스트(스팀)</h1>
    </div>
    <div>
        <table>
            <tr>
                <th>순위</th>
                <th>게임명</th>
            </tr>
            <c:forEach items="${requestScope.steamRankList}" var="item">
                <tr>
                    <td>
                            <%--eq는 문자열이나 숫자가 같다는 의미--%>
                        <c:choose>
                            <c:when test="${item.rankNum eq '1'}"><i class="fas fa-crown gold"></i></c:when>
                            <c:when test="${item.rankNum eq '2'}"><i class="fas fa-crown sliver"></i></c:when>
                            <c:when test="${item.rankNum eq '3'}"><i class="fas fa-crown bronze"></i></c:when>
                        </c:choose>
                        <c:out value="${item.rankNum}"/></td>
                    <td><c:out value="${item.rankNm}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>