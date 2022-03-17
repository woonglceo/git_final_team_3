
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
			
			$('.shopDetail-top_Realimg').attr('src', IMG_SRC + data.shopDTO.img1);
			$('.product_name').text(data.shopDTO.productName);
			$('.product_name_translate').text(data.shopDTO.productNameKor);
			$('.product_modelId').text(data.shopDTO.modelId);
			
			$('.shopDetail-content').append(
				$('<h3/>', {text:'제품 사이즈: '+userProductSize}),
				$('<dl/>', {}).append(
					$('<dt/>', {text:'판매 희망가'}),
					$('<dd/>', {text:userInputPrice + '원'})
				)
			);
			$('.shopDetail-content2').append(
				$('<dl/>', {}).append(
					$('<dt/>', {text:'검수비'}),
					$('<dd/>', {text:'-'})
				),
				$('<dl/>', {}).append(
					$('<dt/>', {text:'판매 수수료'}),
					$('<dd/>', {text:'-'})
				),
				$('<dl/>', {}).append(
					$('<dt/>', {text:'배송비'}),
					$('<dd/>', {text:'-'})
				)
				/*
				$('<ul/>', {}).append(
					$('<li/>', {}).append($('<div/>', {text:'검수비', id:'p1'})),
					$('<li/>', {}).append($('<div/>', {text:'판매 수수료', id:'p2'})),
					$('<li/>', {}).append($('<div/>', {text:'배송비', id:'p3'}))
				)
				*/
			);
			$('.shopDetail-content3').append(
				/*
				$('<h3/>', {text:'총 정산금액: '+($('#p1').val()
											  +$('#p2').val()
											  +$('#p3').val()
											  +userInputPrice).toLocaleString() + '원'}),
				*/
				$('<dl/>', {}).append(
					$('<dt/>', {text:'총 정산금액'}),
					$('<dd/>', {text:'-'})
				),
				$('<input/>', {type:'button', class:'continue-sell-button sell-page2', value:'판매 계속'})
			);
		},
		error: function(err) {
	        alert("페이지 에러났습니다");
	        console.log(err);
	    }
		
	});//end ajax
	
});//end onload

$(document).on("click", ".sell-page2", function(){
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
			console.log(data);
			$('.shopDetail-content').empty();
			$('.shopDetail-content2').empty();
			$('.shopDetail-content3').empty();
			
			$('.shopDetail-content').append(
				$('<div/>', {}).append(
					$('<h3/>', {text:'판매 정산 계좌'})
				),
				$('<div/>', {}).append(
					$('<p/>', {text:'계좌를 선택해주세요.'}).append(
						$('<input>', {type:'button', value:'계좌 선택', class:'continue-sell-button btn-sm view-account-btn'})
					)
				)
			)//end shopDetail-content
			
			$('.shopDetail-content2').append(
				$('<div/>', {class:''}).append(
					$('<h3/>', {text:'반송 주소'}).append(
						$('<input>', {type:'button', value:'주소 보기', class:'continue-sell-button btn-sm view-address-btn'})
					)
				),
				$('<div/>', {class:''}).append(
					$('<h3/>', {text:'발송 방법'}),
					$('<div/>', {class:''})
				)
			)//end shopDetail-content2
			
			$('.shopDetail-content3').append(
				$('<div/>', {}).append(
					$('<h3/>', {text:'최종 주문 정보'}),
					$('<div/>', {class:''})
				)
			)//end shopDetail-content3
			
			$('.body').append(
				$('<div/>', {class:'shopDetail-content4'}).append(
					$('<dl/>', {}).append(
						$('<dt/>', {text:'총 정산금액'}),
						$('<dd/>', {text:'-'})
					),
					$('<input/>', {type:'button', class:'continue-sell-button wrap-up-sell', value:'판매하기'})
				)
			)
		},
		error: function(err) {
	        alert("넘어가다가 페이지 에러났습니다");
	        console.log(err);
	    }
	});//end ajax
	
});//end click

