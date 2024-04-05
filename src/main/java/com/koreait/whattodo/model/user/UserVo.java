package com.koreait.whattodo.model.user;

import com.koreait.whattodo.enums.user.LoginEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo extends UserEntity{
    private LoginEnum loginResult;
    private String autoLoginKey;
}
