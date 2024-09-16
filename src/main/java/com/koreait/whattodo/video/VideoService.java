package com.koreait.whattodo.video;

import com.koreait.whattodo.Const;
import com.koreait.whattodo.model.VideoMovieEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class VideoService {
    @Autowired
    private VideoMapper mapper;

    // 상수 설정
    // 요청(Request) 요청 변수


    // 일자 포맷
    private final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyyMMdd");

    // Map -> QueryString
    public String makeQueryString(Map<String, String> paramMap) {
        final StringBuilder sb = new StringBuilder();

        paramMap.entrySet().forEach(( entry )->{
            if( sb.length() > 0 ) {
                sb.append('&');
            }
            sb.append(entry.getKey()).append('=').append(entry.getValue());
        });

        return sb.toString();
    }

    // API요청
    public void requestAPIDailyMoive(VideoMapper mapper, String movieUrl) {
        // 변수설정
        // 하루전 날짜
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -5);

        // 변수 설정
        //   - 요청(Request) 인터페이스 Map
        //   - 어제자 다양성 한국영화 10개 조회
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key"          , Const.AUTH_KEY);                        // 발급받은 인증키
        paramMap.put("targetDt"     , DATE_FMT.format(cal.getTime()));  // 조회하고자 하는 날짜
        paramMap.put("itemPerPage"  , "10");                            // 결과 ROW 의 개수( 최대 10개 )
        /* paramMap.put("multiMovieYn" , "Y") // Y:다양성 영화, N:상업영화, Default:전체 ;
        paramMap.put("repNationCd"  , "K"); // K:한국영화, F:외국영화, Default:전체 */
        System.out.println(DATE_FMT.format(cal.getTime()));


        try {
            // Request URL 연결 객체 생성
            URL requestURL = new URL(movieUrl+"?"+makeQueryString(paramMap));
            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();

            // GET 방식으로 요청
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            // 응답(Response) 구조 작성
            // Stream -> JSONObject
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String readline = null;
            StringBuffer response = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                response.append(readline);
            }
            System.out.println("response : " + response);
            // JSON 객체로  변환
            JSONObject responseBody = new JSONObject(response.toString());

            // 데이터 추출
            JSONObject boxOfficeResult = responseBody.getJSONObject("boxOfficeResult");

            // 박스오피스 주제 출력
            String boxofficeType = boxOfficeResult.getString("boxofficeType");
            System.out.println(boxofficeType);

            // 박스오피스 목록 출력
            JSONArray dailyBoxOfficeList = boxOfficeResult.getJSONArray("dailyBoxOfficeList");
            System.out.println(dailyBoxOfficeList.getClass().getName());    // 타입검사
            Iterator<Object> iter = dailyBoxOfficeList.iterator();
            while(iter.hasNext()) {
                List<VideoMovieEntity> boxofiiceList = new ArrayList<>();
                VideoMovieEntity entity = new VideoMovieEntity();
                JSONObject boxOffice = (JSONObject) iter.next();
                entity.setBoxofficeType(boxofficeType);
                entity.setMovieNm((String)boxOffice.get("movieNm"));
                entity.setShowRange(DATE_FMT.format(cal.getTime()));
                entity.setRank((String)boxOffice.get("rank"));
                entity.setMovieCd((String)boxOffice.get("movieCd"));
                System.out.printf("%s위 %s\n", boxOffice.get("rnum"), boxOffice.get("movieCd"));
                boxofiiceList.add(entity);
                mapper.insertBoxOffice(boxofiiceList);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void requestAPIWeekMoive(VideoMapper mapper, String movieUrl) {
        // 변수설정
        // 하루전 날짜
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -10);

        // 변수 설정
        //   - 요청(Request) 인터페이스 Map
        //   - 어제자 다양성 한국영화 10개 조회
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("key"          , Const.AUTH_KEY);                        // 발급받은 인증키
        paramMap.put("targetDt"     , DATE_FMT.format(cal.getTime()));  // 조회하고자 하는 날짜
        paramMap.put("itemPerPage"  , "10");                            // 결과 ROW 의 개수( 최대 10개 )
        paramMap.put("weekGb", "0");
        /* paramMap.put("multiMovieYn" , "Y") // Y:다양성 영화, N:상업영화, Default:전체 ;
        paramMap.put("repNationCd"  , "K"); // K:한국영화, F:외국영화, Default:전체 */
        System.out.println(DATE_FMT.format(cal.getTime()));


        try {
            // Request URL 연결 객체 생성
            URL requestURL = new URL(movieUrl+"?"+makeQueryString(paramMap));
            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();

            // GET 방식으로 요청
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            // 응답(Response) 구조 작성
            // Stream -> JSONObject
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String readline = null;
            StringBuffer response = new StringBuffer();
            while ((readline = br.readLine()) != null) {
                response.append(readline);
            }
            System.out.println("response : " + response);
            // JSON 객체로  변환
            JSONObject responseBody = new JSONObject(response.toString());

            // 데이터 추출
            JSONObject boxOfficeResult = responseBody.getJSONObject("boxOfficeResult");

            // 박스오피스 주제 출력
            String boxofficeType = boxOfficeResult.getString("boxofficeType");
            System.out.println(boxofficeType);

            // 박스오피스 목록 출력
            JSONArray weeklyBoxOfficeList = boxOfficeResult.getJSONArray("weeklyBoxOfficeList");
            System.out.println(weeklyBoxOfficeList.getClass().getName());    // 타입검사
            Iterator<Object> iter = weeklyBoxOfficeList.iterator();
            while(iter.hasNext()) {
                List<VideoMovieEntity> boxofiiceList = new ArrayList<>();
                VideoMovieEntity entity = new VideoMovieEntity();
                JSONObject boxOffice = (JSONObject) iter.next();
                entity.setBoxofficeType(boxofficeType);
                entity.setMovieNm((String)boxOffice.get("movieNm"));
                entity.setShowRange(DATE_FMT.format(cal.getTime()));
                entity.setRank((String)boxOffice.get("rank"));
                entity.setMovieCd((String)boxOffice.get("movieCd"));
                System.out.printf("%s위 %s\n", boxOffice.get("rnum"), boxOffice.get("movieCd"));
                boxofiiceList.add(entity);
                mapper.insertBoxOffice(boxofiiceList);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertMovie() {
        requestAPIDailyMoive(mapper, Const.REQUEST_DAILYMOVIE_URL);
        requestAPIWeekMoive(mapper,Const.REQUEST_WEEKMOVIE_URL);
    }
    public void delMovie() { mapper.delBoxOffice(); }
    public List<VideoMovieEntity> listWeeklyMovie() { return mapper.selBoxOffice(); }
    public List<VideoMovieEntity> listWeeklyMovieRandom() { return mapper.selBoxOfficeRandom(); }

    public String naverImgSearch(String searchMovieNm) {
        String clientId = Const.NAVER_CLIENT_ID;//애플리케이션 클라이언트 아이디값";
        String clientSecret = Const.NAVER_SECRET;//애플리케이션 클라이언트 시크릿값";
        String imgLink = null;
        try {
            String text = URLEncoder.encode(searchMovieNm, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/movie.json?query="+ text;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response);

            // JSON 객체로  변환
            JSONObject responseBody = new JSONObject(response.toString());
            // 데이터 추출
            JSONArray naverMovieResult = responseBody.getJSONArray("items");
            System.out.println("responseBody : " + responseBody);
            System.out.println("naverMovieResult : " + naverMovieResult);
            Iterator<Object> iter = naverMovieResult.iterator();

            List<String> list = new ArrayList<>();
            while(iter.hasNext()) {
                JSONObject naverMovie = (JSONObject) iter.next();
                list.add((String) naverMovie.get("image"));
            }
            imgLink = list.get(0);  // 맨첫번째 검색결과의 이미지(최신)
            VideoMovieEntity entity = new VideoMovieEntity();
            entity.setImg(imgLink);
            entity.setMovieNm(searchMovieNm);
            mapper.updateImgBoxOffice(entity);
            System.out.println("entity : " + entity);
            System.out.println("imgLink : " + imgLink);
        } catch (Exception e) {
            System.out.println(e);
        }
        return imgLink;
    }
}