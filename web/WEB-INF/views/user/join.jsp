<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<div class="join_container">
    <h1>회원가입</h1>
    <div class="err_msg_b">${requestScope.msg}</div>
    <form action="/user/join" method="post" id="join-frm">
        <div>
            <h4 class="join_title" style="padding-right: 14rem;">아이디</h4>
            <input type="text" name="uid" class="textBox" id="uidInput" required>
            <h5 class="msg_n" id="err-idMsg">아이디는 대소문자, 숫자조합<br> 4~15글자가 되어야 합니다.</h5>
            <h5 class="msg_n" id="duplication-uid">이미 존재하는 계정입니다.</h5>
            <h5 class="msg_n" id="available-uid">사용 가능한 계정입니다.</h5>
        </div>
        <div>
            <h4 class="join_title" style="padding-right: 12.5rem;">패스워드</h4>
            <input type="password" name="upw" class="textBox" id="upwInput" required>
            <h5 class="msg_n" id="err-pwMsg">비밀번호는 대소문자, 숫자, !@_ 조합<br> 4~20글자가 되어야 합니다.</h5>
            <h5 class="msg_n" id="good-pwMsg">사용 가능한 비밀번호입니다.</h5>
        </div>
        <div>
            <h4 class="join_title" style="padding-right: 9rem;">패스워드 확인</h4>
            <input type="password" name="upw-check" id="upw-chk" class="textBox" required>
            <h5 class="msg_n" id="err-pwChkMsg">비밀번호와 체크 비밀번호를 확인해 주세요.</h5>
            <h5 class="msg_n" id="good-pwChkMsg">비밀번호가 같습니다.</h5>
        </div>
        <div>
            <h4 class="join_title" style="padding-right: 15.5rem;">이름</h4>
            <input type="text" name="nm" class="textBox" required>
        </div>
        <div>
            <h4 class="join_title" style="padding-right: 16rem;">성별</h4>
            <span>
                <select name="gender" class="selBox">
                    <option value="0" selected>성별선택</option>
                    <option value="1">남자</option>
                    <option value="2">여자</option>
                    <option value="3">선택안함</option>
                </select>
            </span>
        </div>
        <div>
            <h4 class="join_title" style="padding-right: 14rem;">휴대폰</h4>
            <input type="text" name="contact" class="textBox" maxlength="8" placeholder="010 제외, '-'빼고 휴대폰 번호">
        </div>
        <div>
            <h4 class="join_title" style="padding-right: 16rem;">주소</h4>
            <input name="postAddress" placeholder="우편번호" type="text" class="addrTextBox" readonly maxlength="5">
            <input type="button" value="우편번호찾기" name="addressPostFindButton">
            <br>
            <input name="addressFirst" type="text" class="addrTextBox" readonly maxlength="100">
            <br>
            <input name="addressSecond" type="text" class="addrTextBox" maxlength="100">
        </div>
        <div>
            <!-- Button trigger modal -->
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                약관 확인하기
            </button>
            <div class="prevText">
                (필수) 개인정보 수집 및 이용 동의 <input type="checkbox" class="prevChk" id="prevChk">
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">개인정보 처리방침</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <title>개인정보처리방침</title>
                        </head>
                        <p> <p class="ls2 lh6 bs5 ts4"><em class="emphasis">< 팀프로젝트 >('http://localhost:8090/user/join'이하 '뭐하Gee')</em>은(는) 「개인정보 보호법」 제30조에 따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리방침을 수립·공개합니다.</p>
                        <p class="ls2">○ 이 개인정보처리방침은 <em class="emphasis">2022</em>년 <em class="emphasis">1</em>월 <em class="emphasis">1</em>부터 적용됩니다.</p>
                        </br>
                        <p class='lh6 bs4'>
                            <strong>제1조(개인정보의 처리 목적)<br/><br/>
                                <em class="emphasis">< 팀프로젝트 >('http://localhost:8090/user/join'이하  '뭐하Gee')</em>은(는) 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며 이용 목적이 변경되는 경우에는 「개인정보 보호법」 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.</strong></p>
                        <ul class="list_indent2 mgt10">
                            <p class="ls2">1. 홈페이지 회원가입 및 관리</p>
                            <p class="ls2">회원 가입의사 확인, 회원제 서비스 제공에 따른 본인 식별·인증, 회원자격 유지·관리 목적으로 개인정보를 처리합니다.</p>
                            </br>
                        </ul></br></br>

                        <p class='lh6 bs4'><strong>제2조(개인정보의 처리 및 보유 기간)</strong></br></br>
                            ① <em class="emphasis">< 팀프로젝트 ></em>은(는) 법령에 따른 개인정보 보유·이용기간 또는 정보주체로부터 개인정보를 수집 시에 동의받은 개인정보 보유·이용기간 내에서 개인정보를 처리·보유합니다.</br></br>
                            ② 각각의 개인정보 처리 및 보유 기간은 다음과 같습니다.</p><ul class='list_indent2 mgt10'>
                        <li class='tt'>1.<홈페이지 회원가입 및 관리></li><li class='tt'>
                        <홈페이지 회원가입 및 관리>와 관련한 개인정보는 수집.이용에 관한 동의일로부터<1년>까지 위 이용목적을 위하여 보유.이용됩니다.</li><li>보유근거 : 연습용</li><li>관련법령 : 1)신용정보의 수집/처리 및 이용 등에 관한 기록 : 3년</br>2) 소비자의 불만 또는 분쟁처리에 관한 기록 : 3년</br>3) 대금결제 및 재화 등의 공급에 관한 기록 : 5년</br>4) 계약 또는 청약철회 등에 관한 기록 : 5년</br>5) 표시/광고에 관한 기록 : 6개월</br></li><li>예외사유 : </li></ul><br/><br/><p class="lh6 bs4"><strong>제3조(정보주체와 법정대리인의 권리·의무 및 그 행사방법)</strong></p><p class="ls2"><br/><br/>① 정보주체는 팀프로젝트에 대해 언제든지 개인정보 열람·정정·삭제·처리정지 요구 등의 권리를 행사할 수 있습니다.</p><p class='sub_p'>② 제1항에 따른 권리 행사는팀프로젝트에 대해 「개인정보 보호법」 시행령 제41조제1항에 따라 서면, 전자우편, 모사전송(FAX) 등을 통하여 하실 수 있으며 팀프로젝트은(는) 이에 대해 지체 없이 조치하겠습니다.</p><p class='sub_p'>③ 제1항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다.이 경우 “개인정보 처리 방법에 관한 고시(제2020-7호)” 별지 제11호 서식에 따른 위임장을 제출하셔야 합니다.</p><p class='sub_p'>④ 개인정보 열람 및 처리정지 요구는  「개인정보 보호법」  제35조 제4항, 제37조 제2항에 의하여 정보주체의 권리가 제한 될 수 있습니다.</p><p class='sub_p'>⑤ 개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.</p><p class='sub_p'>⑥ 팀프로젝트은(는) 정보주체 권리에 따른 열람의 요구, 정정·삭제의 요구, 처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나 정당한 대리인인지를 확인합니다.</p></br></br><p class='lh6 bs4'><strong>제4조(처리하는 개인정보의 항목 작성) </strong></br></br> ① <em class="emphasis">< 팀프로젝트 ></em>은(는) 다음의 개인정보 항목을 처리하고 있습니다.</p><ul class='list_indent2 mgt10'><li class='tt'>1< 홈페이지 회원가입 및 관리 ></li><li>필수항목 : 이메일, 휴대전화번호, 비밀번호, 성별</li><li>선택항목 : </li></ul></br></br><p class='lh6 bs4'><strong>제5조(개인정보의 파기)<em class="emphasis"></strong></p><p class='ls2'></br>① < 팀프로젝트 > 은(는) 개인정보 보유기간의 경과, 처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 지체없이 해당 개인정보를 파기합니다.</br></br>② 정보주체로부터 동의받은 개인정보 보유기간이 경과하거나 처리목적이 달성되었음에도 불구하고 다른 법령에 따라 개인정보를 계속 보존하여야 하는 경우에는, 해당 개인정보를 별도의 데이터베이스(DB)로 옮기거나 보관장소를 달리하여 보존합니다.</br>1. 법령 근거 :</br>2. 보존하는 개인정보 항목 : 계좌정보, 거래날짜</br></br>③ 개인정보 파기의 절차 및 방법은 다음과 같습니다.</br>1. 파기절차</br> < 팀프로젝트 > 은(는) 파기 사유가 발생한 개인정보를 선정하고, < 팀프로젝트 > 의 개인정보 보호책임자의 승인을 받아 개인정보를 파기합니다.</br></p><p class='sub_p mgt10'>2. 파기방법</p><p class='sub_p'파기</p></br></br><p class='lh6 bs4'><strong>제6조(개인정보의 안전성 확보 조치)<em class="emphasis"></br></br>< 팀프로젝트 ></em>은(는) 개인정보의 안전성 확보를 위해 다음과 같은 조치를 취하고 있습니다.</strong></p><p class='sub_p mgt10'>1. 개인정보 취급 직원의 최소화 및 교육</br> 개인정보를 취급하는 직원을 지정하고 담당자에 한정시켜 최소화 하여 개인정보를 관리하는 대책을 시행하고 있습니다.</br></br>2. 개인정보의 암호화</br> 이용자의 개인정보는 비밀번호는 암호화 되어 저장 및 관리되고 있어, 본인만이 알 수 있으며 중요한 데이터는 파일 및 전송 데이터를 암호화 하거나 파일 잠금 기능을 사용하는 등의 별도 보안기능을 사용하고 있습니다.</br></br>3. 접속기록의 보관 및 위변조 방지</br> 개인정보처리시스템에 접속한 기록을 최소 1년 이상 보관, 관리하고 있으며,다만, 5만명 이상의 정보주체에 관하여 개인정보를 추가하거나, 고유식별정보 또는 민감정보를 처리하는 경우에는 2년이상 보관, 관리하고 있습니다.<br/>또한, 접속기록이 위변조 및 도난, 분실되지 않도록 보안기능을 사용하고 있습니다.</br></br></p></br></br><p class="lh6 bs4"><strong>제7조(개인정보 자동 수집 장치의 설치•운영 및 거부에 관한 사항)</strong></p><p class="ls2"><br/><br/>① 팀프로젝트 은(는) 이용자에게 개별적인 맞춤서비스를 제공하기 위해 이용정보를 저장하고 수시로 불러오는 ‘쿠키(cookie)’를 사용합니다.</br>② 쿠키는 웹사이트를 운영하는데 이용되는 서버(http)가 이용자의 컴퓨터 브라우저에게 보내는 소량의 정보이며 이용자들의 PC 컴퓨터내의 하드디스크에 저장되기도 합니다.</br>가. 쿠키의 사용 목적 : 이용자가 방문한 각 서비스와 웹 사이트들에 대한 방문 및 이용형태, 인기 검색어, 보안접속 여부, 등을 파악하여 이용자에게 최적화된 정보 제공을 위해 사용됩니다.</br>나. 쿠키의 설치•운영 및 거부 : 웹브라우저 상단의 도구>인터넷 옵션>개인정보 메뉴의 옵션 설정을 통해 쿠키 저장을 거부 할 수 있습니다.</br>다. 쿠키 저장을 거부할 경우 맞춤형 서비스 이용에 어려움이 발생할 수 있습니다.<p class='sub_p mgt30'><strong>제8조 (개인정보 보호책임자) </strong></p><p class='sub_p mgt10'> ①  <span class='colorLightBlue'>팀프로젝트</span> 은(는) 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.</p><ul class='list_indent2 mgt10'><li class='tt'>▶ 개인정보 보호책임자 </li><li>성명 :최성완</li><li>직책 :연습용</li><li>직급 :연습용</li><li>연락처 :01065513376, wan7523376@gmail.com, </li></ul><p class='sub_p'>※ 개인정보 보호 담당부서로 연결됩니다.<p/> <ul class='list_indent2 mgt10'><li class='tt'>▶ 개인정보 보호 담당부서</li><li>부서명 :</li><li>담당자 :</li><li>연락처 :, , </li></ul><p class='sub_p'>② 정보주체께서는 팀프로젝트 의 서비스(또는 사업)을 이용하시면서 발생한 모든 개인정보 보호 관련 문의, 불만처리, 피해구제 등에 관한 사항을 개인정보 보호책임자 및 담당부서로 문의하실 수 있습니다. 팀프로젝트 은(는) 정보주체의 문의에 대해 지체 없이 답변 및 처리해드릴 것입니다.</p><p class='sub_p mgt30'><strong>제9조(개인정보 열람청구)</br> 정보주체는 ｢개인정보 보호법｣ 제35조에 따른 개인정보의 열람 청구를 아래의 부서에 할 수 있습니다.<br/>< 팀프로젝트 ></span>은(는) 정보주체의 개인정보 열람청구가 신속하게 처리되도록 노력하겠습니다. </strong></p><ul class='list_indent2 mgt10'><li class='tt'>▶ 개인정보 열람청구 접수·처리 부서 </li><li>부서명 : </li><li>담당자 : </li><li>연락처 : , , </li></ul></br></br><p class='lh6 bs4'><strong>제10조(권익침해 구제방법)<em class="emphasis"></em></strong></p><br/><br/>정보주체는 개인정보침해로 인한 구제를 받기 위하여 개인정보분쟁조정위원회, 한국인터넷진흥원 개인정보침해신고센터 등에 분쟁해결이나 상담 등을 신청할 수 있습니다. 이 밖에 기타 개인정보침해의 신고, 상담에 대하여는 아래의 기관에 문의하시기 바랍니다.<br/><br/>



                        1. 개인정보분쟁조정위원회 : (국번없이) 1833-6972 (www.kopico.go.kr)<br/>

                        2. 개인정보침해신고센터 : (국번없이) 118 (privacy.kisa.or.kr)<br/>

                        3. 대검찰청 : (국번없이) 1301 (www.spo.go.kr)<br/>

                        4. 경찰청 : (국번없이) 182 (ecrm.cyber.go.kr)<br/><br/>



                        「개인정보보호법」제35조(개인정보의 열람), 제36조(개인정보의 정정·삭제), 제37조(개인정보의 처리정지 등)의 규정에 의한 요구에 대 하여 공공기관의 장이 행한 처분 또는 부작위로 인하여 권리 또는 이익의 침해를 받은 자는 행정심판법이 정하는 바에 따라 행정심판을 청구할 수 있습니다.<br/><br/>



                        ※ 행정심판에 대해 자세한 사항은 중앙행정심판위원회(www.simpan.go.kr) 홈페이지를 참고하시기 바랍니다.</br></br><p class='lh6 bs4'><strong>제11조(개인정보 처리방침 변경)<em class="emphasis"></em></strong></p><br/></p><p class='sub_p'>① 이 개인정보처리방침은 2022년 1월 1부터 적용됩니다.</p><p class='sub_p'></p><p class='sub_p'></p><p class='sub_p'>② 이전의 개인정보 처리방침은 아래에서 확인하실 수 있습니다. </p><p class='sub_p'></p><p class='sub_p'></p><p class='sub_p'>예시 ) - 20XX. X. X ~ 20XX. X. X 적용   (클릭) </p><p class='sub_p'></p><p class='sub_p'></p><p class='sub_p'>예시 ) - 20XX. X. X ~ 20XX. X. X 적용   (클릭) </p><p class='sub_p'></p><p class='sub_p'></p><p class='sub_p'>예시 ) - 20XX. X. X ~ 20XX. X. X 적용   (클릭)</p></p>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>


        <span>
            <input type="button" value="뒤로가기" class="back_btn">
        </span>
        <span>
            <input type="submit" value="회원가입" class="submit_btn">
        </span>
    </form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</div>
