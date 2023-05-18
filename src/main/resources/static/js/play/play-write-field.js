/* 주소 입력창 */
window.onload = function () {
    document.getElementById('address-kakao').addEventListener('click', function () {
        //주소입력칸을 클릭하면
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function (data) {
                //선택시 입력값 세팅
                document.getElementById('address-kakao').value = data.address; // 주소 넣기
            },
        }).open();
    });
};


/* 가격을 1000단위로 ,찍음 */
$('.price-input').on('input', function () {
    // 입력 필드에서 값 가져오기
    let value = $(this).val();
    // 쉼표 제거
    value = value.replace(/,/g, '');
    // 1000 단위로 쉼표 추가
    value = value.replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
    // 입력 필드에 적용
    $(this).val(value);
});


// 클릭마다 인풋테그의 eventRecruiteCount 바꾸기
$('.select-kids-count').click(function () {
    let kidsCount = $('.select-placeholder2').text().replace('명', '');
    $("input[name=eventRecruitCount]").val(kidsCount)
})

$('.select-menu-box').click(function () {
    let category = $(".select-placeholder").text()
    $('input[name=category]').val(reverseConvertCategory(category))
})

$('.confirm-button').on('click', function () {
    let value = $('.price-input').val(); // 입력 필드에서 값 가져오기
    value = value.replace(/,/g, ''); // 쉼표 제거
    $('.price-input').val(value); // 변경된 값으로 입력 필드에 설정
    $('#firstForm').submit();
});


/* 한국어 영어로 바꾸는 코드 */
function reverseConvertCategory(category) {
    let categoryResult;

    if (category == "농촌") {
        categoryResult = "AGRICULTURE";
    } else if (category == "미술관/박람회") {
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
    } else {
        categoryResult = "ETC";
    }

    return categoryResult;
}