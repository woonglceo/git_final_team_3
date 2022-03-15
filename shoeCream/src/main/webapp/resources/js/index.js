console.log("10");

const productWrap = document.querySelector(".product_wrap");
const addBtn = document.querySelector(".add_btn");
	
/*
	//getProductList
	$.ajax({
		type: 'POST',
		url: '/shoeCream/get/product/getProductListForIndex',
		data: 'pg='+1,	
		//dataType: 'JSON',
		success: function(data){
			console.log(data);
			
		},
		error: function(err){
			alert('상품 에러났습니다');
			console.log(err);
		}
	});//end ajax
	*/

//스타일

//스타일

window.onload = function () {
  $.ajax({
    type: "POST",
    url: "/shoeCream/get/event/getEventList",
    data: {
      pg: "1",
      option: "going",
    },
    //dataType: 'JSON',
    success: function (data) {
      // console.log(data);
    },
    error: function (err) {
      alert("이벤트 에러났습니다");
      console.log(err);
    },
  }); //end ajax

  $.ajax({
    type: "POST",
    url: "/shoeCream/style/getPopularList",
    data: "pg=" + 1,
    //dataType: 'JSON',
    success: function (styleData) {
      console.log(styleData);
      // console.log(styleData[0].price);
      makeStyleList(styleData);
    },
    error: function (err) {
      alert("스타일 에러났습니다");
      console.log(err);
    },
  }); //end ajax

  fetch("/shoeCream/get/product/getproductList", {
    method: "POST",
  })
    .then((response) => response.json())
    .then((productData) => {
      // console.log(productData);

      makeProduct(productData);
    }); //패치는 서림이 코드
};

function makeProduct(data) {
  data.list.forEach((datas) => {
    // console.log(typeof datas);

    productWrap.innerHTML += `<div class="product_list">
    <div class="product_list-img"></div>
    <div class="product_list-infobox">
      <div class="brand-text"></div>
      <p class="product_name"></p>
      <div class="amount-lg">
        <em class="num">1000</em><span class="won">원</span>
      </div>
    </div>
  </div>`;

    const brandName = document.querySelector(".brand-text");
    brandName.innerText = datas.modelId;
  });
}

const styleWrap = document.querySelectorAll(".style_wrap-imgbox");
const styleUserImg = document.querySelectorAll(".style-uesr-img");
const styleUserId = document.querySelectorAll(".style-user-id");
console.log(styleWrap);
console.log(typeof (styleUserId));

function makeStyleList(data) {
  userArr = [];

  data.forEach((datas) => {
    userArr.push(datas.username);
  });

  for (let i = 0; i < userArr.length; i++) {
    styleUserId[i].innerText = userArr[i];
    // styleUserId[i].innerText = userArr[i].toString;
  }
}
