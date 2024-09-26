<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <form method="post">
        <label>
            <span>이름</span>
            <input type="text" name="nm" class="textBox" required>
        </label>
        <br>
        <label>
            <span>휴대폰</span>
            <input type="text" name="contact" class="textBox" maxlength="8" placeholder="010 제외, '-'빼고 휴대폰 번호">
        </label>
        <br>
        <div style="color: red">${requestScope.err}</div>
        <div>
            <input type="submit" value="아이디 찾기">
        </div>
    </form>
</div>
