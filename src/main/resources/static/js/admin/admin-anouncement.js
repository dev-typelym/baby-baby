

/* 상세보기 모달 ===================== */
const $showDetail = $(".row");

// $showDetail.on('click', function(e){
//     $(".user-modal").show();
// })
$showDetail.on('click', function(e){
    if (!$(e.target).is('input[type="checkbox"]') && !$(e.target).hasClass('no-modal')) {
        $("#anouncement-detail").show();
    }
})
/* 상세 모달 닫기 */
const $modalCancel = $("#Capa_1");

$modalCancel.on("click", function(e) {
    $("#anouncement-detail").hide();
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
const $showWrite = $(".add-button");
const $cancelWrite = $(".closeBtn");

$showWrite.on('click', function(e){
    $("#anouncement-write").show();
})

$cancelWrite.on("click", function(e) {
    $("#anouncement-write").hide();
});

// 수정하기 썸네일
$('.update-profile').change(function(event) {
    var input = event.target;
    var reader = new FileReader();
    reader.onload = function() {
      var dataURL = reader.result;
      var image = $('.img-updated');
      image.attr('src', dataURL);
    };
    reader.readAsDataURL(input.files[0]);
  });

// 작성하기 썸네일
$('.insert-profile').change(function(event) {
    var input = event.target;
    var reader = new FileReader();
    reader.onload = function() {
      var dataURL = reader.result;
      var image = $('.img-inserted');
      image.attr('src', dataURL);
    };
    reader.readAsDataURL(input.files[0]);
  });