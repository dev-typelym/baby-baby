// $title.on('click', function () {
//     let index = $(this).parent().index();
//     let $currentContent = $content.eq(index);
//
//     if ($currentContent.is(':visible')) {
//       $currentContent.hide();
//     } else {
//       $content.hide();
//       $currentContent.show();
//     }
// });

// $(document).on('click', '.article', function() {
//     console.log("as")
//     let index = $(this).parent().index();
//     let $currentContent = $content.eq(index);
//     if ($currentContent.is(':visible')) {
//       $currentContent.hide();
//     } else {
//       $content.hide();
//       $currentContent.show();
//     }
// });

/* announcement.js */


// $('.contents').on('click', '.article', function() {
//     console.log("as")
//     let index = $(this).parent().index();
//     let $currentContent = $content.eq(index);
//     if ($currentContent.is(':visible')) {
//         $currentContent.hide();
//         console.log("여기까지는오지 ?")
//     } else {
//         $content.hide();
//         console.log("여기까지는오지1 ?")
//         $currentContent.show();
//         console.log("여기까지는오지2 ?")
//     }
// });

function toggle(e) {
    console.log("as")
    let $content = $('.admin-writed');
    let index = $(e).parent().index();
    let $currentContent = $content.eq(index);
    if ($currentContent.is(':visible')) {
        $currentContent.hide();
        console.log("여기까지는오지 ?")
    } else {
        $content.hide();
        console.log("여기까지는오지1 ?")
        $currentContent.show();
        console.log("여기까지는오지2 ?")
    }
};




/* ajax */



let page = 0;
let boardTitle = $('input[name="boardTitle"]').val();
const boardService = (() => {
    function getList(callback){
        console.log(page)
        $.ajax({
            url: `/mypage/inquiry/${page}&&${boardTitle}`,
            type: 'post',
            data: JSON.stringify({page:page}),
            contentType: "application/json;charset=utf-8",
            success: function(AskDTOS){
                console.log("들어왓다")
                if (AskDTOS.content.length === 0) { // 불러올 데이터가 없으면
                    console.log("막힘")
                    $(window).off('scroll'); // 스크롤 이벤트를 막음
                    return;
                }
                if(callback){
                    callback(AskDTOS);
                    console.log("들어왓다")
                }
            }
        });
    }
    return {getList: getList};
})();

/*${formattedDate}*/
function appendList(AskDTOS) {
    let boardText3 = '';
    console.log(AskDTOS.content);
    AskDTOS.content.forEach(ask => {
        // let date = new Date(eventLike.registerDate); // assuming eventLike.registerDate is a valid date string
        // let formattedDate = date.getFullYear() + '-' + (date.getMonth() + 1).toString().padStart(2, '0') + '-' + date.getDate().toString().padStart(2, '0');

        boardText3 +=  `
        
                         <li class="one-content">
                    <div class="article" onclick="toggle(this)">
                        <div class="info">
                            <div class="text-wrapper">
                                <!-- 답변대기는 그냥 wiaiting 클래스 넣어주기 -->
                                <div class="notice waiting">
                                    <span>답변 대기</span>
                                </div>
                                <div class="content-title">
                                    ${ask.askBoardTitle}
                                </div>
                                <span class="author">
                                    아기자기
                                </span>
                                <span class="update-date">2023.01.30</span>
                            </div>
                        </div>
                    </div>
                    <div class="admin-writed">
                        <div class="announcement-content">
                            ${ask.answerContent}
                        </div>
                    </div>
                </li>
                          `
        ;
    });
    if (AskDTOS.content.length === 0) { // 불러올 데이터가 없으면
        $(window).off('scroll'); // 스크롤이벤트 x
    }
    $('.contents').append(boardText3);
}

// 페이지 로딩 시 초기 리스트를 불러옴
boardService.getList(function(AskDTOS) {
    page = 0;
    console.log(AskDTOS.content);
    appendList(AskDTOS);
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


