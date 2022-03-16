
var productId = new URLSearchParams(location.search).get("productId");
var userInputPrice = new URLSearchParams(location.search).get("userInputPrice");
var userProductSize = new URLSearchParams(location.search).get("userProductSize");
let IMG_SRC = "/shoeCream/resources/images/productImg/";

$(function(){
	//location.search 는 url에 '?' 뒤로 다 짤라온다.
	
	$.ajax({
		type: 'POST',
		url: '/shoeCream/shop/getShop',
		data: {productId: productId},
		success: function(data){
			console.log('data', data);
			$('.shopDetail-content').append(
				$('<h3/>', {text:'제품 사이즈: '+userProductSize}),
				$('<h3/>', {text:'판매 희망가: '+userInputPrice+'원'})
			);
			$('.shopDetail-content2').append(
				$('<ul/>', {}).append(
					$('<li/>', {}).append($('<div/>', {text:'검수비', id:'p1'})),
					$('<li/>', {}).append($('<div/>', {text:'판매 수수료', id:'p2'})),
					$('<li/>', {}).append($('<div/>', {text:'배송비', id:'p3'}))
				)
			);
			$('.shopDetail-content3').append(
				$('<h3/>', {text:'총 정산금액: '+($('#p1').val()
											  +$('#p2').val()
											  +$('#p3').val()
											  +userInputPrice).toLocaleString() + '원'}),
				$('<input/>', {type:'button', class:'continue-sell-button', value:'판매 계속'})
			);
		},
		error: function(err) {
	        alert("페이지 에러났습니다");
	        console.log(err);
	    }
		
	});//end ajax
	
});//end onload

$(document).on("click", ".continue-sell-button", function(){
	alert('다음페이지로 넘어갑니다');
	
	$.ajax({
		type: 'POST',
		url: '/shoeCream/shop/getShopSalesView',
		data: {
			productId: productId,
			userInputPrice: userInputPrice,
			userProductSize: userProductSize
		},
		success: function(data){
			$('.shopDetail-content').empty();
			$('.shopDetail-content2').empty();
			$('.shopDetail-content3').empty();
		},
		error: function(err) {
	        alert("페이지 에러났습니다");
	        console.log(err);
	    }
	});//end ajax
	
});//end click

