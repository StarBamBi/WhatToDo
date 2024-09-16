package com.koreait.whattodo.video;

import com.koreait.whattodo.model.VideoMovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/board", produces = "application/text; charset=UTF-8") // js 한글깨짐 방지
public class VideoController {
    @Autowired
    private VideoService service;

    @GetMapping("/video")
    public void video(Model model) {
        List<VideoMovieEntity> list = service.listWeeklyMovie();
        for(int i=0; i< list.size();i++) {
            service.naverImgSearch(list.get(i).getMovieNm());
        }
        model.addAttribute("weeklyMovieList", service.listWeeklyMovieRandom());
    }

    @GetMapping("/videomCrawling")
    public String videoCrawling() {
        service.delMovie();
        service.insertMovie();

        return "redirect:/board/video";
    }
}