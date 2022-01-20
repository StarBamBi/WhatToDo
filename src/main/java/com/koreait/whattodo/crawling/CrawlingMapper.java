package com.koreait.whattodo.crawling;

import com.koreait.whattodo.model.MecaRankEntity;
import com.koreait.whattodo.model.SteamRankEntity;
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
}
