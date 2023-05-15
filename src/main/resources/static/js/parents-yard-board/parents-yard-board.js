const PAGE_AMOUNT = 10;
const $itemWrap = $(".show-item-wrap");
const SEARCH_URL = "/parentsYard/list/show";
const $pageWrap = $(".paging-list");
const $contentWrap = $(".parents-yard-board-item-link");


function getParensBoardList() {
    $.ajax({
        url: `list/show/${globalThis.page}`,
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
    getParensBoardList();
}



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
        if (pageNumber  + 1 == page) {
            text += `<button type="button" onclick="findPage(${i})" class="paging-list-item-btn active">${i}</button>`;
        } else {
            text += `<button type="button" onclick="findPage(${i})" class="paging-list-item-btn">${i}</button>`;
        }
        text += '</li>';
    }

    // Next button
    if (hasNext) {
        text += '<li class="paging-list-item">';
        text += `<button class="paging-btn-next" onclick="findPage(${endPage+1})" data-page="' + (pageNumber + 1) + '" aria-label="다음 목록">`;
        text += '<div class="paging-btn-next-image-wrapper">';
        text += '<img class="paging-btn-next-image" src="/images/parents-yard-board/parents-yard-board/paging-right.png">';
        text += '</div>';
        text += '</button>';
        text += '</li>';
    }

    text += '</ul>';

    $pageWrap.html(text);
}


//    부모님 마당 목록
function showList(boardDTOS) {
    var content = "";
    boardDTOS.forEach(board => {
        const formattedDate = formatDate(new Date(board.parentsBoardRegisterDate));
        content += ` 
                         <a class="parents-yard-board-item-link">
            <div class="parents-yard-board-item-wrapper">
                <span class="category"><span>[${board.eventCategory}]</span> ${board.eventTitle}</span>
                <div class="parents-yard-board-item-container">
                    <h3 class="parents-yard-board-item-title">
                        ${board.parentsBoardTitle}
                    </h3>
                    <p class="parents-yard-board-item-content">
                        ${board.parentsBoardContent}
                    </p>
                    <div class="parents-yard-board-item-bottom-wrapper">
                        <p class="parents-yard-board-item-writer" style="margin: 0;">
                            작성자: ${board.memberNickname}<span class="bottom-divide-line">ㅣ</span>
                        </p>
                        <span class="parents-yard-board-item-date">
                            ${formattedDate}
                        </span>
                    </div>
                    <div class="parents-yard-board-item-thumbnail-wrapper">
                        <span class="thumbnail" style="background-image: -webkit-image-set(url(&quot;https://cdn.wadiz.kr/ft/images/green001/2021/0504/20210504113227960_null.jpg/wadiz/thumbnail/138/format/jpg/quality/95/&quot;) 1x, url(&quot;https://cdn.wadiz.kr/ft/images/green001/2021/0504/20210504113227960_null.jpg/wadiz/thumbnail/276/format/jpg/quality/95/&quot;) 2x);"></span>
                    </div>
                </div>
            </div>
        </a>`


    });
    $contentWrap.append(content);

}

getParensBoardList();
