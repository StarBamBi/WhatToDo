package com.koreait.whattodo.board;

import com.koreait.whattodo.model.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    @GetMapping("/list")
    public String list(BoardEntity entity, Model model) {
        model.addAttribute("list", service.selBoardList(entity));
        return "board/list";
    }

    @GetMapping("/write")
    public void write() {}

    @PostMapping("/write")
    public String writeProc(BoardEntity entity) {
        int result = service.insBoard(entity);
        return "redirect:/board/list";
    }

    @GetMapping("/detail")
    public void detail(BoardEntity entity, Model model, HttpServletRequest req) {
        String lastIp = req.getHeader("X-FORWARDED-FOR");
        if(lastIp == null) {
            lastIp = req.getRemoteAddr();
        }
        entity.setLastip(lastIp);
        model.addAttribute("data", service.selBoard(entity));
    }
}
