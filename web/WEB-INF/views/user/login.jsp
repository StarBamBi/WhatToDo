<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="login_form">
    <h1>로그인</h1>
    <div>
        <form class="login_container" method="post" action="/user/login" id="login-form">
            <div><input type="text" name="uid" class="login_input" placeholder="아이디" value="${requestScope.dto.uid}"></div>
            <div><input type="password" name="upw" class="login_input" placeholder="비밀번호" value="${requestScope.dto.upw}"></div>
            <div>
                <h5 class="err_msg_b">${requestScope.keymsg}</h5> <%--   아이디 또는 비밀번호가 일치하지 않습니다. <br>다시 시도해 주세요.     --%>
                <h5 class="err_msg_b">${requestScope.nmsg}</h5><%--   알 수 없는 이유로 로그인에 실패하였습니다.     --%>
                <h5 class="err_msg_b">${requestScope.rmsg}</h5><%--   아이디와 비밀번호를 바르게 작성해주세요.     --%>
            </div>
            <div>
                <label>
                    <input type="checkbox" name="autoLogin"> 자동 로그인
                </label>
            </div>
            <div>
                <input class="login_btn" type="submit" value="Login">
            </div>
        </form>
    </div>
    <div>
        <div class="login_find">
            <ul>
                <li><a href="/user/forgot/id">아이디 찾기</a></li>
                <li><a href="/user/forgot/pwf">비밀번호 찾기</a></li>
                <li><a href="/user/join">회원가입</a></li>
            </ul>
        </div>
    </div>

    <div class="api_login">
        <div class="api_login_guide">
            <span class="divider"></span>
            <span class="api_login_guide_text">또는</span>
            <span class="divider"></span>
        </div>
        <ul>
            <li>
                <img src="/res/img/kakao_login_medium_narrow.png" alt="카카오 로그인" onclick="kakaoLogin();">
            </li>
            <li>
                <!-- 네이버 로그인 버튼 노출 영역 -->
                <div id="naver_id_login"></div>
            </li>
            <li onclick="kakaoLogout();">
                <a href="javascript:void(0)">
                    <span>카카오 연결끊기(로그아웃이었던 것)</span>
                </a>
            </li>
        </ul>
    </div>
</div>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>

