<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="tld/MyCustomJstlTag.tld" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<div class="container">
    <div class="row row-cols-2">
        <div class="col info">
            <div class="card" style="width: 18rem;">
                <div class="card-body info_profileCard">
                    <h5 class="card-title"><i class="fa-solid fa-id-card"></i>Profile Card</h5>
                    <div>
                        <my:profileImg iuser="${sessionScope.loginUser.iuser}" profileImgVal="${sessionScope.loginUser.profileimg}" classVal="wh-80 circular--img" imgIdVal="profileimg"/>
                        <span hidden id="data" data-iuser="${sessionScope.loginUser.iuser}"></span>
                        <span class="nickname">별명 : ${sessionScope.loginUser.nm}</span>
                    </div>
                    <div class="btn-link">
                        <label id="input_for_label" class="input_for_label">수정</label>
                        <input type="file" value="수정" accept="image/*" id="input_file" class="hidden">
                    </div>
                </div>
            </div>
        </div>
        <div class="col info"><div class="card" style="width: 18rem;">
            <div class="card-body like_info_profileCard">
                <h5 class="card-title"><i class="fa-solid fa-heart"></i>My Like</h5>
                <div>
                    <table>
                        <tr>
                            <td style="font-weight: bolder; font-size: large; color: lightpink">최근 누른 좋아요</td>
                        </tr>
                        <c:forEach var="item" items="${myLike}" begin="0" end="4">
                            <tr>
                                <td>${item.nm}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="btn-link">
                    <a href="/user/mypage/mylike" class="card-link"><button>확인하기</button></a>
                </div>
            </div>
        </div></div>
        <div class="col info">
            <div class="card" style="width: 21rem;">
                <div class="card-body info_profileCard password_change">
                    <h5 class="card-title"><i class="fa-solid fa-unlock"></i>비밀번호 변경</h5>
                    <h6>변경 후 로그아웃 됩니다.</h6>
                    <form action="/user/mypage/cheUpw" method="post">
                        <label>
                            <span>현재 비밀번호</span>
                            <input type="password" name="oldUpw">
                        </label>
                        <label>
                            <span>새 비밀번호</span>
                            <input type="password" name="newUpw">
                        </label>
                        <label>
                            <span>새 비밀번호 확인</span>
                            <input type="password" name="newUpwChk">
                        </label>
                        <div hidden id="upwChaResult">${requestScope.result}</div>
                        <div class="btn-link">
                            <input type="submit" value="변경">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col info">
            <div class="card" style="width: 18rem;">
                <div class="card-body info_profileCard">
                    <h5 class="card-title"><i class="fa-solid fa-star"></i>My Review</h5>
                    <div>
                        최근 작성한 평가
                    </div>
                    <div>
                        <table>
                            <tr>
                                <td>제목</td>
                                <td>내용</td>
                            </tr>
                            <c:forEach var="item" items="${webtoonReviewMy}" begin="0" end="0">
                                <tr>
                                    <td>${item.nm}</td>
                                    <td>${item.ctnt}</td>
                                </tr>
                            </c:forEach>
                        </table>

                    </div>
                    <div class="btn-link">
                        <a href="/user/mypage/myreview" class="card-link"><button>확인하기</button></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    const upwChaResult = document.querySelector('#upwChaResult'); // div값으로 실패이유를 받아서 alert값으로 띄워줌
    if (upwChaResult.innerText !== '') {
        alert(upwChaResult.innerText);
    }
</script>