<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    @font-face {
        font-family: 'HS-Regular';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2201-2@1.0/HS-Regular.woff') format('woff');
        font-weight: normal;
        font-style: normal;
    }
</style>

<div style="font-family: 'HS-Regular'">
    <button id="crawlingMovieBtn" class="btn btn-outline-dark" onclick="crawling()">
        <span class="spinner-border-sm"></span>
        최신 영화 정보 불러오기
        <script>
            let crawlingBtnElem = document.querySelector("#crawlingMovieBtn");
            let dataElem = document.querySelector("#data");
            let crawling = function () {
                if (dataElem.dataset.iuser <= 0) {
                    alert("로그인 해주세요");
                    return;
                }
                crawlingBtnElem.childNodes[1].classList.add("spinner-border");
                location.href = "/board/videomCrawling"
            }
        </script>
    </button>
</div>

<h1 style="font-family: 'HS-Regular'; margin-top: 10px">이 영화 어때요?</h1>
<div class="movieContainer">
    <c:forEach var="item" items="${weeklyMovieList}">

        <div class="card" style="width:250px">
            <img class="card-img-top" style="max-height: 250px" src="${item.img}" alt="Card image">
            <div class="card-body">
                <h4 class="card-title" style="font-family: 'HS-Regular'">${item.movieNm}</h4>
                <p class="card-text" style="font-family: 'HS-Regular'">개봉일 : ${item.openDt}</p>
                <p class="card-text" style="font-family: 'HS-Regular'">감독 : ${item.director}</p>
                <p class="card-text" style="font-family: 'HS-Regular'">배우 : ${item.actor}</p>
                <p class="rating card-text" style="font-family: 'HS-Regular'">평점 : ${item.rating}</p>
                <div class="progress">
                    <div class="progress-bar bg-danger" ></div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<div class="bookMovie" style="font-family: 'HS-Regular'">
    <a href="http://www.cgv.co.kr/ticket/"><button type="button" class="btn btn-outline-danger">CGV 예매하러 가기</button></a>
    <a href="https://www.megabox.co.kr/booking?rpstMovieNo="><button type="button" class="btn btn-outline-primary">MEGABOX 예매하러 가기</button></a>
    <a href="https://www.lottecinema.co.kr/NLCHS/Ticketing"><button type="button" class="btn btn-outline-warning">롯데시네마 예매하러 가기</button></a>
</div>
