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

/* 좋아요 누르기 */
var clicked = false;
$('.purchase_wrap_div_btn').click(function () {
    if (clicked === false) {
        $('.heart_path').attr(
            'd',
            'M22.16 4h-.007a8.142 8.142 0 0 0-6.145 2.79A8.198 8.198 0 0 0 9.76 3.998a7.36 7.36 0 0 0-7.359 7.446c0 5.116 4.64 9.276 11.6 15.596l2 1.76 2-1.76c6.96-6.32 11.6-10.48 11.6-15.6v-.08A7.36 7.36 0 0 0 22.241 4h-.085z'
        );
        $('.heart_icon path').attr('fill', '#f66');
        clicked = true;
    } else {
        $('.heart_path').attr(
            'd',
            'M22.16 4h-.007a8.142 8.142 0 0 0-6.145 2.79A8.198 8.198 0 0 0 9.76 3.998a7.36 7.36 0 0 0-7.359 7.446c0 5.116 4.64 9.276 11.6 15.596l2 1.76 2-1.76c6.96-6.32 11.6-10.48 11.6-15.6v-.08A7.36 7.36 0 0 0 22.241 4h-.085zm-5.28 21.84l-.88.8-.88-.8h-.08C8.4 19.76 4 15.84 4 11.44l-.001-.082A5.76 5.76 0 0 1 9.928 5.6a6.542 6.542 0 0 1 4.865 2.232l.486.567h1.52l.48-.56a6.548 6.548 0 0 1 4.877-2.24l.084-.001a5.76 5.76 0 0 1 5.76 5.76l-.001.085c0 4.396-4.4 8.316-11.12 14.396z'
        );
        $('.heart_icon').css({
            color: '#868e96',
        });
        $('.heart_path').attr('fill', '#868e96');
        clicked = false;
    }
});

/* 카카오맵 */
// 지도 표시용 js

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(37.2504431, 127.1555927), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
    };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

// 마커가 표시될 위치입니다
var markerPosition = new kakao.maps.LatLng(37.2504431, 127.1555927);

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition,
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

function searchAddrFromCoords(coords, callback) {
    // 좌표로 행정동 주소 정보를 요청합니다
    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
}

// 현재 지도 설정좌표로 주소를 검색해서 지정한 위치에 표시합니다
searchAddrFromCoords(markerPosition, displayAdderessInfo);

// 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
function displayAdderessInfo(result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var infoDiv = document.getElementById('coordAddr');

        for (var i = 0; i < result.length; i++) {
            // 행정동의 region_type 값은 'H' 이므로
            if (result[i].region_type === 'H') {
                infoDiv.innerHTML = result[i].address_name;
                break;
            }
        }
    }
}

/* faq */
let $inquireTitle = $('.inquire-title');
let $inquireContent = $('.inquire-content');
$inquireTitle.on('click', function () {
    let index = $(this).parent().index();
    console.log($inquireContent.eq(index));
    $inquireContent.eq(index).show();
});
