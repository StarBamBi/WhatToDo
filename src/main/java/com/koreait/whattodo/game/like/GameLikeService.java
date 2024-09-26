package com.koreait.whattodo.game.like;

import com.koreait.whattodo.UserUtils;
import com.koreait.whattodo.model.BoardLikeEntity;
import com.koreait.whattodo.model.GameLikeEntity;
import com.koreait.whattodo.model.user.UserPagingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameLikeService {
    @Autowired
    private GameLikeMapper mapper;
    @Autowired
    private UserUtils userUtils;

    public int insGameLike(GameLikeEntity entity) {
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.insGameLike(entity);
    }

    public GameLikeEntity selGameLike(GameLikeEntity entity) {
        return mapper.selGameLike(entity);
    }

    public List<GameLikeEntity> selGameLikeList(UserPagingDTO dto) {
        dto.setIuser(userUtils.getLoginUserPk());
        return mapper.selGameLikeList(dto);
    }

    public List<GameLikeEntity> selGameLikeListPaging(UserPagingDTO dto) {
        dto.setIuser(userUtils.getLoginUserPk());
        return mapper.selGameLikeListPaging(dto);
    }

    public GameLikeEntity gameLikeCount(GameLikeEntity entity) {
        return mapper.gameLikeCount(entity);
    }

    public Map<String, Integer> getLikeInfo(String gameNm) {
        GameLikeEntity entity = createGameLikeEntity(gameNm);
        Map<String, Integer> result = new HashMap<>();

        GameLikeEntity dbLikeInfo = selGameLike(entity);
        result.put("result",  dbLikeInfo == null ? 0 : 1);
        result.put("count", gameLikeCount(entity).getCount());
        return result;
    }

    public Map<String, Integer> delLikeInfo(String gameNm) {
        GameLikeEntity entity = createGameLikeEntity(gameNm);
        Map<String, Integer> result = new HashMap<>();

        result.put("result", delGameLike(entity));
        result.put("count", gameLikeCount(entity).getCount());
        return result;
    }

    public int delGameLike(GameLikeEntity entity) {
        return mapper.delGameLike(entity);
    }

    private GameLikeEntity createGameLikeEntity(String gameNm) {
        GameLikeEntity entity = new GameLikeEntity();
        entity.setGameNm(gameNm);
        entity.setIuser(userUtils.getLoginUserPk());
        return entity;
    }


}
