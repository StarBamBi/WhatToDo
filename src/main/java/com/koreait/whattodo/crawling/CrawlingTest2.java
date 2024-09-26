package com.koreait.whattodo.crawling;

import com.koreait.whattodo.model.PlatformRankEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlingTest2 {
    public static void main(String[] args) throws IOException {
        // 크롤링해서 가져오는 과정

        final String mobileURL = "https://trees.gamemeca.com/gamerank/#1521881342483-b44f2106-9b8d";

        // 액세스 처리
        // Connection con = (Connection) Jsoup.connect(ratingURL).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();

        try {
            Document document = Jsoup.connect(mobileURL).get(); // 403 error 처리(권한 부여)
            Elements rankNum = document.select("td.column-1");    // 순위 번호(1,2,3...) 가져오기
            Elements gameNm = document.select("td.column-2"); // 게임 이름
            Elements company = document.select("td.column-3"); // 회사 명
            List rankNumList = new ArrayList<>();   // 순위 번호 리스트
            List gameNmList = new ArrayList<>();    // 게임 리스트
            List companyList = new ArrayList<>();   // 회사 리스트



//            Elements ratingGameNm = document.select("div.wiki-heading-content:last-child").select("td:nth-child(2)"); // PC게임 전체 이름
//            Elements ratingGameNum = document.select("div.wiki-heading-content:last-child").select("td:first-child");   // PC게임 순위
//            Elements ratingGameRating = document.select("div.wiki-heading-content:last-child").select("td:nth-child(3)");    // PC게임 평점
//            List rateNmList = new ArrayList();
//            List rateNumList = new ArrayList();
//            List rateRatingList = new ArrayList();


//            rateNmList.remove(0);   // 앞에 하나(텍스트) 삭제
//            System.out.println(gameNmList);

            for(Element element: rankNum) {    // 모바일 게임 순위 크롤링
                String mRankNum = element.text();
                rankNumList.add(mRankNum);
            }

            for(Element element: gameNm) {    // 모바일 게임 이름 크롤링
                String mGameNm = element.text();
                gameNmList.add(mGameNm);
            }

            // 앞에 두개(텍스트) 삭제
//            rateNumList.remove(0);
//            rateNumList.remove(0);

            for(Element element: company) {    // 모바일 게임 회사이름 크롤링
                String mCompany = element.text();
                companyList.add(mCompany);
            }
//            rateRatingList.remove(0);   // 앞에 하나(텍스트) 삭제


            /* for(Element element : rankNm) {
                String name = element.text();
                String nmLink = element.getElementsByAttribute("href").attr("href");
                rankNmList.add(name);
                System.out.println(name);
                System.out.println("이미지 태그 가져오기 : " + nmLink);
            } */


            //모바일 순위
            List<PlatformRankEntity> mList = new ArrayList<>();
            for(int i=0; i< gameNmList.size(); i++) {
                PlatformRankEntity entity = new PlatformRankEntity();
                entity.setRankNum((String)rankNumList.get(i));
                entity.setGameNm((String)gameNmList.get(i));
                entity.setCompany((String)companyList.get(i));
                mList.add(entity);
            }
            System.out.println(mList);

            //PC온라인 순위
//            List<MobileRankEntity> pList = new ArrayList<>();
//            for(int i=40;i<80;i++) {
//                entity.setRankNum((String)rankNumList.get(i));
//                entity.setGameNm((String)gameNmList.get(i));
//                entity.setCompany((String)companyList.get(i));
//                pList.add(entity);
//                System.out.println(pList);
//            }

            //스팀 순위
//            List<MobileRankEntity> sList = new ArrayList<>();
//            for(int i=80;i<120;i++) {
//                entity.setRankNum((String)rankNumList.get(i));
//                entity.setGameNm((String)gameNmList.get(i));
//                entity.setCompany((String)companyList.get(i));
//                sList.add(entity);
//                System.out.println(sList);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}