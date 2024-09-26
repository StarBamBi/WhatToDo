package com.koreait.whattodo.review;

import com.koreait.whattodo.model.ReviewEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board/review")
public class ReviewController {
    @Autowired private ReviewService reviewService;

    @PostMapping
    public Map<String, String> insReviewWebtoon(@RequestBody ReviewEntity entity) {
        Map<String, String> result = new HashMap<>();
        ReviewEntity reviewEntity = reviewService.insReviewWebtoon(entity);
        result.put("result", Integer.toString(reviewEntity.getRnum()));
        result.put("resultNickname", reviewEntity.getNickname());
        return result;
    }

    @GetMapping("/{nm}")
    public List<ReviewEntity> selReviewWebtoonList(@PathVariable String nm) {
        return reviewService.selReviewWebtoon(nm);
    }

    @PutMapping
    public Map<String, Integer> updBoardCmt(@RequestBody ReviewEntity entity) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", reviewService.updReviewWebtoon(entity));
        return result;
    }

    @DeleteMapping("/{rnum}")
    public Map<String, Integer> delBoardCmt(@PathVariable int rnum) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", reviewService.delReviewWebtoon(rnum));
        System.out.println("delResult : " + result);
        return result;
    }

}
