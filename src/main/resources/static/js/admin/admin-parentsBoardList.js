

/* 상세보기 모달 ===================== */

function openModal(num, e) {
    var selectedModal = '#parents-detail' + num;
    if (!$(e.target).is('input[type="checkbox"]') && !$(e.target).hasClass('no-modal')) {
        $(selectedModal).show();
    }
    console.log(num+"번 모달 클릭")
}


/* 상세 모달 닫기 */
function closeModal(num) {
    var selectedModal = '#parents-detail' + num;
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
});


// 수정하기 썸네일
$('.update-profile').change(function(event) {
    var input = event.target;
    var reader = new FileReader();
    reader.onload = function() {
        var dataURL = reader.result;
        var image = $('.img-updated');
        image.attr('src', dataURL);
    };
    reader.readAsDataURL(input.files[0]);
});

$('.maker_up_button').click(function() {
    var table = $(this).parents('tr').next('.crew-table');
    if (table.is(':visible')) {
        table.hide();
        $(this).removeClass('rotate');
    } else {
        table.show();
        $(this).addClass('rotate');
    }
});


//-----------------------------------------ajax--------------------------------------------------

const PAGE_AMOUNT = 10;
const $itemWrap = $(".show-item-wrap");
const SEARCH_URL = "/parentsYard/list/show";
const $pageWrap = $(".page-button-box");
const $contentWrap = $(".parents-board-list");
const $detailContentWrap = $(".parents-yard-modal-list");
const adminParentsBoardSearch = {
    parentsBoardTitle: null
};


function getAdminParentsBoardList() {
    $.ajax({
        url: `parentsYardList/${globalThis.page}`,
        data: adminParentsBoardSearch,
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
    getAdminParentsBoardList();
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

    adminParentsBoardSearch.parentsBoardTitle = val;

    console.log( adminParentsBoardSearch.parentsBoardTitle + "777");
    getAdminParentsBoardList();
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


//    부모님 마당 목록
function showList(parentsBoardDTOS) {
    var content = "";
    var detailContent = "";
    parentsBoardDTOS.forEach(parentsBoard => {

        const formattedDate = formatDate(new Date(parentsBoard.parentsBoardRegisterDate));
        const eventTitle = parentsBoard.eventTitle ? parentsBoard.eventTitle : "해당없음";
        content +=
            `
               <tr class="row" onclick="openModal(${parentsBoard.id}, event)">
                    <td class="no-modal">
                        <input type="checkbox" name="check">
                    </td>
                    <td class="parentsBoard-id">${parentsBoard.id}</td>
                    <td>${eventTitle}</td>
                    <td>${parentsBoard.parensBoardTitle}</td>
                    <td>${parentsBoard.memberNickName}</td>
                    <td>${formattedDate}</td>
                    <td>${parentsBoard.parentsBoardContent}</td>
                </tr>
            `

        detailContent +=
            `
                <section class="user-modal" id="parents-detail${parentsBoard.id}">
			<div class="user-modal-shape">
				<div class="modal-header">
					<h4>게시글 상세보기</h4>
					<a class="modal-close">
						<svg
							xmlns="http://www.w3.org/2000/svg"
							data-name="Capa 1"
							id="Capa_1"
							viewBox="0 0 20 19.84"
							onclick="closeModal(${parentsBoard.id})"
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
									${
                parentsBoard.parentsBoardFileDTOS && parentsBoard.parentsBoardFileDTOS.length > 0
                    ? parentsBoard.parentsBoardFileDTOS.map(fileDTO => `<img src="/announcementFiles/display?fileName=Announcement/${fileDTO.filePath}/${fileDTO.fileUUID}_${fileDTO.fileOriginalName}" />`).join('')
                    : `<img src="https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6" />`
            }
								</div>
							</label>
						</div>
						<ul class="content-list-wrap">
							<li class="content-list">
								<span>참여행사</span>
								<div class="content-input">
									<p>${eventTitle}</p>
								</div>
							</li>
							<li class="content-list">
								<span>제목</span>
								<div class="content-input">
									<p>${parentsBoard.parensBoardTitle}</p>
								</div>
							</li>
							<li class="content-list">
								<span>작성자</span>
								<div class="content-input">
									<p>${parentsBoard.memberNickName}</p>
								</div>
							</li>
							<li class="content-list">
								<span>작성일시</span>
								<div class="content-input">
									<p>${formattedDate}</p>
								</div>
							</li>
							<li class="content-list">
								<span>내용</span>
								<div class="content-input content-div">
									<p class="review-content">${parentsBoard.parentsBoardContent}</p>
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

getAdminParentsBoardList();


// 선택된 항목 삭제하기
$('.confirm-delete').on('click', function () {
    var checkedIds = new Array();
    // 체크 박스 체크된 값
    $('input:checkbox[name=check]:checked').closest('tr').find('.parentsBoard-id').each(function () {
        checkedIds.push(this.innerText);
        console.log(this.innerText);
    });

    // $('input:checkbox[name=check]:checked').closest('tr').find('.reply-id').each(function () {
    //     var id = $(this).text();
    //     checkedIds.push(parseInt(id, 10));
    // });
    console.log(checkedIds);
    console.log(typeof checkedIds[0]);
    $.ajax({
        url: "parentsBoard/delete",
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