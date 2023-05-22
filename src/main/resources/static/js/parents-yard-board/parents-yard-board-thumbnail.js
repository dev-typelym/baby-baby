// 현재 URL의 QueryString 가져오기
let queryString = window.location.search;

// QueryString을 파싱하여 객체로 변환
let params = new URLSearchParams(queryString);

// 특정 파라미터 값 가져오기
let boardTitle = params.get('parentsBoardTitle');
let boardContent = params.get('parentsBoardContent');
let eventId = params.get('eventId');


// 가져온 값 출력하기
console.log("boardTitle: " + boardTitle);
console.log("boardContent: " + boardContent);

$('input[name=parentsBoardTitle]').val(boardTitle)
$('input[name=parentsBoardContent]').val(boardContent)
$('input[name=eventId]').val(eventId)


const $sectionBox = $(".content-section");
const $inputTitle = $(".title");
const $maxCount = $(".text-max-count");


// 제목 입력 js
$inputTitle.keyup(() => {
    $maxCount.text((40 - $inputTitle.val().length) + "자 남음");
});


// 파일 버튼
const $fileButton = $(".file-button");
const $fileModal = $(".file-modal-display");
const $fileExit = $(".exit");

const $fileRegisterButton = $(".image-register-button");
const $fileInput = $(".image-file");

// 업로드 클릭시
$fileButton.click(() => {
    $fileModal.show();
});

// 모달창 X 누를 시
$fileExit.click(() => {
    $fileModal.hide();
});

// 이미지 등록 버튼 클릭 시
$fileRegisterButton.click(() => {
    $fileInput.click();
});

let file;
// upload ajax로 받아온 대표이미지 데이터
// let getOrgName = [];
// let getPath = [];
// let getUuid = [];
//
//
// $fileInput.change((event) => {
//     file = event.target.files[0];
//     // 이미지 추가 시
//     let formData = new FormData;
//     var reader = new FileReader();
//     reader.onload = function (e) {
//         $(".first-image-box img").attr("src", e.target.result);
//         $(".second-image-box img").attr("src", e.target.result);
//         $(".image-display").show();
//         $(".file-input-box").hide();
//         $(".file-header").hide();
//         $fileModal.hide();
//     }
//     formData.append("file", file)
//     getFilePath(formData)
//     setFile();
//
//     reader.readAsDataURL(file);
//
// });

// 파일을 올리면 path 받아오는 ajax
// function getFilePath(formData) {
//     $.ajax({
//         url: "/parentsBoardFiles/upload",
//         type: "post",
//         data: formData,
//         contentType: false,
//         processData: false,
//         async: false,
//         enctype: 'multipart/form-data', // 멀티파트 요청 설정
//         success: function (data) {
//             console.log(data);
//             getOrgName = data.orgNames;
//             getUuid = data.uuids;
//             getPath = data.paths;
//         }
//     });
// }
/*--------------------------------------------------------------------------------*/

FileList.prototype.forEach = Array.prototype.forEach;
const $fileListBox = $(".file-wrap");
/*  이미지 추가시 */
$fileInput.change((e) => {
    $fileListBox.empty();
    let index = 0;
    let files = e.target.files;
    let filesArr = Array.prototype.slice.call(files);
    let formData = new FormData();
    filesArr.forEach(file => formData.append("file", file))
    console.log(files)
    console.log(filesArr)
    console.log(e.target.files);


    $.ajax({
        url: "/parentsBoardFiles/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (uuids) {
            globalThis.uuids = uuids;
            console.log(globalThis.uuids)

            const dataTransfer = new DataTransfer();

            console.log(files)

            inputFiles = [];

            filesArr.forEach((file,i) => {

                inputFiles.push(file);

                let reader = new FileReader();
                reader.onload = (e) => {
                    let text = `
                        <div class="review-files" style="position:relative" id="file${i}">
                            <div class="image-file-content-box">
                                <div>
                                    <span class="image-file-info">원본 이미지</span>
                                    <div class="first-image-box">
                                        <img src="${e.target.result}" />
                                            <input type="hidden" name="parentsBoardFileDTOS[${i}].fileOriginalName" value="${file.name}">
                                            <input type="hidden" name="parentsBoardFileDTOS[${i}].fileUUID" value="${globalThis.uuids[i]}">
                                            <input type="hidden" name="parentsBoardFileDTOS[${i}].filePath" value="${toStringByFormatting(new Date())}">
                                    </div>
                                </div>
                                <div>
                                    <span class="image-file-info">정방향 이미지</span>
                                    <div class="second-image-box">
                                        <img src="${e.target.result}" />
                                    </div>
                                </div>
                            </div>
                            <div class="cancel-box">
                                <button type="button" class="image-cancel" id="${i}">
                                    <svg viewBox="0 0 40 40" focusable="false" role="presentation"
                                        class="image-cancel-icon" aria-hidden="true"
                                            style="width: 24px; height: 24px;">
                                        <path d="M33.4 8L32 6.6l-12 12-12-12L6.6 8l12 12-12 12L8 33.4l12-12 12 12 1.4-1.4-12-12 12-12z">
                                        </path>
                                    </svg>
                                </button>
                            </div>
                        </div>
                `;
                    $fileListBox.append(text);
                    $fileModal.hide();
                    $(".file-content-box").show();
                    index++;
                }
                reader.readAsDataURL(file);
                $('.preview-text').hide();
            });





        }
    });

});


/*-------------------------------------------------------*/
function leftPad(value) {
    if (value >= 10) {
        return value;
    }
    return `0${value}`;
}

function toStringByFormatting(source, delimiter = '/') {
    const year = source.getFullYear();
    const month = leftPad(source.getMonth() + 1);
    const day = leftPad(source.getDate());

    return [year, month, day].join(delimiter);
}


function getCouponParentsBoard() {
    $.ajax({
        url: "/coupon/parentsBoard",
        type: "get",
        success: function () {
        }
    });
}

$(".confirm-button").click(function() {
    getCouponParentsBoard();
});












/*----------------------------------------------------------------------------*/
const $form = $('.input-dataset');

function setFile() {
    let text = "";
    text = `
        <input type="hidden" name = "representFileOriginName" value= "${getOrgName[0]}" th:field="*{representFileOriginName}"/>
        <input type="hidden" name = "representFilePath" value= "${getPath[0]}" th:field="*{representFilePath}"/>
        <input type="hidden" name = "representFileUUID" value= "${getUuid[0]}" th:field="*{representFileUUID}"/>
    `;
    $form.append(text);
}

const $confirmButton = $('.confirm-button');

$confirmButton.on('click', function () {
    document.write.submit();
})

// 이미지 삭제 버튼
const $imageCancelButton = $(".image-cancel");

// 이미지 삭제 이벤트
$imageCancelButton.click(() => {
    file = "";
    $(".image-display").hide();
    $(".file-header").show();
    $(".file-input-box").show();
});

$('.file-wrap').on("click",".image-cancel", (e) => {
    let idx = e.currentTarget.id;
    inputFiles.splice(idx, 1);
    let box = '#file' + idx;
    $(box).remove();

    $fileListBox.find('div[id^="file"]').each(function(index) {
        $(this).attr('id', 'file' + index);
        $(this).find('button').attr('id', index);
    });


    if(inputFiles.length < 1){
        $('.preview-text').show();
    }
});
