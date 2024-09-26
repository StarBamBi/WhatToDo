package com.koreait.whattodo.game.cmt;

import com.koreait.whattodo.model.BoardCmtEntity;
import com.koreait.whattodo.model.GameCmtEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GameCmtMapper {
    int insGameCmt(GameCmtEntity entity);
    List<GameCmtEntity> selGameCmtList(GameCmtEntity entity);
    List<GameCmtEntity> selGameCmtListMy(GameCmtEntity entity);
    int delGameCmt(GameCmtEntity entity);
    int updGameCmt(GameCmtEntity entity);
}
