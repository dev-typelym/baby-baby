/* 클릭 시 수정/삭제 버튼 창 */
/* function showList(e){
    if($(e).next().css('display') == 'none'){
        $(e).next().show();
    } else {
        $(e).next().hide();
    }
}
 */
function showList(e){
    console.log("들어옴@@@@")
    // if($(".comment-util-list").css('display') == 'none'){
    console.log($(e).next())
    if($(e).next().css('display') == 'none'){
        $(e).next().show();
        // $(".comment-util-list").show();
    } else {
        $(e).next().hide();
        // $(".comment-util-list").hide();
    }
}

/* 수정버튼 눌렀을 때 */
$(".modify-button").each((i, e) => {
    $(e).click(() => {
        $($(".modify-textarea")[i]).show();//수정영역
        $('.comment-util-list').hide();//수정,삭제 모달 숨기기
        $($('.comment-util')[i]).attr("disabled",true);//수정,삭제 버튼 비활성화
        $($(".comment-content")[i]).css("display","none");//기존영역 숨기기
        $($(".comment-date")[i]).css("display","none");//날짜 숨기기
        $($(".comment-bottom")[i]).css("display","block");//취소,수정완료 버튼
    });
});

/* 삭제버튼 눌렀을 때 - 모달 */
function showModal(){
    $('.modal-copy').css('display', 'block');
    $('.modal-bg').css('display', 'block');
    $('body').css('overflow', 'hidden');
}

function closeModal(){
    $('.modal-copy').css('display', 'none');
    $('.modal-bg').css('display', 'none');
    $('body').css('overflow', 'visible');
}

/* 취소버튼 - 원래 상태로 복구 */
$(".modify-cancel").each((i, e) => {
    $(e).click(() => {
        $($(".modify-textarea")[i]).hide();//수정영역
        $('.comment-util-list').hide();//수정,삭제 모달 숨기기
        $('.comment-util').attr("disabled",false);
        $($(".comment-content")[i]).css("display","block");//기존영역 숨기기
        $($(".comment-date")[i]).css("display","block");//날짜 숨기기
        $($(".comment-bottom")[i]).css("display","none");//취소,수정완료 버튼
    });
});



/* 클릭 했을 때 색 변경/취소 */
// $(".btn-like").click(() => {
//     if(!$(".btn-like").hasClass("active-heart-button")){
//         /* 여기에변경될 요소 */
//         $(".none-heart").hide();
//         $(".active-heart").show();
//         $(".btn-like").addClass("active-heart-button");
//     }else {
//         /* 원래 요소 */
//         $(".none-heart").show();
//         $(".active-heart").hide();
//         $(".btn-like").removeClass("active-heart-button");
//     }
// });





/* 카카오톡 공유하기 API */
/* function shareMessage() {
Kakao.Share.sendDefault({
    objectType: 'feed',
    content: {
    title: '리바운드 보러갈 사람?',
    description: '#리바운드 #슬램덩크 #영화 #스포 #강백호 #농구',
    imageUrl:
        'http://127.0.0.1:5500/static/images/free-board/rebound.jpg',
    link: {
        // [내 애플리케이션] > [플랫폼] 에서 등록한 사이트 도메인과 일치해야 함
        mobileWebUrl: 'http://127.0.0.1:5500',
        webUrl: 'http://127.0.0.1:5500',
    },
    },
    social: {
    likeCount: 286,
    commentCount: 45,
    sharedCount: 845,
    },
    buttons: [
    {
        title: '웹으로 보기',
        link: {
        mobileWebUrl: 'https://developers.kakao.com',
        webUrl: 'https://developers.kakao.com',
        },
    },
    {
        title: '앱으로 보기',
        link: {
        mobileWebUrl: 'https://developers.kakao.com',
        webUrl: 'https://developers.kakao.com',
        },
    },
    ],
});
} */

/* DateTime을 Date로 바꾸기 */


var originalDateElement = document.querySelector('.parents-yard-board-detail-date');
var originalDate = originalDateElement.getAttribute('data-original-date');
var formattedDate = formatDate(originalDate);

// 기존 내용을 지우고 포맷팅된 날짜 값을 삽입
originalDateElement.innerHTML = '';
originalDateElement.innerHTML = formattedDate;

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

// 댓글 무한 스크롤
let page = 0;
const replyService = (() => {
    function getList(callback){
        console.log(boardId + "보오드");
        $.ajax({
            url: `/parentsYard/reply/list/show/${page}/${boardId}`,
            type: 'get',
            data: {page: page, boardId: boardId},
            contentType: "application/json;charset=utf-8",
            success: function(parentsBoardReplyDTOS){
                console.log("드렁");
                callback(parentsBoardReplyDTOS);
            }
        });
    }
    return {getList: getList};
})();


/*${formattedDate}*/
function appendList(parentsBoardReplyDTOS) {
    let replyText = '';
    console.log(parentsBoardReplyDTOS.content);
    parentsBoardReplyDTOS.content.forEach(replyDTOS => {
        console.log(replyDTOS);
        replyText +=  ` 
                        <ul id="comment-list-detail">
                            <li class="top" style="display: list-item;">
                                <div class="comment-wrap">
                                    <div class="comment-info">
                                        <img src="/images/parents-yard-board/parents-yard-board-detail/profile-sample1.jpg">
                                        <span class="name">${replyDTOS.memberNickName}</span>
                                        <button class="comment-util" onclick="showList(this)"></button>
                                        <ul class="comment-util-list">
                                            <li>
                                                <button type="button" class="modify-button">수정</button>
                                            </li>
                                            <li>
                                                <button>삭제</button>
                                            </li>
                                        </ul>
                                    </div>
                                    <p class="comment-content">${replyDTOS.parentsBoardReplyContent}</p>
                                    <textarea id="" class="modify-textarea" style="display: none;">정말 재밌어 보이네요~ 다음에 저희 아이도 이 체험학습에 보내야겠어요!</textarea>
                                    <div class="comment-date">
                                        ${formatDate(replyDTOS.updateDate)}
                                    </div>
                                    <div class="comment-bottom" style="display:none;">
                                        <button type="button" class="modify-cancel">취소</button>
                                        <button type="button" class="modify-confirm">수정완료</button>
                                    </div>
                                </div>
                            </li>
                        </ul> `
        ;
    });
    $('.comment-list').append(replyText);
}



// 페이지 로딩 시 초기 리스트를 불러옴
replyService.getList(function(parentsBoardReplyDTOS) {
    replyPage = 0;
    console.log(parentsBoardReplyDTOS.content);
    appendList(parentsBoardReplyDTOS);
    console.log(replyPage + "페이지 로딩 시 초기화면")
});

$('.btn-comment').click(function() {
        page++;
        replyService.getList(appendList);
        console.log(page)
});


// 카테고리 최신글 2개 가져오기 ajax
const categoryService = (() => {
    function getCategoryList(callback){
        console.log(boardId + "보오드으으으으으으");
        $.ajax({
            url: `/parentsYard/detail/category/${boardId}`,
            type: 'post',
            contentType: "application/json;charset=utf-8",
            success: function(parentsBoardDetail){
                console.log("드렁");
                callback(parentsBoardDetail);
            }
        });
    }
    return {getCategoryList: getCategoryList};
})();


/* 카테고리 최신순 2개 가져오기 */
function appendCategoryList(parentsBoardDetail) {
    let categoryText = '';
    console.log(parentsBoardDetail.content);
    parentsBoardDetail.content.forEach(category => {
        categoryText +=  ` 
                        <h4>
                    <em>${category.eventCategory}</em>
                    카테고리의 최신글
                </h4>
                <div class="other-list">
                    <ul>
                        <li>
                            <a>
                                <div class="other-story">
                                    <div class="story-info">
                                        <em class="category">${category.eventCategory}</em>
                                        <p class="other-title">${category.parentsBoardTitle}</p>
                                        <p class="other-content">${category.parentsBoardContent}</p>
                                        <p class="info">
                                            <em class="date">${category.parentsBoardRegisterDate}</em>
                                        </p>
                                    </div>
                                    <div class="img-wrap">
                                        <img src="/images/parents-yard-board/parents-yard-board-detail/next-write-thumbnail.png">
                                    </div>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>`
        ;
    });
    $('.free-board-other').append(categoryText);
}



// 페이지 로딩 시 초기 리스트를 불러옴
categoryService.getCategoryList(function(parentsBoardDetail) {
    console.log(parentsBoardDetail.content);
    appendCategoryList(parentsBoardDetail);
});
