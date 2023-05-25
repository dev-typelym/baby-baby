const $selectBox2 = $(".select-layout2");
const $selectKidsCount = $(".select-kids-count");

/* 나의 후기 값 꽂아주기 */
events.forEach((e,i)=> {
    let eventText =
        ` 
    <div class="select-option2" value="${e.id}">${e.boardTitle}    ${e.calendar.endDate.date.year}-${e.calendar.startDate.date.month}-${e.calendar.startDate.date.day}</div>
    `;

    $('.select-menu-layout').append(eventText)
})

let $selectOption2 = $(".select-option2");

$selectOption2.each((i, e) => {

    $(e).click(() => {
        $selectOption2.css("color", "#0c0c0c");
        $(".select-placeholder2").css("color","#0c0c0c");
        $(".select-placeholder2").text($(e).text());
        $(e).css("color", "#e1e1e1");
        $selectKidsCount.hide();
    });
});

$selectBox2.mouseup(() => {
    $selectKidsCount.show();
});

$selectKidsCount.mouseleave(() => {
    $selectKidsCount.hide();
})


const $selectBox = $(".select-layout");
const $selectMenuBox = $(".select-menu-box");




$selectBox.mouseup(() => {
    $selectMenuBox.show();
});

$selectMenuBox.mouseleave(() => {
    $selectMenuBox.hide();
})


let $selectOption = $(".select-option");

$selectOption.each((i, e) => {

    $(e).click(() => {
        $selectOption.css("color", "#0c0c0c");
        $(".select-placeholder").css("color","#0c0c0c"); 
        $(".select-placeholder").text($(e).text()); 
        $(e).css("color", "#e1e1e1");
        $selectMenuBox.hide();       
    });
});
/* 카테고리 끝 */

const $inputTitle = $(".title");
const $maxCount = $(".text-max-count");
    // 제목 입력 js
$inputTitle.keyup(() => {
    $maxCount.text((40 - $inputTitle.val().length) + "자 남음");
});

/* 별점 리셋 버튼 */
const $resetBtn = $(".reset-btn")[0];
const stars = document.querySelectorAll('.review-my-star');
stars.forEach((star) => {
    $resetBtn.addEventListener('click', () => {
        const resetIndex = star.getAttribute('data-index');

        // 클릭된 star 이미지와 그 이전 이미지의 모든 grey-star 이미지를 변경
        for (let i = 1; i <= 5; i++) {
            const resetImg = document.querySelector(`.review-my-star[data-index="${i}"]`);
            resetImg.style.fill = 'rgba(0, 0, 0, 0.5)';
            $('input[name=reviewScore]').val(0);
        }
    });
});

// 별정
let rating = 0;
stars.forEach((star) => {
    star.addEventListener('click', () => {
        const index = star.getAttribute('data-index');

        // 클릭된 star 이미지와 그 이전 이미지의 모든 grey-star 이미지를 변경
        for (let i = 1; i <= index; i++) {
            const img = document.querySelector(`.review-my-star[data-index="${i}"]`);
            img.style.fill = '#00c4c4';
        }

        // 클릭된 star 이미지 이후의 grey-star 이미지를 변경
        for (let i = parseInt(index) + 1; i <= 5; i++) {
            const img = document.querySelector(`.review-my-star[data-index="${i}"]`);
            img.style.fill = 'rgba(0, 0, 0, 0.5)';
        }

        // 선택된 별점 값 가져오기
        rating = parseInt(index);
        console.log('별점:', rating);
        $('input[name=reviewScore]').val(rating)
    });
});



$('.select-option2').on('click', function () {
    let eventId = $(this).attr('value')
    console.log(eventId)
    $('input[name=eventId]').val(eventId)
})

console.log("=============sksksksksksk");
console.log(events);

$('.profile-name').text(events[0].memberNickname)