<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <h1></h1>
    <form action="/user/join" method="post">
        <div>ID : <input type="text" name="uid"></div>
        <div>PW : <input type="password" name="upw"></div>
        <div>PW-Check : <input type="password" name="upw-check"></div>
        <div>NAME : <input type="text" name="nm"></div>
        <div>
            gender : <input type="radio" name="gender" value="1"> 남성
            <input type="radio" name="gender" value="2"> 여성
        </div>
        <div></div>
    </form>
</div>
