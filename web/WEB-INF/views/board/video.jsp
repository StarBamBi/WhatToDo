<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
    <h1>비디오</h1>
    <c:forEach var="item" items="${weeklyMovieList}">
        <div>
            <li>${item.movieNm}</li>
            <li><img src="${item.img}" alt=""></li>
        </div>
    </c:forEach>
</div>
<div>
    <a href="/board/videomCrawling"><button>크롤링</button></a>

</div>