$(document).ready(function() {
    // 이메일 유효성 검사를 위한 정규식
    var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  
    // 입력한 이메일이 유효한지 확인하는 함수
    function checkEmail() {
      var email = $('#userName').val();
      var error = $('.helper.error');
      var isValid = true;
  
      // 이메일 형식이 올바른지 검사
      if (!emailRegex.test(email)) {
        error.eq(0).show();
        isValid = false;
      } else {
        error.eq(0).hide();
      }
  
      // 등록된 계정 검사
  
      if (isValid) {
        $('#send-modal').modal('show'); // 이메일 유효성 검사가 통과되면 모달창 띄우기
      }
    }
  
    // 링크 발송 버튼 클릭 이벤트 처리
    $('.button').on('click', function() {
      checkEmail();
    });
  });
  

