var naver_id_login = new naver_id_login("9CbEg9cxRUs7V2Q6_IMd", "http://localhost:8090/user/naver/callback");
var state = naver_id_login.getUniqState();
naver_id_login.setButton("green", 3);
naver_id_login.setDomain("http://localhost:8090/user/login");
naver_id_login.setState(state);
naver_id_login.init_naver_id_login();






Kakao.init('31a4b437d075fde689913e97c7d35df1'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
function kakaoLogin() {
    try {
        return new Promise((resolve, reject)=> {
            if(!Kakao) {
                reject('Kakao 인스턴스가 존재하지 않습니다.')
            }
            Kakao.Auth.login({
                success: function (response) {
                    Kakao.API.request({
                        url: '/v2/user/me',
                        success: function (data) {
                            console.log(data);
                            insKakaoInfo(data.id,data.properties.nickname,data.properties.profile_image);
                            },
                        fail: function (error) {
                            console.log(error)
                        },
                    })
                },
                fail: function (error) {
                    console.log(error)
                }
            })
        })
    } catch (err) {
        console.log(err)
    }
}

//카카오로그아웃
function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
        Kakao.API.request({
            url: '/v1/user/unlink',
            success: function (response) {
                console.log(response)
            },
            fail: function (error) {
                console.log(error)
            },
        })
        Kakao.Auth.setAccessToken(undefined)
    }
}

function insKakaoInfo(uid, nm, profileImg) {
    const param = {
        'uid' : uid,
        'nm' : nm,
        'profileImg' : profileImg
    };
    myFetch.post('/user/kakao', (data)=> {
        console.log('카카오 회원가입 결과 : ' + data.result);
        switch (data.resultLogin) {
            case 0:
                console.log('카카오 로그인 실패');
                break;
            case 1:
                console.log('회원가입 후 로그인');
                location.href = '/board/main';
                break;
            case 2:
                console.log('일반 로그인');
                location.href = '/board/main';
                break;
            default:
                console.log('뭔지 모를 오류');
                break;
        }
    }, param);
}

