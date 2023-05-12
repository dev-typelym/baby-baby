//문의 모달
let modalCheck;


$(".ask-admin-btn-wrapper").on("click", function(){
    $(".ask-modal").fadeIn(100);
    $(this).fadeOut(100);
})

$(".close-btn").on("click", function(){
    $("div.modal").hide(0);
    $("div.ask-modal").fadeOut(100);
    $(".ask-admin-btn-wrapper").fadeIn(100)
});



console.log($(".kids-wrapper"))
$($(".inner-content")[2]).on('click', function(){
    console.log($(".kids-wrapper").css('display'))
    if($(".kids-wrapper").css('display') == 'none'){
        $(".kids-wrapper").fadeIn(200);
    } else{
        $(".kids-wrapper").fadeOut(200);
    }
})
