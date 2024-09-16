
let writeBtn = document.querySelector(".writeBtn");

writeBtn.addEventListener("click", (e)=> {

    let writeCtnt = document.querySelector("#summernote").value;
    let writeTitle = document.querySelector("#writeElem1 > input").value;

    if(writeCtnt == "<br>" || writeCtnt==""||writeCtnt == null || writeTitle=="" || writeTitle == null) {
        e.preventDefault()
        alert("제목 혹은 내용을 입력하세요.")
    }
})