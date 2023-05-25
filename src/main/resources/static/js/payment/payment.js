/**
 *
 */
 const paymentButton = $("#paymentbutton");
let check = false;
$('.Checkbox_icon').on('click', $('.Checkbox_icon'), function () {
    /*check=$("#checkboxtf").is(":checked");*/
    console.log(check);
    if (!check) {
        $('#realbutton').css('opacity', '1');
        $('#checkboxmint').css('backgroundColor', '#00c4c4');

        paymentButton.prop("disabled", false);
        paymentButton.css("backgroundColor", "#00c4c4");
        paymentButton.css("borderColor", "#00c4c4");
        paymentButton.css("cursor", "pointer");

        check = true;
    } else {
        paymentButton.prop("disabled", true);
        paymentButton.css("backgroundColor", "grey");
        paymentButton.css("borderColor", "grey");
        paymentButton.css("cursor", "default");

        $('#realbutton').css('opacity', '0');
        $('#checkboxmint').css('backgroundColor', '#fff');
        
        check = false;
    }









});

/* 쿠폰창 모달 */

// const openModalBtn = document.getElementById('open-modal');
// const modal = document.getElementById('coupon-modal');
// const closeModalBtn = document.getElementsByClassName('close')[0];
//
// openModalBtn.onclick = function () {
//     modal.style.display = 'block';
// };
//
// closeModalBtn.onclick = function () {
//     modal.style.display = 'none';
// };
//
// window.onclick = function (event) {
//     if (event.target == modal) {
//         modal.style.display = 'none';
//     }
// };

// const modalForm = document.querySelector('#modal-form');

// modalForm.addEventListener('submit', function(event) {
//   // 기본 동작을 막는다
//   event.preventDefault();

//   // 폼 데이터를 전송하는 코드
//   // ...
// });


/// Get the modal
let agrModal = document.getElementById('agrModal');

// Get the buttons
let agreeBtn = document.getElementById('agree-btn');
let closeBtn = document.getElementById('close-btn');

// When the user clicks the agree button, close the modal
// agreeBtn.onclick = function() {
//     agrModal.style.display = "block";
// };
// closeBtn.onclick = function() {
//     agrModal.style.display = "none";
// };





let couponText = '';
if(eventDTO.coupons.length > 0){



}
eventDTO.coupons.forEach((e,i) => {
    couponText +=
        `
    <div class="coupon-full-wrap-margin"> <!-- = CouponDownloadItem_container__1hk2 -->
            <div class="coupon-full-wrap" value="${e.id}">
            <div class="coupon-left-full-wrap">
            <dl>
            <dd class="coupon-left-title">후기</dd>
            <dd class="coupon-left-title-explain">${changeButtonText(e.couponType)}</dd>
            <dd class="coupon-left-price-wrap">
            <span class="coupon-left-price">${e.couponPrice}</span><span>원</span>
            </dd>
            <dd class="coupon-left-deadline-wrap">
            <span class="coupon-left-deadline">받은 날짜</span><span>${convertDateFormat(e.registerDate)} </span>
            </dd>
            </dl>
            </div>
            <button type="button" class="coupon-right-full-wrap">
            <i class="coupon-right-icon">
            <img width="40px" height="40px" src="/images/payment/coupon-use-S.png">
            </i>
            <span class="coupon-right-can-use">사용 가능</span>
            </button>
            </div>
            </div>
    `;
})
$('.modal-content').append(couponText)

function changeButtonText(type) {
    let result = ''
    if (type === 'REVIEW') {
        result = '성실한 후기 작성 쿠폰'
    } else if (type === 'PARENT') {
        result = '부모님 마당 작성 감사쿠폰'
    }

    return result;
}



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

let paymentAreaText =
                    `
                        <div class="payment-contributors-info-container">
										<div class="payment-final-price-container">
											<p class="summary total-price">참여인원
												<span><span>${kidIdList.length} 명</span></span>
											</p>
											<p class="summary discount-price">통솔자 신청 (그룹인원 최대 10명) (그룹 인당 가격)
												<span>${formatNumberWithCommas(eventDTO.eventPrice)} <span>원</span></span>
											</p>
											<hr class="hr-lighter">
											<p class="final-price">추가금액 합계
												<span>${formatNumberWithCommas(eventDTO.eventPrice * kidIdList.length)} <span>원</span></span>
											</p>
										</div>
								</div>
    `;
$('.top-payment-area').append(paymentAreaText)

let buttomPaymentArea =
    `
    <div class="payment-contributors-info-container">
<div class="payment-final-price-container">
<p class="summary total-price">총 금액
<span>${formatNumberWithCommas(eventDTO.eventPrice)}<span>원 x </span><span>${kidIdList.length}</span><span>명</span></span>
</p>
<p class="summary discount-price">할인 금액
<span><span>-</span><span class="coupon-amount-area">0</span><span>원</span></span>
</p>
<hr class="hr-lighter">
<p class="final-price">총 결제금액 
<span>${formatNumberWithCommas(eventDTO.eventPrice * kidIdList.length )}<span>원</span></span>
</p>
</div>
</div>
    `;

$('.bottom-payment-area').append(buttomPaymentArea)

let leftText =
    `
<div class="DefaultPrice">
<div class="DefaultPrice_title">
<label class="Radio1 Radio2 Radio3">
<input type="radio" name="price" readonly="" value="DEFAULT" checked="">
<span class="Radio_icon"></span>
<span class="Radio_label"></span>
</label>일반 금액
</div>
<div class="Price_container">
<dl class="">
<dt>상품 금액</dt>
<dd>
<span>
<em class="Price_money">${formatNumberWithCommas(eventDTO.eventPrice * kidIdList.length)}</em>
원
</span>
</dd>
</dl>
</div>
<div class="Price_container">
<!--<dl class="">-->
<!--<dt>쿠폰 할인 금액</dt>-->
<!--<dd>-->
<!--<span class="coupon-amount-area">0</span>원-->
<!--</dd>-->
<!--</dl>-->
</div>
<div class="Price_container">
<hr class="Price_divide">
<dl class="Price_total">
<dt>최종 결제 금액</dt>
<dd>
<span><em class="final-price-number">${formatNumberWithCommas(eventDTO.eventPrice * kidIdList.length - parseInt($('.coupon-amount-area').text()))}</em></span>
</dd>
</dl>
</div>
</div>
    `;
$('.right_section_area').append(leftText)


$('.button-pay-go').text(`${formatNumberWithCommas(eventDTO.eventPrice * kidIdList.length)} 원 결제하기`)





$('.coupon-right-full-wrap').on('click', function () {
    let stringCoupnId = $('.coupon-full-wrap').attr('value')
    let couponId = parseInt(stringCoupnId);
    $('.coupon-amount-area').text('5000')
    $('.coupon-right-can-use').text("사용중")
});










let purchasePrice = eventDTO.eventPrice * kidIdList.length;

paymentButton.on('click', function () {
    BootPay.request({
        price: eventDTO.eventPrice * kidIdList.length , //실제 결제되는 가격

        // 관리자로그인 -> 결제설치 -> 인증키 및 보안 -> WEB Application ID
        application_id: "646e20f03049c8001ef8bee8",

        name: '아기자기', //결제창에서 보여질 이름
        pg: '',
        method: '', //결제수단, 입력하지 않으면 결제수단 선택부터 화면이 시작합니다.
        show_agree_window: 0, // 부트페이 정보 동의 창 보이기 여부
        items: [
            {
                item_name: eventDTO.boardTitle, //상품명
                qty: 1, //수량
                unique: '123', //해당 상품을 구분짓는 primary key
                price: eventDTO.eventPrice * kidIdList.length , //상품 단가
            }
        ],
        order_id: '고유order_id_1234', //고유 주문번호로, 생성하신 값을 보내주셔야 합니다.
    }).error(function (data) {
        //결제 진행시 에러가 발생하면 수행됩니다.
        console.log(data);
    }).cancel(function (data) {
        //결제가 취소되면 수행됩니다.
        const container = document.querySelector(".modal");
        const close = document.querySelector(".pay-popup-check");

        //모달창 열기
        container.style.display = "block";

        //모달창 닫기
        close.addEventListener("click", function () {
            container.style.display = "none";
        });
        console.log(data);

    }).close(function (data) {
        // 결제창이 닫힐때 수행됩니다. (성공,실패,취소에 상관없이 모두 수행됨)
        console.log(data);
    }).done(function (data) {
        //결제가 정상적으로 완료되면 수행됩니다
        //비즈니스 로직을 수행하기 전에 결제 유효성 검증을 하시길 추천합니다.4
        $.ajax({
            url: "/payment/save", // 컨트롤러 주소
            type: "post",
            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
            data: { purchasePrice : purchasePrice, "purchaseCount" : kidIdList.length, "eventId": eventDTO.id, "kidList" : kidIdList }, // 결제정보 객체 paymetnVO
            success: function () {
                location.href = "/parentsYard/list";
            },
            error: function (error) {
                console.log(error);
            }
        });
        console.log(data);
    });


})


function formatNumberWithCommas(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}




















