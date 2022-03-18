
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
			
			//let IMG_SRC = "/shoeCream/resources/images/productImg/";
			$('.shopDetail-top_Realimg').attr('src', IMG_SRC + data.shopDTO.img1);
			$('.product_modelId').text(data.shopDTO.modelId);
			$('.product_name').text(data.shopDTO.productName);
			$('.product_name_translate').text(data.shopDTO.productNameKor);
			
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


//주소보기 모달 창 
$(document).on("click", '.view-address-btn', function(){
	$('.layer_delivery .title').text('새 주소 추가');
	$('.layer').css('display', 'flex');
	$('body').css('overflow', 'auto');
	
	/*
	$('.layer_content').empty();
	
	$('.layer_content').append(
		$('<div/>', {class:'delivery_bind'}).append(
			$('<div/>', {class:'delivery_input'}).append(
				$('<div/>', {class:'input_box'}).append(
					$('<h5/>', {class:'input_title', text:'수령인'}),
					$('<input>', {class:'input_txt', type:'text', id:'input_recipient', autocomplete:'off', placeholder:'수령인의 이름'}),
					$('<p/>', {class:'input_err', text:'올바른 이름을 입력해주세요. (2~4자)'})
				),
				$('<div/>', {class:'input_box'}).append(
					$('<h5/>', {class:'input_title', text:'우편번호'}),
					$('<input>', {class:'input_txt', type:'text', id:'input_zipcode', autocomplete:'off', placeholder:'우편 번호를 검색하세요', readonly:'true'}),
					$('<a/>', {class:'btn zipcode_btn', href:"#", text:'우편번호'})
				),
				$('<div/>', {class:'input_box'}).append(
					$('<h5/>', {class:'input_title', text:'주소'}),
					$('<input>', {class:'input_txt', type:'text', id:'input_addr1', autocomplete:'off', placeholder:'우편 번호 검색 후, 자동입력 됩니다', readonly:'true'})
				),
				$('<div/>', {class:'input_box'}).append(
					$('<h5/>', {class:'input_title', text:'상세 주소'}),
					$('<input>', {class:'input_txt', type:'text', id:'input_addr2', autocomplete:'off', placeholder:'건물, 아파트, 동/호수 입력'})
				)
			),
			$('<div/>', {class:'delivery_check'}).append(
				$('<div/>', {class:'checkbox_item'}).append(
					$('<label/>', {class:'check_label', for:'check1'}).append(
						$('<input/>', {type:'checkbox', id:'check1'}),
						$('<span/>', {class:'label_txt', text:'기본 배송지로 설정'})
					)
				)
			)
		),//end append .delivery_bind
		$('<div/>', {class:'layer_btn'}).append(
			$('<a/>', {class:'btn cancel_btn', href:'javascript:;', text:'취소'}),
			$('<a/>', {class:'btn save_btn', href:'javascript:;', text:'저장'})
		)
	);//end append .layer_content
	
	$('<div/>', {}).append(
		$('<a/>', {class:'layer_close_btn', href:'javascript:;'}).append(
			$('<i/>', {class:'fa-solid fa-xmark'})
		)
	).appendTo('.layer_container');
	*/
});//end click '.view-address-btn'

//계좌 선택 모달 창 
$(document).on("click", '.view-account-btn', function(){
	$('.layer_delivery .title').text('계좌 선택');
	$('.layer').css('display', 'flex');
	$('body').css('overflow', 'auto');
	
	//$('.layer_content').empty();
});	

$(document).on("click", '.modify_btn', function(){ 
	$('.layer_delivery .title').text('배송지 수정');
	$('.layer').css('display', 'flex');
	$('body').css('overflow', 'hidden');
});

$(document).on("click", '.save_btn', function(){ 
	if($(this).hasClass('save_btn_able')){
		// 기본 배송지로 설정 확인
		let defaultAddr = ''
		const checked = $('#check1').is(':checked');
		
		if(!checked){
			defaultAddr = 'N'
		}else{
			defaultAddr = 'Y'
		}
		
		if($('.title').text()=='새 주소 추가'){
			$.ajax({
				type:'get',
				url:'/shoeCream/my/registerAddress',
				data:{
					'recipient':$('#input_recipient').val(),
					'zipcode':$('#input_zipcode').val(),
					'addr1':$('#input_addr1').val(),
					'addr2':$('#input_addr2').val(),
					'defaultAddr':defaultAddr
				},
				success:function(){
					location.href="/shoeCream/my/myAddress";
				},
				error:function(){
					alert('Error: 새 주소 추가')
				}
			}); // end ajax
			
		}else if($('.title').text()=='배송지 수정'){
			$.ajax({
				type:'get',
				url:'/shoeCream/my/updateAddress',
				data:{
					'recipient':$('#input_recipient').val(),
					'zipcode':$('#input_zipcode').val(),
					'addr1':$('#input_addr1').val(),
					'addr2':$('#input_addr2').val(),
					'defaultAddr':defaultAddr,
					'addressId':$('#addressId').val()
				},
				success:function(){
					location.href="/shoeCream/my/myAddress";
				},
				error:function(){
					alert('Error: 배송지 수정')
				}
			}); // end ajax
		}
	}
});

	// 다른 주소를 기본배송지로 변경 후, 삭제할 수 있습니다.
	$('.delete_btn').click(function(){});	
	
	$(document).on("click", '.zipcode_btn', function(){ 
		checkPost();
	});
	
	$(document).on("click", '.cancel_btn, .layer_close_btn', function(){ 
		$('.layer').css('display', 'none');
		$('body').css('overflow', 'auto');
		
		$('.input_txt').val('');
		$('#check1').prop('checked', false);
		$('.input_err').hide();
	});
	
	$('#input_recipient').on('input', function(){
		isRecipient();
		setBtn();
	});
	
	//readonly input box
	$('#input_zipcode, #input_addr1').on('input', function(){
		console.log("Input text changed!" + $(this).val());
		isAddress();
	});
	
	(function ($) {
		var originalVal = $.fn.val;
	    $.fn.val = function (value) {
	        var res = originalVal.apply(this, arguments);
	 
	        if (this.is('input:text') && arguments.length >= 1) {
	            this.trigger("input");
	        }
	        
	        return res;
	    };
	})(jQuery);
	
	$('#input_addr2').on('input', function(){
		isAddress();
		setBtn();
	});

	//페이징
	function paging(pg) {
		location.href = '/shoeCream/my/myAddress?pg='+pg;
	}
	
	//유효성 검사
	function isRecipient(){
		const recipient = $('#input_recipient').val();
		const reg = RegExp(/^[가-힣]{2,4}$/);
		
		if(recipient==''){
			$('#input_recipient').attr('validation', 'false');
			$('.input_err').show();
			return;
		}
		
		if(!reg.test(recipient)){
			$('#input_recipient').attr('validation', 'false');
			$('.input_err').show();
		}else{
			$('#input_recipient').attr('validation', 'true');
			$('.input_err').hide();
		}
	}
	
	function isAddress(){
		const zipcode = $('#input_zipcode').val();
		const addr1 = $('#input_addr1').val();
		const addr2 = $('#input_addr2').val();
		
		if(zipcode==''){
			$('#input_zipcode').attr('validation', 'false');
		}else{
			$('#input_zipcode').attr('validation', 'true');
		}
		
		if(addr1==''){
			$('#input_addr1').attr('validation', 'false');
		}else{
			$('#input_addr1').attr('validation', 'true');
		}
		
		if(addr2==''){
			$('#input_addr2').attr('validation', 'false');
		}else{
			$('#input_addr2').attr('validation', 'true');
		}
		
	}
	
	// 버튼 활성화
	function setBtn(){
		const recipient = $('#input_recipient').attr('validation');
		const zipcode = $('#input_zipcode').attr('validation');
		const addr1 = $('#input_addr1').attr('validation');
		const addr2 = $('#input_addr2').attr('validation');
		
		if(recipient=='true'&&zipcode=='true'&&addr1=='true'&&addr2=='true'){
			$('.save_btn').addClass('save_btn_able');
		} else {
			$('.save_btn').removeClass('save_btn_able');
		}
	}
		
	//다음 우편 번호 서비스
	function checkPost() {
		new daum.Postcode({
			oncomplete : function(data) {
				var addr = '';
				
				if (data.userSelectedType === 'R') {
					addr = data.roadAddress;
					
				} else {
					addr = data.jibunAddress;
				}
				
				$('#input_zipcode').val(data.zonecode);
				$('#input_addr1').val(addr);
				$('#input_addr2').val('');
				$('#input_addr2').focus();
			}
		}).open();
	}
	
	//마스킹 처리
	function maskingRecipient(recipient){
		return recipient.replace(/(?<=.{1})./gi, '*');
	}
