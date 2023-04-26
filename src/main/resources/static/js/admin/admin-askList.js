
/* 상세보기 모달 ===================== */
const $showDetail = $(".row");

$showDetail.on('click', function(e){
    $("#ask-detail").show();
})

/* 상세 모달 닫기 */
const $modalCancel = $("#Capa_1");

$modalCancel.on("click", function(e) {
    $("#ask-detail").hide();
});

/* 삭제 모달 ======================== */
const $showDelete = $(".delete-button");
const $cancelDelete = $(".cancel-delete");

$showDelete.on('click', function(e){
    $(".delete-modal").show();
})

$cancelDelete.on('click', function(e){
    $(".delete-modal").hide();
})

/* 작성 모달 ======================== */
const $showWrite = $(".answer");
const $cancelWrite = $(".closeBtn");

$showWrite.on('click', function(e){
    $("#answer-write").show();
})

$cancelWrite.on("click", function(e) {
    $("#answer-write").hide();
});

