package com.koreait.whattodo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RatingEntity {
    private int rum;
    private String gameNm;
    private String gameRating;
    private String gameRank;
}
