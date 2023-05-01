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
    var value = $(this).val();
    // 쉼표 제거
    value = value.replace(/,/g, '');
    // 1000 단위로 쉼표 추가
    value = value.replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
    // 입력 필드에 적용
    $(this).val(value);
});
