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

    // 이미지 추가 시
    $fileInput.change((event) => {
        file = event.target.files[0];

        var reader = new FileReader();
        reader.onload = function(e) {
            $(".first-image-box img").attr("src", e.target.result);
            $(".second-image-box img").attr("src", e.target.result);
            $(".image-display").show();
            $(".file-input-box").hide();
            $(".file-header").hide();
            $fileModal.hide();
        }

        reader.readAsDataURL(file);
    });

    // 이미지 삭제 버튼
    const $imageCancelButton = $(".image-cancel");

    // 이미지 삭제 이벤트
    $imageCancelButton.click(() => {
        file = "";
        $(".image-display").hide();
        $(".file-header").show();
        $(".file-input-box").show();
    });