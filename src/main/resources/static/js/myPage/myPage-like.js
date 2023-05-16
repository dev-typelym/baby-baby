/* mypage-like */
/* 하트 누르면 없어지게 하는 버튼 */
$('.wish-button').on('click', function(){
    let svgPath = $(this).children().children()
    if(svgPath.css('fill') == 'rgb(255, 0, 0)'){
        svgPath.css('fill', 'none')
    } else{
        svgPath.css('fill', 'red')
    }
})


