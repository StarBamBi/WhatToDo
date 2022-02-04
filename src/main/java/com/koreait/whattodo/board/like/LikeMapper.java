package com.koreait.whattodo.board.like;

import com.koreait.whattodo.model.BoardLikeEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {
    int insBoardLike(BoardLikeEntity entity);
    BoardLikeEntity selBoardLike(BoardLikeEntity entity);
    int delBoardLike(BoardLikeEntity entity);
}