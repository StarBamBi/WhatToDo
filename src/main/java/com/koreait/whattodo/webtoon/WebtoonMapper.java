package com.koreait.whattodo.webtoon;

import com.koreait.whattodo.model.WebtoonEntity;
import com.koreait.whattodo.model.WebtoonGenreEntity;
import com.koreait.whattodo.model.WebtoonRecommandEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WebtoonMapper {
    // 네이버
    void insertWebtoon(List<WebtoonEntity> webtoonEntityList);
    void insertRecommandWebtoon(List<WebtoonRecommandEntity> webtoonRecommandEntityList);
    void insertGenredWebtoon(List<WebtoonGenreEntity> webtoonGenreEntityList);
    void delWebtoon();
    void delRecommandWebtoon();
    void delGenreWebtoon();
    List<WebtoonEntity> webtoonList();
    List<WebtoonEntity> webtoonListRandom();
    List<WebtoonRecommandEntity> webtoonRecommandList();
    List<WebtoonRecommandEntity> webtoonRecommandListRandom();
    List<WebtoonGenreEntity> webtoonGenreListAll();
    List<WebtoonGenreEntity> webtoonGenreListBtnRandom();
}
