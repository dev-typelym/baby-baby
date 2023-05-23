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
             '<div class="icon"> <img src="/images/member/ppt.png" alt=""> </div>' +
            '<div class="ResumeInfo_name">' + fileName + '</div>' +
            '<div class="ResumeInfo_size">' + fileSizeInKb + 'KB</div>' +
            '<div class="ResumeInfo_cancel"><a href="#" class="cancel-link"><i class="fa-times" aria-hidden="true"></i></a></div>' +
            '</div>';
        resumeContainer.html(resumeInfo);
    };
    reader.readAsDataURL(file);
}

/*file ajax*/
const fileArray = new Array();
let formData = new FormData(); // input 태그 담는 폼


/* 버튼을 감싸고있는 label객체 들고오기 */
const fileInput = document.getElementById("resume");

/* 버튼을 감싸고있는 label객체 클릭하면 위에 function handleFiles 실행 */
fileInput.addEventListener("change", function(event) {
    $fileListBox.empty();
    let index = 0;
    let files = e.target.files;
    console.log(files)
    let filesArr = Array.prototype.slice.call(files);
    let formData = new FormData();
    filesArr.forEach(file => formData.append("file", file))
    console.log(files)
    console.log(filesArr)
    console.log(e.target.files);


    $.ajax({
        url: "/members/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuids) {
            globalThis.uuids = uuids;
            console.log(globalThis.uuids)

            const dataTransfer = new DataTransfer();
            filesArr = dataTransfer.files;

            console.log(files)

            let inputFiles1 = "";
            files.forEach((file) => {
                inputFiles1 +=
                    `
                    <input type="hidden" name="files[${j}].fileOriginalName" value="${file.name}">
                    <input type="hidden" name="files[${j}].fileUUID" value="${globalThis.uuids[j]}">
                    <input type="hidden" name="files[${j}].filePath" value="${toStringByFormatting(new Date())}">
                    `
                j++;
            });
            j=0;
            $('#play-header-multi').append(inputFiles1);
        }
    });
});

function handleFiles(files) {
    console.log("files");
    console.log(files);


    /* 썸네일 담을 div의 부모 */
    // const thumbnailList = document.getElementById("thumbnail-list");

    for (let i = 0; i < files.length; i++) {

        /* 파일절대경로얻기 */
        const file = files[i];
        const reader = new FileReader();

        formData.append("file", file);


        // /* reader가 onload 할때 */
        // reader.onload = function(event) {
        //     /* 썸네일 담을 div와 그 자식의 span 선언 */
        //     const thumbnail = document.createElement("div");
        //     const thumbnailSpan = document.createElement("span");
        //
        //
        //     let result = event.target.result;
        //
        //     /* 썸네일 담을 div와 그 자식의 span에 썸네일 css와 x버튼 css 추가*/
        //     thumbnail.classList.add("imageThumbnail");
        //     thumbnailSpan.classList.add("closeImgButton");
        //
        //     /* 썸네일 담을 div에 절대경로 넣어주기 */
        //     thumbnail.style.backgroundImage = `url('${result}')`;
        //
        //     /* 썸네일 담을 div와 그 자식의 span 추가해주기 */
        //     thumbnailList.prepend(thumbnail);
        //     thumbnail.appendChild(thumbnailSpan);
        //
        //     /* x버튼 선언 */
        //     const closeButton = document.querySelector(".closeImgButton");
        //
        //     /* x버튼 누를 시 x버튼과 backgroundImage 지워주기 */
        //     closeButton.addEventListener('click', function (e) {
        //         e.preventDefault();
        //         file.value = "";
        //         this.style.display = 'none';
        //         thumbnail.style.backgroundImage = `url('')`;
        //         thumbnail.remove(thumbnail);
        //         $("#plus_picture").show();
        //     });
        //
        //     /* 파일 개수가 8개 이상이면 버튼숨기기 */
        //     if($(".imageThumbnail").length > 2 ){
        //         $("#plus_picture").hide();
        //         return;
        //     }
        //
        // };
        /* result 속성(attribute)에 담기 */
        reader.readAsDataURL(file);

        console.log("formData");
        console.log(formData);

    }

}
