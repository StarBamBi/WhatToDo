package com.koreait.whattodo.interceptor;

import com.koreait.whattodo.UserUtils;
import com.koreait.whattodo.model.user.UserEntity;
import com.koreait.whattodo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FindPwSInterceptor implements HandlerInterceptor {
    private final UserUtils userUtils;
    private final UserService service;

    public FindPwSInterceptor(UserUtils userUtils, UserService service) {
        this.userUtils = userUtils;
        this.service = service;
    }

    @Autowired
    private HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        UserEntity entity = userUtils.getLoginUser();
        if (entity == null && request.getCookies() != null) {
            Cookie key = null;
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("findPw")) {
                    key = cookie;
                    break;
                }
            }
            if (key != null) {
               int iuser =  service.selKey(key.getValue());
               session.setAttribute("iuser", iuser);

               service.delKey(key.getValue(), iuser);
               key.setMaxAge(0);
               key.setValue(null);
               response.addCookie(key);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
