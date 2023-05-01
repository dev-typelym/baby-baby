

/* 프로젝트, 메이커 정보, 만족도 클릭시 css 변경  */
$(document).ready(function() {
    $('.page_content_span_not, .page_content_span').click(function() {
      if (!$(this).hasClass('page_content_span')) {
        $('.page_content_span').toggleClass('page_content_span_not page_content_span');
        $(this).toggleClass('page_content_span_not page_content_span');
      }
    });
  });
  
  
  /* 프로젝트, 메이커 정보, 만족도 클릭시 display 변경  */
  $(document).ready(function() {
    $('.page_content_div').hide();
    $('.maker_wrap').hide();
    $('#wrap_satisfaction').hide();
    $('.page_content_div').show(); // 기본적으로 프로젝트를 보여줍니다.
    
    $('.page_content_li').eq(0).click(function() { // 프로젝트 클릭 시
      $('.maker_wrap').hide();
      $('#wrap_satisfaction').hide();
      $('.page_content_div').show();
    });
    
    $('.page_content_li').eq(1).click(function() { // 메이커 정보 클릭 시
        $('.page_content_div').hide();
        $('.maker_wrap').hide();
        $('#wrap_satisfaction').show();
    });
  });
  
  