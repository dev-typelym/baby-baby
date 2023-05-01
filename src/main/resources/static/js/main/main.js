/* banner.html */

HTMLCollection.prototype.forEach = Array.prototype.forEach;
const banner = document.querySelector('div.banner');
const imageDiv = document.querySelectorAll('div.banner div');
const lastImageDiv = document.createElement('div');
const firstImageDiv = document.createElement('div');
const next = document.querySelector('div.next');
const prev = document.querySelector('div.prev');
const buttons = document.querySelectorAll('.buttons button');
const loadingBar = document.querySelector('.loading-bar-now');


let checkArrow = false;
let count = 1;
let auto = setInterval(autoSlide, 2000);
let temp = buttons[0];
const pageNow = document.querySelector('#page-now');

HTMLCollection.prototype.forEach = Array.prototype.forEach;
buttons.forEach((button) => {
    button.addEventListener('click', () => {
        clearInterval(auto);
        count = parseInt(button.innerHTML);
        changeButtonStyle();
        banner.style.transition = 'transform 0.3s';
        banner.style.transform = `translate(${-1519.2 * count}px)`;
        auto = setInterval(autoSlide, 2000);
    });
});

const imageDivArray = Array.from(imageDiv);
imageDivArray.forEach((div, i) => {
  const imageUrl = `url(../../static/css/main/images/main-00${i + 1}.jpg)`;
  const gradient = 'linear-gradient(to bottom, rgba(0, 0, 0, 0.08), rgba(0, 0, 0, 0.64))';
  div.style.backgroundImage = `${gradient}, ${imageUrl}`;
});

banner.appendChild(lastImageDiv);
lastImageDiv.style.background = `linear-gradient(to bottom,rgba(0,0,0,.08),rgba(0,0,0,.64)), url(../../static/css/main/images/main-001.jpg)`;
lastImageDiv.style.backgroundSize = '100%';
lastImageDiv.style.backgroundRepeat = 'no-repeat';
lastImageDiv.style.backgroundPosition = '50% 50%';

banner.insertBefore(firstImageDiv, document.querySelector('div.banner div'));
firstImageDiv.style.background = `linear-gradient(to bottom,rgba(0,0,0,.08),rgba(0,0,0,.64)), url(../../static/css/main/images/main-00${imageDiv.length}.jpg)`;
firstImageDiv.style.backgroundSize = '100%';
firstImageDiv.style.backgroundRepeat = 'no-repeat';
firstImageDiv.style.backgroundPosition = '50% 50%';


banner.style.transform = `translate(-1519.2px)`;

function changeButtonStyle() {
  if(temp){
      temp.style.background = "rgba(255, 255, 255, 0.4)";
      temp.style.color = "black";
  }
  buttons[count - 1].style.background = "white";
  buttons[count - 1].style.color = "white";
  temp = buttons[count - 1];

  if (count === 1) {
    loadingBar.style.width = "20%";
  } else if (count === 2) {
    loadingBar.style.width = "40%";
  } else if (count === 3) {
    loadingBar.style.width = "60%";
  } else if (count === 4) {
    loadingBar.style.width = "80%";
  } else {
    loadingBar.style.width = "100%";
  }
}

function autoSlide() {
    banner.style.transition = 'transform 0.3s';
    banner.style.transform = `translate(${-1519.2 * ++count}px)`;
    console.log(count);
    if (count == 6) {
        count = 1;
        setTimeout(function () {
            banner.style.transition = 'transform 0s';
            banner.style.transform = 'translate(-1519.2px)';
        }, 300);
    }
    changeButtonStyle();
}

prev.addEventListener('click', function () {
    if (checkArrow) {
        return;
    }
    checkArrow = true;
    clearInterval(auto);
    banner.style.transition = 'transform 0.3s';
    banner.style.transform = `translate(${-1519.2 * --count}px)`;
    if (count == 0) {
        count = 5;
        setTimeout(function () {
            banner.style.transition = 'transform 0s';
            banner.style.transform = `translate(${-1519.2 * imageDiv.length}px)`;
        }, 300);
    }
    changeButtonStyle();
    auto = setInterval(autoSlide, 2000);
    setTimeout(() => {
        checkArrow = false;
    }, 300);
});

next.addEventListener('click', function () {
    if (checkArrow) {
        return;
    }
    checkArrow = true;
    clearInterval(auto);
    banner.style.transition = 'transform 0.3s';
    banner.style.transform = `translate(${-1519.2 * ++count}px)`;
    if (count == 6) {
        count = 1;
        setTimeout(function () {
            banner.style.transition = 'transform 0s';
            banner.style.transform = 'translate(-1519.2px)';
        }, 300);
    }
    changeButtonStyle();
    auto = setInterval(autoSlide, 2000);
    setTimeout(() => {
        checkArrow = false;
    }, 300);
});

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
  (div, i) => (div.style.backgroundImage = `url(../../static/css/main/images/event-00${i + 1}.jpg)`)
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
  (div, i) => (div.style.backgroundImage = `url(../../static/css/main/images/third-00${i + 1}.jpg)`)
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
  div.style.backgroundImage = `url(../../static/css/main/images/second-00${i + 1}.jpg)`;
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
    $("div.modal").css("display", "flex").hide().fadeIn(500);
    $("div.ask-modal").css("animation", "popUp 0.5s");
    $("div.ask-modal").css("display", "block").hide().fadeIn(500);
    setTimeout(function(){modalCheck = true;}, 500);
}

function categoryModal() {

    let modalMessage = '';


    showWarnModal(modalMessage);
}


$(".close-btn").on("click", function(){
    $('html, body').css('overflow', 'auto');
    if(modalCheck){
        $("div.modal").fadeOut(500);
        $("div.ask-modal").css("animation", "popDown 0.5s");
        $("div.ask-modal").fadeOut(500);
    }
});