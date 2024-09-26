{
    let choiceBtnFrm = document.querySelector('#choiceBtnFrm');
    let displayResultDiv = document.querySelector('.display_result');
    let displayResultSpan = document.querySelector('.result_span');
    let funBtn = choiceBtnFrm.querySelector('.fun-btn');
    let dataList = document.querySelector('.data');
    let modalLink = document.querySelector('.modalLink');
    let modal = document.querySelector('#modal');
    let modalCloseBtn = document.querySelector('.close-area');


    /* common.js에서 가져온 버튼 클릭 관련 js */

    let rtBtns = document.querySelectorAll(".rtBtn");
    let ppBtns = document.querySelectorAll(".ppBtn");
    let kdBtns = document.querySelectorAll(".kdBtn");
    let thmBtns = document.querySelectorAll(".thmBtn");
    let pnBtns = document.querySelectorAll(".pnBtn");

    // 버튼 다시 클릭 시 버튼 해제
    function rtClickedRmv() {
        for (let i = 0; i < rtBtns.length; i++) {
            rtBtns[i].classList.remove("clicked");
        }
    }
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
    function thmClickedRmv() {
        for (let i = 0; i < thmBtns.length; i++) {
            thmBtns[i].classList.remove("clicked");
        }
    }
    function pnClickedRmv() {
        for (let i = 0; i < pnBtns.length; i++) {
            pnBtns[i].classList.remove("clicked");
        }
    }

    // 평가 버튼 값
    let rtBtnReturnNum = 0;
    rtBtns.forEach(function (item) {
        item.addEventListener('click', function (e) {
            e.preventDefault();
            switch (item.innerText) {
                case '띵작':
                    rtBtnReturnNum = 3;
                    break;
                case '수작':
                    rtBtnReturnNum = 2;
                    break;
                case '평작':
                    rtBtnReturnNum = 1;
                    break;
            }

            if (e.target.classList.contains("clicked")) {
                rtClickedRmv();
            } else {
                rtClickedRmv();
                item.classList.add("clicked");
            }
        })
    });

    // 인기도 버튼 값
    let ppBtnReturnNum = 0;
    ppBtns.forEach(function (item) {
        item.addEventListener('click', function (e) {
            e.preventDefault();
            switch (item.innerText) {
                case '많은':
                    ppBtnReturnNum = 3;
                    break;
                case '보통':
                    ppBtnReturnNum = 2;
                    break;
                case '적은':
                    ppBtnReturnNum = 1;
                    break;
            }

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
            if (e.target.classList.contains("clicked")) {
                kdClickedRmv();
            } else {
                kdClickedRmv();
                item.classList.add("clicked");
            }
        })
    });
    thmBtns.forEach(function (item) {
        item.addEventListener('click', function (e) {
            e.preventDefault();
            if (e.target.classList.contains("clicked")) {
                thmClickedRmv();
            } else {
                thmClickedRmv();
                item.classList.add("clicked");
            }

        })
    });
    pnBtns.forEach(function (item) {
        item.addEventListener('click', function (e) {
            e.preventDefault();
            if (e.target.classList.contains("clicked")) {
                pnClickedRmv();
            } else {
                pnClickedRmv();
                item.classList.add("clicked");
            }
        })
    });

    // 뭐하Gee 버튼 관련
    funBtn = document.querySelector('.fun-btn');
    funBtn.addEventListener('click', function (e) {
        e.preventDefault();
        let btnNumSum = rtBtnReturnNum + ppBtnReturnNum;
        console.log('btnNumSum:' + btnNumSum);
        console.log('rtBtnReturnNum : ' + rtBtnReturnNum);
        console.log('ppBtnReturnNum : ' + ppBtnReturnNum);
        /*let random = Math.floor(Math.random()*2)+1;
        console.log('random:' + random);
        switch (random) {
            case 1:
                getMecaRandomGame(mecaurl);
                break;
            case 2:
                getSteamRandomGame(steamurl);
                break;
        }*/
        if(btnNumSum>=4 && btnNumSum <=6) {
            return getRatingRandomGame(ratingurl);
        } else if(btnNumSum <=3 && btnNumSum>=0) {
            return getRankRandomGame(rankingurl);
        }
    })

    /* 랜덤값 도출 */

    var mecaurl = '/board/mecarankingjson';
    var steamurl = '/board/steamrankingjson';
    var ratingurl = '/board/ratinggamejson'
    var rankingurl = '/board/rankingjson'

    // 랜덤게임 서버에서 불러서(fetch) 출력해주는 함수
    function getRatingRandomGame(url) {   // 게임메카 데이터
        fetch(url).then((res) => {
            return res.json();
        }).then((data) => {
            // 랜덤 숫자 도출 및 게임 도출
            // min <= number <= max
            // Math.floor(Math.random() * (max - min + 1)) + min;
            let randomNum = Math.floor((Math.random() * data.length));
            btnSwitch(rtBtnReturnNum,randomNum);
            gameRecommandDisplay(data[randomNum].gameNm);
            modalDisplay(data[randomNum].gameNm, data[randomNum].gameRank);
        }).catch((err) => {
            console.log(err);
        });
    }

    function getRankRandomGame(url) {   // 랭킹 데이터
        fetch(url).then((res) => {
            return res.json();
        }).then((data) => {
            // 랜덤 숫자 도출 및 게임 도출
            // min <= number <= max
            // Math.floor(Math.random() * (max - min + 1)) + min;
            console.log(data);
            let randomNum = Math.floor((Math.random() * data.length));

            btnSwitch(rtBtnReturnNum,randomNum)
            gameRecommandDisplay(data[randomNum].rankNm);
            modalDisplay(data[randomNum].rankNm, data[randomNum].rankNum);
        }).catch((err) => {
            console.log(err);
        });
    }


    function btnSwitch(btnNameNum, randomNum) {
        let numArrange = 10;    // 범위, 15개 도출
        let min = 0;    // 최솟값
        switch (btnNameNum) {
            case 3: // 1위~10위
                randomNum = Math.floor(Math.random() * numArrange);
                break;
            case 2: // 11위~30위
                min = 10;
                numArrange = 20;
                randomNum = Math.floor(Math.random() * numArrange) + min;
                break;
            case 1: // 31위~50위
                min = 30;
                randomNum = Math.floor(Math.random() * numArrange) + min;
                break;
        }
    }

    // 화면창에 랜덤 게임을 띄움
    function gameRecommandDisplay(whatGame) {
        displayResultSpan.style.display = 'inline-block';
        modalLink.textContent =  whatGame;
    }

    // 눌렀을 때 모달창 열리는 함수
    modalLink.addEventListener('click', function (e) {
        e.preventDefault();
        modal.style.display = 'flex';
    })


    function modalDisplay(whatGame, whatGameNum) {
        modalLink.addEventListener('click', function (e) {
            e.preventDefault();
            modal.style.display = 'flex';
        })
        let modalWhatGame = document.querySelector('.modalWhatGame');
        let modalrank = document.querySelector('.modal-rank');
        modalWhatGame.textContent = whatGame;
        modalrank.textContent = '게임 순위' + whatGameNum + '위';
    }

    // 모달 닫아주는 함수
    modalCloseBtn.addEventListener('click', e => {
        modal.style.display = 'none';
    })

    // 모달이 모달창 밖으로 클릭되면 꺼지는 함수
    modal.addEventListener("click", e => {
        const evTarget = e.target
        if(evTarget.classList.contains("modal-overlay")) {
            modal.style.display = "none"
        }
    })

    // 모달창이 켜진 상태에서 ESC를 누르면 모달창이 닫히는 함수
    // window는 현재 자바스크립트가 실행되고 있는 창을 얘기함.
    window.addEventListener("keyup", e => {
        if(modal.style.display === "flex" && e.key === "Escape") {
            modal.style.display = "none"
        }
    })
}