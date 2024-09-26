package com.koreait.whattodo.review;

import com.koreait.whattodo.UserUtils;
import com.koreait.whattodo.model.ReviewEntity;
import com.koreait.whattodo.model.user.UserEntity;
import com.koreait.whattodo.model.user.UserPagingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired private ReviewMapper reviewMapper;
    @Autowired private UserUtils userUtils;

    public ReviewEntity insReviewWebtoon(ReviewEntity entity) {
        entity.setIuser(userUtils.getLoginUserPk());
        entity.setNickname(userUtils.getLoginUserNm());
        reviewMapper.insReviewWebtoon(entity);
        return entity;
    }

    public List<ReviewEntity> selReviewWebtoon(String nm) {
        ReviewEntity entity = new ReviewEntity();
        entity.setNm(nm);
        return reviewMapper.selReviewWebtoon(entity);
    }

    public List<ReviewEntity> selReviewWebtoonMy() {
        ReviewEntity entity = new ReviewEntity();
        entity.setIuser(userUtils.getLoginUserPk());
        return reviewMapper.selReviewWebtoonMy(entity);
    }

    public List<ReviewEntity> selReviewAllMy() {
        return reviewMapper.selReviewAllMy();
    }

    public List<ReviewEntity> selReviewWebtoonMyPaging(UserPagingDTO dto) {
        dto.setIuser(userUtils.getLoginUserPk());
        return reviewMapper.selReviewWebtoonMyPaging(dto);
    }
    public List<ReviewEntity> selReviewGameMyPaging(UserPagingDTO dto) {
        dto.setIuser(userUtils.getLoginUserPk());
        return reviewMapper.selReviewGameMyPaging(dto);
    }
    public List<ReviewEntity> selReviewAllMyPaging(UserPagingDTO dto) {
//        dto.setPage(2); // 보여줄 페이지(시작 행, 2로 지정하면 (2-1)*10이 되서 10번째 값부터 PerPageNum개만큼 출력
//        dto.setPerPageNum(10);  // 한 페이지에 보여줄 갯수
        dto.setIuser(userUtils.getLoginUserPk());
        return reviewMapper.selReviewAllMyPaging(dto);
    }

    public int delReviewWebtoon(int rnum) {
        ReviewEntity entity = new ReviewEntity();
        entity.setRnum(rnum);
        entity.setIuser(userUtils.getLoginUserPk());
        return reviewMapper.delReviewWebtoon(entity);
    }

    public int updReviewWebtoon(ReviewEntity entity) {
        entity.setIuser(userUtils.getLoginUserPk());
        return reviewMapper.updReviewWebtoon(entity);
    }
}
