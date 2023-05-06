   
   
   $('.create').on('click', function(){
    let input = `
    <div class="input-wrapper">
    <select
      name="gender"
      id="option-select"
      class="option-select"
      style="width: 30%; margin-left: 10px"
    >
      <option value="">* 성별 선택</option>
      <option value="남">남자</option>
      <option value="여">여자</option>
    </select>
    <select
      name="age"
      id="option-select"
      class="option-select"
      style="width: 30%"
    >
      <option value="">* 나이 선택</option>
      <option value="5">5</option>
      <option value="6">6</option>
      <option value="7">7</option>
      <option value="8">8</option>
      <option value="9">9</option>
      <option value="10">10</option>
      <option value="11">11</option>
      <option value="12">12</option>
      <option value="13">13</option>
      <option value="14">14</option>
    </select>
    <input
      type="text"
      class="children-name"
      name="name"
      placeholder=" 아이의 이름을 입력해주세요"
      style="width: 30%; height: 18px"
    />
  </div>
`;
        $('.input-full-wrapper').append(input)
   })
