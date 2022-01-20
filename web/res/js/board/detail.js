let cmtFrmElem = document.querySelector("#cmtFrm");
let dataElem = document.querySelector("#data");

if(cmtFrmElem) {
    cmtFrmElem.addEventListener("submit", (e) => {
        e.preventDefault();
    });

    cmtFrmElem.cmt_submit.addEventListener('click', () => {
        let cmtVal = cmtFrmElem.ctnt.value;
        if(cmtVal.length === 0) {
            alert("내용을 입력해 주세요.");
        } else if (cmtVal.includes("<") || cmtVal.includes(">")) {
            alert("내용에 < 혹은 >를 사용하실 수 없습니다.");
        } else {
            insBoardCmtAjax(cmtVal);
        }
    });

    let insBoardCmtAjax = (val) => {
        let param = {
            'iboard' : dataElem.dataset.iboard,
            'ctnt' : val
        };
        fetch('/board/cmt', {
            'method' : 'post',
            'headers': { 'Content-Type': 'application/json' },
            'body' : JSON.stringify(param)
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
            }).catch(e => {
                console.log(e);
        })
    }
}

let delBtnElem = document.querySelector("#delBtn");

if(delBtnElem) {
    delBtnElem.addEventListener('click', () => {
        let iboard = dataElem.dataset.iboard;

        if(confirm("삭제하시겠습니까?")) {
            location.href=`/board/del/?iboard=${iboard}`;
        }
    });
}

let lastPBtnElem = document.querySelector('.lastPBtn');
let lastNBtnElem = document.querySelector('.lastNBtn');

if(lastPBtnElem) {
    lastPBtnElem.addEventListener('click', (e) => {
        e.preventDefault();
        alert("마지막 글입니다.");
    })
}

if(lastNBtnElem) {
    lastNBtnElem.addEventListener('click', (e) => {
        e.preventDefault();
        alert("최신 글입니다.");
    })
}
