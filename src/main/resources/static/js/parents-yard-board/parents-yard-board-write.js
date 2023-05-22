eventDTOS.forEach((e,i) => {
    let textEvent =
        `
        <div class="select-option2" value="${e.id}">실내 액티비티 VR체험학습 ${convertDateFormat(e.calendar.startDate)}   ~    ${convertDateFormat(e.calendar.endDate)}</div>
        `;
    $('.select-menu-layout').append(textEvent)
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

const $selectBox2 = $(".select-layout2");
const $selectKidsCount = $(".select-kids-count");

$selectBox2.click(() => {
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

/* 리셋 버튼 */
// const $resetBtn = $(".reset-btn")[0];
// const $fieldStudyTitle = $(".normal-text-input")[0];
// const $fieldStudyDate = $(".field-study-date-input")[0];
//
// $resetBtn.onclick = () => {
//     $fieldStudyTitle.value = '';
//     $fieldStudyDate.value = '';
// };


/* 추가 */
/* 이벤트선택시 이벤트 아이디 히든에 추가 */
$('.select-option2').on('click', function () {
    let eventId = $(this).attr('value')
    console.log(eventId)
    $('input[name=eventId]').val(eventId)
})


function convertDateFormat(dateString) {
    let date = new Date(dateString);
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();

    // 월과 일이 한 자리 숫자인 경우 앞에 0을 추가합니다.
    if (month < 10) {
        month = '0' + month;
    }
    if (day < 10) {
        day = '0' + day;
    }

    var formattedDate = year + '.' + month + '.' + day;
    return formattedDate;
}