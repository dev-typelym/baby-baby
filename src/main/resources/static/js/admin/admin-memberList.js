
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

// "전체선택" 체크박스 클릭 이벤트
$('#allSelect').click(function() {
    if ($(this).is(':checked')) {
        // 전체선택 체크박스가 체크되었을 때 개별 체크박스 모두 체크
        $('input[name="check"]').prop('checked', true);
    } else {
        // 전체선택 체크박스가 체크해제되었을 때 개별 체크박스 모두 체크해제
        $('input[name="check"]').prop('checked', false);
    }
});

// 삭제 버튼 클릭 이벤트
$(".delete-button").click(function() {
    if ($("input[type=checkbox]:checked").length === 0) {
        // 선택된 체크박스가 없으면 모달 띄우기
        $(".delete-no-check-modal").show();
    } else {
        // 선택된 체크박스가 있으면 모달 띄우기
        $(".delete-modal").show();
    }
});

// 모달 확인 버튼 클릭 시 모달 닫기
$(".confirm-delete, .no-check-confirm-delete").click(function() {
    $(".delete-no-check-modal, .delete-modal").hide();
});

// 모달 취소 버튼 클릭 시 모달 닫기
$(".cancel-delete").click(function() {
    $(".delete-modal").hide();
    $('input[name="check"]').prop('checked', false);
});


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


//-----------------------------------------ajax--------------------------------------------------

const PAGE_AMOUNT = 10;
const $itemWrap = $(".show-item-wrap");
const SEARCH_URL = "/parentsYard/list/show";
const $pageWrap = $(".page-button-box");
const $contentWrap = $(".member-list");
const adminMemberSearch = {
    memberName: null
};


function getAdminMemberList() {
    $.ajax({
        url: `memberList/${globalThis.page}`,
        data: adminMemberSearch,
        success: function(data) {
            $pageWrap.empty();
            showPage(data);
            $contentWrap.empty();
            showList(data.content);
        }

    })
}

globalThis.page = 1;



function findPage(page) {
    globalThis.page = page;
    getAdminMemberList();
}

//검색
$(".search-btn-icon").on("click", function (e) {
    e.preventDefault();
    let val;
    let $search = $(".search-input");

    val = $search.val();

    if ($search.val() === ""){
        val = null
    };

    adminMemberSearch.memberName = val;

    console.log( adminMemberSearch.memberName + "777");
    getAdminMemberList();
    $(".search-input").val("");
});

function showPage(data) {
    let pageable = data.pageable;
    let pageNumber = pageable.pageNumber;
    let totalPages = data.totalPages;
    let count = Math.floor(pageNumber/PAGE_AMOUNT);

    let startPage = count * PAGE_AMOUNT;
    let endPage = startPage + PAGE_AMOUNT;

    endPage = endPage > data.totalPages ? data.totalPages : endPage;

    let hasPrev = startPage > 1;
    // 170 page / 5 = 24 -> 171 /
    let hasNext = endPage < data.totalPages;

    let text = "";


    // Previous button
    if (hasPrev) {
        text += `<div class="">`;
        text += `<div class="page-button-margin">`;
        text += `<div>`;
        text += `<img src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_keyboard_arrow_left_48px-128.png" class="left-button" onclick="findPage(${startPage})" data-page = "${pageNumber}" aria-label="이전 목록">`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
    }

    // Page buttons
    for (let i = startPage + 1; i < endPage + 1; i++) {
        let page = i;
        if (pageNumber  + 1 == page) {
            text += `<div class="page-button-active page-button" onclick="findPage(${i})">`;
        } else {
            text += `<div class="page-button" onclick="findPage(${i})">`;
        }
        text += `<div class="page-button-margin">`;
        text += `<div>`;
        text += `<span>${i}</span>`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
    }

    // Next button
    if (hasNext) {
        text += `<div class="">`;
        text += `<div class="page-button-margin">`;
        text += `<div>`;
        text += `<img src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_keyboard_arrow_right_48px-128.png" class="right-button" onclick="findPage(${endPage+1})" data-page="' + (pageNumber + 1) + '" aria-label="다음 목록">`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
    }

    $pageWrap.html(text);
}


//    부모님 마당 목록
function showList(memberDTOS) {
    var content = "";
    memberDTOS.forEach(member => {
        const formattedDate = formatDate(new Date(member.memberRegisterDate));
        content +=
            `
               <tr class="no-hover-row">
                    <td class="no-modal">
                        <input type="checkbox" name="check">
                    </td>
                    <td class="member-id">${member.id}</td>
                    <td>${member.memberName}</td>
                    <td>${member.memberNickname}</td>
                    <td>${member.memberPhone}</td>
                    <td>${member.memberEmail}</td>
                    <td>${formattedDate}</td>
                    <td>${member.memberAddress}</td>
                    <td>${member.memberAddressDetail}</td>
                    <td>${member.memberAddressSubDetail}</td>
                </tr>
            `
    });
    $contentWrap.append(content);

}

getAdminMemberList();


// 선택된 항목 삭제하기
$('.confirm-delete').on('click', function () {
    var checkedIds = new Array();
    // 체크 박스 체크된 값
    $('input:checkbox[name=check]:checked').closest('tr').find('.member-id').each(function () {
        console.log(this.innerText);
        checkedIds.push(this.innerText);
    });

    // $('input:checkbox[name=check]:checked').closest('tr').find('.reply-id').each(function () {
    //     var id = $(this).text();
    //     checkedIds.push(parseInt(id, 10));
    // });
    console.log(checkedIds);
    console.log(typeof checkedIds[0]);
    $.ajax({
        url: "member/delete",
        type: "patch",
        data: {
            "checkedIds": checkedIds,
        },
        success: function () {
            findPage(page);
        }
    });

    $('input:checkbox[id=allSelect]:checked').prop('checked', false);
});

