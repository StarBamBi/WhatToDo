package com.koreait.whattodo.user.mypage;

import com.koreait.whattodo.model.user.UserVo;
import com.koreait.whattodo.model.user.mypage.ChaUpwEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMypageMapper {

    UserVo selUpw(ChaUpwEntity entity);

    void updUpw(ChaUpwEntity entity);
}
