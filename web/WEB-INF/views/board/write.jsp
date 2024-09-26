<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="titleVal" value="글쓰기" />
<c:set var="actionVal" value="/board/write" />
<c:set var="submitVal" value="글쓰기" />
<c:set var="iboardVal" value="0" />
<c:if test="${requestScope.data != null && requestScope.data.iboard > 0}">
    <c:set var="titleVal" value="수정" />
    <c:set var="actionVal" value="/board/mod" />
    <c:set var="submitVal" value="수정" />
    <c:set var="iboardVal" value="${requestScope.data.iboard}" />
</c:if>
<div id="writeContainer" class="flex-container flex-center flex-direction-column">
    <h1 id="wTitle">${titleVal}</h1>
    <form id="writeFrm" action="${actionVal}" method="post">
        <div><h5 style="color: red">${requestScope.error}</h5></div>
        <input type="hidden" name="iboard" value="${iboardVal}">
<%--        <input type="hidden" name="icategory" value="${icategoryVal}">--%>
        <div id="writeElem1">
            <input type="text" class="form-control" name="title" placeholder="제목을 입력하세요." value="<c:out value='${requestScope.data.title}'/>">
        </div>
        <div id="writeElem2">

            <textarea style="text-align: left" id="summernote" name="ctnt" placeholder="내용을 입력하세요."><c:out value="${requestScope.data.ctnt}" escapeXml="false"/></textarea>
        </div>
        <div id="writeElem3"><input class="writeBtn btn btn-outline-success" type="submit" value="${submitVal}"></div>
    </form>
</div>
<script>
    $('#summernote').summernote(
        'justifyLeft',
        {
            placeholder: '내용을 입력하세요.',
            tabsize: 3,
            height: 500,
            toolbar: [
                // [groupName, [list of button]]
                ['fontname', ['fontname']],
                ['fontsize', ['fontsize']],
                ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
                ['color', ['forecolor','color']],
                ['table', ['table']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['height', ['height']],
                ['insert',['picture','link','video']],
                ['view', ['fullscreen', 'help']]
            ],
            fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
            fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
            callbacks: {
                onEnter: function(){
                    document.querySelector('textarea').value.summernote('justifyLeft');
                    document.querySelector('textarea').value.summernote('insertNode', document.createTextNode("<br>"));
                }
            }
        },
        );

</script>