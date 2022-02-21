function changeVal(val) {
    console.log(val);
    if (val == 1) {
        location.href="/user/mypage/mylike";
        // allLikeList()
    } else if (val == 2) {
        webtoonLikeList();
    } else if (val == 3) {
        gameLikeList();
    } else if (val == 4) {
        boardLikeList();
    }
}

const myLike = document.querySelector(".myLike");
// const allLikeList = () => {
//     fetch(`/mypage/like/all`)
//         .then(res => {
//             return res.json();
//         }).then((data) => {
//             console.log(data)
//         setCmtList(data)
//     }).catch(e => {
//         console.log(e);
//     })
// }

const webtoonLikeList = () => {
    fetch(`/mypage/like/webtoon`)
        .then(res => {
            return res.json();
        }).then((data) => {
        console.log(data)
        setCmtList(data)
    }).catch(e => {
        console.log(e);
    })
}

const gameLikeList = () => {
    fetch(`/mypage/like/game`)
        .then(res => {
            return res.json();
        }).then((data) => {
        console.log(data)
        setCmtList(data)
    }).catch(e => {
        console.log(e);
    })
}

const boardLikeList = () => {
    fetch(`/mypage/like/board`)
        .then(res => {
            return res.json();
        }).then((data) => {
        console.log(data)
        setCmtList(data)
    }).catch(e => {
        console.log(e);
    })
}

let likeAllListElem = document.querySelectorAll(".likeAllList")
likeAllListElem.forEach(function (item) {
    item.addEventListener("click", ()=> {
        if(item.dataset.iboard != 0) {
            location.href=`/board/detail?iboard=${item.dataset.iboard}`
        } else {
            let selectedGameNm = item.childNodes[1].textContent;
            console.log(selectedGameNm);
            const modalWindow = document.querySelector("#modal-like")
            const modalXBtn = document.querySelector(".close-area")

            modalWindow.style.display = 'flex';

            const likeBtnElem = document.querySelector('#likeBtn');
            let likeCountElem = document.querySelector(".like_count")


            const isLike = function() {

                console.log(selectedGameNm);
                fetch(`/game/like/${selectedGameNm}`)
                    .then(res => res.json())
                    .then((data) => {
                        console.log(data)
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

            isLike();

            const offLike = () => {
                if(likeBtnElem) {
                    likeBtnElem.classList.remove('fas');
                    likeBtnElem.classList.add('far');
                }
            }

            const onLike = () => {
                if(likeBtnElem) {
                    likeBtnElem.classList.remove('far');
                    likeBtnElem.classList.add('fas');
                }
            }

            const dataElem = document.querySelector("#data");

            likeBtnElem.addEventListener('click', (e) => {
                if (dataElem.dataset.iuser <= 0) {
                    alert("로그인 해주세요.");
                    return;
                }

                if(e.target.classList.contains('far')) {
                    const param = {gameNm : selectedGameNm,
                        'iuser' : dataElem.dataset.iuser};

                    fetch('/game/like', {
                        'method': 'post',
                        'headers': { 'Content-Type': 'application/json' },
                        'body': JSON.stringify(param)
                    })
                        .then(res => res.json())
                        .then(data => {
                            console.log(data);
                            console.log("ins like gameNm : " + selectedGameNm)
                            onLike();
                            isLike(selectedGameNm);
                        })
                } else  {
                    fetch(`/game/like/${selectedGameNm}`, {
                        'method': 'delete',
                        'headers': { 'Content-Type': 'application/json' },
                    }).then(res => res.json())
                        .then(data => {
                            console.log(data);
                            console.log("del like gameNm : " + selectedGameNm);
                            offLike();
                            isLike(selectedGameNm);
                        });
                }
            })

            let gameCmtFrmElem = document.querySelector("#gameCmtFrm");

            if(dataElem.dataset.iuser <= 0) {
                gameCmtFrmElem.style.display = 'none';
            }

            let insCmt = function () {
                if (gameCmtFrmElem) {
                    gameCmtFrmElem.addEventListener("submit", (e) => {
                        e.preventDefault();
                    });

                    gameCmtFrmElem.cmt_submit.addEventListener('click', () => {
                        let cmtVal = gameCmtFrmElem.ctnt.value;
                        if (cmtVal.length === 0) {
                            alert("내용을 입력해 주세요.");
                        } else if (cmtVal.includes("<") || cmtVal.includes(">")) {
                            alert("내용에 < 혹은 >를 사용하실 수 없습니다.");
                        } else {
                            insGameCmtAjax(cmtVal);
                            // location.href="/board/detail?iboard="+ iboard;
                        }
                    });
                    // const item = {
                    //     icmt: data.result,
                    //     iuser: parseInt(dataElem.dataset.iuser),
                    //     ctnt: gameCmtFrmElem.ctnt.value,
                    // }
                    let insGameCmtAjax = (val) => {


                        console.log("ins cmt gameNm : " + selectedGameNm)
                        let param = {
                            gameNm: selectedGameNm,
                            iuser: dataElem.dataset.iuser,
                            ctnt: val
                        };
                        console.log(param);
                        fetch('/game', {
                            'method': 'POST',
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
                                gameCmtListElem.innerText = '';
                                gameCmtListElem.ctnt = null;
                                gameCmtFrmElem.ctnt.value = null;
                                getCmtList(selectedGameNm);

                            }).catch(e => {
                            console.log(e);
                        })
                    }
                }
            }
            insCmt();

            let gameNm;

            const getCmtList = (selectedGameNm) => {
                if (document.querySelector("table")) {
                    document.querySelector("table").remove();
                }
                gameNm = selectedGameNm;
                fetch(`/game/${gameNm}`)
                    .then(res => {
                        return res.json();
                    }).then(data => {
                    console.log(data);
                    gameCmtListElem.innerText = '';
                    // gameCmtListElem.ctnt = null;
                    // gameCmtFrmElem.ctnt.value = null;
                    setCmtList(data);
                }).catch(e => {
                    console.log(e);
                });
            }

            const setCmtList = (list) => {

                if (list.length == 0) {
                    gameCmtListElem.innerText = '유저 평가가 없습니다.';
                    return;
                }
                let table = document.createElement('table');
                table.classList.add("table-striped");
                table.innerHTML = `
        <tr>
            <th style="width: 60%">내용</th>
            <th style="width: 20%">작성자</th>
            <th style="width: 20%"></th>
        </tr>
        `
                list.forEach(item => {
                    makeTr(table, item);
                });
                gameCmtListElem.appendChild(table);
            }

            const makeTr = (table, item) => {
                const tr = document.createElement('tr');

                tr.innerHTML = `
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
                        const tdCell = tdArr[0];//댓글 내용

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
                            fetch('/game', {
                                'method': 'put',
                                'headers': {'Content-Type': 'application/json'},
                                'body': JSON.stringify(param)
                            })
                                .then(data => {
                                    console.log(data);
                                    tdCell.innerText = modInput.value;
                                    item.ctnt = modInput.value;
                                    gameCmtFrmElem.ctnt.value = null;
                                    removeCancelBtn();
                                })
                                .catch(data => {
                                    alert("평가 수정에 실패했습니다.");
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
                        if (confirm("나의 평가를 삭제하시겠습니까?")) {
                            delCmt(item.icmt, tr);
                            // location.href='/board/detail?iboard='+iboard;
                        }
                    });

                    td.appendChild(modBtn);
                    td.appendChild(delBtn);
                }
                table.appendChild(tr);
            }


            const delCmt = (icmt, tr) => {
                fetch(`/game/${icmt}`,
                    {
                        'method': 'delete',
                        'headers': {'Content-Type': 'application/json'}
                    }).then(res => res.json())
                    .then(data => {
                        console.log(data.result);
                        const tableElem = document.querySelector('table');
                        tableElem.remove();
                        gameCmtFrmElem.ctnt.value = null;
                        getCmtList(selectedGameNm);
                    }).catch(e => {
                    console.log(e)
                });
            }

            modalXBtn.addEventListener('click', () => {
                modalWindow.style.display = 'none';
                gameCmtFrmElem.ctnt = null;
            })
            window.addEventListener("keyup", (e) => {
                if (modalWindow.style.display === "flex" && e.key === "Escape") {
                    modalWindow.style.display = "none"
                    gameCmtFrmElem.ctnt = null;
                }
            })
        }
    })
})

const setCmtList = (list) => {
    if (list.length === 0) {
        myLike.innerHTML = '<span>아직 좋아요를 누르지 않았어요.</span>';
        return;
    }
    const table = makeTable();
    myLike.appendChild(table);

    list.forEach(item => {
        const tr = makeTr(item);
        table.appendChild(tr);
    });
}


const makeTable = () => {
    const table = document.createElement('table');
    myLike.querySelector("table").remove();
    myLike.innerHTML = '';
    table.innerHTML = `
            <tr style="background-color: lightgray;">
                <th>제목</th>
                <th>작성 일시</th>
            </tr>`;
    // table.classList.add("table");
    return table;
}

const makeTr = item => {
    console.log(item);
    const tr = document.createElement('tr');
    tr.innerHTML = `
            <td style="border-collapse: collapse; border: 1px solid lightgrey;">${item.nm}</td>
            <td style="border-collapse: collapse; border: 1px solid lightgrey;">${item.rdt}</td>
    `;
    const td = document.createElement('td');
    tr.appendChild(td);
    return tr;
}
