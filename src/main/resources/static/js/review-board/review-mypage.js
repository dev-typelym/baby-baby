globalThis.page = 1;
const boardService = (() => {
    // page = 0;
    function getList(callback){
        $.ajax({
            url: `/mypage/review/${globalThis.page}`,
            type: 'post',
            data: JSON.stringify,
            contentType: "application/json;charset=utf-8",
            success: function(reviewDTOS){
                console.log("들어왓다")
                if (reviewDTOS.length === 0) { // 불러올 데이터가 없으면
                    console.log("막힘")
                    $(window).off('scroll'); // 스크롤 이벤트를 막음
                    return;
                }
                if(callback){
                    callback(reviewDTOS);
                    console.log("들어왓다")
                }
            }
        });
        page++;
    }
    return {getList: getList};
})();

function appendList(reviewDTOS) {
    let boardText3 = '';
    console.log(reviewDTOS.content);
    reviewDTOS.content.forEach(review => {
        console.log(reviewDTOS);
        boardText3 +=  `
              <a class="parents-yard-board-item-link">
              <div class="parents-yard-board-item-wrapper">
                <!-- 카테고리 -->
                <span class="category"> <span>[박물관]</span> <span>온 가족이 함께 즐기는 VR 행사</span></span>
                <div class="parents-yard-board-item-container">
                    <!-- 내가 쓴 체험학습 이름 -->
                    ${review.boardTitle}
                  <h3 class="parents-yard-board-item-title">
                  </h3>
                  <!-- 내가 쓴 리뷰 내용 -->
                  <p class="parents-yard-board-item-content" >
                     ${review.boardContent}
                  </p>
                  <span class="parents-yard-board-item-date">
                    <span>
                        2021.06.22${review.registerDate}<span class="bottom-divide-line">ㅣ</span>
                    </span>
                    <svg viewBox="0 0 33 33" focusable="false" role="presentation" class="star_svg" aria-hidden="true">
                        <path d="M16.5 27l-7.652 4.674a2.001 2.001 0 0 1-2.988-2.171l2.08-8.722-6.81-5.833a2 2 0 0 1 1.143-3.513l8.937-.716 3.443-8.28a2.001 2.001 0 0 1 3.694.001l3.443 8.279 8.938.716a2.001 2.001 0 0 1 1.141 3.513l-6.81 5.833 2.081 8.722a2.001 2.001 0 0 1-1.481 2.41 2.002 2.002 0 0 1-1.507-.24L16.5 27z" fill-rule="evenodd"></path>
                    </svg>
                    <span class="star-score">
                        ${review.reviewScore}
                    </span>
                  </span>
                  <div class="parents-yard-board-item-thumbnail-wrapper">
                    <span class="thumbnail" style="background-image: -webkit-image-set(url(&quot;https://cdn.wadiz.kr/ft/images/green001/2021/0504/20210504113227960_null.jpg/wadiz/thumbnail/138/format/jpg/quality/95/&quot;) 1x, url(&quot;https://cdn.wadiz.kr/ft/images/green001/2021/0504/20210504113227960_null.jpg/wadiz/thumbnail/276/format/jpg/quality/95/&quot;) 2x);"></span>
                </div>
                </div>
              </div>
            </a>
                        `
        ;
    });
    if (reviewDTOS.length === 0) { // 불러올 데이터가 없으면
        $(window).off('scroll'); // 스크롤이벤트 x
    }
    $('.collection-table').append(boardText3);
}

// 페이지 로딩 시 초기 리스트를 불러옴
boardService.getList(function(reviewDTOS) {
    console.log("초기 리스트")
    appendList(reviewDTOS);
});


$(window).scroll(function() {
    if($(window).scrollTop() + $(window).height() == $(document).height()) {
        page++;
        boardService.getList(appendList);
    }
});
