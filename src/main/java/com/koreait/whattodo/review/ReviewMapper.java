package com.koreait.whattodo.review;

import com.koreait.whattodo.model.ReviewEntity;
import com.koreait.whattodo.model.user.UserPagingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    int insReviewWebtoon(ReviewEntity entity);
    List<ReviewEntity> selReviewWebtoon(ReviewEntity entity);
    List<ReviewEntity> selReviewWebtoonMy(ReviewEntity entity);
    List<ReviewEntity> selReviewAllMy();

    List<ReviewEntity> selReviewAllMyPaging(UserPagingDTO dto);
    List<ReviewEntity> selReviewGameMyPaging(UserPagingDTO dto);
    List<ReviewEntity> selReviewWebtoonMyPaging(UserPagingDTO dto);
    int delReviewWebtoon(ReviewEntity entity);
    int updReviewWebtoon(ReviewEntity entity);
}
