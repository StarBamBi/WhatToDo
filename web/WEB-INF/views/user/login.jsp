<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="login_font">
    <h1>Login</h1>
</div>
<form class="login_container" method="post" action="/user/login">
    <div>ID : <input type="text" name="uid" class="login_input"></div>
    <div>PW : <input type="password" name="upw" class="login_input"></div>
    <div>
        <input type="checkbox" > 아이디 저장 <input type="checkbox"> 자동 로그인
    </div>
    <div>
        <input class="login_btn" type="submit" value="Login">
    </div>
</form>

<div>
    <div><a>이메일 또는 비밀번호를 잊으셨나요?</a></div>
    <div><a href="/user/join">아직 계정이 없으신가요?</a></div>
</div>