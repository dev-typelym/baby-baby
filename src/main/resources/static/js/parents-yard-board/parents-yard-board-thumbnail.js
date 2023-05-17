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
let getOrgName = [];
let getPath = [];
let getUuid = [];


$fileInput.change((event) => {
    file = event.target.files[0];
    // 이미지 추가 시
    let formData = new FormData;
    var reader = new FileReader();
    reader.onload = function (e) {
        $(".first-image-box img").attr("src", e.target.result);
        $(".second-image-box img").attr("src", e.target.result);
        $(".image-display").show();
        $(".file-input-box").hide();
        $(".file-header").hide();
        $fileModal.hide();
    }
    formData.append("file", file)
    getFilePath(formData)
    setFile();

    reader.readAsDataURL(file);

});

// 파일을 올리면 path 받아오는 ajax
function getFilePath(formData) {
    $.ajax({
        url: "/parentsBoardFiles/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        async: false,
        enctype: 'multipart/form-data', // 멀티파트 요청 설정
        success: function (data) {
            console.log(data);
            getOrgName = data.orgNames;
            getUuid = data.uuids;
            getPath = data.paths;
        }
    });
}

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

