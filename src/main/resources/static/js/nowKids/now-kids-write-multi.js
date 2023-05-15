const $arrowButton = $(".nav-title-box");
const $arrowIcon = $(".nav-arrow-icon");
const $subNav = $(".nav-info-ul");
console.log(!$arrowIcon.hasClass("active"));
$arrowButton.click(() => {
    if (!$arrowIcon.hasClass("active")) {
        $arrowIcon.addClass("active");
        $arrowIcon.css("transition", "0.3s");
        $arrowIcon.css("transform", "rotate(0deg)");
        $subNav.slideDown();
    } else {
        $arrowIcon.removeClass("active");
        $arrowIcon.css("transition", "0.3s");
        $arrowIcon.css("transform", "rotate(-90deg)");
        $subNav.slideUp();
    }

});

const $inputTitle = $(".title");

// 제목 입력 js
$inputTitle.keyup(() => {
    $maxCount.text((40 - $inputTitle.val().length) + "자 남음");
});

// 여기 부분 작업하기

// 파일 버튼
const $fileButton = $(".file-button");
const $fileModal = $(".file-modal-display");
const $fileExit = $(".exit");

const $fileRegisterButton = $(".image-register-button");
const $fileInput = $(".image-file");
const $fileListBox = $(".file-list-box");


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

let inputFiles = [];
globalThis.arrayFile = new Array();
FileList.prototype.forEach = Array.prototype.forEach;
let j = 0;
// 이미지 추가 시
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
        url: "/nowKidsFiles/upload",
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
            files.forEach(file => {
                inputFiles1 +=
                    `
                    <input type="hidden" name="files[${j}].fileOriginalName" value="${file.name}">
                    <input type="hidden" name="files[${j}].fileUUID" value="${globalThis.uuids[j]}">
                    <input type="hidden" name="files[${j}].filePath" value="${toStringByFormatting(new Date())}">
                    `
                j++;
            });
            j=0;
            $('#header-nowKids').append(inputFiles1);
        }
    });

    inputFiles = [];

    filesArr.forEach((file,i) => {

        inputFiles.push(file);

        let reader = new FileReader();
        reader.onload = (e) => {
            let text = `
                        <div style="position:relative" id="file${i}">
                            <div class="image-file-content-box">
                                <div>
                                    <span class="image-file-info">원본 이미지</span>
                                    <div class="first-image-box">
                                        <img src="${e.target.result}" />
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
                                <button class="image-cancel" id="${i}">
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
    console.log(inputFiles);
});

$fileListBox.on("click",".image-cancel", (e) => {
    let idx = e.currentTarget.id;
    inputFiles.splice(idx, 1);
    let box = '#file' + idx;
    $(box).remove();

    $fileListBox.find('div[id^="file"]').each(function(index) {
        $(this).attr('id', 'file' + index);
        $(this).find('button').attr('id', index);
    });

    console.log(inputFiles);

    if(inputFiles.length < 1){
        $('.preview-text').show();
    }
});

//     /*****************************************************/
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


/* 썸네일 끝 */

// 현재 URL의 QueryString 가져오기
let queryString = window.location.search;

// QueryString을 파싱하여 객체로 변환
let params = new URLSearchParams(queryString);

// 특정 파라미터 값 가져오기
let eventId = params.get('eventId');
let eventDate = params.get('eventDate');

// 가져온 값 출력하기
console.log("eventId: " + eventId);
console.log("eventDate: " + eventDate);

$('input[name=eventId]').val(eventId)
$('input[name=eventDate]').val(eventDate)

// input작업 끝



// 카테고리 한국어로 바꾸는 코드
function convertCategory(category) {
//    AGRICULTURE, ART, TRADITION, CRAFT, SCIENCE, MUSEUM, SPORTS, ETC
    let categoryResult;

    if(category == "AGRICULTURE"){
        categoryResult = "농촌";
    } else if(category == "ART"){
        categoryResult = "예술";
    } else if(category == "TRADITION"){
        categoryResult = "전통";
    } else if(category == "CRAFT"){
        categoryResult = "공방";
    } else if(category == "SCIENCE"){
        categoryResult = "과학";
    } else if(category == "MUSEUM"){
        categoryResult = "박물관";
    } else if(category == "SPORTS"){
        categoryResult = "스포츠";
    } else{
        categoryResult = "기타";
    }
    return categoryResult;
}
console.log(englishCategory)
$($('.nav-header-span')[0]).html(convertCategory(englishCategory))