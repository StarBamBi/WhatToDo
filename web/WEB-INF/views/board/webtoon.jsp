<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<div data-bs-spy="scroll" data-bs-target="#list-example" data-bs-offset="0" class="scrollspy-example" tabindex="0">
<div class="btn-group-vertical" role="group" aria-label="Basic example" id="guide-tooltip">
    <button type="button" class="btn btn-primary">↓↓ Click Guide ↓↓</button>
    <%--    data-bs-trigger="focus"는 다른데 초점이 맞춰지면 자동으로 사라지는 속성--%>
    <button type="button" class="btn btn-outline-info" data-bs-trigger="hover focus" data-bs-toggle="popover" title="랜덤 웹툰"
            data-bs-content="RESTful api 방식. 사용자가 고르고 싶은 주제를 선택하면 JavaScript에서 분기를 처리해 배경과 글씨 색을 바꾸고 선택한 장르에 맞는 랜덤한 데이터를 출력함.">
        <a class="list-group-item list-group-item-action" href="#list-item-1">랜덤웹툰</a></button>
    <button type="button" class="btn btn-outline-info" data-bs-trigger="hover focus" data-bs-toggle="popover" title="웹툰 정보&리뷰&좋아요"
            data-bs-content="RESTful api 방식. 웹툰을 선택하면 모달창을 띄우고 웹툰 정보를 출력. 웹툰에 대한 평가를 남기고 좋아요를 누를 수 있음(CRUD).">
        <a class="list-group-item list-group-item-action" href="#list-item-2">리뷰&좋아요&정보</a></button>
</div>
<div>
    <p id="list-item-1">
        <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
            웹툰 Page 설명 <i class="fa-solid fa-caret-down"></i>
        </button>
    </p>
    <%--    class에 show와 위 button 클래스에 aria-exapande에 true를 줘야 맨 처음 상태가 펴준 상태가 됨.--%>
    <div class="collapse" id="collapseExample" style="margin: 10px 0;">
        <div class="card card-body">
            <h4>랜덤 웹툰 추천 페이지.</h4>
            <p></p>
            <p>
                ✓ 데이터 수집 : <b>Jsoup을 활용한 크롤링.</b> 크롤링은 네이버웹툰을 장르, 요일, 완결 등으로 웹툰 제목, 이미지, 작가 등의 데이터를 수집함.<br><br>
                ✓ 랜덤 로직 : <b>Javascript</b>를 통해 조건을 선택하면 조건에 맞는 랜덤 웹툰을, 선택하지 않으면 전체 자료 중 랜덤하게 웹툰을 출력.<br><br>
                ✓ 리뷰&좋아요<b>(CRUD)</b> : 웹툰을 선택하면 모달창이 뜨면서 로그인한 유저에 한해 리뷰를 작성(Create), 삭제(Delete),<br> 수정(Update)할 수 있고 작성된 리뷰를 볼 수(Read) 있음.(좋아요도 마찬가지).<br><br>
                <span style="color: red">✓ 기술 스택 : JavaScript(AJax 통신 : fetch API , Restful API) / Java(Jsoup, Spring) / JSTL / HTML&CSS&BootStrap</span>
            </p>

        </div>
    </div>
    <div class="webtoon_box">
        <div class="choiceBtn_section">
            <button>일상</button>
            <button>개그</button>
            <button>판타지</button>
            <button>액션</button>
            <button>드라마</button>
            <button>순정</button>
            <button>감성</button>
            <button>스릴러</button>
            <button>시대극</button>
            <button>스포츠</button>
            <button>완결</button>
            <button id="randomSubmitBtn" class="fun-btn">RANDOM</button>
            <button class="crawlingBtn">크롤링</button>
        </div>
        <div class="result_box" data-iuser="${sessionScope.loginUser.iuser}"
             data-writernm="${sessionScope.loginUser.nm}">

            <c:forEach var="item" items="${webtoonGenreListRandom}">
                <div class="random-card webtoonModalElement" id="resultBoxData" data-nm="${item.nm}" data-writer="${item.writer}" >
                    <div class="genre_title" id="genre_title">>> 추천 웹툰 <<</div>
                    <div><img src="${item.img}"></div>
                    <div class="random-webtoon-nm"><span class="card-nm"><b>${item.nm}</b></span>
                        <div><span class="card-writer">${item.writer}</span></div></div>
                    <div class="webtoonLink"><span><a href="${item.link}"> >>보러가기<< </a></span></div></div>
            </c:forEach>
        </div>
    </div>
    <div id="recommand_box">

        <span class="webtoon_recommand_title" id="list-item-2">이런 웹툰 어때요?</span>

        <ul class="recommend_section">
            <c:forEach var="item" items="${webtoonRecommandListRandom}">
                <div class="card webtoonModalElement" id="card" data-nm="${item.nm}"
                     data-weekend="${item.weekend}"
                     data-iuser="${sessionScope.loginUser.iuser}"
                     data-writernm="${sessionScope.loginUser.nm}">
                    <img src="${item.img}" class="card-img-top">
                    <div class="card-body" id="card-body">

                        <span class="card-title card-nm" id="card-title">${item.nm}</span>
                        <span class="card-text card-writer" id="card-writer">${item.writer}</span>
                        <span class="card-text webtoonLink"><a href="${item.link}">>> 보러가기 <<</a></span>
                    </div>
                </div>
            </c:forEach>
        </ul>
    </div>
    <div class="naver_section">
        <ul>
            <c:forEach var="item" items="${webtoonListRandom}">
            <div class="webtoonModalElement" id="data"
                 data-nm="${item.nm}"
                 data-weekend="${item.weekend}"
                 data-iuser="${sessionScope.loginUser.iuser}"
                 data-writernm="${sessionScope.loginUser.nm}">
                <li>
                    <div id="webtoon_img">
                        <img src="${item.img}">
                    </div>
                    <div id="webtoon_databox">
                        <span class="font-14px">${item.nm}</span>
                    </div>

                    <span class="webtoonLink webtoonWriter">작가 : ${item.writer}</span>
                    <span class="webtoonLink"><a href="${item.link}">>> 보러가기 <<</a></span>
                    <span class="webtoonInfoHidden webtoonWeekend">${item.weekend}</span>
                    <span class="webtoonInfoHidden webtoonIuser">${item.iuser}</span>
                </li>
            </div>
            </c:forEach>
    </div>

    <div id="modal" class="modal-overlay" style="display: none">
        <div class="modal-window">
            <div class="close-area">X</div>
            <div class="modalTitle">
                웹툰 정보
            </div>
            <div class="heart_section">
                <span><i class="fa-regular fa-heart" id="fav_icon"></i></span>
                <span class="like_cnt"></span>
            </div>
            <div class="modalContent">

            </div>
            <div>
                <form id="reviewFrm">
                    <input type="text" name="ctnt" class="reviewTextInput" placeholder="리뷰를 작성해주세요.">
                    <input type="button" id="btn_submit" value="작성">
                </form>
                <div id="review_list">

                </div>
            </div>

        </div>
    </div>
</div>
</div>