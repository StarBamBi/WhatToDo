

gameBtnFrm = document.querySelector('#gameBtnFrm');
if(gameBtnFrm) {
    let aaa = gameBtnFrm.querySelector('#aaa')
    aaa.addEventListener('click', function (e) {
        e.preventDefault();
        aaa.classList.add('plusBtn');
    })
}