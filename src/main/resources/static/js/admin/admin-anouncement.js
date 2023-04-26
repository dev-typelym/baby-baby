
/* 상세보기 모달 ===================== */
const $showDetail = $(".row");

$showDetail.on('click', function(e){
    $("#anouncement-detail").show();
})

/* 상세 모달 닫기 */
const $modalCancel = $("#Capa_1");

$modalCancel.on("click", function(e) {
    $("#anouncement-detail").hide();
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