<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 현재 날짜 사용시 -->
<%--bootstrap--%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link
        rel="stylesheet"
        href="https://unpkg.com/swiper@8/swiper-bundle.min.css"
/>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="today"/>

<!-- Code -->
<div class="main_box">
    <div data-bs-spy="scroll" data-bs-target="#list-example" data-bs-offset="0" class="scrollspy-example" tabindex="0">
        <div class="left_section">

            <div class="recommend_section" id="list-item-1">
                <!-- Slider main container -->
                <div class="swiper">
                    <!-- Additional required wrapper -->
                    <div class="swiper-wrapper">
                        <!-- Slides -->
                        <div class="swiper-slide">
                            <span>${randomWebtoon.nm}</span>
                            <div><a href="${randomWebtoon.link}"><img src="${randomWebtoon.img}" alt=""></a></div>
                        </div>
                        <div class="swiper-slide">
                            <span>${randomGame.gameNm}</span>
                            <div><a href="${randomGame.selLink}"><img src="${randomGame.imgsrc}" alt=""></a></div>
                        </div>
                        <div class="swiper-slide">
                            <c:forEach var="item" items="${weeklyMovieList}" begin="0" end="0">
                                <span>영화관 가서 ${item.movieNm} 어때요?</span>
                                <div>
                                    <a href="https://www.cgv.co.kr/"><img src="${item.img}" alt=""></a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>


                    <!-- If we need pagination -->
                    <div class="swiper-pagination" id="pagination"></div>

                    <!-- If we need navigation buttons -->
                    <%--                <div class="swiper-button-prev"></div>--%>
                    <%--                <div class="swiper-button-next"></div>--%>

                    <!-- If we need scrollbar -->
                    <%--<div class="swiper-scrollbar"></div>--%>
                </div>
                <!-- Button Box -->
                <div class="button-group">
                    <div class="btn-group" role="group" aria-label="Basic radio toggle button group" id="mainBtnGroup">
                        <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
                        <a href="/board/list" class="btn btn-light shadow-none" for="btnradio1" style="border-top-left-radius: 4px;
                        border-bottom-left-radius: 4px;">자유게시판</a>

                        <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
                        <a href="" class="btn btn-light shadow-none" for="btnradio1">순위</a>

                        <input type="radio" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off">
                        <a href="/board/list" class="btn btn-light shadow-none" for="btnradio1">랜덤</a>

                        <input type="radio" class="btn-check" name="btnradio" id="btnradio4" autocomplete="off">
                        <a href="/board/webtoon" class="btn btn-light shadow-none" for="btnradio1">웹툰</a>

                        <input type="radio" class="btn-check" name="btnradio" id="btnradio5" autocomplete="off">
                        <a href="/board/game" class="btn btn-light shadow-none" for="btnradio1">게임</a>
                    </div>
                </div>
            </div>
        </div>

        <div id="list-example" class="list-group">
            <div class="btn-group-vertical" role="group" aria-label="Basic example" id="guide-tooltip">
                <button type="button" class="btn btn-primary">↓↓ Click Guide ↓↓</button>
                <%--    data-bs-trigger="focus"는 다른데 초점이 맞춰지면 자동으로 사라지는 속성--%>
                <button type="button" class="btn btn-outline-info" data-bs-trigger="hover focus"
                        data-bs-toggle="popover" title="슬라이드(Swiper)"
                        data-bs-content="Swiper 라이브러리를 활용해 슬라이드 구성. 1번 슬라이드는 웹툰, 2번 슬라이드는 게임, 3번 슬라이드는 영화를 랜덤하게 뿌려줌.
                영화는 영화진흥위원회 오픈API를 사용해 데이터를 받고 영화 제목에 맞는 이미지를 네이버 영화 API에서 이미지를 받아 사용함.">
                    <a class="list-group-item list-group-item-action" href="#list-item-1">슬라이드</a></button>
                <button type="button" class="btn btn-outline-info" data-bs-trigger="hover focus"
                        data-bs-toggle="popover" title="RANK"
                        data-bs-content="Jsoup을 활용, 크롤링한 데이터를 데이터베이스에 저장 후 각각을 select문으로 출력함.">
                    <a class="list-group-item list-group-item-action" href="#list-item-2">RANK</a></button>
                <button type="button" class="btn btn-outline-info" data-bs-trigger="hover focus"
                        data-bs-toggle="popover" title="YOUTUBE"
                        data-bs-content="Youtube api를 활용. 한국에서 인기 급상승 중인 50가지 영상 중에 하나를 랜덤으로 출력하고(왼),
                오른쪽은 설정한 검색 키워드를 통한 결과 50가지 중 하나를 랜덤으로 출력함.">
                    <a class="list-group-item list-group-item-action" href="#list-item-3">YOUTUBE</a></button>
            </div>
        </div>
        <div class="project_info_section">
            <p>
                <button class="btn btn-primary" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                    전체 프로젝트 설명 <i class="fa-solid fa-caret-down"></i>
                </button>
            </p>
            <%--    class에 show와 위 button 클래스에 aria-exapande에 true를 줘야 맨 처음 상태가 펴준 상태가 됨.--%>
            <div class="collapse" id="collapseExample">
                <div class="card card-body">
                    <h4>전체 프로젝트 소개.</h4>
                    <p>
                        ✓ 다양한 데이터(게임, 웹툰, 영화, Youtube)를 수집하여, 페이지에 랜덤하게 뿌려줌.<br><br>
                        ✓ 데이터 수집 : <b>Jsoup을 활용한 크롤링</b>을 통해 Database에 담아 Data 활용. <br><br>
                        ✓ 랜덤 로직 : <b>Javascript</b>와 <b>JAVA</b>에서 Math 함수를 통해 랜덤로직 구현.<br><br>
                        ✓ 외부 REST API : Youtube, Naver Movie, 영화진흥위원회 api, Kakao Login.<br><br>
                        ✓ CRUD : 크롤링 자료, 게시판, 로그인, 회원가입, 댓글, 좋아요 등으로 CRUD를 구현.<br><br>
                    </p>
                    <h4>기술 스택.</h4>
                    <p>
                        <span class="color-red">✓ JavaScript</span> : AJax 통신 - fetch API / Rest API / Swiper
                        Library<br><br>
                        <span class="color-red">✓ Java</span> : Spring FrameWork(Rest API, Mybatis, HikariCP, lombok,
                        Gson, BCrypt, tiles) / JSTL / Jsoup<br><br>
                        <span class="color-red">✓ Front</span> : HTML / CSS(flex, grid) / BootStrap<br><br>
                    </p>
                </div>
            </div>
        </div>


        <div class="page_info">
            <div class="member_section">
                <h4><i class="fa-solid fa-address-card"></i>Member</h4>
                <div class="flex-center">
                    <ul>
                        <li>
                            <img src="/res/img/profile_taejun.jpg" alt="">
                            <div class="member_info">
                                <div class="member_name">
                                    <span>김태준</span>
                                </div>

                                <ul>
                                    <li>전체적인 구성/디자인(CSS) 담당</li>
                                    <li>RANDOM, 크롤링 Logic 구현</li>
                                    <li>Webtoon, Youtube, Main Part</li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <img src="/res/img/profile_taejun.jpg" alt="">
                            <div class="member_info">
                                <div class="member_name">
                                    <span>최성완</span>
                                </div>
                                <ul>
                                    <li>한 일</li>
                                    <li>한 일</li>
                                    <li>한 일</li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <img src="/res/img/profile_taejun.jpg" alt="">
                            <div class="member_info">
                                <div class="member_name">
                                    <span>손주영</span>
                                </div>
                                <ul>
                                    <li>한 일</li>
                                    <li>한 일</li>
                                    <li>한 일</li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="rank_section" id="list-item-2">
                <div class="game_rank">
                    <div class="rank_title">
                        <div class="rank_title_left">
                            <h4><i class="fa-solid fa-gamepad"></i>Hot Game</h4>
                            <span><c:out value="${today}"/>기준</span>
                        </div>

                        <a href="/board/game"><span>보러가기</span></a>
                    </div>
                    <ul>
                        <c:forEach var="item" items="${gameList}" begin="0" end="4">
                            <li>
                                <div>
                                    <a href="${item.selLink}"><img src="${item.imgsrc}" alt=""></a>
                                </div>
                                <div class="rank_info rank_info_game">
                                    <span>${item.rankNum}위</span>
                                    <span>${item.gameNm}</span>
                                    <span>${item.company}</span>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="webtoon_rank">

                    <div class="rank_title">
                        <div class="rank_title_left">
                            <h4><i class="fa-solid fa-pager"></i></i>이 웹툰들 어때요?</h4>
                        </div>

                        <a href="/board/webtoon"><span>보러가기</span></a>
                    </div>
                    <ul>
                        <c:forEach var="item" items="${webtoonListRandom}" begin="0" end="4">
                            <li>
                                <div>
                                    <a href="${item.link}"><img src="${item.img}" alt=""></a>
                                </div>
                                <div class="rank_info rank_info_webtoon">
                                    <span>${item.nm}</span>
                                    <span>${item.writer}</span>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>

            </div>
            <div class="youtube_section" id="list-item-3">
                <div class="youtube_section_title"><h4><i class="fa-brands fa-youtube"></i>이런 Youtube는 어때?</h4></div>
                <div id="video_section">

                </div>
            </div>

        </div>
    </div>
</div>

<%-- swiper --%>
<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>