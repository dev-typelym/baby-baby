/* mypage-follow.js */

  $('.following-btn').click(function() {
    if($(this).hasClass('.following-btn-not')){
        $(this).addClass('.following-btn').removeClass('.following-btn-not')
        $(this).css('color', 'black')
        $(this).css('border', '1px #cdd3d8 solid')
        $(this).children().children().attr('viewBox', '0 0 48 48')
        $(this).children().children().children().attr('d', 'M18 39.6L4.8 26.4l3.36-3.36L18 32.76l21.84-21.72 3.36 3.36z')
        $(this).children().children().children().attr('fill', 'black')
    } else{
        $(this).addClass('.following-btn-not').removeClass('.following-btn')
        $(this).css('color', 'red')
        $(this).css('border', '1px red solid')
        $(this).children().children().attr('viewBox', '0 0 32 32')
        $(this).children().children().children().attr('d', 'M30.4 15.2H16.8V1.6h-1.6v13.6H1.6v1.6h13.6v13.6h1.6V16.8h13.6v-1.6z')
        $(this).children().children().children().attr('fill', 'red')
    }
  });