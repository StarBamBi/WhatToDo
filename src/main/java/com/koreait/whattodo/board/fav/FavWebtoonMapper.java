package com.koreait.whattodo.board.fav;

import com.koreait.whattodo.model.FavWebtoonEntity;
import com.koreait.whattodo.model.WebtoonGenreEntity;
import com.koreait.whattodo.model.user.UserPagingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavWebtoonMapper {
    int insWebtoonFav(FavWebtoonEntity entity);
    FavWebtoonEntity selWebtoondFav(FavWebtoonEntity entity);
    FavWebtoonEntity selWebtoonFavCount(FavWebtoonEntity entity);
    int delWebtoonFav(FavWebtoonEntity entity);
    List<FavWebtoonEntity> selWebtoonLikeList(UserPagingDTO dto);
    List<WebtoonGenreEntity> selWebtoonLikeInfoList(FavWebtoonEntity entity);

    List<FavWebtoonEntity> selWebtoonLikeListPaging(UserPagingDTO dto);
}
