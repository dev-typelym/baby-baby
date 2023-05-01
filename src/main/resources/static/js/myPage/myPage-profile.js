/* mypage-profie.html */

const Calendar = tui.Calendar;
const container = document.getElementById('calendar');
const options = {
  defaultView: 'month',
  taskView: true,
  useCreationPopup: true,
  useDetailPopup: true,
  scheduleView: 'timeLine', 
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
      name: '입문',
      backgroundColor: '#2e51ef',
    },
    {
      id: '2',
      name: '실전',
      backgroundColor: '#9f8673',
    },

    {
        id: '3',
        name: '멘토링',
        backgroundColor: '#705f53',
      },

      {
        id: '4',
        name: '특강',
        backgroundColor: '#90949c',
      },
  ],
};

const calendar = new Calendar(container, options);

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




/* 일정 만드는 방법 */
/* 하나씩 담기 */
/* calendarId : 색을 정해주는 코드  */
/* eventId와 calendarId는 무조건 문자열이여야함 */
/* title : 제목 */
/* discription : 내용 */
/*  */

  /* 여러개 담기 */
  const scheduleData2 = [
    {
      calendarId: '2',
      id: '12',
      title: '새로운 일정 1',
      category: 'time',
      body : '놀러가요2',
      start: '2023-04-30T11:00:00+09:00',
      end: '2023-04-30T12:00:00+09:00'
    },  
    {
      id: '13',
      calendarId: '2',
      title: '새로운 일정 2',
      category: 'time',
      body : '놀러가요3',
      start: '2023-05-04T14:00:00+09:00',
      end: '2023-05-04T15:00:00+09:00'
    },
  ];
  
  calendar.createEvents(scheduleData2);
  calendar.render();

  $(document).ready(function() {
    $(".toastui-calendar-weekday-event-block").on("click", function() {
      const eventId = $(this).data('event-id');
      const calendarId = $(this).data('calendar-id');

      /* 이벤트를 가져오는 코드 */
      let event = calendar.getEvent(`${eventId}`,`${calendarId}`)
      console.log(event)
    });

  });
  /* 
      같은 날에 있는걸 하나씩 가져와서 하나의 폼에 담아준 다음
     그리고 그걸 가지고 반복문을 돌려서 왼쪽 창에 띄워준다
  */

/*      for(let i=0; i< scheduleData2.length ; i++ ){
      let content = `               
      <!-- 왼쪽 컨텐츠 한개 -->
      <div class="lecture">
        <span class="lecture-image visible">
            <img src="https://cdn.wadiz.kr/ft/images/green001/2023/0313/20230313133752303_null.jpg/wadiz/thumbnail/253/format/jpg/quality/95/) 1x, url(https://cdn.wadiz.kr/ft/images/green001/2023/0313/20230313133752303_null.jpg/wadiz/thumbnail/506/format/jpg/quality/95/" alt="">
        </span>
        
        <a href="#"
          ><div class="lecture-content">
            <p class="lecture-type">
              <span><i class="reward"></i>입문</span>
            </p>
            <p class="lecture-title">
              농촌체험학습
            </p>
            <p class="lecture-subtitle">
              떠나요~ 농촌체험학습
            </p>
            <p class="lecture-info">
              시작 : 04월 06일(목) 오후 3시 00분
              <br> 
              종료 : 오후 4시 30분
              <br />장소 : 유튜브
              라이브
            </p>
            <p class="lecture-number">
              <em><strong>지나간 체험학습 입니다.</strong></em>
            </p>
          </div></a
        >
      </div>
<!-- 한개 끝 -->`
      
      $('.calender-content-wrapper').append(content);
  
      
      }
 */

      //  해보려고 했지만 실패