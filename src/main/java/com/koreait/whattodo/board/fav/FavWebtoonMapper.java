package com.koreait.whattodo.board.fav;

import com.koreait.whattodo.model.FavWebtoonEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavWebtoonMapper {
    int insWebtoonFav(FavWebtoonEntity entity);
    FavWebtoonEntity selWebtoondFav(FavWebtoonEntity entity);
    List<FavWebtoonEntity> selWebtoonMyFav(FavWebtoonEntity entity);
    FavWebtoonEntity selWebtoonFavCount(FavWebtoonEntity entity);
    int delWebtoonFav(FavWebtoonEntity entity);
}
