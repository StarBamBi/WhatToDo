{
    // let rtBtns = document.querySelectorAll(".rtBtn");
    let ppBtns = document.querySelectorAll(".ppBtn");
    let kdBtns = document.querySelectorAll(".kdBtn");
    let pfBtns = document.querySelectorAll(".pfBtn");
    let gameCmtListElem = document.querySelector(".gameCmtList");


    //
    // function rtClickedRmv() {
    //     for (let i = 0; i < rtBtns.length; i++) {
    //         rtBtns[i].classList.remove("clicked");
    //     }
    // }

    function ppClickedRmv() {
        for (let i = 0; i < ppBtns.length; i++) {
            ppBtns[i].classList.remove("clicked");
        }
    }

    function kdClickedRmv() {
        for (let i = 0; i < kdBtns.length; i++) {
            kdBtns[i].classList.remove("clicked");
        }
    }

    function pfClickedRmv() {
        for (let i = 0; i < pfBtns.length; i++) {
            pfBtns[i].classList.remove("clicked");
        }
    }

    // for (let i = 0; i < rtBtns.length; i++) {
    //     rtBtns[i].addEventListener("click", function (e) {
    //         e.preventDefault();
    //         if (e.target.classList.contains("clicked")) {
    //             rtClickedRmv();
    //         } else {
    //             rtClickedRmv();
    //             rtBtns[i].classList.add("clicked");
    //         }
    //     })
    // }

    ppBtns.forEach(function (item) {
        item.addEventListener('click', function (e) {
            e.preventDefault();
            kdClickedRmv();
            pfClickedRmv();
            if (e.target.classList.contains("clicked")) {
                ppClickedRmv();
            } else {
                ppClickedRmv();
                item.classList.add("clicked");
            }
        })
    });
    kdBtns.forEach(function (item) {
        item.addEventListener('click', function (e) {
            e.preventDefault();
            ppClickedRmv();
            pfClickedRmv();
            if (e.target.classList.contains("clicked")) {
                kdClickedRmv();
            } else {
                kdClickedRmv();
                item.classList.add("clicked");
            }
        })
    });
    pfBtns.forEach(function (item) {
        item.addEventListener('click', function (e) {
            e.preventDefault();
            ppClickedRmv();
            kdClickedRmv();
            if (e.target.classList.contains("clicked")) {
                pfClickedRmv();
            } else {
                pfClickedRmv();
                item.classList.add("clicked");
            }

        })
    });


    // gameBtnFrm = document.querySelector('#gameBtnFrm');
    // if (gameBtnFrm) {
    //     let aaa = gameBtnFrm.querySelector('#aaa')
    //     aaa.addEventListener('click', function (e) {
    //         e.preventDefault();
    //         aaa.classList.add('plusBtn');
    //     })
    // }

    let randomMobileJson = null;
    // 뭐하Gee 버튼 관련
    const modalWindow = document.getElementById("modal")
    const modalXBtn = document.querySelector(".close-area")

    //뭐하지 버튼
    funBtn = document.querySelector('.fun-btn');
    ppBtn = document.querySelector('#ppRanBtn');
    kdBtn = document.querySelector('#kdRanBtn');
    pfBtn = document.querySelector('#pfRanBtn');

    ppBtn.addEventListener("click", function async() {

        modalWindow.style.display = 'flex';

        if (ppBtns[0].classList.contains("clicked")) {
            mecaData.getMrTopRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
            });
        } else if (ppBtns[1].classList.contains("clicked")) {
            mecaData.getMrGreatRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
            });
        } else if (ppBtns[2].classList.contains("clicked")) {
            mecaData.getMrGoodRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
            });
        } else {
            mecaData.getMrAllRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
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
    });

    kdBtn.addEventListener("click", function async() {

        if (kdBtns[0].classList.contains("clicked")) {
            genreData.getRpgRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
            });
        } else if (kdBtns[1].classList.contains("clicked")) {
            genreData.getFpsRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
            });
        } else if (kdBtns[2].classList.contains("clicked")) {
            genreData.getSportsRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
            });
        } else if (kdBtns[3].classList.contains("clicked")) {
            genreData.getActionRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList();
                }).catch(function (e) {
                console.log(e);
            });
        } else if (kdBtns[4].classList.contains("clicked")) {
            genreData.getStrRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
            });
        } else if (kdBtns[5].classList.contains("clicked")) {
            genreData.getOthersRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
            });
        } else {
            pfData.getAllPfRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
            });
        }

        modalWindow.style.display = 'flex';
        modalXBtn.addEventListener('click', () => {
            modalWindow.style.display = 'none';
            gameCmtFrmElem.ctnt.value = null;
        })
        window.addEventListener("keyup", (e) => {
            if (modalWindow.style.display === "flex" && e.key === "Escape") {
                modalWindow.style.display = "none"
                gameCmtFrmElem.ctnt = null;
            }
        })
    })

    pfBtn.addEventListener("click", function async() {

        if (pfBtns[0].classList.contains("clicked")) {
            pfData.getMobileRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
            });
        } else if (pfBtns[1].classList.contains("clicked")) {
            pfData.getPcRandomGame()
                .then(function () {
                    getCmtList(selectedGameNm);
                    isLike(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
            });
        } else if (pfBtns[2].classList.contains("clicked")) {
            pfData.getStRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
            });
        } else {
            pfData.getAllPfRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
            });
        }

        modalWindow.style.display = 'flex';
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
    })

    funBtn.addEventListener('click', function async() {

        // if (pfBtns[0].classList.contains("clicked")) {
        //     if (kdBtns[0].classList.contains("clicked")) {
        //
        //     }
        // }
        //
        // if (ppBtns[0].classList.contains("clicked")) {
        //     mecaData.getMrTopRandomGame();
        // } else if (ppBtns[1].classList.contains("clicked")) {
        //     mecaData.getMrGreatRandomGame();
        // } else if (ppBtns[2].classList.contains("clicked")) {
        //     mecaData.getMrGoodRandomGame();
        // } else mecaData.getMrAllRandomGame();

        let randomNum = Math.floor(Math.random() * 2)
        console.log(randomNum)
        if (randomNum === 1) {
            mecaData.getMrAllRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
            });
        } else {
            pfData.getAllPfRandomGame()
                .then(function () {
                    isLike(selectedGameNm);
                    getCmtList(selectedGameNm);
                }).catch(function (e) {
                console.log(e);
            });
        }

        modalWindow.style.display = 'flex';
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
    })

    let selectedGameNm;
    /* 랜덤값 도출 */
    //메카 순위별 랜덤함수 객체
    let mecaData = {
        //메카순위 1~50
        getMrAllRandomGame: function () {
            return new Promise(function (resolve, reject) {
                fetch("/board/mecarankingjson").then((res) => {
                    return res.json();
                }).then((data) => {
                    let mrdata = [];
                    for (let i = 0; i < 50; i++) {
                        mrdata.push(data[i])
                    }
                    let mrRN = Math.floor(Math.random() * 50)
                    // console.log(mrRN);
                    console.log(JSON.stringify(mrdata[mrRN].gameNm));
                    // let rGame = JSON.stringify(mrdata[mrRN])
                    //     console.log(rGame);
                    selectedGameNm = JSON.stringify(mrdata[mrRN].gameNm).replace("\"", "").replace("\"", "")
                    document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${mrdata[mrRN].selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                    document.querySelector(".selected-img").innerHTML = `<img src=${mrdata[mrRN].imgsrc}>`

                    resolve();

                }).catch((err) => {
                    console.log(err);
                    reject();
                });
            });
        },

        //메카 1~10위
        getMrTopRandomGame: function () {
            return new Promise(function (resolve, reject) {
                fetch("/board/mecarankingjson").then((res) => {
                    return res.json();
                }).then((data) => {
                    let mrdata = [];
                    for (let i = 0; i < 10; i++) {
                        mrdata.push(data[i])
                    }
                    let mrRN = Math.floor(Math.random() * 10)
                    // console.log(mrRN);
                    console.log(JSON.stringify(mrdata[mrRN].gameNm));
                    // let rGame = JSON.stringify(mrdata[mrRN])
                    //     console.log(rGame);
                    selectedGameNm = JSON.stringify(mrdata[mrRN].gameNm).replace("\"", "").replace("\"", "")
                    document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${mrdata[mrRN].selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                    document.querySelector(".selected-img").innerHTML = `<img src=${mrdata[mrRN].imgsrc}>`
                    resolve();
                }).catch((err) => {
                    console.log(err);
                    reject();
                });
            });
        },

        //메카 11~30위
        getMrGreatRandomGame: function () {   // 게임순위 데이터
            return new Promise(function (resolve, reject) {
                fetch("/board/mecarankingjson").then((res) => {
                    return res.json();
                }).then((data) => {
                    let mrdata = [];
                    for (let i = 10; i < 30; i++) {
                        mrdata.push(data[i])
                    }
                    let mrRN = Math.floor(Math.random() * 20)

                    selectedGameNm = JSON.stringify(mrdata[mrRN].gameNm).replace("\"", "").replace("\"", "")
                    document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${mrdata[mrRN].selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                    document.querySelector(".selected-img").innerHTML = `<img src=${mrdata[mrRN].imgsrc}>`

                    resolve();
                }).catch((err) => {
                    console.log(err);

                    reject();
                });
            });
        },

        //메카 31~50위
        getMrGoodRandomGame: function () {   // 게임순위 데이터
            return new Promise(function (resolve, reject) {
                fetch("/board/mecarankingjson").then((res) => {
                    return res.json();
                }).then((data) => {
                    let mrdata = [];
                    for (let i = 30; i < 50; i++) {
                        mrdata.push(data[i])
                    }
                    let mrRN = Math.floor(Math.random() * 20)

                    selectedGameNm = JSON.stringify(mrdata[mrRN].gameNm).replace("\"", "").replace("\"", "")
                    document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${mrdata[mrRN].selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                    document.querySelector(".selected-img").innerHTML = `<img src=${mrdata[mrRN].imgsrc}>`

                    resolve();
                }).catch((err) => {
                    console.log(err);
                    reject();
                });
            });
        }
    }

    //장르별 랜덤 함수 배열
    let genreData = {
        //rpg 랜덤
        getRpgRandomGame: function () {
            return new Promise(function (resolve, reject) {
                fetch("/board/platformrankingjson"
                    ).then((res) => {
                    return res.json();
                }).then((data) => {
                    let mdata = [];
                    for (let i = 0; i < Object.keys(data).length; i++) {
                        mdata.push(data[i])
                    }
                    let rpgdata = [];
                    mdata.forEach((item) => {
                        if (item.genre.includes("롤플레잉") || item.genre.includes("RPG")) {
                            rpgdata.push(item)
                        }
                    })
                    let randomNm = Math.floor(Math.random() * rpgdata.length)

                    selectedGameNm = JSON.stringify(rpgdata[randomNm].gameNm).replace("\"", "").replace("\"", "")
                    document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${rpgdata[randomNm].selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                    document.querySelector(".selected-img").innerHTML = `<img src=${rpgdata[randomNm].imgsrc}>`

                    resolve();
                }).catch((err) => {
                    console.log(err);

                    reject();
                });
            })
        },

        //FPS랜덤
        getFpsRandomGame: function () {
            return new Promise(function (resolve, reject) {
                fetch("/board/platformrankingjson").then((res) => {
                    return res.json();
                }).then((data) => {
                    let mdata = [];
                    for (let i = 0; i < Object.keys(data).length; i++) {
                        mdata.push(data[i])
                    }
                    let fpsdata = [];
                    mdata.forEach((item) => {
                        if (item.genre.includes("FPS") || item.genre.includes("슈팅")) {
                            fpsdata.push(item)
                        }
                    })
                    let randomNm = Math.floor(Math.random() * fpsdata.length)

                    selectedGameNm = JSON.stringify(fpsdata[randomNm].gameNm).replace("\"", "").replace("\"", "")
                    document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${fpsdata[randomNm].selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                    document.querySelector(".selected-img").innerHTML = `<img src=${fpsdata[randomNm].imgsrc}>`

                    resolve();
                }).catch((err) => {
                    console.log(err);

                    reject();
                });
            });
        }

        ,

        //액션랜덤
        getActionRandomGame: function () {
            return new Promise(function (resolve, reject) {
                fetch("/board/platformrankingjson").then((res) => {
                    return res.json();
                }).then((data) => {
                    let mdata = [];
                    for (let i = 0; i < Object.keys(data).length; i++) {
                        mdata.push(data[i])
                    }
                    let actiondata = [];
                    mdata.forEach((item) => {
                        if (item.genre.includes("액션")) {
                            actiondata.push(item)
                        }
                    })
                    let randomNm = Math.floor(Math.random() * actiondata.length)

                    selectedGameNm = JSON.stringify(actiondata[randomNm].gameNm).replace("\"", "").replace("\"", "")
                    document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${actiondata[randomNm].selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                    document.querySelector(".selected-img").innerHTML = `<img src=${actiondata[randomNm].imgsrc}>`

                    resolve();
                }).catch((err) => {
                    console.log(err);

                    reject();
                });
            });
        }

        ,

        //전략 랜덤
        getStrRandomGame: function () {
            return new Promise(function (resolve, reject) {
                fetch("/board/platformrankingjson").then((res) => {
                    return res.json();
                }).then((data) => {
                    let mdata = [];
                    for (let i = 0; i < Object.keys(data).length; i++) {
                        mdata.push(data[i])
                    }
                    let strdata = [];
                    mdata.forEach((item) => {
                        if (item.genre.includes("전략") || item.genre.includes("RTS")) {
                            strdata.push(item)
                        }
                    })
                    let randomNm = Math.floor(Math.random() * strdata.length)

                    selectedGameNm = JSON.stringify(strdata[randomNm].gameNm).replace("\"", "").replace("\"", "")
                    document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${strdata[randomNm].selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                    document.querySelector(".selected-img").innerHTML = `<img src=${strdata[randomNm].imgsrc}>`

                    resolve();
                }).catch((err) => {
                    console.log(err);
                    reject();
                });
            });
        }

        ,

        //스포츠,레이싱 랜덤
        getSportsRandomGame: function () {
            return new Promise(function (resolve, reject) {
                fetch("/board/platformrankingjson").then((res) => {
                    return res.json();
                }).then((data) => {
                    let mdata = [];
                    for (let i = 0; i < Object.keys(data).length; i++) {
                        mdata.push(data[i])
                    }
                    let sportsdata = [];
                    mdata.forEach((item) => {
                        if (item.genre.includes("스포츠") || item.genre.includes("자동차") || item.genre.includes("레이싱")) {
                            sportsdata.push(item)
                        }
                    })
                    let randomNm = Math.floor(Math.random() * sportsdata.length)

                    selectedGameNm = JSON.stringify(sportsdata[randomNm].gameNm).replace("\"", "").replace("\"", "")
                    document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${sportsdata[randomNm].selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                    document.querySelector(".selected-img").innerHTML = `<img src=${sportsdata[randomNm].imgsrc}>`

                    resolve();
                }).catch((err) => {
                    console.log(err);

                    reject();
                });
            });
        }

        ,

        //기타 랜덤
        getOthersRandomGame: function () {
            return new Promise(function (resolve, reject) {
                fetch("/board/platformrankingjson").then((res) => {
                    return res.json();
                }).then((data) => {
                    let mdata = [];
                    for (let i = 0; i < Object.keys(data).length; i++) {
                        mdata.push(data[i])
                    }

                    let othsData = mdata.filter((item) => !(item.genre.includes("전략") || item.genre.includes("RTS") || item.genre.includes("스포츠")
                        || item.genre.includes("자동차") || item.genre.includes("레이싱") || item.genre.includes("액션")
                        || item.genre.includes("FPS") || item.genre.includes("슈팅") || item.genre.includes("롤플레잉") || item.genre.includes("RPG")
                    ))
                    let randomNm = Math.floor(Math.random() * othsData.length)

                    selectedGameNm = JSON.stringify(mdata[randomNm].gameNm).replace("\"", "").replace("\"", "")
                    document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${mdata[randomNm].selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                    document.querySelector(".selected-img").innerHTML = `<img src=${mdata[randomNm].imgsrc}>`

                    resolve();
                }).catch((err) => {
                    console.log(err);

                    reject();
                });
            });
        }
    }

    //플랫폼 랜덤 함수 배열
    let pfData = {
        getAllPfRandomGame: function () {
            return new Promise(function (resolve, reject) {
                fetch("/board/platformrankingjson"
                    ).then((res) => {
                    return res.json();
                }).then((data) => {
                    let mdata = [];
                    for (let i = 0; i < 120; i++) {
                        mdata.push(data[i])
                    }
                    let randomNm = Math.floor(Math.random() * 120);

                    selectedGameNm = JSON.stringify(mdata[randomNm].gameNm).replace("\"", "").replace("\"", "")
                    document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${mdata[randomNm].selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                    document.querySelector(".selected-img").innerHTML = `<img src=${mdata[randomNm].imgsrc}>`

                    resolve();
                }).catch((err) => {
                    console.log(err);

                    reject();
                });
            });
        },
        // 모바일게임 랜덤
        getMobileRandomGame: function () {
            return new Promise(function (resolve, reject) {
                fetch("/board/platformrankingjson"
                    ).then((res) => {
                    return res.json();
                }).then((data) => {
                    let mdata = [];
                    for (let i = 0; i < 40; i++) {
                        mdata.push(data[i])
                    }
                    let randomNm = Math.floor(Math.random() * 40);

                    selectedGameNm = JSON.stringify(mdata[randomNm].gameNm).replace("\"", "").replace("\"", "")
                    document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${mdata[randomNm].selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                    document.querySelector(".selected-img").innerHTML = `<img src=${mdata[randomNm].imgsrc}>`

                    resolve();
                }).catch((err) => {
                    console.log(err);

                    reject();
                });
            });
        },

// pc온라인 랜덤
        getPcRandomGame: function () {
            return new Promise(function (resolve, reject) {
                fetch("/board/platformrankingjson").then((res) => {
                    return res.json();
                }).then((data) => {
                    let pdata = [];
                    for (let i = 40; i < 80; i++) {
                        pdata.push(data[i])
                    }
                    let randomNm = Math.floor(Math.random() * 40);

                    selectedGameNm = JSON.stringify(pdata[randomNm].gameNm).replace("\"", "").replace("\"", "")
                    document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${pdata[randomNm].selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                    document.querySelector(".selected-img").innerHTML = `<img src=${pdata[randomNm].imgsrc}>`

                    resolve();
                }).catch((err) => {
                    console.log(err);

                    reject();
                });
            });
        }

        ,

// 스팀게임 랜덤
        getStRandomGame: function () {
            return new Promise(function (resolve, reject) {
                fetch("/board/platformrankingjson").then((res) => {
                    return res.json();
                }).then((data) => {
                    let sdata = [];
                    for (let i = 80; i < 120; i++) {
                        sdata.push(data[i])
                    }
                    let randomNm = Math.floor(Math.random() * 40);

                    selectedGameNm = JSON.stringify(sdata[randomNm].gameNm).replace("\"", "").replace("\"", "")
                    document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${sdata[randomNm].selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                    document.querySelector(".selected-img").innerHTML = `<img src=${sdata[randomNm].imgsrc}>`

                    resolve();
                }).catch((err) => {
                    console.log(err);

                    reject();
                });
            });
        }
    }

    let dataElem = document.querySelector("#data");
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

                selectedGameNm = gameNm;
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

    const getTrLen = () => {
        const cmtListElem = document.querySelector('#cmt_list');
        const trArr = cmtListElem.querySelectorAll('table tr');
        return trArr.length;
    }

    let mecaThumbElems = document.querySelectorAll(".meca_thumb_img");
    let gameThumbElems = document.querySelectorAll(".game_thumb_img");

    mecaThumbElems.forEach(function (item) {
        item.addEventListener("click", () => {

            let selectedGameNm = item.parentNode.children[1].querySelector(".sel_gameNm").textContent;
            isLike(selectedGameNm);

            function getMrAllRandomGame() {
                return new Promise(function (resolve, reject) {
                    fetch("/board/mecarankingjson").then((res) => {
                        return res.json();
                    }).then((data) => {
                        data.forEach(function (item) {
                            if (item.gameNm == selectedGameNm) {

                                document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${item.selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                                document.querySelector(".selected-img").innerHTML = `<img src=${item.imgsrc}>`

                                getCmtList(selectedGameNm);

                                resolve();
                            } else {
                                return;
                            }
                        })
                    }).catch((err) => {
                        console.log(err);
                        reject();
                    });
                });
            }

            getMrAllRandomGame ();

            modalWindow.style.display = 'flex'
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
        })

    }
        )

    gameThumbElems.forEach(function (item) {
        item.addEventListener("click", () => {
            let selectedGameNm = item.parentNode.children[1].querySelector(".sel_gameNm").textContent;
            isLike(selectedGameNm);

            function getAllPfRandomGame() {
                return new Promise(function (resolve, reject) {
                    fetch("/board/platformrankingjson"
                        ).then((res) => {
                        return res.json();
                    }).then((data) => {
                        data.forEach(function (item) {

                            if (item.gameNm == selectedGameNm) {
                                document.querySelector(".modalContent").innerHTML = `<a class="text-important" href="${item.selLink}" target="_blank">` + selectedGameNm + " 어때요?" + "</a>"
                                document.querySelector(".selected-img").innerHTML = `<img src=${item.imgsrc}>`

                                getCmtList(selectedGameNm);

                                resolve();
                            }
                        })
                    }).catch((err) => {
                        console.log(err);

                        reject();
                    });
                });
            }

            getAllPfRandomGame();


            modalWindow.style.display = 'flex';
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
        })
    })

    //좋아요

    const likeBtnElem = document.querySelector('#likeBtn');
    let likeCountElem = document.querySelector(".like_count")


    const isLike = function(selectedGameNm) {
        gameNm = selectedGameNm
        console.log(gameNm);
        fetch(`/game/like/${gameNm}`)
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

    const offLike = () => {
        if(likeBtnElem) {
            likeBtnElem.classList.remove('fa-heart');
            likeBtnElem.classList.add('fa-heart-crack');
        }
    }

    const onLike = () => {
        if(likeBtnElem) {
            likeBtnElem.classList.remove('fa-heart-crack');
            likeBtnElem.classList.add('fa-heart');
        }
    }

    likeBtnElem.addEventListener('click', (e) => {
        if (dataElem.dataset.iuser <= 0) {
            alert("로그인 해주세요.");
            return;
        }

        if(e.target.classList.contains('fa-heart-crack')) {
            const param = {gameNm : gameNm,
                           'iuser' : dataElem.dataset.iuser};

            fetch('/game/like', {
                'method': 'post',
                'headers': { 'Content-Type': 'application/json' },
                'body': JSON.stringify(param)
            })
                .then(res => res.json())
                .then(data => {
                    console.log(data);
                    console.log("ins like gameNm : " + gameNm)
                    onLike();
                    isLike(gameNm);
                })
        } else  {
            fetch(`/game/like/${gameNm}`, {
                'method': 'delete',
                'headers': { 'Content-Type': 'application/json' },
            }).then(res => res.json())
                .then(data => {
                    console.log(data);
                    console.log("del like gameNm : " + gameNm);
                    offLike();
                    isLike(gameNm);
                });
        }
    })


}
