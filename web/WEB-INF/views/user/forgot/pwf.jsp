<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form method="post" action="/user/forgot/pwf">
    <label>
        <span>Whattodo아이디 : </span>
        <input type="text" name="uid" required>
    </label>
    <br>
    <input type="submit" value="다음">
</form>
