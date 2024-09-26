package com.koreait.whattodo.user;

import com.koreait.whattodo.model.user.UserDto;
import com.koreait.whattodo.model.user.UserEntity;
import com.koreait.whattodo.model.user.UserVo;
import com.koreait.whattodo.model.user.mypage.ChaUpwEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.Cookie;
import java.util.List;

@Mapper
public interface UserMapper {
    int insUser(UserDto dto); // 회원가입

    void insAutoLoginKey(@Param("key") String key,
                         @Param("uid") String uid,
                         @Param("day") int day); // 자동로그인 키 생성

    UserVo selUserWithAutoLogin(@Param("key") String loginKey); // 자동로그인 키 조회

    void updLoginKeyRenewal(@Param("key") String key,
                            @Param("day") int day); // 자동로그인 키 갱신

    void delAutoLoginKey(@Param("key") String key); // 자동로그인 키 만료

    UserVo selUser(UserEntity entity); // 아아디 중복검사

    List<UserVo> forgotEmailSel(UserDto dto); // 아이디 찾기

    void forgotPw(@Param("cookie") String cookie,
                  @Param("uid") String uid,
                  @Param("expiration_at") int expiration_at); // 비밀번호 찾은 후 다음진행을위한 쿠키생성

    int selFindPwKey(@Param("key") String key); // 접근하는사람이 비밀번호 찾기 진행중인지 확인

    void delFindPwKey(@Param("cookie") String cookie,
                      @Param("iuser") int iuser); // 역할을 다한 쿠키값은 만료시키기

    void findPw(ChaUpwEntity entity); // 새로운 비밀번호로 변경시키기
}
