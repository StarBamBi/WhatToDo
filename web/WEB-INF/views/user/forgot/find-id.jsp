<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <form action="/user/find-id" method="post">
        <c:forEach var="data" items="${sessionScope.uidData}">
        <span>
            <input type="radio">
            <span>${data.uid}</span>
        </span>
            <span></span>
            <span>가입일 : ${data.rdt}</span>
            <br>
        </c:forEach>
    </form>
    <div>
        <input type="submit" value="비밀번호 찾기">
        <button><a href="/user/login">로그인 하기</a></button>
    </div>
</div>