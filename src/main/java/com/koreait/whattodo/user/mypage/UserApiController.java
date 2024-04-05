package com.koreait.whattodo.user.mypage;

import com.koreait.whattodo.UserUtils;
import com.koreait.whattodo.model.ReviewEntity;
import com.koreait.whattodo.model.user.UserDto;
import com.koreait.whattodo.model.user.UserEntity;
import com.koreait.whattodo.review.ReviewService;
import com.koreait.whattodo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/kakao")
public class UserApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserUtils userUtils;

    @PostMapping
    public Map<String, Integer> insReviewWebtoon(@RequestBody UserDto dto) {
        Map<String, Integer> result = new HashMap<>();
        result.put("resultLogin", userService.socialLogin(dto));
        System.out.println(userUtils.getLoginUserPk());
        return result;
    }
}
