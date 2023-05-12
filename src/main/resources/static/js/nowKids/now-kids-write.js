console.log(calendars)
console.log(eventTitles)


const $selectBox = $(".select-layout");
const $selectMenuBox = $(".select-menu-box");

$selectBox.mouseup(() => {
    $selectMenuBox.show();
});

$selectMenuBox.mouseleave(() => {
    $selectMenuBox.hide();
})


let $selectOption = $(".select-option");

$('.select-menu-layout').on("click",".select-option",function(e) {
    $(".select-placeholder").css("color", "#0c0c0c");
    let i = e.target.id.replaceAll("select","");

    $($(".select-placeholder")[i]).css("color","#0c0c0c");
    $($(".select-placeholder")[i]).text($(e.currentTarget).text());
    $(e.currentTarget).css("color", "#e1e1e1");
    $($(".select-menu-box")[i]).hide();

});


// $selectOption.each((i, e) => {
//
//     $(e).click(() => {
//         $selectOption.css("color", "#0c0c0c");
//         $(".select-placeholder").css("color","#0c0c0c");
//         $(".select-placeholder").text($(e).text());
//         $(e).css("color", "#e1e1e1");
//         $selectMenuBox.hide();
//     });
// });


/* 달력 모달 */

// datepicker를 사용하여 달력 생성
/*         $('#datepicker').datepicker({
            dateFormat: 'yy년 mm월 dd일',
            monthNames: [
                '1월',
                '2월',
                '3월',
                '4월',
                '5월',
                '6월',
                '7월',
                '8월',
                '9월',
                '10월',
                '11월',
                '12월',
            ],
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
            titleFormat: 'yyyy년 MM월',
            todayHighlight: true,
            autoclose: true,
            titleFormat: 'yyyy년 MM월',
            yearSuffix: '년',
            monthSuffix: '월',
        });

        // date-container 클릭 시 datepicker 보이도록 설정
        $('.date-container').click(function (e) {
            e.stopPropagation();
            $('#datepicker').datepicker('show');
        });

        // datepicker에서 날짜 선택 시 input value에 값을 넣는 함수
        $('#datepicker').datepicker({
            onSelect: function (dateText) {
                $('#datepicker').val(dateText);
            },
        }); */

for(let i=0; i < eventTitles.length; i++){
    let eventLists = `
                                <div id="select${i}" class="select-option" value=${eventTitles[i].boardTitle}> ${eventTitles[i].boardTitle} </div>
                    `;
    $('.select-menu-layout').append(eventLists);
}