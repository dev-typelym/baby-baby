/* banner.html */

HTMLCollection.prototype.forEach = Array.prototype.forEach;
const banner = document.querySelector('div.banner');
const imageDiv = document.querySelectorAll('div.banner div');
const lastImageDiv = document.createElement('div');
const firstImageDiv = document.createElement('div');
const next = document.querySelector('div.next');
const prev = document.querySelector('div.prev');
const buttons = document.querySelectorAll('.buttons button');

let checkArrow = false;
let count = 1;
let auto = setInterval(autoSlide, 3000);
let temp = buttons[0];
const pageNow = document.querySelector('#page-now');

HTMLCollection.prototype.forEach = Array.prototype.forEach;
buttons.forEach((button) => {;
    button.addEventListener('click', () => {;
        clearInterval(auto);
        count = parseInt(button.innerHTML);
        changeButtonStyle();
        banner.style.transition = 'transform 0.3s';
        banner.style.transform = `translate(${-100 * count}vw)`;
        auto = setInterval(autoSlide, 3000);
    });
});

imageDiv.forEach(
    (div, i) => (div.style.backgroundImage = `url(/images/main/images/main-00${i + 1}.jpg)`)
);

banner.appendChild(lastImageDiv);
lastImageDiv.style.backgroundImage = `url(/images/main/images/main-001.jpg)`;

banner.insertBefore(firstImageDiv, document.querySelector('div.banner div'));
firstImageDiv.style.backgroundImage = `url(/images/main/images/main-00${imageDiv.length}.jpg)`;

banner.style.transform = `translate(-100vw)`;

function changeButtonStyle() {;
    if (temp) {;
        temp.style.background = 'white';
        temp.style.color = 'black';
    };
    buttons[count - 1].style.background = 'black';
    buttons[count - 1].style.color = 'white';
    temp = buttons[count - 1];
    pageNow.innerHTML = count;
};

function autoSlide() {;
    banner.style.transition = 'transform 0.3s';
    banner.style.transform = `translate(${-100 * ++count}vw)`;
    if (count == 6) {;
        count = 1;
        setTimeout(function () {;
            banner.style.transition = 'transform 0s';
            banner.style.transform = 'translate(-100vw)';
        }, 300);
    };
    changeButtonStyle();
};

prev.addEventListener('click', function () {;
    if (checkArrow) {;
        return;
    };
    checkArrow = true;
    clearInterval(auto);
    banner.style.transition = 'transform 0.3s';
    banner.style.transform = `translate(${-100 * --count}vw)`;
    if (count == 0) {;
        count = 5;
        setTimeout(function () {;
            banner.style.transition = 'transform 0s';
            banner.style.transform = `translate(${-100 * imageDiv.length}vw)`;
        }, 300);
    };
    changeButtonStyle();
    auto = setInterval(autoSlide, 3000);
    setTimeout(() => {;
        checkArrow = false;
    }, 300);
});

next.addEventListener('click', function () {;
    if (checkArrow) {;
        return;
    };
    checkArrow = true;
    clearInterval(auto);
    banner.style.transition = 'transform 0.3s';
    banner.style.transform = `translate(${-100 * ++count}vw)`;
    if (count == 6) {;
        count = 1;
        setTimeout(function () {;
            banner.style.transition = 'transform 0s';
            banner.style.transform = 'translate(-100vw)';
        }, 300);
    };
    changeButtonStyle();
    auto = setInterval(autoSlide, 3000);
    setTimeout(() => {;
        checkArrow = false;
    }, 300);
});


function nowKidsBannerActions(){
    /* 세번째 배너 */
    HTMLCollection.prototype.forEach = Array.prototype.forEach;
    const thirdBanner = document.querySelector('div.third-banner');
    const thirdImageDiv = document.querySelectorAll('div.third-banner div .third-banner-image');
    const thirdFirstImageDiv = document.createElement('div');
    const thirdNext = document.querySelector('div.third-next');
    const thirdPrev = document.querySelector('div.third-prev');
    const thirdButtons = document.querySelectorAll('.third-buttons button');

    let thirdCheckArrow = false;
    let thirdCount = 1;
    const thirdPageNow = document.querySelector('#third-page-now');


    // thirdImageDiv.forEach(
    //   (div, i) => (div.style.backgroundImage = `url(/images/main/images/third-00${i + 1}.jpg)`)
    // );

    const clonedThirdBanner1 = $('.third-banner1').clone()[0];
    thirdBanner.appendChild(clonedThirdBanner1);

    const clonedThirdBanner2 = $('.third-banner2').clone()[0];
    thirdBanner.appendChild(clonedThirdBanner2);

    const clonedThirdBanner3 = $('.third-banner3').clone()[0];
    thirdBanner.appendChild(clonedThirdBanner3);

    const clonedThirdBanner4 = $('.third-banner4').clone()[0];
    thirdBanner.appendChild(clonedThirdBanner4);



    const clonedThirdBanner5 = $('.third-banner5').clone()[0];
    thirdBanner.insertBefore(clonedThirdBanner5, document.querySelector('div.third-banner .third-banner1'));

    thirdBanner.style.transform = `translate(-326px)`;

    thirdPrev.addEventListener('click', function () {
      if (thirdCheckArrow) {
        return;
      }
      thirdCheckArrow = true;
      thirdCount--;
      if (thirdCount === 0) {
        thirdBanner.style.transition = 'transform 0s';
        thirdBanner.style.transform = `translate(${-326 * (thirdImageDiv.length + 1)}px)`;
        setTimeout(() => {
          thirdCount = thirdImageDiv.length;
          thirdBanner.style.transition = 'transform 0.3s';
          thirdBanner.style.transform = `translate(${-326 * thirdCount}px)`;
          thirdCheckArrow = false;
        }, 10);
      } else {
        thirdBanner.style.transition = 'transform 0.3s';
        thirdBanner.style.transform = `translate(${-326 * thirdCount}px)`;
        setTimeout(() => {
          thirdCheckArrow = false;
        }, 300);
      }
    });

    thirdNext.addEventListener('click', function () {
      if (thirdCheckArrow) {
        return;
      }
      thirdCheckArrow = true;
      thirdCount++;
      thirdBanner.style.transition = 'transform 0.3s';
      thirdBanner.style.transform = `translate(${-326 * thirdCount}px)`;
      if (thirdCount == thirdImageDiv.length + 1) {
        thirdCount = 1;
        setTimeout(function () {
                thirdBanner.style.transition = 'transform 0s';
                thirdBanner.style.transform = 'translate(-326px)';
            }, 300);
      }
      setTimeout(() => {
        thirdBanner.style.transition = 'transform 0s';
        thirdBanner.style.transform = `translate(${-326 * thirdCount}px)`;
        thirdCheckArrow = false;
      }, 300);
    });
}

function parentsBannerActions(){
    /* 이벤트 배너 js */
    HTMLCollection.prototype.forEach = Array.prototype.forEach;
    const eventBanner = document.querySelector('div.event-banner');
    const eventImageDiv = document.querySelectorAll('div.event-banner div .event-banner-image');
    const eventFirstImageDiv = document.createElement('div');
    const eventNext = document.querySelector('div.event-next');
    const eventPrev = document.querySelector('div.event-prev');
    const eventButtons = document.querySelectorAll('.event-buttons button');

    let eventCheckArrow = false;
    let eventCount = 1;
    const eventPageNow = document.querySelector('#event-page-now');


    // eventImageDiv.forEach(
    //   // (div, i) => (div.style.backgroundImage = `url(/images/main/images/event-00${i + 1}.jpg)`)
    // );

    const clonedEventBanner1 = $('.event-banner1').clone()[0];
    eventBanner.appendChild(clonedEventBanner1);

    const clonedEventBanner2 = $('.event-banner2').clone()[0];
    eventBanner.appendChild(clonedEventBanner2);

    const clonedEventBanner3 = $('.event-banner3').clone()[0];
    eventBanner.appendChild(clonedEventBanner3);

    const clonedEventBanner4 = $('.event-banner4').clone()[0];
    eventBanner.appendChild(clonedEventBanner4);



    const clonedEventBanner5 = $('.event-banner5').clone()[0];
    eventBanner.insertBefore(clonedEventBanner5, document.querySelector('div.event-banner .event-banner1'));

    eventBanner.style.transform = `translate(-326px)`;

    eventPrev.addEventListener('click', function () {
        if (eventCheckArrow) {
            return;
        }
        eventCheckArrow = true;
        eventCount--;
        if (eventCount === 0) {
            eventBanner.style.transition = 'transform 0s';
            eventBanner.style.transform = `translate(${-326 * (eventImageDiv.length + 1)}px)`;
            setTimeout(() => {
                eventCount = eventImageDiv.length;
                eventBanner.style.transition = 'transform 0.3s';
                eventBanner.style.transform = `translate(${-326 * eventCount}px)`;
                eventCheckArrow = false;
            }, 10);
        } else {
            eventBanner.style.transition = 'transform 0.3s';
            eventBanner.style.transform = `translate(${-326 * eventCount}px)`;
            setTimeout(() => {
                eventCheckArrow = false;
            }, 300);
        }
    });

    eventNext.addEventListener('click', function () {
        if (eventCheckArrow) {
            return;
        }
        eventCheckArrow = true;
        eventCount++;
        eventBanner.style.transition = 'transform 0.3s';
        eventBanner.style.transform = `translate(${-326 * eventCount}px)`;
        if (eventCount == eventImageDiv.length + 1) {
            eventCount = 1;
            setTimeout(function () {
                eventBanner.style.transition = 'transform 0s';
                eventBanner.style.transform = 'translate(-326px)';
            }, 300);
        }
        setTimeout(() => {
            eventBanner.style.transition = 'transform 0s';
            eventBanner.style.transform = `translate(${-326 * eventCount}px)`;
            eventCheckArrow = false;
        }, 300);
    });
}

function eventBannerActions() {
    /* 두번째 배너 */
    const secondBanner = document.querySelector('.second-banner');
    const secondImageDiv = document.querySelectorAll('.second-banner .second-banner-image');
    const secondPrev = document.querySelector('.second-prev');
    const secondNext = document.querySelector('.second-next');
    let secondCheckArrow = false;
    let secondCount = 1;

    // secondImageDiv.forEach((div, i) => {
    //   div.style.backgroundImage = `url(/images/main/images/second-00${i + 1}.jpg)`;
    // });

    const clonedsecondBanners = document.querySelectorAll('.second-banner > div').values();

    for (const clonedBanner of clonedsecondBanners) {
        secondBanner.appendChild(clonedBanner.cloneNode(true));
    }

    const clonedsecondBanner5 = document.querySelector('.second-banner5').cloneNode(true);
    secondBanner.insertBefore(clonedsecondBanner5, document.querySelector('.second-banner .second-banner1'));

    secondBanner.style.transform = `translateX(-261px)`;

    secondPrev.addEventListener('click', function () {
        if (secondCheckArrow) {
            return;
        }
        secondCheckArrow = true;
        secondCount--;
        if (secondCount === 0) {
            secondBanner.style.transition = 'transform 0s';
            secondBanner.style.transform = `translateX(${-261 * (secondImageDiv.length + 1)}px)`;
            setTimeout(() => {
                secondCount = secondImageDiv.length;
                secondBanner.style.transition = 'transform 0.3s';
                secondBanner.style.transform = `translateX(${-261 * secondCount}px)`;
                secondCheckArrow = false;
            }, 10);
        } else {
            secondBanner.style.transition = 'transform 0.3s';
            secondBanner.style.transform = `translateX(${-261 * secondCount}px)`;
            setTimeout(() => {
                secondCheckArrow = false;
            }, 300);
        }
    });

    secondNext.addEventListener('click', function () {
        if (secondCheckArrow) {
            return;
        }
        secondCheckArrow = true;
        secondCount++;
        secondBanner.style.transition = 'transform 0.3s';
        secondBanner.style.transform = `translateX(${-261 * secondCount}px)`;
        if (secondCount === secondImageDiv.length + 1) {
            secondCount = 1;
            setTimeout(function () {
                secondBanner.style.transition = 'transform 0s';
                secondBanner.style.transform = 'translateX(-261px)';
            }, 300);
        }
        setTimeout(() => {
            secondBanner.style.transition = 'transform 0s';
            secondBanner.style.transform = `translateX(${-261 * secondCount}px)`;
            secondCheckArrow = false;
        }, 300);
    });

    /* 두번째 배너 액션 */
    $('.second-banners').each((i, e) => {
        $(e).mouseover(() => {
            $($(".recruit-box-context")[i]).css("transform", "translate(0, -180px)");
            $($(".recruit-box-context")[i]).css("background", "linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.4))");
        });
        $(e).mouseout(() => {
            $($(".recruit-box-context")[i]).css("transform", "translate(0, 0px)");
            $($(".recruit-box-context")[i]).css("background", "linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0))");
        });
    });
    // /* 좋아요 버튼 */
    // /* 두번째 리스트 */
    function secondListLikeOn(num) {
        $('.second-list-like-btn' + num).css('display', 'none');
        $('.second-list-like-red-btn' + num).css('display', 'block');
    }

    function secondListLikeOff(num) {
        $('.second-list-like-btn' + num).css('display', 'block');
        $('.second-list-like-red-btn' + num).css('display', 'red');
    }
}



/* 문의 버튼  */
const askAdminBtnWrapper = document.querySelector('.ask-admin-btn-wrapper');
let lastScrollPosition = window.pageYOffset;

window.addEventListener('scroll', function() {
  const currentScrollPosition = window.pageYOffset;

  if (currentScrollPosition > lastScrollPosition) {
    // 스크롤이 내려갈 때
    askAdminBtnWrapper.style.display = 'none';
  } else {
    // 스크롤이 올라갈 때
    askAdminBtnWrapper.style.display = 'block';
  }

  lastScrollPosition = currentScrollPosition;
});

/* top 버튼 */
const topButton = document.querySelector('.top-btn');
let lastTopButtonScrollPosition = window.pageYOffset;

window.addEventListener('scroll', function() {
  const topButttonCurrentScrollPosition = window.pageYOffset;

  if (topButttonCurrentScrollPosition > lastTopButtonScrollPosition) {
    // 스크롤이 내려갈 때
    topButton.style.display = 'none';
  } else {
    // 스크롤이 올라갈 때
    topButton.style.display = 'block';
  }

  lastTopButtonScrollPosition = topButttonCurrentScrollPosition;
});


topButton.addEventListener('click', function() {
  // 맨 위로 스크롤되도록 설정
  window.scrollTo({
    top: 0,
    behavior: 'smooth'
  });

  // 버튼 숨기기
  topButton.style.display = 'none';
});

// 스크롤 이벤트 리스너 추가
window.addEventListener('scroll', function() {
  // 페이지가 맨 위로 스크롤되었을 때 버튼 숨기기
  if (window.scrollY === 0) {
    topButton.style.display = 'none';
  }
});

//문의 모달
let modalCheck;
function showWarnModal(modalMessage){
    modalCheck = false;
    $("div#content-wrap").html(modalMessage)
    $("div.modal").css("display", "flex").hide().fadeIn(400);
    $("div.ask-modal").css("animation", "popUp 0.4s");
    $("div.ask-modal").css("display", "block").hide().fadeIn(400);
    setTimeout(function(){modalCheck = true;}, 400);
}

function categoryModal() {

    let modalMessage = '';


    showWarnModal(modalMessage);
}


$(".close-btn").on("click", function(){
    $('html, body').css('overflow', 'auto');
    if(modalCheck){
        $("div.modal").fadeOut(0);
        $("div.ask-modal").fadeOut(0);
    }
});

function announcementBannerActions(){

    const frequentSelect = document.querySelector('.guide-frequent-select');
    const useSelect = document.querySelector('.guide-use-select');
    const reserveSelect = document.querySelector('.guide-reserve-select');
    const use = document.querySelector('.guide-frequent');
    const frequent = document.querySelector('.guide-use');
    const reserve = document.querySelector('.guide-reserve');


    frequentSelect.style.width = '';
    useSelect.style.width = '';
    reserveSelect.style.width = '';




    let currentSelection = null; // 현재 선택된 영역을 추적하기 위한 변수

    /* frequentSelect를 마우스 오버시 다른 요소 바꾸기 */
    if (frequentSelect) {
      frequentSelect.addEventListener('mouseover', function() {
        if (currentSelection !== 'frequent') {
          currentSelection = 'frequent';

          frequentSelect.style.width = '378px';
          frequentSelect.style.backgroundColor = 'rgb(0, 196, 196)';
          useSelect.style.width = '104px';
          useSelect.style.backgroundColor = 'rgb(231, 249, 249)';
          reserveSelect.style.width = '104px';
          reserveSelect.style.backgroundColor = 'rgb(231, 249, 249)';

          $(".guide-frequent-container").css("display", "flex");
          $(".guide-use-container").css("display", "none");
          $(".guide-select-container").css("display", "none");
          $(".guide-frequent").css("display", "none");
          $(".guide-use").css("display", "block");
          $(".guide-reserve").css("display", "block");
        }
      });

    }

    /* useSelect를 마우스 오버시 다른 요소 바꾸기 */
    if (useSelect) {
      useSelect.addEventListener('mouseover', function() {
        if (currentSelection !== 'use') {
          currentSelection = 'use';

          useSelect.style.width = '378px';
          useSelect.style.backgroundColor = 'rgb(0, 196, 196)';
          frequentSelect.style.width = '104px';
          frequentSelect.style.backgroundColor = 'rgb(231, 249, 249)';
          reserveSelect.style.width = '104px';
          reserveSelect.style.backgroundColor = 'rgb(231, 249, 249)';

          $(".guide-use-container").css("display", "flex");
          $(".guide-frequent-container").css("display", "none");
          $(".guide-reserve-container").css("display", "none");
          $(".guide-use").css("display", "none");
          $(".guide-frequent").css("display", "block");
          $(".guide-reserve").css("display", "block");
        }
      });

    }

    /* reserveSelect를 마우스 오버시 다른 요소 바꾸기 */
    if (reserveSelect) {
      reserveSelect.addEventListener('mouseover', function() {
        if (currentSelection !== 'reserve') {
          currentSelection = 'reserve';

          reserveSelect.style.width = '378px';
          reserveSelect.style.backgroundColor = 'rgb(0, 196, 196)';
          frequentSelect.style.width = '104px';
          frequentSelect.style.backgroundColor = 'rgb(231, 249, 249)';
          useSelect.style.width = '104px';
          useSelect.style.backgroundColor = 'rgb(231, 249, 249)';

          $(".guide-reserve-container").css("display", "flex");
          $(".guide-frequent-container").css("display", "none");
          $(".guide-use-container").css("display", "none");
          $(".guide-reserve").css("display", "none");
          $(".guide-frequent").css("display", "block");
          $(".guide-use").css("display", "block");
        }
      });

    }
}
/* 글자수 */
function getLength(num)
{
    var contentValue = document.getElementById("write-section"+num).value;
    var txtlenValue = document.getElementById("text_length"+num);
    var max_byte = contentValue.length;
    var str_len = contentValue.length;

    if (max_byte == 1001)
    {
        document.getElementById("write-section"+num).value = document.getElementById("write-section"+num).value.substring(0, str_len-1);
        txtlenValue.value = max_byte-1;
        return;
    }
    else
    {
        txtlenValue.value = max_byte;
    }

}

/* 문의 모달 시간 */
function getTime() {
  const date = new Date();
  const hour = date.getHours();
  const min = date.getMinutes();
  const ampm = hour < 12 ? "오전" : "오후";
  const hour12 = hour % 12 || 12; // 0시를 12시로 변환
  const minute = min < 10 ? `0${min}` : min;

  const adminTime = document.querySelector('.ask-admin-time');
  if (adminTime) {
    adminTime.innerText = `${ampm} ${hour12}:${minute}`;
  }
}

window.onload = function() {
  getTime();
  setInterval(getTime, 1000); // 1초마다 getTime 함수 호출
};

globalThis.category = "ALL";

/// 카테고리 최신글 2개 가져오기 ajax
const categoryService = (() => {
    function getCategoryList(callback) {
        $.ajax({
            url: `/mains/parentsBoards`,
            type: 'post',
            success: function (categoryResults) {
                console.log("parentsBoards");
                callback(categoryResults);
            }
            // error: function(error) {
            //     console.log('Error fetching data:', error)
            // }
        });
    }

    function getEventList(callback) {

        $.ajax({
            url: `/mains/events`,
            data: {"category" : globalThis.category},
            type: 'post',
            success: function (categoryResults) {
                console.log("events");
                console.log(categoryResults);
                console.log(globalThis.category);
                callback(categoryResults);
            }
            // error: function(error) {
            //     console.log('Error fetching data:', error)
            // }
        });
    }

    function getAnnouncementList(callback) {
        $.ajax({
            url: `/mains/announcements`,
            type: 'post',
            success: function (categoryResults) {
                console.log("announcements");
                console.log(categoryResults);
                callback(categoryResults);
            }
            // error: function(error) {
            //     console.log('Error fetching data:', error)
            // }
        });
    }

    function getNowKidsList(callback) {
        $.ajax({
            url: `/mains/nowKids`,
            type: 'post',
            success: function (categoryResults) {
                console.log("nowKids");
                console.log(categoryResults);
                callback(categoryResults);
            }
        });
    }

    function insertAsk() {
        let askDTO = {
            "askBoardTitle" : $(".ask-admin-title-input").val(),
            "askBoardContent" : $(".write-section").val()
        }
        $.ajax({
            url: `/mains/asks`,
            type: 'post',
            data: JSON.stringify(askDTO),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function (categoryResults) {
                console.log("asks");
                console.log(categoryResults);
                $('html, body').css('overflow', 'auto');
                if(modalCheck){
                    $("div.modal").fadeOut(0);
                    $("div.ask-modal").fadeOut(0);
                }
            }
        });
    }


    return {getCategoryList : getCategoryList, getEventList : getEventList, getAnnouncementList : getAnnouncementList, getNowKidsList : getNowKidsList, insertAsk : insertAsk};
})();

$(".ask-admin-btn").on("click", function(){
    console.log("click!")
    categoryService.insertAsk();
})


/* 부모님 마당 카테고리 최신순 5개 가져오기 */
function appendCategoryList(categoryResults) {
    let categoryText = '';
    let index = 1;
    categoryResults.forEach(category => {
        const convertedCategory = convertCategory(category.eventCategory); // 영어 카테고리를 한글로 변환
        // const convertedStartDate = formatDate(category.startDate);
        // const convertedEndDate = formatDate(category.endDate);
        const uploadDate = formatDate(category.parentsBoardRegisterDate);
        categoryText += ` 
                    <div class="event-banner${index} event-banners" onclick="location.href='/parentsYard/detail/${category.id}'">
                                <div class="event-banner-image-wrapper">
                                    <div class="event-banner-image"><img src="/parentsBoardFiles/display?fileName=ParentsBoard/${category.parentsBoardFileDTOS[0].filePath}/${category.parentsBoardFileDTOS[0].fileUUID}_${category.parentsBoardFileDTOS[0].fileOriginalName}"></div>
                                </div>
                                <div class="event-status">
                                    <div></div>
                                    <div class="status-container">
                                        <div class="important-status">NEW</div>
                                    </div>
                                </div>
                                <div class="event-box-title-container">
                                    <div class="event-box-title">
                                        [<span>${convertedCategory}</span>]${category.parentsBoardTitle}
                                    </div>
                                </div>
                                <div class="event-box-context-container">
                                    <div class="event-box-context">
                                        ${category.parentsBoardContent}
                                    </div>
                                </div>
                                <div class="event-box-date-container">
                                    ${uploadDate}
                                </div>
                            </div>`
        ;
        index++;
    });
    $('.event-banner').html(categoryText);
}


/* 놀러가요 카테고리 최신순 2개 가져오기 */
function appendEventList(categoryResults) {
    let categoryText = '';
    let index = 1;
    categoryResults.forEach(category => {
        const convertedCategory = convertCategory(category.category); // 영어 카테고리를 한글로 변환
        const convertedStartDate = formatDate(category.calendar.startDate);
        const convertedEndDate = formatDate(category.calendar.endDate);
        // const uploadDate = formatDate(category.parentsBoardRegisterDate);
        categoryText += ` 
                   <div class="second-banner${index} second-banners"  onclick="location.href='/event/detail/${category.id}'">
                                <div class="second-banner-image-wrapper">
                                    <div class="second-banner-image">
                                    <img src="/eventFiles/display?fileName=Event/${category.files[0].filePath}/${category.files[0].fileUUID}_${category.files[0].fileOriginalName}">
                                    </div>
                                </div>
                                <div class="recruit-box-context">
                                    <div class="recruit-box-title">
                                        [<span>${convertedCategory}</span>] ${category.boardTitle}
                                    </div>
                                    <div class="recruit-box-writer">
                                        ${category.memberName}
                                    </div>
                                     <div >
                                         <div class="event-box-date-container">
                                            ${convertedStartDate}
                                            <span>
                                                &nbsp;~&nbsp;
                                            </span>
                                            ${convertedEndDate}
                                        </div> 
                                         <div class="event-box-date-container title">
                                            ${category.boardContent}
                                        </div> 
                                    </div>
                                </div>
                            </div>`
        ;
        index++;
    });
    $('.second-banner').html("");
    $('.second-banner').html(categoryText);
}

/* 지금우리아이들은 카테고리 최신순 2개 가져오기 */
function appendNowKidsList(categoryResults) {
    let categoryText = '';
    console.log("======nowKids=========");
    let index = 1;
    categoryResults.forEach(category => {
        categoryText +=` 
        <div class="third-banner${index} third-banners" onclick="location.href='/nowKids/list'">
            <div class="third-banner-image-wrapper">
                <div class="third-banner-image">
                    <img src="/nowKidsFiles/display?fileName=NowKids/${category.nowKidsFileDTOList[0].filePath}/${category.nowKidsFileDTOList[0].fileUUID}_${category.nowKidsFileDTOList[0].fileOriginalName}">
                </div>
            </div>
            <div class="third-banner-text-wrapper">
                <div class="third-banner-text">
                    ${category.boardTitle}
                </div>
            </div>
        </div>`;
        index++;
    });
    $('.third-banner').html(categoryText);
}

/* 공지사항 3개 가져오기 */
function appendAnnouncementList(categoryResults) {
    let categoryText = '';
    console.log("======announcement=========");
    categoryText =`<div class="guide-frequent-select">
                    <div class="guide-frequent-container">
                        <div class="frequent-title-bar">
                            <div class="frequent-title">공지사항</div>
                            <div class="plus-menu">
                                <div class="plus"><a href="user-part/announcement">더보기</a></div>
                                <svg width="7" height="12" viewBox="0 0 7 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M1 11L6 6L1 1" stroke="white" stroke-linecap="round" stroke-linejoin="round"></path>
                                </svg>
                            </div>
                        </div>
                        <div class="context-container">
                            <div class="context-mark">
                                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M12 22C17.5228 22 22 17.5228 22 12C22 6.47715 17.5228 2 12 2C6.47715 2 2 6.47715 2 12C2 17.5228 6.47715 22 12 22Z" stroke="white" stroke-width="2.5" stroke-miterlimit="10"></path><path d="M15 12L10 9V15L15 12Z" stroke="white" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                </svg>
                            </div>
                            <div class="context-inner">
                                <div class="context">
                                    ${categoryResults[0].announcementTitle}<span class="more">...</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="guide-frequent">
                        <div class="container-noselect">
                            <div class="title-bar-noselect">
                                첫번째<br>공지
                            </div>
                            <svg width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M20.8567 32H6.85435C6.36255 32 5.89089 31.8223 5.54313 31.506C5.19537 31.1896 5 30.7606 5 30.3133V10.6867C5 10.2394 5.19537 9.81035 5.54313 9.49403C5.89089 9.17771 6.36255 9 6.85435 9H22" fill="white"></path>
                                <path d="M20.8567 32H6.85435C6.36255 32 5.89089 31.8223 5.54313 31.506C5.19537 31.1896 5 30.7606 5 30.3133V10.6867C5 10.2394 5.19537 9.81035 5.54313 9.49403C5.89089 9.17771 6.36255 9 6.85435 9H22" stroke="#212529" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"></path>
                                <path d="M19 9H33.2165C33.689 9.00136 34.1418 9.1795 34.476 9.49553C34.8101 9.81156 34.9985 10.2398 34.9999 10.6867V30.2926C35.0021 30.5156 34.9576 30.7369 34.869 30.9436C34.7804 31.1503 34.6495 31.3385 34.4837 31.4971C34.318 31.6558 34.1207 31.7819 33.9032 31.8682C33.6857 31.9545 33.4523 31.9993 33.2165 32H19.7495" fill="white"></path>
                                <path d="M19 9H33.2165C33.689 9.00136 34.1418 9.1795 34.476 9.49553C34.8101 9.81156 34.9985 10.2398 34.9999 10.6867V30.2926C35.0021 30.5156 34.9576 30.7369 34.869 30.9436C34.7804 31.1503 34.6495 31.3385 34.4837 31.4971C34.318 31.6558 34.1207 31.782 33.9032 31.8683C33.6857 31.9545 33.4523 31.9993 33.2165 32H19.7495" stroke="#212529" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"></path>
                                <path d="M20 28.8605H17.9327V33.1173L19.9977 32.5105" fill="#00C4C4"></path>
                                <path d="M20 28.8605H22.0673V33.1173L19.9977 32.5105" fill="#00C4C4"></path>
                                <path d="M20 28.8605H17.9327V33.1173L19.9977 32.5105L22.0673 33.1173V28.8605H20Z" stroke="#212529" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"></path>
                                <path d="M32 27.301C32 27.7507 31.8202 28.182 31.5 28.5005C31.1798 28.819 30.7453 28.9986 30.2917 29H9.7083C9.25475 28.9986 8.82025 28.819 8.50003 28.5005C8.17981 28.182 8 27.7507 8 27.301V8.69383C8 8.2446 8.17998 7.81376 8.50035 7.49611C8.82072 7.17846 9.25523 7 9.7083 7L20.0419 8.03916L30.2917 7C30.7448 7 31.1793 7.17846 31.4997 7.49611C31.82 7.81376 32 8.2446 32 8.69383V27.301Z" fill="#C5E8E8" stroke="#212529" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"></path>
                                <path d="M25.8894 13H14.1106C13.4972 13 13 13.505 13 14.1279V21.8721C13 22.495 13.4972 23 14.1106 23H25.8894C26.5028 23 27 22.495 27 21.8721V14.1279C27 13.505 26.5028 13 25.8894 13Z" fill="#00C4C4"></path><path d="M19 16L22 18.0015L19 20V16Z" fill="white"></path>
                            </svg>
                        </div>
                    </div>
                </div>

                <div class="guide-use-select">
                    <div class="guide-use-container">
                        <div class="use-title-bar">
                            <div class="use-title">공지사항</div>
                            <div class="plus-menu">
                                <div class="plus"><a href="user-part/announcement">더보기</a></div>
                                <svg width="7" height="12" viewBox="0 0 7 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M1 11L6 6L1 1" stroke="white" stroke-linecap="round" stroke-linejoin="round"></path>
                                </svg>
                            </div>
                        </div>
                        <div class="context-container">
                            <div class="context-mark">
                                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M12 22C17.5228 22 22 17.5228 22 12C22 6.47715 17.5228 2 12 2C6.47715 2 2 6.47715 2 12C2 17.5228 6.47715 22 12 22Z" stroke="white" stroke-width="2.5" stroke-miterlimit="10"></path><path d="M15 12L10 9V15L15 12Z" stroke="white" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                </svg>
                            </div>
                            <div class="context-inner">
                                <div class="context">
                                    ${categoryResults[1].announcementTitle}<span class="more">...</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="guide-use">
                        <div class="container-noselect">
                            <div class="title-bar-noselect">
                                두번째<br>공지
                            </div>
                            <svg width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M20.8567 32H6.85435C6.36255 32 5.89089 31.8223 5.54313 31.506C5.19537 31.1896 5 30.7606 5 30.3133V10.6867C5 10.2394 5.19537 9.81035 5.54313 9.49403C5.89089 9.17771 6.36255 9 6.85435 9H22" fill="white"></path>
                                <path d="M20.8567 32H6.85435C6.36255 32 5.89089 31.8223 5.54313 31.506C5.19537 31.1896 5 30.7606 5 30.3133V10.6867C5 10.2394 5.19537 9.81035 5.54313 9.49403C5.89089 9.17771 6.36255 9 6.85435 9H22" stroke="#212529" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"></path>
                                <path d="M19 9H33.2165C33.689 9.00136 34.1418 9.1795 34.476 9.49553C34.8101 9.81156 34.9985 10.2398 34.9999 10.6867V30.2926C35.0021 30.5156 34.9576 30.7369 34.869 30.9436C34.7804 31.1503 34.6495 31.3385 34.4837 31.4971C34.318 31.6558 34.1207 31.7819 33.9032 31.8682C33.6857 31.9545 33.4523 31.9993 33.2165 32H19.7495" fill="white"></path>
                                <path d="M19 9H33.2165C33.689 9.00136 34.1418 9.1795 34.476 9.49553C34.8101 9.81156 34.9985 10.2398 34.9999 10.6867V30.2926C35.0021 30.5156 34.9576 30.7369 34.869 30.9436C34.7804 31.1503 34.6495 31.3385 34.4837 31.4971C34.318 31.6558 34.1207 31.782 33.9032 31.8683C33.6857 31.9545 33.4523 31.9993 33.2165 32H19.7495" stroke="#212529" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"></path>
                                <path d="M20 28.8605H17.9327V33.1173L19.9977 32.5105" fill="#00C4C4"></path>
                                <path d="M20 28.8605H22.0673V33.1173L19.9977 32.5105" fill="#00C4C4"></path>
                                <path d="M20 28.8605H17.9327V33.1173L19.9977 32.5105L22.0673 33.1173V28.8605H20Z" stroke="#212529" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"></path>
                                <path d="M32 27.301C32 27.7507 31.8202 28.182 31.5 28.5005C31.1798 28.819 30.7453 28.9986 30.2917 29H9.7083C9.25475 28.9986 8.82025 28.819 8.50003 28.5005C8.17981 28.182 8 27.7507 8 27.301V8.69383C8 8.2446 8.17998 7.81376 8.50035 7.49611C8.82072 7.17846 9.25523 7 9.7083 7L20.0419 8.03916L30.2917 7C30.7448 7 31.1793 7.17846 31.4997 7.49611C31.82 7.81376 32 8.2446 32 8.69383V27.301Z" fill="#C5E8E8" stroke="#212529" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"></path>
                                <path d="M25.8894 13H14.1106C13.4972 13 13 13.505 13 14.1279V21.8721C13 22.495 13.4972 23 14.1106 23H25.8894C26.5028 23 27 22.495 27 21.8721V14.1279C27 13.505 26.5028 13 25.8894 13Z" fill="#00C4C4"></path><path d="M19 16L22 18.0015L19 20V16Z" fill="white"></path>
                            </svg>
                        </div>
                    </div>
                </div>
                
                <div class="guide-reserve-select">
                    <div class="guide-reserve-container">
                        <div class="reserve-title-bar">
                            <div class="reserve-title">공지사항</div>
                            <div class="plus-menu">
                                <div class="plus"><a href="user-part/announcement">더보기</a></div>
                                <svg width="7" height="12" viewBox="0 0 7 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M1 11L6 6L1 1" stroke="white" stroke-linecap="round" stroke-linejoin="round"></path>
                                </svg>
                            </div>
                        </div>
                        <div class="context-container">
                            <div class="context-mark">
                                <svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M12 22C17.5228 22 22 17.5228 22 12C22 6.47715 17.5228 2 12 2C6.47715 2 2 6.47715 2 12C2 17.5228 6.47715 22 12 22Z" stroke="white" stroke-width="2.5" stroke-miterlimit="10"></path><path d="M15 12L10 9V15L15 12Z" stroke="white" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round"></path>
                                </svg>
                            </div>
                            <div class="context-inner">
                                <div class="context">
                                    ${categoryResults[2].announcementTitle}<span class="more">...</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="guide-reserve">
                        <div class="container-noselect">
                            <div class="title-bar-noselect">
                                세번째<br>공지
                            </div>
                            <svg width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M20.8567 32H6.85435C6.36255 32 5.89089 31.8223 5.54313 31.506C5.19537 31.1896 5 30.7606 5 30.3133V10.6867C5 10.2394 5.19537 9.81035 5.54313 9.49403C5.89089 9.17771 6.36255 9 6.85435 9H22" fill="white"></path>
                                <path d="M20.8567 32H6.85435C6.36255 32 5.89089 31.8223 5.54313 31.506C5.19537 31.1896 5 30.7606 5 30.3133V10.6867C5 10.2394 5.19537 9.81035 5.54313 9.49403C5.89089 9.17771 6.36255 9 6.85435 9H22" stroke="#212529" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"></path>
                                <path d="M19 9H33.2165C33.689 9.00136 34.1418 9.1795 34.476 9.49553C34.8101 9.81156 34.9985 10.2398 34.9999 10.6867V30.2926C35.0021 30.5156 34.9576 30.7369 34.869 30.9436C34.7804 31.1503 34.6495 31.3385 34.4837 31.4971C34.318 31.6558 34.1207 31.7819 33.9032 31.8682C33.6857 31.9545 33.4523 31.9993 33.2165 32H19.7495" fill="white"></path>
                                <path d="M19 9H33.2165C33.689 9.00136 34.1418 9.1795 34.476 9.49553C34.8101 9.81156 34.9985 10.2398 34.9999 10.6867V30.2926C35.0021 30.5156 34.9576 30.7369 34.869 30.9436C34.7804 31.1503 34.6495 31.3385 34.4837 31.4971C34.318 31.6558 34.1207 31.782 33.9032 31.8683C33.6857 31.9545 33.4523 31.9993 33.2165 32H19.7495" stroke="#212529" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"></path>
                                <path d="M20 28.8605H17.9327V33.1173L19.9977 32.5105" fill="#00C4C4"></path>
                                <path d="M20 28.8605H22.0673V33.1173L19.9977 32.5105" fill="#00C4C4"></path>
                                <path d="M20 28.8605H17.9327V33.1173L19.9977 32.5105L22.0673 33.1173V28.8605H20Z" stroke="#212529" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"></path>
                                <path d="M32 27.301C32 27.7507 31.8202 28.182 31.5 28.5005C31.1798 28.819 30.7453 28.9986 30.2917 29H9.7083C9.25475 28.9986 8.82025 28.819 8.50003 28.5005C8.17981 28.182 8 27.7507 8 27.301V8.69383C8 8.2446 8.17998 7.81376 8.50035 7.49611C8.82072 7.17846 9.25523 7 9.7083 7L20.0419 8.03916L30.2917 7C30.7448 7 31.1793 7.17846 31.4997 7.49611C31.82 7.81376 32 8.2446 32 8.69383V27.301Z" fill="#C5E8E8" stroke="#212529" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"></path>
                                <path d="M25.8894 13H14.1106C13.4972 13 13 13.505 13 14.1279V21.8721C13 22.495 13.4972 23 14.1106 23H25.8894C26.5028 23 27 22.495 27 21.8721V14.1279C27 13.505 26.5028 13 25.8894 13Z" fill="#00C4C4"></path><path d="M19 16L22 18.0015L19 20V16Z" fill="white"></path>
                            </svg>
                        </div>
                    </div>
                </div>`;

    $('.guide-menu-list').html(categoryText);
}


// 페이지 로딩 시 초기 리스트를 불러옴
categoryService.getCategoryList(
    function (categoryResults) {
    appendCategoryList(categoryResults);
    parentsBannerActions();
});

// 페이지 로딩 시 초기 리스트를 불러옴
categoryService.getEventList(
    function (categoryResults, category) {
    appendEventList(categoryResults);
    eventBannerActions();
});

// 페이지 로딩 시 초기 리스트를 불러옴
categoryService.getNowKidsList(
    function (categoryResults) {
    appendNowKidsList(categoryResults);
    nowKidsBannerActions();
});

// 페이지 로딩 시 초기 리스트를 불러옴
categoryService.getAnnouncementList(
    function (categoryResults) {
    appendAnnouncementList(categoryResults);
    announcementBannerActions();
});

$(".header-category").on("click", function() {
    var selectedCategory = $(this).text();
    console.log("selectedCategory : " + selectedCategory);
    $(".header-category").css("background-color", "#00c4c4");
    $(".header-category").css("color", "#fff");
    $(this).css("background-color", "#f5f7fa");
    $(this).css("color", "#00c4c4");
    $(this).css("border", "1px solid #00c4c4");

    globalThis.category = convertCategoryToType(selectedCategory);

    // 페이지 로딩 시 초기 리스트를 불러옴
    categoryService.getEventList(
        function (categoryResults, category) {
            appendEventList(categoryResults);
            eventBannerActions();
    });
});


/* **************************************************************************************************************************************** */

// 카테고리 한국어로 바꾸는 코드
function convertCategory(category) {
//    AGRICULTURE, ART, TRADITION, CRAFT, SCIENCE, MUSEUM, SPORTS, ETC
    let categoryResult;

    if (category == "AGRICULTURE") {
        categoryResult = "농촌";
    } else if (category == "ART") {
        categoryResult = "예술";
    } else if (category == "TRADITION") {
        categoryResult = "전통";
    } else if (category == "CRAFT") {
        categoryResult = "공방";
    } else if (category == "SCIENCE") {
        categoryResult = "과학";
    } else if (category == "MUSEUM") {
        categoryResult = "박물관";
    } else if (category == "SPORTS") {
        categoryResult = "스포츠";
    } else {
        categoryResult = "기타";
    }
    return categoryResult;
}

// 카테고리 영어로 바꾸는 코드
function convertCategoryToType(category) {
//    AGRICULTURE, ART, TRADITION, CRAFT, SCIENCE, MUSEUM, SPORTS, ETC
    let categoryResult;

    if (category == "농촌") {
        categoryResult = "AGRICULTURE";
    } else if (category == "예술") {
        categoryResult = "ART";
    } else if (category == "전통") {
        categoryResult = "TRADITION";
    } else if (category == "공방") {
        categoryResult = "CRAFT";
    } else if (category == "과학") {
        categoryResult = "SCIENCE";
    } else if (category == "박물관") {
        categoryResult = "MUSEUM";
    } else if (category == "스포츠") {
        categoryResult = "SPORTS";
    } else if (category == "기타") {
        categoryResult = "ETC";
    } else {
        categoryResult = "ALL";
    }
    return categoryResult;
}

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

// 동의 모달
let agreeModalCheck;
function showAgreeModal(modalMessage){
    agreeModalCheck = false;
    $("div#content-wrap").html(modalMessage)
    /* $("div.modal").css("display", "flex").hide().fadeIn(500); */
    $("div.agree-modal").css("animation", "popUp 0.5s");
    $("div.agree-modal").css("display", "block").hide().fadeIn(500);
    $("div.modal-background").css("display", "flex").hide().fadeIn(500);
    setTimeout(function(){agreeModalCheck = true;}, 500);
}

function agreeModal() {

    let modalMessage = '';


    showAgreeModal(modalMessage);
    $('html, body').css('overflow', 'hidden');
}

// $("div.modal-background").on("click", function(){
//     $('html, body').css('overflow', 'auto');
//     if(agreeModalCheck){
//         $("div.modal").fadeOut(500);
//         $("div.agree-modal").fadeOut(500);
//         $("div.modal-background").fadeOut(500);
//     }
// });

$(".agree-close-btn").on("click", function(){
    $('html, body').css('overflow', 'auto');
    if(agreeModalCheck){
        $("div.modal").fadeOut(500);
        /*  $("div.agree-modal").css("animation", "popDown 0.5s"); */
        $("div.agree-modal").fadeOut(500);
        $("div.modal-background").fadeOut(500);
    }
});

$(document).ready(function() {
    agreeModal();
});

