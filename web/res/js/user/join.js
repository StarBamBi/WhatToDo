{
    const joinFrmElem = document.querySelector('#join-frm');
    const idRegex = /^([a-zA-Z0-9]{4,15})$/;
    const pwRegex = /^([a-zA-Z0-9!@_]{4,20})$/;
    const nmRegex = /^([가-힣]{2,5})$/;

    if(joinFrmElem) {
        joinFrmElem.addEventListener('submit', (e) => {
            const uid = joinFrmElem.uid.value;
            const upw = joinFrmElem.upw.value;
            const upwChk = joinFrmElem.querySelector('#upw-chk').value;
            const nm = joinFrmElem.nm.value;

            console.log(uid);
            console.log(upw);
            console.log(upwChk);
            console.log(nm);

        });
    }
}