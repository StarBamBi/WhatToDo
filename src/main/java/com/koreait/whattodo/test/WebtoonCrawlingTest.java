package com.koreait.whattodo.test;

import com.koreait.whattodo.model.WebtoonEntity;
import com.koreait.whattodo.model.WebtoonGenreEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebtoonCrawlingTest {
    public static void main(String[] args) throws IOException {

        // 크롤링해서 가져오는 과정
        String naverWebtoonURL = "http://www.kyobobook.co.kr/bestSellerNew/bestseller.laf?orderClick=d79https://some.co.kr/media/home";

        // 액세스 처리
        try {
            Document document = Jsoup.connect(naverWebtoonURL).userAgent("Chrome/5.0").get(); // 403 error 처리(권한 부여)

            Elements toonNm = document.select("ul.list_type01").select("div.title");
            List<String> toonNmList = new ArrayList<>();
            List<String> toonImgList = new ArrayList<>();
            List<String> toonWriterList = new ArrayList<>();
            List<String> toonLinkList = new ArrayList<>();

            for(Element element: toonNm) {    // 웹툰 이름
                String name =element.text();
                toonNmList.add(name);
                System.out.println(name);
            }


            for(int i=0; i<toonImgList.size();i++) {
                List<WebtoonGenreEntity> list = new ArrayList<>();
                WebtoonGenreEntity entity = new WebtoonGenreEntity();
                entity.setNm(toonNmList.get(i));
                entity.setImg(toonImgList.get(i));
                entity.setWriter(toonWriterList.get(i));
                entity.setLink(toonLinkList.get(i));
                list.add(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}
    }
