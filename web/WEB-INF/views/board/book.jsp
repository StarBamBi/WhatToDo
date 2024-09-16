<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="search_book">
    <fieldset class="srch">
        <legend>검색영역</legend>
        <input type="text" name="query" id="query" accesskey="s" title="검색어" class="keyword" value="애자일">
        <input type="button" id="search" name="search" alt="검색" value="검색" />
    </fieldset>

    <table cellspacing="0" border="1" summary="책검색 API 결과" class="tbl_type">
        <caption>책검색 API 결과</caption>
        <colgroup>
            <col width="10%">
            <col width="20%">
            <col width="15%">
            <col width="15%">
            <col width="15%">
            <col width="10%">
            <col width="15%">
        </colgroup>
        <thead>
        <tr>
            <th scope="col">책표지</th>
            <th scope="col">책이름</th>
            <th scope="col">저자</th>
            <th scope="col">출판사</th>
            <th scope="col">출판년도</th>
            <th scope="col">가격</th>
            <th scope="col">ISBN</th>
        </tr>
        </thead>
        <tbody id="list">
        <tr class="__oldlist" style="">
            <td colspan="7">검색 결과가 없습니다.</td>
        </tr>
        <tr class="__template" style="display: none">
            <td><img src="#image#"  width="50px" height="70px" /></td>
            <td>#title#</td>
            <td>#author#</td>
            <td>#publisher#</td>
            <td>#pubdate#</td>
            <td>#price#</td>
            <td>#isbn#</td>
        </tr>
        </tbody>
    </table>
</div>