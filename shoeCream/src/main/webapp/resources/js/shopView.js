$(function () {
  let IMG_SRC = "/shoeCream/resources/images/productImg/";

  //location.search 는 url에 '?' 뒤로 다 짤라온다.
  var pId = new URLSearchParams(location.search).get("productId");

  $.ajax({
    type: "POST",
    url: "/shoeCream/shop/getShop",
    data: { productId: pId },
    //dataType: 'JSON',
    success: function (data) {
      console.log(data);

      $(".fluctuation-decrease-increse").empty();

      $(".product-brand").text(data.brandname);
      $("#productImg").attr("src", IMG_SRC + data.img1);
      $(".shopDetail-top_Realimg").attr("src", IMG_SRC + data.img1);
      $(".procut-title").text(data.productName);
      $(".product-subtitle").text(data.productNameKor);
      $(".produxt-price-detail").text(data.todaysPrice.toLocaleString());
      $(".like-count").text(data.wishListCount);
      $(".model-number").text(data.modelId);
      $(".model-release-date").text(data.releaseDate);
      $(".model-color").text("컬러 안할꺼임");
      $(".model-release-price").text(data.releasePrice.toLocaleString() + "원");
      //$('.').text(data.);
    },
    error: function (err) {
      alert("샵뷰 페이지 에러났습니다");
      console.log(err);
    },
  }); //end ajax
}); //end onload

/* 서림씨코드 */
const box = document.getElementById("shop-detail-top");
const buttonBox = document.querySelector(".shopDetail-content-button2");
const buttonNum = -20;
window.addEventListener("scroll", scrollEvent);

function scrollEvent() {
  const text = buttonBox.getBoundingClientRect().top;

  if (text < buttonNum) box.classList.remove("tophidden");
  else box.classList.add("tophidden");
}

// 더보기더보기더보기

const showBox = document.querySelectorAll(".drop-down-head");
// for (let i = 0; i < showBox.length; i++) {
//   showBox[i].addEventListener("click", (e) => console.log(e.target));
// }

showBox.forEach((item) => {
  item.addEventListener("click", (e) => {
    const sibling = e.currentTarget.nextElementSibling;
    sibling.classList.toggle("drop-down-content");
    e.currentTarget.classList.toggle("add-bold");
  });
});

// 모달 오픈(구매)
$('.shopDetail-buy-link').click(function(){	
	$('.modal-title-text').empty();
	$('.modal-content').empty();
	$('.modal-footer').empty();
	$('#modal-content-warning-div').empty();
	
	$('.modal').css('display', 'flex');
	$('body').css('overflow', 'hidden'); //모달창 켜졌을때 스크롤 금지
	
	$('.modal-title-text').text('구매하기');	// 슈크림과 거래시: 1000원, 회원과 거래시: 500원.
	$('.modal-content').append($('<div/>', {text:'오늘의 시세는: '+$('.produxt-price-detail').text()+' 원', class:'modal-content-price-div'}));
	//$('.modal-content').append($('<div/>', {text:'판매가를 입력하세요:'}));
	$('.modal-content').append(
		$('<div/>', {class:'modal-content-input-div'}).append(
			$('<select>', {class:'modal-content-input-select'}).append(
				$('<option/>', {value:'-1', text:'사이즈 선택'})
			  , $('<option/>', {value:'225', text:'225'})	
			  , $('<option/>', {value:'230', text:'230'})	
			  , $('<option/>', {value:'235', text:'235'})	
			  , $('<option/>', {value:'240', text:'240'})	
			  , $('<option/>', {value:'245', text:'245'})	
			  , $('<option/>', {value:'250', text:'250'})	
			  , $('<option/>', {value:'255', text:'255'})	
			  , $('<option/>', {value:'260', text:'260'})	
			  , $('<option/>', {value:'265', text:'265'})	
			  , $('<option/>', {value:'270', text:'270'})
			  , $('<option/>', {value:'275', text:'275'})	
			  , $('<option/>', {value:'280', text:'280'})	
			  , $('<option/>', {value:'285', text:'285'})	
			  , $('<option/>', {value:'290', text:'290'})
	)));
	$('.modal-content').append($('<div/>', {id:'modal-content-warning-div', text:'잘못된 값 입니다.'}));
	$('.modal-footer').append($('<input>', {type:'button', value:'상품 구매', class:'modal-content-btn purchase', id:'purchaseBtn'}));
});//end click

// 모달 오픈(판매)
$('.shopDetail-cell-link').click(function(){
	$('.modal-title-text').empty();
	$('.modal-content').empty();
	$('.modal-footer').empty();
	$('#modal-content-warning-div').empty();
	
	$('.modal').css('display', 'flex');
	$('body').css('overflow', 'hidden'); //모달창 켜졌을때 스크롤 금지
	
	$('.modal-title-text').text('판매하기');
	$('.modal-content').append($('<div/>', {text:'오늘의 시세는: '+$('.produxt-price-detail').text()+' 원', class:'modal-content-price-div'}));
	//$('.modal-content').append($('<div/>', {text:'판매가를 입력하세요:'}));
	$('.modal-content').append(
		$('<div/>', {class:'modal-content-input-div'}).append(
			$('<input>', {type:'text', placeholder:'판매가 입력', class:'modal-content-input-price'})
		  , $('<select>', {class:'modal-content-input-select'}).append(
				$('<option/>', {value:'-1', text:'사이즈 선택'})
			  , $('<option/>', {value:'225', text:'225'})	
			  , $('<option/>', {value:'230', text:'230'})	
			  , $('<option/>', {value:'235', text:'235'})	
			  , $('<option/>', {value:'240', text:'240'})	
			  , $('<option/>', {value:'245', text:'245'})	
			  , $('<option/>', {value:'250', text:'250'})	
			  , $('<option/>', {value:'255', text:'255'})	
			  , $('<option/>', {value:'260', text:'260'})	
			  , $('<option/>', {value:'265', text:'265'})	
			  , $('<option/>', {value:'270', text:'270'})
			  , $('<option/>', {value:'275', text:'275'})	
			  , $('<option/>', {value:'280', text:'280'})	
			  , $('<option/>', {value:'285', text:'285'})	
			  , $('<option/>', {value:'290', text:'290'})
	)));
	$('.modal-content').append($('<div/>', {id:'modal-content-warning-div', text:'잘못된 값 입니다.'}));
	$('.modal-footer').append($('<input>', {type:'button', value:'상품 판매', class:'modal-content-btn sell', id:'sellBtn'}));
});//end click

// 모달 클로즈 (x버튼 클릭)
$(".close-area").click(function () {
  $(".modal").css("display", "none");
  $("body").css("overflow", "auto");
});

$(document).on("click", "#purchaseBtn", function(){
	//alert('구매 버튼 클릭 ' + $('.modal-content-input-select').val());
	alert( $('.modal-content-input-select').val());
	if($('.modal-content-input-select').val() == -1){
		$('#modal-content-warning-div').text('잘못된 값 입니다.');
	}
});

$(document).on("click", "#sellBtn", function(){
	//alert('판매 버튼 클릭 ' + $('.modal-content-input-price').val() + $('.modal-content-input-select').val());
	alert( $('.modal-content-input-select').val());
	if($('.modal-content-input-select').val() == -1){
		$('#modal-content-warning-div').text('잘못된 값 입니다.');
	}
});
/*	
// 모달 클로즈 (어두운 배경 클릭) 
$('.modal').click(function(){
	$('.modal').css('display', 'none');
	$('body').css('overflow', 'auto'); //모달 꺼지면 스크롤 가능
});
*/
