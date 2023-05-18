const PAGE_AMOUNT = 10;
const $itemWrap = $(".show-item-wrap");
const SEARCH_URL = "/review/list/show";
const $pageWrap = $(".paging-list");
const $contentWrap = $(".parents-yard-board-item-link");
const parentsBoardSearch = {
    searchTitle: null,
    categoryType: null,
    searchContent: null
};

function getReviewBoardList() {
    $.ajax({
        url: `/review/list/show/${globalThis.page}`,
        data: parentsBoardSearch,
        success: function (data) {
            console.log(data)
            $pageWrap.empty();
            showPage(data);
            $contentWrap.empty();
            showList(data.content);
        }
    });
}

globalThis.page = 1;

function findPage(page) {
    globalThis.page = page;
    getReviewBoardList();
}

$(".filter-select-box").on("change", function () {
    let val;
    if ($(this).val() === "ALL") {
        val = null;
    } else {
        val = $(this).val();
    }
    parentsBoardSearch.categoryType = val;
    getReviewBoardList();
});

$("form[name='search-form']").on("submit", function (e) {
    e.preventDefault();
    let val;
    let searchSelected = $(".filter-for-serach").val();
    let $search = $(".search-input");
    if ($search.val() === "") return;

    val = $search.val();

    if (searchSelected === "title") {
        parentsBoardSearch.searchTitle = val;
        parentsBoardSearch.searchContent = "null";
    } else if (searchSelected === "titleContents") {
        parentsBoardSearch.searchContent = val;
        parentsBoardSearch.searchTitle = val;
    }
    console.log(parentsBoardSearch.searchContent + "parentsBoardSearch의 content");
    console.log(parentsBoardSearch.searchTitle + "parentsBoardSearch의 title");
    getReviewBoardList();
    // parentsBoardSearch.searchContent = "null";
    // parentsBoardSearch.searchTitle = "null";
});

// 검색 옵션 바뀔 때마다 검색어 창 비우기
$(".filter-for-serach").on("change", function () {
    $(".search-input").val("");
});


// 페이지 불러오기
function showPage(data) {
    let pageable = data.pageable;
    let pageNumber = pageable.pageNumber;
    let totalPages = data.totalPages;
    let count = Math.floor(pageNumber / PAGE_AMOUNT);

    let startPage = count * PAGE_AMOUNT;
    let endPage = startPage + PAGE_AMOUNT;

    endPage = endPage > data.totalPages ? data.totalPages : endPage;

    let hasPrev = startPage > 1;
    // 170 page / 5 = 24 -> 171 /
    let hasNext = endPage < data.totalPages;

    let text = "";


    // Previous button
    if (hasPrev) {
        text += '<li class="paging-list-item">';
        text += `<button class="paging-btn-prev" onclick="findPage(${startPage})" data-page = "${pageNumber}" aria-label="이전 목록">`;
        text += '<div class="paging-btn-prev-image-wrapper">';
        text += '<img class="paging-btn-prev-image" src="/images/parents-yard-board/parents-yard-board/paging-left.png">';
        text += '</div>';
        text += '</button>';
        text += '</li>';
    }

    // Page buttons
    for (let i = startPage + 1; i < endPage + 1; i++) {
        let page = i;
        text += '<li class="paging-list-item">';
        if (pageNumber + 1 == page) {
            text += `<button type="button" onclick="findPage(${i})" class="paging-list-item-btn active">${i}</button>`;
        } else {
            text += `<button type="button" onclick="findPage(${i})" class="paging-list-item-btn">${i}</button>`;
        }
        text += '</li>';
    }

    // Next button
    if (hasNext) {
        text += '<li class="paging-list-item">';
        text += `<button class="paging-btn-next" onclick="findPage(${endPage + 1})" data-page="' + (pageNumber + 1) + '" aria-label="다음 목록">`;
        text += '<div class="paging-btn-next-image-wrapper">';
        text += '<img class="paging-btn-next-image" src="/images/parents-yard-board/parents-yard-board/paging-right.png">';
        text += '</div>';
        text += '</button>';
        text += '</li>';
    }

    text += '</ul>';

    $pageWrap.html(text);
}

//    리뷰 목록
function showList(boardDTOS) {
    console.log(boardDTOS)
    let content = "";
    boardDTOS.forEach(board => {
        console.log(board)
        const formattedDate = formatDate(new Date(board.updateDate));
        content += `
                         <a class="parents-yard-board-item-link">
            <div class="parents-yard-board-item-wrapper">
                <span class="category"><span> [ ${convertCategory(board.eventCategory)} ] </span> ${board.eventTitle}</span>
                <div class="parents-yard-board-item-container">
                    <h3 class="parents-yard-board-item-title">
                        ${board.boardTitle}
                    </h3>
                    <p class="parents-yard-board-item-content">
                        ${board.boardContent}
                    </p>
                    <div class="parents-yard-board-item-bottom-wrapper">
                        <p class="parents-yard-board-item-writer" style="margin: 0;">
                            작성자: ${board.memberNickName}<span class="bottom-divide-line">ㅣ</span>
                        </p>
                        <span class="parents-yard-board-item-date">
                            ${formattedDate}
                        </span>
                    </div>
                    <div class="parents-yard-board-item-thumbnail-wrapper">
            `;

        if(board.files != undefined && board.files.length > 0) {
            content += `
                        <span>
                            <img class="thumbnail"  src="/reviewFiles/display?fileName=Review/${board.files[0].filePath}/${board.files[0].fileUUID}_${board.files[0].fileOriginalName}">
                        </span>
                        `
        }
        content += `
                    </div>
                </div>
            </div>
        </a>`
    });
    $contentWrap.append(content);

}


getReviewBoardList();
/* localDateTime을 Date로 깔끔하게 만드는 코드 */
function formatDate(originalDate) {
    let date = new Date(originalDate);
    let formattedDate = date.toLocaleDateString("ko-KR", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit"
    });

    // 마지막 점 제거
    formattedDate = formattedDate.replace(/\.$/, "");

    return formattedDate;
}

function convertCategory(category) {
//    AGRICULTURE, ART, TRADITION, CRAFT, SCIENCE, MUSEUM, SPORTS, ETC
    let categoryResult;

    if(category == "AGRICULTURE"){
        categoryResult = "농촌";
    } else if(category == "ART"){
        categoryResult = "예술";
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






