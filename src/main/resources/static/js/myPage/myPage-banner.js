$(document).ready(function() {
    // 기간 선택
    var optionSelect = $('.option-select');
    // 통솔자 선택
    var as = $('.as');
    // 글 내용
    var contentData = $('.contentData');
    // 이력서 제출
    var resumeContainer = $('#resume_container');
  
    // 제출 버튼
    var bottomButton = $('#bottom-button');
  
  //   // 제출 버튼 클릭 이벤트 처리
  //   bottomButton.on('click', function(event) {
  //     // 필수 입력 요소가 모두 입력되었는지 확인
  //     if (optionSelect.val() === '' || as.val() ==='' || contentData.val() === '' || resumeContainer.html() === '')  {
  //       showModal1();
  //       event.preventDefault(); // 버튼 기본 동작 방지
  //       return;
  //     }
  //     console.log("asd");
  //     showModal();
  //     // 제출 처리
  //     // TODO: 신청 처리 코드 작성
  //   });
  // });
  
  //   // 제출 버튼 클릭 이벤트 처리
  // bottomButton.on('click', function(event) {
  //        var modalTitle = $('.modal__title');
  //         var modalMessage = $('.modal__message');
  //     // 필수 입력 요소가 모두 입력되었는지 확인
  //     if (optionSelect.val() === '' && as.val() ==='' &&  contentData.val() === '' && resumeContainer.html() === '') {
  //       modalTitle.text('필수입력사항을 입력해주세요');
  //       console.log(optionSelect.val());
  //       modalMessage.text('');
  //       showModal();
  //       bottomButton.attr('type','button');
  //       return;
  //     }else{
  //       modalTitle.text('신청이 완료되었습니다');
  //         modalMessage.text('');
  //         showModal();
  //         bottomButton.attr('type','submit');
  //         // 제출 처리
  //         // TODO: 신청 처리 코드 작성
  //     }

    });

var maxFiles = 1;
var currentFiles = 0;


// 이력서 이미지만 받던거 수정본
function setResume(e) {
    var file = e.target.files[0];
    var resumeContainer = $("#resume_container");
    var maxFileSize = 2 * 1024 * 1024;
    var fileSize = file.size;
    var fileType = file.type;
    var fileName = file.name;
    var fileExtension = fileName.split('.').pop().toLowerCase();

    if (fileSize > maxFileSize) {
        alert("2MB 이하의 파일만 등록 가능합니다.");
        return false;
    }

    var reader = new FileReader();
    reader.onload = function(e) {
        var fileContent = e.target.result;
        var fileSizeInKb = Math.round(fileSize / 1024);
        var fileTypeIcon = '<i class="fa-file-o" aria-hidden="true"></i>';
        var resumeInfo = '<div class="ResumeInfo">' +
             '<div class="icon"> <img src="../../static/images/member/ppt.png" alt=""> </div>' + 
            '<div class="ResumeInfo_name">' + fileName + '</div>' +
            '<div class="ResumeInfo_size">' + fileSizeInKb + 'KB</div>' +
            '<div class="ResumeInfo_cancel"><a href="#" class="cancel-link"><i class="fa-times" aria-hidden="true"></i></a></div>' +
            '</div>';
        resumeContainer.html(resumeInfo);
    };
    reader.readAsDataURL(file);
}






