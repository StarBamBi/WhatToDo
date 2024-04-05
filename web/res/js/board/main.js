const swiper = new Swiper('.swiper', {
    // Optional parameters
    direction: 'horizontal',
    loop: true, // 슬라이드 반복 여부
    loopAdditionalSlides : 1, // 슬라이드 반복 시 마지막 슬라이드에서 다음 슬라이드가 보여지지 않는 현상
    autoplay :
        {  // 자동 슬라이드 설정 , 비 활성화 시 false
        delay : 3000,   // 시간 설정
        disableOnInteraction : false,  // false로 설정하면 스와이프 후 자동 재생이 비활성화 되지 않음
    },

    // fade 효과일 때 슬라이드 이미지가 쌓이는 느낌
    // 그림자 이미지가 점점 진하게 드리움 위 현상을 막기 위해 옵션 추가
    effect : 'fade',
    fadeEffect: { crossFade: true },

    // swiper js display:none 상태에서 작동하지 않을 때
    // display:none 요소에서 block이 될 때 슬라이드는 작동되지 않는다. 아래 옵션값을 넣어 초기화 해준다.
    observer: true, observeParents: true,

    speed: 1000,
    // If we need pagination
    pagination: {
        el: '.swiper-pagination',
        clickable : true,  // 버튼 클릭 여부
    },

    // Navigation arrows
    // navigation: {
    //     nextEl: '.swiper-button-next',
    //     prevEl: '.swiper-button-prev',
    // },

    // And if we need scrollbar
    // scrollbar: {
    //     el: '.swiper-scrollbar',
    // },
});

// ------------------youtube---------------------- //

let q = '고양이';  // 검색해서 출력 해낼 것
let part = 'id';
let maxResults = 50;    // 최대 결과값
let videoSection = document.querySelector('#video_section');
const url = 'https://www.googleapis.com/youtube/v3/search?key=AIzaSyBZal1oi0pC032oLeM-AYoqfo2sojWbbmo&part='+part+ '&q='+q + '&maxResults=' + maxResults;
const url2 = 'https://www.googleapis.com/youtube/v3/videos?key=AIzaSyBZal1oi0pC032oLeM-AYoqfo2sojWbbmo&part=' + part +'&maxResults='+ maxResults + '&regionCode=KR&chart=mostPopular';

function youtubeSearchApi(url) {
    fetch(url).then(function(res) {
        return res.json();
    }).then(function(data) {
        // 랜덤 숫자 도출
        // min <= number <= max
        // Math.floor(Math.random() * (max - min + 1)) + min;
        let randomNum = Math.floor((Math.random() * (maxResults-1)));
        console.log('randomNum:' + randomNum);
        console.log(data);
        videoInput(data.items[randomNum].id.videoId);
    }).catch(function (err) {
        console.log(err);
    })
}
function youtubePopApi(url) {
    fetch(url).then(function(res) {
        return res.json();
    }).then(function(data) {
        // 랜덤 숫자 도출
        // min <= number <= max
        // Math.floor(Math.random() * (max - min + 1)) + min;
        let randomNum = Math.floor((Math.random() * (maxResults-1)));
        console.log('randomNum:' + randomNum);
        console.log(data);
        videoInput(data.items[randomNum].id);
    }).catch(function (err) {
        console.log(err);
    })
}
function videoInput(img) {
    videoSection.innerHTML += `
    <iframe width="500" height="315" src="https://www.youtube.com/embed/${img}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
    `;
}

youtubeSearchApi(url);
youtubePopApi(url2)

// 스크롤 위치에 따라 button group 이동
$(window).scroll(function () {
    var height = $(document).scrollTop();
    const guideBox = document.querySelector('#guide-tooltip');
    if(height>1300) {
        guideBox.style.top = '200px';
    } else {
        guideBox.style.top = '300px';
    }
});
