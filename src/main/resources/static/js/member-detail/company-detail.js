
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
      $('#wrap_satisfaction').hide();
      $('.maker_wrap').show();
    });
    
    $('.page_content_li').eq(2).click(function() { // 만족도 클릭 시
      $('.page_content_div').hide();
      $('.maker_wrap').hide();
      $('#wrap_satisfaction').show();
    });
  });

let path = window.location.pathname;
let segments = path.split('/');
let memberId = segments.pop();
let index = 0;

$.ajax({
  url: '/member/details/companies/' + memberId,
  type: 'POST',
  success: function(companyInfo) {
    console.log(companyInfo);

    // getProfileImg(".image_section");
    // getProfileImg(".image_section_span");
    $(".company_title_strong").text(companyInfo.memberNickname)
    $(".company_title_p").text(companyInfo.memberHiSentence)
    $($(".satisfaction_amount")[0]).text(companyInfo.reviews.length)
    $($(".satisfaction_amount")[1]).text(companyInfo.events.length)

    let avgScore = getAvgScore(companyInfo.reviews);
    if (isNaN(avgScore)) {
      avgScore = "아직 후기가 없습니다";
    }

    let markerContentText =
        `
        <div class="maker_content">
                            <div style="padding: 0;">
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <th class="th">상호/법인명</th>
                                            <td class="td">${companyInfo.memberNickname}</td>
                                        </tr>
                                        <tr>
                                            <th class="th">연락처</th>
                                            <td class="td">${companyInfo.memberPhone}</td>
                                        </tr>
                                        <tr>
                                            <th class="th">주소</th>
                                            <td class="td">${companyInfo.memberAddress.address} ${companyInfo.memberAddress.addressDetail} ${companyInfo.memberAddress.addressSubDetail}</td>
                                        </tr>
                                        <tr>
                                            <th class="th">이메일</th>
                                            <td class="td">${companyInfo.memberEmail}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
        `;

    /* 기업 정보 끝 */

    let satisfactionText =
        `
        <section class="satisfaction_rating">
                            <div style="margin-bottom: 48px;">
                                <h2 class="new_h2">전체 행사 후기</h2>
                                <span class="plus_satisfaction">${companyInfo.reviews.length}개 평가</span>
                            </div>
                            <div style="margin-bottom: 40px; text-align: center">
                                <strong class="strong_score">${avgScore}</strong>
                                <div class="bigStarContainer" style="display: inline-block; position: relative;">
                                   ${addStarsToContainer(avgScore)}
                                </div>
                            </div>
                            <ul>
                `;


    companyInfo.reviews.forEach((e,i) => {
      console.log(e)
      satisfactionText +=
          `
          <li class="review_li">
                                    <div class="page_content_ul">
                                        <span class="real_name"></span>
                                        <div class="starContainers" style="display: inline-block; position: relative;">
                                        ${addStarsToContainer(companyInfo.reviews.reviewScore)}
                                        </div>
                                    </div>
                                    <p class="like_p">${e.boardTitle}</p>
                                    <span class="review_span">${convertCategory(companyInfo.events[index].category)}</span>
                                </li>
          `;
    })

    satisfactionText +=
                  `
                            </ul>
                            <div style="text-align: center;">
<!--                                <button class="see_more">-->
<!--                                    더 보기-->
<!--                                    <svg viewBox="0 0 32 32" focusable="false" role="presentation" class="button_svg" aria-hidden="true">-->
<!--                                        <path d="M16 22.4L5.6 12l1.12-1.12L16 20.16l9.28-9.28L26.4 12 16 22.4z"></path>-->
<!--                                    </svg>-->
<!--                                </button>-->
                            </div>
                        </section>
        `;
    index++
    $('.maker_wrap').html(markerContentText)
    $('#wrap_satisfaction').html(satisfactionText)


    let nowEvents = [];
    let endedEvents = [];
    let upcomingEvents = [];
    if(companyInfo.length > 0) {
      companyInfo.events.forEach(event => {
        const eventStatus = classifyEvent(event.startDate, event.endDate);
        const eventObj = {
          eventId: event.id,
          eventTitle: event.boardTitle,
          eventFileUUID: event.eventFileDTOS[0].fileUUID,
          eventFileStatus: event.eventFileDTOS[0].fileStatus,
          eventFilePath: event.eventFileDTOS[0].filePath,
          eventFileOriginalName: event.eventFileDTOS[0].fileOriginalName,
          eventStartDate: formatDate(event.calendar.startDate),
          eventEndDate: formatDate(event.calendar.endDate),
          location: event.eventLocation,
          price: event.eventPrice,

        };
        if (eventStatus === "진행 중인 행사") {
          nowEvents.push(eventObj);
        } else if (eventStatus === "이미 지나간 행사") {
          endedEvents.push(eventObj);
        } else if (eventStatus === "예정된 행사") {
          upcomingEvents.push(eventObj);
        }
      });
    }
    console.log(nowEvents)
    console.log(endedEvents)
    console.log(upcomingEvents)

    let nowEventsText = '';
    let endedEventText = '';
    let upcommingText = '';
    /* 수정필요 -- 링크 넣기 */
    nowEvents.forEach((e,i) => {
      nowEventsText +=
          `
        <div class="real_content_div">
                                                <div class="project_card">
                                                    <a class="project_card_a" href="">
                                                        <div class="project_card_img"
                                                        data-event-file-Path="${e.eventFilePath}"
                                                        data-event-file-OriginalName="${e.eventFileOriginalName}"
                                                        data-event-file-UUID="${e.eventFileUUID}"
                                                        ></div>
                                                    </a>
                                                    <div class="project_card_div">
                                                        <div class="air_ear">${e.eventTitle}</div>
                                                        <div class="participation">
                                                            <div class="event-info-wrap">
                                                                <div class="proceeding_span total_amount">
                                                                    <span>${e.eventStartDate}</span> <span>~</span> <span>${e.eventEndDate}</span>
                                                                </div>
                                                                <div class="proceeding_span total_amount">
                                                                    <span>${e.location.address} ${e.location.addressDetail} ${e.location.addressSubDetail}</span>
                                                                </div>
                                                            </div>
                                                            <div class="proceeding_h2">
                                                                <span style="color: #00b2b2;">
                                                                    ${e.price}
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
        `;
    })

    if(upcomingEvents.length > 0){
      upcommingText =
          `
                            <div class="one_open_img">
                                            <div class="project_card">
                                                <a class="one_open_a">
                                                    <div class="one_project_card_img" style="background-image: url('//api.cdn.visitjeju.net/photomng/imgpath/201908/19/4a7384f3-3ea5-4b56-a519-20cd569686b1.jpg');"></div>
                                                </a>
                                                <div class="project_card_div">
                                                    <div class="holder">[4차 앵콜] 제주에서 가장 제주다운 곳, 사려니숲길 탐방</div>
                                                    <div class="participation">
                                                        <div class="proceeding_h2">
                                                            <span style="color: #00b2b2;">
                                                                ${formatDate(upcomingEvents[0].calendar.startDate)} 오픈 예정
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
        `;
    }

    endedEvents.forEach((e,i)=>{
      endedEventText +=
          `
                        <!-- // 컨텐츠 1개 -->
                                            <div class="six_content">
                                                <div class="project_card">
                                                    <a class="project_card_a">
                                                        <div class="real_cafe"
                                                        data-event=""
                                                        ></div>
                                                    </a>
                                                    <div class="project_card_div">
                                                        <div class="camping">${e.eventTitle}</div>
                                                        <div class="participation">
                                                            <div class="event-info-wrap">
                                                                <div class="proceeding_span total_amount">
                                                                    <span>${e.eventStartDate}</span> <span>~</span> <span>${e.eventEndDate}</span>
                                                                </div>
                                                                <div class="proceeding_span total_amount">
                                                                    <span>${e.location.address} ${e.location.addressDetail} ${e.location.addressSubDetail}</span>
                                                                </div>
                                                            </div>
                                                            <div class="proceeding_h2">
                                                                <span style="color: #00b2b2;">
                                                                    ${e.price}
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- // 컨텐츠 1개 -->
        `;
    })

    let eventsText =
        `
                                    <section class="content_wrap_section">
                                    <div class="proceeding">
                                        <h2 class="proceeding_h2">
                                            진행 중인 행사
                                            <span class="free_order">🌟</span>
                                        </h2>
                                        <span class="proceeding_span">
                                            ${nowEvents.length}건
                                        </span>
                                    </div>
                                    <div>
                                        <div class="real_content">
                                            <!-- 행사 1개 -->
                                            ${nowEventsText}
                                            <!-- //행사 1개 -->
                                        </div>
                                    </div>
                                </section>


                                <section class="content_wrap_section">
                                    <div class="proceeding">
                                        <h2 class="proceeding_h2">
                                            오픈 예정
                                            <span class="free_order">⏰</span>
                                        </h2>
                                        <span class="proceeding_span">
                                            ${upcomingEvents.length}건
                                        </span>
                                    </div>
                                    <div class="real_content">
                                        ${upcommingText}
                                    </div>
                                </section>
                                <section class="content_wrap_section">
                                    <div class="proceeding">
                                        <h2 class="proceeding_h2">
                                            종료된 행사
                                            <span class="free_order">🕒</span>
                                        </h2>
                                        <span class="proceeding_span">
                                            ${endedEvents.length}건
                                        </span>
                                    </div>
                                    <div>
                                        <div class="real_content">
                                        ${endedEventText}
                                        </div>
                                    </div>
                                    <div style="text-align: center;">
<!--                                        <button class="see_more">-->
<!--                                            더 보기-->
<!--                                            <svg viewBox="0 0 32 32" focusable="false" role="presentation" class="button_svg" aria-hidden="true">-->
<!--                                                <path d="M16 22.4L5.6 12l1.12-1.12L16 20.16l9.28-9.28L26.4 12 16 22.4z"></path>-->
<!--                                            </svg>-->
<!--                                        </button>-->
                                    </div>
                                </section>
      `;

    $(".content_wrap").html(eventsText)


  },
});


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

// 프로필 백그라운드 이미지 넣는 코드
//경로수정 필요
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

// 이벤트 파일 백그라운드
//경로수정 필요
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

// 별점 평균 구하는 코드
function getAvgScore(reviews) {
  let totalScore = 0;
  let reviewCount = reviews.length;

  // reviewScore를 모두 더함
  for (let i = 0; i < reviewCount; i++) {
    totalScore += reviews[i].reviewScore;
  }

  // 평균을 계산하여 반환
  let avgScore = totalScore / reviewCount;
  return avgScore;
}



function convertCategory(category) {
//    AGRICULTURE, ART, TRADITION, CRAFT, SCIENCE, MUSEUM, SPORTS, ETC
  let categoryResult;

  if(category == "AGRICULTURE"){
    categoryResult = "농촌";
  } else if(category == "ART"){
    categoryResult = "예술";
  } else if(category == "TRADITION"){
    categoryResult = "전통";
  } else if(category == "CRAFT"){
    categoryResult = "공방";
  } else if(category == "SCIENCE"){
    categoryResult = "과학";
  } else if(category == "MUSEUM"){
    categoryResult = "박물관";
  } else if(category == "SPORTS"){
    categoryResult = "스포츠";
  } else{
    categoryResult = "기타";
  }
  return categoryResult;
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


/* 지나간일정인지 아니면 진행중인지 끝난일정인지  */

function classifyEvent(startDate, endDate) {
  const now = new Date(); // 현재 날짜와 시간

  // 주어진 날짜 형식을 JavaScript Date 객체로 변환
  const start = new Date(startDate);
  const end = new Date(endDate);

  if (now > end) {
    // 현재 날짜가 종료일(endDate)보다 큰 경우 - 이미 지나간 일정
    return "이미 지나간 행사";
  } else if (now < start) {
    // 현재 날짜가 시작일(startDate)보다 작은 경우 - 예정된 일정
    return "예정된 행사";
  } else {
    // 현재 날짜가 시작일(startDate) 이후이며 종료일(endDate) 이전인 경우 - 진행 중인 일정
    return "진행 중인 행사";
  }
}
