package com.koreait.whattodo.user;

import com.koreait.whattodo.UserUtils;
import com.koreait.whattodo.enums.user.LoginEnum;
import com.koreait.whattodo.model.user.UserDto;
import com.koreait.whattodo.model.user.UserEntity;
import com.koreait.whattodo.model.user.UserVo;
import com.koreait.whattodo.model.user.mypage.ChaUpwEntity;
import com.koreait.whattodo.model.user.mypage.ChaUpwVo;
import com.koreait.whattodo.user.mypage.UserMypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private UserUtils userUtils;
    @Autowired
    private UserMypageService userMypageService;

    @GetMapping("/idChk/{uid}")
    @ResponseBody
    public Map<String, Integer> idChk(@PathVariable String uid) {
        Map<String, Integer> res = new HashMap();
        res.put("result", service.idChk(uid));
        return res;
    }

    @GetMapping("/login")
    public String login() {
        UserEntity loginUser = userUtils.getLoginUser();
        // 로그인한 유저의 경우 로그인창으로 접근 막음
        if (loginUser != null) {
            return "redirect:/board/main";
        }

        return "user/login";
    }

    @PostMapping("/login")
    public String loginPost(UserVo vo, RedirectAttributes reAttr, HttpServletResponse response, UserDto dto) {
        UserEntity loginUser = userUtils.getLoginUser();
        // 로그인한 유저의 경우 로그인창으로 접근 막음
        if (loginUser != null) {
            return "redirect:/board/main";
        }

        vo = service.login(dto); // 로그인 결과
        if (vo.getLoginResult().equals(LoginEnum.UID_REGEX_ERR) || vo.getLoginResult().equals(LoginEnum.UPW_REGEX_ERR)) { // 정규식 오류
            reAttr.addFlashAttribute("dto", dto);
            reAttr.addFlashAttribute("nmsg", "");
            reAttr.addFlashAttribute("keymsg", "");
            reAttr.addFlashAttribute("rmsg", "아이디와 비밀번호를 바르게 작성해주세요.");
            return "redirect:/user/login";
        } else if (vo.getLoginResult().equals(LoginEnum.UID_ERR) || vo.getLoginResult().equals(LoginEnum.UPW_ERR)) { // 아이디, 비번 오류
            reAttr.addFlashAttribute("dto", dto);
            reAttr.addFlashAttribute("nmsg", "");
            reAttr.addFlashAttribute("keymsg", "아이디 또는 비밀번호가 일치하지 않습니다. <br>다시 시도해 주세요.");
            reAttr.addFlashAttribute("rmsg", "");
            return "redirect:/user/login";
        } else if (vo.getLoginResult() == LoginEnum.SUCCESS) { // 성공
            userUtils.setLoginUser(vo); // 세션에 로그인정보 담음
            if (dto.isAutoLogin()) { // 자동로그인 여부를 확인
                service.insAutoLoginKey(vo); // 쿠키로 보낼 키 생성해서 db에 넣어둠
                Cookie cookie = new Cookie("loginKey", vo.getAutoLoginKey()); // cookie 만듬
                cookie.setMaxAge(60*60*24*UserService.Config.AUTO_LOGIN_KEY_EXPIRY_DATE); // cookie 사용기간 (60*60*24) = (하루 * 7) = 일주일
                cookie.setPath("/"); // 모든 경로에서 cookie 접근 가능
                response.addCookie(cookie); // cookie 보내줌
                return "redirect:/board/main";
            }
            return "redirect:/board/main";
        }
        reAttr.addFlashAttribute("nmsg", "알 수 없는 이유로 로그인에 실패하였습니다.");
        reAttr.addFlashAttribute("keymsg", "");
        reAttr.addFlashAttribute("rmsg", "");
        return "redirect:/user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession hs, HttpServletRequest request, HttpServletResponse response) {
        if (userUtils.getLoginUser() != null) {
            Cookie loginCookie = null;
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("loginKey")) { // 브라우저가 가진 쿠키중 "loginKey"라는 쿠키가 있을경우 잡아냄
                    loginCookie = cookie;
                    break;
                }
            }
            if (loginCookie != null) { // 해당 쿠키의 내용이 있을경우
                service.delAutoLoginKey(loginCookie.getValue()); // db의 쿠키정보를 만료일을 현재시간으로 만들고 만료상태를 true로 update함
                loginCookie.setMaxAge(0); // 브라우저에게 줄 쿠키의 사용기한을 0으로 넣음
                loginCookie.setValue(null); //쿠키의 내용을 비워줌
                response.addCookie(loginCookie); // 해당 쿠키를 브라우저의 메모리에 보내줘서 기존 쿠키를 만료시킴
            }
        }
        hs.invalidate(); // login 정보가 담긴 session 비워줌
        return "redirect:/user/login";
    }

    @GetMapping("/join")
    public String join() {
        UserEntity loginUser = userUtils.getLoginUser();
        // 로그인 세션을 가져와서 로그인한 유저일경우 회원가입접근 막음
        if (loginUser != null) {
            return "redirect:/board/main";
        }
        return "user/join";
    }

    @PostMapping("/join")
    public String joinPost(UserDto dto, Model model) {
        UserEntity loginUser = userUtils.getLoginUser();
        // 로그인 세션을 가져와서 로그인한 유저일경우 회원가입접근 막음
        if (loginUser != null) {
            return "redirect:/board/main";
        }
        int result = service.join(dto);
        if (result == 1) {
            return "redirect:/user/login";
        }
        model.addAttribute("msg", "알 수 없는 오류로 회원가입에 실패하였습니다.");
        return "redirect:/user/join";
    }

    @GetMapping("/naver/callback")
    public String naverCallback() {
        return "/user/naver/callback";
    }

    @PostMapping("/naver/login")
    @ResponseBody
    public int naverLogin(@RequestBody UserDto dto) {
        int result = service.socialLogin(dto);
        System.out.println("API 로그인 결과 : " + result);
        return result;
    }



    @GetMapping("/forgot/id")
    public void forgotId() {}

    @PostMapping("/forgot/id")
    public String forgotIdPost(UserDto dto, HttpSession session, RedirectAttributes reAttr) {
        List<UserVo> list = service.forgotId(dto);
        if (list.size() > 0) {
            session.setAttribute("uidData", list);
            return "redirect:/user/forgot/find-id";
        }
        reAttr.addFlashAttribute("err", "계정이 존재하지 않습니다.");
        return "redirect:/user/forgot/id";
    }

    @GetMapping("/forgot/find-id")
    public void findId() {}

    @PostMapping("/forgot/find-id")
    public String findIdPost() {
        return null;
    }


    @GetMapping("/forgot/pwf")
    public void forgotPwF(UserEntity entity) {}

    @PostMapping("/forgot/pwf")
    public String forgotPwFPost(String uid, HttpServletResponse response) {
        int result = service.idChk(uid);
        if (result == 0) {
            String key = service.forgotPwKey(uid);
            Cookie cookie = new Cookie("findPw", key);
            cookie.setMaxAge(60*60*24*UserService.Config.FIND_PW_KEY_EXPIRY_DATE);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/user/forgot/pws";
        }
        return null;
    }

    @GetMapping("/forgot/pws")
    public void forgotPwS() {}

    @PostMapping("/forgot/pws")
    public String forgotPwSPost(ChaUpwEntity entity, RedirectAttributes reAttr) {
        ChaUpwVo result = service.findPw(entity);
        reAttr.addFlashAttribute("result", result.getChaUpwResult());
        System.out.println(result.getChaUpwResult());
        if (result.getChaUpwResult().equals("비밀번호가 변경되었습니다.")) {
            return "/user/forgot/find-pw";
        }
        return "redirect:/user/forgot/pws";
    }
}





















