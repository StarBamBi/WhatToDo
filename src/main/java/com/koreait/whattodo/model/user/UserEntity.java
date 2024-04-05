package com.koreait.whattodo.model.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserEntity {
    private int iuser;
    private String uid;
    private String upw;
    private String nm;
    private int gender;
    private int contact;
    private int postAddress;
    private String addressFirst;
    private String addressSecond;
    private String profileimg;
    private String rdt;
    private String mdt;
    private int level;
}
