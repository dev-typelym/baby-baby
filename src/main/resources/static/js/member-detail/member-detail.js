

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

let path = window.location.pathname;
let segments = path.split('/');
let memberId = segments.pop();

$.ajax({
    url: '/member/details/generals/' + memberId,
    type: 'POST',
    success: function (generalMember) {
        console.log(generalMember)

        $('.company_title_strong').html(generalMember.memberNickname)
        $('.company_title_p').html(generalMember.memberHiSentence)
        $('.supporter_value').html(generalMember.followingCount)
        $('.supporter_value').html(generalMember.followCount)

        let parentsBoardsText = '';

        if(generalMember.parentsBoards.length > 0){
            generalMember.parentsBoards.forEach((e,i) => {
                parentsBoardsText +=
                    `
                                        <div class="real_content_div">
                                                <div class="project_card">
                                                    <a class="project_card_a">
                                                        <div class="project_card_img" style="background-image: url('http://www.christiantoday.co.kr/files/article/db/2010/9/20/1284941413_9ad64ba26b.jpg?w=654');"></div>
                                                    </a>
                                                    <div class="project_card_div">
                                                        <div class="air_ear">[온 가족이 함께하는] 진흙놀이</div>
                                                        <div class="participation">
                                                            <div class="event-info-wrap">
                                                                <div class="proceeding_span total_amount">
                                                                    <span>아이들과 함께하는 진흙놀이 즐겁고 행복한 하루를 보내세요</span>
                                                                </div>
                                                                <div class="proceeding_span total_amount write-date" style="color: #000;">
                                                                    <span>2022.02.13</span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- //행사 1개 -->
            `;
            })
        }


        $('.real_content').append(parentsBoardsText)


        let reviewText = '';
        if(generalMember.reviews.length > 0) {

            generalMember.reviews.forEach((e, i) => {
                reviewText =
                    `
            <li class="review_li">
                                    <div class="page_content_ul reviews-container">
                                        <span class="real_name">${e.boardTitle}</span>
                                        <div style="display: inline-block; position: relative;">
                                            <div class="rating_star">
                                                <button class="one_rating_star">
                                                    <svg viewBox="0 0 33 33" focusable="false" role="presentation" class="star_svg" aria-hidden="true">
                                                        <path d="M16.5 27l-7.652 4.674a2.001 2.001 0 0 1-2.988-2.171l2.08-8.722-6.81-5.833a2 2 0 0 1 1.143-3.513l8.937-.716 3.443-8.28a2.001 2.001 0 0 1 3.694.001l3.443 8.279 8.938.716a2.001 2.001 0 0 1 1.141 3.513l-6.81 5.833 2.081 8.722a2.001 2.001 0 0 1-1.481 2.41 2.002 2.002 0 0 1-1.507-.24L16.5 27z" fill-rule="evenodd"></path>
                                                    </svg>
                                                </button>
                                            </div>
                                            <div class="rating_star">
                                                <button class="one_rating_star">
                                                    <svg viewBox="0 0 33 33" focusable="false" role="presentation" class="star_svg" aria-hidden="true">
                                                        <path d="M16.5 27l-7.652 4.674a2.001 2.001 0 0 1-2.988-2.171l2.08-8.722-6.81-5.833a2 2 0 0 1 1.143-3.513l8.937-.716 3.443-8.28a2.001 2.001 0 0 1 3.694.001l3.443 8.279 8.938.716a2.001 2.001 0 0 1 1.141 3.513l-6.81 5.833 2.081 8.722a2.001 2.001 0 0 1-1.481 2.41 2.002 2.002 0 0 1-1.507-.24L16.5 27z" fill-rule="evenodd"></path>
                                                    </svg>
                                                </button>
                                            </div>
                                            <div class="rating_star">
                                                <button class="one_rating_star">
                                                    <svg viewBox="0 0 33 33" focusable="false" role="presentation" class="star_svg" aria-hidden="true">
                                                        <path d="M16.5 27l-7.652 4.674a2.001 2.001 0 0 1-2.988-2.171l2.08-8.722-6.81-5.833a2 2 0 0 1 1.143-3.513l8.937-.716 3.443-8.28a2.001 2.001 0 0 1 3.694.001l3.443 8.279 8.938.716a2.001 2.001 0 0 1 1.141 3.513l-6.81 5.833 2.081 8.722a2.001 2.001 0 0 1-1.481 2.41 2.002 2.002 0 0 1-1.507-.24L16.5 27z" fill-rule="evenodd"></path>
                                                    </svg>
                                                </button>
                                            </div>
                                            <div class="rating_star">
                                                <button class="one_rating_star">
                                                    <svg viewBox="0 0 33 33" focusable="false" role="presentation" class="star_svg" aria-hidden="true">
                                                        <path d="M16.5 27l-7.652 4.674a2.001 2.001 0 0 1-2.988-2.171l2.08-8.722-6.81-5.833a2 2 0 0 1 1.143-3.513l8.937-.716 3.443-8.28a2.001 2.001 0 0 1 3.694.001l3.443 8.279 8.938.716a2.001 2.001 0 0 1 1.141 3.513l-6.81 5.833 2.081 8.722a2.001 2.001 0 0 1-1.481 2.41 2.002 2.002 0 0 1-1.507-.24L16.5 27z" fill-rule="evenodd"></path>
                                                    </svg>
                                                </button>
                                            </div>
                                            <div class="rating_star">
                                                <button class="one_rating_star">
                                                    <svg viewBox="0 0 33 33" focusable="false" role="presentation" class="star_svg" aria-hidden="true">
                                                        <path d="M16.5 27l-7.652 4.674a2.001 2.001 0 0 1-2.988-2.171l2.08-8.722-6.81-5.833a2 2 0 0 1 1.143-3.513l8.937-.716 3.443-8.28a2.001 2.001 0 0 1 3.694.001l3.443 8.279 8.938.716a2.001 2.001 0 0 1 1.141 3.513l-6.81 5.833 2.081 8.722a2.001 2.001 0 0 1-1.481 2.41 2.002 2.002 0 0 1-1.507-.24L16.5 27z" fill-rule="evenodd"></path>
                                                    </svg>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <p class="like_p">좋아요~~~~~~~~~~~~~~~~~~~~~~~~~\`</p>
                                    <span class="review_span">나로우주센터 우주과학관</span>
                                </li>
                    `;
            })
            $(".satisfaction_rating ul").append(reviewText)
        }
    }
})
  