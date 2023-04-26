
/* banner.html */

HTMLCollection.prototype.forEach = Array.prototype.forEach;
const banner = document.querySelector('div.banner');
const imageDiv = document.querySelectorAll('div.banner div');
const lastImageDiv = document.createElement('div');
const firstImageDiv = document.createElement('div');
const next = document.querySelector('div.next');
const prev = document.querySelector('div.prev');
const buttons = document.querySelectorAll('.buttons button');
const spans = document.querySelectorAll('.buttons button span');

let checkArrow = false;
let count = 1;
let temp = buttons[0];
const pageNow = document.querySelector('#page-now');

HTMLCollection.prototype.forEach = Array.prototype.forEach;
spans.forEach((span) => {
  span.addEventListener('click', () => {
    count = parseInt(span.innerHTML);
    changeButtonStyle();
    banner.style.transition = 'transform 0.3s';
    banner.style.transform = `translate(${-344 * count}px)`;
  });
});

imageDiv.forEach(
  (div, i) => (div.style.backgroundImage = `url(../../static/images/nowKids/00${i + 1}.jpg)`)
);

banner.appendChild(lastImageDiv);
lastImageDiv.style.backgroundImage = `url(../../static/images/nowKids/001.jpg)`;

banner.insertBefore(firstImageDiv, document.querySelector('div.banner div'));
firstImageDiv.style.backgroundImage = `url(../../static/images/nowKids/00${imageDiv.length}.jpg)`;

banner.style.transform = `translate(-344px)`;
function changeButtonStyle() {
  if(temp){
      temp.style.background = "rgba(255, 255, 255, 0.4)";
      temp.style.color = "black";
  }
  buttons[count - 1].style.background = "white";
  buttons[count - 1].style.color = "white";
  temp = buttons[count - 1];
}

prev.addEventListener('click', function () {
  if (checkArrow) {
    return;
  }
  checkArrow = true;
  count--;
  if (count === 0) {
    banner.style.transition = 'transform 0s';
    banner.style.transform = `translate(${-344 * (imageDiv.length + 1)}px)`;
    setTimeout(() => {
      count = imageDiv.length;
      banner.style.transition = 'transform 0.3s';
      banner.style.transform = `translate(${-344 * count}px)`;
      changeButtonStyle();
      checkArrow = false;
    }, 10);
  } else {
    banner.style.transition = 'transform 0.3s';
    banner.style.transform = `translate(${-344 * count}px)`;
    changeButtonStyle();
    setTimeout(() => {
      checkArrow = false;
    }, 300);
  }
});

next.addEventListener('click', function () {
  if (checkArrow) {
    return;
  }
  checkArrow = true;
  count++;
  banner.style.transition = 'transform 0.3s';
  banner.style.transform = `translate(${-344 * count}px)`;
  if (count == imageDiv.length + 1) {
    count = 1;
    setTimeout(function () {
            banner.style.transition = 'transform 0s';
            banner.style.transform = 'translate(-808px)';
        }, 300);
  }
  setTimeout(() => {
    banner.style.transition = 'transform 0s';
    banner.style.transform = `translate(${-344 * count}px)`;
    checkArrow = false;
    changeButtonStyle();
  }, 300);
});

changeButtonStyle();

/* 이미지 모달 */
let modalCheck;
function showWarnModal(modalMessage){
    modalCheck = false;
    $("div#content-wrap").html(modalMessage)
    /* $("div.modal").css("display", "flex").hide().fadeIn(500); */

    $("div.category-modal").css("display", "block").hide().fadeIn(500);
    $("div.modal-background").css("display", "flex").hide().fadeIn(500);
    setTimeout(function(){modalCheck = true;}, 500);
}

function imageModal() {

    let modalMessage = '';


    showWarnModal(modalMessage);
    $('html, body').css('overflow', 'hidden');
}

$("div.modal-background").on("click", function(){
    $('html, body').css('overflow', 'auto');
    if(modalCheck){
        $("div.modal").fadeOut(500);
        $("div.category-modal").fadeOut(500);
        $("div.modal-background").fadeOut(500);
    }
});

$(".close-btn").on("click", function(){
    $('html, body').css('overflow', 'auto');
    if(modalCheck){
        $("div.modal").fadeOut(500);
        $("div.category-modal").fadeOut(500);
        $("div.modal-background").fadeOut(500);
    }
});