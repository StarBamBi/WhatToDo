package com.koreait.whattodo.game.like;

import com.koreait.whattodo.model.BoardLikeEntity;
import com.koreait.whattodo.model.GameLikeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/game/like")
public class GameLikeController {

    @Autowired
    private GameLikeService service;

    @PostMapping
    public Map<String, Integer> insGameLike(@RequestBody GameLikeEntity entity) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.insGameLike(entity));
        return result;
    }

    @GetMapping("/{gameNm}")
    public Map<String, Integer> isLike(@PathVariable String gameNm) {
        return service.getLikeInfo(gameNm);
    }

    @DeleteMapping("/{gameNm}")
    public Map<String, Integer> delGameLike(@PathVariable String gameNm) {
        return service.delLikeInfo(gameNm);
    }
}
