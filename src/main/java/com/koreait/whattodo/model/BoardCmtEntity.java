package com.koreait.whattodo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardCmtEntity {
    private int icmt;
    private int iboard;
    private int iuser;
    private String ctnt;
    private String rdt;
    private String mdt;
}
