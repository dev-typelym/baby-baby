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
agreeBtn.onclick = function() {
    agrModal.style.display = "block";
};
closeBtn.onclick = function() {
    agrModal.style.display = "none";
};

