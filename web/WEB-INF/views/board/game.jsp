<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <form action="">
        <div class="choiceBtn_section">
<%--            <div class="choiceBtn_section_line rating">--%>
<%--                <label id="rtLb">평가--%>
<%--                    <button class="rtBtn">GOAT</button>--%>
<%--                    <button class="rtBtn">띵작</button>--%>
<%--                    <button class="rtBtn">수작</button>--%>
<%--                </label>--%>
<%--            </div>--%>
            <div class="choiceBtn_section_line popular">
                <label id="ppLb">인기
                    <button class="ppBtn">TOP</button>
                    <button class="ppBtn">GREAT</button>
                    <button class="ppBtn">GOOD</button>
                </label>
            </div>
            <div class="choiceBtn_section_line kinds">
                <label id="kdLb">장르
                    <button class="kdBtn">RPG</button>
                    <button class="kdBtn">FPS</button>
                    <button class="kdBtn">스포츠 / 레이싱</button>
                    <button class="kdBtn">액션</button>
                    <button class="kdBtn">전략</button>
                    <button class="kdBtn">기타</button>
                </label>
            </div>
            <div class="choiceBtn_section_line theme">
                <label id="pfLb">플랫폼
                    <button class="pfBtn">MOBILE</button>
                    <button class="pfBtn">PC온라인</button>
                    <button class="pfBtn">스팀</button>
                </label>
            </div>
        </div>
        <div class="search_section">
            <button class="fun-btn">뭐하Gee?</button>
        </div>
    </form>
    <div id="modal" class="modal-overlay">
        <div class="modal-window">
            <div class="title">
                <h2>오늘은 이 게임 어때요?</h2>
            </div>
            <div class="close-area">X</div>
            <div class="content">
                <p>가나다라마바사 아자차카타파하</p>
                <p>가나다라마바사 아자차카타파하</p>
                <p>가나다라마바사 아자차카타파하</p>
                <p>가나다라마바사 아자차카타파하</p>

            </div>
        </div>
    </div>
</div>