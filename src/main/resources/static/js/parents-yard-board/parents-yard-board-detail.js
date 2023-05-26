// function showList(e) {
//     // if($(".comment-util-list").css('display') == 'none'){
//     if ($(e).next().css('display') == 'none') {
//         $(e).next().show();
//         // $(".comment-util-list").show();
//     } else {
//         $(e).next().hide();
//         // $(".comment-util-list").hide();
//     }
// }

function showList(button) {
    var commentUtilList = $(button).parent().find('.comment-util-list');
    if (commentUtilList.css('display') == 'none') {
        commentUtilList.show();
    } else {
        commentUtilList.hide();
    }
}
/* 수정버튼 눌렀을 때 */
$('.comment-list').on('click', '.modify-button', function () {
    var index = $(this).index('.modify-button'); // 클릭한 버튼의 인덱스 확인
    $($(".modify-textarea")[index]).show(); // 수정영역
    $('.comment-util-list').hide(); // 수정, 삭제 모달 숨기기
    $($('.comment-util')[index]).attr("disabled", true); // 수정, 삭제 버튼 비활성화
    $($(".comment-content")[index]).css("display", "none"); // 기존영역 숨기기
    $($(".comment-date")[index]).css("display", "none"); // 날짜 숨기기
    $($(".comment-bottom")[index]).css("display", "block"); // 취소, 수정완료 버튼
});

/* 삭제버튼 눌렀을 때 - 모달 */
function showModal() {
    $('.modal-copy').css('display', 'block');
    $('.modal-bg').css('display', 'block');
    $('body').css('overflow', 'hidden');
}

function closeModal() {
    $('.modal-copy').css('display', 'none');
    $('.modal-bg').css('display', 'none');
    $('body').css('overflow', 'visible');
}

/* 취소버튼 - 원래 상태로 복구 */
$(".modify-cancel").each((i, e) => {
    $(e).click(() => {
        $($(".modify-textarea")[i]).hide();//수정영역
        $('.comment-util-list').hide();//수정,삭제 모달 숨기기
        $('.comment-util').attr("disabled", false);
        $($(".comment-content")[i]).css("display", "block");//기존영역 숨기기
        $($(".comment-date")[i]).css("display", "block");//날짜 숨기기
        $($(".comment-bottom")[i]).css("display", "none");//취소,수정완료 버튼
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
globalThis.page = 0;
globalThis.count = 0;

// 댓글 더보기
$('.comment-open').click(function () {
    // globalThis.page++;
    // replyService.getList(appendList);
    console.log("더보기 클릭: ");
    console.log(globalThis.page);
    getReplyList(globalThis.page);
});


// ====================================================================================카테고리

// 카테고리 최신글 2개 가져오기 ajax
const categoryService = (() => {
    function getCategoryList(callback) {
        $.ajax({
            url: `/parentsYard/detail/category/${boardId}`,
            type: 'post',
            data: {boardId: boardId},
            success: function (categoryResults) {
                callback(categoryResults);
            }
            // error: function(error) {
            //     console.log('Error fetching data:', error)
            // }
        });
    }

    return {getCategoryList: getCategoryList};
})();


/* 카테고리 최신순 2개 가져오기 */
function appendCategoryList(categoryResults) {
    let categoryText = '';
    categoryResults.forEach(category => {
        const convertedCategory = convertCategory(category.eventCategory); // 영어 카테고리를 한글로 변환
        const convertedTime = formatDate(category.parentsBoardUpdateDate);
        categoryText += ` 
                    <ul>
                        <li>
                            <a>
                                <div class="other-story">
                                    <div class="story-info">
                                        <em class="category">${convertedCategory}</em>
                                        <p class="other-title">${category.parentsBoardTitle}</p>
                                        <p class="other-content">${category.parentsBoardContent}</p>
                                        <p class="info">
                                            <em class="date">${convertedTime}</em>
                                        </p>
                                    </div>
                                    <div class="img-wrap">
                                        <img src="/parentsBoardFiles/display?fileName=ParentsBoard/${category.parentsBoardFileDTOS[0].filePath}/${category.parentsBoardFileDTOS[0].fileUUID}_${category.parentsBoardFileDTOS[0].fileOriginalName}">
                                    </div>
                                </div>
                            </a>
                        </li>
                    </ul>`
        ;
    });
    $('.other-list').append(categoryText);
    // globalThis.page++;
}

// 페이지 로딩 시 초기 리스트를 불러옴
categoryService.getCategoryList(function (categoryResults) {
    appendCategoryList(categoryResults);
});

// 카테고리 한국어로 바꾸는 코드
function convertCategory(category) {
//    AGRICULTURE, ART, TRADITION, CRAFT, SCIENCE, MUSEUM, SPORTS, ETC
    let categoryResult;

    if (category == "AGRICULTURE") {
        categoryResult = "농촌";
    } else if (category == "ART") {
        categoryResult = "예술";
    } else if (category == "TRADITION") {
        categoryResult = "전통";
    } else if (category == "CRAFT") {
        categoryResult = "공방";
    } else if (category == "SCIENCE") {
        categoryResult = "과학";
    } else if (category == "MUSEUM") {
        categoryResult = "박물관";
    } else if (category == "SPORTS") {
        categoryResult = "스포츠";
    } else {
        categoryResult = "기타";
    }
    return categoryResult;
}

// 댓글 삭제
// $(document).on('click', '.delete-reply', function () {
//     console.log("드로로로롱");
//     var replyId = $(this).data('reply-id'); // data-reply-id 속성을 통해 댓글의 ID 값을 가져옴
//     console.log("replyId2: " + replyId);
//     $.ajax({
//         url: `/parentsYard/reply/delete/${replyId}`,
//         type: 'post',
//         success: function (result) {
//             $(".comment-list").html("");
//             for (var i = 0; i < globalThis.page; i++) {
//                 console.log("댓글 삭제" + globalThis.page);
//                 getReplyList(i);
//             }
//
//         }
//     });
// });
// 댓글 삭제
$(document).on('click', '.delete-reply', function () {
    var replyId = $(this).data('reply-id'); // data-reply-id 속성을 통해 댓글의 ID 값을 가져옴
    $.ajax({
        url: `/parentsYard/reply/delete/${replyId}`,
        type: 'post',
        success: function (result) {
            $(".comment-list").html("");
            let round =  globalThis.page;
            for (var i = 0; i < round; i++) {
                console.log("댓글 삭제" + globalThis.page);
                setTimeout(function(i) {
                    getReplyList(i);
                }, i * 30, i);
                globalThis.page--;
            }
        }
    });
});

// 댓글 작성
$(".write-reply").click(function () {
    globalThis.page = 0;
    if ($('.replyContent').val() == "") {
        return;
    }

    $.ajax({
        url: `/parentsYard/reply/write/${boardId}`,
        type: 'get',
        data: {parentsBoardReplyContent: $('.replyContent').val()},
        success: function (result) {
            // appendList(result);
            $(".comment-list").html("");
            getReplyList(globalThis.page);
            $('.replyContent').val("");
        }
    })
});

// 댓글 수정
// $('.comment-list').on('click', '.modify-confirm', function () {
//     console.log("댓글수저어어엉~~~~");
//
//     var replyId = $(this).data('reply-id');
//     let replyContent = $(this).closest('.comment-wrap').find('.modify-textarea').val();
//
//     if (replyContent == "") {
//         return;
//     }
//
//     $.ajax({
//         url: `/parentsYard/reply/update/${replyId}/${replyContent}`,
//         type: 'post',
//         data: {replyContent: replyContent},
//         success: function (result) {
//             closeModal();
//             $(".comment-list").html("");
//             for (var i = 0; i < globalThis.page; i++) {
//                 console.log("댓글 수정" + globalThis.page);
//                 getReplyList(i);
//             }
//         }
//     });
//     console.log("댓글 수정" + globalThis.page);
// });
$('.comment-list').on('click', '.modify-confirm', function () {

    var replyId = $(this).data('reply-id');
    let replyContent = $(this).closest('.comment-wrap').find('.modify-textarea').val();

    if (replyContent == "") {
        return;
    }

    $.ajax({
        url: `/parentsYard/reply/update/${replyId}/${replyContent}`,
        type: 'post',
        data: {replyContent: replyContent},
        success: function (result) {
            closeModal();
            $(".comment-list").html("");
            let round = globalThis.page;

            for (var i = 0; i < round; i++) {
                console.log("댓글 수정 ajax-success: " + globalThis.page);
                setTimeout(function(i) {
                    getReplyList(i);
                }, i * 30, i);
                globalThis.page--;
            }

        }
    });
    console.log("댓글 수정완료 : " + globalThis.page);
});

// 댓글 총 수를 구하기 위한 globalThis.totalReplies


// 댓글목록 불러오기==========================================
function getReplyList(page) {
    $.ajax({
        url: `/parentsYard/reply/list/show/${page}/${boardId}`,
        data: {page: page, boardId: boardId},
        contentType: "application/json;charset=utf-8",
        success: function (data) {
            console.log(data)
            globalThis.count = data.count;
            showReplyList(data.data);
            getReplyCount();
            console.log("======count List success: ")
            console.log(count)
            console.log("======page List success: ")
            console.log(globalThis.page)
            if (page == (globalThis.count - 2)) {
                $(".btn-comment").hide();
            }else{
                $(".btn-comment").show();
            }
        }
    });

}

getReplyList(globalThis.page);




function showReplyCount(replyAllcount) {
    let countText = '';
    countText += `<em>${replyAllcount}</em>개의 댓글이 달려있습니다.`
    $('.comment-count').html(countText);
}

function getReplyCount() {
    $.ajax({
        url: `/parentsYard/reply/show/${boardId}`,
        data: {boardId: boardId},
        async:false,
        type: 'get',
        success: function (count) {
            let replyAllcount = count;
            showReplyCount(replyAllcount);
        }
    });
}


getReplyCount();




function showReplyList(data) {
    let parentsBoardDTOS = data.content;
    console.log(parentsBoardDTOS);
    parentsBoardDTOS.forEach((e,i) => {
        console.log("reply :" +  e);
        let text = '';
        text += ` 
                        <ul id="comment-list-detail">
                            <li class="top" style="display: list-item;">
                             <input class="replyId" type="hidden" value="${e.id}" style="display: none;">
                             <input class="parentsBoardId" type="hidden" value="${e.parentsBoardId} style="display: none;">
                                <div class="comment-wrap">
                                    <div class="comment-info">
                                       <img src="/eventFiles/display?fileName=/Member/Profile/${e.memberFilePath}/${e.memberFileUUID}_${e.memberFileOriginalName}">
                                        <span class="name">${e.memberNickName}</span>
                                        <button class="comment-util" onclick="showList(this)"></button>
                                        <ul class="comment-util-list">
                                            <li>
                                                <button type="button" class="modify-button">수정</button>
                                            </li>
                                            <li>
                                                <button  type="button" class="delete-reply" data-reply-id="${e.id}">삭제</button>
                                            </li>
                                        </ul>
                                    </div>
                                    <p class="comment-content">${e.parentsBoardReplyContent}</p>
                                    <textarea id="" class="modify-textarea" style="display: none;">${e.parentsBoardReplyContent}</textarea>
                                    <div class="comment-date">
                                        ${formatDate(e.updateDate)}
                                    </div>
                                    <div class="comment-bottom" style="display:none;">
                                        <button type="button" class="modify-cancel">취소</button>
                                        <button type="button" class="modify-confirm" data-reply-id="${e.id}">수정완료</button>
                                    </div>
                                </div>
                            </li>
                        </ul> `
        ;
        $('.comment-list').append(text);
    });
    console.log("=============page append 후===============")
    console.log(globalThis.page);
    console.log("=============count append 후===============")
    console.log(globalThis.count);
    globalThis.page = globalThis.page == globalThis.count ? globalThis.page : globalThis.page + 1;
    console.log("result: " + globalThis.page);
}

$(document).on('click', '.modify-cancel', function() {
    var index = $('.modify-cancel').index(this);
    $($(".modify-textarea")[index]).hide(); // 수정영역
    $('.comment-util-list').hide(); // 수정,삭제 모달 숨기기
    $('.comment-util').attr("disabled", false);
    $($(".comment-content")[index]).css("display", "block"); // 기존영역 숨기기
    $($(".comment-date")[index]).css("display", "block"); // 날짜 숨기기
    $($(".comment-bottom")[index]).css("display", "none"); // 취소,수정완료 버튼
});