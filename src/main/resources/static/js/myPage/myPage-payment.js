  // const inputField = document.querySelector('.chosen-value');
  // const dropdown = document.querySelector('.value-list');
  // const dropdownArray = [... document.querySelectorAll('li')];
  // console.log(typeof dropdownArray)
  // dropdown.classList.add('open');
  // inputField.focus(); // Demo purposes only
  // let valueArray = [];
  // dropdownArray.forEach(item => {
  //   valueArray.push(item.textContent);
  // });
  //
  // const closeDropdown = () => {
  //   dropdown.classList.remove('open');
  // }
  //
  // inputField.addEventListener('input', () => {
  //   dropdown.classList.add('open');
  //   let inputValue = inputField.value.toLowerCase();
  //   let valueSubstring;
  //   if (inputValue.length > 0) {
  //     for (let j = 0; j < valueArray.length; j++) {
  //       if (!(inputValue.substring(0, inputValue.length) === valueArray[j].substring(0, inputValue.length).toLowerCase())) {
  //         dropdownArray[j].classList.add('closed');
  //       } else {
  //         dropdownArray[j].classList.remove('closed');
  //       }
  //     }
  //   } else {
  //     for (let i = 0; i < dropdownArray.length; i++) {
  //       dropdownArray[i].classList.remove('closed');
  //     }
  //   }
  // });
  //
  // dropdownArray.forEach(item => {
  //   item.addEventListener('click', (evt) => {
  //     inputField.value = item.textContent;
  //     dropdownArray.forEach(dropdown => {
  //       dropdown.classList.add('closed');
  //     });
  //   });
  // })
  //
  // inputField.addEventListener('focus', () => {
  //    inputField.placeholder = 'Type to filter';
  //    dropdown.classList.add('open');
  //    dropdownArray.forEach(dropdown => {
  //      dropdown.classList.remove('closed');
  //    });
  // });
  //
  // inputField.addEventListener('blur', () => {
  //    inputField.placeholder = 'Select state';
  //   dropdown.classList.remove('open');
  // });
  //
  // document.addEventListener('click', (evt) => {
  //   const isDropdown = dropdown.contains(evt.target);
  //   const isInput = inputField.contains(evt.target);
  //   if (!isDropdown && !isInput) {
  //     dropdown.classList.remove('open');
  //   }
  // });
  //


  let page = 0;
  const boardService = (() => {
    function getList(callback){
      $.ajax({
        url: `/mypage/payment/${page}`,
        type: 'post',
        data: JSON.stringify({page:page}),
        contentType: "application/json;charset=utf-8",
        success: function(purchaseDTOS){
          console.log("들어왓다")
          if (purchaseDTOS.content.length === 0) { // 불러올 데이터가 없으면
            console.log("막힘")
            $(window).off('scroll'); // 스크롤 이벤트를 막음
            return;
          }
          if(callback){
            callback(purchaseDTOS);
            console.log("들어왓다")
          }
        }
      });
      page++;
    }
    return {getList: getList};
  })();

  function appendList(purchaseDTOS) {
    let boardText3 = '';
    console.log(purchaseDTOS.content);
    purchaseDTOS.content.forEach(purchase => {
      console.log(purchase.id);
      let date = new Date(purchase.purchaseRegisterDate); // assuming eventLike.registerDate is a valid date string
      let formattedDate = date.getFullYear() + '-' + (date.getMonth() + 1).toString().padStart(2, '0') + '-' + date.getDate().toString().padStart(2, '0');

      boardText3 +=  `
                                         <li class="one-list">
                                <div class="list-inner">
                                <dl class="order-info">
                                <dd> 주문 번호 : ${purchase.id}</dd>
                            <dd style="text-align: right;"> 주문 일시 : ${formattedDate}</dd>
                            </dl>
                            
                            <div class="product-info">
                            
                                <div class="product-thumb-area">
                                     `
                        if(purchase.eventFileDTOS.length != 0) {
                                boardText3 += `
                            <div class="photo-thumbnail">
                                <img style="width: 100%; height: 100%;" src="/eventFiles/display?fileName=Event/${purchase.eventFileDTOS[0].filePath}/${purchase.eventFileDTOS[0].fileUUID}_${purchase.eventFileDTOS[0].fileOriginalName}">
                             </div>
                                `
                            }
                    boardText3 += `
                                </div>
                            
                                <dl class="product-info">
                                <dt class="product-name" style="display:none">상품명</dt>
                                <dd>
                                "${purchase.eventTitle}"
                                </dd>
                                </dl>
                                <div class="more-info">
                                <span>${purchase.memberName}</span>
                                <span class="more-text" onclick="location.href ='/mypage/payment/detail/${purchase.id}'">상세 보기 <span class="more-text-icon"> > </span></span>
                            </div>
                            
                            </div>
                            
                            
                            </div>
                            </li>
                          `
      ;
    });
    if (purchaseDTOS.content.length === 0) { // 불러올 데이터가 없으면
      console.log("막힘?")
      $(window).off('scroll'); // 스크롤이벤트 x
    }
    $('.order-lists').append(boardText3);
  }

  boardService.getList(function(purchaseDTOS) {
    page = 0;
    console.log(purchaseDTOS.content);
    appendList(purchaseDTOS);
    console.log(page + "페이지 로딩 시 초기화면")
  });

  console.log("sadasdasd");

  $(window).scroll(function() {
    if($(window).scrollTop() + $(window).height() > $(document).height() * 0.9) {
      page++;
      boardService.getList(appendList);
      console.log(page)
    }
  });



  /*-----------*/














