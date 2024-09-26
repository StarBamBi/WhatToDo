package com.koreait.whattodo.board.fav;

import com.koreait.whattodo.model.FavWebtoonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/webtoon/fav")
public class FavWebtoonController {
    @Autowired
    private FavWebtoonService service;

    @PostMapping
    public Map<String, Integer> insBoardFav(@RequestBody FavWebtoonEntity entity) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.insWebtoonFav(entity));
        return result;
    }

    @GetMapping("/{nm}")
    public Map<String, Integer> isFav(@PathVariable String nm) {
        FavWebtoonEntity dbEntity = service.selWebtoondFav(nm);
        int favcount = service.selWebtoonFavCnt(nm);
        Map<String, Integer> result = new HashMap<>();
        result.put("result", dbEntity == null ? 0 : 1);
        result.put("favcount", favcount);
        return result;
    }

    @DeleteMapping("/{nm}")
    public Map<String, Integer> delBoardFav(@PathVariable String nm) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.delWebtoonFav(nm));
        int favcount = service.selWebtoonFavCnt(nm);
        result.put("favcount", favcount);
        return result;
    }
}
