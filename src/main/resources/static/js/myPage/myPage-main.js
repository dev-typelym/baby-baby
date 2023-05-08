//문의 모달
let modalCheck;




console.log($(".one-link")[7])
/* 문의 */

let inquiry = $(".one-link")[7]

$(inquiry).on("click", function(){
    $("div.ask-modal").fadeIn(100);
    console.log(this)
})

$(".close-btn").on("click", function(){
    $("div.modal").hide(0);
    $("div.ask-modal").fadeOut(0);
});


$(".ask-admin-btn").on('click', function(){
    
    $("div.ask-modal").fadeOut(0);
})