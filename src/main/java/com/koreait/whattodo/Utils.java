package com.koreait.whattodo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class Utils {
    // 리스트에 크롤링 해온 결과값을 담는 메소드
    public List crawling(Elements elements, List listNm) {
        for (Element element : elements) {
            listNm.add(element.text());
        }
        return listNm;
    }
}
