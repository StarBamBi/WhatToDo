<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="/user/forgot/pws" method="post">
    <input type="hidden" name="iuser" value="${sessionScope.iuser}">
    <div>
        <label>
            <span>새 비밀번호</span>
            <input type="password" name="newUpw">
        </label>
    </div>
    <div>
        <label>
            <span>새 비밀번호 확인</span>
            <input type="password" name="newUpwChk">
        </label>
    </div>
    <input type="submit" value="변경하기">
</form>

