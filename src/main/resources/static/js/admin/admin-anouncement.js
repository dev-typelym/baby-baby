

/* 상세보기 모달 ===================== */

function openModal(num, e) {
    var selectedModal = '#anouncement-detail' + num;
    if (!$(e.target).is('input[type="checkbox"]') && !$(e.target).hasClass('no-modal')) {
        $(selectedModal).show();
    }
    console.log(num+"번 모달 클릭")
}


/* 상세 모달 닫기 */
function closeModal(num) {
    var selectedModal = '#anouncement-detail' + num;
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


/* 작성 모달 ======================== */
const $showWrite = $(".add-button");
const $cancelWrite = $(".closeBtn");

$showWrite.on('click', function(e){
    $("#anouncement-write").show();
})

$cancelWrite.on("click", function(e) {
    $('#announcementTitle').val("");
    $('#announcementContent').val("");
    $('input[name=file]').val('');
    var img = document.querySelector('.img-inserted');
    var newSrc = 'https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6';
    img.src = newSrc;
    $("#anouncement-write").hide();
});

// 수정하기 썸네일
function updateThumbnail(){
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
}

// 작성하기 썸네일
$('.insert-profile').change(function(event) {
    var input = event.target;
    var reader = new FileReader();
    reader.onload = function() {
        var dataURL = reader.result;
        var image = $('.img-inserted');
        image.attr('src', dataURL);
    };
    reader.readAsDataURL(input.files[0]);
});


//-----------------------------------------ajax--------------------------------------------------

const PAGE_AMOUNT = 10;
const $itemWrap = $(".show-item-wrap");
const SEARCH_URL = "/parentsYard/list/show";
const $pageWrap = $(".page-button-box");
const $contentWrap = $(".announcement-list");
const $detailContentWrap = $(".announcement-modal-list");

const adminAnnouncementSearch = {
    announcementTitle: null
};


function getAdminAnnouncementList() {
    $.ajax({
        url: `announcementList/${globalThis.page}`,
        data: adminAnnouncementSearch,
        success: function(data) {
            console.log(data)
            $pageWrap.empty();
            showPage(data);
            $contentWrap.empty();
            showList(data.content);
            updateThumbnail();
        }

    })
}

globalThis.page = 1;



function findPage(page) {
    globalThis.page = page;
    getAdminAnnouncementList();
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
    adminAnnouncementSearch.announcementTitle = val;

    console.log( adminAnnouncementSearch.announcementTitle + "777");
    getAdminAnnouncementList();
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


//    공지사항 목록
function showList(announcementDTOS) {
    var content = "";
    var detailContent = "";
    // console.log(announcementDTOS)
    announcementDTOS.forEach(announcement => {
        console.log(announcement.announcementFileDTOS[0])
        const formattedDate = formatDate(new Date(announcement.writeDate));
        content +=
            `
                <tr class="row" onclick="openModal(${announcement.id}, event)">
                    <td class="no-modal">
                        <input type="checkbox" name="check">
                    </td>
                    <td class="announcement-id">${announcement.id}</td>
                    <td>${announcement.announcementTitle}</td>
                    <td>${announcement.writerName}</td>
                    <td>${formattedDate}</td>
                    <td>${announcement.announcementContent}</td>
                </tr>
                
            `
        detailContent +=
            `
                <section class="user-modal" id="anouncement-detail${announcement.id}">
                    <div class="user-modal-shape">
                        <div class="modal-header">
                            <h4>공지사항 상세보기</h4>
                            <a class="modal-close">
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    data-name="Capa 1"
                                    id="Capa_1"
                                    viewBox="0 0 20 19.84"
                                    onclick="closeModal(${announcement.id})"
                                >
                                    <path d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z"/>
                                </svg>
                            </a>
                        </div>
                        <form class="user-info" action>
                            <main class="user-detail">
                                <h5 class="detail-title">공지사항 상세정보</h5>
                                <div class="profile-img-wrapper">
                                    <div class="content-img">
                                                                                                                                   
                                        <img
                                            class="img-updated"  src="/announcementFiles/display?fileName=Announcement/${announcement.announcementFileDTOS[0].filePath}/${announcement.announcementFileDTOS[0].fileUuid}_${announcement.announcementFileDTOS[0].fileOrgName}"
                                        />
                                    </div>                                                                                  
<!--                                    <label class="label-for-update-img">-->
                                        <?xml version="1.0" ?><svg id="Layer_1" style="enable-background:new 0 0 100 100;" version="1.1" viewBox="0 0 100 100" xml:space="preserve" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
                                            <style type="text/css">
                                            .st0{fill:#E64C3C;}
                                            .st1{fill:#EFEFEF;}
                                            </style><circle class="st0" cx="50" cy="49.6" id="XMLID_10_" r="50"/><g id="XMLID_5_"><path class="st1" d="M76.9,38h-8l-3.1-5c-1.3-2-3.5-3.2-5.8-3.2H40.1c-2.4,0-4.6,1.2-5.8,3.2l-3.1,5h-8   c-1.4,0-2.6,1.2-2.6,2.6v26.3c0,1.4,1.2,2.6,2.6,2.6h53.8c1.4,0,2.6-1.2,2.6-2.6V40.6C79.5,39.2,78.3,38,76.9,38z M50,64.9   c-6.5,0-11.7-5.3-11.7-11.7S43.5,41.4,50,41.4c6.5,0,11.7,5.3,11.7,11.7S56.5,64.9,50,64.9z" id="XMLID_8_"/><circle class="st1" cx="50" cy="53.2" id="XMLID_9_" r="8.9"/></g></svg>
<!--                                        <input class="update-profile" type="file" name="file" style="display: none" />-->
<!--                                    </label>-->
                                </div>
                                <ul class="content-list-wrap">
                                    <li class="content-list">
                                        <span>제목</span>
                                        <div class="content-input">
                                            <input type="text" value="${announcement.announcementTitle}" readonly/>
                                        </div>
                                    </li>
                                    <li class="content-list">
                                        <span>작성자</span>
                                        <div class="content-input">
                                            <input type="text" value="${announcement.writerName}" readonly/>
                                        </div>
                                    </li>
                                    <li class="content-list">
                                        <span>작성일시</span>
                                        <div class="content-input">
                                            <input type="text" value="${formattedDate}" readonly/>
                                        </div>
                                    </li>
                                    <li class="content-list">
                                        <span>내용</span>
                                        <div class="content-input content-div">
                                            <textarea class="anouncement-content" readonly>${announcement.announcementContent}</textarea>
                                        </div>
                                    </li>
                                </ul>
                                <div class="update-box">
<!--                                    <button type="button" class="update-button">수정하기</button>-->
                                </div>
                            </main>
                        </form>
                    </div>
                </section>
            `
    });
    $contentWrap.append(content);
    $detailContentWrap.append(detailContent);


}

getAdminAnnouncementList();

// ---------------------- 공지사항 상세 ------------------------
// const $contentDetailWrap = $(".announcement-modal-list");
//
//
//
// function getAdminAnnouncementDetailList() {
//
//     // var clickedDetail = null;
//     // $('.row').click(function () {
//     //     clickedDetail = $(this).find('.announcement-id').text();
//     // });
//
//     $.ajax({
//         url: `announcementDetail`,
//         success: function(data) {
//             $contentDetailWrap.empty();
//             showDetailList(data);
//         }
//
//     })
// }
//
//
// //    공지사항 상세 목록
// function showDetailList(announcementDetailDTOS) {
//     var detailContent = "";
//     console.log(announcementDetailDTOS)
//     announcementDetailDTOS.forEach(announcementDetail => {
//         console.log("path확인")
//         console.log(announcementDetail.announcementFileDTOS)
//         console.log("path확인")
//         const formattedDate = formatDate(new Date(announcementDetail.writeDate));
//         detailContent +=
//             `
//                 <section class="user-modal" id="anouncement-detail${announcementDetail.id}">
//                     <div class="user-modal-shape">
//                         <div class="modal-header">
//                             <h4>공지사항 상세보기</h4>
//                             <a class="modal-close">
//                                 <svg
//                                     xmlns="http://www.w3.org/2000/svg"
//                                     data-name="Capa 1"
//                                     id="Capa_1"
//                                     viewBox="0 0 20 19.84"
//                                     onclick="closeModal(${announcementDetail.id})"
//                                 >
//                                     <path d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z"/>
//                                 </svg>
//                             </a>
//                         </div>
//                         <form class="user-info" action>
//                             <main class="user-detail">
//                                 <h5 class="detail-title">공지사항 상세정보</h5>
//                                 <div class="profile-img-wrapper">
//                                     <div class="content-img">
//
//                                         <img
//                                             class="img-updated"
//                                             src="/announcementFiles/display?fileName=Announcement/${announcementDetail.announcementFileDTOS[0].filePath}/${announcementDetail.announcementFileDTOS[0].fileUuid}_${announcementDetail.announcementFileDTOS[0].fileOrgName}"
//                                         />
//                                     </div>
//                                     <label class="label-for-update-img">
//                                         <?xml version="1.0" ?><svg id="Layer_1" style="enable-background:new 0 0 100 100;" version="1.1" viewBox="0 0 100 100" xml:space="preserve" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
//                                             <style type="text/css">
//                                             .st0{fill:#E64C3C;}
//                                             .st1{fill:#EFEFEF;}
//                                             </style><circle class="st0" cx="50" cy="49.6" id="XMLID_10_" r="50"/><g id="XMLID_5_"><path class="st1" d="M76.9,38h-8l-3.1-5c-1.3-2-3.5-3.2-5.8-3.2H40.1c-2.4,0-4.6,1.2-5.8,3.2l-3.1,5h-8   c-1.4,0-2.6,1.2-2.6,2.6v26.3c0,1.4,1.2,2.6,2.6,2.6h53.8c1.4,0,2.6-1.2,2.6-2.6V40.6C79.5,39.2,78.3,38,76.9,38z M50,64.9   c-6.5,0-11.7-5.3-11.7-11.7S43.5,41.4,50,41.4c6.5,0,11.7,5.3,11.7,11.7S56.5,64.9,50,64.9z" id="XMLID_8_"/><circle class="st1" cx="50" cy="53.2" id="XMLID_9_" r="8.9"/></g></svg>
//                                         <input class="update-profile" type="file" name="file" style="display: none" />
//                                     </label>
//                                 </div>
//                                 <ul class="content-list-wrap">
//                                     <li class="content-list">
//                                         <span>제목</span>
//                                         <div class="content-input">
//                                             <input type="text" value="${announcementDetail.announcementTitle}"/>
//                                         </div>
//                                     </li>
//                                     <li class="content-list">
//                                         <span>작성자</span>
//                                         <div class="content-input">
//                                             <input type="text" value="${announcementDetail.writerName}"/>
//                                         </div>
//                                     </li>
//                                     <li class="content-list">
//                                         <span>작성일시</span>
//                                         <div class="content-input">
//                                             <input type="text" value="${formattedDate}"/>
//                                         </div>
//                                     </li>
//                                     <li class="content-list">
//                                         <span>내용</span>
//                                         <div class="content-input content-div">
//                                             <textarea class="anouncement-content">${announcementDetail.announcementContent}</textarea>
//                                         </div>
//                                     </li>
//                                 </ul>
//                                 <div class="update-box">
//                                     <button type="button" class="update-button">수정하기</button>
//                                 </div>
//                             </main>
//                         </form>
//                     </div>
//                 </section>
//             `
//     });
//     $contentDetailWrap.append(detailContent);
//
// }
//
// getAdminAnnouncementDetailList();



// 선택된 공지사항 삭제하기
/*$('.confirm-delete').on('click', function () {
    var checkedIds = new Array();
    // 체크 박스 체크된 값
    $('input:checkbox[name=check]:checked').closest('tr').find('.member-id').each(function () {
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
        url: "member/delete",
        type: "patch",
        data: {
            "checkedIds": checkedIds,
        },
        success: function () {
            findPage(page);
        }
    });

    $('input:checkbox[id=allSelect]:checked').prop('checked', false);
});*/


// ------------------------ 공지사항 추가하기----------------------------
// function leftPad(value) {
//     if (value >= 10) {
//         return value;
//     }
//     return `0${value}`;
// }
//
// function toStringByFormatting(source, delimiter = '/') {
//     const year = source.getFullYear();
//     const month = leftPad(source.getMonth() + 1);
//     const day = leftPad(source.getDate());
//
//     return [year, month, day].join(delimiter);
// }
//
//
//
// const $fileInput = $(".insert-profile");
// $fileInput.change((e) => {
//     globalThis.files = e.target.files;
//     let filesArr = Array.prototype.slice.call(globalThis.files);
//     let formData = new FormData();
//     filesArr.forEach(file => formData.append("file", file))
//     console.log(files);
//     $.ajax({
//         url: "/announcementFiles/upload",
//         type: "post",
//         data: formData,
//         contentType: false,
//         processData: false,
//         success: function (uuids) {
//             globalThis.uuids = uuids;
//             console.log(globalThis.uuids)
//
//             const dataTransfer = new DataTransfer();
//             filesArr = dataTransfer.files;
//
//             console.log(files)
//
//             let inputFiles1 = "";
//             files.forEach(file => {
//                 inputFiles1 +=
//                     `
//                         <input type="hidden" name="files[${j}].fileOriginalName" value="${file.name}">
//                         <input type="hidden" name="files[${j}].fileUUID" value="${globalThis.uuids[j]}">
//                         <input type="hidden" name="files[${j}].filePath" value="${toStringByFormatting(new Date())}">
//                         `
//                 j++;
//             });
//             j=0;
//             $('#header').append(inputFiles1);
//         }
//     });
// });
//
//
// $('.push-button').click(function() {
//     var boardTitle = $('.announcementTitle').val();
//     var boardContent = $('.announcementContent').val();
//         var announcementData = {
//             boardTitle: boardTitle,
//             boardContent: boardContent,
//             "adminAnnouncementDTO.fileUUID" : globalThis.uuids ,
//             "adminAnnouncementDTO.filePath" : toStringByFormatting(new Date()) ,
//             "adminAnnouncementDTO.fileOriginalName" :globalThis.files[0].name
//         };
//
//         console.log(globalThis.files[0].name)
//
//         $.ajax({
//             url: 'announcement/insert',
//             method: 'POST',
//             contentType: 'application/json',
//             data: JSON.stringify(announcementData),
//             success: function(response) {
//                 console.log('저장되었습니다.');
//             },
//             error: function(xhr, status, error) {
//                 console.error('저장 실패:', error);
//             }
//         });
// });



// const $fileInput = $(".insert-profile");
// FileList.prototype.forEach = Array.prototype.forEach;
// let j = 0;
// $fileInput.change((e) => {
//     let files = e.target.files;
//     let filesArr = Array.prototype.slice.call(files);
//     let formData = new FormData();
//     filesArr.forEach(file => formData.append("file", file))
//     console.log(files);
//     $.ajax({
//         url: "/announcementFiles/upload",
//         type: "post",
//         data: formData,
//         contentType: false,
//         processData: false,
//         success: function (uuids) {
//             globalThis.uuids = uuids;
//             console.log(globalThis.uuids)
//
//             const dataTransfer = new DataTransfer();
//             filesArr = dataTransfer.files;
//
//             console.log(files)
//
//             let inputFiles1 = "";
//             files.forEach(file => {
//                 inputFiles1 +=
//                     `
//                     <input type="hidden" name="files[${j}].fileOriginalName" value="${file.name}">
//                     <input type="hidden" name="files[${j}].fileUUID" value="${globalThis.uuids[j]}">
//                     <input type="hidden" name="files[${j}].filePath" value="${toStringByFormatting(new Date())}">
//                     `
//                 j++;
//             });
//             j=0;
//             $('.hidden-input-wrapper').html(inputFiles1);
//         }
//     });
// });
//
//
//
// $('.push-button').click(function() {
//     $('.hidden-input-wrapper').empty();
//     var boardTitle = $('.announcementTitle').val();
//     var boardContent = $('.announcementContent').val();
//     var fileName = $('input[name="files[0].fileOriginalName"]').val();
//     var announcementData = {
//         boardTitle: boardTitle,
//         boardContent: boardContent,
//         "announcementFileDTOS.fileUUID" : globalThis.uuids ,
//         "announcementFileDTOS.filePath" : toStringByFormatting(new Date()) ,
//         "announcementFileDTOS.fileOriginalName" : fileName
//     };
//
//     console.log(fileName)
//
//     $.ajax({
//         url: 'announcement/announcementInsert',
//         method: 'POST',
//         contentType: 'application/json',
//         data: JSON.stringify(announcementData),
//         success: function(response) {
//             console.log('저장되었습니다.');
//         },
//         error: function(xhr, status, error) {
//             console.error('저장 실패:', error);
//         }
//     });
// });
// 파일 넣기

let fileList = [];
// 받은 uuid 담을 배열
let Uuid = [];
// 받은 path를 담을 배열
let path = [];
let formData = new FormData();

$(`input[name=file]`).on('change', function () {
    fileList = [];
    let input = $(this)
    // 파일 찾아오기
    // const $files = $(`input[name=file]`)[i].files;
    const $files = $(this).prop('files');
    if ($files.length > 0) {
        // 배열의 각 i번째에 해당 파일을 집어넣는다
        fileList.push($files[0]);
    }

    formData.append("file", fileList[0])

    getFilePath(formData);
    console.log("파일")
    console.log(fileList)
    console.log(fileList[0])
    console.log("파일")
    console.log(formData)
    console.log(formData.get("file"));
});

function getFilePath(formData) {
    $.ajax({
        url: "/announcementFiles/upload",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        success: function (data) {
            console.log(data)
            console.log( getFilePath+"들어옴")
            Uuid[0] = data.uuids[0];
            path[0] = data.paths[0];

            console.log("출력 uuid0번")
            console.log(Uuid[0])
            console.log(path[0])
            console.log("출력 uuid0번")
            // addFile();
        }
    })
};


/* 작성 */
function announcementWrite() {
    // 공지 파일 DTO 설정
    let announcementFiles = [];

    let announcementFileDTO = {
        fileOrgName: fileList[0].name,
        filePath: path[0],
        fileUuid: Uuid[0]
    };
    announcementFiles.push(announcementFileDTO);

    console.log(Uuid)
    console.log(path)

    // 공지 DTO 설정
    let adminAnnouncementDTO = new Object();
    adminAnnouncementDTO.announcementTitle = $('#announcementTitle').val();
    adminAnnouncementDTO.announcementContent = $('#announcementContent').val();
    adminAnnouncementDTO.announcementFileDTOS = announcementFiles;

    if (announcementFiles.length === 0) {
        console.log('공지 파일이 없습니다.');
        return;
    }
    $.ajax({
        url: "announcement/insert",
        type: "post",
        data: JSON.stringify(adminAnnouncementDTO),
        contentType: "application/json; charset=utf-8",
        success: function () {
            // $('.add-modal').fadeOut(500);
            getAdminAnnouncementList();
            // getAdminAnnouncementDetailList();
            $('#announcementTitle').val("");
            $('#announcementContent').val("");
            $('input[name=file]').val('');
            var img = document.querySelector('.img-inserted');
            var newSrc = 'https://us.123rf.com/450wm/mathier/mathier1905/mathier190500002/134557216-%EC%8D%B8%EB%84%A4%EC%9D%BC-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%86%EC%9D%8C-%ED%8F%AC%EB%9F%BC-%EB%B8%94%EB%A1%9C%EA%B7%B8-%EB%B0%8F-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8%EC%9A%A9-%EC%9E%90%EB%A6%AC-%ED%91%9C%EC%8B%9C%EC%9E%90.jpg?ver=6';
            img.src = newSrc;
            $("#anouncement-write").hide();

        }
    })
}

const $writeNoticeButton = $('.push-button');

$writeNoticeButton.on('click', function () {
    console.log("들어옴")
    let noticeTitle = $('#announcementTitle').val();
    let noticeContent = $('#announcementContent').val();


    if (noticeTitle != null && noticeContent != null) {
        announcementWrite();
        console.log("공지사항 제목, 내용 들어옴")
    }
})


// 선택된 항목 삭제하기
$('.confirm-delete').on('click', function () {
    var checkedIds = new Array();
    // 체크 박스 체크된 값
    $('input:checkbox[name=check]:checked').closest('tr').find('.announcement-id').each(function () {
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
        url: "announcement/delete",
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