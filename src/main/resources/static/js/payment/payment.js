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

const openModalBtn = document.getElementById('open-modal');
const modal = document.getElementById('coupon-modal');
const closeModalBtn = document.getElementsByClassName('close')[0];

openModalBtn.onclick = function () {
    modal.style.display = 'block';
};

closeModalBtn.onclick = function () {
    modal.style.display = 'none';
};

window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = 'none';
    }
};

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
            <div class="coupon-full-wrap">
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