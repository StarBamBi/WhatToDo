package com.koreait.whattodo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PlatformRankEntity {
    private int irank;
    private String rankNum;
    private String gameNm;
    private String company;
    private String genre;
    private String selLink;
    private String imgsrc;
}
