package com.koreait.whattodo.user;

import com.koreait.whattodo.UserUtils;
import com.koreait.whattodo.enums.user.LoginEnum;
import com.koreait.whattodo.model.user.UserDto;
import com.koreait.whattodo.model.user.UserVo;
import com.koreait.whattodo.model.user.mypage.ChaUpwEntity;
import com.koreait.whattodo.model.user.mypage.ChaUpwVo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserUtils userUtils;


    public static class Config{
        public static final int AUTO_LOGIN_KEY_EXPIRY_DATE = 7; // 자동로그인 사용가능 기간
        public static final int FIND_PW_KEY_EXPIRY_DATE = 1; // 자동로그인 사용가능 기간
        public static final int COOKIE_ENCRYPTION_COUNT = 10; // 쿠키 암호화 숫자
    }

    private static class Regex { // 정규식
        private static final String ID = "^([a-zA-Z0-9]{4,15})$";
        private static final String PASSWORD = "^([a-zA-Z0-9!@_]{4,20})$";
    }

    public static boolean checkUid (String uid) { // uid 정규식 체크
        return uid.matches(Regex.ID);
    }

    public static boolean checkUpw (String upw) { // upw 정규식 체크
        return upw.matches(Regex.PASSWORD);
    }

    public int join(UserDto dto) { // 회원가입 로직
        UserDto copyEntity = new UserDto();
        BeanUtils.copyProperties(dto, copyEntity); // 같은거 하나더 만듬

        String hashPw = BCrypt.hashpw(copyEntity.getUpw(), BCrypt.gensalt());
        copyEntity.setUpw(hashPw); // 비밀번호 해시화 해서 넣음
        copyEntity.setLevel(2);
        return mapper.insUser(copyEntity);
    }

    public int idChk(String uid) { // 아이디 중복검사 & 아이디 찾기 1단계
        if (!checkUid(uid)) { // id 정규식검사
            return 2;
        }
        UserDto dto = new UserDto();
        dto.setUid(uid);
        UserVo result = mapper.selUser(dto);
        return result == null ? 1 : 0; // 계정이 없을시 사용가능
    }

    public UserVo login(UserDto dto) { // 로그인 로직
        UserVo vo = new UserVo();

        if (!UserService.checkUid(dto.getUid())) { // 정규식 검사
            vo.setLoginResult(LoginEnum.UID_REGEX_ERR); // 정규식 id 오류
            return vo;
        } else if (!UserService.checkUpw(dto.getUpw())) {
            vo.setLoginResult(LoginEnum.UPW_REGEX_ERR); // 정규식 pw 오류
            return vo;
        }

        try {
            if (mapper.selUser(dto) == null) {
                vo.setLoginResult(LoginEnum.UID_ERR); // 계정없음(아이디 오류)
                return vo;
            }
            vo = mapper.selUser(dto);
            if (!BCrypt.checkpw(dto.getUpw(), vo.getUpw())) {
                vo.setLoginResult(LoginEnum.UPW_ERR); // 비번 오류
                return vo;
            }
            vo.setLoginResult(LoginEnum.SUCCESS); // 성공
            return vo;
        } catch (Exception e) {
            e.printStackTrace();
            vo.setLoginResult(LoginEnum.FAILURE); // 알 수 없는 에러
            return vo;
        }
    }



    public void insAutoLoginKey(UserVo vo) { // Ch1.자동로그인  쿠키 생성
        String key = String.format("%s%s%s%f",
                vo.getUid(),
                vo.getUpw(),
                new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()),
                Math.random()); // String.format(uid+upw+생성날짜+0~9랜덤숫자1개)

        key = BCrypt.hashpw(key, BCrypt.gensalt()); // 암호화로 키생성
        mapper.insAutoLoginKey(key, vo.getUid(), Config.AUTO_LOGIN_KEY_EXPIRY_DATE); // db에 cookie 저장시킴
        vo.setAutoLoginKey(key); // vo(반환)값에 쿠키안에 넣어둘 값을 추가함
    }

    public UserVo login(String loginKey) { // Ch2.자동로그인 쿠키값으로 로그인
        UserVo dbuser = mapper.selUserWithAutoLogin(loginKey); // 쿠키로 받은 value값으로 db와 동일한지 비교해서 성공하면 유저정보를 가져옴
        if (dbuser != null) {
            dbuser.setLoginResult(LoginEnum.SUCCESS);
            return dbuser;
        }
        dbuser = new UserVo();
        dbuser.setLoginResult(LoginEnum.COOKIE_ERR); // 실패하면 vo(반환)값을 새로 객체화해서 실패 메세지만 반환함
        return dbuser;
    }

    public void updAutoLoginKey(String loginKey) { // Ch3.자동로그인 쿠키 만료기한 갱신
        mapper.updLoginKeyRenewal(loginKey, Config.AUTO_LOGIN_KEY_EXPIRY_DATE); // 쿠키안의 value 값으로 만료기간을 현재기준 일주일로 갱신시킴
    }

    public void delAutoLoginKey(String loginKey) { // Ch4.자동로그인 쿠키 만료
        mapper.delAutoLoginKey(loginKey); // value 값으로 만료기한을 현재시간으로 갱신시키고 만료여부를 true 로 바꿈
    }

    public int socialLogin(UserDto dto) { // 소셜 로그인
        UserVo vo = null;
        String upw = String.format("%f%s%f",
                Math.random(),
                new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()),
                Math.random()); // 비밀번호 = 0~9랜덤값 , 오늘날짜 , 0~9랜덤값
        upw = BCrypt.hashpw(upw, BCrypt.gensalt()); // 암호화
        dto.setUpw(upw);

        if (dto.getNm() == null) { // 성함 제공안하면 기본값 '제공안함'
            dto.setNm("not provided");
        }

        if (dto.getGender() == 0) { // 성별 없으면 기본값 '선택안함'
            dto.setGender(3);
        }

        if (dto.getPostAddress() == 0) { // 우편번호 없으면 기본값
            dto.setPostAddress(12345);
        }

        if (dto.getAddressFirst() == null)  { // 주소 없으면 기본값
            dto.setAddressFirst("주소없음");
        }

        try {
            if (mapper.selUser(dto) == null) {
                dto.setLevel(3); // 소셜 유저는 3번
                mapper.insUser(dto);
                vo = mapper.selUser(dto); // iuser 값 필요해서 넘어온 db값을 vo에 다시 담음
                userUtils.setLoginUser(vo);
                return 1;   // 아이디가 없어서 회원가입 처리
            }
            vo = mapper.selUser(dto);
            userUtils.setLoginUser(vo);
            return 2;   // 아이디가 있음
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;   // db에서 값 반환이 안됨
    }


    public List<UserVo> forgotId(UserDto dto) { // 아이디 찾기 2단계
        List<UserVo> vo = null;
        vo = mapper.forgotEmailSel(dto);
        return vo;
    }


    public String forgotPwKey(String uid) { // 비밀번호 찾기 1단계
        String cookie = String.format("%s%s%f",
                uid,
                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()),
                Math.random());
        for (int i = 0; i < Config.COOKIE_ENCRYPTION_COUNT; i++) {
                cookie = BCrypt.hashpw(cookie, BCrypt.gensalt());
        }
        mapper.forgotPw(cookie, uid, Config.FIND_PW_KEY_EXPIRY_DATE);
        return cookie;
    }

    public int selKey(String key) { // 비밀번호 찾기 2단계
        int iuser = mapper.selFindPwKey(key);
        return iuser;
    }

    public void delKey(String key, int iuser) { // 비밀번호 찾기 2.5단계
        mapper.delFindPwKey(key, iuser);
    }

    public ChaUpwVo findPw(ChaUpwEntity entity) { // 비밀번호 찾기 3단계
        ChaUpwVo vo = new ChaUpwVo(); // 실패시 결과값을 담아서 반환할 vo
        if (!UserService.checkUpw(entity.getNewUpw()) ||
                !UserService.checkUpw(entity.getNewUpwChk())) { // 정규식 체크
            vo.setChaUpwResult("값을 올바르게 작성해 주세요.");
            return vo;
        }

        if (!entity.getNewUpw().equals(entity.getNewUpwChk())) { // 새 비밀번호와 확인값이 같은지 체크
            vo.setChaUpwResult("비밀번호와 비밀번호 확인이 같지 않습니다.");
            return vo;
        }
        entity.setNewUpw(BCrypt.hashpw(entity.getNewUpw(), BCrypt.gensalt()));

        try {
            mapper.findPw(entity); // 모든게 확인됬을경우 새 비밀번호를 암호화해서 db에 update 시도
            vo.setChaUpwResult("비밀번호가 변경되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            vo.setChaUpwResult("알 수 없는 오류로 실패하였습니다. 잠시 후 다시 시도해주세요."); // update 실패
        }

        return vo; // 그후 vo => result == 결과값 Controller로 전성
    }
}





















