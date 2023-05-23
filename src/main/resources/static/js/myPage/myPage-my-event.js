let page = 0;
const boardService = (() => {
    function getList(callback){
        $.ajax({
            url: `/mypage/my-event/${page}`,
            type: 'post',
            data: JSON.stringify({page:page}),
            contentType: "application/json;charset=utf-8",
            success: function(eventDTOS){
                console.log("들어왓다")
                if (eventDTOS.content.length === 0) { // 불러올 데이터가 없으면
                    console.log("막힘")
                    $(window).off('scroll'); // 스크롤 이벤트를 막음
                    return;
                }
                if(callback){
                    callback(eventDTOS);
                    console.log("들어왓다")
                }
            }
        });
        page++;
    }
    return {getList: getList};
})();

function appendList(eventDTOS) {
    let boardText3 = '';
    console.log(eventDTOS.content);
    eventDTOS.content.forEach(event => {
        // let date = new Date(event.parentsBoardRegisterDate); // assuming eventLike.registerDate is a valid date string
        // let formattedDate = date.getFullYear() + '-' + (date.getMonth() + 1).toString().padStart(2, '0') + '-' + date.getDate().toString().padStart(2, '0');

        boardText3 +=  `
                              <a class="parents-yard-board-item-link" href="/event/detail/${event.id}">
              <div class="parents-yard-board-item-wrapper">
                <!-- 카테고리 -->
                <span class="category"><span>${'['+event.category+']' + ' ' + event.boardTitle}</span></span>
                <div class="parents-yard-board-item-container">
                    <!-- 내가 쓴 체험학습 이름 -->
                  <h3 class="parents-yard-board-item-title">
                   ${event.boardTitle}
                  </h3>
                  <!-- 내가 쓴 리뷰 내용 -->
                  <p class="parents-yard-board-item-content">
                  ${event.boardContent}
                  </p>
                  <span class="parents-yard-board-item-date">11</span>
                  `
                if(event.files != 0) {
                    boardText3 += `
                        <div class="parents-yard-board-item-thumbnail-wrapper">
                    <span class="thumbnail">
                    <img style="width: 100%; height: 100%;" src="/eventFiles/display?fileName=Event/${event.files[0].filePath}/${event.files[0].fileUUID}_${event.files[0].fileOriginalName}">
                    </span>
                </div>
                        `
                }
                    boardText3 += `
                  
                </div>
              </div>
            </a>
                          `
        ;
    });
    if (eventDTOS.content.length === 0) { // 불러올 데이터가 없으면
        console.log("막힘?")
        $(window).off('scroll'); // 스크롤이벤트 x
    }
    $('.collection-table').append(boardText3);
}

boardService.getList(function(eventDTOS) {
    page = 0;
    console.log(eventDTOS.content);
    appendList(eventDTOS);
    console.log(page + "페이지 로딩 시 초기화면")
});

console.log("sadasdasd");

$(window).scroll(function() {
    if($(window).scrollTop() + $(window).height() > $(document).height() * 0.9) {
        page++;
        boardService.getList(appendList);
        console.log(page)
    }
});/*<img src="/parentsBoardFiles/display?fileName=ParentsBoard/${parent.parentsBoardFileDTOS[0].filePath}/${parent.parentsBoardFileDTOS[0].fileUUID}_${parent.parentsBoardFileDTOS[0].fileOriginalName}">*/