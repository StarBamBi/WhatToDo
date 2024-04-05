package com.koreait.whattodo;
import com.koreait.whattodo.webtoon.WebtoonService;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Random;

public class Utils {

    // 네이버 웹툰 삭제 후 데이터베이스 삽입입(월~)
    public static void insertWebtoon(WebtoonService service) {
        service.delRecommandWebtoon();
        service.delWebtoon();
        service.delGenreWebtoon();
        service.insertWebtoon(Const.NAVER_WEBTOON_1);
        service.insertWebtoon(Const.NAVER_WEBTOON_2);
        service.insertWebtoon(Const.NAVER_WEBTOON_3);
        service.insertWebtoon(Const.NAVER_WEBTOON_4);
        service.insertWebtoon(Const.NAVER_WEBTOON_5);
        service.insertWebtoon(Const.NAVER_WEBTOON_6);
        service.insertWebtoon(Const.NAVER_WEBTOON_7);

        service.insertMainWebtoon(Const.NAVER_WEBTOON_MAIN);
        // 장르별 구분
        service.insertGenreWebtoon(Const.NAVER_WEBTOON_DAILY);
        service.insertGenreWebtoon(Const.NAVER_WEBTOON_COMIC);
        service.insertGenreWebtoon(Const.NAVER_WEBTOON_FANTASY);
        service.insertGenreWebtoon(Const.NAVER_WEBTOON_ACTION);
        service.insertGenreWebtoon(Const.NAVER_WEBTOON_DRAMA);
        service.insertGenreWebtoon(Const.NAVER_WEBTOON_PURE);
        service.insertGenreWebtoon(Const.NAVER_WEBTOON_SENSIBILITY);
        service.insertGenreWebtoon(Const.NAVER_WEBTOON_THRILL);
        service.insertGenreWebtoon(Const.NAVER_WEBTOON_HISTORICAL);
        service.insertGenreWebtoon(Const.NAVER_WEBTOON_SPORTS);
        // 완결 웹툰 (state에 들어감)
        service.insertGenreStateWebtoon(Const.NAVER_WEBTOON_FINISH);
    }

    // 0부터 사이즈만큼의 랜덤 정수값을 반환환
    public static int randomNumOutput(int size) {
        Random random = new Random(); //랜덤 객체 생성(디폴트 시드값 : 현재시간)
        return random.nextInt(size);
    }
}
