package com.koreait.whattodo.board;

import com.koreait.whattodo.model.BoardEntity;
import com.koreait.whattodo.model.BoardPrevNextVo;
import com.koreait.whattodo.model.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardEntity> selBoardList(BoardEntity entity);
    BoardEntity selBoard(BoardEntity entity);
    int insBoard(BoardEntity entity);
    int addHits(BoardEntity entity);
    int updBoard(BoardEntity entit);
    BoardPrevNextVo selPrevNext(BoardVo vo);
}
