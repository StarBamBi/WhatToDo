package com.koreait.whattodo.enums.user;

public enum LoginEnum {
    SUCCESS, // 성공
    FAILURE, // 실패
    UID_ERR, // 계정 없음
    UPW_ERR, // 비밀번호 틀림
    UID_REGEX_ERR, // id 정규식 오류
    UPW_REGEX_ERR, // 비밀번호 정규식 오류
    COOKIE_ERR // 쿠키 에러
}
