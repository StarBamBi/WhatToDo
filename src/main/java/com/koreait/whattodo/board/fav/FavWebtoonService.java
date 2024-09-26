package com.koreait.whattodo.board.fav;

import com.koreait.whattodo.UserUtils;
import com.koreait.whattodo.model.FavWebtoonEntity;
import com.koreait.whattodo.model.user.UserPagingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavWebtoonService {
    @Autowired
    private FavWebtoonMapper mapper;
    @Autowired private UserUtils userUtils;

    public int insWebtoonFav(FavWebtoonEntity entity) {
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.insWebtoonFav(entity);
    }

    public FavWebtoonEntity selWebtoondFav(String nm) {
        return mapper.selWebtoondFav(createBoardFavEntity(nm));
    }

    public List<FavWebtoonEntity> selWebtoonLikeList(UserPagingDTO dto) {
        dto.setIuser(userUtils.getLoginUserPk());
        return mapper.selWebtoonLikeList(dto);
    }

    public List<FavWebtoonEntity> selWebtoonLikeListPaging(UserPagingDTO dto) {
        dto.setIuser(userUtils.getLoginUserPk());
        return mapper.selWebtoonLikeListPaging(dto);
    }

    public int selWebtoonFavCnt(String nm) {
        FavWebtoonEntity entity = new FavWebtoonEntity();
        entity.setNm(nm);
        entity.setIuser(userUtils.getLoginUserPk());
        return mapper.selWebtoonFavCount(entity).getCount();
    }

    public int delWebtoonFav(String nm) {
        return mapper.delWebtoonFav(createBoardFavEntity(nm));
    }

    private FavWebtoonEntity createBoardFavEntity(String nm) {
        FavWebtoonEntity entity = new FavWebtoonEntity();
        entity.setNm(nm);
        entity.setIuser(userUtils.getLoginUserPk());
        return entity;
    }
}
