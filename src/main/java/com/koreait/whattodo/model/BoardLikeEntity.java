package com.koreait.whattodo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardLikeEntity {
    private int iboard;
    private int iuser;
    private String rdt;
    private int count;
    private String nm;
}
