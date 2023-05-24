/* mypage-follow.js */


globalThis.page = 0;
let followService = (() => {

    function getFollowers(callback) {
        // 팔로워 페이지 페이지 수 전달
        $.ajax({
            url: `/follows/list/followers`,
            data: {"page": globalThis.page},
            type: 'post',
            success: function (results) {
                console.log("ajax== getFollowers success");
                callback(results);
                attachFollowingButtonEvent();
            }
        });
    }

    function getFollowings(callback) {
        $.ajax({
            url: `/follows/list/followings`,
            data: {"page": globalThis.page},
            type: 'post',
            success: function (results) {
                console.log("ajax== getFollowings success");
                callback(results);
                attachFollowingButtonEvent();
            }
        });
    }

    function isFollowed(memberEmail) {
        $.ajax({
            url: `/follows/isFollowed`,
            data: {"memberEmail": memberEmail},
            type: 'post',
            success: function (results) {
                console.log("ajax== isFollowed success");
                return results;
                attachFollowingButtonEvent();
            }
        });
    }

    function countFollowers(memberEmail) {
        return new Promise(function (resolve, reject) {
            $.ajax({
                url: `/follows/countFollowers`,
                data: {"memberEmail": memberEmail},
                type: 'post',
                success: function (results) {
                    resolve(results);
                },
                error: function (error) {
                    reject(error);
                }
            });
        });
    }

    return {
        getFollowers: getFollowers,
        getFollowings: getFollowings,
        isFollowed: isFollowed,
        countFollowers: countFollowers
    }

})();

// function appendFollowersList(results) {
//     let text = '';
//     results.content.forEach(result => {
//         console.log("result");
//         console.log(result);
//         let count = followService.countFollowers(result.memberEmail);
//         console.log("count")
//         console.log(count)
//         let isFollowedByMe = followService.isFollowed(result.memberEmail);
//         text += `
//                                  <div class="one-content-wrapper">
//                   <div class="one-content">
//                     <a href="#">
//                       <div class="profile-area">
//                         <div class="profile">
//                           <img
//                             src="/members/display?fileName=Member/Profile/${result.memberProfilePath}/${result.memberProfileUUID}_${result.memberProfileOriginalName}"
//                             alt=""
//                           />
//                         </div>
//                       </div>
//
//                       <div class="profile-name-area">
//                         <p class="name">${result.memberNickname}</p>
//                         <p class="follower-count">팔로우 하는 사람 <span>${count}</span></p>
//                       </div>
//                     </a>`
//         if (isFollowedByMe == true) {
//             text += `<div class="following-btn-area">
//                       <button class="following-btn btn">
//                         <span>
//                           <svg
//                             viewBox="0 0 48 48"
//                             focusable="false"
//                             role="presentation"
//                             class="withIcon_icon__2KxnX Button_icon__16grv FollowingButton_icon__25M12"
//                             aria-hidden="true"
//                             style="width: 14px; height: 14px"
//                           >
//                             <path
//                               d="M18 39.6L4.8 26.4l3.36-3.36L18 32.76l21.84-21.72 3.36 3.36z"
//                             ></path>
//                           </svg>
//                           <span class="btn-text">팔로잉</span>
//                         </span>
//                       </button>
//                     </div>
//                   </div>
//                   <div class="line"></div>
//                 </div>`
//         } else {
//             text += `<div class="following-btn-area">
//                     <button
//                         class="following-btn-not btn"
//                         type="button"
//                       >
//                         <span
//                           ><svg
//                             viewBox="0 0 32 32"
//                             focusable="false"
//                             role="presentation"
//                             class="withIcon_icon__2KxnX Button_icon__16grv FollowingButton_icon__25M12"
//                             aria-hidden="true"
//                             style="width: 14px; height: 14px;padding-top: 2px;padding-right: 4px;"
//                           >
//                             <path
//                               d="M30.4 15.2H16.8V1.6h-1.6v13.6H1.6v1.6h13.6v13.6h1.6V16.8h13.6v-1.6z"
//                             ></path></svg
//                           ><span class="Button_children__raEW4">팔로우</span></span
//                         >
//                       </button>
//                    </div>
//                   </div>
//                   <div class="line"></div>
//                 </div>`
//         }
//     });
//     $('.contents-lists').html(text);
// }

function appendFollowersList(results) {
    let text = '';
    let promises = [];

    results.content.forEach(result => {
        let isFollowedByMe = followService.isFollowed(result.memberEmail);
        let countPromise = followService.countFollowers(result.memberEmail);
        promises.push(countPromise);

        countPromise.then(count => {
            console.log("count");
            console.log(count);
            text += `
            <div class="one-content-wrapper">
                <div class="one-content">
                <a href="#">
                <div class="profile-area">
                <div class="profile">
                <img
            src="/members/display?fileName=Member/Profile/${result.memberProfilePath}/${result.memberProfileUUID}_${result.memberProfileOriginalName}"
            alt=""
                />
                </div>
                </div>

                <div class="profile-name-area">
                <p class="name">${result.memberNickname}</p>
                <p class="follower-count">팔로우 하는 사람 <span>${count}</span></p>
            </div>
            </a>`
            if (isFollowedByMe == true) {
                text += `<div class="following-btn-area">
                      <button class="following-btn btn">
                        <span>
                          <svg
                            viewBox="0 0 48 48"
                            focusable="false"
                            role="presentation"
                            class="withIcon_icon__2KxnX Button_icon__16grv FollowingButton_icon__25M12"
                            aria-hidden="true"
                            style="width: 14px; height: 14px"
                          >
                            <path
                              d="M18 39.6L4.8 26.4l3.36-3.36L18 32.76l21.84-21.72 3.36 3.36z"
                            ></path>
                          </svg>
                          <span class="btn-text">팔로잉</span>
                        </span>
                      </button>
                    </div>
                  </div>
                  <div class="line"></div>
                </div>`
            } else {
                text += `<div class="following-btn-area">
                    <button
                        class="following-btn-not btn"
                        type="button"
                      >
                        <span
                          ><svg
                            viewBox="0 0 32 32"
                            focusable="false"
                            role="presentation"
                            class="withIcon_icon__2KxnX Button_icon__16grv FollowingButton_icon__25M12"
                            aria-hidden="true"
                            style="width: 14px; height: 14px;padding-top: 2px;padding-right: 4px;"
                          >
                            <path
                              d="M30.4 15.2H16.8V1.6h-1.6v13.6H1.6v1.6h13.6v13.6h1.6V16.8h13.6v-1.6z"
                            ></path></svg
                          ><span class="Button_children__raEW4">팔로우</span></span
                        >
                      </button>
                   </div>
                  </div>
                  <div class="line"></div>
                </div>`
            }
        });
    });

    // 모든 Promise가 완료될 때까지 기다린 후에 결과를 처리합니다.
    Promise.all(promises).then(() => {
        $('.contents-lists').html(text);
    });
}

// 페이지 로딩 시 초기 리스트를 불러옴
followService.getFollowers(
    function (results) {
        if (results.content.length === 0) {
            $('.btn-place').hide(); // 더 이상 불러올 페이지가 없는 경우 "더 보기" 버튼 숨김
        } else {
            appendFollowersList(results);
        }

        // 더 보기 버튼 및 comment-open 클래스를 클릭 시 동작
        $('.btn-comment, .comment-open').on('click', function() {
            globalThis.page++; // 페이지 증가
            appendFollowersList(results);
        });
        // appendFollowersList(results);
    });






function loadFollowers() {
    followService.getFollowers(function (results) {
        if (results.content.length === 0) {
            $('.btn-place').hide();
        } else {
            appendFollowersList(results);
        }

        $('.btn-comment, .comment-open').on('click', function () {
            globalThis.page++;
            followService.getFollowers(function (results) {
                appendFollowersList(results);
            });
        });
    });
}

loadFollowers();

// 클릭 이벤트 부분을 함수로 분리하여 재사용할 수 있도록 수정합니다.
$(document).on('click', '.following-btn', function() {
    console.log("애있음?")
    if ($(this).hasClass('following-btn-not')) {
        $(this).addClass('following-btn').removeClass('following-btn-not');
        $(this).css('color', 'black');
        $(this).css('border', '1px #cdd3d8 solid');
        $(this).children().children().attr('viewBox', '0 0 48 48');
        $(this).children().children().children().attr('d', 'M18 39.6L4.8 26.4l3.36-3.36L18 32.76l21.84-21.72 3.36 3.36z');
        $(this).children().children().children().attr('fill', 'black');
    } else {
        $(this).addClass('following-btn-not').removeClass('following-btn');
        $(this).css('color', 'red');
        $(this).css('border', '1px red solid');
        $(this).children().children().attr('viewBox', '0 0 32 32');
        $(this).children().children().children().attr('d', 'M30.4 15.2H16.8V1.6h-1.6v13.6H1.6v1.6h13.6v13.6h1.6V16.8h13.6v-1.6z');
        $(this).children().children().children().attr('fill', 'red');
    }
});