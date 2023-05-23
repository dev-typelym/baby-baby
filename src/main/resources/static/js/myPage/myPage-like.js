/* mypage-like */
/* 하트 누르면 없어지게 하는 버튼 */
// $('.wish-button').on('click', function(){
//     let svgPath = $(this).children().children()
//     if(svgPath.css('fill') == 'rgb(255, 0, 0)'){
//         svgPath.css('fill', 'none')
//     } else{
//         svgPath.css('fill', 'red')
//     }
// })

$(document).on('click', '.wish-button', function(){
    let svgPath = $(this).children().children();
    if(svgPath.css('fill') == 'rgb(255, 0, 0)'){
        svgPath.css('fill', 'none')
    } else{
        svgPath.css('fill', 'red')
    }
});


let page = 0;
const boardService = (() => {
    function getList(callback){
        console.log(page)
        $.ajax({
            url: `/mypage/play-like/${page}`,
            type: 'post',
            data: JSON.stringify({page:page}),
            contentType: "application/json;charset=utf-8",
            success: function(eventLikeDTOS){
                console.log("들어왓다")
                if (eventLikeDTOS.content.length === 0) { // 불러올 데이터가 없으면
                    console.log("막힘")
                    $(window).off('scroll'); // 스크롤 이벤트를 막음
                    return;
                }
                if(callback){
                    callback(eventLikeDTOS);
                    console.log("들어왓다")
                }
            }
        });
    }
    return {getList: getList};
})();

$('.collection-table').on('click', '.wish-button', function() {
    var eventId = $(this).find('.like-id').val();
    console.log('Clicked ID: ' + eventId);
    deleteLike(eventId);
});

//삭제 좋아요
function deleteLike(eventId) {
    deleteLikeYou(eventId);
}

function deleteLikeYou(eventId){
    $.ajax({
        url: `/mypage/delete/${eventId}`,
        type: 'post',
        data: JSON.stringify({eventId:eventId}),
        contentType: "application/json;charset=utf-8",
        success: function(){
            $('.collection-table').empty();
            boardService.getList(appendList);
        }
    });
}

// })();


/*${formattedDate}*/
function appendList(eventLikeDTOS) {
    let boardText3 = '';
    console.log(eventLikeDTOS.content);
    eventLikeDTOS.content.forEach(eventLike => {
        let startDate = new Date(eventLike.calendar.startDate); // assuming eventLike.registerDate is a valid date string
        let formattedDate = startDate.getFullYear() + '-' + (startDate.getMonth() + 1).toString().padStart(2, '0') + '-' + startDate.getDate().toString().padStart(2, '0');
        let endDate = new Date(eventLike.calendar.endDate); // assuming eventLike.registerDate is a valid date string
        let formattedEndDate = endDate.getFullYear() + '-' + (endDate.getMonth() + 1).toString().padStart(2, '0') + '-' + endDate.getDate().toString().padStart(2, '0');

        console.log(eventLikeDTOS);
        boardText3 +=  `
                                  
                                  <div role="presentation" class="instance">
                <a class="item" href="/event/detail/${eventLike.eventId}">
                    <div class="thumbnail-container">
                        <div class="thumbnail-list">
                              `
                        if(eventLike.eventFileDTOS.length != 0) {
                                boardText3 += `
                            <div class="photo-thumbnail">
                                <img style="width: 100%; height: 100%;" src="/eventFiles/display?fileName=Event/${eventLike.eventFileDTOS[0].filePath}/${eventLike.eventFileDTOS[0].fileUUID}_${eventLike.eventFileDTOS[0].fileOriginalName}">
                             </div>
                                `
                            }
                    boardText3 += `
                            <!-- 사진 div -->
                        </div>
                    </div>
                    <div class="list-content">
                        <div class="list-title" >
                            ${eventLike.boardTitle}
                        </div>
                        <div class="for-price-full-contain">
                            <div class="for-price-wrap">
                                <div class="list-writer" >${eventLike.memberName}</div>
                                <div class="list-date-container">
                                    <span class="print-data"
                                        >${eventLike.eventAddress}</span
                                    >
                                </div>
                            </div>
                            <span class="event-price-wrap">
                                <span class="event-price">${eventLike.eventPrice}</span
                                ><span>원</span>
                            </span>
                        </div>
                        <div clsas="list-footer">
                            <div class="list-footer-container">
                                <div class="loca-status-container">
                                    <div class="status-community">
                                        <i class="second-confirm"></i>
                                        <span class="ing">
                                            <span class="event-start-day"
                                                >${formattedDate}</span>
                                            ~ <span class="event-end-day"
                                                >${formattedEndDate}</span
                                            >
                                            <div class="like-count-container">
                                                <div class="people-icon"></div>
                                                <span class="like-count"
                                                    >${eventLike.eventRecruitCount}</span
                                                >
                                                <span>명 모집</span>
                                            </div>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
                <button type="button" class="wish-button" aria-pressed="false">
                <input type="hidden" value="${eventLike.eventId}" name="eventId" class="like-id">
                    <svg
                        viewBox="0 0 32 32"
                        focusable="false"
                        role="presentation"
                        class="wish-button-svg"
                        aria-hidden="true"
                    >
                        <path style="fill:#ff0000"
                            d="M22.16 4h-.007a8.142 8.142 0 0 0-6.145 2.79A8.198 8.198 0 0 0 9.76 3.998a7.36 7.36 0 0 0-7.359 7.446c0 5.116 4.64 9.276 11.6 15.596l2 1.76 2-1.76c6.96-6.32 11.6-10.48 11.6-15.6v-.08A7.36 7.36 0 0 0 22.241 4h-.085z"
                        ></path>
                    </svg>
                </button>
                
            </div>
                          `
        ;
    });
    if (eventLikeDTOS.content.length === 0) { // 불러올 데이터가 없으면
        $(window).off('scroll'); // 스크롤이벤트 x
    }
    $('.collection-table').append(boardText3);
}

// 페이지 로딩 시 초기 리스트를 불러옴
boardService.getList(function(eventLikeDTOS) {
    page = 0;
    console.log(eventLikeDTOS.content);
    appendList(eventLikeDTOS);
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


/*이벤트 좋아요 유찬 var*/




