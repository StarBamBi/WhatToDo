{
    let idChkState = 2 // 0: 아이디 사용 불가능, 1: 아이디 사용 가능, 2:체크 안함
    const joinFrmElem = document.querySelector('#join-frm'); // 회원가입 form
    const uidInput = joinFrmElem.querySelector('#uidInput'); // ID칸
    const upwInput = joinFrmElem.querySelector('#upwInput'); // PW칸
    const upwChkInput = joinFrmElem.querySelector('#upw-chk'); // 비번체크 칸


    // 우편번호 api
    joinFrmElem['addressPostFindButton'].addEventListener('click', () => {
        new daum.Postcode({
            oncomplete: (data) => {
                joinFrmElem['postAddress'].value = data['zonecode'];
                joinFrmElem['addressFirst'].value = data['address'];
                joinFrmElem['addressSecond'].value = '';
                joinFrmElem['addressSecond'].focus();
            }
        }).open();
    });


    // 회원가입 실행여부
    if(joinFrmElem) {
        joinFrmElem.addEventListener('submit', (e) => {
            const uid = joinFrmElem.uid.value;
            const upw = joinFrmElem.upw.value;
            const upwChk = joinFrmElem.querySelector('#upw-chk').value;
            const nm = joinFrmElem.nm.value;
            const gender = joinFrmElem.gender.value;
            const phone = joinFrmElem.contact.value;
            const postaddr = joinFrmElem.postAddress.value;
            const prevChk = joinFrmElem.prevChk;


            if (!idRegex.test(uid)) {
                alert('아이디를 바르게 적어주세요');
                e.preventDefault();
            } else if (joinFrmElem.querySelector('#duplication-uid').classList.value === 'err_msg_b') {
                alert('이미 존재하는 계정입니다.');
                e.preventDefault();
            } else if (!pwRegex.test(upw)) {
                alert('비밀번호를 올바르게 작성해주세요.');
                e.preventDefault();
            } else if(upw !== upwChk) {
                alert('비밀번호와 체크 비밀번호를 확인해 주세요.');
                e.preventDefault();
            } else if(!nmRegex.test(nm)) {
                alert('성함을 올바르게 작성해 주세요.');
                console.log(nm);
                e.preventDefault();
            } else if(gender === '0') {
                alert('성별을 선택해주세요');
                e.preventDefault();
            } else if (!phoneRegex.test(phone)) {
                alert('휴대폰 번호를 정확히 입력해주세요.');
                e.preventDefault();
            }
            else if (!postRegex.test(postaddr)) {
                alert('주소가 필요합니다.');
                e.preventDefault();
            } else if (prevChk.checked !== true) {
                alert('약관동의에 체크해주세요.');
                e.preventDefault();
            }

        });


        // 아이디 중복체크 #1
        uidInput.addEventListener('blur', (e) => {
           const uid = joinFrmElem.uid.value;
           if (!idRegex.test(uid)) {
               joinFrmElem.querySelector('#err-idMsg').classList.remove('msg_n');
               joinFrmElem.querySelector('#duplication-uid').classList.remove('err_msg_b');
               joinFrmElem.querySelector('#available-uid').classList.remove('successMsg');
               joinFrmElem.querySelector('#err-idMsg').classList.add('err_msg_b');
               joinFrmElem.querySelector('#duplication-uid').classList.add('msg_n');
               joinFrmElem.querySelector('#available-uid').classList.add('msg_n');
               e.preventDefault();
           }

           fetch(`/user/idChk/${uid}`)
                .then(res => res.json())
                .then((data) => {
                    setIdChkMsg(data);
                }).catch((e)=> {
                console.log(e);
            });
           e.preventDefault();
        });

        // 아이디 중복체크 #2
        const setIdChkMsg = (data) => {
            idChkState = data.result;
            console.log(idChkState);
            switch(idChkState) {
                case 0:
                    joinFrmElem.querySelector('#err-idMsg').classList.remove('err_msg_b');
                    joinFrmElem.querySelector('#duplication-uid').classList.remove('msg_n');
                    joinFrmElem.querySelector('#available-uid').classList.remove('successMsg');
                    joinFrmElem.querySelector('#err-idMsg').classList.add('msg_n');
                    joinFrmElem.querySelector('#duplication-uid').classList.add('err_msg_b');
                    joinFrmElem.querySelector('#available-uid').classList.add('msg_n');
                    break;
                case 1:
                    joinFrmElem.querySelector('#err-idMsg').classList.remove('err_msg_b');
                    joinFrmElem.querySelector('#duplication-uid').classList.remove('err_msg_b');
                    joinFrmElem.querySelector('#available-uid').classList.remove('msg_n');
                    joinFrmElem.querySelector('#err-idMsg').classList.add('msg_n');
                    joinFrmElem.querySelector('#duplication-uid').classList.add('msg_n');
                    joinFrmElem.querySelector('#available-uid').classList.add('successMsg');
                    break;
            }
        };

        // 패스워드 정규식 확인
        upwInput.addEventListener('blur',(e) => {
            const upw = joinFrmElem.upw.value;
            if (!pwRegex.test(upw)) {
                joinFrmElem.querySelector('#err-pwMsg').classList.remove('msg_n');
                joinFrmElem.querySelector('#good-pwMsg').classList.remove('successMsg');
                joinFrmElem.querySelector('#err-pwMsg').classList.add('err_msg_b');
                joinFrmElem.querySelector('#good-pwMsg').classList.add('msg_n');
            } else if (pwRegex.test(upw)) {
                joinFrmElem.querySelector('#err-pwMsg').classList.remove('err_msg_b');
                joinFrmElem.querySelector('#good-pwMsg').classList.remove('successMsg');
                joinFrmElem.querySelector('#err-pwMsg').classList.add('msg_n');
                joinFrmElem.querySelector('#good-pwMsg').classList.add('successMsg');
            }
        });


        // 패스워드 === 패스워드 확인
        upwChkInput.addEventListener('blur', (e) => {
            const upw = joinFrmElem.upw.value;
            const upwChk = joinFrmElem.querySelector('#upw-chk').value;

            if (upw !== upwChk || upw === '' || upwChk === '') {
                joinFrmElem.querySelector('#err-pwChkMsg').classList.remove('msg_n');
                joinFrmElem.querySelector('#good-pwChkMsg').classList.remove('successMsg');
                joinFrmElem.querySelector('#good-pwChkMsg').classList.add('msg_n');
                joinFrmElem.querySelector('#err-pwChkMsg').classList.add('err_msg_b');
                e.preventDefault();

            } else if (upw === upwChk) {
                joinFrmElem.querySelector('#good-pwChkMsg').classList.remove('msg_n');
                joinFrmElem.querySelector('#err-pwChkMsg').classList.remove('err_msg_b');
                joinFrmElem.querySelector('#err-pwChkMsg').classList.add('msg_n');
                joinFrmElem.querySelector('#good-pwChkMsg').classList.add('successMsg');
            }
        });


        const backBtnElem = document.querySelector(".back_btn");
        backBtnElem.addEventListener('click', (e) => {
           location.href = "/user/login";
        });
    }
}