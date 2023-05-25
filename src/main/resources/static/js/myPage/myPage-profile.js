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
// eventKidDTOS.content.forEach((e,i)=>{
//   calendar.createEvents([
//     {
//       id: `${e.id}`,
//       calendarId: `${e.category}`,
//       title: `${e.boardTitle}`,
//       start: `${e.calendarDTO.startDate}`,
//       end: `${e.calendarDTO.endDate}`,
//     }
//   ])
// })




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
                    <img src="https://cdn.wadiz.kr/ft/images/green001/2023/0313/20230313133752303_null.jpg/wadiz/thumbnail/253/format/jpg/quality/95/) 1x, url(https://cdn.wadiz.kr/ft/images/green001/2023/0313/20230313133752303_null.jpg/wadiz/thumbnail/506/format/jpg/quality/95/" alt="">
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
                      <br />장소 : ${eventKid.eventLocation.address} ${eventKid.eventLocation.addressDetail}
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