package com.koreait.whattodo.game.cmt;

import com.koreait.whattodo.model.GameCmtEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameCmtController {

    @Autowired
    private GameCmtService service;

    @PostMapping
    public Map<String, Integer> insGameCmt(@RequestBody GameCmtEntity entity) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.insGameCmt(entity));
        return result;
    }
    @GetMapping("/{gameNm}")
    public List<GameCmtEntity> selGameCmtList(@PathVariable String gameNm, Model model) {
        model.addAttribute("data", service.selGameCmtList(gameNm));
        return service.selGameCmtList(gameNm);
    }

    @DeleteMapping("/{icmt}")
    public Map<String, Integer> delGameCmt(@PathVariable int icmt) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.delGameCmt(icmt));
        return result;
    }

    @PutMapping
    public Map<String, Integer> updBoardCmt(@RequestBody GameCmtEntity entity) {
        Map<String, Integer> result = new HashMap<>();
        result.put("result", service.updGameCmt(entity));
        return result;
    }
}
