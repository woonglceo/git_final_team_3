console.log("1");

const productWrap = document.querySelector(".product_wrap");
const addBtn = document.querySelector(".add_btn");
const wrap = document.querySelector(".product_wrap");

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
  let count = 1;
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
      makeStyleList(styleData);
    },
    error: function (err) {
      alert("스타일 에러났습니다");
      console.log(err);
    },
  }); //end ajax

  fetch(`/shoeCream/get/product/getProductListForIndex?pg=${count}`, {
    method: "POST",
  })
    .then((response) => response.json())
    .then((productData) => {
      console.log(productData);

      makeProduct(productData);
      count++;
      addBtn.addEventListener("click", () => makeProduct(productData,event));
    }); //패치는 서림이 코드
};

function makeProduct(data) {
  data.forEach((datas) => {
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

    if (wrap.childElementCount == 16) {
      console.log("인제끝..");
      addBtn.style.display = "none";
    }
  });
}

const styleWrap = document.querySelectorAll(".style_wrap-imgbox");
const styleUserImg = document.querySelectorAll(".style-uesr-img");
const styleUserId = document.querySelectorAll(".style-user-id");

function makeStyleList(data) {
  styleUserId.forEach((datas, idx) => {
    datas.innerText = `@${data[idx].username}`;
  });
}
