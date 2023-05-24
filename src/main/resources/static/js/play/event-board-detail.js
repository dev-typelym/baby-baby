/* 오른쪽 고정시켜서 딸려 내려오게 하는 부분 */
const mainText = $('.main_text');
let isScrolling = false;

function handleScroll() {
    if (!isScrolling) {
        window.requestAnimationFrame(() => {
            const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
            const wrapperTop = $('.wrapper').offset().top;
            const mainTextHeight = mainText.height();

            // wrapperTop이 0 이하일 때만 mainText를 이동시킴
            if (wrapperTop <= 0) {
                mainText.css('top', Math.abs(wrapperTop) + 'px');

                // mainText가 wrapper 밑으로 넘어갈 때는 고정
                if (mainTextHeight >= $(window).height()) {
                    mainText.css('top', $(window).height() - mainTextHeight + 'px');
                }
            } else {
                mainText.css('top', 0);
            }

            isScrolling = false;
        });
    }

    isScrolling = true;
}

$(window).on('scroll', handleScroll);
const companyOpenBtn = $('.company_open');
const moteButtonCompany = $('.more_button_company');
const companyCloseBtn = $('.company_close');

companyOpenBtn.click(function () {
    moteButtonCompany.show();
    companyOpenBtn.hide();
    companyCloseBtn.css({
        display: 'flex',
        'align-items': 'center',
    });
});

/* 게시글 상세보기에서 내렸을 때 신청하기 버튼 딸려있는거 오른쪽 옆에 뜨게 하기 */
$(window).scroll(function() {
    var detailTab = $('.detail_tab');
    var mainText = $('.main_text');
    
    if ($(window).scrollTop() >= detailTab.offset().top) {
      mainText.css('display', 'block');
    } else {
      mainText.css('display', 'none');
    }
  });

companyCloseBtn.click(function () {
    moteButtonCompany.hide();
    companyOpenBtn.show();
    companyCloseBtn.hide();
});

/* 상세보기 더보기 */
$('.more_button').click(function () {
    const storyContainer = $('.story_container');
    const moreButtonSpan = $('.more_button_span');
    if (storyContainer.css('max-height') === '150px') {
        storyContainer.css('max-height', '100%');
        moreButtonSpan.addClass('expanded').removeClass('collapsed');
    } else {
        storyContainer.css('max-height', '150px');
        moreButtonSpan.addClass('collapsed').removeClass('expanded');
    }
});

/* 상품 정보 제공 고시 */
const moreUpButton = $('.more_up_button');
const infoWrap = $('.provide_information_full_wrap');
const path = $('.more_up_svg path');

moreUpButton.on('click', () => {
    if (infoWrap.css('display') === 'none') {
        infoWrap.css('display', 'block');
        let angle = 0;

        const intervalId = setInterval(() => {
            angle += 5; // 회전 각도를 5도씩 증가시킵니다.

            if (angle <= 180) {
                // 회전 각도가 180도 이하인 경우
                path.attr('transform', `rotate(${angle} 16 16)`);
            } else {
                // 회전 각도가 180도 이상인 경우
                angle = 360 - angle; // 180도를 빼서 원래 각도로 되돌립니다.
                path.attr('transform', `rotate(${angle} 16 16)`);
                clearInterval(intervalId); // setInterval을 종료합니다.
            }
        }, 5);
    } else {
        let angle = 180; // 회전 각도를 180도로 초기화합니다.

        const intervalId = setInterval(() => {
            angle += 5; // 회전 각도를 5도씩 증가시킵니다.
            path.attr('transform', `rotate(${angle} 16 16)`);

            if (angle >= 360) {
                // 회전 각도가 360도 이상이 되면 setInterval을 종료합니다.
                clearInterval(intervalId);
                infoWrap.css('display', 'none'); // display를 none으로 설정합니다.
            }
        }, 5);
    }
});

/* 메이커 정보 */
const makerUpButton = $('.maker_up_button');
const makerWrap = $('.maker_real_info');
const makerPath = $('.maker_svg path');

makerUpButton.click(() => {
    if (makerWrap.css('display') === 'none') {
        makerWrap.css('display', 'block');
        let makerAngle = 0;

        const intervalId = setInterval(() => {
            makerAngle += 5;
            if (makerAngle <= 180) {
                makerPath.attr('transform', `rotate(${makerAngle} 16 16)`);
            } else {
                makerAngle = 360 - makerAngle;
                makerPath.attr('transform', `rotate(${makerAngle} 16 16)`);
                clearInterval(intervalId);
            }
        }, 5);
    } else {
        let makerAngle = 180;

        const intervalId = setInterval(() => {
            makerAngle += 5;
            makerPath.attr('transform', `rotate(${makerAngle} 16 16)`);
            if (makerAngle >= 360) {
                clearInterval(intervalId);
                makerWrap.css('display', 'none');
            }
        }, 5);
    }
});

/* 자주묻는 질문 */
const faqButton = $('.faq_button');
const faqWrap = $('.faq_real_info');
const faqPath = $('.faq_svg path');

faqButton.click(() => {
    if (faqWrap.css('display') === 'none') {
        faqWrap.css('display', 'block');
        let faqAngle = 0;

        const intervalId = setInterval(() => {
            faqAngle += 5;
            if (faqAngle <= 180) {
                faqPath.attr('transform', `rotate(${faqAngle} 16 16)`);
            } else {
                faqAngle = 360 - faqAngle;
                faqPath.attr('transform', `rotate(${faqAngle} 16 16)`);
                clearInterval(intervalId);
            }
        }, 5);
    } else {
        let faqAngle = 180;

        const intervalId = setInterval(() => {
            faqAngle += 5;
            faqPath.attr('transform', `rotate(${faqAngle} 16 16)`);
            if (faqAngle >= 360) {
                clearInterval(intervalId);
                faqWrap.css('display', 'none');
            }
        }, 5);
    }
});

/* 게시물 더보기 */
const moreButton = $('.more_button');
const moreButtonSpan = $('.more_button_span');
const imgSection = $('.img_section');
const contentWrap = $('.content_wrap');
const arrowPath = $('.more_button_svg path');

// 스토리 더보기 버튼을 클릭했을 때 실행할 함수를 정의합니다.
function showAllContent() {
    const contentWrap = $('.content_wrap');
    contentWrap.css({
        overflow: 'visible',
        height: 'auto',
        position: 'static',
    });
    imgSection.css({
        position: 'static',
    });
    moreButtonSpan.text('체험학습 접기');
    // 다시 원래 상태로 되돌리기 위해 추가한 코드입니다.
    moreButton.off('click', showAllContent);
    moreButton.on('click', hideAllContent);
    arrowPath.attr('transform', 'rotate(360 16 16)');
}

// 스토리 감추기 버튼을 클릭했을 때 실행할 함수를 정의합니다.
function hideAllContent() {
    const contentWrap = $('.content_wrap');
    contentWrap.css({
        overflow: 'hidden',
        height: '2350px',
        position: 'relative',
    });
    // 다시 더보기 버튼을 클릭할 수 있도록 리스너를 등록합니다.
    imgSection.css({
        position: 'relative',
    });
    moreButtonSpan.text('체험학습 상세보기');
    moreButton.off('click', hideAllContent);
    moreButton.on('click', showAllContent);
    arrowPath.attr('transform', 'rotate(180 16 16)');
}

// 스토리 더보기 버튼에 클릭 이벤트 리스너를 등록합니다.
moreButton.on('click', showAllContent);

/* 아래쪽에 있는 상품 고시 설명에 1000단위로 , 찍는거 */
$('.bottom-price').text(function (i, text) {
    return Number(text).toLocaleString('en');
});

/* 카카오맵 */
// 지도 표시용 js

var mapContainer = document.getElementById('map'); // 지도를 표시할 div
var mapOption = {
    center: new kakao.maps.LatLng(37.2504431, 127.1555927), // 지도의 중심좌표
    level: 3, // 지도의 확대 레벨
};

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 대구 달성군 화원읍 류목정길 5 주소를 검색하여 지도에 표시합니다
var address = eventDTO.eventLocation.address;
geocoder.addressSearch(address, function (result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
        map.setCenter(coords); // 지도 중심 좌표 설정

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            position: coords,
        });

        // 마커가 지도 위에 표시되도록 설정합니다
        marker.setMap(map);

        // 지도 중심 좌표에 대한 주소 정보를 표출합니다
        var infoDiv = document.getElementById('coordAddr');
        infoDiv.innerHTML = result[0].address.address_name;
    }
});



/* faq */
let $inquireTitle = $('.inquire-title');
let $inquireContent = $('.inquire-content');
$inquireTitle.on('click', function () {
    let index = $(this).parent().index();
    console.log($inquireContent.eq(index));
    $inquireContent.eq(index).show();
});


/* 이벤트 날짜 */
let eventText =
    `
                                <span style="letter-spacing: 0">
                                                            <span class="event-start-year"
                                                                >${convertToKoreanDate(eventDTO.calendar.startDate)}</span>
                                                                ~
                                                            <span class="event-end-year">
                                                            ${convertToKoreanDate(eventDTO.calendar.endDate)}
                                                        </span>
    `;

$('.inline_right').html(eventText)

/* 아래 또르륵 정보넣기 */
let eventInfoText =
    `
    <div>
                                                    <div class="provide_information_wrap">
                                                        <div
                                                            class="provide_information_wrap_first"
                                                        >
                                                            <div class="notice_container">
                                                                <div class="notice_item">
                                                                    <div
                                                                        class="notice_item_title"
                                                                    >
                                                                        카테고리
                                                                    </div>
                                                                    <div
                                                                        class="notice_item_content"
                                                                    >
                                                                        ${convertCategory(eventDTO.category)}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="notice_container">
                                                                <div class="notice_item">
                                                                    <div
                                                                        class="notice_item_title"
                                                                    >
                                                                        모집 정원
                                                                    </div>
                                                                    <div
                                                                        class="notice_item_content"
                                                                    >
                                                                        ${eventDTO.eventRecruitCount}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="notice_container">
                                                                <div class="notice_item">
                                                                    <div
                                                                        class="notice_item_title"
                                                                    >
                                                                        가격
                                                                    </div>
                                                                    <div
                                                                        class="notice_item_content"
                                                                    >
                                                                        <span
                                                                            class="bottom-price"
                                                                            >${eventDTO.eventPrice}</span
                                                                        >원
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="notice_container">
                                                                <div class="notice_item">
                                                                    <div
                                                                        class="notice_item_title"
                                                                    >
                                                                        모집 기간
                                                                    </div>
                                                                    <div
                                                                        class="notice_item_content"
                                                                    >
                                                                        <span
                                                                            class="event-start"
                                                                            >${convertToKoreanDate(eventDTO.calendar.startDate)}</span
                                                                        >
                                                                        ~
                                                                        <span class="event-end"
                                                                            >${convertToKoreanDate(eventDTO.calendar.endDate)}</span
                                                                        >
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="notice_container">
                                                                <div class="notice_item">
                                                                    <div
                                                                        class="notice_item_title"
                                                                    >
                                                                        모이는 장소
                                                                    </div>
                                                                    <div
                                                                        class="notice_item_content"
                                                                    >
                                                                        ${eventDTO.eventLocation.address}
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div
                                                            class="provide_information_wrap_second"
                                                        >
                                                            <hr
                                                                class="provide_information_hr"
                                                            />
                                                        </div>
                                                    </div>
                                                </div>
    `;

$('.provide_information_full_wrap').html(eventInfoText)

/* 위 해쉬태그 */
$('.category-hash-tag').text(convertCategory(eventDTO.category))


/* 만약 이 사람이 이미 like를 한 상태라면----------------------------------- */
if(eventDTO.isEventLiked){
    $('.heart_icon').attr('aria-hidden', 'true')
}
if($('.heart_icon').attr('aria-hidden') == 'false'){
        result =
            `
                <path class="heart_path" d="M22.16 4h-.007a8.142 8.142 0 0 0-6.145 2.79A8.198 8.198 0 0 0 9.76 3.998a7.36 7.36 0 0 0-7.359 7.446c0 5.116 4.64 9.276 11.6 15.596l2 1.76 2-1.76c6.96-6.32 11.6-10.48 11.6-15.6v-.08A7.36 7.36 0 0 0 22.241 4h-.085zm-5.28 21.84l-.88.8-.88-.8h-.08C8.4 19.76 4 15.84 4 11.44l-.001-.082A5.76 5.76 0 0 1 9.928 5.6a6.542 6.542 0 0 1 4.865 2.232l.486.567h1.52l.48-.56a6.548 6.548 0 0 1 4.877-2.24l.084-.001a5.76 5.76 0 0 1 5.76 5.76l-.001.085c0 4.396-4.4 8.316-11.12 14.396z" fill="#868e96"></path>
                `;
} else{

    result =
        `
                <path class="heart_path" d="M22.16 4h-.007a8.142 8.142 0 0 0-6.145 2.79A8.198 8.198 0 0 0 9.76 3.998a7.36 7.36 0 0 0-7.359 7.446c0 5.116 4.64 9.276 11.6 15.596l2 1.76 2-1.76c6.96-6.32 11.6-10.48 11.6-15.6v-.08A7.36 7.36 0 0 0 22.241 4h-.085z" fill="#f66"></path>
                   `;
}
$('.heart_icon').html(result)
/* 만약 이 사람이 이미 like를 한 상태라면 ----------------------------*/






/* 컨텐츠 이미지 넣기 */
let imgText = '';

for (let i=1; i<eventDTO.files.length; i++){
    imgText +=
        `
            <img data-width="583" data-height="1966" class="inner_img_align inner_img_float inner_img_width inner_img_posi" src="/eventFiles/display?fileName=Event/${eventDTO.files[i].filePath}/${eventDTO.files[i].fileUUID}_${eventDTO.files[i].fileOriginalName}">
        `;
}

$('.event-img-area').html(imgText)



let urlPath = window.location.pathname;
let segments = urlPath.split('/');
let eventId = segments.pop();


$('.purchase_like_btn').on('click', function () {
    let lsLikeString = $('.heart_icon').attr('aria-hidden');

    let isLike = (lsLikeString === "true");
    console.log(isLike)
    $.ajax({
        url: '/eventLikes/save',
        type: 'POST',
        data: { "eventId": eventId, "isLike": isLike },
        success: function(response) {
            let result ='';


            if($('.heart_icon').attr('aria-hidden') == 'false'){
                result =
                    `
                <path class="heart_path" d="M22.16 4h-.007a8.142 8.142 0 0 0-6.145 2.79A8.198 8.198 0 0 0 9.76 3.998a7.36 7.36 0 0 0-7.359 7.446c0 5.116 4.64 9.276 11.6 15.596l2 1.76 2-1.76c6.96-6.32 11.6-10.48 11.6-15.6v-.08A7.36 7.36 0 0 0 22.241 4h-.085z" fill="#f66"></path>
                fill="#868e96"
                   `;
                $('.heart_icon').attr('aria-hidden', 'true')
            } else{
                result =
                    `
                <path class="heart_path" d="M22.16 4h-.007a8.142 8.142 0 0 0-6.145 2.79A8.198 8.198 0 0 0 9.76 3.998a7.36 7.36 0 0 0-7.359 7.446c0 5.116 4.64 9.276 11.6 15.596l2 1.76 2-1.76c6.96-6.32 11.6-10.48 11.6-15.6v-.08A7.36 7.36 0 0 0 22.241 4h-.085zm-5.28 21.84l-.88.8-.88-.8h-.08C8.4 19.76 4 15.84 4 11.44l-.001-.082A5.76 5.76 0 0 1 9.928 5.6a6.542 6.542 0 0 1 4.865 2.232l.486.567h1.52l.48-.56a6.548 6.548 0 0 1 4.877-2.24l.084-.001a5.76 5.76 0 0 1 5.76 5.76l-.001.085c0 4.396-4.4 8.316-11.12 14.396z" fill="#868e96"></path>
                    fill="#f66"                
                `;
                $('.heart_icon').attr('aria-hidden', 'false')
            }


            $('.heart_icon').html(result)



        },
    });
})



    function moveApply() {
        let url = `/event/applyEvent/${eventDTO.id}`;
        window.location.href = url;
    }


$('.button_leader').click(function (e) {
    let value = $(this).attr('value')
    let isApplied = value == 'true';
    console.log(isApplied)

    $.ajax({
        url: '/events/applyGuide',
        type: 'POST',
        data: {"eventId": eventId, "isLike": isApplied},
        success: function (response) {
            console.log(response)
            if(response == true){
                $('.guide_wrap_button').remove();
            }
        }
    })
})


if($('.button_leader').attr('value') == 'true'){
    $('.guide_wrap_button').remove();
}




function convertToKoreanDate(inputDate) {
    let date = new Date(inputDate);
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();

    let convertedDate = year + "년 " + month + "월 " + day + "일";
    return convertedDate;
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