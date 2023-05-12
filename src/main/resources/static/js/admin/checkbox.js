// 체크박스
// const selectAll = document.querySelector('#select-all');
// const selectMembers = document.querySelectorAll('.select-member');

// selectAll.addEventListener('click', (e) => {
//     selectMembers.forEach(member => member.checked = e.target.checked);
// });

// selectMembers.forEach(member => {
//     member.addEventListener('click', (e) => {
//         console.log(document.querySelectorAll('.select-member:checked').length);
//         console.log(selectMembers.length);
//         if (!e.target.checked) {
//             selectAll.checked = false;
//         } else if (document.querySelectorAll('.select-member:checked').length === selectMembers.length) {
//             selectAll.checked = true;
//         }
//     });
// });

$('#allSelect').click(function () {
	$('input[name="check"]').prop('checked', this.checked);
});

$('input[name="check"]').change(function () {
	var checkCount = $('input[name="check"]:checked').length;
	$('#allSelect').prop('checked', checkCount == $('input[name="check"]').length);
});

// 스크롤 기능 막기
// window.addEventListener("scroll", () => {
//     window.scrollTo(0, 0);
// });
// 스크롤 기능 막기
