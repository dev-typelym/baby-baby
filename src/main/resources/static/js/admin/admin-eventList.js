
/* 상세보기 모달 ===================== */
const $showDetail = $(".row");

// $showDetail.on('click', function(e){
//     $(".user-modal").show();
// })
$showDetail.on('click', function(e){
    if (!$(e.target).is('input[type="checkbox"]') && !$(e.target).hasClass('no-modal')) {
        $(".user-modal").show();
    }
})

/* 상세 모달 닫기 */
const $modalCancel = $("#Capa_1");

$modalCancel.on("click", function(e) {
    $(".user-modal").hide();
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

// /* 상세보기 버튼 js */
// const detailBtnBodyList = document.querySelectorAll('.row');
// const detailBtnList = document.querySelectorAll('.answer');

// detailBtnBodyList.forEach(function(detailBtnBody, index) {
//   detailBtnBody.addEventListener('mouseover', function() {
//     detailBtnList[index].style.background = "#828282";
//     detailBtnList[index].style.color = "#fff";
//     detailBtnBodyList[index].style.background = "#828282";
//     detailBtnBodyList[index].style.color = "#fff";
//   });
  
//   detailBtnBody.addEventListener('mouseout', function() {
//     detailBtnList[index].style.background = "#fff";
//     detailBtnList[index].style.color = "#000000";
//     detailBtnBodyList[index].style.background = "#fff";
//     detailBtnBodyList[index].style.color = "#000000";
//   });
// });

// detailBtnList.forEach(function(detailBtn, index) {
//   detailBtn.addEventListener('mouseover', function() {
//     detailBtnList[index].style.background = "#828282";
//     detailBtnList[index].style.color = "#fff";
//     detailBtnBodyList[index].style.background = "#828282";
//     detailBtnBodyList[index].style.color = "#fff";
//   });
  
//   detailBtn.addEventListener('mouseout', function() {
//     detailBtnList[index].style.background = "#fff";
//     detailBtnList[index].style.color = "#000000";
//     detailBtnBodyList[index].style.background = "#fff";
//     detailBtnBodyList[index].style.color = "#000000";
//   });
// });


