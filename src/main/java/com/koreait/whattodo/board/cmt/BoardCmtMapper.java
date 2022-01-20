package com.koreait.whattodo.board.cmt;

import com.koreait.whattodo.model.BoardCmtEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCmtMapper {
    int insBoardCmt(BoardCmtEntity entity);
    List<BoardCmtEntity> selBoardCmtList(BoardCmtEntity entity);
    int delBoardCmt(BoardCmtEntity entity);
}
