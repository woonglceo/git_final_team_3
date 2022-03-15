$(function(){
	$.ajax({
		type: 'POST',
		url: '/shoeCream/adminViews/order/getDeal',
		data: 'dealId=' + $('#dealIdText').val(),	
		//dataType: 'JSON',
		success: function(data){
			console.log('data', data);
			
			var cr = "불통"
			if(data.checkResult == 1) { cr = "통과완료"; }

			$('.tdForView').empty();
			$('.tdForView').eq(0).text(data.dealId); //거래 번호

			$('.tdForView').eq(1).text(data.pemail); //구매자 아이디
			$('.tdForView').eq(2).text(data.preportCount); //구매자 경고횟수
			$('.tdForView').eq(3).text(data.pphoneNum); //구매자 번호
			$('.tdForView').eq(4).text(data.purchaseDueDate); //구매자 번호
			
			$('.tdForView').eq(5).text(data.email); //판매자 아이디
			$('.tdForView').eq(6).text(data.reportCount); //판매자 경고횟수
			$('.tdForView').eq(7).text(data.phoneNum); //판매자 번호
			$('.tdForView').eq(8).text(data.salesDueDate); //판매 마감일

			$('.tdForView').eq(9).text(data.productName); //상품 이름
			$('.tdForView').eq(10).text(data.salesPrice.toLocaleString()+"원"); //상품 판매가격
			$('.tdForView').eq(11).text(cr); //검수결과
			$('.tdForView').eq(12).text(data.dealRegDate); //거래 생성일
		},
		error: function(err){
			alert('에러났습니다');
			console.log(err);
		}
	});//end ajax
});//end onload

