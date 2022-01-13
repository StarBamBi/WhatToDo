package com.koreait.whattodo.board;

import com.koreait.whattodo.model.BoardEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardEntity> selBoardList(BoardEntity entity);
    BoardEntity selBoard(BoardEntity entity);
    int insBoard(BoardEntity entity);
    int addHits(BoardEntity entity);
}
