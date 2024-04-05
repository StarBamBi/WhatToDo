package com.koreait.whattodo.interceptor;

import com.koreait.whattodo.UserUtils;
import com.koreait.whattodo.enums.user.LoginEnum;
import com.koreait.whattodo.model.user.UserDto;
import com.koreait.whattodo.model.user.UserEntity;
import com.koreait.whattodo.model.user.UserVo;
import com.koreait.whattodo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AutoLoginInterceptor implements HandlerInterceptor {
    private final UserService service;
    private final UserUtils userUtils;

    @Autowired
    public AutoLoginInterceptor(UserService service, UserUtils userUtils) {
        this.service = service;
        this.userUtils = userUtils;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception { // 컨트롤러에 접근하기전 작동
        UserEntity entity = userUtils.getLoginUser(); // 세션 가져옴
        if (entity == null && request.getCookies() != null) { // 세션이 비어있고 쿠키가 있어야함
            Cookie autoLoginCookie = null;
            for (Cookie cookie : request.getCookies()) { // 모든쿠키중 "loginKey" 라는 이름의 쿠키를 찾아서 생성해놓은 autoLoginCookie 안에 담음
                if (cookie.getName().equals("loginKey")) {
                    autoLoginCookie = cookie;
                    break;
                }
            }
            if (autoLoginCookie != null) { // 위 과정을 통해 필요한 쿠키가 있어야함
                UserVo vo = service.login(autoLoginCookie.getValue()); // cookie 안에있는 value 값을통해 로그인을 시도하여 성공하면 vo(반환)에 값을 담아줌
                if (vo.getLoginResult().equals(LoginEnum.SUCCESS)) { // 자동로그인 성공시에
                    userUtils.setLoginUser(vo); // 세션에 user정보 담아줌 (기존 login과 같은과정)

                    service.updAutoLoginKey(autoLoginCookie.getValue()); // db에서 cookie의 사용기간을 현제기준 일주일로 갱신시켜줌
                    autoLoginCookie.setMaxAge(60*60*24*UserService.Config.AUTO_LOGIN_KEY_EXPIRY_DATE); // 브라우저한테 넘겨줄 cookie 또한 사용기간 동일하게 갱신
                    autoLoginCookie.setPath("/"); // 모든경로에서 쿠키에 접근가능
                    response.addCookie(autoLoginCookie); // 브라우저에게 쿠키 보냄
                    if (request.getRequestURI().equals("/user/login") || request.getRequestURI().equals("/user/join")) { // 성공상태에서 login, join 접근시 main페이지로 보냄
                            response.sendRedirect("/board/main");
                            return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception { // 컨트롤러가 작동한 후 작동

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception { // 모든 로직이 작동한 후 마지막으로 작동

    }
}
