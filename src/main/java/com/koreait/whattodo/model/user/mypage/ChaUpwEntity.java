package com.koreait.whattodo.model.user.mypage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChaUpwEntity {
    private int iuser;
    private String oldUpw;
    private String newUpw;
    private String newUpwChk;
}
