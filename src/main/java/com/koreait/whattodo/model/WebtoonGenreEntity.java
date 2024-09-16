package com.koreait.whattodo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebtoonGenreEntity {
    private int rnum;
    private String nm;
    private String writer;
    private String rating;
    private String img;
    private int homepage;   // 홈페이지값 1이면 Naver
    private String genre;
    private String state;
    private String link;
}
