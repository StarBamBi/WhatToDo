let cmtFrmElem = document.querySelector("#cmtFrm");
let dataElem = document.querySelector("#dataWithIboard");
let iboard = dataElem.dataset.iboard;

if (cmtFrmElem) {
    cmtFrmElem.addEventListener("submit", (e) => {
        e.preventDefault();
    });

    cmtFrmElem.cmt_submit.addEventListener('click', () => {
        let cmtVal = cmtFrmElem.ctnt.value;
        if (cmtVal.length === 0) {
            alert("내용을 입력해 주세요.");
        } else if (cmtVal.includes("<") || cmtVal.includes(">")) {
            alert("내용에 < 혹은 >를 사용하실 수 없습니다.");
        } else {
            insBoardCmtAjax(cmtVal);
            // location.href="/board/detail?iboard="+ iboard;
        }
    });
    const item = {
        icmt: data.result,
        iuser: parseInt(dataElem.dataset.iuser),
        ctnt: cmtFrmElem.ctnt.value,
    }
    let insBoardCmtAjax = (val) => {
        let param = {
            'iboard': dataElem.dataset.iboard,
            'ctnt': val
        };
        fetch('/board/cmt', {
            'method': 'post',
            'headers': {'Content-Type': 'application/json'},
            'body': JSON.stringify(param),
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                const tableElem = document.querySelector('table');
                if (tableElem) {
                    tableElem.remove();
                }
                cmtListElem.innerText = '';
                cmtFrmElem.ctnt.value = null;
                getCmtList();

            }).catch(e => {
            console.log(e);
        })
    }
}

let dtCtnt = document.querySelector("#dtCtnt");

// const getDetail = () => {
//     fetch('/board/detailItem')
//         .then(res => {
//             return res.json();
//         }).then(data => {
//         console.log(data);
//         dtCtnt.innerHTML = `
//                             ${data.ctnt}
//                             `;
//     });
// }
// getDetail();
let likeCountElem = document.querySelector("#likeCount");


let cmtListElem = document.querySelector("#cmt_list");
console.log(iboard);
// let iboard = dataElem.dataset.iboard;
const getCmtList = () => {
    fetch(`/board/cmt/${iboard}`)
        .then(res => {
            return res.json();
        }).then(data => {
        console.log(data);
        setCmtList(data);
    });
}

const setCmtList = (list) => {

    if (list.length === 0) {
        cmtListElem.innerText = '댓글이 없습니다.';
        return;
    }

    const table = document.createElement('table');
    table.innerHTML = `
        <tr>
            <th>NO</th>
            <th>내용</th>
            <th>작성자</th>
            <th></th>
        </tr>
        `
    list.forEach(item => {
        makeTr(table, item);
    });
    cmtListElem.appendChild(table);
}

const makeTr = (table, item) => {
    const tr = document.createElement('tr');
    tr.style.borderBottom = "1px solid darkgray";
    tr.innerHTML = `
        <td>${item.icmt}</td>
        <td>${item.ctnt}</td>
        <td>${item.nm}</td>
        `;

    const td = document.createElement('td');
    tr.appendChild(td);

    if (parseInt(dataElem.dataset.iuser) === item.iuser) {
        const modBtn = document.createElement("input");
        modBtn.type = 'button';
        modBtn.value = '수정';
        modBtn.classList.add('boardBtn');
        modBtn.addEventListener('click', () => {
            const tdArr = tr.querySelectorAll('td');
            const tdCell = tdArr[1];//댓글 내용

            const modInput = document.createElement('input');
            modInput.value = item.ctnt;
            const saveBtn = document.createElement('input');
            saveBtn.type = 'button';
            saveBtn.value = '저장';
            saveBtn.classList.add('boardBtn');
            saveBtn.addEventListener('click', () => {
                const param = {
                    icmt: item.icmt,
                    ctnt: modInput.value
                }
                fetch('/board/cmt', {
                    'method': 'put',
                    'headers': {'Content-Type': 'application/json'},
                    'body': JSON.stringify(param)
                })
                    .then(data => {
                        console.log(data);
                        tdCell.innerText = modInput.value;
                        item.ctnt = modInput.value;
                        removeCancelBtn();
                    })
                    .catch(data => {
                        alert("댓글 작성에 실패했습니다.");
                        console.log(data);
                    })
            });
            tdCell.innerHTML = null;
            tdCell.appendChild(modInput);
            tdCell.appendChild(saveBtn);

            const cancelBtn = document.createElement('input');
            cancelBtn.type = 'button';
            cancelBtn.value = '취소';
            cancelBtn.classList.add('boardBtn');
            cancelBtn.addEventListener('click', () => {
                tdCell.innerText = item.ctnt;
                removeCancelBtn();
            });

            const removeCancelBtn = () => {
                modBtn.classList.remove('hidden');
                delBtn.classList.remove('hidden');
                cancelBtn.remove();
            }

            td.insertBefore(cancelBtn, modBtn);
            modBtn.classList.add('hidden');
            delBtn.classList.add('hidden');
        });


        const delBtn = document.createElement("input");
        delBtn.type = 'button';
        delBtn.value = '삭제';
        delBtn.classList.add('boardBtn');
        delBtn.addEventListener('click', () => {
            if (confirm("댓글을 삭제하시겠습니까?")) {
                delCmt(item.icmt, tr);
                // location.href='/board/detail?iboard='+iboard;
            }
        });

        td.appendChild(modBtn);
        td.appendChild(delBtn);
    }
    table.appendChild(tr);
}
getCmtList();


const delCmt = (icmt, tr) => {
    fetch(`/board/cmt/${icmt}`,
        {
            'method': 'delete',
            'headers': {'Content-Type': 'application/json'}
        }).then(res => res.json())
        .then(data => {
            console.log(data.result);
            const tableElem = document.querySelector('table');
            tableElem.remove();
            getCmtList();
        }).catch(e => {
        console.log(e)
    });
}

const getTrLen = () => {
    const cmtListElem = document.querySelector('#cmt_list');
    const trArr = cmtListElem.querySelectorAll('table tr');
    return trArr.length;
}


let delBtnElem = document.querySelector("#delBtn");

if (delBtnElem) {
    delBtnElem.addEventListener('click', () => {

        if (confirm("삭제하시겠습니까?")) {
            location.href = `/board/del/?iboard=${iboard}`;
        }
    });
}

let lastPBtnElem = document.querySelector('.lastPBtn');
let lastNBtnElem = document.querySelector('.lastNBtn');

if (lastPBtnElem) {
    lastPBtnElem.addEventListener('click', (e) => {
        e.preventDefault();
        alert("마지막 글입니다.");
    })
}

if (lastNBtnElem) {
    lastNBtnElem.addEventListener('click', (e) => {
        e.preventDefault();
        alert("최신 글입니다.");
    })
}

const likeBtnElem = document.querySelector('#likeBtn');


const isLike = function (iboard) {
    console.log("islike : " + iboard);
    fetch(`/board/like/${iboard}`)
        .then(res => res.json())
        .then((data) => {
            console.log(data);
            switch (data.result) {
                case 0:
                    offLike();
                    break;
                case 1:
                    onLike();
                    break;
            }
            likeCountElem.innerHTML = `<div style="margin-top: 5px; color: lightpink; font-weight: bolder">${data.count}명의 유저가 좋아합니다.</div>`
        })
        .catch((e) => {
            console.log(e);
        });
}

isLike(iboard);

const offLike = () => {
    if (likeBtnElem) {
        likeBtnElem.classList.remove('fas');
        likeBtnElem.classList.add('far');
    }

}
const onLike = () => {
    if (likeBtnElem) {
        likeBtnElem.classList.remove('far');
        likeBtnElem.classList.add('fas');
    }

}
// if(dataElem.dataset.iuser) {
likeBtnElem.addEventListener('click', (e) => {
    if (e.target.classList.contains('far')) {
        const param = {iboard: iboard,
                        'iuser' : dataElem.dataset.iuser};

        fetch('/board/like', {
            'method': 'post',
            'headers': {'Content-Type': 'application/json'},
            'body': JSON.stringify(param)
        })
            .then(res => res.json())
            .then(data => {
                console.log(data);
                console.log("ins like board : " + iboard)
                onLike();
                isLike(iboard);
            })
    } else {
        fetch(`/board/like/${iboard}`, {
            'method': 'delete',
            'headers': {'Content-Type': 'application/json'},
        }).then(res => res.json())
            .then(data => {
                console.log(data);
                console.log("del like board : " + iboard);
                offLike();
                isLike(iboard);
            });
    }
})
// }

