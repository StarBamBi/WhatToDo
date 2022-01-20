package com.koreait.whattodo.crawling;

import com.koreait.whattodo.model.MecaRankEntity;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlingTest {
    public static void main(String[] args) {
        // 게임메카에서 크롤링해서 가져오는 과정

        final String RankURL = "https://www.gamemeca.com/ranking.php";
        Connection con = Jsoup.connect(RankURL);

        try {
            Document document = con.get();
            Elements rankNum = document.select("tr.ranking-table-rows").select("span.rank");    // 순위 번호(1,2,3...) 가져오기
            Elements rankNm = document.select("div.game-name > a"); // 게임 이름
            Elements company = document.select("div.game-info > span.company"); // 회사 명
            List rankNumList = new ArrayList<>();   // 순위 번호 리스트
            List rankNmList = new ArrayList<>();    // 게임 리스트
            List companyList = new ArrayList<>();   // 회사 리스트

            MecaRankEntity entity = new MecaRankEntity();

            for(Element element : rankNum) {
                String num = element.text();
                rankNumList.add(num);
            }
            for(Element element : rankNm) {
                String name = element.text();
                String nmLink = element.getElementsByAttribute("href").attr("href");
                rankNmList.add(name);
                System.out.println(nmLink);
            }
            for(Element element : company) {
                String companyNm = element.text();

                companyList.add(companyNm);
            }

            List<MecaRankEntity> list = new ArrayList<>();
            for(int i=0;i<rankNmList.size();i++) {
                entity.setRankNum((String)rankNumList.get(i));
                entity.setRankNm((String)rankNmList.get(i));
                entity.setCompany((String)companyList.get(i));
                list.add(entity);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
