/* mypage-follow.js */

  $('.following-btn').click(function() {
    if($(this).hasClass('.following-btn-not')){
        $(this).addClass('.following-btn').removeClass('.following-btn-not')
        $(this).css('color', 'black')
        $(this).css('border', '1px #cdd3d8 solid')
        $(this).children().children().attr('viewBox', '0 0 48 48')
        $(this).children().children().children().attr('d', 'M18 39.6L4.8 26.4l3.36-3.36L18 32.76l21.84-21.72 3.36 3.36z')
        $(this).children().children().children().attr('fill', 'black')
    } else{
        $(this).addClass('.following-btn-not').removeClass('.following-btn')
        $(this).css('color', 'red')
        $(this).css('border', '1px red solid')
        $(this).children().children().attr('viewBox', '0 0 32 32')
        $(this).children().children().children().attr('d', 'M30.4 15.2H16.8V1.6h-1.6v13.6H1.6v1.6h13.6v13.6h1.6V16.8h13.6v-1.6z')
        $(this).children().children().children().attr('fill', 'red')
    }
  });

  globalThis.page = 0;
let followService =(() =>{

    function getFollowers(callback) {
        $.ajax({
            url: `/follows/list/followers`,
            data: {"page" : globalThis.page},
            type: 'post',
            success: function (results) {
                console.log("ajax== getFollowers success");
                callback(results);
            }
        });
    }

    function getFollowings(callback) {
        $.ajax({
            url: `/follows/list/followings`,
            data: {"page" : globalThis.page},
            type: 'post',
            success: function (results) {
                console.log("ajax== getFollowings success");
                callback(results);
            }
        });
    }

    function isFollowed(memberId) {
        $.ajax({
            url: `/follows/isFollowed`,
            data: {"memberId": memberId},
            type: 'post',
            success: function (results) {
                console.log("ajax== isFollowed success");
                return results;
            }
        });
    }

    function countFollowers(memberId) {
        $.ajax({
            url: `/follows/countFollowers`,
            data: {"memberId": memberId},
            type: 'post',
            success: function (results) {
                return results;
            }
        });
    }

    return {getFollowers : getFollowers , getFollowings : getFollowings, isFollowed : isFollowed, countFollowers : countFollowers}

})();

function appendFollowersList(results){
    let text = '';
    results.forEach(result => {
        let count = followService.countFollowers(result.memberId);
        let isFollowedByMe = followService.isFollowed(result.memberId);
        text += ` 
                                 <div class="one-content-wrapper">
                  <div class="one-content">
                    <a href="#">
                      <div class="profile-area">
                        <div class="profile">
                          <img
                            src="https://cdn.wadiz.kr/ft/images/maker/2023/0202/20230202172350131_null.png/wadiz/resize/150/format/jpg/quality/90/"
                            alt=""
                          />
                        </div>
                      </div>

                      <div class="profile-name-area">
                        <p class="name">이름</p>
                        <p class="follower-count">팔로우 하는 사람 <span>${count}</span></p>
                      </div>
                    </a>`
            if(isFollowedByMe){
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
            }else{
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
    $('.contents-lists').html(text);
}


// 페이지 로딩 시 초기 리스트를 불러옴
followService.getFollowers(
    function (results) {
        appendFollowersList(results);
    });