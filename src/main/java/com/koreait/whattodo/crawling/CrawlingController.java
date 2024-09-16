package com.koreait.whattodo.crawling;

import com.google.gson.Gson;
import com.koreait.whattodo.Utils;
import com.koreait.whattodo.board.BoardService;
import com.koreait.whattodo.model.*;
import com.koreait.whattodo.video.VideoService;
import com.koreait.whattodo.webtoon.WebtoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/board", produces = "application/text; charset=UTF-8") // js 한글깨짐 방지
public class CrawlingController {
    @Autowired
    private BoardService service;

    @Autowired
    private CrawlingService crawlingService;

    @Autowired
    private WebtoonService webtoonService;

    @Autowired
    private VideoService videoService;

    private Utils utils;

    @GetMapping("/netflix")
    public void netflix() {
    }


    @GetMapping("/main")
    public void main(Model model, PlatformRankEntity entity) {
        String ratingUrl = "https://namu.wiki/w/%EB%A9%94%ED%83%80%ED%81%AC%EB%A6%AC%ED%8B%B1/MUST-PLAY%20%EB%AA%A9%EB%A1%9D";
        String naverWebtoonURL = "https://comic.naver.com/webtoon/weekdayList?week=mon&order=User&view=image";  // 월요일&인기순

        // 웹툰
        List<WebtoonEntity> webtoonList = webtoonService.listWebtoon();
        if(webtoonList.size()==0) { // 웹툰 리스트 없으면 크롤링해주고 값 넣어줌
            webtoonService.insertWebtoon(naverWebtoonURL);
            webtoonList = webtoonService.listWebtoon();
        }

        // 게임
        crawlingService.insertRating(ratingUrl);
        List gameList = crawlingService.platformListWithImg(entity);
        List<WebtoonRecommandEntity> webtoonRecommandEntityList = webtoonService.listRecommandWebtoon();

        // 랜덤번째 리스트를 전달해줌
        int randomGameNum = utils.randomNumOutput(gameList.size());
        int randomWebtoon = utils.randomNumOutput(webtoonRecommandEntityList.size());
        System.out.println("랜덤 [게임] 숫자 : " + randomGameNum);
        System.out.println("랜덤 [웹툰] 숫자 : " + randomWebtoon);

        model.addAttribute("gameList", gameList);
        model.addAttribute("randomGame", gameList.get(randomGameNum));
        model.addAttribute("randomWebtoon", webtoonRecommandEntityList.get(randomWebtoon));
        model.addAttribute("webtoonListRandom", webtoonService.listWebtoonRandom());
        List<VideoMovieEntity> list = videoService.listWeeklyMovie();
        for(int i=0; i< list.size();i++) {
            videoService.naverImgSearch(list.get(i).getMovieNm());
        }
        model.addAttribute("weeklyMovieList", videoService.listWeeklyMovieRandom());
    }

    @GetMapping("/book")
    public void book() {}

    @GetMapping("/rankingjson")
    @ResponseBody
    public String rankingJson(MecaRankEntity mecaRankEntity, SteamRankEntity steamRankEntity, HttpServletResponse res) throws IOException {
        String mecaUrl = "https://www.gamemeca.com/ranking.php";
        String steamUrl = "https://store.steampowered.com/stats/?l=koreana";

        crawlingService.insertMeca(mecaUrl);
        crawlingService.insertSteam(steamUrl);

        // 0부터 1까지
        Random random = new Random(); //랜덤 객체 생성(디폴트 시드값 : 현재시간)
        random.setSeed(System.currentTimeMillis()); //시드값 설정을 따로 할수도 있음
        long randomGameNum = Math.round(Math.random());
        // json ajax통신
        Gson gson = new Gson();

        String mecaListJson = gson.toJson(crawlingService.mecaRankList(mecaRankEntity));
        String steamListJson = gson.toJson(crawlingService.steamRankList(steamRankEntity));

        System.out.println("randomGameNum : " + randomGameNum);
        if(randomGameNum==0) {
            return mecaListJson;
        } else {
            return steamListJson;
        }
    }

    @GetMapping("/mecarankingjson")
    @ResponseBody
    public String mecarankingjson(MecaRankEntity entity, HttpServletResponse res) throws IOException {
        String mecaUrl = "https://www.gamemeca.com/ranking.php";

        crawlingService.insertMeca(mecaUrl);

        // json ajax통신
        Gson gson = new Gson();

        String mecaListJson = gson.toJson(crawlingService.mecaRankList(entity));

        System.out.println(mecaListJson);
        return mecaListJson;
    }


//    @GetMapping("/mecarankingjson")
//    @ResponseBody
//    public String mecarankingjson(MecaRankEntity entity, HttpServletResponse res) throws IOException {
//
//        // json ajax통신
//        Gson gson = new Gson();
//
//        String mecaListJson = gson.toJson(crawlingService.mecaRankList(entity));
//
////        System.out.println(mecaListJson);
//        return mecaListJson;
//    }

    //모바일, pc온라인, 스팀
    @GetMapping("/game")
    public void game(Model model, MecaRankEntity entity1, PlatformRankEntity entity2) {

            List gameList = crawlingService.mecaRankList(entity1);
            List platformGameList = crawlingService.platformListWithImg(entity2);
            model.addAttribute("gameList", gameList);
            model.addAttribute("pfGameList", platformGameList);
        }

    @GetMapping("/gameCrawling")
    public String gameCrawling(Model model, MecaRankEntity entity1, PlatformRankEntity entity2) {

        String platformUrl = "https://trees.gamemeca.com/gamerank/";
        String mecaUrl = "https://www.gamemeca.com/ranking.php";

        boolean canRun = true;
        // 로직 수행 가능여부 설정
        try{ DatagramSocket ds = new DatagramSocket(8090);
            // 포트점유
        }
        catch (SocketException e) { System.out.println("동일한 프로그램이 동작중입니다. 포트 : " + 8090);
            e.printStackTrace();
            canRun = false;
        }
        if(canRun){
            crawlingService.insertMeca(mecaUrl);
            crawlingService.insertPlatform(platformUrl);
            crawlingService.insertPlatformImgList(entity2);

            List gameList = crawlingService.mecaRankList(entity1);
            List platformGameList = crawlingService.platformListWithImg(entity2);
            model.addAttribute("gameList", gameList);
            model.addAttribute("pfGameList", platformGameList);
        } else {
            List gameList = crawlingService.mecaRankList(entity1);
            List platformGameList = crawlingService.platformListWithImg(entity2);
            model.addAttribute("gameList", gameList);
            model.addAttribute("pfGameList", platformGameList);
        }
        return "redirect:/board/game";
    }

    @GetMapping("/platformrankingjson")
    @ResponseBody
    public String platformRankJson(PlatformRankEntity entity, HttpServletResponse res) throws IOException {

        // json ajax통신
        Gson gson = new Gson();

        String platformListJson = gson.toJson(crawlingService.platformListWithImg(entity));
//        System.out.println(platformListJson);
        return platformListJson;
    }

}