package com.koreait.whattodo.game.like;

import com.koreait.whattodo.model.BoardLikeEntity;
import com.koreait.whattodo.model.GameLikeEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameLikeMapper {
    int insGameLike(GameLikeEntity entity);
    GameLikeEntity selGameLike(GameLikeEntity entity);
    int delGameLike(GameLikeEntity entity);
    GameLikeEntity gameLikeCount(GameLikeEntity entity);
}
