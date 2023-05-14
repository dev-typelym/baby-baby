$('.event-price').each(function() {
    var price = $(this).text();
    price = parseInt(price);
    price = price.toLocaleString();
    $(this).text(price);
});



let page = 0;
const boardService = (() => {
    page = 0;
    function getList(callback){
        $.ajax({
            url: `/event/list`,
            type: 'post',
            data: JSON.stringify({page:page}),
            contentType: "application/json;charset=utf-8",
            success: function(eventListDTO){
                console.log("들어왓다")
                if (eventListDTO.length === 0) { // 불러올 데이터가 없으면
                    console.log("막힘")
                    $(window).off('scroll'); // 스크롤 이벤트를 막음
                    return;
                }
                if(callback){
                    callback(eventListDTO);
                    console.log("들어왓다")
                }
            }
        });
        page++;
    }
    return {getList: getList};
})();

function appendList(eventListDTO) {
    let boardText3 = '';
    console.log(eventListDTO.content[0]);
    eventListDTO.content.forEach(eventList => {
        console.log(eventList);
        boardText3 +=  `
             <div role="presentation" class="instance">
                            <a class="item" href="">
                                <div class="thumbnail-container">
                                    <div class="thumbnail-list">
                                        <div class="photo-thumbnail"
                                             style="background-image:url('/images/main/images/event-001.jpg')"></div>
                                        <!-- 사진 div -->
                                    </div>
                                </div>
                                <div class="list-content">
                                    <div class="list-title">
                                        장항준 감독 영화 리바운드 재밌다던데 같이 보러 가실
                                        분
                                    </div>
                                    <div class="for-price-full-contain">
                                        <div class="for-price-wrap">
                                            <div class="list-writer">ss</div>
                                            <div class="list-date-container">
                                                        <span class="print-data"
                                                        >역삼역 4번 출구</span
                                                        >
                                            </div>
                                        </div>
                                        <span class="event-price-wrap">
                                                    <span class="event-price">45000</span
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
                                                                >2023-04-12</span
                                                                >
                                                                ~<span class="event-end-day"
                                                    >2023-06-24</span
                                                    >
                                                                <div class="like-count-container">
                                                                    <div class="people-icon"></div>
                                                                    <span class="like-count"
                                                                    >10</span
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
                                <svg
                                        viewBox="0 0 32 32"
                                        focusable="false"
                                        role="presentation"
                                        class="wish-button-svg"
                                        aria-hidden="true"
                                >
                                    <path
                                            d="M22.16 4h-.007a8.142 8.142 0 0 0-6.145 2.79A8.198 8.198 0 0 0 9.76 3.998a7.36 7.36 0 0 0-7.359 7.446c0 5.116 4.64 9.276 11.6 15.596l2 1.76 2-1.76c6.96-6.32 11.6-10.48 11.6-15.6v-.08A7.36 7.36 0 0 0 22.241 4h-.085z"
                                    ></path>
                                </svg>
                            </button>
                        </div>
                        `
                        ;
    });
    if (eventListDTO.length === 0) { // 불러올 데이터가 없으면
        $(window).off('scroll'); // 스크롤이벤트 x
    }
    $('.collection-table').append(boardText3);
}

// 페이지 로딩 시 초기 리스트를 불러옴
boardService.getList(function(eventListDTO) {
    appendList(eventListDTO);
});

console.log("sadasdasd");

$(window).scroll(function() {
    if($(window).scrollTop() + $(window).height() == $(document).height()) {
        page++;
        boardService.getList(appendList);
    }
});


/*좋아요 버튼*/
/* 좋아요 버튼 */
$(function () {
    $('.wish-button').click(function (e) {
        var target = $(e.target);
        if ($(target).attr('aria-pressed') == 'false') {
            $(target).attr('aria-pressed', 'true'); //하트 색 채우기
            $('.like-cancel-text').hide(); //해제 문구
            $('#like-modal').css({ right: '-30%' }); //오->왼 슬라이드 등장
            $('#like-modal').animate({ right: '30px' }, { duration: 500 }); //오->왼 슬라이드 등장
            $('#like-modal').show(); //슬라이드 보이기
            $('.like-text').show(); //찜 추가 문구
            $('#like-modal').fadeOut(); //사라지기
        } else {
            $(target).attr('aria-pressed', 'false'); //색 비우기
            $('.like-text').hide(); //찜 추가 문구
            $('#like-modal').css({ right: '-30%' }); //오->왼 슬라이드 등장
            $('#like-modal').animate({ right: '30px' }, { duration: 500 }); //오->왼 슬라이드 등장
            $('#like-modal').show(); //슬라이드 보이기
            $('.like-cancel-text').show(); //찜 해제 문구
            $('#like-modal').fadeOut(); //사라지기
        }
    });
});

/* top 버튼  */
var topEle = $('.top-btn');
var delay = 400;
console.log(topEle);

/* top show-hide */
$(window).scroll(function () {
    if ($(this).scrollTop() > 200) {
        $('.top-btn').fadeIn();
    } else {
        $('.top-btn').fadeOut();
    }
});

/* top버튼- 위로 올리기 */
$('.top-btn').click(function () {
    $('html, body').animate({ scrollTop: 0 }, delay);
    return false;
});


