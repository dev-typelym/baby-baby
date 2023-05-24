const PAGE_AMOUNT = 10;
const $itemWrap = $(".show-item-wrap");
const SEARCH_URL = "/parentsYard/list/show";
const $pageWrap = $(".page-button-box");
const $contentWrap = $(".review-reply-list");
const adminReviewBoardReplySearch = {
    reviewBoardReplyContent: null
};



function getAdminReviewReplyList() {
    $.ajax({
        url: `reviewReplyList/${globalThis.page}`,
        data: adminReviewBoardReplySearch,
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
    getAdminReviewReplyList();
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
    adminReviewBoardReplySearch.reviewBoardReplyContent = val;

    console.log( adminReviewBoardReplySearch.reviewBoardReplyContent + "777");
    getAdminReviewReplyList();
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
        text += '<div class="">';
        text += '<div class="page-button-margin">';
        text += '<div>';
        text += `<img src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_keyboard_arrow_left_48px-128.png" class="left-button" onclick="findPage(${startPage})" data-page = "${pageNumber}" aria-label="이전 목록">`;
        text += '</div>';
        text += '</div>';
        text += '</div>';
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
function showList(boardReplyDTOS) {
    var content = "";
    boardReplyDTOS.forEach(reply => {
        const formattedDate = formatDate(new Date(reply.writeDate));
        content +=
            `
                <tr>
                <td class="no-modal">
                    <input type="checkbox" name="check">
                    </td>
                    <td class="reply-id">${reply.id}</td>
                    <td>${reply.reviewTitle}</td>
                <td>${formattedDate}</td>
                <td class="reply-content">${reply.replyContent}</td>
                </tr>
            `
    });
    $contentWrap.append(content);

}

getAdminReviewReplyList();

// 선택된 항목 삭제하기
$('.confirm-delete').on('click', function () {
    var checkedIds = new Array();
    // 체크 박스 체크된 값
    $('input:checkbox[name=check]:checked').closest('tr').find('.reply-id').each(function () {
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
        url: "reviewBoardReply/delete",
        type: "delete",
        data: {
            "checkedIds": checkedIds,
        },
        success: function () {
            findPage(page);
        }
    });

    $('input:checkbox[id=allSelect]:checked').prop('checked', false);
});