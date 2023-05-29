
let page = 0;
const boardService = (() => {
    function getList(callback){
        console.log(page)
        $.ajax({
            url: `/mypage/review/${page}`,
            type: 'post',
            data: JSON.stringify({page:page}),
            contentType: "application/json;charset=utf-8",
            success: function(reviewDTOS){
                console.log("들어왓다")
                if (reviewDTOS.content.length === 0) { // 불러올 데이터가 없으면
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
    }
    return {getList: getList};
})();

/*${formattedDate}*/
function appendList(reviewDTOS) {
    let boardText3 = '';
    console.log(reviewDTOS.content);
    reviewDTOS.content.forEach(review => {
        let date = new Date(review.uploadDate); // assuming eventLike.registerDate is a valid date string
        let formattedDate = date.getFullYear() + '-' + (date.getMonth() + 1).toString().padStart(2, '0') + '-' + date.getDate().toString().padStart(2, '0');

        console.log(reviewDTOS);
        boardText3 +=  `
        <a class="parents-yard-board-item-link" href="/review/detail/${review.id}">
              <div class="parents-yard-board-item-wrapper">
                <!-- 카테고리 -->
                <span class="category"> <span>[${review.eventCategory}]</span> <span></span></span>
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
                        ${formattedDate}<span class="bottom-divide-line">ㅣ</span>
                    </span>
                    <svg viewBox="0 0 33 33" focusable="false" role="presentation" class="star_svg" aria-hidden="true">
                        <path d="M16.5 27l-7.652 4.674a2.001 2.001 0 0 1-2.988-2.171l2.08-8.722-6.81-5.833a2 2 0 0 1 1.143-3.513l8.937-.716 3.443-8.28a2.001 2.001 0 0 1 3.694.001l3.443 8.279 8.938.716a2.001 2.001 0 0 1 1.141 3.513l-6.81 5.833 2.081 8.722a2.001 2.001 0 0 1-1.481 2.41 2.002 2.002 0 0 1-1.507-.24L16.5 27z" fill-rule="evenodd"></path>
                    </svg>
                    <span class="star-score">
                        ${review.reviewScore}
                    </span>
                  </span>
                  <div class="parents-yard-board-item-thumbnail-wrapper">
<!--                    <span class="thumbnail" style="background-image: -webkit-image-set(url(&quot;https://cdn.wadiz.kr/ft/images/green001/2021/0504/20210504113227960_null.jpg/wadiz/thumbnail/138/format/jpg/quality/95/&quot;) 1x, url(&quot;https://cdn.wadiz.kr/ft/images/green001/2021/0504/20210504113227960_null.jpg/wadiz/thumbnail/276/format/jpg/quality/95/&quot;) 2x);"></span>-->
                       `
            if (review.files.length != 0){
                boardText3 +=
                `                   
                    <span class="thumbnail"><img style="width: 100%; height: 100%;" src="/reviewFiles/display?fileName=Review/${review.files[0].filePath}/${review.files[0].fileUUID}_${review.files[0].fileOriginalName}">
                </span>
                              `
            }
            boardText3 +=
                `
                </div>
                </div>
              </div>
            </a>

                          `
        ;
    });
    if (reviewDTOS.content.length === 0) { // 불러올 데이터가 없으면
        $(window).off('scroll'); // 스크롤이벤트 x
    }
    $('.collection-table').append(boardText3);
}

// 페이지 로딩 시 초기 리스트를 불러옴
boardService.getList(function(reviewDTOS) {
    page = 0;
    console.log(reviewDTOS.content);
    appendList(reviewDTOS);
    console.log(page + "페이지 로딩 시 초기화면")
});

console.log("sadasdasd");

// $(window).scroll(function() {
//     if($(window).scrollTop() + $(window).height() == $(document).height()) {
//         page++;
//         boardService.getList(appendList);
//         console.log(page)
//     }
// });

$(window).scroll(function() {
    if($(window).scrollTop() + $(window).height() > $(document).height() * 0.9) {
        page++;
        boardService.getList(appendList);
        console.log(page)
    }
});



