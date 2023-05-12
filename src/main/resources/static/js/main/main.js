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
    (div, i) => (div.style.backgroundImage = `url(../../static/images/main/images/main-00${i + 1}.jpg)`)
);

banner.appendChild(lastImageDiv);
lastImageDiv.style.backgroundImage = `url(../../static/images/main/images/main-001.jpg)`;

banner.insertBefore(firstImageDiv, document.querySelector('div.banner div'));
firstImageDiv.style.backgroundImage = `url(../../static/images/main/images/main-00${imageDiv.length}.jpg)`;

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
    console.log(count);
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

/* banner.html */

/* HTMLCollection.prototype.forEach = Array.prototype.forEach;
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
const width = window.innerWidth * -1;


HTMLCollection.prototype.forEach = Array.prototype.forEach;
buttons.forEach((button) => {;
    button.addEventListener('click', () => {;
        clearInterval(auto);
        count = parseInt(button.innerHTML);
        changeButtonStyle();
        banner.style.transition = 'transform 0.3s';
        banner.style.transform = `translate(${width * count}vw)`;
        auto = setInterval(autoSlide, 3000);
    });
});

imageDiv.forEach(;
    (div, i) => (div.style.backgroundImage = `url(../../static/images/main/images/main-00${i + 1}.jpg)`);
);

banner.appendChild(lastImageDiv);
lastImageDiv.style.backgroundImage = `url(../../static/images/main/images/main-001.jpg)`;

banner.insertBefore(firstImageDiv, document.querySelector('div.banner div'));
firstImageDiv.style.backgroundImage = `url(../../static/images/main/images/main-00${imageDiv.length}.jpg)`;


banner.style.transform = `translate(${width}vw)`;

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
    banner.style.transform = `translate( ${width} * ++count}vw)`;
    console.log(count);
    if (count == 6) {;
        count = 1;
        setTimeout(function () {;
            banner.style.transition = 'transform 0s';
            banner.style.transform = `translate(${width}vw)`;
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
    banner.style.transform = `translate(${width * --count}vw)`;
    if (count == 0) {;
        count = 5;
        setTimeout(function () {;
            banner.style.transition = 'transform 0s';
            banner.style.transform = `translate(${width * imageDiv.length}vw)`;
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
    banner.style.transform = `translate(${width * ++count}vw)`;
    if (count == 6) {;
        count = 1;
        setTimeout(function () {;
            banner.style.transition = 'transform 0s';
            banner.style.transform = `translate(${width}vw)`;
        }, 300);
    };
    changeButtonStyle();
    auto = setInterval(autoSlide, 3000);
    setTimeout(() => {;
        checkArrow = false;
    }, 300);
}); */

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


eventImageDiv.forEach(
  (div, i) => (div.style.backgroundImage = `url(../../static/images/main/images/event-00${i + 1}.jpg)`)
);

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


thirdImageDiv.forEach(
  (div, i) => (div.style.backgroundImage = `url(../../static/images/main/images/third-00${i + 1}.jpg)`)
);

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


/* 두번째 배너 */
const secondBanner = document.querySelector('.second-banner');
const secondImageDiv = document.querySelectorAll('.second-banner .second-banner-image');
const secondPrev = document.querySelector('.second-prev');
const secondNext = document.querySelector('.second-next');
let secondCheckArrow = false;
let secondCount = 1;

secondImageDiv.forEach((div, i) => {
  div.style.backgroundImage = `url(../../static/images/main/images/second-00${i + 1}.jpg)`;
});

const clonedsecondBanners = document.querySelectorAll('.second-banner > div').values();

for (const clonedBanner of clonedsecondBanners) {
  secondBanner.appendChild(clonedBanner.cloneNode(true));
}

const clonedsecondBanner5 = document.querySelector('.second-banner5').cloneNode(true);
secondBanner.insertBefore(clonedsecondBanner5, document.querySelector('.second-banner .second-banner1'));

secondBanner.style.transform = `translateX(-261px)`;

secondPrev.addEventListener('click', function() {
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

secondNext.addEventListener('click', function() {
  if (secondCheckArrow) {
    return;
  }
  secondCheckArrow = true;
  secondCount++;
  secondBanner.style.transition = 'transform 0.3s';
  secondBanner.style.transform = `translateX(${-261 * secondCount}px)`;
  if (secondCount === secondImageDiv.length + 1) {
    secondCount = 1;
    setTimeout(function() {
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


/* 좋아요 버튼 */
/* 두번째 리스트 */
function secondListLikeOn(num){
  $('.second-list-like-btn'+num).css('display', 'none');
  $('.second-list-like-red-btn'+num).css('display', 'block');
}

function secondListLikeOff(num){
  $('.second-list-like-btn'+num).css('display', 'block');
  $('.second-list-like-red-btn'+num).css('display', 'red');
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


/* frequent에 마우스 오버시 다른 요소들 넓이 줄이기 */
/* frequentSelect.addEventListener('mouseover', function() {
  frequentSelect.style.width = '378px';
  frequentSelect.style.backgroundColor = 'rgb(0, 196, 196)';
  useSelect.style.width = '104px';
  useSelect.style.backgroundColor = 'rgb(231, 249, 249)';
  reserveSelect.style.width = '104px';
  reserveSelect.style.backgroundColor = 'rgb(231, 249, 249)';
});
 */
/* use에 마우스 오버시 다른 요소들 넓이 줄이기 */
/* useSelect.addEventListener('mouseover', function() {
  useSelect.style.width = '378px';
  useSelect.style.backgroundColor = 'rgb(0, 196, 196)';
  frequentSelect.style.width = '104px';
  frequentSelect.style.backgroundColor = 'rgb(231, 249, 249)';
  reserveSelect.style.width = '104px';
  reserveSelect.style.backgroundColor = 'rgb(231, 249, 249)';
}); */

/* reserve에 마우스 오버시 다른 요소들 넓이 줄이기 */
/* reserveSelect.addEventListener('mouseover', function() {
  reserveSelect.style.width = '378px';
  reserveSelect.style.backgroundColor = 'rgb(0, 196, 196)';
  frequentSelect.style.width = '104px';
  frequentSelect.style.backgroundColor = 'rgb(231, 249, 249)';
  useSelect.style.width = '104px';
  useSelect.style.backgroundColor = 'rgb(231, 249, 249)';
});
 */

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