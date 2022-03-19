$(function(){
	var dealIdA;
	$.ajax({
		type: 'POST',
		url: '/shoeCream/adminViews/order/getDealList',
		data: 'pg=' + $('#pg').val(),	
		//dataType: 'JSON',
		success: function(data){
			console.log('data', data);
			

			//gt(0)는 0보다 큰 애를 뜻한다(Greater Than)
			//즉, th말고 다른줄들은 싹 다 지운다
			$('#dealListTable tr:gt(0)').remove(); 
			
			

			$.each(data.list, function(index, items){
				var ds = '배송전';
				if(items.deliveryStatus == 1) {
					ds = '배송중';
				} else if(items.deliveryStatus == 2) {
					ds = '배송완료';
				}
				var cs = '검수전';
				if(items.checkState == 1) {
					cs = '검수중';
				} else if(items.checkState == 2) {
					cs = '검수완료';
				}
				
				$('<tr/>', {
					//onclick: () =>
      				//		urlFunction('/shoeCream/adminViews/order/dealView?dealId=', items.dealId),
					class: 'dataTr',
					style: 'cursor: pointer'				

					}).append($('<td/>')
						.append($('<a/>', {// 거래번호
							href: '/shoeCream/adminViews/order/dealView?dealId='+items.dealId,
							text: items.dealId,
							class: 'deal' //'subjectA subjectA_' + items.seq    
						}))
					).append($('<td/>', { // 상품이름
						text: items.productName
					})).append($('<td/>', {	// 구매자아이디
						text: items.pemail
					})).append($('<td/>', {	// 판매자아이디
						text: items.email
					})).append($('<td/>', {	// 검수상태
						text: cs
					})).append($('<td/>', {	// 배송상태
						text: ds
					})).append($('<td/>', {	// 입찰마감기한
						text: items.purchaseDueDate
					})).append($('<td/>', {	// 판매마감기한
						text: items.salesDueDate
					})).appendTo($('#dealListTable #tableBody'));	
			/*
				document.querySelector('.dataTr').addEventListener("click", () =>
				      urlFunction('/shoeCream/adminViews/order/dealView?dealId=', items.dealId)
				);
			*/
				//$('tr').click( () => dealIdA.splice(0, dealIdA.length));
				
				//$('tr').click( () => location.href='/shoeCream/adminViews/order/dealView?dealId='+dealIdA[0] );
			});//end each
				dealIdA.splice(0, dealIdA.length)
/*				
				document.querySelector('.dataTr').addEventListener("click", () =>
				      console.log( this ) //.children('.deal')
				);
*/				
				$('tr').click( () => dealIdA = this.dealId );
				$('tr').click( () => console.log(dealIdA) );
			// 페이징 처리
			$('#dealPagingDiv').html(data.dealPaging.pagingHTML);
		},
		error: function(err){
			alert('에러났습니다');
			console.log(err);
		}
	});//end ajax
});//end onload

function dealPaging(newPg){
	location.href = '/shoeCream/adminViews/order/dealList?pg='+newPg;
}

$('#searchBtn').click(function(){
	//alert($('#dealSearchSelect').val()+', '+$('#dealSearchText').val());
	$('#dealSearchDiv').empty();
	if($('#dealSearchText').val() == '') {
		//alert('값을 입력해주세요.');
		$('#searchDivText').text('값을 입력해주세요');
	} else {
		$.ajax({
			type: 'POST',
			url: '/shoeCream/adminViews/order/getDealSearchList',
			data: {
				searchOption: $('#searchOption').val(),
				keyword: $('#dealSearchText').val(),
				pg: '1'
			},
			success: function(data){
				$('#dealListTable tr:gt(0)').remove(); 
				console.log(data)

				$.each(data.list, function(index, items){
				var ds = '배송전';
				if(items.deliveryStatus == 1) {
					ds = '배송중';
				} else if(items.deliveryStatus == 2) {
					ds = '배송완료';
				}
				var cs = '검수전';
				if(items.checkState == 1) {
					cs = '검수중';
				} else if(items.checkState == 2) {
					cs = '검수완료';
				}
				
				$('<tr/>').append($('<td/>')
						.append($('<a/>', {// 거래번호
							href: '/shoeCream/adminViews/order/dealView?dealId='+items.dealId,
							text: items.dealId,
							class: 'deal_' + items.dealId  //'subjectA subjectA_' + items.seq    
						}))
					).append($('<td/>', { // 상품이름
						text: items.productName
					})).append($('<td/>', {	// 구매자아이디
						text: items.pemail
					})).append($('<td/>', {	// 판매자아이디
						text: items.email
					})).append($('<td/>', {	// 검수상태
						text: cs
					})).append($('<td/>', {	// 배송상태
						text: ds
					})).append($('<td/>', {	// 입찰마감기한
						text: items.purchaseDueDate
					})).append($('<td/>', {	// 판매마감기한
						text: items.salesDueDate
					})).appendTo($('#dealListTable'));	

			});//end each
			},
			error: function(err){
				alert('오류났다아~~~');
				console.log(err);
			},
			beforeSend: function(){
				$('#dealSearchDiv').text('로딩중입니다.');				
			},
			complete: function(){
				
			}
		});//end ajax
	}//end if
});//end click


function urlFunction(url, id){
	location.href=url+id;
}


