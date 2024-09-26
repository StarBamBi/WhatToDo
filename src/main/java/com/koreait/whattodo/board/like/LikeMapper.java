package com.koreait.whattodo.board.like;

import com.koreait.whattodo.model.BoardLikeEntity;
import com.koreait.whattodo.model.GameLikeEntity;
import com.koreait.whattodo.model.user.UserPagingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeMapper {
    int insBoardLike(BoardLikeEntity entity);
    BoardLikeEntity selBoardLike(BoardLikeEntity entity);
    int delBoardLike(BoardLikeEntity entity);
    BoardLikeEntity boardLikeCount(BoardLikeEntity entity);
    List<BoardLikeEntity> selBoardLikeList(UserPagingDTO dto);
    List<BoardLikeEntity> selAllLikeList(BoardLikeEntity entity);

    List<BoardLikeEntity> selAllLikeListPaging(UserPagingDTO dto);
    List<BoardLikeEntity> selBoardLikeListPaging(UserPagingDTO dto);
}
