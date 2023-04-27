/* mypage-info */


/* 눌렀을때 아래 나오도록 */


/* 기본 정보 설정은 ajax로 하면 될듯 */
$(".my-info").on("click", function(){
    let index = $(this).parent().index() - 1;
    let content = $(".content-wrapper");
    
    // 클릭된 요소가 보여지고 있는 경우 숨김 처리
    if (content.eq(index).is(":visible")) {
      content.eq(index).hide();
    }
    // 클릭된 요소가 보여지지 않은 경우 보여줌
    else {
      content.css("display", "none");
      content.eq(index).show();
    }
  });


console.log($(".changeBtn"))
/* 비밀번호 변경 */
$("#newPassword").on('keyup', function(){
    let pw = $("#newPassword").val();
    let num = pw.search(/[0-9]/g);
    let eng = pw.search(/[a-z]/ig);
    let spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

    if(pw.length < 8 || pw.length > 20){
   
    $("#passwordError").css('color', 'red')
     return false;
    }else if(pw.search(/\s/) != -1){
        $("#passwordError").css('color', 'red')
     return false;
    }else if(num < 0 || eng < 0 || spe < 0 ){
        $("#passwordError").css('color', 'red')
     return false;
    }else {
        $("#passwordError").css('color', 'black')
       return true;
    }
})

$("#newPasswordConfirm").on('keyup', function(){
    if($(this).val() == $("#newPassword").val()){
        $(".error-text").hide()
    } else {
        $(".error-text").show()
    }
})


// 확인 버튼을 누르면 해당 게시글 없어지는 것도 똑같음
$(".cancel-btn").on('click', function(){
    $($(".my-info")[1]).hide();
})


/* 토글버튼 */
$(document).ready(function() {
    $(".allow-btn, .not-allow-btn").each(function() {
        var value = '';
        if ($(this).hasClass('allow-btn')) {
            value = 'true';
        } else {
            value = 'false';
        }
        $(this).attr('value', value);
    });
});
/* 토글 클릭해서 색 나오면 true 넣어주고 아니면 false 넣어줌 */
$(".allow-btn, .not-allow-btn").on('click', function(){
    var value = '';
    if ($(this).hasClass('allow-btn')) {
        value = 'false';
        $(this).removeClass('allow-btn').addClass('not-allow-btn');
    } else {
        $(this).removeClass('not-allow-btn').addClass('allow-btn');
        value = 'true';
    }
    $(this).attr('value', value);
});