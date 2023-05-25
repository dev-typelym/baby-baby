const PAGE_AMOUNT = 10;
const $itemWrap = $(".show-item-wrap");
const SEARCH_URL = "/review/list/show";
const $pageWrap = $(".paging-list");
const $contentWrap = $(".reviewList");
const parentsBoardSearch = {
    searchTitle: null,
    categoryType: null,
    searchContent: null
};

// 카테고리 별 정렬

function getParentsBoardList() {
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
    getParentsBoardList();
}

/* 카테고리 바꿨을 때 */ /*민구버전*/
// $("#filter-select").on("change", function () {
//     let val;
//     if ($(this).val() === "RECENT") val = null;
//     val = $(this).val();
//
//     parentsBoardSearch.categoryType = val;
//
//     getParentsBoardList();
// });

console.log(parentsBoardSearch.categoryType+"카아테에고오리이");
/* 카테고리 바꿨을 때 */ /*동한 버전*/
$("#filter-select").on("change", function () {
    let val;
    if ($(this).val() === "ALL") {
        val = null;
    } else {
        val = $(this).val();
    }
    console.log(val + "123456789");
    parentsBoardSearch.categoryType = val;
    getParentsBoardList();
});

// 검색 조건 별 수행 민구버젼
// $("form[name='search-form']").on("submit", function (e) {
//     e.preventDefault();
//     let val;
//
//     // 빈 문자열이면 검색 수행 안됨
//     let $search = $(".search-input");
//     if($search.val() === "") return;
//
//     val = $search.val();
//
//     parentsBoardSearch.searchTitle = val;
//
//     getParensBoardList();
// });


// 동한 버전
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
    console.log(parentsBoardSearch.searchContent + "777");
    console.log(parentsBoardSearch.searchTitle + "888");
    getParentsBoardList();
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
function showList(reviewDTOS) {
    console.log(reviewDTOS)
    var content = "";
    reviewDTOS.forEach(review => {
        const convertedCategory = convertCategory(review.eventCategory); // 영어 카테고리를 한글로 변환
        const formattedDate = formatDate(new Date(review.updateDate));
        content += ` 
                         <a href="/review/detail/${review.id}" class="parents-yard-board-item-link">
            <div class="parents-yard-board-item-wrapper">
                <span class="category"><span>[${convertedCategory}]</span> ${review.eventTitle}</span>
                <div class="parents-yard-board-item-container">
                    <h3 class="parents-yard-board-item-title">
                        ${review.boardTitle}
                    </h3>
                    <p class="parents-yard-board-item-content">
                        ${review.boardContent}
                    </p>
                    <div class="parents-yard-board-item-bottom-wrapper">
                        <p class="parents-yard-board-item-writer" style="margin: 0;">
                            작성자: ${review.memberNickName}<span class="bottom-divide-line">ㅣ</span>
                        </p>
                                                    <span class="parents-yard-board-item-date">
                                <span>
                                    ${formattedDate}<span class="bottom-divide-line">ㅣ</span>
                                </span>
                                <svg viewBox="0 0 33 33" focusable="false" role="presentation" class="star_svg" aria-hidden="true">
                                    <path d="M16.5 27l-7.652 4.674a2.001 2.001 0 0 1-2.988-2.171l2.08-8.722-6.81-5.833a2 2 0 0 1 1.143-3.513l8.937-.716 3.443-8.28a2.001 2.001 0 0 1 3.694.001l3.443 8.279 8.938.716a2.001 2.001 0 0 1 1.141 3.513l-6.81 5.833 2.081 8.722a2.001 2.001 0 0 1-1.481 2.41 2.002 2.002 0 0 1-1.507-.24L16.5 27z" fill-rule="evenodd"></path>
                                </svg>
                                <span class="star-score">
                                    ${review.reviewScore}
                                </span>
                            </span>
                        </div>
                        <div class="parents-yard-board-item-thumbnail-wrapper">
                        `
        if(review.files.length != 0) {
            content += `
                        <span>
                            <img class="thumbnail"  src="/reviewFiles/display?fileName=Review/${review.files[0].filePath}/${review.files[0].fileUUID}_${review.files[0].fileOriginalName}">
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


getParentsBoardList();


// 카테고리 한국어로 바꾸는 코드
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


