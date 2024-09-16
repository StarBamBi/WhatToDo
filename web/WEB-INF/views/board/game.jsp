<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss" var="today"/>
<link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css"/>
<style>
    @font-face {
        font-family: 'SDSamliphopangche_Outline';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts-20-12@1.0/SDSamliphopangche_Outline.woff') format('woff');
        font-weight: normal;
        font-style: normal;
    }
</style>
<div class="container">
    <div hidden id="data" data-iuser="${sessionScope.loginUser.iuser}"></div>
    <button id="crawlingBtn" class="btn btn-outline-dark" onclick="crawling()">
        <span class="spinner-border-sm"></span>
        최신 게임 정보 불러오기
        <script>
            let crawlingBtnElem = document.querySelector("#crawlingBtn");
            let dataElem = document.querySelector("#data");
            let crawling = function () {
                if (dataElem.dataset.iuser <= 0) {
                    alert("로그인 해주세요");
                    return;
                }
                crawlingBtnElem.childNodes[1].classList.add("spinner-border");
                location.href="/board/gameCrawling"
            }
        </script>
    </button>
    <div class="choiceBtn_section">
        <div class="choiceBtn_section_line popular">
            <label id="ppLb"><p class="btLb" style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">
                인기</p>
                <div class="btn-group">
                    <button class="ppBtn" style="margin-left: 15px">TOP</button>
                    <button class="ppBtn">GREAT</button>
                    <button class="ppBtn">GOOD</button>
                </div>
            </label>
            <button id="ppRanBtn">인기 RANDOM</button>
        </div>
        <div class="choiceBtn_section_line kinds">
            <label id="kdLb"><p class="btLb" style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">
                장르</p>
                <div class="btn-group">
                    <button class="kdBtn" style="margin-left: 15px">RPG</button>
                    <button class="kdBtn">FPS</button>
                    <button class="kdBtn">스포츠 / 레이싱</button>
                    <button class="kdBtn">액션</button>
                    <button class="kdBtn">전략</button>
                    <button class="kdBtn">기타</button>
                </div>
            </label>
            <button id="kdRanBtn">장르 RANDOM</button>
        </div>
        <div class="choiceBtn_section_line theme">
            <label id="pfLb"><p class="btLb" style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">
                플랫폼</p>
                <div class="btn-group">
                    <button class="pfBtn" style="margin-left: 15px">MOBILE</button>
                    <button class="pfBtn">PC온라인</button>
                    <button class="pfBtn">스팀</button>
                </div>
            </label>
            <button id="pfRanBtn">플랫폼 RANDOM</button>
        </div>
    </div>
    <div class="search_section">
        <button class="fun-btn">뭐하Gee?<br>
        ALL RANDOM</button>
    </div>
</div>

<div class="swiper" style="margin-top: 30px">
    <div class="swiper-wrapper">
        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">MECA TOP 50</h4>
            <ul class="meca_game_rank">
            <div class="rank_container">
            <c:forEach var="item" items="${gameList}" begin="0" end="9">
                <li>
                    <div class="meca_thumb_img">
                        <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                    </div>
                    <div class="rank_info rank_info_game">
                        <div>${item.rankNum}위</div>
                        <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                        <div>${item.company}</div>
                    </div>
                </li>
            </c:forEach>
            </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">MECA TOP 50</h4>
            <ul class="meca_game_rank">
                <div class="rank_container">
                    <c:forEach var="item" items="${gameList}" begin="10" end="19">
                        <li>
                            <div class="meca_thumb_img">
                                <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                            </div>
                            <div class="rank_info rank_info_game">
                                <div>${item.rankNum}위</div>
                                <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                                <div>${item.company}</div>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">MECA TOP 50</h4>
            <ul class="meca_game_rank">
                <div class="rank_container">
                    <c:forEach var="item" items="${gameList}" begin="20" end="29">
                        <li>
                            <div class="meca_thumb_img">
                                <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                            </div>
                            <div class="rank_info rank_info_game">
                                <div>${item.rankNum}위</div>
                                <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                                <div>${item.company}</div>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">MECA TOP 50</h4>
            <ul class="meca_game_rank">
                <div class="rank_container">
                    <c:forEach var="item" items="${gameList}" begin="30" end="39">
                        <li>
                            <div class="meca_thumb_img">
                                <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                            </div>
                            <div class="rank_info rank_info_game">
                                <div>${item.rankNum}위</div>
                                <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                                <div>${item.company}</div>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">MECA TOP 50</h4>
            <ul class="meca_game_rank">
                <div class="rank_container">
                    <c:forEach var="item" items="${gameList}" begin="40" end="49">
                        <li>
                            <div class="meca_thumb_img">
                                <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                            </div>
                            <div class="rank_info rank_info_game">
                                <div>${item.rankNum}위</div>
                                <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                                <div>${item.company}</div>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">MOBILE TOP 40</h4>
            <ul class="mobile_game_rank">
                <div class="rank_container">
                <c:forEach var="item" items="${pfGameList}" begin="0" end="9">
                    <li>
                        <div class="game_thumb_img">
                            <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                        </div>
                        <div class="rank_info rank_info_game">
                            <div>${item.rankNum}위</div>
                            <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                            <div>${item.company}</div>
                        </div>
                    </li>
                </c:forEach>
                </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">MOBILE TOP 40</h4>
            <ul class="mobile_game_rank">
                <div class="rank_container">
                    <c:forEach var="item" items="${pfGameList}" begin="10" end="19">
                        <li>
                            <div class="game_thumb_img">
                                <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                            </div>
                            <div class="rank_info rank_info_game">
                                <div>${item.rankNum}위</div>
                                <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                                <div>${item.company}</div>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">MOBILE TOP 40</h4>
            <ul class="mobile_game_rank">
                <div class="rank_container">
                    <c:forEach var="item" items="${pfGameList}" begin="20" end="29">
                        <li>
                            <div class="game_thumb_img">
                                <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                            </div>
                            <div class="rank_info rank_info_game">
                                <div>${item.rankNum}위</div>
                                <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                                <div>${item.company}</div>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">MOBILE TOP 40</h4>
            <ul class="mobile_game_rank">
                <div class="rank_container">
                    <c:forEach var="item" items="${pfGameList}" begin="30" end="39">
                        <li>
                            <div class="game_thumb_img">
                                <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                            </div>
                            <div class="rank_info rank_info_game">
                                <div>${item.rankNum}위</div>
                                <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                                <div>${item.company}</div>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">PC TOP 40</h4>
            <ul class="pc_game_rank">
                <div class="rank_container">
                <c:forEach var="item" items="${pfGameList}" begin="40" end="49">
                    <li>
                        <div class="game_thumb_img">
                            <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                        </div>
                        <div class="rank_info rank_info_game">
                            <div>${item.rankNum}위</div>
                            <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                            <div>${item.company}</div>
                        </div>
                    </li>
                </c:forEach>
                </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">PC TOP 40</h4>
            <ul class="pc_game_rank">
                <div class="rank_container">
                    <c:forEach var="item" items="${pfGameList}" begin="50" end="59">
                        <li>
                            <div class="game_thumb_img">
                                <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                            </div>
                            <div class="rank_info rank_info_game">
                                <div>${item.rankNum}위</div>
                                <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                                <div>${item.company}</div>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">PC TOP 40</h4>
            <ul class="pc_game_rank">
                <div class="rank_container">
                    <c:forEach var="item" items="${pfGameList}" begin="60" end="69">
                        <li>
                            <div class="game_thumb_img">
                                <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                            </div>
                            <div class="rank_info rank_info_game">
                                <div>${item.rankNum}위</div>
                                <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                                <div>${item.company}</div>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">PC TOP 40</h4>
            <ul class="pc_game_rank">
                <div class="rank_container">
                    <c:forEach var="item" items="${pfGameList}" begin="70" end="79">
                        <li>
                            <div class="game_thumb_img">
                                <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                            </div>
                            <div class="rank_info rank_info_game">
                                <div>${item.rankNum}위</div>
                                <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                                <div>${item.company}</div>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">STEAM TOP 40</h4>
            <ul class="st_game_rank">
                <div class="rank_container">
                <c:forEach var="item" items="${pfGameList}" begin="80" end="89">
                    <li>
                        <div class="game_thumb_img">
                            <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                        </div>
                        <div class="rank_info rank_info_game">
                            <div>${item.rankNum}위</div>
                            <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                            <div>${item.company}</div>
                        </div>
                    </li>
                </c:forEach>
                </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">STEAM TOP 40</h4>
            <ul class="st_game_rank">
                <div class="rank_container">
                    <c:forEach var="item" items="${pfGameList}" begin="89" end="99">
                        <li>
                            <div class="game_thumb_img">
                                <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                            </div>
                            <div class="rank_info rank_info_game">
                                <div>${item.rankNum}위</div>
                                <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                                <div>${item.company}</div>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">STEAM TOP 40</h4>
            <ul class="st_game_rank">
                <div class="rank_container">
                    <c:forEach var="item" items="${pfGameList}" begin="100" end="109">
                        <li>
                            <div class="game_thumb_img">
                                <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                            </div>
                            <div class="rank_info rank_info_game">
                                <div>${item.rankNum}위</div>
                                <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                                <div>${item.company}</div>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </ul>
        </div>

        <div class="swiper-slide">
            <h4 style="display: inline-block; font-family: 'SDSamliphopangche_Outline'">STEAM TOP 40</h4>
            <ul class="st_game_rank">
                <div class="rank_container">
                    <c:forEach var="item" items="${pfGameList}" begin="110" end="119">
                        <li>
                            <div class="game_thumb_img">
                                <img style="width: 100px; height: 100px" src="${item.imgsrc}" alt="">
                            </div>
                            <div class="rank_info rank_info_game">
                                <div>${item.rankNum}위</div>
                                <a href="${item.selLink}" target='_blank'><span class="sel_gameNm">${item.gameNm}</span></a>
                                <div>${item.company}</div>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </ul>
        </div>

    </div>

    <div class="swiper-pagination"></div>

    <div class="swiper-button-prev"></div>
    <div class="swiper-button-next"></div>

    <div class="swiper-scrollbar"></div>
</div>

<div id="modal" class="modal modal-overlay" style="display: none">
    <div class="modal-window modal">
        <div class="modalTitle">오늘의 게임</div>
        <div class="close-area close text-important">X</div>
        <div class="selected-img"></div>
        <div class="modalContent"></div>
        <div class="gameLink"></div>
        <div class="heart_section">
            <div class="like_count"></div>
            <span><i id="likeBtn" class="fa-solid fa-heart-crack fav_icon"></i></span>
        </div>
        <div class="modalContent">
        <div class="gameCmtList"></div>
        <div>
            <form id="gameCmtFrm" class="form-group">
                    <input class="form-control" id="gameCmtCtnt" type="text" name="ctnt">
                    <input style="font-weight: bolder; color: white" class="btn btn-outline-dark" type="button"
                           id="cmt_submit" value="나의 평가 쓰기">
            </form>
        </div>
    </div>
</div>
</div>
<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>

<script>
    let swiper = new Swiper(".swiper", {

        loop: true,
        spaceBetween: 300,

        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },

        pagination: {
            el: '.swiper-pagination',
            type: 'bullets',
        },

        scrollbar: {
            el: '.swiper-scrollbar',
            draggable: true,
        },

        autoplay: {
            delay: 3000,
        }
    });
</script>