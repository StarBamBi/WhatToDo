package com.koreait.whattodo.user.mypage;

import com.koreait.whattodo.FileUtils;
import com.koreait.whattodo.UserUtils;
import com.koreait.whattodo.model.user.UserDto;
import com.koreait.whattodo.model.user.UserEntity;
import com.koreait.whattodo.model.user.UserVo;
import com.koreait.whattodo.model.user.mypage.ChaUpwEntity;
import com.koreait.whattodo.model.user.mypage.ChaUpwVo;
import com.koreait.whattodo.user.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserMypageService {

    @Autowired
    UserMypageMapper mapper;

    @Autowired
    UserUtils userUtils;
    @Autowired
    FileUtils fileUtils;

    public static class Config{
        public static final String UPLOAD_IMG_PATH = "D:/upload/images"; // 이미지 저장 경로
    }

    public String uploadProfileImg(MultipartFile profileimg) {
        if (profileimg == null) {
            return null;
        }

        UserEntity loginUser = userUtils.getLoginUser(); // 로그인 유저정보

        final String PATH = Config.UPLOAD_IMG_PATH + "/user/" + loginUser.getIuser(); // 경로생성 D:/upload/images/user/${iuser}/
        String fileNm = fileUtils.saveFile(PATH, profileimg); // 경로에 파일 생성
        System.out.println("fileNm : " + fileNm);

        if (fileNm == null) { // 생성 실패하면 리턴
            return null;
        }

        UserDto dto = new UserDto();
        dto.setIuser(loginUser.getIuser()); // update 쿼리에 쓸 iuser 값 dto로 가져오기

        // 기존 이미지 제거
        String oldFile = PATH + "/" + loginUser.getProfileimg();
        fileUtils.delFile(oldFile);

        // UserDB에 이미지값 update 시키기
        dto.setProfileimg(fileNm);
        mapper.updProfileImg(dto);

        // 세션에 프로필 이미지값 업데이트
        loginUser.setProfileimg(fileNm);
        return fileNm;
    }

    public ChaUpwVo passwordChange(ChaUpwEntity entity) { // 패스워드 변경
        ChaUpwVo vo = new ChaUpwVo(); // 실패시 결과값을 담아서 반환할 vo
        if (!UserService.checkUpw(entity.getOldUpw()) ||
            !UserService.checkUpw(entity.getNewUpw()) ||
            !UserService.checkUpw(entity.getNewUpwChk())) { // 정규식 체크
            vo.setChaUpwResult("값을 올바르게 작성해 주세요.");
            return vo;
        }
        if (userUtils.getLoginUserPk() == 0) { // 로그인 되어있는 유저인지 확인
            vo.setChaUpwResult("잘못된 접근입니다.");
            return vo;
        }




        entity.setIuser(userUtils.getLoginUserPk());
        UserVo userVo = mapper.selUpw(entity);
        if (!BCrypt.checkpw(entity.getOldUpw(), userVo.getUpw())) { // 현재 비밀번호 확인 => iuser로 유저정보 전체를 UserVo로 가져와서 패스워드와 비교
            vo.setChaUpwResult("현재 비밀번호가 올바르지 않습니다.");
            return vo;
        }

        if (!entity.getNewUpw().equals(entity.getNewUpwChk())) { // 새 비밀번호와 확인값이 같은지 체크
            vo.setChaUpwResult("비밀번호와 비밀번호 확인이 같지 않습니다.");
            return vo;
        }
        entity.setNewUpw(BCrypt.hashpw(entity.getNewUpw(), BCrypt.gensalt()));

        try {
            mapper.updUpw(entity); // 모든게 확인됬을경우 새 비밀번호를 암호화해서 db에 update 시도
            vo.setChaUpwResult("성공");
        } catch (Exception e) {
            vo.setChaUpwResult("알 수 없는 오류로 실패하였습니다. 잠시 후 다시 시도해주세요."); // update 실패
        }

        return vo; // 그후 vo => result == 결과값 Controller로 전성
    }
}
