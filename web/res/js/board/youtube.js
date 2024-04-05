let q = '고양이';
let part = 'snippet';
let maxResults = 50;
const url = 'https://www.googleapis.com/youtube/v3/search?key=AIzaSyBZal1oi0pC032oLeM-AYoqfo2sojWbbmo&part='+part+ '&q='+q + '&maxResults=' + maxResults;

fetch(url).then(function(res) {
    return res.json();
}).then(function(data) {
    // 랜덤 숫자 도출
    // min <= number <= max
    // Math.floor(Math.random() * (max - min + 1)) + min;
    let randomNum = Math.floor((Math.random() * (maxResults-1)));
    console.log('randomNum:' + randomNum);
    videoInput(data.items[randomNum].id.videoId);
}).catch(function (err) {
    console.log(err);
})

function videoInput(img, title) {
    let videoSection = document.querySelector('#video_section');
    videoSection.innerHTML = `
    <iframe width="560" height="315" src="https://www.youtube.com/embed/${img}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
    `;
}