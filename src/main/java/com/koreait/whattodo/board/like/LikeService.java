package com.koreait.whattodo.board.like;

import com.koreait.whattodo.UserUtils;
import com.koreait.whattodo.model.BoardLikeEntity;
import com.koreait.whattodo.model.GameLikeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LikeService {
    @Autowired
    private LikeMapper mapper;
    @Autowired
    private UserUtils userUtils;

    public int insBoardLike(BoardLikeEntity entity) {
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.insBoardLike(entity);
    }

    public BoardLikeEntity selBoardLike(int iboard) {
        return mapper.selBoardLike(createBoardLikeEntity(iboard));
    }

    public BoardLikeEntity boardLikeCount(BoardLikeEntity entity) {
        return mapper.boardLikeCount(entity);
    }

    public Map<String, Integer> getLikeInfo(int iboard) {
        BoardLikeEntity entity = createBoardLikeEntity(iboard);
        Map<String, Integer> result = new HashMap<>();

        BoardLikeEntity dbLikeInfo = selBoardLike(iboard);
        result.put("result", dbLikeInfo == null ? 0 : 1);
        result.put("count", boardLikeCount(entity).getCount());
        return result;
    }

    public Map<String, Integer> delLikeInfo(int iboard) {
        BoardLikeEntity entity = createBoardLikeEntity(iboard);
        Map<String, Integer> result = new HashMap<>();

        result.put("result", delBoardLike(iboard));
        result.put("count", boardLikeCount(entity).getCount());
        return result;
    }

    public int delBoardLike(int iboard) {
        return mapper.delBoardLike(createBoardLikeEntity(iboard));
    }

    private BoardLikeEntity createBoardLikeEntity(int iboard) {
        BoardLikeEntity entity = new BoardLikeEntity();
        entity.setIboard(iboard);
        entity.setIuser(userUtils.getLoginUserPk());
        return entity;
    }
}
