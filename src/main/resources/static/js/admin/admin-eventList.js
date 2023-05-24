/* 상세보기 모달 ===================== */

function openModal(num, e) {
    var selectedModal = '#event-detail' + num;
    if (!$(e.target).is('input[type="checkbox"]') && !$(e.target).hasClass('no-modal')) {
        $(selectedModal).show();
    }
    console.log(num+"번 모달 클릭")
}


/* 상세 모달 닫기 */
function closeModal(num) {
    var selectedModal = '#event-detail' + num;
    $(selectedModal).hide();
}


/* 삭제 모달 ======================== */
const $showDelete = $(".delete-button");
const $cancelDelete = $(".cancel-delete");

// "전체선택" 체크박스 클릭 이벤트
$('#allSelect').click(function() {
    if ($(this).is(':checked')) {
        // 전체선택 체크박스가 체크되었을 때 개별 체크박스 모두 체크
        $('input[name="check"]').prop('checked', true);
    } else {
        // 전체선택 체크박스가 체크해제되었을 때 개별 체크박스 모두 체크해제
        $('input[name="check"]').prop('checked', false);
    }
});

// 삭제 버튼 클릭 이벤트
$(".delete-button").click(function() {
    if ($("input[type=checkbox]:checked").length === 0) {
        // 선택된 체크박스가 없으면 모달 띄우기
        $(".delete-no-check-modal").show();
    } else {
        // 선택된 체크박스가 있으면 모달 띄우기
        $(".delete-modal").show();
    }
});

// 모달 확인 버튼 클릭 시 모달 닫기
$(".confirm-delete, .no-check-confirm-delete").click(function() {
    $(".delete-no-check-modal, .delete-modal").hide();
});

// 모달 취소 버튼 클릭 시 모달 닫기
$(".cancel-delete").click(function() {
    $(".delete-modal").hide();
    $('input[name="check"]').prop('checked', false);
});
// /* 상세보기 버튼 js */
// const detailBtnBodyList = document.querySelectorAll('.row');
// const detailBtnList = document.querySelectorAll('.answer');

// detailBtnBodyList.forEach(function(detailBtnBody, index) {
//   detailBtnBody.addEventListener('mouseover', function() {
//     detailBtnList[index].style.background = "#828282";
//     detailBtnList[index].style.color = "#fff";
//     detailBtnBodyList[index].style.background = "#828282";
//     detailBtnBodyList[index].style.color = "#fff";
//   });

//   detailBtnBody.addEventListener('mouseout', function() {
//     detailBtnList[index].style.background = "#fff";
//     detailBtnList[index].style.color = "#000000";
//     detailBtnBodyList[index].style.background = "#fff";
//     detailBtnBodyList[index].style.color = "#000000";
//   });
// });

// detailBtnList.forEach(function(detailBtn, index) {
//   detailBtn.addEventListener('mouseover', function() {
//     detailBtnList[index].style.background = "#828282";
//     detailBtnList[index].style.color = "#fff";
//     detailBtnBodyList[index].style.background = "#828282";
//     detailBtnBodyList[index].style.color = "#fff";
//   });

//   detailBtn.addEventListener('mouseout', function() {
//     detailBtnList[index].style.background = "#fff";
//     detailBtnList[index].style.color = "#000000";
//     detailBtnBodyList[index].style.background = "#fff";
//     detailBtnBodyList[index].style.color = "#000000";
//   });
// });


//-----------------------------------------ajax--------------------------------------------------

const PAGE_AMOUNT = 10;
const $itemWrap = $(".show-item-wrap");
const SEARCH_URL = "/parentsYard/list/show";
const $pageWrap = $(".page-button-box");
const $contentWrap = $(".event-board-list");
const $detailContentWrap = $(".event-modal-list");
const adminEventSearch = {
    eventTitle: null,
    categoryType : null,
    eventStatus : '전체'
};

// 이벤트 카테고리
$('.category-entire').click(function() {
    adminEventSearch.categoryType = null;
    adminEventSearch.eventStatus = '전체';
    adminEventSearch.eventTitle = null;

    getAdminEventBoardList();

    $('.category-entire').css('color', '#c97793')
    $('.category-country-side').css('color', 'black');
    $('.category-gallery-art').css('color', 'black');
    $('.category-tradition').css('color', '#black');
    $('.category-craft').css('color', 'black');
    $('.category-science').css('color', 'black');
    $('.category-museum').css('color', 'black');
    $('.category-sports').css('color', 'black');
    $('.category-etc').css('color', 'black');

    $('.hold').css('color', 'gray');
    $('.process').css('color', 'gray');
    $('.end').css('color', 'gray');
});

$('.category-country-side').click(function() {
    adminEventSearch.categoryType = 'AGRICULTURE';
    getAdminEventBoardList();

    $('.category-entire').css('color', 'black')
    $('.category-country-side').css('color', '#c97793');
    $('.category-gallery-art').css('color', 'black');
    $('.category-tradition').css('color', 'black');
    $('.category-craft').css('color', 'black');
    $('.category-science').css('color', 'black');
    $('.category-museum').css('color', 'black');
    $('.category-sports').css('color', 'black');
    $('.category-etc').css('color', 'black');
});

$('.category-gallery-art').click(function() {
    adminEventSearch.categoryType = 'ART';
    getAdminEventBoardList();

    $('.category-entire').css('color', 'black')
    $('.category-country-side').css('color', 'black');
    $('.category-gallery-art').css('color', '#c97793');
    $('.category-tradition').css('color', 'black');
    $('.category-craft').css('color', 'black');
    $('.category-science').css('color', 'black');
    $('.category-museum').css('color', 'black');
    $('.category-sports').css('color', 'black');
    $('.category-etc').css('color', 'black');
});

$('.category-tradition').click(function() {
    adminEventSearch.categoryType = 'TRADITION';
    getAdminEventBoardList();

    $('.category-entire').css('color', 'black')
    $('.category-country-side').css('color', 'black');
    $('.category-gallery-art').css('color', 'black');
    $('.category-tradition').css('color', '#c97793');
    $('.category-craft').css('color', 'black');
    $('.category-science').css('color', 'black');
    $('.category-museum').css('color', 'black');
    $('.category-sports').css('color', 'black');
    $('.category-etc').css('color', 'black');
});

$('.category-craft').click(function() {
    adminEventSearch.categoryType = 'CRAFT';
    getAdminEventBoardList();

    $('.category-entire').css('color', 'black')
    $('.category-country-side').css('color', 'black');
    $('.category-gallery-art').css('color', 'black');
    $('.category-tradition').css('color', 'black');
    $('.category-craft').css('color', '#c97793');
    $('.category-science').css('color', 'black');
    $('.category-museum').css('color', 'black');
    $('.category-sports').css('color', 'black');
    $('.category-etc').css('color', 'black');
});

$('.category-science').click(function() {
    adminEventSearch.categoryType = 'SCIENCE';
    getAdminEventBoardList();

    $('.category-entire').css('color', 'black')
    $('.category-country-side').css('color', 'black');
    $('.category-gallery-art').css('color', 'black');
    $('.category-tradition').css('color', 'black');
    $('.category-craft').css('color', 'black');
    $('.category-science').css('color', '#c97793');
    $('.category-museum').css('color', 'black');
    $('.category-sports').css('color', 'black');
    $('.category-etc').css('color', 'black');
});

$('.category-museum').click(function() {
    adminEventSearch.categoryType = 'MUSEUM';
    getAdminEventBoardList();

    $('.category-entire').css('color', 'black')
    $('.category-country-side').css('color', 'black');
    $('.category-gallery-art').css('color', 'black');
    $('.category-tradition').css('color', 'black');
    $('.category-craft').css('color', 'black');
    $('.category-science').css('color', 'black');
    $('.category-museum').css('color', '#c97793');
    $('.category-sports').css('color', 'black');
    $('.category-etc').css('color', 'black');
});

$('.category-sports').click(function() {
    adminEventSearch.categoryType = 'SPORTS';
    getAdminEventBoardList();

    $('.category-entire').css('color', 'black')
    $('.category-country-side').css('color', 'black');
    $('.category-gallery-art').css('color', 'black');
    $('.category-tradition').css('color', 'black');
    $('.category-craft').css('color', 'black');
    $('.category-science').css('color', 'black');
    $('.category-museum').css('color', 'black');
    $('.category-sports').css('color', '#c97793');
    $('.category-etc').css('color', 'black');
});

$('.category-etc').click(function() {
    adminEventSearch.categoryType = 'ETC';
    getAdminEventBoardList();

    $('.category-entire').css('color', 'black')
    $('.category-country-side').css('color', 'black');
    $('.category-gallery-art').css('color', 'black');
    $('.category-tradition').css('color', 'black');
    $('.category-craft').css('color', 'black');
    $('.category-science').css('color', 'black');
    $('.category-museum').css('color', 'black');
    $('.category-sports').css('color', 'black');
    $('.category-etc').css('color', '#c97793');
});

// 이벤트 진행상황
// $('.all').click(function() {
//     adminEventSearch.eventStatus = '전체';
//     getAdminEventBoardList();
// });

$('.hold').click(function() {
    adminEventSearch.eventStatus = '대기';
    getAdminEventBoardList();

    $('.hold').css('color', 'red');
    $('.process').css('color', 'gray');
    $('.end').css('color', 'gray');
});

$('.process').click(function() {
    adminEventSearch.eventStatus = '진행중';
    getAdminEventBoardList();

    $('.hold').css('color', 'gray');
    $('.process').css('color', 'green');
    $('.end').css('color', 'gray');
});

$('.end').click(function() {
    adminEventSearch.eventStatus = '종료';
    getAdminEventBoardList();

    $('.hold').css('color', 'gray');
    $('.process').css('color', 'gray');
    $('.end').css('color', 'black');
});





function getAdminEventBoardList() {
    $.ajax({
        url: `eventList/${globalThis.page}`,
        data: adminEventSearch,
        success: function(data) {
            $pageWrap.empty();
            showPage(data);
            $contentWrap.empty();
            showList(data.content);
        }

    })
}

globalThis.page = 1;



function findPage(page) {
    globalThis.page = page;
    getAdminEventBoardList();
}

//검색
$(".search-btn-icon").on("click", function (e) {
    e.preventDefault();
    let val;
    let $search = $(".search-input");

    val = $search.val();

    if ($search.val() === ""){
        val = null
    };

    adminEventSearch.eventTitle = val;
    adminEventSearch.eventStatus = '전체';
    adminEventSearch.categoryType = null;

    console.log( adminEventSearch.eventTitle + "777");
    getAdminEventBoardList();
    $(".search-input").val("");
});

function showPage(data) {
    let pageable = data.pageable;
    let pageNumber = pageable.pageNumber;
    let totalPages = data.totalPages;
    let count = Math.floor(pageNumber/PAGE_AMOUNT);

    let startPage = count * PAGE_AMOUNT;
    let endPage = startPage + PAGE_AMOUNT;

    endPage = endPage > data.totalPages ? data.totalPages : endPage;

    let hasPrev = startPage > 1;
    // 170 page / 5 = 24 -> 171 /
    let hasNext = endPage < data.totalPages;

    let text = "";


    // Previous button
    if (hasPrev) {
        text += `<div class="">`;
        text += `<div class="page-button-margin">`;
        text += `<div>`;
        text += `<img src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_keyboard_arrow_left_48px-128.png" class="left-button" onclick="findPage(${startPage})" data-page = "${pageNumber}" aria-label="이전 목록">`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
    }

    // Page buttons
    for (let i = startPage + 1; i < endPage + 1; i++) {
        let page = i;
        if (pageNumber  + 1 == page) {
            text += `<div class="page-button-active page-button" onclick="findPage(${i})">`;
        } else {
            text += `<div class="page-button" onclick="findPage(${i})">`;
        }
        text += `<div class="page-button-margin">`;
        text += `<div>`;
        text += `<span>${i}</span>`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
    }

    // Next button
    if (hasNext) {
        text += `<div class="">`;
        text += `<div class="page-button-margin">`;
        text += `<div>`;
        text += `<img src="https://cdn3.iconfinder.com/data/icons/google-material-design-icons/48/ic_keyboard_arrow_right_48px-128.png" class="right-button" onclick="findPage(${endPage+1})" data-page="' + (pageNumber + 1) + '" aria-label="다음 목록">`;
        text += `</div>`;
        text += `</div>`;
        text += `</div>`;
    }

    $pageWrap.html(text);
}


//   이벤트 목록
function showList(eventBoardDTOS) {
    var content = "";
    var detailContent = "";
    eventBoardDTOS.forEach(eventBoard => {

        const formattedDate = formatDate(new Date(eventBoard.writeDate));
        const formattedEventStartDate = formatDate(new Date(eventBoard.startDate));
        const formattedEventEndDate = formatDate(new Date(eventBoard.endDate));
        let changeCategoryName = null;
        let now = new Date();
        let changeEventStatus = null;
        let eventStatusColor = null;
        let  fullAddress = eventBoard.eventAddress + ' ' + eventBoard.eventAddressDetail + ' ' + eventBoard.memberAddressSubDetail;

        if (eventBoard.category === null) {
            changeCategoryName = '전체';
        } else if (eventBoard.category === 'AGRICULTURE'){
            changeCategoryName = '농촌';
        } else if (eventBoard.category === 'ART'){
            changeCategoryName = '예술';
        } else if (eventBoard.category === 'TRADITION'){
            changeCategoryName = '전통';
        } else if (eventBoard.category === 'CRAFT'){
            changeCategoryName = '공방';
        } else if (eventBoard.category === 'SCIENCE'){
            changeCategoryName = '과학';
        } else if (eventBoard.category === 'MUSEUM'){
            changeCategoryName = '박물관';
        } else if (eventBoard.category === 'SPORTS') {
            changeCategoryName = '스포츠';
        } else{
            changeCategoryName = '기타';
        }

        const dateStartTimeString = eventBoard.startDate.toString();
        const dateEndTimeString = eventBoard.endDate.toString();

        const jsStartDate = new Date(dateStartTimeString);
        const jsEndDate = new Date(dateEndTimeString);

        if (jsStartDate > now) {
            changeEventStatus = '대기';
            eventStatusColor = 'red';
        } else if(jsStartDate <= now  && jsEndDate >= now ){
            changeEventStatus = '진행중';
            eventStatusColor = 'green';
        } else{
            changeEventStatus = '종료';
            eventStatusColor = 'black';
        }

        content +=
            `
               <tr class="row" onclick="openModal(${eventBoard.id}, event)">
                    <td class="no-modal">
                        <input type="checkbox" name="check">
                    </td>
                    <td class="event-num event-id">${eventBoard.id}</td>
                    <td class="event-company">${eventBoard.memberName}</td>
                    <td class="event-category">${changeCategoryName}</td>
                    <td class="event-location">${eventBoard.boardTitle}</td>
                    <td class="event-location">${fullAddress}</td>
                    <td class="event-period"><span>${formattedEventStartDate}</span><span> ~ </span><span>${formattedEventEndDate}</span></td>
                    <td class="event-crewCount">${eventBoard.eventRecruitCount}<span>명</span></td>
                    <td class="event-status hold selected" style="color: ${eventStatusColor}">${changeEventStatus}</td>
                </tr>
            `

        detailContent +=
            `
               <section class="user-modal" id="event-detail${eventBoard.id}">
			<div class="user-modal-shape">
				<div class="modal-header">
					<h4>게시글 상세보기</h4>
					<a class="modal-close">
						<svg
							xmlns="http://www.w3.org/2000/svg"
							data-name="Capa 1"
							id="Capa_1"
							viewBox="0 0 20 19.84"
							onclick="closeModal(${eventBoard.id})"
						>
							<path d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z"/>
						</svg>
					</a>
				</div>
				<form class="user-info" action>
					<main class="user-detail">
						<h5 class="detail-title">게시글 상세정보</h5>
						<div class="profile-img-wrapper">
							<label>
								<div class="content-img">
									<img src="/eventFiles/display?fileName=Event/${eventBoard.eventFileDTOS[0].filePath}/${eventBoard.eventFileDTOS[0].fileUUID}_${eventBoard.eventFileDTOS[0].fileOriginalName}"
									/>
								</div>
							</label>
						</div>
						<ul class="content-list-wrap">
							<li class="content-list">
								<span>기업</span>
								<div class="content-input">
									<p>${eventBoard.memberName}</p>
								</div>
							</li>
							<li class="content-list">
								<span>분야</span>
								<div class="content-input">
									<p>${changeCategoryName}</p>
								</div>
							</li>
							<li class="content-list">
								<span>행사명</span>
								<div class="content-input">
									<p>${eventBoard.boardTitle}</p>
								</div>
							</li>
							<li class="content-list">
								<span>위치</span>
								<div class="content-input">
									<p>${eventBoard.eventLocation}</p>
								</div>
							</li>
							<li class="content-list">
								<span>기간</span>
								<div class="content-input content-div">
									<p><span>${formattedEventStartDate}</span><span> ~ </span><span>${formattedEventEndDate}</span></p>
								</div>
							</li>
							<li class="content-list">
								<span>모집인원</span>
								<div class="content-input content-div">
									<p>${eventBoard.eventRecruitCount}</textarea>
								</div>
							</li>
						</ul>
					</main>
				</form>
			</div>
		</section>
            `
    });
    $contentWrap.append(content);
    $detailContentWrap.append(detailContent);

}

getAdminEventBoardList();


// 선택된 항목 삭제하기
$('.confirm-delete').on('click', function () {
    var checkedIds = new Array();
    // 체크 박스 체크된 값
    $('input:checkbox[name=check]:checked').closest('tr').find('.event-id').each(function () {
        console.log(this.innerText);
        checkedIds.push(this.innerText);
    });

    // $('input:checkbox[name=check]:checked').closest('tr').find('.reply-id').each(function () {
    //     var id = $(this).text();
    //     checkedIds.push(parseInt(id, 10));
    // });
    console.log(checkedIds);
    console.log(typeof checkedIds[0]);
    $.ajax({
        url: "event/delete",
        type: "delete",
        data: {
            "checkedIds": checkedIds,
        },
        success: function () {
            findPage(page);
        }
    });

    $('input:checkbox[id=allSelect]:checked').prop('checked', false);
});
