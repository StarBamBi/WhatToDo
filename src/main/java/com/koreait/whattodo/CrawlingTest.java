package com.koreait.whattodo;

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

            // 리스트에 크롤링을 넣는 작업
            Utils utils = new Utils();
            utils.crawling(rankNum, rankNumList);
            utils.crawling(rankNm, rankNmList);
            utils.crawling(company, companyList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
