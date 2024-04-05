package com.koreait.whattodo.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto extends UserEntity{
    private boolean autoLogin;
    private String autoLoginKey;
}
