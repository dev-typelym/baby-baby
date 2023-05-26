(function () {
    // globals
    var canvas;
    var ctx;
    var W;
    var H;
    var mp = 150; //max particles
    var particles = [];
    var angle = 0;
    var tiltAngle = 0;
    var confettiActive = true;
    var animationComplete = true;
    var deactivationTimerHandler;
    var reactivationTimerHandler;
    var animationHandler;

    // objects

    var particleColors = {
        colorOptions: ["DodgerBlue", "OliveDrab", "Gold", "pink", "SlateBlue", "lightblue", "Violet", "PaleGreen", "SteelBlue", "SandyBrown", "Chocolate", "Crimson"],
        colorIndex: 0,
        colorIncrementer: 0,
        colorThreshold: 10,
        getColor: function () {
            if (this.colorIncrementer >= 10) {
                this.colorIncrementer = 0;
                this.colorIndex++;
                if (this.colorIndex >= this.colorOptions.length) {
                    this.colorIndex = 0;
                }
            }
            this.colorIncrementer++;
            return this.colorOptions[this.colorIndex];
        }
    }

    function confettiParticle(color) {
        this.x = Math.random() * W; // x-coordinate
        this.y = (Math.random() * H) - H; //y-coordinate
        this.r = RandomFromTo(10, 15); //radius;
        this.d = (Math.random() * mp) + 10; //density;
        this.color = color;
        this.tilt = Math.floor(Math.random() * 10) - 10;
        this.tiltAngleIncremental = (Math.random() * 0.07) + .05;
        this.tiltAngle = 0;

        this.draw = function () {
            ctx.beginPath();
            ctx.lineWidth = this.r / 2;
            ctx.strokeStyle = this.color;
            ctx.moveTo(this.x + this.tilt + (this.r / 4), this.y);
            ctx.lineTo(this.x + this.tilt, this.y + this.tilt + (this.r / 4));
            return ctx.stroke();
        }
    }

    $(document).ready(function () {
        SetGlobals();
        InitializeButton();
        //InitializeConfetti();

        $(window).resize(function () {
            W = window.innerWidth;
            H = window.innerHeight;
            canvas.width = W;
            canvas.height = H;
        });

    });

    function InitializeButton() {
        $('#stopButton').click(DeactivateConfetti);
        $('#startButton').click(RestartConfetti);
    }

    function SetGlobals() {
        canvas = document.getElementById("canvas");
        ctx = canvas.getContext("2d");
        W = window.innerWidth;
        H = window.innerHeight;
        canvas.width = W;
        canvas.height = H;
    }

    function InitializeConfetti() {
        particles = [];
        animationComplete = false;
        for (var i = 0; i < mp; i++) {
            var particleColor = particleColors.getColor();
            particles.push(new confettiParticle(particleColor));
        }
        StartConfetti();
    }

    function Draw() {
        ctx.clearRect(0, 0, W, H);
        var results = [];
        for (var i = 0; i < mp; i++) {
            (function (j) {
                results.push(particles[j].draw());
            })(i);
        }
        Update();

        return results;
    }

    function RandomFromTo(from, to) {
        return Math.floor(Math.random() * (to - from + 1) + from);
    }


    function Update() {
        var remainingFlakes = 0;
        var particle;
        angle += 0.01;
        tiltAngle += 0.1;

        for (var i = 0; i < mp; i++) {
            particle = particles[i];
            if (animationComplete) return;

            if (!confettiActive && particle.y < -15) {
                particle.y = H + 100;
                continue;
            }

            stepParticle(particle, i);

            if (particle.y <= H) {
                remainingFlakes++;
            }
            CheckForReposition(particle, i);
        }

        if (remainingFlakes === 0) {
            StopConfetti();
        }
    }

    function CheckForReposition(particle, index) {
        if ((particle.x > W + 20 || particle.x < -20 || particle.y > H) && confettiActive) {
            if (index % 5 > 0 || index % 2 == 0) //66.67% of the flakes
            {
                repositionParticle(particle, Math.random() * W, -10, Math.floor(Math.random() * 10) - 20);
            } else {
                if (Math.sin(angle) > 0) {
                    //Enter from the left
                    repositionParticle(particle, -20, Math.random() * H, Math.floor(Math.random() * 10) - 20);
                } else {
                    //Enter from the right
                    repositionParticle(particle, W + 20, Math.random() * H, Math.floor(Math.random() * 10) - 20);
                }
            }
        }
    }

    function stepParticle(particle, particleIndex) {
        particle.tiltAngle += particle.tiltAngleIncremental;
        particle.y += (Math.cos(angle + particle.d) + 3 + particle.r / 2) / 3;
        particle.x += Math.sin(angle);
        particle.tilt = (Math.sin(particle.tiltAngle - (particleIndex / 3))) * 15;
    }

    function repositionParticle(particle, xCoordinate, yCoordinate, tilt) {
        particle.x = xCoordinate;
        particle.y = yCoordinate;
        particle.tilt = tilt;
    }

    function StartConfetti() {
        W = window.innerWidth;
        H = window.innerHeight;
        canvas.width = W;
        canvas.height = H;
        (function animloop() {
            if (animationComplete) return null;
            animationHandler = requestAnimFrame(animloop);
            return Draw();
        })();
    }

    function ClearTimers() {
        clearTimeout(reactivationTimerHandler);
        clearTimeout(animationHandler);
    }

    function DeactivateConfetti() {
        confettiActive = false;
        ClearTimers();
    }

    function StopConfetti() {
        animationComplete = true;
        if (ctx == undefined) return;
        ctx.clearRect(0, 0, W, H);
    }

    function RestartConfetti() {
        ClearTimers();
        StopConfetti();
        reactivationTimerHandler = setTimeout(function () {
            confettiActive = true;
            animationComplete = false;
            InitializeConfetti();
        }, 100);

    }

    window.requestAnimFrame = (function () {
        return window.requestAnimationFrame ||
            window.webkitRequestAnimationFrame ||
            window.mozRequestAnimationFrame ||
            window.oRequestAnimationFrame ||
            window.msRequestAnimationFrame ||
            function (callback) {
                return window.setTimeout(callback, 1000 / 60);
            };
    })();
})();


$(document).ready(function () {
    function reAction() {
        $("#startButton").trigger("click");
        setTimeout(function () {
            $("#stopButton").trigger("click");
        }, 6000);
    }

    $(".header-make-button").on('click', function () {
        reAction();
    });
});

/* 알람 모달 */
let alarmModalCheck = false;

function showAlarmModal(modalMessage) {
    $("div#content-wrap").html(modalMessage);
    $("div.alarm-modal").css("animation", "popUp 0.5s");
    $("div.alarm-modal").css("display", "block").hide().fadeIn(500);
    alarmModalCheck = true;
}

function hideAlarmModal() {
    $("div.modal").fadeOut(500);
    $("div.alarm-modal").fadeOut(500);
    alarmModalCheck = false;
}

$(".alarm-modal-btn").on("click", function () {
    if (!alarmModalCheck) {
        let modalMessage = '';
        getFollowAlertList();
        showAlarmModal(modalMessage);
    } else {
        hideAlarmModal();
    }
});

// .alarm-modal-body 클래스와 .alarm-modal-btn 클래스를 가진 요소를 모두 선택합니다.
var alarmModalBodies = document.querySelectorAll('.alarm-modal-body');
var alarmModalBtns = document.querySelectorAll('.alarm-modal-btn');

// body 요소를 선택합니다.
var body = document.querySelector('body');

// body의 모든 자식 요소를 선택합니다.
var children = body.children;

// children 배열에서 .alarm-modal-body 클래스와 .alarm-modal-btn 클래스를 가진 요소를 제외한 모든 요소를 선택합니다.
var elements = [];
for (var i = 0; i < children.length; i++) {
    var child = children[i];
    if (!child.classList.contains('alarm-modal-body') && !child.classList.contains('alarm-modal-btn')) {
        elements.push(child);
    }
}

// 선택된 요소에 이벤트 리스너를 추가합니다.
elements.forEach(function (element) {
    element.addEventListener('click', function (event) {
        if (!event.target.closest('.alarm-modal-body') && !event.target.closest('.alarm-modal-btn')) {
            hideAlarmModal();
        }
    });
});

function formatDate(originalDate) {
    let date = new Date(originalDate);
    let formattedDate = date.toLocaleDateString("ko-KR", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit"
    });

    // 마지막 점 제거
    formattedDate = formattedDate.replace(/\.$/, "");

    return formattedDate;
}


if (memberDTO != null) {

    let date = new Date(memberDTO.memberRegisterDate);
    let formattedDate = date.toLocaleDateString("ko-KR", {
        year: "numeric",
        month: "2-digit",
        day: "2-digit"
    });

    // 마지막 점 제거
    formattedDate = formattedDate.replaceAll(".", "");
    formattedDate = formattedDate.replaceAll(" ", "");

    console.log(formattedDate)
    /*현재 날짜 계산*/

    Date.prototype.YYYYMMDD = function () {
        var yyyy = this.getFullYear().toString();
        var MM = pad(this.getMonth() + 1, 2);
        var dd = pad(this.getDate(), 2);
        return yyyy + MM + dd; //날짜 형식
    };

    function pad(number, length) {
        var str = '' + number;
        while (str.length < length) {
            str = '0' + str;
        }
        return str;
    }

    /*날짜 계산 */
    var nowDate = new Date();
    //console.log(nowDate); Mon Aug 16 2021 19:56:50 GMT+0900 (한국 표준시)
    console.log(nowDate.YYYYMMDD()); //현재 날짜 출력
    var now = nowDate.YYYYMMDD(); //현재 날짜
    var diffDate = now - formattedDate; //현재 날짜 -
    console.log(diffDate);

    document.getElementById("membership-days").textContent = diffDate + 1;

}

// 읽지 않은 알림이 있을 때 그림 바꾸기
function getCount() {
    // $.ajax({
    //     url: '/alert/follow/controller/unread', // 서버의 요청 경로
    //     type: 'GET',
    //     success: function (response) {
    //         var count = response;
    //         console.log("response : " + response);
    //         console.log("count : " + count);
    //         if (count === 0) {
    //             $('.new-alarm').hide();
    //         } else {
    //             $('.new-alarm').show();
    //         }
    //     },
    //     error: function (xhr, status, error) {
    //         console.log('Error:', error);
    //         console.log("에러메세지");
    //     }
    // });
}

// getCount 함수 호출
getCount();


// function getFollowAlertList() {
//     $.ajax({
//         url: '/alert/follow/controller/list', // 서버의 요청 경로
//         type: 'GET',
//         success: function (response) {
//             console.log("response : " + response);
//             $('.modal-alarm-btn-wrapper').empty();
//             appendFollowAlertList(response);
//         },
//         error: function (xhr, status, error) {
//             console.log('Error:', error);
//             console.log("에러메세지");
//         }
//     });
// }

function getFollowAlertList() {
    $.ajax({
        url: '/alert/follow/controller/list',
        type: 'GET',
        success: function (response) {
                console.log("alertReadStatus:", response.alert);
            $('.modal-alarm-btn-wrapper').empty();
            appendFollowAlertList(response);
        },
        error: function (xhr, status, error) {
            console.log('Error:', error);
            console.log("에러메세지");
        }
    });
}
// 헤더 빨간점 띄우기!!!
// $.ajax({
//     url: '/alert/follow/controller/read',
//     type: 'GET',
//     success: function(response) {
//         // 응답 처리 나를 팔로우하는 사람의 id값의 리스트를 보냄.(response = List<Long> uniqueIds)
//         console.log("리스포스능ㅁㄴ음ㄴㅇ : " + response);
//     },
//     error: function(error) {
//         // 오류 처리
//         console.error("오류요 : " + error);
//     }
// });
// $.ajax({
//     url: '/alert/follow/controller/read',
//     type: 'GET',
//     success: function (response) {
//         // 서버에서 받은 uniqueIds를 동적인 배열에 저장
//         console.log("response입니다, : " + response)
//         if (response == false) {
//             $('.new-alarm').show();
//         } else {
//             $('.new-alarm').hide();
//         }
//     },
//     error: function(error) {
//         // 오류 처리
//         console.error("오류요 : " + error);
//     }
// });



function appendFollowAlertList(alertResult) {
    let followText = '';
    alertResult.forEach(alert => {
        // const convertedCategory = convertCategory(category.eventCategory); // 영어 카테고리를 한글로 변환
        // const convertedTime = formatDate(category.parentsBoardUpdateDate);
        followText += ` 
                                    <button type="button" class="modal-alarm-btn">
                    <img src="https://cdn3.wadiz.kr/inbox/SYSTEM.png" class="message-image">
                    <div class="modal-alarm-content-wrapper">
                        <div class="modal-alarm-content-container">
                            <div class="modal-alarm-content-title">
                                <p>아기자기알림</p>
                            </div>
                            <div class="modal-alarm-content-time"></div>
                        </div>
                        <input class="alertId" type="hidden" value="${alert.id} style="display: none;">
                        <p class="modal-alarm-content-text"><span>${alert.memberNickname}</span>님이 나를 팔로우합니다!</p>
                    </div>
                </button>`
        ;
    });
    $('.modal-alarm-btn-wrapper').append(followText);
    // globalThis.page++;
}

