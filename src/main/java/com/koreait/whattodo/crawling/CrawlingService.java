package com.koreait.whattodo.crawling;

import com.koreait.whattodo.model.*;
import org.apache.ibatis.annotations.Param;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class CrawlingService {
    @Autowired
    private CrawlingMapper mapper;

    // ★★★★★ 게임메카 관련 ★★★★★
    public void insertMeca(String url) {
        Document doc = null;
        List rankNumList = new ArrayList<>();   // 순위 번호 리스트
        List rankNmList = new ArrayList<>();    // 게임 리스트
        List companyList = new ArrayList<>();   // 회사 리스트
        List imgList = new ArrayList<>(); //이미지 리스트
        List linkList = new ArrayList<>(); //링크 리스트

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 크롤링 과정
        Elements rankNum = doc.select("tr.ranking-table-rows").select("span.rank");    // 순위 번호(1,2,3...) 가져오기
        Elements gameNm = doc.select("div.game-name > a"); // 게임 이름
        Elements company = doc.select("div.game-info > span.company"); // 회사 명
        Elements img = doc.select("img.game-icon"); // 이미지

        // 크롤링해서 가져온 값의 text만 뽑아서 리스트에 담음.
        for (Element element : rankNum) {
            String num = element.text();
            rankNumList.add(num);
        }
        for (Element element : gameNm) {
            String name = element.text();
            rankNmList.add(name);
        }
        for (Element element : company) {
            String companyNm = element.text();
            companyList.add(companyNm);
        }
        for (Element element : img) {
            String imgSrc = element.attributes().get("src");
            imgList.add(imgSrc);
        }
        for (Element element : gameNm) {
            String link = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=" + element.text();
            linkList.add(link);
        }

        // 반복해서 들어가지 않도록 테이블 안에 내용이 있으면 비우는 과정.
        mapper.delMecaRank();

        // 크롤링 담을 CrawlingMecaRankEntity 객체 생성.

        List<MecaRankEntity> list = new ArrayList<>();
        // for문이 한 번 돌 때마다 한 행씩 추가.
        for (int i = 0; i < rankNmList.size(); i++) {
            MecaRankEntity entity = new MecaRankEntity();
            entity.setRankNum((String) rankNumList.get(i));
            entity.setGameNm((String) rankNmList.get(i));
            entity.setCompany((String) companyList.get(i));
            entity.setImgsrc((String) imgList.get(i));
            entity.setSelLink((String) linkList.get(i));
            list.add(entity);
        }
        mapper.insertRankMecaDb(list);
    }

    public List<MecaRankEntity> mecaRankList(MecaRankEntity entity) {
        return mapper.mecaRankList(entity);
    }

    // ★★★★★ 스팀 관련 ★★★★★

    public void insertSteam(String url) {
        Document doc = null;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 크롤링 과정
        List rankNmList = new ArrayList<>();    // 게임 리스트

        Elements rankNm = doc.select("div#detailStats").select("a.gameLink");    // 순위 번호(1,2,3...) 가져오기

        // 크롤링 담을 CrawlingMecaRankEntity 객체 생성.
        SteamRankEntity entity = new SteamRankEntity();

        // 크롤링해서 가져온 값의 text만 뽑아서 리스트에 담음.
        for (Element element : rankNm) {
            String name = element.text();
            rankNmList.add(name);
        }

        // 반복해서 들어가지 않도록 테이블 안에 내용이 있으면 비우는 과정.
        mapper.delSteamRank();

        // for문이 한 번 돌 때마다 한 행씩 추가.
        for (int i = 1; i <= rankNmList.size(); i++) {
            List<SteamRankEntity> list = new ArrayList<>();
            String num = Integer.toString(i);   // 순위
            entity.setRankNm((String) rankNmList.get(i - 1));
            entity.setRankNum(num);
            list.add(entity);
            mapper.insertRankSteamDb(list);
        }
    }

    public List<SteamRankEntity> steamRankList(SteamRankEntity entity) {
        return mapper.steamRankList(entity);
    }

    // ★★★★★ 평점 관련 ★★★★★

    public void insertRating(String url) {
        Document document = null;

        try {
            document = Jsoup.connect(url).userAgent("Chrome/5.0").get(); // 403 error 처리(권한 부여)
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 크롤링 과정
        Elements ratingGameNm = document.select("div.wiki-heading-content:last-child").select("td:nth-child(2)"); // PC게임 전체 이름
        Elements ratingGameNum = document.select("div.wiki-heading-content:last-child").select("td:first-child");   // PC게임 순위
        Elements ratingGameRating = document.select("div.wiki-heading-content:last-child").select("td:nth-child(3)");    // PC게임 평점
        List rateNmList = new ArrayList();
        List rateNumList = new ArrayList();
        List rateRatingList = new ArrayList(); // 회사 명

        for (Element element : ratingGameNm) {    // PC게임 이름 크롤링
            String gameNm = element.text();
            rateNmList.add(gameNm);
        }
        rateNmList.remove(0);   // 앞에 하나(텍스트) 삭제

        for (Element element : ratingGameNum) {    // PC게임 순위 크롤링
            String gameNum = element.text();
            rateNumList.add(gameNum);
        }
        // 앞에 두개(텍스트) 삭제
        rateNumList.remove(0);
        rateNumList.remove(0);

        for (Element element : ratingGameRating) {    // PC게임 이름 크롤링
            String gameRating = element.text();
            rateRatingList.add(gameRating);
        }
        rateRatingList.remove(0);   // 앞에 하나(텍스트) 삭제

        // 반복해서 들어가지 않도록 테이블 안에 내용이 있으면 비우는 과정.
        mapper.delRating();

        // 크롤링 담을 객체 생성.
        RatingEntity entity = new RatingEntity();

        // for문이 한 번 돌 때마다 한 행씩 추가.
        for (int i = 0; i < rateNumList.size(); i++) {
            List<RatingEntity> list = new ArrayList<>();
            entity.setGameNm((String) rateNmList.get(i));
            entity.setGameRank((String) rateNumList.get(i));
            entity.setGameRating((String) rateRatingList.get(i));
            list.add(entity);
            mapper.insertRatingDb(list);
        }
    }

    public List<RatingEntity> ratingList(RatingEntity entity) {
        return mapper.ratingList(entity);
    }

    //모바일, pc온라인, 스팀
    public void insertPlatform(String url) {
        Document doc = null;
        List rankNumList = new ArrayList<>();   // 순위 번호 리스트
        List gameNmList = new ArrayList<>();    // 게임 리스트
        List companyList = new ArrayList<>();   // 회사 리스트
        List genreList = new ArrayList<>(); //장르 리스트
        List linkList = new ArrayList<>(); // 링크 리스트
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 크롤링 과정
        Elements rankNum = doc.select("td.column-1");    // 순위 번호(1,2,3...) 가져오기
        Elements gameNm = doc.select("td.column-2"); // 게임 이름
        Elements company = doc.select("td.column-3"); // 회사 명
        Elements genre = doc.select("td.column-4"); // 장르 명

        // 크롤링해서 가져온 값의 text만 뽑아서 리스트에 담음.
        for (Element element : rankNum) {
            String num = element.text();
            System.out.println(num);
            String num1 = num.split(" ")[0];
            rankNumList.add(num1);
        }
        for (Element element : gameNm) {
            String name = element.text();
            gameNmList.add(name);
        }
        for (Element element : company) {
            String companyNm = element.text();
            companyList.add(companyNm);
        }
        for (Element element : genre) {
            String genreNm = element.text();
            genreList.add(genreNm);
        }
        for (Element element : gameNm) {
            String linkNm = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=" + element.text();
            linkList.add(linkNm);
        }
        // 반복해서 들어가지 않도록 테이블 안에 내용이 있으면 비우는 과정.
        mapper.delPlatformRank();


        List<PlatformRankEntity> list = new ArrayList<>();
        // for문이 한 번 돌 때마다 한 행씩 추가.
        //0~39까지 모바일 , 40~79까지 pc온라인, 80~119까지 스팀
        for (int i = 0; i < gameNmList.size(); i++) {
            PlatformRankEntity entity = new PlatformRankEntity();
            entity.setRankNum((String) rankNumList.get(i));
            entity.setGameNm((String) gameNmList.get(i));
            entity.setCompany((String) companyList.get(i));
            entity.setGenre((String) genreList.get(i));
            entity.setSelLink((String) linkList.get(i));
            list.add(entity);
        }
        mapper.insertPlatformRankDb(list);
    }

    public List<PlatformRankEntity> platformList(PlatformRankEntity entity) {
        List platformList = mapper.platformRankList(entity);
        List<PlatformRankEntity> platList = new ArrayList<>();
        for (int i = 0; i < 120; i++) {
            platList.add((PlatformRankEntity) platformList.get(i));
        }
        return platList;
    }

    public void insertPlatformImgList(PlatformRankEntity entity) {


        mapper.delPlatformImg();
        List platformList = mapper.platformRankList(entity);

        List<String> gameNmList = new ArrayList<>();
        for (int i = 0; i < 120; i++) {
            gameNmList.add(((platformList.get(i).toString().split(","))[2]).split("=")[1]);
//            System.out.println(((platformList.get(i).toString().split(","))[2]).split("=")[1]);
        }

        List<PlatformImgEntity> list = new ArrayList<>();
//        List<String > nameList = new ArrayList<>();
        for (int i = 0; i < 120; i++) {
            Document doc = null;
//            List imgList = new ArrayList<>();
            try {
                doc = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=" + gameNmList.get(i)).get();

            } catch (IOException e) {
                e.printStackTrace();
            }

            // 크롤링 과정
            Element imgs = doc.select("div.detail_info > a > img").first();    // 순위 번호(1,2,3...) 가져오기
//            Elements name = doc.select("div.title_area.type_keep._title_area > h2 > a > strong");//


//
            // 크롤링해서 가져온 값의 src만 뽑아서 리스트에 담음.
            if(imgs != null) {
                    PlatformImgEntity platformImgEntity = new PlatformImgEntity();
                    platformImgEntity.setGameNm(gameNmList.get(i));
                    String imgSrc = imgs.attr("src");
                    System.out.println(i + "번째 img src : " + imgSrc);
                    platformImgEntity.setImgsrc(imgSrc);
                    list.add(platformImgEntity);
            }
        else {
                PlatformImgEntity platformImgEntity = new PlatformImgEntity();
                platformImgEntity.setGameNm(gameNmList.get(i));
                String imgSrc = null;
                System.out.println(i + "번째 img src : " + imgSrc);
                platformImgEntity.setImgsrc(imgSrc);
                list.add(platformImgEntity);
            }
//            for (Element element : name) {
//                String gameNm = element.text();
//                System.out.println(gameNm);
//                nameList.add(gameNm);
//            }
        }
//        System.out.println("list size : " + list.size());
//        System.out.println(list);
        List<PlatformImgEntity> imgList = new ArrayList<>();
        for(int i=0;i<120;i++) {
            PlatformImgEntity imgEntity = new PlatformImgEntity();
            imgEntity.setImgsrc(list.get(i).getImgsrc());
            imgEntity.setGameNm(list.get(i).getGameNm());
            mapper.insPlatformImg(imgEntity);
        }

//        System.out.println(gameNmList);

//        List<PlatformImgEntity> imgSrcList = new ArrayList<>();
//
//            for(int i=0;i<120;i++) {
//                PlatformImgEntity imgEntity = new PlatformImgEntity();
//                imgEntity.setImgsrc((String) list.get(i));
//                imgEntity.setGameNm((String) gameNmList.get(i));
//                imgSrcList.add(imgEntity);
//            }
//
//        System.out.println(imgSrcList);

    }

    public List platformListWithImg(PlatformRankEntity entity) {
        return mapper.platformRankListWithImg(entity);
    }
}



