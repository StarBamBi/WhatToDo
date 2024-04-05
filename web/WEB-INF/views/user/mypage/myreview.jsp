<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <h3><i class="fa-solid fa-star"></i>${sessionScope.loginUser.nm}님의 평가</h3>
    <select class="form-select" aria-label="Default select example" id="select_form" name="select_form" onchange="changeVal(this.value)">
        <option value="1">전체</option>
        <option value="2">웹툰</option>
        <option value="3">게임</option>
    </select>
    <div id="my_review">
        <table>
            <tr>
                <th>제목</th>
                <th>나의 평가</th>
                <th>작성 일시</th>
            </tr>
            <c:forEach var="item" items="${pageList}">
                <tr>
                    <td>${item.nm}</td>
                    <td>${item.ctnt}</td>
                    <td>${item.rdt}</td>
                </tr>
            </c:forEach>
        </table>
        <div>
            <ul class="pagination">
                <c:if test="${pageMaker.prev}">
                    <li><a href="/user/mypage/myreview?page=${pageMaker.startPage - 1}">이전</a></li>
                </c:if>
                <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
                    <li <c:out value="${pageMaker.dto.page == idx ? 'class=active' : ''}"/>>
                        <a href="/user/mypage/myreview?page=${idx}">${idx}</a>
                    </li>
                </c:forEach>
                <c:if test="${pageMaker.next && pageMaker.endPage >0}">
                    <li>
                        <a href="/user/mypage/myreview?page=${pageMaker.endPage + 1}">다음</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>

</div>