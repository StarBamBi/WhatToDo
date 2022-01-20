<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="join_container">
    <h1>회원가입</h1>
    <form action="/user/join" method="post" id="join-frm">
        <div>
            <h4 class="join_title" style="padding-right: 15rem;">아이디</h4>
            <input type="text" name="uid" class="textBox" required>
            <h5 class="err_msg" hidden>아이디는 대소문자, 숫자조합으로 4~15글자가 되어야 합니다.</h5>
        </div>
        <div>
            <h4 class="join_title" style="padding-right: 14rem;">패스워드</h4>
            <input type="password" name="upw" class="textBox" required>
            <h5 class="err_msg" hidden>비밀번호는 대소문자, 숫자, !@_ 조합으로 4~20글자가 되어야 합니다.</h5>
        </div>
        <div>
            <h4 class="join_title" style="padding-right: 12rem;">패스워드 확인</h4>
            <input type="password" name="upw-check" class="textBox" required>
        </div>
        <div>
            <h4 class="join_title" style="padding-right: 16rem;">이름</h4>
            <input type="text" name="nm" class="textBox" required>
        </div>
        <div>
            <h4 class="join_title" style="padding-right: 16rem;">성별</h4>
            <span>
                <select name="gender" class="selBox">
                    <option value="1">남자</option>
                    <option value="2">여자</option>
                </select>
            </span>
        </div>
        <div>
            <input type="submit" value="회원가입" class="submit_btn">
        </div>
    </form>
</div>
