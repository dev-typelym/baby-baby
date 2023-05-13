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