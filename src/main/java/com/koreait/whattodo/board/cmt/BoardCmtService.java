package com.koreait.whattodo.board.cmt;

import com.koreait.whattodo.UserUtils;
import com.koreait.whattodo.model.BoardCmtEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardCmtService {

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private BoardCmtMapper mapper;

    public int insBoardCmt(BoardCmtEntity entity) {
        entity.setIuser(userUtils.getLoginUserPk());
        entity.setNm(userUtils.getLoginUserNm());
        return mapper.insBoardCmt(entity);
    }

    public List<BoardCmtEntity> selBoardCmtList(int iboard) {
        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIboard(iboard);
        return mapper.selBoardCmtList(entity);
    }

    public int updBoardCmt(BoardCmtEntity entity) {
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.updBoardCmt(entity);
    }

    public int delboard(int icmt) {
        BoardCmtEntity entity = new BoardCmtEntity();
        entity.setIcmt(icmt);
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.delBoardCmt(entity);
    }
}
