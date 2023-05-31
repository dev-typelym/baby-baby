var openKey = '52526378646672653138734456724a';

$(document).ready(function(){
    $(".lecture-type-buttons li").click(function(){
        $(".lecture-type-buttons li").removeClass("selected");
        $(this).addClass("selected");
    });
});

function bannerEvent(){
    /* 마우스오버 이벤트 */
    $('.instance').mouseover(function(e) {
        $(this).find('.next').show(); // 우측 지도 버튼
        $(this).find('.next').css('display', 'block');
    }).mouseout(function(e) {
        $(this).find('.next').hide();
    });

    /* 클릭 이벤트 */
    const $nextBtn = $('.next');
    $nextBtn.click(function(e) {
        const $hideTarget = $(this).parent().parent().parent();
        const $showTarget = $hideTarget.next();
        $hideTarget.css('opacity', 0);
        $showTarget.css('opacity', 1);
    });

    const $prevBtn = $('.prev');
    $prevBtn.click(function(e) {
        const $hideTarget = $(this).parent();
        const $showTarget = $hideTarget.prev('.culture-space');
        $hideTarget.css('opacity', 0);
        $showTarget.css('opacity', 1);
    });

}

function mapEvent(code_x, code_y, index){
        console.log("들어옴")
        var mapContainer = document.getElementById('map' + (index+1)); // 지도를 표시할 div
        console.log(mapContainer)
        var mapOption = {
            center: new kakao.maps.LatLng(code_x, code_y), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

        // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
        var map = new kakao.maps.Map(mapContainer, mapOption);
        var markerPosition = new kakao.maps.LatLng(code_x, code_y);
        var marker = new kakao.maps.Marker({
            position: markerPosition
        });
        marker.setMap(map);
}

const categoryService = (() => {

    function getCultureSpace(callback){
        $.ajax({
            url: 'http://openapi.seoul.go.kr:8088/'+openKey+'/json/culturalSpaceInfo/1/100/',
            type: 'GET',
            dataType: 'json',
            success: function (result) {
                if (callback) {
                    callback(result);
                }
            }
        });
    }

    function getEtcList(callback){
        console.log("ajax")
        $.ajax({
            url: 'http://openapi.seoul.go.kr:8088/'+openKey+'/json/culturalEventInfo/1/100/',
            type: 'GET',
            dataType: 'json',
            success: function (result) {
                console.log("success")
                if (callback) {
                    callback(result);
                }
            }
        });
    }

    function getAgricultureList(callback){
        console.log("ajax")
        $.ajax({
            url: 'http://openapi.seoul.go.kr:8088/'+openKey+'/json/vNtcnNgc001/1/100/',
            type: 'GET',
            dataType: 'json',
            success: function (result) {
                console.log("success")
                if (callback) {
                    callback(result);
                }
            }
        });
    }

    function getSportsList(callback){
        console.log("ajax")
        $.ajax({
            url: 'http://openAPI.seoul.go.kr:8088/'+openKey+'/json/ListPublicReservationSport/1/100/',
            type: 'GET',
            dataType: 'json',
            success: function (result) {
                console.log("success")
                if (callback) {
                    callback(result);
                }
            }
        });
    }

    function getMuseumList(callback){
        console.log("ajax")
        $.ajax({
            url: 'http://openapi.seoul.go.kr:8088/'+openKey+'/json/SebcMuseumInfoKor2/1/100/',
            type: 'GET',
            dataType: 'json',
            success: function (result) {
                console.log("success")
                if (callback) {
                    callback(result);
                }
            }
        });
    }

    function getGalleryList(callback){
        console.log("ajax")
        $.ajax({
            url: 'http://openapi.seoul.go.kr:8088/'+openKey+'/json/ListExhibitionOfSeoulMOAInfo/1/100/',
            type: 'GET',
            dataType: 'json',
            success: function (result) {
                console.log("success")
                if (callback) {
                    callback(result);
                }
            }
        });
    }

    function getCraftList(callback){
        console.log("ajax")
        $.ajax({
            url: 'http://openapi.seoul.go.kr:8088/'+openKey+'/json/ViewShpLecture/1/100/',
            type: 'GET',
            dataType: 'json',
            success: function (result) {
                console.log("success")
                if (callback) {
                    callback(result);
                }
            }
        });
    }

    function getTraditionalList(callback){
        console.log("ajax")
        $.ajax({
            url: 'http://openapi.seoul.go.kr:8088/'+openKey+'/json/ViewShpMapMst/1/100/',
            type: 'GET',
            dataType: 'json',
            success: function (result) {
                console.log("success")
                if (callback) {
                    callback(result);
                }
            }
        });
    }

    return {getCultureSpace : getCultureSpace, getEtcList : getEtcList, getAgricultureList : getAgricultureList,
                getSportsList : getSportsList, getMuseumList : getMuseumList, getGalleryList : getGalleryList,
                getTraditionalList : getTraditionalList, getCraftList : getCraftList}
})();


function appendCultureSpace(result) {
    let text = '';
    result.culturalSpaceInfo.row.forEach(function(center, index) {
        text +=`
    <div class="instance imgBox">
            <div class="culture-space img on">
            <img class="background" src="${center.MAIN_IMG}">
            <div class="space-info">
            <img class="photo-img" src="${center.MAIN_IMG}">
            <div class="space-text-info">
            <div class="space-name-small"><span><strong>${center.FAC_NAME}</strong></span></div>
        <div class="space-name-category"><span>${center.SUBJCODE}</span></div>
        <div class="space-address"><span>${center.ADDR}</span></div>
        <div class="space-tel"><span>${center.PHNE}</span></div>
        </div>
        <div class="arrow-button">
            <img class="next" src="/images/play/toTheMap.png">
            </div>
            </div>
            </div>
            <div class="img left-arrow-class">
            <img class="prev" src="/images/play/backToInfo.png">
            <div id="map${index + 1}" style="width:350px;height:350px;">${center.X_COORD},${center.Y_COORD}</div>
        </div>
        </div>`
        ;
        console.log(center.X_COORD);
        console.log(center.Y_COORD);
        console.log(index);
    });
    $('.culture-space-instance').html("");
    $('.culture-space-instance').html(text);
    result.culturalSpaceInfo.row.forEach(function(center, index){
        mapEvent(center.X_COORD,center.Y_COORD, index);
    });
    bannerEvent();
};

function appendEtcList(result) {
    let text = '';
    result.culturalEventInfo.row.forEach(function (center) {
        text += `
    <div class="instance imgBox">
       <div class="culture-space img on">
            <img class="background" src="${center.MAIN_IMG}">
            <div class="space-info">
                <img class="photo-img" src="${center.MAIN_IMG}">
                <div class="space-text-info">
                    <div class="space-name-small"><span><strong>${center.TITLE}</strong></span></div>
                    <div class="space-name-category"><span>${center.CODENAME}</span></div>
                    <div class="space-tel"><span>${center.GUNAME}</span></div>
                    <div class="space-address"><span>${center.PLACE}</span></div>
                    <div class="space-tel site-url" onclick="window.open('${center.ORG_LINK}', '_blank')"><span>사이트 바로가기</span></div>
                    <div class="space-tel"><span>${center.USE_TRGT}</span></div>
                    <div class="space-tel"><span>${center.DATE}</span></div>
                </div>
            </div>
       </div>
    </div>`
        ;
        $('.culture-space-instance').html("");
        $('.culture-space-instance').html(text);
    });
}

function appendAgricultureList(result) {
    let text = '';
    result.vNtcnNgc001.row.forEach(function (center) {
        text += `
    <div class="instance imgBox">
       <div class="culture-space img on">
            <img class="background" src="/images/play/agriculture.jpg">
            <div class="space-info">
                <img class="photo-img" src="/images/play/agriculture.jpg">
                <div class="space-text-info">
                    <div class="space-name-small"><span><strong>${center.SJ}</strong></span></div>
                    <div class="space-name-category"><span>${center.ATDRC_CODE}</span></div>
                    <div class="space-tel"><span>${center.RDNMADR}</span></div>
                    <div class="space-address"><span>${center.ADRES_DETAIL}</span></div>
                    <div class="space-tel site-url" onclick="window.open('${center.HMPG_ADRES}', '_blank')"><span>사이트 바로가기</span></div>
                    <div class="space-tel"><span>${center.REQST_STTUS_CODE}</span></div>
                </div>
            </div>
       </div>
    </div>`
        ;
        $('.culture-space-instance').html("");
        $('.culture-space-instance').html(text);
    });
}

function appendSportsList(result) {
    let text = '';
    result.ListPublicReservationSport.row.forEach(function(center, index) {
        text +=`
    <div class="instance imgBox">
            <div class="culture-space img on">
            <img class="background" src="${center.IMGURL}">
            <div class="space-info">
            <img class="photo-img" src="${center.IMGURL}">
            <div class="space-text-info">
            <div class="space-name-small"><span><strong>${center.MAXCLASSNM}</strong></span></div>
        <div class="space-name-category"><span>${center.MAXCLASSNM}</span></div>
        <div class="space-address"><span>${center.AREANM}</span></div>
        <div class="space-address"><span>${center.TELNO}</span></div>
        <div class="space-tel site-url" onclick="window.open('${center.SVCURL}', '_blank')"><span>사이트 바로가기</span></div>
        </div>
        <div class="arrow-button">
            <img class="next" src="/images/play/toTheMap.png">
            </div>
            </div>
            </div>
            <div class="img left-arrow-class">
            <img class="prev" src="/images/play/backToInfo.png">
            <div id="map${index + 1}" style="width:350px;height:350px;">${center.X},${center.Y}</div>
        </div>
        </div>`
        ;
    });
    $('.culture-space-instance').html("");
    $('.culture-space-instance').html(text);
    result.ListPublicReservationSport.row.forEach(function(center, index){
        mapEvent(center.X,center.Y, index);
    });
    bannerEvent();
};

function appendMuseumList(result) {
    let text = '';
    result.SebcMuseumInfoKor2.row.forEach(function (center) {
        text += `
    <div class="instance imgBox">
       <div class="culture-space img on">
            <img class="background" src="/images/play/museum.png">
            <div class="space-info">
                <img class="photo-img" src="/images/play/museum.png">
                <div class="space-text-info">
                    <div class="space-name-small"><span><strong>${center.NAME_KOR}</strong></span></div>
                    <div class="space-name-category"><span>${center.CATE2_NAME}</span></div>
                    <div class="space-tel"><span>${center.GUNAME}</span></div>
                    <div class="space-address"><span>${center.H_KOR_CITY} ${center.H_KOR_GU} ${center.H_KOR_DONG}</span></div>
                </div>
            </div>
       </div>
    </div>`;
        $('.culture-space-instance').html("");
        $('.culture-space-instance').html(text);
    });
}
function appendGalleryList(result) {
    let text = '';
    result.ListExhibitionOfSeoulMOAInfo.row.forEach(function(center, index) {
        text +=`
    <div class="instance imgBox">
            <div class="culture-space img on">
            <img class="background" src="${center.DP_MAIN_IMG}">
            <div class="space-info">
            <img class="photo-img" src="${center.DP_MAIN_IMG}">
            <div class="space-text-info">
            <div class="space-name-small"><span><strong>${center.DP_NAME}</strong></span></div>
            <div class="space-name-small"><span><strong>${center.DP_ARTIST}</strong></span></div>
            <div class="space-name-category"><span>${center.DP_ART_PART}</span></div>
            <div class="space-address"><span>${center.DP_PLACE}</span></div>
            <div class="space-tel site-url" onclick="window.open('${center.DP_LNK}', '_blank')"><span>사이트 바로가기</span></div>
        </div>
        <div class="arrow-button">
            <img class="next" src="/images/play/toTheMap.png">
            </div>
            </div>
            </div>
            <div class="img left-arrow-class">
            <img class="prev" src="/images/play/backToInfo.png">
            <div id="map${index + 1}" style="width:350px;height:350px;"></div>
        </div>
        </div>`
        ;
    });
    $('.culture-space-instance').html("");
    $('.culture-space-instance').html(text);
    result.ListExhibitionOfSeoulMOAInfo.row.forEach(function(center, index){
        if(center.DP_PLACE == "서울시립미술관 서소문본관"){
            mapEvent(37.56404814003919,126.9737209550975, index);
        }else if(center.DP_PLACE == "서울시립 북서울미술관"){
            mapEvent(37.64066136033519,127.0668575355187, index);
        }else {
            mapEvent(37.476073542121625,126.97947851821328, index);
        }

    });
    bannerEvent();
};

function appendCraftList(result) {
    let text = '';
    result.ViewShpLecture.row.forEach(function(center, index) {
        text +=`
    <div class="instance imgBox">
            <div class="culture-space img on">
            <img class="background" src="/images/play/craft.jpg">
            <div class="space-info">
            <img class="photo-img" src="/images/play/craft.jpg">
            <div class="space-text-info extra-work">
            <div class="space-name-small"><span><strong>${center.SLM_NAME}</strong></span></div>
        <div class="space-address"><span>${center.SLM_DESC}</span></div>
        </div>
        <div class="arrow-button">
            <img class="next" src="/images/play/toTheMap.png">
            </div>
            </div>
            </div>
            <div class="img left-arrow-class">
            <img class="prev" src="/images/play/backToInfo.png">
            <div id="map${index + 1}" style="width:350px;height:350px;">${center.SLM_LATITUDE},${center.SLM_LONGITUDE}</div>
        </div>
        </div>`
    });
    $('.culture-space-instance').html("");
    $('.culture-space-instance').html(text);
    result.ViewShpLecture.row.forEach(function(center, index){
        mapEvent(center.SLM_LATITUDE,center.SLM_LONGITUDE, index);
    });
    bannerEvent();
};


function appendTraditionalList(result) {
    let text = '';
    result.ViewShpMapMst.row.forEach(function(center, index) {
        text +=`
    <div class="instance imgBox">
            <div class="culture-space img on">
            <img class="background" src="/images/play/traditional.jpg">
            <div class="space-info">
            <img class="photo-img" src="/images/play/traditional.jpg">
            <div class="space-text-info">
            <div class="space-name-small"><span><strong>${center.SMM_NAME}</strong></span></div>
        <div class="space-address"><span>${center.SMM_ADDR}</span></div>
        <div class="space-tel site-url" onclick="window.open('${center.SMM_HOMEPAGE}', '_blank')"><span>사이트 바로가기</span></div>
        </div>
        <div class="arrow-button">
            <img class="next" src="/images/play/toTheMap.png">
            </div>
            </div>
            </div>
            <div class="img left-arrow-class">
            <img class="prev" src="/images/play/backToInfo.png">
            <div id="map${index + 1}" style="width:350px;height:350px;">${center.SMM_LATITUDE},${center.SMM_LONGITUDE}</div>
        </div>
        </div>`
        ;
    });
    $('.culture-space-instance').html("");
    $('.culture-space-instance').html(text);
    result.ViewShpMapMst.row.forEach(function(center, index){
        mapEvent(center.SMM_LATITUDE,center.SMM_LONGITUDE, index);
    });
    bannerEvent();
};

categoryService.getCultureSpace(
    function(result){appendCultureSpace(result);}
)

// categoryService.getEtcList(
//     function(result){appendEtcList(result);}
// )

function getList(num){
    switch(num){
        case 1:
            categoryService.getCultureSpace(
                function(result){appendCultureSpace(result);}
            )
            break;
        case 3:
            categoryService.getSportsList(
                function(result){appendSportsList(result);}
            )
            break;
        case 4:
            categoryService.getTraditionalList(
                function(result){appendTraditionalList(result);}
            )
            break;
        case 5:
            categoryService.getMuseumList(
                function(result){appendMuseumList(result);}
            )
            break;
        case 6:
            categoryService.getAgricultureList(
                function(result){appendAgricultureList(result);}
            )
            break;
        case 7:
            categoryService.getGalleryList(
                function(result){appendGalleryList(result);}
            )
            break;
        case 8:
            categoryService.getCraftList(
                function(result){appendCraftList(result);}
            )
            break;
        case 9:
            console.log("click")
            categoryService.getEtcList(
                function(result){appendEtcList(result);}
            )
            break;
        default:
            $('.culture-space-instance').html("");
            $('.culture-space-instance').html("불러 올 내용이 없습니다.");
            break;
    }
}


