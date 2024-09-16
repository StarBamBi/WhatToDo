package com.koreait.whattodo.crawling;

import com.koreait.whattodo.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CrawlingMapper {
    // 게임메카 관련
    void insertRankMecaDb(List<MecaRankEntity> mecaList);
    void delMecaRank();
    List<MecaRankEntity> mecaRankList(MecaRankEntity entity);

    // 스팀 관련
    void insertRankSteamDb(List<SteamRankEntity> steamList);
    void delSteamRank();
    List<SteamRankEntity> steamRankList(SteamRankEntity entity);

    // 게임 평점 관련
    void insertRatingDb(List<RatingEntity> entities);
    void delRating();
    List<RatingEntity> ratingList(RatingEntity entity);

    //플랫폼 순위
    void insertPlatformRankDb(List<PlatformRankEntity> platformList);
    void delPlatformRank();
    List<PlatformRankEntity> platformRankList(PlatformRankEntity entity);
    void insPlatformImg(PlatformImgEntity platformList);
    void delPlatformImg();
    List<PlatformRankEntity> platformRankListWithImg(PlatformRankEntity entity);
}
