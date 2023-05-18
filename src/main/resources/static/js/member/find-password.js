// $(document).ready(function() {
//     // 이메일 유효성 검사를 위한 정규식
//     var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
//
//     // 입력한 이메일이 유효한지 확인하는 함수
//     function checkEmail() {
//       var email = $('#userName').val();
//       var error = $('.helper.error');
//       var isValid = true;
//
//       // 이메일 형식이 올바른지 검사
//       if (!emailRegex.test(email)) {
//         error.eq(0).show();
//         isValid = false;
//       } else {
//         error.eq(0).hide();
//       }
//
//       // 등록된 계정 검사
//
//       if (isValid) {
//         $('#send-modal').modal('show'); // 이메일 유효성 검사가 통과되면 모달창 띄우기
//       }
//     }
//
//     // 링크 발송 버튼 클릭 이벤트 처리
//     $('.button').on('click', function() {
//       checkEmail();
//     });
//   });

// 이메일
const $emailInput = $("#userName");
const $emailError = $(".error");

const $confirmBtn = $('#confirm-button');
const $mainDiv = $('.common.password-check');

// 이메일 정규식 이벤트 사용 및 함수
$emailInput.on("blur", function () {
    // 이메일 정규식
    let emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

    /* 이메일 중복 확인 */

    $.ajax({
        url: '/members/checkEmail', //Controller에서 요청 받을 주소
        method: 'POST', //POST 방식으로 전달
        data: {"memberEmail": $emailInput.val()},
        success: function (result) { //컨트롤러에서 넘어온 cnt값을 받는다
            if (result == 1) { // cnt가 1일 경우 -> 이미 존재하는 이메일
                $emailError.css("display", "block");
                $emailError.css('color', '#2bb673');
                $emailError.text(`아기자기에 등록되어 있는 계정입니다.`);
                $confirmBtn.prop('disabled', false);
                $confirmBtn.css('background-color', '#00c4c4').css('cursor','pointer');
            } else if (!emailPattern.test($emailInput.val())) {
                $emailError.text("이메일 형식이 올바르지 않습니다.");
                $emailError.css("display", "block");
                $emailError.css('color', 'red');
                $confirmBtn.prop('disabled', true);1
                $confirmBtn.css('background-color', 'gray').css('cursor','default');
            } else {
                $emailError.text("아기자기에 등록되어 있지 않은 계정입니다.");
                $emailError.css("display", "block");
                $emailError.css('color', 'red');
                $confirmBtn.prop('disabled', true);
                $confirmBtn.css('background-color', 'gray').css('cursor','default');
            }

        },
        error: function () {
            alert("에러 입니다");
        }
    });
});


$confirmBtn.on("click", function () {
    let text = '';
    $.ajax({
        url: '/members/sendEmail',
        method: 'POST',
        data: {"memberEmail": $emailInput.val()},
        success: function (result) {
            text = `<div class="common id-result">
                    <div class="email-wrap">
                        <p class="headline">${$emailInput.val()}</p>
                        <p class="result-text">
                            이메일이 발송 되었습니다. 
                            <br> 
                            확인 후 비밀번호를 변경해 주세요.
                            <br>
                        </p>
                        <div class="inner-container">
                            <button type="button" class="primary block button" onclick="location.href='/main'">메인으로 가기</button>
                            <button type="button" class="primary block button" onclick="location.href='/member/login'">로그인</button>
                        </div>
                    </div>
                </div>`
            $mainDiv.html(text);
        },
        error: function () {
            text = `<div class="common id-result"">
                    <div class="email-wrap">
                        <p class="headline">${$emailInput.val()}</p>
                        <p class="result-text">
                            이메일이 발송에 실패했습니다. 
                            <br> 
                            이메일을 다시 한번 확인해 주세요.
                            <br>
                        </p>
                        <div class="inner-container">
                            <button type="button" class="primary block button" onclick="location.href='/member/find-id'">이메일 확인하러 가기</button>
                            <button type="button" class="primary block button" onclick="location.href='/member/login'">로그인</button>
                        </div>
                    </div>
                </div>`
            $mainDiv.html(text);
        }
    });
});

