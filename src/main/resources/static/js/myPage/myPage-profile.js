/* mypage-profie.html */

const Calendar = tui.Calendar;
const container = document.getElementById('calendar');
const options = {
  defaultView: 'month',
  useFormPopup: true,
  useDetailPopup: true,
  isReadOnly: true,

  gridSelection: {
    enableDblClick: false,
    enableClick: false,
  },
  month: {
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    isAlways6Weeks: false,
    visibleEventCount: 4,
  },
  timezone: {
    zones: [
      {
        timezoneName: 'Asia/Seoul',
        displayLabel: 'Seoul',
      },
      {
        timezoneName: 'Europe/London',
        displayLabel: 'London',
      },
    ],
  },


  calendars: [
    {
      id: '1',
      name: '과학',
      backgroundColor: '#2e51ef',
    },
    {
      id: '2',
      name: '스포츠',
      backgroundColor: '#9f8673',
    },

    {
        id: '3',
        name: '전통',
        backgroundColor: '#705f53',
      },

      {
        id: '4',
        name: '박물관',
        backgroundColor: '#74aaf0',
      },
      {
        id: '5',
        name: '농촌',
        backgroundColor: '#90949c',
      },
      {
        id: '6',
        name: '미술관',
        backgroundColor: '#9b72e7',
      },
      {
        id: '7',
        name: '공방',
        backgroundColor: '#f0bb9d',
      },
      {
        id: '8',
        name: '기타',
        backgroundColor: '#7a87f5',
      }
  ],
};


const calendar = new Calendar(container, options);

calendar.on('clickEvent', ({ event }) => {
  console.log(event)
});

var currentDate = calendar.getDate();


$(".year").text(currentDate.getFullYear()+ "년");
$(".month").text(currentDate.getMonth() + 1 + "월");

$("#calender-prev").click(() => {
  currentDate = calendar.getDate();

  var prevDate = new Date(currentDate.getFullYear(), currentDate.getMonth() - 1, 1);
  var prevYear = prevDate.getFullYear();
  var prevMonthIndex = prevDate.getMonth();

  $(".year").text(prevYear+ "년");
  $(".month").text(prevMonthIndex + 1 + "월");

  calendar.prev();
});

$("#calender-next").click(() => {
  currentDate = calendar.getDate();

  var nextDate = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 1);
  var nextMonthIndex = nextDate.getMonth();
  var nextYear = nextDate.getFullYear();

  $(".year").text(nextYear+ "년");
  $(".month").text(nextMonthIndex + 1 + "월");

  calendar.next();
})

$("#today").click(() => {
  $(".year").text(year+ "년");
  $(".month").text(month + "월");

  calendar.today();
});


/* 쿼리 */

/* 전체 일정을 가져오는 쿼리 : 아래 createEvents로 모든 날을 등록해야함
  그래야 달력에 현재 내가 체험학습을 등록한 일정이 점으로 표현됨

  해당 날짜에 대한 날짜 쿼리 : ajax로 해당 날짜를 누를때마다 쿼리가 나가서 해당 월의 정보를 다 가져오기
  그리고 prev 버튼이나 next 버튼을 누르면 ajax로 다시 컨트롤러를 타야함
   */

/*  */
/* 
    달력 이벤트를 담는 법 json으로 받으면 됨
    대신 모든 정보는 string으로 받아야함
    달력에 점을 넣기 위한 코드
    하루에 하나씩만 넣어줘도 됨
    id는 무조거 달라야함
    *무조건 있어야함
*/
  // calendar.createEvents([
  //   {
  //     id: `${e.id}`,
  //     calendarId: `${e.category}`,
  //     title: `${e.boardTitle}`,
  //     start: `${e.calendarDTO.startDate}`,
  //     end: `${e.calendarDTO.endDate}`,
  //   }
  // ])




  /* 상세 일정을 띄워주는 코드 */
  $(document).ready(function() {
    let monthText = $('.month').text().trim();
      let monthMatch = monthText.match(/(\d+)월/);
      let month = monthMatch ? parseInt(monthMatch[1]) : null;


    /* document.ready를 무조건 해야 이 클래스를 로드할 수 있음 */
    $('.toastui-calendar-daygrid-cell').on('click', function() {
      let day = $(this).text();
      let passText = '';
      let now = new Date();
      let iconBackground = '';
      let eventStart = '';
      let eventEnd = '';

      // 클릭한 날짜의 시작 시간과 끝 시간 구하기
      // start: 시작시간 00:00:00
      // end : 끝 시간 23:59:59
      let start = new Date(Date.UTC(2023, month - 1, parseInt(day), 0, 0, 0)); // 0시 0분 0초
      let end = new Date(Date.UTC(2023, month - 1, parseInt(day), 23, 59, 59)); // 23시 59분 59초
      // 받아오는 정보
      let eventTitle = "놀러가요"
      let eventBody = "놀러가면 재밌어요"
      let eventCategory = "농촌"
      let location = '서울시 강동구'
      let category = '박물관';
      eventStart = '2023-05-24';
      eventEnd = new Date(start);
      console.log(eventEnd + "@@@@@@@@@@@@@@@@@@@@@@@@@@");

      var date = new Date(eventEnd);
      var formattedDate111 = date.toISOString().split('T')[0];

      console.log(formattedDate111); // Outputs: 2023-05-24

      let kstOffset = -540; // UTC+9
      start.setMinutes(start.getMinutes() + kstOffset); // 시작 시간을 한국 시간대로 변경
      end.setMinutes(end.getMinutes() + kstOffset); // 끝 시간을 한국 시간대로 변경


      // start와 end를 db에 넘기고 이 사이 정보를 받아온 다음
      // 그걸 반복문 돌려서 아래의 코드를 실행
      console.log('start : ' + start)
      console.log('end : ' + end)
      console.log('now :' + now )
      /* 날짜 한국어로 변경하는 코드 */
      let startFormat = eventStart.toLocaleString('ko-KR', {
        month: 'long',
        day: 'numeric',
        hour: 'numeric',
        minute: 'numeric'
      });

      let endFormat = eventEnd.toLocaleString('ko-KR', {
        month: 'long',
        day: 'numeric',
        hour: 'numeric',
        minute: 'numeric'
      });
      /* 한국어로 변경 코드 끝 */



      if(end < now ){
        /* status 받기? */
        passText = '이미 지나간 체험학습 입니다'
      } else {
        passText = '현재 진행가능한 체험학습 입니다'
      }

      /* 바뀐 시간 및 이미 지나간 체험학습인지 확인 */
      console.log('passText : ' + passText)
      console.log('startFormat : ' + startFormat)
      console.log('endFormat : ' + endFormat)

      if(category == '농촌'){
        iconBackground = '#2e51ef'
      } else if(category == '스포츠'){
        iconBackground = '#9f867';
      } else if(category == '전통'){
        iconBackground = '#705f53'
      } else if(category == '박물관'){
        iconBackground = '#90949c'
      }

      let page = 0;
      // ajax로 그날 참여한 모집 가져오기
      const sendData = () => {
        console.log("sendData 들어옴@@@@");
        let date = formattedDate111;
        $.ajax({
          type: 'post',
          url: `/mypage/profile/${date}`,
          // data: $('#reply-form').serialize(),
          success: function (eventKidDTOS) {
            console.log(eventKidDTOS);
            getResult(eventKidDTOS);
          },
          error: function (error) {
            $(".lecture-list").html('');
            console.log('Error fetching data:', error);
          }
        });
      };
      sendData();

      /* 클릭시마다 파란색으로 바뀌기 */
      for(let i =0; i< 37;i++){
        clickedDay = $($('.toastui-calendar-daygrid-cell').children().children().children()[i])
        if(clickedDay.hasClass('toastui-calendar-weekday-grid-date-decorator')){
          console.log("zzz")
          clickedDay.removeClass('toastui-calendar-weekday-grid-date-decorator')
          clickedDay.css('color', 'black')
          $(this).children().children().children().addClass('toastui-calendar-weekday-grid-date-decorator')
          $(this).children().children().children().css('color', 'white')
        }
      }

      // 결과 화면에 뿌려주기
  function getResult(eventKidDTOS) {
          eventKidDTOS.forEach((eventKid, i) => {
          let eventAll = "";
            let startDate = new Date(eventKid.calendarDTO.startDate); // assuming eventLike.registerDate is a valid date string
            let formattedDate = startDate.getFullYear() + '-' + (startDate.getMonth() + 1).toString().padStart(2, '0') + '-' + startDate.getDate().toString().padStart(2, '0');
            let endDate = new Date(eventKid.calendarDTO.endDate); // assuming eventLike.registerDate is a valid date string
            let formattedEndDate = endDate.getFullYear() + '-' + (endDate.getMonth() + 1).toString().padStart(2, '0') + '-' + endDate.getDate().toString().padStart(2, '0');

            eventAll +=
              `
              <!-- 왼쪽 컨텐츠 한개 -->
              <div class="lecture">
                <div class="lecture-wrap">
                <span class="lecture-image visible">
<!--                    <img src="https://cdn.wadiz.kr/ft/images/green001/2023/0313/20230313133752303_null.jpg/wadiz/thumbnail/253/format/jpg/quality/95/) 1x, url(https://cdn.wadiz.kr/ft/images/green001/2023/0313/20230313133752303_null.jpg/wadiz/thumbnail/506/format/jpg/quality/95/" alt="">-->
                    <img src="data:image/jpeg;base64,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" alt="">
                </span>
                
                <div class="lecture-wrapper">
                  <div class="lecture-content">
                    <p class="lecture-type">
                      <span><i class="reward" style='background:${iconBackground}'></i>입문</span>
                    </p>
                    <p class="lecture-title">
                      ${eventKid.boardTitle}
                    </p>
                    <p class="lecture-subtitle">
                      ${eventKid.boardContent}
                    </p>
                    <p class="lecture-info">
                      시작 : ${formattedDate}
                      <br> 
                      종료 : ${formattedEndDate}
                      <br />장소 : ${eventKid.eventLocation.address}
                      라이브
                    </p>
                    <p class="lecture-number">
                      <em><strong>${passText}</strong></em>
                    </p>
                  </div>
                </div>
              </div>
              <div class="table-wrapper">
                <table class="children-table">
                  <thead>
                    <tr>
                      <th class="nickname">나이</th>
                      <th class="name">이름</th>
                      <th class="place">체험 이름</th>
                      <th class="participant">체험 날짜</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td class="nickname">${eventKid.kidAge}</td>
                      <td class="name">${eventKid.kidName}</td>
                      <td class="place">${eventKid.boardTitle}</td>
                      <td class="participant">2023-04-22</td>
                    </tr>
                  </tbody>
                </table>
                </div>
              </div>
        <!-- 한개 끝 -->
              `
          $(".lecture-list").html(eventAll)
          $('.lecture').on('click', handleLectureClick);
          /* 상세 페이지 누르면 table 나오게 */
        })
    };
  });
})


/* 눌렀을때 또르륵 이벤트 핸들러 */
function handleLectureClick() {
  let i = $(this).index();
  let tableWrapper = $('.table-wrapper').eq(i);

  if (tableWrapper.is(':hidden')) {
    // 해당 테이블이 숨겨져 있는 경우
    $('.table-wrapper').slideUp(); // 다른 테이블 숨김 처리
    tableWrapper.slideDown(); // 해당 테이블 슬라이드 다운
  } else {
    tableWrapper.slideUp(); // 해당 테이블 슬라이드 업
  }
}


      /* 달력 끝 */


      /* 해당 월을  */

      /* 처음 월 넣기 */
      $('.lecture-list-month').html($('.month').text());

/* 클릭시마다 월 바뀌기 */
      $('#calender-next').on('click', function(){
        $('.lecture-list-month').html($('.month').text());
      })

      $('#calender-prev').on('click', function(){
        $('.lecture-list-month').html($('.month').text());
      })

/* 추가 코드*/
// '1~12월' 텍스트에서 월 지우기
function removeHangleMonth() {
  var monthElement = $('.month');
  var monthText = monthElement.text();
  var monthNumber = monthText.replace(/[^0-9]/g, ''); // 숫자만 남기기

  if (monthNumber.length == 1) {
    return "0" + monthNumber;
  } else {
    return monthNumber;
  }
}

// '2023년' 텍스트에서 년 지우기
function removeHangleYear() {
  var yearElement = $('.year');
  var yearText = yearElement.text();
  var yearNumber = yearText.replace(/[^0-9]/g, ''); // 숫자만 남기기

  return yearNumber;
}

// ajax로 그날 참여한 모집 가져오기
const getDate = () => {
  console.log("getDate 들어옴@@@@");
  $.ajax({
    type: 'GET',
    url: `/mypage/get-recruitment-dates`,
    success: function (result) {
      console.log(result);
      /* caledar에 일정 있는 날 점 찍기*/
      for (var i = 0; i < result.length; i++){
        calendar.createEvents([
          {
            start: result[i],
            end: result[i]
          }
        ]);
      }
    },
    error: function (error) {
      console.log('Error fetching data:', error);
    }
  });
};


$(document).ready(function () {
  console.log("여기로 들어왔어!!!!!!!!!!");
  getDate();
});