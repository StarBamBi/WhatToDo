package com.koreait.whattodo.user.mypage;

import com.koreait.whattodo.UserUtils;
import com.koreait.whattodo.board.fav.FavWebtoonService;
import com.koreait.whattodo.game.cmt.GameCmtService;
import com.koreait.whattodo.model.FavWebtoonEntity;
import com.koreait.whattodo.model.GameCmtEntity;
import com.koreait.whattodo.model.ReviewEntity;
import com.koreait.whattodo.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/webtoon/mypage")
public class UserMypageDataController {
    @Autowired
    UserUtils userUtils = new UserUtils();
    @Autowired
    ReviewService reviewService = new ReviewService();
    @Autowired
    FavWebtoonService favWebtoonService = new FavWebtoonService();
    @Autowired
    GameCmtService gameCmtService = new GameCmtService();

    @GetMapping("/cmt-game-mylist")
    public List<GameCmtEntity> gameCmtMyList() {
        return gameCmtService.selGameCmtListMy();
    }

    @GetMapping("/cmt-webtoon-mylist")
    public List<ReviewEntity> webtoonReviewMyList() {
        return reviewService.selReviewWebtoonMy();
    }

    @GetMapping("/cmt-all-mylist")
    public List<ReviewEntity> reviewAllMyList() { return reviewService.selReviewAllMy(); }
}
