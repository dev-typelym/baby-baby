

/* 상세보기 모달 ===================== */
const $showDetail = $(".row");

// $showDetail.on('click', function(e){
//     $(".user-modal").show();
// })
$showDetail.on('click', function(e){
    if (!$(e.target).is('input[type="checkbox"]') && !$(e.target).hasClass('no-modal')) {
        $(".user-modal").show();
    }
})

/* 상세 모달 닫기 */
const $modalCancel = $("#Capa_1");

$modalCancel.on("click", function(e) {
    $(".user-modal").hide();
});

/* 삭제 모달 ======================== */
const $showDelete = $(".delete-button");
const $cancelDelete = $(".cancel-delete");

$showDelete.on('click', function(e){
    $(".delete-modal").show();
})

$cancelDelete.on('click', function(e){
    $(".delete-modal").hide();
})


// 수정하기 썸네일
$('.update-profile').change(function(event) {
    var input = event.target;
    var reader = new FileReader();
    reader.onload = function() {
      var dataURL = reader.result;
      var image = $('.img-updated');
      image.attr('src', dataURL);
    };
    reader.readAsDataURL(input.files[0]);
  });

//   const makerUpButton = document.querySelector('.maker_up_button');
//   const makerWrap = document.querySelector('.crew-table');
//   const makerPath = document.querySelector('.maker_svg path');
//   makerUpButton.addEventListener('click', () => {
//       if (makerWrap.style.display === 'none') {
//           makerWrap.style.display = 'block';
//           let makerAngle = 0;

//           const intervalId = setInterval(() => {
//               makerAngle += 5; // 회전 각도를 5도씩 증가시킵니다.

//               if (makerAngle <= 180) {
//                   // 회전 각도가 180도 이하인 경우
//                   makerPath.setAttribute('transform', `rotate(${makerAngle} 16 16)`);
//               } else {
//                   // 회전 각도가 180도 이상인 경우
//                   makerAngle = 360 - makerAngle; // 180도를 빼서 원래 각도로 되돌립니다.
//                   makerPath.setAttribute('transform', `rotate(${makerAngle} 16 16)`);
//                   clearInterval(intervalId); // setInterval을 종료합니다.
//               }
//           }, 5);
//       } else {
//           let makerAngle = 180; // 회전 각도를 180도로 초기화합니다.

//           const intervalId = setInterval(() => {
//               makerAngle += 5; // 회전 각도를 5도씩 증가시킵니다.
//               makerPath.setAttribute('transform', `rotate(${makerAngle} 16 16)`);

//               if (makerAngle >= 360) {
//                   // 회전 각도가 360도 이상이 되면 setInterval을 종료합니다.
//                   clearInterval(intervalId);
//                   makerWrap.style.display = 'none'; // display를 none으로 설정합니다.
//               }
//           }, 5);
//       }
//   });

$('.maker_up_button').click(function() {
    var table = $(this).parents('tr').next('.crew-table');
    if (table.is(':visible')) {
      table.hide();
      $(this).removeClass('rotate');
    } else {
      table.show();
      $(this).addClass('rotate');
    }
  });