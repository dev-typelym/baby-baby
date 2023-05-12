
/* 상세보기 모달 ===================== */
const $showDetail = $(".row");

// $showDetail.on('click', function(e){
//     $(".user-modal").show();
// })
$showDetail.on('click', function(e){
    if (!$(e.target).is('input[type="checkbox"]') && !$(e.target).hasClass('no-modal')) {
        $("#ask-detail").show();
    }
})

/* 상세 모달 닫기 */
const $modalCancel = $("#Capa_1");

$modalCancel.on("click", function(e) {
    $("#ask-detail").hide();
});

/* 삭제 모달 ======================== */
const $showDelete = $(".delete-button");
const $cancelDelete = $(".cancel-delete");

// "전체선택" 체크박스 클릭 이벤트
$('#allSelect').click(function() {
  if ($(this).is(':checked')) {
    // 전체선택 체크박스가 체크되었을 때 개별 체크박스 모두 체크
    $('input[name="check"]').prop('checked', true);
  } else {
    // 전체선택 체크박스가 체크해제되었을 때 개별 체크박스 모두 체크해제
    $('input[name="check"]').prop('checked', false);
  }
});

// 삭제 버튼 클릭 이벤트
$(".delete-button").click(function() {
  if ($("input[type=checkbox]:checked").length === 0) {
    // 선택된 체크박스가 없으면 모달 띄우기
    $(".delete-no-check-modal").show();
  } else {
    // 선택된 체크박스가 있으면 모달 띄우기
    $(".delete-modal").show();
  }
});

// 모달 확인 버튼 클릭 시 모달 닫기
$(".confirm-delete, .no-check-confirm-delete").click(function() {
  $(".delete-no-check-modal, .delete-modal").hide();
});

// 모달 취소 버튼 클릭 시 모달 닫기
$(".cancel-delete").click(function() {
  $(".delete-modal").hide();
});
/* 작성 모달 ======================== */
const $showWrite = $(".answer");
const $cancelWrite = $(".closeBtn");

$showWrite.on('click', function(e){
    $("#answer-write").show();
})

$cancelWrite.on("click", function(e) {
    $("#answer-write").hide();
});

/* 상세보기 버튼 js */
const detailBtnBodyList = document.querySelectorAll('.row');
const detailBtnList = document.querySelectorAll('.answer');

detailBtnBodyList.forEach(function(detailBtnBody, index) {
  detailBtnBody.addEventListener('mouseover', function() {
    detailBtnList[index].style.background = "#828282";
    detailBtnList[index].style.color = "#fff";
    detailBtnBodyList[index].style.background = "#828282";
    detailBtnBodyList[index].style.color = "#fff";
  });
  
  detailBtnBody.addEventListener('mouseout', function() {
    detailBtnList[index].style.background = "#fff";
    detailBtnList[index].style.color = "#000000";
    detailBtnBodyList[index].style.background = "#fff";
    detailBtnBodyList[index].style.color = "#000000";
  });
});

detailBtnList.forEach(function(detailBtn, index) {
  detailBtn.addEventListener('mouseover', function() {
    detailBtnList[index].style.background = "#828282";
    detailBtnList[index].style.color = "#fff";
    detailBtnBodyList[index].style.background = "#828282";
    detailBtnBodyList[index].style.color = "#fff";
  });
  
  detailBtn.addEventListener('mouseout', function() {
    detailBtnList[index].style.background = "#fff";
    detailBtnList[index].style.color = "#000000";
    detailBtnBodyList[index].style.background = "#fff";
    detailBtnBodyList[index].style.color = "#000000";
  });
});


