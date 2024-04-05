package com.koreait.whattodo.webtoon;

import com.google.gson.Gson;
import com.koreait.whattodo.Const;
import com.koreait.whattodo.UserUtils;
import com.koreait.whattodo.Utils;
import com.koreait.whattodo.board.fav.FavWebtoonService;
import com.koreait.whattodo.model.WebtoonEntity;
import com.koreait.whattodo.model.WebtoonGenreEntity;
import com.koreait.whattodo.model.WebtoonRecommandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/board", produces = "application/text; charset=UTF-8") // js 한글깨짐 방지
public class WebtoonController {
    @Autowired
    private WebtoonService webtoonService;
    @Autowired
    private FavWebtoonService favWebtoonService;

    private Utils utils;

    @GetMapping("/webtoon")
    public void webtoon(Model model, String nm) {
        List<WebtoonRecommandEntity> webtoonRecommandEntityList = webtoonService.listRecommandWebtoon();
        if(webtoonRecommandEntityList.size()==0) { // 웹툰 리스트 없으면 크롤링해주고 값 넣어줌
            utils.insertWebtoon(webtoonService);    // 네이버 웹툰 전체 크롤링
            webtoonRecommandEntityList = webtoonService.listRecommandWebtoon(); // 추천웹툰 리스트 전체
        }

        model.addAttribute("webtoonRecommandList", webtoonRecommandEntityList);
        model.addAttribute("webtoonListRandom", webtoonService.listWebtoonRandom());
        model.addAttribute("webtoonRecommandListRandom", webtoonService.listRecommandWebtoonRandom());
        model.addAttribute("webtoonGenreListRandom", webtoonService.listGenreBtnRandom());
    }

    @GetMapping("/webtooncrawling")
    public String webtoonCrawling(Model model) {
        utils.insertWebtoon(webtoonService);

        return "redirect:/board/webtoon";
    }

    @GetMapping("/webtoonGenreRandom")
    @ResponseBody
    public String webtoonGenreRandom() {
        List<WebtoonGenreEntity> list = webtoonService.listGenreAll();
        Gson gson = new Gson();
        String webtoonGenreListJson = gson.toJson(list);
        return webtoonGenreListJson;
    }

}
