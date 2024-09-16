{
    const recordList = document.querySelectorAll('.record');
    const recordEvent = (item) => {
        item.addEventListener('click', () => {
            const iboard = item.dataset.iboard;
            console.log(iboard);
            location.href = `/board/detail?iboard=${iboard}`;
        });
    };
    recordList.forEach(recordEvent);
}


let totalData = document.querySelectorAll('tr'); //게시판 전제 tr
let totalDataArr = Array.prototype.slice.call(totalData); //게시판 tr 배열로 변환
let totalDataNum = document.querySelectorAll('tr').length - 1; //게시물 갯수
let dataPerPage = document.querySelector('#paging > select');
let dataPerPageVal = document.querySelector('#paging > select').value; //화면에 나오는 게시물 갯수

function pageSelectVal() { // 갯수별로 보기 값 담는 함수

    let val = dataPerPage.options[dataPerPage.selectedIndex].value;
    return dataPerPageVal = val;
}

let pageCount = Math.floor(Number(totalDataNum / pageSelectVal())) + 1;
let pageIdxElem = document.querySelector(".pagination");
let firstPageNum = 1;
let firstPageData = totalDataArr.slice(((firstPageNum - 1) * pageSelectVal()) + 1, firstPageNum * pageSelectVal() + 1);


//페이징 표시

let pagingCount = 3;

if (Number(totalDataNum % pageSelectVal()) === 0) {
    pageCount = pageCount - 1;
}

let makeIdx = () => {

    pageIdxElem.innerHTML +=
        `
        <li class="pageNum page-item" data-idx="${firstPageNum}"><a class="page-preNext"> < </a></li>
        `

    for (let i = 1; i <= pageCount; i++) {

        pageIdxElem.innerHTML +=
            `
        <li class="pageNum page-item" data-idx="${i}"><a class="page-link">${i}</a></li>
        `

    }
    // if (pageCount > pagingCount) {
    pageIdxElem.innerHTML +=
        `
        <li class="pageNum page-item" data-idx="${pageCount}"><a class="page-preNext"> > </a></li>
        `
// }
}
makeIdx();

let showFirstPage = () => {
    totalDataArr.forEach(function (element) {
        element.style.display = 'none'
    });
    totalDataArr[0].style.display = '';
    firstPageData.forEach(function (element) {
        element.style.display = ''
    });
}
showFirstPage();

let currentPageNum;
let currentPageData;



//뒤로가기 누를 시 이전 페이지로
window.onpopstate = function (event) {
    console.log(currentPageNum)
    // pushState로 인하여 페이지가 하나 더 생성되기 떄문에 한번에 뒤로가기 위해서 뒤로가기를 한번 더 해줍니다.
    firstPageNum = currentPageNum;

    console.log("fpn : " + firstPageNum);
    totalDataArr.forEach(function (element) {
        element.style.display = 'none'
    });
    totalDataArr[0].style.display = '';
    firstPageData.forEach(function (element) {
        element.style.display = ''

    })
}
let pageNumElem = document.querySelectorAll('.pageNum');
let pageNumVal = (item) => {
    item.addEventListener('click', () => {

        history.pushState(null, null, '');

        let pageIdxElem = document.querySelectorAll("a.page-link");
        pageIdxElem.forEach(function (item) {
            item.classList.remove("selected")
        });
        // item.querySelector("a").classList.add("selected");
        let val = Number(item.dataset.idx);
        console.log(val);
        pageIdxElem[val - 1].classList.add("selected");
        currentPageNum = val;
        currentPageData = totalDataArr.slice(((currentPageNum - 1) * pageSelectVal()) + 1, currentPageNum * pageSelectVal() + 1);
        let showCurrentPage = () => {
            totalDataArr.forEach(function (element) {
                element.style.display = 'none'
            });
            totalDataArr[0].style.display = '';
            currentPageData.forEach(function (element) {
                element.style.display = ''
            });
        }
        showCurrentPage();
    })
};
pageNumElem.forEach(pageNumVal);

let dataPerPageElem = document.querySelector("select");
dataPerPageElem.addEventListener('change', () => {

    document.querySelectorAll(".pageNum").forEach((item) => {
        item.remove();
    });
    let pageCount = Math.floor(Number(totalDataNum / pageSelectVal())) + 1;
    if (Number(totalDataNum % pageSelectVal()) === 0) {
        pageCount = pageCount - 1;
    }
    // if (pageCount > pagingCount) {
    pageIdxElem.innerHTML +=
        `
        <li class="pageNum page-item" data-idx="${firstPageNum}"><a class="page-preNext"> < </a></li>
        `
// }
    for (let i = 1; i <= pageCount; i++) {
        pageIdxElem.innerHTML +=
            `
        <li class="pageNum page-item" data-idx="${i}"><a class="page-link">${i}</a></li>
        `
    }
    // if (pageCount > pagingCount) {
    pageIdxElem.innerHTML +=
        `
        <li class="pageNum page-item" data-idx="${pageCount}"><a class="page-preNext"> > </a></li>
        `
// }
    let pageNumElem = document.querySelectorAll('.pageNum');
    totalDataArr.forEach(function (element) {
        element.style.display = 'none'
    });
    totalDataArr[0].style.display = '';
    let selData = totalDataArr.slice(((firstPageNum - 1) * pageSelectVal()) + 1, firstPageNum * pageSelectVal() + 1)
    selData.forEach(function (element) {
        element.style.display = ''
    });

    let pageNumVal = (item) => {
        item.addEventListener('click', () => {
            let pageIdxElem = document.querySelectorAll("a.page-link");
            pageIdxElem.forEach(function (item) {
                item.classList.remove("selected")
            });
            // item.querySelector("a").classList.add("selected");
            totalDataArr.forEach(function (element) {
                element.style.display = 'none'
            });
            let val = Number(item.dataset.idx);
            console.log(val);
            let currentPageNum = val;
            pageIdxElem[val - 1].classList.add("selected");
            let currentPageData = totalDataArr.slice(((currentPageNum - 1) * pageSelectVal()) + 1, currentPageNum * pageSelectVal() + 1);
            let showCurrentPage = () => {
                totalDataArr[0].style.display = '';
                currentPageData.forEach(function (element) {
                    element.style.display = ''
                });
            }
            showCurrentPage();
        })
    }
    pageNumElem.forEach(pageNumVal);

})

let searchBtn = document.querySelector(".searchBtn");

//검색
searchBtn.addEventListener("click", () => {
    let searchTxt = document.querySelector(".searchTxt").value;

    let totalDataNum = document.querySelectorAll("tr.showList > td").length / 4;

    //여기서 포문 돌려서 4로 나눴을때 1이 남는 인덱스만 찾아내서 그 값을 얻는다
    let totalCtntData = [];
    for (let i = 0; i < totalDataNum * 4; i++) {

        if (i % 4 === 1) {
            totalCtntData.push(document.querySelectorAll("tr.showList > td")[i]);
        }
    }
    console.log(totalCtntData);
    let totalSelectedData = [];

    for (let i = 0; i < totalDataNum; i++) {
        if (totalCtntData[i].textContent.includes(searchTxt)) {
            totalSelectedData.push(totalCtntData[i].parentNode);
        }
    }
    console.log(searchTxt);
    console.log("totalSelectedData : " +totalSelectedData);

    let totalSelectedDataNum = totalSelectedData.length;

    document.querySelectorAll(".pageNum").forEach((item) => {
        item.remove();
    });

    let pageCount = Math.floor(Number(totalSelectedDataNum / pageSelectVal())) + 1;
    if (Number(totalSelectedDataNum % pageSelectVal()) === 0) {
        pageCount = pageCount - 1;
    }
    // if (pageCount > pagingCount) {
    pageIdxElem.innerHTML +=
        `
        <li class="pageNum page-item" data-idx="${firstPageNum}"><a class="page-preNext"> < </a></li>
        `
// }
    for (let i = 1; i <= pageCount; i++) {
        pageIdxElem.innerHTML +=
            `
        <li class="pageNum page-item" data-idx="${i}"><a class="page-link">${i}</a></li>
        `
    }
    // if (pageCount > pagingCount) {
    pageIdxElem.innerHTML +=
        `
        <li class="pageNum page-item" data-idx="${pageCount}"><a class="page-preNext"> > </a></li>
        `
// }
    let pageNumElem = document.querySelectorAll('.pageNum');

    totalDataArr.forEach(function (element) {
        element.style.display = 'none'
    });

    totalDataArr[0].style.display = '';

    let selData = totalSelectedData.slice(((firstPageNum - 1) * pageSelectVal()), firstPageNum * pageSelectVal() + 1);
    console.log("seldata : " + selData)
    selData.forEach(function (element) {
        element.style.display = ''
    });

    let pageNumVal = (item) => {
        item.addEventListener('click', () => {
            let pageIdxElem = document.querySelectorAll("a.page-link");
            pageIdxElem.forEach(function (item) {
                item.classList.remove("selected")
            });
            // item.querySelector("a").classList.add("selected");
            totalDataArr.forEach(function (element) {
                element.style.display = 'none'
            });
            let val = Number(item.dataset.idx);
            console.log(val);
            currentPageNum = val;
            pageIdxElem[val - 1].classList.add("selected");
            let currentPageData = totalSelectedData.slice(((currentPageNum - 1) * pageSelectVal()) + 1, currentPageNum * pageSelectVal() + 1);
            let showCurrentPage = () => {
                totalDataArr[0].style.display = '';
                currentPageData.forEach(function (element) {
                    element.style.display = ''
                });
            }
            showCurrentPage();
        })
    }
    pageNumElem.forEach(pageNumVal);

    let currentPageNum;

    let dataPerPageElem = document.querySelector("select");
    dataPerPageElem.addEventListener('change', () => {

        document.querySelectorAll(".pageNum").forEach((item) => {
            item.remove();
        });
        let pageCount = Math.floor(Number(totalSelectedDataNum / pageSelectVal())) + 1;
        if (Number(totalSelectedDataNum % pageSelectVal()) === 0) {
            pageCount = pageCount - 1;
        }

        pageIdxElem.innerHTML +=
            `
        <li class="pageNum page-item" data-idx="${firstPageNum}"><a class="page-preNext"> < </a></li>
        `
        for (let i = 1; i <= pageCount; i++) {
            pageIdxElem.innerHTML +=
                `
        <li class="pageNum page-item" data-idx="${i}"><a class="page-link">${i}</a></li>
        `
        }
        pageIdxElem.innerHTML +=
            `
        <li class="pageNum page-item" data-idx="${pageCount}"><a class="page-preNext"> > </a></li>
        `

        let pageNumElem = document.querySelectorAll('.pageNum');
        totalDataArr.forEach(function (element) {
            element.style.display = 'none'
        });
        totalDataArr[0].style.display = '';
        let selData = totalSelectedData.slice(((firstPageNum - 1) * pageSelectVal()) + 1, firstPageNum * pageSelectVal() + 1)
        selData.forEach(function (element) {
            element.style.display = ''
        });

        let pageNumVal = (item) => {
            item.addEventListener('click', () => {
                let pageIdxElem = document.querySelectorAll("a.page-link");
                pageIdxElem.forEach(function (item) {
                    item.classList.remove("selected")
                });
                // item.querySelector("a").classList.add("selected");
                totalDataArr.forEach(function (element) {
                    element.style.display = 'none'
                });
                let val = Number(item.dataset.idx);
                console.log(val);
                let currentPageNum = val;
                pageIdxElem[val - 1].classList.add("selected");
                let currentPageData = totalSelectedData.slice(((currentPageNum - 1) * pageSelectVal()) + 1, currentPageNum * pageSelectVal() + 1);
                let showCurrentPage = () => {
                    totalDataArr[0].style.display = '';
                    currentPageData.forEach(function (element) {
                        element.style.display = ''
                    });
                }
                showCurrentPage();
            })
        }
        pageNumElem.forEach(pageNumVal);

    })
});
