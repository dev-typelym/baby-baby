/* 좋아요 */
$(function () {
    $('.wish-button').click(function (e) {
        var target = $(e.target);
        if ($(target).attr('aria-pressed') == 'false') {
            $(target).attr('aria-pressed', 'true'); //하트 색 채우기
            $('.like-cancel-text').hide(); //해제 문구
            $('#like-modal').css({ right: '-30%' }); //오->왼 슬라이드 등장
            $('#like-modal').animate({ right: '30px' }, { duration: 500 }); //오->왼 슬라이드 등장
            $('#like-modal').show(); //슬라이드 보이기
            $('#like-modal').css({'display': 'flex'}); 
            $('#like-modal').css({'align-items': 'center'});
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
/* 좋아요 끝 */

const $banner = $(".banner");
const width = 344;

var bannerImage1 = [
    "../../../static/images/nowKids/001.jpg",
    "../../../static/images/nowKids/002.jpg",
    "../../../static/images/nowKids/003.jpg",
    "../../../static/images/nowKids/004.jpg",
    "../../../static/images/nowKids/005.jpg",
];

var bannerImage2 = [
    "https://cdn.class101.net/images/d125bc77-a03a-41fb-9ea6-b04db24f3aff/2048xauto.webp",
    "../../../static/images/nowKids/002.jpg",
    "../../../static/images/nowKids/003.jpg",
    "../../../static/images/nowKids/004.jpg",
    "../../../static/images/nowKids/005.jpg",
    "https://cdn.class101.net/images/1efd9a4c-849e-4dfb-95aa-7485860043d6/2048xauto.webp",
];

let imageList = [bannerImage1,bannerImage2];

const $imageWrap = $(".bottom-content-full-flex");

imageList.forEach((e, i) => {

    let text = `
                <div class="bottom-content-flex" id ="${i}">
                    <section class="feed-header-full-wrap">
                        <div class="modify-delete">
                            <button type="button">수정하기</button>
                            <button type="button">삭제하기</button>
                        </div>
                        <div class="feed-header-inner-flex">
                            <div class="feed-header-left-flex">
                                <a class="feed-header-user-img-wrap">
                                    <span class="feed-header-user-img">
                                    <span class="feed-header-new-img">
                                    </span>
                                </a>
                                <div class="feed-header-new-board">
                                    <em class="feed-header-user-name">
                                        김동한
                                    </em>
                                </div>
                            </div>
                            <span class="feed-header-time">3시간 전</span>
                        </div>
                    </section>
                    <div class=title-like-wrap>
                        <button type="button" class="wish-button" aria-pressed="false">
                            <svg viewBox="0 0 32 32" focusable="false" role="presentation" class="wish-button-svg" aria-hidden="true">
                                <path d="M22.16 4h-.007a8.142 8.142 0 0 0-6.145 2.79A8.198 8.198 0 0 0 9.76 3.998a7.36 7.36 0 0 0-7.359 7.446c0 5.116 4.64 9.276 11.6 15.596l2 1.76 2-1.76c6.96-6.32 11.6-10.48 11.6-15.6v-.08A7.36 7.36 0 0 0 22.241 4h-.085z"></path>
                            </svg>
                        </button>
                        <span class="bottom-event-title">뚝딱이가 왔어요 [체험학습 제목]</span>
                    </div>

                    <div class="board-component-image-wrapper">
                        <section class="banner-container">
                            <!-- 메인 배너 -->
                            <div class="banner" id="banner${i}">
                            /* 이미지가 없으면 이 이미지아래로 이미지 태그 전부를 if로 감싸야한다. */
                            <div>
                                <img src="${e[e.length-1]}">
                            </div>
    
    `;

    e.forEach((image, j) => {
        let totalWidth = width * (image.length + 2);
        $($banner[j]).css("width",`${totalWidth}px`);
        text += `
                    <div>
                        <img src="${image}">
                    </div>
        `;
    });

    text += `

                <div>
                    <img src="${e[0]}">
                </div>
            </div>
            <div class="move-arrow">
                <!-- 이전 버튼 -->
                <div class="prev" id="prev${i}">
                    <img data-role="none" class="prev-image" src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='50' height='50' viewBox='0 0 50 50'%3E %3Cpath fill='none' fill-rule='evenodd' stroke='%23FFF' stroke-width='2' d='M21 17l8 8.014L21.028 33'/%3E %3C/svg%3E" style="display: block;">
                </div>
                <!-- 다음 버튼 -->
                <div class="next" id="next${i}">
                    <img data-role="none" class="prev-image" src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='50' height='50' viewBox='0 0 50 50'%3E %3Cpath fill='none' fill-rule='evenodd' stroke='%23FFF' stroke-width='2' d='M21 17l8 8.014L21.028 33'/%3E %3C/svg%3E" style="display: block;">
                </div>
            </div>
        </section>
        </div>
        <section class="bottom-full-wrap">
            <section class="bottom-event-title-wrap">
            참여인원 보기
                <button type="button" id="table${i}" class="table-button">
                    <span>
                        <svg viewBox="0 0 32 32" focusable="false" role="presentation" class="nav-arrow-icon" aria-hidden="true" style="width: 20px; height: 20px; margin-top: 3px; margin-left: 5px;">
                            <path d="M16 22.4L5.6 12l1.12-1.12L16 20.16l9.28-9.28L26.4 12 16 22.4z"></path>
                        </svg>
                    </span>
                </button>
            </section>
            <div class="table-display">
            <div class="table-kids-count">홍길동<span>&nbsp;외&nbsp;</span><span>4</span>명</div>
                <table class="table-box">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>닉네임</th>
                            <th>이름</th>
                            <th>나이</th>                                
                            <th>통솔자 이름</th>                                
                            <th>참여일자</th>    
                        </tr>
                    </thead>
                    <tbody>
                        <!-- 하나의 데이터 -->
                        <tr>  
                            <td>1</td>
                            <td>뚝딱이</td>
                            <td>임종욱</td>
                            <td>27</td>
                            <td>뚝딱이</td>
                            <td>2023.04.27</td>  
                        </tr>   
                    </tbody>
                </table>
            </div>
        </section>
        <div class="bottom-space"></div>
        </div>
    `;
    $imageWrap.append(text);
});

let checkArrow = false;

$imageWrap.on("click","div.prev", (e) => {
    if(checkArrow){return;}
    checkArrow = true;

    let i = e.currentTarget.id.replace("prev","");
    let bannerId = '#banner' + i;
    let $banner = $(bannerId);
    let currentPos = parseInt($banner.css("transform").split(",")[4]);

    $banner.css("transition","transform 0.3s");
    $banner.css("transform",`translate(${currentPos + width}px)`);

    if (currentPos == -width) {
        setTimeout(function(){
            $banner.css("transition","transform 0s");
            $banner.css("transform",`translate(-${width * ($banner.children().length - 2)}px)`);
        }, 300);
    }   
    setTimeout(()=>{checkArrow = false}, 300);
});

$imageWrap.on("click","div.next", (e) => {
    if(checkArrow){return;}
    checkArrow = true;

    let i = e.currentTarget.id.replace("next","");
    let bannerId = '#banner' + i;
    let $banner = $(bannerId);
    let currentPos = parseInt($banner.css("transform").split(",")[4]);
    
    $banner.css("transition","transform 0.3s");
    $banner.css("transform",`translate(${currentPos - width}px)`);

    if(currentPos == -width * ($banner.children().length-2)){
        setTimeout(function(){
            $banner.css("transition","transform 0s");
            $banner.css("transform",`translate(${-width}px)`);
        }, 300);
    }
    setTimeout(()=>{checkArrow = false}, 300);
});

$imageWrap.on("click","button.table-button",(e) => {
    let idx = e.currentTarget.id.replace("table","");
    let $arrowButton = $($(".nav-arrow-icon")[idx]);

    if(!$arrowButton.hasClass("active-arrow")){
        $arrowButton.addClass("active-arrow");
        $($(".table-display")[idx]).slideDown();
    }else {
        $arrowButton.removeClass("active-arrow");
        $($(".table-display")[idx]).slideUp();
    }
    
});

  

