{

    let optionVal;

    function changeVal(val) {
        let optionElem = document.querySelector("select");

        console.log(val);
        if (val == 1) {
            location.href = "/user/mypage/mylike?category=1";
            // allLikeList()
        } else if (val == 2) {
            optionVal = optionElem.options[optionElem.selectedIndex].value;
            console.log(optionVal);
            location.href = "/user/mypage/mylike?category=" + val;
            webtoonLikeList();
            optionVal = val;
        } else if (val == 3) {
            optionVal = optionElem.options[optionElem.selectedIndex].value;
            console.log(optionVal);
            location.href = "/user/mypage/mylike?category=" + val;
            gameLikeList();
            optionVal = val;

        } else if (val == 4) {
            optionVal = optionElem.options[optionElem.selectedIndex].value;
            console.log(optionVal);
            location.href = "/user/mypage/mylike?category=" + val;
            boardLikeList();
            optionVal = val;
        }

    }

    const myLike = document.querySelector(".myLike");

    const dataElem = document.querySelector("#data");

    let gameCmtListElem = document.querySelector(".gameCmtList");
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
            setLikeList(data)
            let likeListElem = document.querySelectorAll("tbody > tr")
            console.log(likeListElem)
            likeListLink(likeListElem);
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
            setLikeList(data)
            let likeListElem = document.querySelectorAll("tbody > tr")
            console.log(likeListElem)
            likeListLink(likeListElem);
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
            setLikeList(data)
            let likeListElem = document.querySelectorAll("tbody > tr")
            for (let i = 0; i < likeListElem.length; i++) {
                likeListElem[i].setAttribute("data-iboard", data[i].iboard);
            }
            console.log(likeListElem)
            likeListLink(likeListElem);
        }).catch(e => {
            console.log(e);
        })
    }

    let likeAllListElem = document.querySelectorAll(".likeAllList")

    likeListLink(likeAllListElem);

    let webtoonNmLikeList = [];

    fetch(`/mypage/like/webtoon`)
        .then(res => {
            return res.json();
        }).then((data) => {
        console.log(data)
        data.forEach(function (item) {
            webtoonNmLikeList.push(item.nm);
        })
        console.log("webtoonNmLikeList : " + webtoonNmLikeList);
    }).catch(e => {
        console.log(e);
    })

    let distinctNmParam;

    function likeListLink(list) {
        list.forEach(function (item) {
            item.addEventListener("click", function() {

                if (optionVal == 2) {

                    distinctNmParam = 1;

                    let selectedWebtoonNm = item.childNodes[1].textContent;
                    webtoonNm = selectedWebtoonNm;
                    console.log(selectedWebtoonNm);
                    const modalWindow = document.querySelector("#modal-like")
                    const modalXBtn = document.querySelector("#close-area")

                    modalWindow.style.display = 'flex';

                    document.querySelector(".modalTitle").textContent = "오늘의 웹툰";

                    getloadWebtoonInfo();

                    getWtCmtList(selectedWebtoonNm);

                    isLike();

                    likeBtnOperation();


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
                    return;
                }

                let selectedGameNm = item.childNodes[1].textContent;


                if (item.dataset.iboard > 0) {
                    location.href = `/board/detail?iboard=${item.dataset.iboard}`;
                } else if (webtoonNmLikeList.includes(selectedGameNm)) {
                    distinctNmParam = 1;
                    let selectedWebtoonNm = selectedGameNm;
                    webtoonNm = selectedWebtoonNm;
                    console.log(selectedWebtoonNm);
                    const modalWindow = document.querySelector("#modal-like")
                    const modalXBtn = document.querySelector("#close-area")

                    modalWindow.style.display = 'flex';

                    document.querySelector(".modalTitle").textContent = "오늘의 웹툰";


                    getloadWebtoonInfo();

                    getWtCmtList(selectedWebtoonNm);

                    isLike();

                    likeBtnOperation();

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
                } else {
                    distinctNmParam = 2;
                    gameNm = selectedGameNm;
                    console.log(selectedGameNm);
                    const modalWindow = document.querySelector("#modal-like")
                    const modalXBtn = document.querySelector("#close-area")

                    modalWindow.style.display = 'flex';

                    document.querySelector(".modalTitle").textContent = "오늘의 게임";

                    const likeBtnElem = document.querySelector('#likeBtn');
                    let likeCountElem = document.querySelector(".like_count")

                    getMrloadGameInfo();

                    getCmtList(selectedGameNm);

                    const isLike = function () {

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


                    likeBtnElem.addEventListener('click', (e) => {
                        if (dataElem.dataset.iuser <= 0) {
                            alert("로그인 해주세요.");
                            return;
                        }

                        if (e.target.classList.contains('far')) {
                            const param = {
                                gameNm: selectedGameNm,
                                'iuser': dataElem.dataset.iuser
                            };

                            fetch('/game/like', {
                                'method': 'post',
                                'headers': {'Content-Type': 'application/json'},
                                'body': JSON.stringify(param)
                            })
                                .then(res => res.json())
                                .then(data => {
                                    console.log(data);
                                    console.log("ins like gameNm : " + selectedGameNm)
                                    onLike();
                                    isLike(selectedGameNm);
                                })
                        } else {
                            fetch(`/game/like/${selectedGameNm}`, {
                                'method': 'delete',
                                'headers': {'Content-Type': 'application/json'},
                            }).then(res => res.json())
                                .then(data => {
                                    console.log(data);
                                    console.log("del like gameNm : " + selectedGameNm);
                                    offLike();
                                    isLike(selectedGameNm);
                                });
                        }
                    })

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
    }

    let gameNm;
    let webtoonNm;

    function getloadWebtoonInfo() {
        return new Promise(function (resolve, reject) {
            fetch("/mypage/like/webtoonInfo"
                ).then((res) => {
                return res.json();
            }).then((data) => {
                data.forEach(function (item) {

                    if (item.nm == webtoonNm) {
                        document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${item.link}" target="_blank">` + webtoonNm + " 어때요?" + "</a>"
                        document.querySelector(".selected-img").innerHTML = `<img src=${item.img}>`

                        // getCmtList(gameNm);

                        resolve();
                    }
                })
            }).catch((err) => {
                console.log(err);

                reject();
            });
        });
    }

    //이미지 불러오기
    function getMrloadGameInfo() {
        return new Promise(function (resolve, reject) {
            fetch("/board/mecarankingjson").then((res) => {
                return res.json();
            }).then((data) => {
                data.forEach(function (item) {

                    if (item.gameNm == gameNm) {
                        console.log("gameNm : " + item.gameNm);
                        document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${item.selLink}" target="_blank">` + gameNm + " 어때요?" + "</a>"
                        document.querySelector(".selected-img").innerHTML = `<img src=${item.imgsrc}>`

                        // getCmtList(gameNm);
                        resolve();
                        return;
                    } else getPfloadGameInfo();
                    return;
                })
            }).catch((err) => {
                console.log(err);
                reject();
            });
        });
    }

    function getPfloadGameInfo() {
        return new Promise(function (resolve, reject) {
            fetch("/board/platformrankingjson"
                ).then((res) => {
                return res.json();
            }).then((data) => {
                data.forEach(function (item) {

                    if (item.gameNm == gameNm) {
                        document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${item.selLink}" target="_blank">` + gameNm + " 어때요?" + "</a>"
                        document.querySelector(".selected-img").innerHTML = `<img src=${item.imgsrc}>`

                        // getCmtList(gameNm);

                        resolve();
                    }
                })
            }).catch((err) => {
                console.log(err);

                reject();
            });
        });
    }

    let gameCmtFrmElem = document.querySelector("#gameCmtFrm");

    if (dataElem.dataset.iuser <= 0) {
        gameCmtFrmElem.style.display = 'none';
    }

    const getCmtList = (gameNm) => {
        if (optionVal === 2) {
            fetch(`/board/review/${gameNm}`)
                .then(res => {
                    return res.json();
                }).then(data => {
                console.log(data);
                gameCmtListElem.innerText = '';
                // gameCmtListElem.ctnt = null;
                // gameCmtFrmElem.ctnt.value = null;
                setCmtList(data);
                return;
            }).catch(e => {
                console.log(e);
            });

        }
        // if (document.querySelector("table")) {
        //     document.querySelector("table").remove();
        // }
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
                gameCmtFrmElem.ctnt.value = null;
                getCmtList(gameNm);
            }).catch(e => {
            console.log(e)
        });
    }

    let insCmt = function () {
        console.log("inscmt ready")
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
                    if(distinctNmParam == 1) {
                        insWebtoonCmtAjax(cmtVal, webtoonNm);
                    } else{
                        insGameCmtAjax(cmtVal);
                    }
                    // location.href="/board/detail?iboard="+ iboard;
                }
            });
            // const item = {
            //     icmt: data.result,
            //     iuser: parseInt(dataElem.dataset.iuser),
            //     ctnt: gameCmtFrmElem.ctnt.value,
            // }
            let insGameCmtAjax = (val) => {


                console.log("ins cmt gameNm : " + gameNm)
                let param = {
                    gameNm: gameNm,
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

                        gameCmtListElem.innerText = '';
                        gameCmtListElem.ctnt = null;
                        gameCmtFrmElem.ctnt.value = null;
                        getCmtList(gameNm);

                    }).catch(e => {
                    console.log(e);
                })
            }
        }
    }

    insCmt();

    const setLikeList = (list) => {
        if (list.length === 0) {
            myLike.innerHTML = '<span>아직 좋아요를 누르지 않았어요.</span>';
            return;
        }
        const table = makeLikeTable();
        myLike.appendChild(table);

        list.forEach(item => {
            const tr = makeLikeTr(item);
            table.querySelector("tbody").appendChild(tr);
        });
    }


    const makeLikeTable = () => {
        const table = document.createElement('table');
        table.classList.add("table");
        table.classList.add("table-hover");
        myLike.querySelector("table").remove();
        myLike.innerHTML = '';
        table.innerHTML = `
            <thead>
            <tr style="background-color: lightgray;">
                <th>제목</th>
                <th>작성 일시</th>
            </tr>
            </thead>
            <tbody></tbody>
`;

        return table;
    }

    const makeLikeTr = item => {
        console.log(item);

        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${item.nm}</td>
            <td>${item.rdt}</td>
    `;

        return tr;
    }

    const getWtCmtList = (selectedWebtoonNm) => {
        fetch(`/board/review/${selectedWebtoonNm}`)
            .then(res => {
                return res.json();
            }).then(data => {
            console.log(data);
            gameCmtListElem.innerText = '';
            // gameCmtListElem.ctnt = null;
            // gameCmtFrmElem.ctnt.value = null;
            setWtCmtList(data);
            return;
        }).catch(e => {
            console.log(e);
        });

        // if (document.querySelector("table")) {
        //     document.querySelector("table").remove();
        // }
    }


    const setWtCmtList = (list) => {

        if (list.length == 0) {
            gameCmtListElem.innerText = '이 웹툰의 리뷰가 아직 없습니다.';
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
            makeWtTr(table, item);
        });
        gameCmtListElem.appendChild(table);
    }

    const makeWtTr = (table, item) => {
        const tr = document.createElement('tr');

        tr.innerHTML = `
        <td>${item.ctnt}</td>
        <td>${item.nickname}</td>
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
                        rnum: item.rnum,
                        ctnt: modInput.value
                    }
                    fetch('/board/review', {
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
                    delWtCmt(item.rnum, tr);
                    // location.href='/board/detail?iboard='+iboard;
                }
            });

            td.appendChild(modBtn);
            td.appendChild(delBtn);
        }
        table.appendChild(tr);
    }


    const delWtCmt = (rnum, tr) => {
        let selectedWebtoonNm = webtoonNm;
        fetch(`/board/review/${rnum}`,
            {
                'method': 'delete',
                'headers': {'Content-Type': 'application/json'}
            }).then(res => res.json())
            .then(data => {
                console.log(data.result);
                gameCmtFrmElem.ctnt.value = null;
                getWtCmtList(selectedWebtoonNm);
            }).catch(e => {
            console.log(e)
        });
    }

    // let insWtCmt = function () {
    //     console.log("insWtcmt ready")
    //     if (gameCmtFrmElem) {
    //         gameCmtFrmElem.addEventListener("submit", (e) => {
    //             e.preventDefault();
    //         });
    //         gameCmtFrmElem.cmt_submit.addEventListener('click', () => {
    //             console.log("inswtcmt");
    //             let cmtVal = gameCmtFrmElem.ctnt.value;
    //             selectedWebtoonNm = webtoonNm;
    //             if (cmtVal.length === 0) {
    //                 alert("내용을 입력해 주세요.");
    //             } else if (cmtVal.includes("<") || cmtVal.includes(">")) {
    //                 alert("내용에 < 혹은 >를 사용하실 수 없습니다.");
    //             } else {
    //                 insWebtoonCmtAjax(cmtVal, selectedWebtoonNm);
    //                 // location.href="/board/detail?iboard="+ iboard;
    //             }
    //         });
            // const item = {
            //     icmt: data.result,
            //     iuser: parseInt(dataElem.dataset.iuser),
            //     ctnt: gameCmtFrmElem.ctnt.value,
            // }
            function insWebtoonCmtAjax (val, selectedWebtoonNm) {


                console.log("ins cmt webtoonNm : " + selectedWebtoonNm)
                let param = {
                    nm: selectedWebtoonNm,
                    iuser: dataElem.dataset.iuser,
                    ctnt: val
                };
                console.log(param);
                fetch('/board/review', {
                    'method': 'POST',
                    'headers': {'Content-Type': 'application/json'},
                    'body': JSON.stringify(param),
                })
                    .then(res => res.json())
                    .then(data => {
                        console.log(data);

                        gameCmtListElem.innerText = '';
                        gameCmtListElem.ctnt = null;
                        gameCmtFrmElem.ctnt.value = null;
                        getWtCmtList(selectedWebtoonNm);

                    }).catch(e => {
                    console.log(e);
                })
            }
        // }
    // }

    const likeBtnElem = document.querySelector('#likeBtn');
    const isLike = function () {
        let likeCountElem = document.querySelector(".like_count")
        let selectedWebtoonNm = webtoonNm;
        console.log(selectedWebtoonNm);
        fetch(`/webtoon/fav/${selectedWebtoonNm}`)
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
                likeCountElem.innerHTML = `<div style="margin-top: 5px; color: lightpink; font-weight: bolder">${data.favcount}명의 유저가 좋아합니다.</div>`
            })
            .catch((e) => {
                console.log(e);
            });
    }

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

    function likeBtnOperation() {
        likeBtnElem.addEventListener('click', (e) => {
            let selectedWebtoonNm = webtoonNm;
            if (dataElem.dataset.iuser <= 0) {
                alert("로그인 해주세요.");
                return;
            }

            if (e.target.classList.contains('far')) {
                const param = {
                    'nm': selectedWebtoonNm,
                    'iuser': dataElem.dataset.iuser
                };

                fetch('/webtoon/fav', {
                    'method': 'post',
                    'headers': {'Content-Type': 'application/json'},
                    'body': JSON.stringify(param)
                })
                    .then(res => res.json())
                    .then(data => {
                        console.log(data);
                        console.log("ins like webtoonNm : " + selectedWebtoonNm)
                        onLike();
                        isLike(selectedWebtoonNm);
                    })
            } else {
                fetch(`/webtoon/fav/${selectedWebtoonNm}`, {
                    'method': 'delete',
                    'headers': {'Content-Type': 'application/json'},
                }).then(res => res.json())
                    .then(data => {
                        console.log(data);
                        console.log("del like webtoonNm : " + selectedWebtoonNm);
                        offLike();
                        isLike(selectedWebtoonNm);
                    });
            }
        })
    }

}