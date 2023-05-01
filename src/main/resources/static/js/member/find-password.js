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
  
      // 도담도담에 등록된 계정인지 검사
      // TODO: 도담도담 API와 연동하여 계정 검사 코드 작성
  
      if (isValid) {
        $('#send-modal').modal('show'); // 이메일 유효성 검사가 통과되면 모달창 띄우기
      }
    }
  
    // 링크 발송 버튼 클릭 이벤트 처리
    $('.button').on('click', function() {
      checkEmail();
    });
  });
  

const modalContainer = document.querySelector('.modal-container');
const modal = document.querySelector('.modal');
const modalCloseButtons = document.querySelectorAll('.modal-close');

function openModal() {
  modalContainer.style.display = 'block';
}

function closeModal() {
  modalContainer.style.display = 'none';
}

function handleModalClick(event) {
  if (event.target === modalContainer || event.target.classList.contains('modal-close')) {
    closeModal();
  }
}

modalCloseButtons.forEach((button) => {
  button.addEventListener('click', closeModal);
});

modalContainer.addEventListener('click', handleModalClick);
