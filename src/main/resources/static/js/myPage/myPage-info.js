/* mypage-info */


/* 눌렀을때 아래 나오도록 */


/* 기본 정보 설정은 ajax로 하면 될듯 */
$(".my-info").on("click", function(){
    let index = $(this).parent().index() - 1;
    let content = $(".content-wrapper");
    
    // 클릭된 요소가 보여지고 있는 경우 숨김 처리
    if (content.eq(index).is(":visible")) {
      content.eq(index).hide();
    }
    // 클릭된 요소가 보여지지 않은 경우 보여줌
    else {
      content.css("display", "none");
      content.eq(index).show();
    }
  });


console.log($(".changeBtn"))
/* 비밀번호 변경 */
$("#newPassword").on('keyup', function(){
    let pw = $("#newPassword").val();
    let num = pw.search(/[0-9]/g);
    let eng = pw.search(/[a-z]/ig);
    let spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    if(pw.length < 8 || pw.length > 20){
   
    $("#passwordError").css('color', 'red')
     return false;
    }else if(pw.search(/\s/) != -1){
        $("#passwordError").css('color', 'red')
     return false;
    }else if(num < 0 || eng < 0 || spe < 0 ){
        $("#passwordError").css('color', 'red')
     return false;
    }else {
        $("#passwordError").css('color', 'black')
       return true;
    }
})

$("#newPasswordConfirm").on('keyup', function(){
    if($(this).val() == $("#newPassword").val()){
        $(".error-text").hide()
    } else {
        $(".error-text").show()
    }
})


// 확인 버튼을 누르면 해당 게시글 없어지는 것도 똑같음
$(".cancel-btn").on('click', function(){
    $($(".my-info")[1]).hide();
})


/* 토글버튼 */
$(document).ready(function() {
    $(".allow-btn, .not-allow-btn").each(function() {
        var value = '';
        if ($(this).hasClass('allow-btn')) {
            value = 'true';
        } else {
            value = 'false';
        }
        $(this).attr('value', value);
    });
});

/* 토글 클릭해서 색 나오면 true 넣어주고 아니면 false 넣어줌 */
$(".allow-btn, .not-allow-btn").on('click', function(){
    var value = '';
    if ($(this).hasClass('allow-btn')) {
        value = 'false';
        $(this).removeClass('allow-btn').addClass('not-allow-btn');
    } else {
        $(this).removeClass('not-allow-btn').addClass('allow-btn');
        value = 'true';
    }
    $(this).attr('value', value);
});


/* 이력서 넣었을때 그 파일에 대한 정보를 띄워주기 */
$("#resume").on('change', function(){
    const file = $(this)[0].files[0]
    let fileName = file.name
    $(".resume-info").html(fileName)

    $('.resume-img').attr('src', '../../static/images/member/ppt.png')
})


/* 이메일 정규식 */
  $("#userEmail").keyup(function(){
    let emailRegex = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    let email = $(this).val()
    if(!emailRegex.test(email)){
        $(".err-email").html("잘못된 이메일 형식입니다")
    } else{
        $(".err-email").html();
    }
  })

  /* 회원 닉네임 정규식? */
  $('#memberNickname').keyup(function(){
    let value = $(this).val()
    if(value == ''){
        $('.err-nickname').html("변경할 닉네임을 넣어주세요")
    } else{
        $('.err-nickname').html()
    }
  })

/* 주소 */
function sample6_execDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

			// 각 주소의 노출 규칙에 따라 주소를 조합한다.
			// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
			var addr = ''; // 주소 변수
			var extraAddr = ''; // 참고항목 변수

			//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
			if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				addr = data.roadAddress;
			} else { // 사용자가 지번 주소를 선택했을 경우(J)
				addr = data.jibunAddress;
			}

			// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
			if(data.userSelectedType === 'R'){
				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
					extraAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if(data.buildingName !== '' && data.apartment === 'Y'){
					extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if(extraAddr !== ''){
					extraAddr = ' (' + extraAddr + ')';
				}
				// 조합된 참고항목을 해당 필드에 넣는다.
				document.getElementById("sample6_extraAddress").value = extraAddr;

			} else {
				document.getElementById("sample6_extraAddress").value = '';
			}

			// 우편번호와 주소 정보를 해당 필드에 넣는다.
			document.getElementById('sample6_postcode').value = data.zonecode;
			document.getElementById("sample6_address").value = addr;
			// 커서를 상세주소 필드로 이동한다.
			document.getElementById("sample6_detailAddress").focus();
		}
	}).open();
}


/* ajax*/
/*${formattedDate}*/

// let page = 0;
// const boardService = (() => {
//     function getList(callback){
//         console.log(page)
//         $.ajax({
//             url: `/mypage/info`,
//             type: 'post',
//             contentType: "application/json;charset=utf-8",
//             success: function(memberDTO){
//                 console.log("들어왓다")
//                 if (memberDTO.content.length === 0) { // 불러올 데이터가 없으면
//                     console.log("막힘")
//                     return;
//                 }
//                 if(callback){
//                     callback(memberDTO);
//                     console.log("들어왓다")
//                 }
//             }
//         });
//     }
//     return {getList: getList};
// })();
//
// function appendList(memberDTO) {
//     let boardText3 = '';
//     console.log(memberDTO.content);
//     memberDTO.content.forEach(memberDTO => {
//         // let startDate = new Date(eventLike.calendar.startDate); // assuming eventLike.registerDate is a valid date string
//         // let formattedDate = startDate.getFullYear() + '-' + (startDate.getMonth() + 1).toString().padStart(2, '0') + '-' + startDate.getDate().toString().padStart(2, '0');
//         // let endDate = new Date(eventLike.calendar.endDate); // assuming eventLike.registerDate is a valid date string
//         // let formattedEndDate = endDate.getFullYear() + '-' + (endDate.getMonth() + 1).toString().padStart(2, '0') + '-' + endDate.getDate().toString().padStart(2, '0');
//
//         console.log(memberDTO);
//         boardText3 +=  `
//                                                 <!-- 사진 div -->
//                                            <main id="main-app">
//                         <!-- main -->
//                         <div id="accountWrap">
//                         <!-- S : #newContainer -->
//                     <div id="newContainer">
//                         <!-- account-wrap with-footer -->
//                     <div class="account-wrap with-footer">
//                         <h2 class="big">MY 정보설정</h2>
//                     <div class="wrapper-button">
//                         <button class="my-info" href="#">
//                         <div class="my-info-text">
//                         <h3>기본 정보 설정</h3>
//                     <p>이름, 이메일, 휴대폰, SNS연동 등</p>
//                     </div>
//                     <div class="my-info-btn">
//                         <i class="wadizicon wa-chevron-right"></i>
//                         </div>
//                         </button>
//                         <form action="/mypage/info" method="post">
//                         <input type="hidden">
//                         <div class="content-wrapper" style="display: none">
//                         <span>회원정보 수정</span>
//                     <div class="input-btn-wrap">
//                         <div class="input">
//                         <input
//                     type="text"
//                     id="memberNickname"
//                     name="memberNickname"
//                     class="input-text"
//                     placeholder="닉네임"
//                     value="린먕"
//                         />
//                         ${memberDTO.memberNickname}
//                         <div class="err-nickname"></div>
//                         </div>
//                         </div>
//
//                         <div class="input-btn-wrap">
//                         <div class="input">
//                         <input
//                     type="email"
//                     id="userEmail"
//                     name="userName"
//                     class="disable input-text"
//                     placeholder="이메일 계정"
//                     value="rladnrtjdsla@gmail.com"
//                         />
//                         ${memberDTO.memberEmail}
//                         <div class="err-email"></div>
//                         </div>
//                         </div>
//                         <div class="input-btn-wrap">
//                         <div class="input">
//                         <input
//                     id="mobileNumber"
//                     name="mobileNumber"
//                     type="tel"
//                     class="disable input-text"
//                     placeholder="휴대폰 번호"
//                     value="01012341234"
//                         />
//                         ${member.memberPhone}
//                         </div>
//                         <div
//                     id="mobileCheckBtn"
//                     class="mobileAuthBtn btn"
//                     style="display: none"
//                     data-status="check"
//                         >
//                         <span>등록하기</span>
//                         </div>
//                         </div>
//                         <div id="emailChangeBtn" class="changeBtnTop btn changeModel" name="modalTrigger" data-status="change">
//                         <button type="submit">
//                         <span>변경</span>
//                         </button>
//                         </div>
//                         </div>
//                         </form>
//                         </div>
//                         <div class="wrapper-button">
//                         <button class="my-info" href="#">
//                         <div class="my-info-text">
//                         <h3>비밀번호 변경</h3>
//                     <p>현재 비밀번호 변경</p>
//                     </div>
//                     <div class="my-info-btn">
//                         <i class="wadizicon wa-chevron-right"></i>
//                         </div>
//                         </button>
//
//                         <div class="content-wrapper" style="display: none; padding-left: 0; margin-top: 0">
//                         <div class="account-wrap" style="padding: 0 25px 0px 0px; padding-left: 13px; padding-right: 35px;">
//                         <h2 class="small">비밀번호 변경</h2>
//                     <div class="email-input-wrap">
//                         <form id="saveForm" action="info-password" method="post">
//                         <input
//                     type="password"
//                     id="newPassword"
//                     name="newPassword"
//                     class="input-text passwordval"
//                     placeholder="비밀번호"
//                     maxlength="17"
//                         />
//                         <p id="passwordError" class="pwd-text">
//                         영문,숫자,특수문자를 조합한 8자 이상
//                     </p>
//                     <input
//                     type="password"
//                     id="newPasswordConfirm"
//                     name="memberPassword"
//                     class="input-text passwordvalconfirm"
//                     placeholder="비밀번호 확인"
//                     maxlength="17"
//                         />
//                         <p class="error-text" style="display: none">비밀번호가 같지 않습니다.</p>
//                     <div class="email-input-wrap small">
//                         <div class="btn-wrap smaller">
//                         <button id="saveBtn" type="submit" class="wz button primary block changeModel">
//                         확인
//                         </button>
//                         </div>
//                         </div>
//                         </form>
//                         </div>
//                         </div>
//                         </div>
//                         </div>
//
//                         <div class="wrapper-button">
//                         <button class="my-info" href="#">
//                         <div class="my-info-text">
//                         <h3>회원 정보 설정</h3>
//                     <p>이력서, 프로필 정보 변경</p>
//                     </div>
//                     <div class="my-info-btn">
//                         <i class="wadizicon wa-chevron-right"></i>
//                         </div>
//                         </button>
//                         <div class="content-wrapper" style="display: none">
//                         <div id="waccountContainer">
//                         <div id="tabContent1" class="tab-content">
//                         <div class="file-area">
//                         <div class="profile-wrapper-mypage">
//                         <h5 style="text-align: center">프로필 사진</h5>
//
//                     <div class="profile-area">
//                         <label for="profile">
//                         <img src="/images/myPage/children.png" alt="" />
//                         </label>
//                         </div>
//                         </div>
//                         <input type="file" style="display: none" id="profile" />
//                         </div>
//                         <div class="form-group">
//                         <label for="address-btu" class="address-label inputs" onclick="sample6_execDaumPostcode()">주소 변경</label>
//                     <div class="address-div" id="" >
//                         <input type="text" id="sample6_postcode"  class="inputs" onclick="sample6_execDaumPostcode()" placeholder="우편번호" th:value="${member.memberAddress.postcode}">
//                         <input type="button" name="address-btu" class="inputs" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
//                         </div>
//                         <input type="text" id="sample6_address" class="inputs"  onclick="sample6_execDaumPostcode()" placeholder="주소" th:value="${member.memberAddress.address}"><br>
//                         <input type="text" id="sample6_detailAddress" class="inputs" placeholder="상세주소1"th:value="${member.memberAddress.addressDetail}">
//                         <input type="text" id="sample6_extraAddress" class="inputs" placeholder="상세주소2"th:value="${member.memberAddress.addressSubDetail}">
//                         </div>
//                         <h5>한줄 소개 변경</h5>
//                     <div class="textarea-wrap">
//                         <textarea id="introduceme" maxlength="80"></textarea>
//                         </div>
//                         <div class="btn-bottom">
//                         <ul class="btn-div2">
//                         <li>
//                         <button
//                     type="button"
//                     class="save-btn button primary btn-mint changeModel"
//                     id="gray"
//                         >
//                         확인
//                         </button>
//                         </li>
//                         </ul>
//                         </div>
//                         </div>
//                         </div>
//                         </div>
//                         </div>
//
//                         <div class="wrapper-button">
//                         <button class="my-info" href="#">
//                         <div class="my-info-text">
//                         <h3>알림 설정</h3>
//                     <p>체험학습 알림</p>
//                     </div>
//                     <div class="my-info-btn">
//                         <i class="wadizicon wa-chevron-right"></i>
//                         </div>
//                         </button>
//                         <div class="content-wrapper" style="display: none">
//                         <div class="main-alarm">
//                         <div class="header-alarm">
//                         <h1 style="margin-bottom: 15px">알림설정</h1>
//                         </div>
//                         <div class="alarm-content">
//                         <div class="member-alarm-all">
//                         <label for="member-follow">회원・서비스 이벤트 혜택 알림 동의</label>
//                     <div class="member-toggle">
//                         <!--not-allow-btn은 동의 안되어있는 상태 -->
//                     <label for="allow" class="not-allow-btn"></label>
//                         </div>
//                         </div>
//                         <ul>
//                         <li>
//                         <div class="first-alarm">
//                         <label for="alarm-member">팔로잉</label>
//                         <div class="member-toggle">
//                         <!--allow-btn은 동의되어있는 상태 -->
//                     <label for="allow" class="allow-btn"></label>
//                         </div>
//                         </div>
//                         </li>
//                         <li>
//                         <div class="second-alarm">
//                         <label for="alarm-member">통솔자 알람</label>
//                     <div class="member-toggle">
//                         <label for="allow" class="allow-btn"></label>
//                         </div>
//                         </div>
//                         </li>
//                         </ul>
//                         </div>
//                         </div>
//                         </div>
//                         </div>
//                         </div>
//                         <!-- //account-wrap with-footer -->
//                         </div>
//                         <!-- E : #newContainer -->
//                         </div>
//                         </main>
//                           `
//         ;
//     });
//     $('.page-container').append(boardText3);
// }
//
// // 페이지 로딩 시 초기 리스트를 불러옴
// // boardService.getList(function(memberDTO) {
// //     page = 0;
// //     console.log(memberDTO.content);
// //     appendList(memberDTO);
// //     console.log(page + "페이지 로딩 시 초기화면")
// // });
//
// console.log("sadasdasd");






// ajax로 댓글 작성
const sendData = () => {
    console.log("sendData 들어옴@@@@");
    $.ajax({
        type: 'POST',
        url: `/mypage/info`,
        data: $('.formInfo').serialize(),
        success: function (result) {
            console.log(result);
            $('#replyContent').val('');
            $('#memberNickname').text(result);
            $(".comment-list-wrapper").empty();
        },
        error: function (error) {
            console.log('Error fetching data:', error);
        }
    });
};














/*asd*/
