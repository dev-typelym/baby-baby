



let $checkbox = $('#dropConfirm');
let $dropBtn = $('#btnDropOut');

/* 동의할때 체크박스 */
$checkbox.on('click', function() {
  if ($checkbox.prop('checked')) {
    $dropBtn.removeAttr('disabled');
    $dropBtn.css('opacity', '1');
    $dropBtn.css('background-color', '#00c4c4');
    $dropBtn.css('color', '#fff');
  } else {
    $dropBtn.prop('disabled', true);
    $dropBtn.css('opacity', '0.45');
    $dropBtn.css('background-color', '#00c4c4');
    $dropBtn.css('color', '#fff');
  }
});


/* 모달 */
$dropBtn.on('click', function() {
  $(".modal-mypage").show();
})

$('.modal-cancel-mypage').on('click', function(){
  $('.modal-mypage').hide();
})

/* 제출 코드 */
$('.modal-ok-mypage').on('click', function(){
  $('.form-submit').submit();
})