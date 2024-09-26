package com.koreait.whattodo.webtoon;

import com.koreait.whattodo.model.WebtoonEntity;
import com.koreait.whattodo.model.WebtoonGenreEntity;
import com.koreait.whattodo.model.WebtoonRecommandEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebtoonService {
    @Autowired
    private WebtoonMapper mapper;

    public void insertWebtoon(String url) {
        // 액세스 처리
        try {
            Document document = Jsoup.connect(url).userAgent("Chrome/5.0").get(); // 403 error 처리(권한 부여)

            // 월~일 웹툰 정보 크롤링
            Elements toonNm = document.select("ul.img_list").select("dt > a"); // 웹툰 이름(title), 링크(href)
            Elements toonWriter = document.select("ul.img_list").select("dd.desc > a");   // 작가
            Elements toonRating = document.select("ul.img_list").select("dd > div.rating_type").select("strong");    // 별점
            Elements toonImg = document.select("ul.img_list").select("div.thumb > a > img");    // 이미지
            Elements toonWeekend = document.select("div.view_type").select("h3.sub_tit");   // 요일
            List toonNmList = new ArrayList();
            List toonWriterList = new ArrayList();
            List toonRatingList = new ArrayList();
            List toonImgList = new ArrayList();
            List toonLinkList = new ArrayList();
            String weekend = null;

            for(Element element: toonNm) {    // 웹툰 이름
                String name = element.getElementsByAttribute("title").attr("title");
                toonNmList.add(name);
            }
            for(Element element: toonWriter) {    // 작가이름
                String name = element.text();
                toonWriterList.add(name);
            }
            for(Element element: toonRating) {    // 별점
                String name = element.text();
                toonRatingList.add(name);
            }
            for(Element element: toonImg) {    // 이미지 링크
                String name = element.getElementsByAttribute("src").attr("src");
                toonImgList.add(name);
            }
            for(Element element: toonWeekend) {    // 요일
                String name = element.text();
                String[] nameArr = name.split("\\s");   // 공백(정규식 : \s)으로 구분
                String naming = nameArr[0] + "일"; // nameArr에 첫번째에는 월요, 화요 이런 식으로 담기는데 첫번째만 빼주고 '일'을 덧붙임
                weekend = naming;
            }
            for(Element element: toonNm) {    // 링크
                String link = element.getElementsByAttribute("href").attr("href");
                String fullLink = "https://comic.naver.com" + link;
                toonLinkList.add(fullLink);
            }

            for(int i=0;i<toonNmList.size();i++) {
                List<WebtoonEntity> list = new ArrayList<>();
                WebtoonEntity entity = new WebtoonEntity();
                entity.setNm((String)toonNmList.get(i));
                entity.setWriter((String)toonWriterList.get(i));
                entity.setRating((String)toonRatingList.get(i));
                entity.setImg((String)toonImgList.get(i));
                entity.setWeekend(weekend);
                entity.setLink((String)toonLinkList.get(i));
                list.add(entity);
                mapper.insertWebtoon(list);
            }

            // 요일 추천 웹툰 크롤링
            Elements toonRecommandNm = document.select("div.webtoon_spot").select("li > dl > dt > a > strong"); // 웹툰 이름
            Elements toonRecommandWriter = document.select("div.webtoon_spot").select("dd.desc > p"); // 작가
            Elements toonRecommandRating = document.select("div.webtoon_spot").select("div.rating_type2 > strong"); // 평점
            Elements toonRecommandLink = document.select("div.webtoon_spot").select("div.thumb > a"); // 링크
            Elements toonRecommandImg = document.select("div.webtoon_spot").select("div.thumb > a > img"); // 이미지
            Elements toonRecommandWeekend = document.select("div.webtoon_spot").select("h3.sub_tit"); // 요일
            List toonRecommandNmList = new ArrayList();
            List toonRecommandWriterList = new ArrayList();
            List toonRecommandLinkList = new ArrayList();
            List toonRecommandRatingList = new ArrayList();
            List toonRecommandImgList = new ArrayList();
            String toonRecommandWhatWeekend = null;


            for(Element element: toonRecommandNm) {    // 이름
                String nm = element.text();
                toonRecommandNmList.add(nm);
            }
            for(Element element: toonRecommandWriter) {    // 작가
                String name = element.text();
                toonRecommandWriterList.add(name);
            }
            for(Element element: toonRecommandLink) {    // 평점
                String name = element.getElementsByAttribute("href").attr("href");
                String fullLink = "https://comic.naver.com" + name;
                toonRecommandLinkList.add(fullLink);
            }
            for(Element element: toonRecommandRating) {    // 평점
                String name = element.text();
                toonRecommandRatingList.add(name);
            }
            for(Element element: toonRecommandWeekend) {    // 요일
                String name = element.text();
                String[] nameArr = name.split("\\s");   // 공백(정규식 : \s)으로 구분
                String naming = nameArr[0] + "일"; // nameArr에 첫번째에는 월요, 추천, 웹툰 이런 식으로 담기는데 첫번째만 빼주고 '일'을 덧붙임
                toonRecommandWhatWeekend = naming;
            }
            for(Element element: toonRecommandImg) {    // 이미지
                String link = element.getElementsByAttribute("src").attr("src");
                toonRecommandImgList.add(link);
            }

            for(int i=0;i<toonRecommandNmList.size();i++) {
                List<WebtoonRecommandEntity> list = new ArrayList<>();
                WebtoonRecommandEntity entity = new WebtoonRecommandEntity();
                entity.setNm((String)toonRecommandNmList.get(i));
                entity.setWriter((String)toonRecommandWriterList.get(i));
                entity.setRating((String)toonRecommandRatingList.get(i));
                entity.setImg((String)toonRecommandImgList.get(i));
                entity.setWeekend(toonRecommandWhatWeekend);
                entity.setLink((String)toonRecommandLinkList.get(i));
                list.add(entity);
                mapper.insertRecommandWebtoon(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void insertMainWebtoon(String url) {
        // 액세스 처리
        try {
            Document document = Jsoup.connect(url).userAgent("Chrome/5.0").get(); // 403 error 처리(권한 부여)

            Elements toonNm = document.select("ul#genreRecommand").select("div.genreRecomInfo2").select("h6 > a").select("span");
            Elements toonImg = document.select("ul#genreRecommand").select("div.genreRecomImg2").select("a").select("img");
            Elements toonWriter = document.select("ul#genreRecommand").select("div.genreRecomInfo2").select("span.user").select("a");
            Elements toonLink = document.select("ul#genreRecommand").select("div.genreRecomImg2 > a");
            List<String> toonNmList = new ArrayList<>();
            List<String> toonImgList = new ArrayList<>();
            List<String> toonWriterList = new ArrayList<>();
            List<String> toonLinkList = new ArrayList<>();

            for(Element element: toonImg) {    // 웹툰 이미지
                String name =element.getElementsByAttribute("src").attr("src");
                toonImgList.add(name);
            }
            for(Element element: toonNm) {    // 웹툰 이름
                String name =element.text();
                toonNmList.add(name);
            }
            for(Element element: toonWriter) {    // 웹툰 작가
                String name =element.text();
                toonWriterList.add(name);
            }
            for(Element element: toonLink) {    // 웹툰 링크
                String name =element.getElementsByAttribute("href").attr("href");
                String fullLink = "https://comic.naver.com" + name;
                toonLinkList.add(fullLink);
            }
            for(int i=0; i<toonNmList.size();i++) {
                List<WebtoonRecommandEntity> webtoonList = new ArrayList<>();
                WebtoonRecommandEntity entity = new WebtoonRecommandEntity();
                entity.setImg(toonImgList.get(i));
                entity.setWriter(toonWriterList.get(i));
                entity.setNm(toonNmList.get(i));
                entity.setLink(toonLinkList.get(i));
                webtoonList.add(entity);
                mapper.insertRecommandWebtoon(webtoonList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void insertGenreWebtoon(String url) {
        // 액세스 처리
        try {
            Document document = Jsoup.connect(url).userAgent("Chrome/5.0").get(); // 403 error 처리(권한 부여)

            Elements toonNm = document.select("div.thumb").select("a");
            Elements toonImg = document.select("div.thumb").select("a").select("img");  // 이미지, 완결 구분
            Elements toonWriter = document.select("ul.img_list").select("dd.desc").select("a");
            Elements toonLink = document.select("div.thumb").select("a");
            Elements genre = document.select("h3.sub_tit");
            List<String> toonNmList = new ArrayList<>();
            List<String> toonImgList = new ArrayList<>();
            List<String> toonWriterList = new ArrayList<>();
            List<String> toonLinkList = new ArrayList<>();

            for(Element element: toonNm) {    // 웹툰 이름
                String name =element.getElementsByAttribute("title").attr("title");
                toonNmList.add(name);
            }
            for(Element element: toonImg) {    // 웹툰 이미지
                String name =element.getElementsByAttribute("src").attr("src");
                if(!name.contains("https://ssl.pstatic.net/static/comic/images/migration/webtoon/ico_finish.gif")) { // jpg 있는 것만 빼내오기(아니면 완결 이미지도 포함됨)
                    toonImgList.add(name);
                }
            }
            for(Element element: toonWriter) {    // 웹툰 작가
                String name =element.text();
                toonWriterList.add(name);
            }
            for(Element element: toonLink) {    // 웹툰 링크
                String name =element.getElementsByAttribute("href").attr("href");
                String fullLink = "https://comic.naver.com" + name;
                toonLinkList.add(fullLink);
            }

            for(int i=0; i<toonNmList.size();i++) {
                List<WebtoonGenreEntity> list = new ArrayList<>();
                WebtoonGenreEntity entity = new WebtoonGenreEntity();
                entity.setNm(toonNmList.get(i));
                entity.setImg(toonImgList.get(i));
                entity.setWriter(toonWriterList.get(i));
                entity.setLink(toonLinkList.get(i));
                entity.setGenre(genre.text().substring(0,3));   // 0번째부터 3번째 전까지(2번째 인덱스까지) 자르기
                list.add(entity);
                mapper.insertGenredWebtoon(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void insertGenreStateWebtoon(String url) {
        // 액세스 처리
        try {
            Document document = Jsoup.connect(url).userAgent("Chrome/5.0").get(); // 403 error 처리(권한 부여)

            Elements toonNm = document.select("div.thumb").select("a");
            Elements toonImg = document.select("div.thumb").select("a").select("img");  // 이미지, 완결 구분
            Elements toonWriter = document.select("ul.img_list").select("dd.desc").select("a");
            Elements toonLink = document.select("div.thumb").select("a");
            Elements genre = document.select("h3.sub_tit");
            List<String> toonNmList = new ArrayList<>();
            List<String> toonImgList = new ArrayList<>();
            List<String> toonWriterList = new ArrayList<>();
            List<String> toonLinkList = new ArrayList<>();

            for(Element element: toonNm) {    // 웹툰 이름
                String name =element.getElementsByAttribute("title").attr("title");
                toonNmList.add(name);
            }
            for(Element element: toonImg) {    // 웹툰 이미지
                String name =element.getElementsByAttribute("src").attr("src");
                if(!name.contains("https://ssl.pstatic.net/static/comic/images/migration/webtoon/ico_finish.gif")) { // jpg 있는 것만 빼내오기(아니면 완결 이미지도 포함됨)
                    toonImgList.add(name);
                }
            }
            for(Element element: toonWriter) {    // 웹툰 작가
                String name =element.text();
                toonWriterList.add(name);
            }
            for(Element element: toonLink) {    // 웹툰 링크
                String name =element.getElementsByAttribute("href").attr("href");
                String fullLink = "https://comic.naver.com" + name;
                toonLinkList.add(fullLink);
            }

            for(int i=0; i<toonNmList.size();i++) {
                List<WebtoonGenreEntity> list = new ArrayList<>();
                WebtoonGenreEntity entity = new WebtoonGenreEntity();
                entity.setNm(toonNmList.get(i));
                entity.setImg(toonImgList.get(i));
                entity.setWriter(toonWriterList.get(i));
                entity.setLink(toonLinkList.get(i));
                entity.setState(genre.text().substring(0,3));   // 0번째부터 3번째 전까지(2번째 인덱스까지) 자르기, 완결 부분만 state 부분에 들어감
                list.add(entity);
                mapper.insertGenredWebtoon(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delWebtoon() { mapper.delWebtoon(); }
    public void delRecommandWebtoon() { mapper.delRecommandWebtoon(); }
    public void delGenreWebtoon() { mapper.delGenreWebtoon(); }

    public List<WebtoonEntity> listWebtoon() { return mapper.webtoonList(); }
    public List<WebtoonEntity> listWebtoonRandom() { return mapper.webtoonListRandom(); }
    public List<WebtoonRecommandEntity> listRecommandWebtoon() { return mapper.webtoonRecommandList(); }
    public List<WebtoonRecommandEntity> listRecommandWebtoonRandom() { return mapper.webtoonRecommandListRandom(); }
    public List<WebtoonGenreEntity> listGenreAll() { return mapper.webtoonGenreListAll(); }
    public List<WebtoonGenreEntity> listGenreBtnRandom() { return mapper.webtoonGenreListBtnRandom(); }

}
