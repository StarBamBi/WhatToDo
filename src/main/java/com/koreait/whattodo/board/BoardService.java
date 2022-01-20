package com.koreait.whattodo.board;

import com.koreait.whattodo.UserUtils;
import com.koreait.whattodo.model.BoardEntity;
import com.koreait.whattodo.model.BoardPrevNextVo;
import com.koreait.whattodo.model.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BoardService {

    @Autowired
    private BoardMapper mapper;

    @Autowired
    private UserUtils userUtils;

    public int insBoard(BoardEntity entity) {
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.insBoard(entity);
    }

    public List<BoardEntity> selBoardList(BoardEntity entity) {
        return mapper.selBoardList(entity);
    }

    public BoardEntity selBoard(BoardEntity entity) {
        BoardEntity detail = mapper.selBoard(entity);
        if(entity.getLastip() != null && !Objects.equals(entity.getLastip(), detail.getLastip())){
            int hitsResult = mapper.addHits(entity);
            if(hitsResult == 1) {
                detail.setHits(detail.getHits() + 1);
            }
        }
        return detail;
    }

    public int updBoard(BoardEntity entity) {
        try {
            entity.setIuser(userUtils.getLoginUserPk());
            return mapper.updBoard(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int delBoard(BoardEntity entity) {
        entity.setIuser(userUtils.getLoginUserPk());
        entity.setIsdel(1);
        return mapper.updBoard(entity);
    }

    public BoardPrevNextVo selPrevNext(BoardVo vo) {
        return mapper.selPrevNext(vo);
    }
}
