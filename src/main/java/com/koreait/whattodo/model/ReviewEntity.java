package com.koreait.whattodo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewEntity {
    private int rnum;
    private String ctnt;
    private String nm;
    private int iuser;
    private String nickname;
    private String rdt;
    private String mdt;
}
