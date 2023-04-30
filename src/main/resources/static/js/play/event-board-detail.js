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

/* 오른쪽 딸려 내려오는 부분의 더보기. 회사 상세보기 더보기 */
/* const closeBtn = $('.close');
const moreButton = $('.more_button_company');
const companyClose = $('.company_close_btn');
const companyOpen = $('.company_open_btn');

companyClose.on('click', function () {
    moreButton.hide();
    companyOpen.show();
    companyClose.hide();
});

companyOpen.on('click', function () {
    moreButton.show();
    companyOpen.hide();
    companyClose.show();
}); */
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
const moreUpButton = document.querySelector('.more_up_button');
const infoWrap = document.querySelector('.provide_information_full_wrap');
const path = document.querySelector('.more_up_svg path');

moreUpButton.addEventListener('click', () => {
    if (infoWrap.style.display === 'none') {
        infoWrap.style.display = 'block';
        // path.setAttribute('transform', 'rotate(180 16 16)');
        let angle = 0;

        const intervalId = setInterval(() => {
          angle += 5; // 회전 각도를 5도씩 증가시킵니다.
        
          if (angle <= 180) { // 회전 각도가 180도 이하인 경우
            path.setAttribute('transform', `rotate(${angle} 16 16)`);
          } else { // 회전 각도가 180도 이상인 경우
            angle = 360 - angle; // 180도를 빼서 원래 각도로 되돌립니다.
            path.setAttribute('transform', `rotate(${angle} 16 16)`);
            clearInterval(intervalId); // setInterval을 종료합니다.
          }
        }, 5); 
    } else {
        infoWrap.style.display = 'none';
        let angle = 0;

        const intervalId = setInterval(() => {
            angle += 5; // 회전 각도를 5도씩 증가시킵니다.
            path.setAttribute('transform', `rotate(${angle} 16 16)`);

            if (angle === 180) {
                // 회전 각도가 180도가 되면 setInterval을 종료합니다.
                clearInterval(intervalId);
            }
        }, 5);
    }
});
