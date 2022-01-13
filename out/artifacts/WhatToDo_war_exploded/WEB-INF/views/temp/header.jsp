<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<tiles:importAttribute name="menuList"/>
<div class="header">
    <h1>뭐하Gee</h1>
    <span class="login">로그인</span>
</div>
<div class="header_menu">
    <%-- <ul>
        <li>게임</li>
        <li>넷플릭스</li>
        <li>유튜브</li>
        <li>게시판</li>
    </ul> --%>
    <ul>
    <c:forEach items="${menuList}" var="item">
        <li>${item.nm}</li>
    </c:forEach>
    </ul>
</div>