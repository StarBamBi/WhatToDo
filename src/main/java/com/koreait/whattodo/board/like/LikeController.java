package com.koreait.whattodo.board.like;

import com.koreait.whattodo.model.BoardLikeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/board/like")
public class LikeController {

    @Autowired
    private LikeService service;

    @PostMapping
    public Map<String, Integer> insBoardLike(@RequestBody BoardLikeEntity entity) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.insBoardLike(entity));
        return result;
    }

    @GetMapping("/{iboard}")
    public Map<String, Integer> isLike(@PathVariable int iboard) {
        return service.getLikeInfo(iboard);
    }

    @DeleteMapping("/{iboard}")
    public Map<String, Integer> delBoardLike(@PathVariable int iboard) {
        return service.delLikeInfo(iboard);
    }
}
