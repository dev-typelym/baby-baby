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

const $selectBox2 = $(".select-layout2");
const $selectKidsCount = $(".select-kids-count");

$selectBox2.mouseup(() => {
    $selectKidsCount.show();
});

$selectKidsCount.mouseleave(() => {
    $selectKidsCount.hide();
})


let $selectOption2 = $(".select-option2");

$selectOption2.each((i, e) => {

    $(e).click(() => {
        $selectOption.css("color", "#0c0c0c");
        $(".select-placeholder2").css("color","#0c0c0c"); 
        $(".select-placeholder2").text($(e).text()); 
        $(e).css("color", "#e1e1e1");
        $selectKidsCount.hide();       
    });
});

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
        }
    });
});
/* 별점 */

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
            img.style.fill =  'rgba(0, 0, 0, 0.5)';
        }
    });
});
