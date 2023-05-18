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

// 내가 받은 calendar를 한국어로 바꾸는 코드
function convertDate(eventDate) {
    let dateObj = new Date(eventDate);
    let convertedDate = dateObj.toLocaleDateString('ko-KR', { year: 'numeric', month: 'long', day: 'numeric' });
    return convertedDate;
}

// 카테고리 한국어로 바꾸는 코드
function convertCategory(category) {
//    AGRICULTURE, ART, TRADITION, CRAFT, SCIENCE, MUSEUM, SPORTS, ETC
    let categoryResult;

    if(category == "AGRICULTURE"){
        categoryResult = "농촌";
    } else if(category == "ART"){
        categoryResult = "미술관/박람회";
    } else if(category == "TRADITION"){
        categoryResult = "전통";
    } else if(category == "CRAFT"){
        categoryResult = "공방";
    } else if(category == "SCIENCE"){
        categoryResult = "과학";
    } else if(category == "MUSEUM"){
        categoryResult = "박물관";
    } else if(category == "SPORTS"){
        categoryResult = "스포츠";
    } else{
        categoryResult = "기타";
    }
    return categoryResult;
}

for(let i=0; i < eventTitles.length; i++){
    let startEventDate =  new Date(calendars[i].startDate);
    let endEventDate =  new Date(calendars[i].endDate);
    let convertedStartDate = convertDate(startEventDate)
    let convertedEndDate = convertDate(endEventDate)
    let eventCategory = convertCategory(eventTitles[i].eventCategory)
    let eventLists = `
             <div id="select${i}" class="select-option" categoryType=${eventCategory} value=${eventTitles[i].eventId}> ${eventTitles[i].eventTitle} ${convertedStartDate} ~ ${convertedEndDate} </div>
                    `;
    $('.select-menu-layout').append(eventLists);
}





//  데이터로 가져온 이벤트 갯수 가져오기
let options = $('.select-menu-layout').children()

let $selectOption = $(".select-option");

$('.select-menu-layout').on("click",".select-option",function(e) {
    $(".select-placeholder").css("color", "#0c0c0c");
    let i = e.target.id.replaceAll("select","");

    // 이벤트 갯수만큼 먼저 검은색으로 바꾼다.
    for (let j =0; j < options.length; j++){
        $($(".select-menu-layout").children()[j]).css("color","black");
    }
    //그리고 내가 클릭한 것의 색 변경
    $($(".select-placeholder")[0]).text($(e.currentTarget).text());
    $(e.currentTarget).css("color", "#e1e1e1");

    $(".select-menu-box").hide();
    let currentEventId = $(this).attr('value');
    //이벤트 아이디 저장을 위한 코드
    $('input[name=eventId]').val(currentEventId);
    
//    왼쪽 카테고리 바꾸는 코드
    $($('.left-category-box')[0]).html(`<span class="nav-header-span">통솔자</span> <span class="nav-header-span">${$(this).attr('categoryType')}</span>`)
    $('.play-number').html($('input[name=eventId]').val())
});

let kidsList = null;
options.on('click', function () {
    let optionValue = $(this).attr('value');
    console.log(optionValue)

    $.ajax({
        url: '/nowKid/getKids',
        type: 'POST',
        data: {"eventId": optionValue},
        success: function(kids) {
            kidsList = JSON.parse(kids)
            console.log(kidsList)
            console.log(kidsList[0].kidName)
            $('.table-box tbody').empty()


            for (let j=0; j< kidsList.length; j++) {
                let eventDate = convertDate(kidsList[j].eventStartDate)
                let kidsText =
                    `
                                            <!-- 여기에 요소 추가 -->                                 
                                            <tr>
                                                <td>${j}</td>
                                                <td>${kidsList[j].kidName}</td>
                                                <td>${kidsList[j].kidAge}</td>
                                                <td>${kidsList[j].eventTitle}</td>
                                                <td>${eventDate}</td>
                                            </tr>    
        
                    `;

                $('.table-box tbody').append(kidsText)
            }
        },

    });
})
$('confirm-button').on('click', function (e) {
    e.preventDefault()
    document.getElementById('write-submit-form').submit();
})

$('input[type=date]').on('change', function () {
    let dateValue = $(this).val();
    $('input[name=eventDate]').val(dateValue)
})






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
