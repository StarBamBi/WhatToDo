package com.koreait.whattodo.board;

import com.google.gson.Gson;
import com.koreait.whattodo.crawling.CrawlingService;
import com.koreait.whattodo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping(value = "/board", produces = "application/text; charset=UTF-8") // js 한글깨짐 방지
public class BoardController {

    @Autowired
    private BoardService service;



    @GetMapping("/youtube")
    public void youtube() {}

    @GetMapping("/list")
    public String list(BoardEntity entity, Model model) {
        model.addAttribute("list", service.selBoardList(entity));
        return "board/list";
    }

    @GetMapping("/write")
    public void write() {}

    @PostMapping("/write")
    public String writeProc(BoardEntity entity, RedirectAttributes reAttr) {
        int result = service.insBoard(entity);
        if(result == 0) {
            reAttr.addFlashAttribute("error", "제목 혹은 내용을 입력하세요.");
        }
        System.out.println(result);
        return "redirect:/board/list";
    }

    @GetMapping("/detail")
    public BoardVo detail(BoardEntity entity, Model model, HttpServletRequest req) {
        String lastIp = req.getHeader("X-FORWARDED-FOR");
        if(lastIp == null) {
            lastIp = req.getRemoteAddr();
        }
        entity.setLastip(lastIp);
        BoardVo vo = (BoardVo) service.selBoard(entity);
        BoardPrevNextVo pnVo = service.selPrevNext(vo);
        model.addAttribute("prevNext", pnVo);
        model.addAttribute("data", service.selBoard(entity));
        System.out.println(vo);
        return vo;
    }

//    @ResponseBody
//    @GetMapping("/detailItem")
//    public String detailItem(BoardEntity entity, @PathVariable int iboard) {
//
//        Gson gson = new Gson();
//
//        BoardEntity detail = service.selBoard(entity);
//        System.out.println(detail);
//        String detailJson = gson.toJson(detail);
//
//        System.out.println(detailJson);
//        return detailJson;
//    }

    @GetMapping("/mod")
    public String mod(BoardEntity entity, Model model) {
        model.addAttribute("data", service.selBoard(entity));
        return "board/write";
    }

    @PostMapping("/mod")
    public String modProc(BoardEntity entity) {
        int result = service.updBoard(entity);
        return "redirect:/board/detail?iboard=" + entity.getIboard();
    }

    @GetMapping("/del")
    public String delProc(BoardEntity entity) {
        int result = service.delBoard(entity);
        return "redirect:/board/list";
    }
}
