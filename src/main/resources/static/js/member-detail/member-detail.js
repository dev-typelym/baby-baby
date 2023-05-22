function getProfileImg(memberInfo) {
    let imgText = `
        <div class="image_section" style="background-image: url('members/display?fileName=Member/Profile/${memberInfo.memberProfilePath}/${memberInfo.memberProfileUUID}_${memberInfo.memberProfileOriginalName}');">
            <div class="image_section_div">
                <span class="image_section_span" style="background-image: url('members/display?fileName=Member/Profile/${memberInfo.memberProfilePath}/${memberInfo.memberProfileUUID}_${memberInfo.memberProfileOriginalName}'); border: 1px solid rgb(221, 226, 230);"></span>
            </div>
        </div>
    `;
    return imgText
}

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
let size = 2;
let parentBoardPage = 0;
let reviewPage = 0;
$.ajax({
    url: '/member/details/generals/' + memberId,
    type: 'POST',
    success: function (generalMember) {
        console.log(generalMember)
        $('.company_title_strong').html(generalMember.memberNickname)
        $('.company_title_p').html(generalMember.memberHiSentence)
        $('.follow_button').attr('follow', generalMember.isFollowed);
        console.log($('.image_section'))
        $('.image_section').css('background-image', `url('/members/display?fileName=Member/Profile/${generalMember.memberProfilePath}/${generalMember.memberProfileUUID}_${generalMember.memberProfileOriginalName}' `)
        $('.image_section_span').css('background-image', `url('/members/display?fileName=Member/Profile/${generalMember.memberProfilePath}/${generalMember.memberProfileUUID}_${generalMember.memberProfileOriginalName}' `)

        let followCountText =
            `
                     <div>
                            <span class="supporter_icon" aria-hidden="true"></span>
                            <span class="supporter_value">
                                팔로잉
                            </span>
                            <span class="supporter_value">
                                ${generalMember.followingCount} 명
                            </span>
                        </div>
                        <div>
                            <span class="supporter_icon" aria-hidden="true"></span>
                            <span class="supporter_value">
                                팔로워
                            </span>
                            <span class="supporter_value">
                                ${generalMember.followerCount} 명
                            </span>
                        </div>
                    </div>
            `;

        seeMoreParentsHandler();
        seeMoreReviewHandler();

        let seeMoreTextParentsBoard = '';
        if(generalMember.parentsBoards.length > 2){

             seeMoreTextParentsBoard =
                `
                        <div style="text-align: center;">
                                        <button class="see_more see_more_parents_board">
                                            더 보기
                                            <svg viewBox="0 0 32 32" focusable="false" role="presentation" class="button_svg" aria-hidden="true">
                                                <path d="M16 22.4L5.6 12l1.12-1.12L16 20.16l9.28-9.28L26.4 12 16 22.4z"></path>
                                            </svg>
                                        </button>
                                    </div>
            `;


        }


        let seeMoreReview = '';
        if(generalMember.reviews.length > 2){

            seeMoreReview =
                `
                        <div style="text-align: center;">
                                        <button class="see_more see_more_reviews">
                                            더 보기
                                            <svg viewBox="0 0 32 32" focusable="false" role="presentation" class="button_svg" aria-hidden="true">
                                                <path d="M16 22.4L5.6 12l1.12-1.12L16 20.16l9.28-9.28L26.4 12 16 22.4z"></path>
                                            </svg>
                                        </button>
                                    </div>
            `;


        }

        $('.main_content_second').html(followCountText)

        // 회원 상세 더보기
        $('.parent-board-section-1').append(seeMoreTextParentsBoard)
        $('.satisfaction_rating').append(seeMoreReview)

        $('.see_more_parents_board').on('click', seeMoreParentsHandler);
        $('.see_more_reviews').on('click', seeMoreReviewHandler)


        changeFollowBtn()










        //
        // let parentsBoardsText = '';
        //
        // if(generalMember.parentsBoards.length > 0){
        //     generalMember.parentsBoards.forEach((e,i) => {
        //         parentsBoardsText +=
        //             `
        //                                 <div class="real_content_div">
        //                                         <div class="project_card">
        //                                             <a class="project_card_a">
        //                                                 <div class="project_card_img"
        //                                                    data-event-filePath="${e.filePath}"
        //                                                    data-event-fileOriginalName="${e.fileOriginalName}"
        //                                                    data-event-fileUUID="${e.fileUUID}"
        //                                                 ></div>
        //                                             </a>
        //                                             <div class="project_card_div">
        //                                                 <div class="air_ear">${e.parentsBoardTitle}</div>
        //                                                 <div class="participation">
        //                                                     <div class="event-info-wrap">
        //                                                         <div class="proceeding_span total_amount">
        //                                                             <span>${e.parentsBoardContent}</span>
        //                                                         </div>
        //                                                         <div class="proceeding_span total_amount write-date" style="color: #000;">
        //                                                             <span>${formatDate(e.parentsBoardUpdateDate)}</span>
        //                                                         </div>
        //                                                     </div>
        //                                                 </div>
        //                                             </div>
        //                                         </div>
        //                                     </div>
        //                                     <!-- //행사 1개 -->
        //     `;
        //     })
        // }
        //
        //
        // $('.real_content').append(parentsBoardsText)
        //
        //
        // let reviewText = '';
        // if(generalMember.reviews.length > 0) {
        //
        //     generalMember.reviews.forEach((e, i) => {
        //         reviewText =
        //             `
        //     <li class="review_li">
        //                             <div class="page_content_ul reviews-container">
        //                                 <span class="real_name">${e.boardTitle}</span>
        //                                 <div style="display: inline-block; position: relative;">
        //                                     ${addStarsToContainer(e.reviewScore)}
        //                                 </div>
        //                             </div>
        //                             <p class="like_p">${e.eventContent}</p>
        //                         </li>
        //             `;
        //     })
        //     $(".satisfaction_rating ul").append(reviewText)
        }
    // }
})

/*<span class="review_span">${e.eventType}</span>*/

function seeMoreParentsHandler(){
        $.ajax({
            url: '/member/details/generals/getInfo/' + memberId + '?page=' + reviewPage + '&size=' + size,
            type: 'GET',
            success: function (memberDetails) {
                console.log(memberDetails);
                let parentBoardText = '';
                if(memberDetails.parentsBoards.length > 0){
                    memberDetails.parentsBoards.forEach((e,i)=>{
                        parentBoardText +=
                            `
                                    <div class="real_content_div parent-board-one-content">
                                         <div class="project_card">
                                            <a class="project_card_a" href="/parentsYard/detail/${e.id}">
                                                         <div class="project_card_img"
                                                         style = "background-image: url('/parentsBoardFiles/display?fileName=ParentsBoard/${e.parentsBoardFileDTOS[0].filePath}/${e.parentsBoardFileDTOS[0].fileUUID}_${e.parentsBoardFileDTOS[0].fileOriginalName}')"
                                                         ></div>
                                                     </a>
                                                     <div class="project_card_div">
                                                         <div class="air_ear">${e.parentsBoardTitle}</div>
                                                         <div class="participation">
                                                             <div class="event-info-wrap">
                                                                 <div class="proceeding_span total_amount">
                                                                     <span>${e.parentsBoardContent}</span>
                                                                 </div>
                                                                 <div class="proceeding_span total_amount write-date" style="color: #000;">
                                                                     <span>${formatDate(e.parentsBoardUpdateDate)}</span>
                                                                 </div>
                                                             </div>
                                                         </div>
                                                     </div>
                                                 </div>
                                               </div>
                        `;
                    })

                } else{
                    parentBoardText = '아직 작성된 부모님마당 게시글이 없습니다'
                }

                $('.parents-board-section').append(parentBoardText)

            if($('.parent-board-one-content').length == memberDetails.totalParentsBoardCount){
                $('.see_more_parents_board').hide()
            }


            }


        })
    parentBoardPage++
    }




function seeMoreReviewHandler(){
        $.ajax({
            url: '/member/details/generals/getInfo/' + memberId + '?page=' + reviewPage + '&size=' + size,
            type: 'GET',
            success: function (memberDetails) {
                console.log(memberDetails);
                let reviewText = '';
                if(memberDetails.reviews.length > 0){
                    memberDetails.reviews.forEach((e,i)=>{
                        reviewText +=
                            `
                            <li class="review_li">
                                    <div class="page_content_ul reviews-container">
                                        <span class="real_name">${e.boardTitle}</span>
                                        <div style="display: inline-block; position: relative;">
                                            ${addStarsToContainer(e.reviewScore)}
                                        </div>
                                    </div>
                                    <p class="like_p">${e.boardContent}</p>
                                    <span class="review_span">${e.eventLocation.address}</span>
                                </li>
                            `;
                    })

                } else{
                    reviewText = "아직 작성한 리뷰가 없습니다"
                }

                $('.review-ul').append(reviewText)

                if($('.review_li').length == memberDetails.totalReviewCount){
                    $('.see_more_reviews').hide()
                }


            }


    })
    reviewPage++
}






















/* 별점 생성 코드 */
function addStarsToContainer(starCount) {
    let coloredStar = `
    <div class="rating_star">
      <button class="one_rating_star">
        <svg viewBox="0 0 33 33" focusable="false" role="presentation" class="star_svg" aria-hidden="true">
          <path d="M16.5 27l-7.652 4.674a2.001 2.001 0 0 1-2.988-2.171l2.08-8.722-6.81-5.833a2 2 0 0 1 1.143-3.513l8.937-.716 3.443-8.28a2.001 2.001 0 0 1 3.694.001l3.443 8.279 8.938.716a2.001 2.001 0 0 1 1.141 3.513l-6.81 5.833 2.081 8.722a2.001 2.001 0 0 1-1.481 2.41 2.002 2.002 0 0 1-1.507-.24L16.5 27z" fill-rule="evenodd"></path>
        </svg>
        </button>
    </div>
  `;

    let unColoredStar = `
    <div class="rating_star">
      <button class="one_down_star">
      <svg viewBox="0 0 33 33" focusable="false" role="presentation" class="star_svg" aria-hidden="true">
             <path d="M16.5 27l-7.652 4.674a2.001 2.001 0 0 1-2.988-2.171l2.08-8.722-6.81-5.833a2 2 0 0 1 1.143-3.513l8.937-.716 3.443-8.28a2.001 2.001 0 0 1 3.694.001l3.443 8.279 8.938.716a2.001 2.001 0 0 1 1.141 3.513l-6.81 5.833 2.081 8.722a2.001 2.001 0 0 1-1.481 2.41 2.002 2.002 0 0 1-1.507-.24L16.5 27z" fill-rule="evenodd"></path>
        </svg>
</button>
    </div>
  `;

    let stars = '';

    // 채워진 별점 생성
    for (let i = 0; i < starCount; i++) {
        stars += coloredStar;
    }

    // 빈 별점 생성
    for (let i = starCount; i < 5; i++) {
        stars += unColoredStar;
    }

    return stars;
}

/* localDateTime을 Date로 깔끔하게 만드는 코드 */
function formatDate(originalDate) {
    let date = new Date(originalDate);
    let formattedDate = date.toLocaleDateString("ko-KR", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit"
    });

    // 마지막 점 제거
    formattedDate = formattedDate.replace(/\.$/, "");

    return formattedDate;
}


function getEventImg(selector) {
    $(selector).each(function () {
        let eventPath = $(this).data('event-file-Path');
        let eventUUID = $(this).data('event-file-UUID');
        let eventOriginalName = $(this).data('event-file-OriginalName');
        let eventURL = '/nowKidFiles/display?fileName = Event/' + eventPath + '/' + eventUUID + '_' + eventOriginalName;

        // 데이터를 변수에 저장
        $(this).css('background-image', 'url(' + eventURL + ')');
    });
}

function getProfileImg(selector) {
    $(selector).each(function () {
        let profilePath = $(this).data('member-profile-path');
        let profileUUID = $(this).data('member-profile-uuid');
        let profileFileOriginalName = $(this).data('member-profile-original-name');
        let profileURL = '/nowKidFiles/display?fileName=NowKids/' + profilePath + '/' + profileUUID + '_' + profileFileOriginalName;

        // 데이터를 변수에 저장
        $(this).css('background-image', 'url(' + profileURL + ')');
    });
}


// 팔로잉
let $followBtn = $('.follow_button')

$followBtn.click(function () {
    let isFollowed = $(this).attr('follow')
    console.log(typeof(isFollowed))
    $.ajax({
        url: '/follows/save',
        type: 'POST',
        data: {memberId:memberId, isFollowed : isFollowed},
        success: function () {
            if(isFollowed == 'false'){
                $('.follow_button_span svg').hide()
                $followBtn.css('color', 'black')
                $followBtn.css('backgroundColor', "white")
                $('.follow_button_span span').text("팔로잉중")
                $('.follow_button').attr('follow', 'true')
            } else if(isFollowed == 'true'){
                $('.follow_button_span svg').show()
                $followBtn.css('color', 'white')
                $followBtn.css('backgroundColor', "#00c4c4")
                $('.follow_button_span span').text("팔로우")
                $('.follow_button').attr('follow', 'false')
            }

        }
    })
})


function changeFollowBtn() {
    let $followBtn = $('.follow_button')
    let isFollowed = $('.follow_button').attr('follow')
    console.log(isFollowed)
    console.log(isFollowed == 'true')
    console.log(isFollowed == true)
    if(isFollowed == 'true'){
        $('.follow_button_span svg').hide()
        $followBtn.css('color', 'black')
        $followBtn.css('backgroundColor', "white")
        $('.follow_button_span span').text("팔로잉중")
        $('.follow_button').attr('follow', 'true')
    }
}