
/* 상세보기 모달 ===================== */
const $showDetail = $(".row");

function openModal(num, e) {
  var selectedModal = '#ask-detail' + num;
  if (!$(e.target).is('input[type="checkbox"]') && !$(e.target).hasClass('no-modal')) {
    $(selectedModal).show();
  }
  console.log(num+"번 모달 클릭")
}

function writeModalOpen() {
  $('.answer').click(function () {
    $('#answer-write').show();
    console.log("클릭됨")
  });

  $('.closeBtn').click(function () {
    $('#answer-write').hide();
  });
}

/* 상세 모달 닫기 */
function closeModal(num) {
  var selectedModal = '#ask-detail' + num;
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
const $showWrite = $(".answer");
const $cancelWrite = $(".closeBtn");

$showWrite.on('click', function(e){
  $("#answer-write").show();
})

$cancelWrite.on("click", function(e) {
  $("#answer-write").hide();
});

//-----------------------------------------ajax--------------------------------------------------

const PAGE_AMOUNT = 10;
const $itemWrap = $(".show-item-wrap");
const SEARCH_URL = "/parentsYard/list/show";
const $pageWrap = $(".page-button-box");
const $contentWrap = $(".ask-list");



function getAdminAskList() {
  $.ajax({
    url: `askList/${globalThis.page}`,
    success: function(data) {
      $pageWrap.empty();
      showPage(data);
      $contentWrap.empty();
      showList(data.content);
      askButtonEvents();
      writeModalOpen();
      getTr();
    }

  })
}

globalThis.page = 1;



function findPage(page) {
  globalThis.page = page;
  getAdminAskList();
}



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


//    문의 목록
function showList(askDTOS) {
  var content = "";
  askDTOS.forEach(ask => {
    const formattedDate = formatDate(new Date(ask.writeDate));
    content +=
        `    <tbody class="first-body">
               <tr class="row" onclick="openModal(${ask.id}, event)">
                    <td class="no-modal">
                        <input type="checkbox" name="check">
                    </td>
                    <td class="ask-id">${ask.id}</td>
                    <td>${ask.askTitle}</td>
                    <td>${ask.writerName}</td>
                    <td>${formattedDate}</td>
                    <td>${ask.askContent}</td>
                    <td></td>
                </tr>
                <tr class="btn-row">
                    <td class="answer-btn-wrapper">
                      <button type="button" class="answer" id="${ask.id}">답변하기</button>
                    </td>
                </tr>
              </tbody>
            `
  });
  $contentWrap.append(content);

}

getAdminAskList();

// ---------------------- 공지사항 상세 ------------------------
const $contentDetailWrap = $(".ask-modal-list");



function getAdminAskDetailList() {

  // var clickedDetail = null;
  // $('.row').click(function () {
  //     clickedDetail = $(this).find('.announcement-id').text();
  // });

  $.ajax({
    url: `askDetail`,
    success: function(data) {
      $contentDetailWrap.empty();
      showDetailList(data);
    }

  })
}


//    공지사항 상세 목록
function showDetailList(askDetailDTOS) {
  var detailContent = "";
  console.log(askDetailDTOS)
  askDetailDTOS.forEach(askDetail => {
    const formattedDate = formatDate(new Date(askDetail.writeDate));
    detailContent +=
        `
          <section class="user-modal" id="ask-detail${askDetail.id}">
              <div class="user-modal-shape">
                  <div class="modal-header">
                      <h4>문의 상세보기</h4>
                      <a class="modal-close">
                          <svg
                              xmlns="http://www.w3.org/2000/svg"
                              data-name="Capa 1"
                              id="Capa_1"
                              viewBox="0 0 20 19.84"
                              onclick="closeModal(${askDetail.id})"
                          >
                              <path d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z"/>
                          </svg>
                      </a>
                  </div>
                  <form class="user-info" action>
                      <main class="user-detail">
                          <h5 class="detail-title">문의 상세정보</h5>
                          <ul class="content-list-wrap">
                              <li class="content-list ask-detail">
                                  <span>제목</span>
                                  <div class="content-input">
                                      <p>${askDetail.askTitle}</p>
                                  </div>
                              </li>
                              <li class="content-list ask-detail">
                                  <span>작성자</span>
                                  <div class="content-input">
                                      <p>${askDetail.writerName}</p>
                                  </div>
                              </li>
                              <li class="content-list ask-detail">
                                  <span>작성일시</span>
                                  <div class="content-input">
                                      <p>${formattedDate}</p>
                                  </div>
                              </li>
                              <li class="content-list ask-detail">
                                  <span>내용</span>
                                  <div class="content-input content-div">
                                      <p class="anouncement-content">${askDetail.askContent}</textarea>
                                  </div>
                              </li>
                          </ul>
                      </main>
                  </form>
              </div>
          </section>
<!--          <section class="user-modal" id="answer-write">-->
<!--                <div class="user-modal-shape">-->
<!--                    <div class="modal-header">-->
<!--                        <h4>문의 답변하기</h4>-->
<!--                        <a class="modal-close">-->
<!--                          <svg-->
<!--                          xmlns="http://www.w3.org/2000/svg"-->
<!--                          data-name="Capa 1"-->
<!--                          id="Capa_1"-->
<!--                          viewBox="0 0 20 19.84"-->
<!--                          class="closeBtn"-->
<!--                          >-->
<!--                              <path d="M10.17,10l3.89-3.89a.37.37,0,1,0-.53-.53L9.64,9.43,5.75,5.54a.37.37,0,1,0-.53.53L9.11,10,5.22,13.85a.37.37,0,0,0,0,.53.34.34,0,0,0,.26.11.36.36,0,0,0,.27-.11l3.89-3.89,3.89,3.89a.34.34,0,0,0,.26.11.35.35,0,0,0,.27-.11.37.37,0,0,0,0-.53Z"/>-->
<!--                          </svg>-->
<!--                        </a>-->
<!--                    </div>-->
<!--                    <form class="user-info" action>-->
<!--                        <main class="user-detail">-->
<!--                            <h5 class="detail-title">문의 답변</h5>-->
<!--                            <ul class="content-list-wrap">-->
<!--                              <li class="content-list">-->
<!--                                <span>제목</span>-->
<!--                                <div class="content-input">-->
<!--                                    <input type="text"/>-->
<!--                                </div>-->
<!--                              </li>-->
<!--                              <li class="content-list">-->
<!--                                <span>내용</span>-->
<!--                                <div class="content-input content-div">-->
<!--                                  <textarea class="anouncement-content"></textarea>-->
<!--                                </div>-->
<!--                              </li>-->
<!--                            </ul>-->
<!--                            <div class="update-box">-->
<!--                              <button type="button" class="update-button">작성하기</button>-->
<!--                            </div>-->
<!--                        </main>-->
<!--                    </form>-->
<!--                </div>-->
<!--          </section>                                                 -->
          
       `
  });
  $contentDetailWrap.append(detailContent);

}

getAdminAskDetailList();



// 선택된 문의 삭제하기
$('.confirm-delete').on('click', function () {
  var checkedIds = new Array();
  // 체크 박스 체크된 값
  $('input:checkbox[name=check]:checked').closest('tr').find('.ask-id').each(function () {
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
    url: "ask/delete",
    type: "patch",
    data: {
      "checkedIds": checkedIds,
    },
    success: function () {
      findPage(page);
    }
  });

  $('input:checkbox[id=allSelect]:checked').prop('checked', false);
});



/* 문의답변 버튼 js */
function askButtonEvents() {
  const detailBtnBodyList = document.querySelectorAll('.row');
  const detailBtnList = document.querySelectorAll('.answer');

  detailBtnBodyList.forEach(function (detailBtnBody, index) {
    detailBtnBody.addEventListener('mouseover', function () {
      detailBtnList[index].style.background = "#828282";
      detailBtnList[index].style.color = "#fff";
      detailBtnBodyList[index].style.background = "#828282";
      detailBtnBodyList[index].style.color = "#fff";
    });

    detailBtnBody.addEventListener('mouseout', function () {
      detailBtnList[index].style.background = "#fff";
      detailBtnList[index].style.color = "#000000";
      detailBtnBodyList[index].style.background = "#fff";
      detailBtnBodyList[index].style.color = "#000000";
    });
  });

  detailBtnList.forEach(function (detailBtn, index) {
    detailBtn.addEventListener('mouseover', function () {
      detailBtnList[index].style.background = "#828282";
      detailBtnList[index].style.color = "#fff";
      detailBtnBodyList[index].style.background = "#828282";
      detailBtnBodyList[index].style.color = "#fff";
    });

    detailBtn.addEventListener('mouseout', function () {
      detailBtnList[index].style.background = "#fff";
      detailBtnList[index].style.color = "#000000";
      detailBtnBodyList[index].style.background = "#fff";
      detailBtnBodyList[index].style.color = "#000000";
    });
  });
}

// 답변하기 버튼 누르면
function getTr() {
  $('.answer').click(function (e) {
    var askId = $(e.currentTarget).attr('id');
    console.log("답변하기 누름")
    console.log(globalThis.askId)
    $('.answer-button').data('askId', askId);
  });
}



//  답변하기
$('.answer-button').click(function() {
  var answerTitle = $('.answer-title').val();
  var answerContent = $('.answer-content').val();
  var askId = $(this).data('askId');
  var answerData = {
    answerTitle: answerTitle,
    answerContent: answerContent,
    askId: askId
  };
  console.log(answerData)

  $.ajax({
    url: 'ask/answer',
    method: 'POST',
    contentType: 'application/json',
    data: JSON.stringify(answerData),
    success: function(response) {
      var tdElements = document.querySelectorAll('.ask-id');
      var askIdElements = $(tdElements).filter(function() {
        return $(this).text() == response;
      });
      console.log(askIdElements.text())
      console.log(response)
      console.log(typeof response)
      askIdElements.parents().find('.btn-row > .answer-btn-wrapper > .answer#'+response).text('답변완료');
      askIdElements.parents().find('.btn-row > .answer-btn-wrapper > .answer#'+response).css('color', 'green');
      console.log('저장되었습니다.');
    },
    error: function(xhr, status, error) {
      console.error('저장 실패:', error);
    }
  });
});




