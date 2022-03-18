<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



		<div class="purchase_head">
			<div class="head_product"><a href="#" class="btn_filter"> 전체 <div style="float: right;"><img src="/shoeCream/resources/images/down.png"></div></a></div>
			
			
			<div class="head_status">
				<div class="status_box field_price"><a href="#" class="status_link"><span class="status_txt">판매가</span></a></div>			
				<div class="status_box field_expires_at"><a  href="#" class="status_link"><span class="status_txt">만료일</span></a></div>
				
				
				<!-- 모달창 부분 -->
				<div class="layer lg">
					<div class="layer_container">
						<div class="layer_header"><h2 class="title">선택한 상태 보기</h2></div>
						<div lass="layer_content">
							<div class="select_status">
								<ul class="status_list">
									<li class="status_item item_on status_item1"><a href="#" class="status_link2">전체</a></li>
									<li class="status_item status_item2"><a href="#" class="status_link2">입찰 중</a></li>
									<li class="status_item status_danger status_item3"><a href="#" class="status_link2">기한만료</a></li>
								</ul>
							</div>
						</div>
					<a class="btn_layer_close"><img src="/shoeCream/resources/images/xxx.png"></a></div>
				</div>
				
			</div>
		</div>
	
		<div id="one"></div>
		<ul id="two"></ul>	

		<div class="pagingDiv" id="pagingDiv"></div>
		<input type="hidden" id="pg" value="${pg}">
		
		
		<input type="hidden" id="searchPg" name="searchPg" value="1"> 
		<input type="hidden" id="input" name="input"> 
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
 	<script type="text/javascript">
 	$(load);	
 	
	$('.status_link2').click(load);
	
	// 2, 4, 6 개월 동안의 주문
	$('.month_link').click(load2);
	$('.sellingBtn').click(load2);
 	
 	function load(){
 		$.ajax({
			 type: 'post',
			 url: '/shoeCream/my/getSellingList',
			 data: {'pg' : $('#pg').val(),
				 'option' : $(this).text()
				 },
			 dataType: 'JSON',
			 success: function(data){
				 console.log(data.sellList);

				 $('#one').empty();
				 $('#two').empty();
				 
				 if(data.sellList.length == 0){					 
					 var row = '<div class="empty-area">';
		        		row	+= '<p>구매 입찰 내역이 없습니다.</p>';
		        		row	+= '<a href="/shoeCream/shop" class="gray_btn mypage_btn">SHOP 바로가기</a>';
		        		row += '</div>';
		        		
		        	$('#one').append(row);
				 }
				 
				
				 $.each(data.sellList, function(index, items){
					 
					 var row = '';
					    row +='<div class="purchase_item bid2">';			
		        		row	+= 		'<div class="history_product">';
		        		row	+= 			'<div class="product_box">';
		        		row	+= 				'<div class="shopDetail-top_img">';
		        		row	+= 					'<img class="shopDetail-top_Realimg" src="/shoeCream/resources/images/productImg/'+items.img +'">';
		        		row += 				'</div>';
		        		row += 			'</div>';
		        		row	+= 			'<div class="product_detail">';			        
		        		row	+= 				'<p class="name">' + items.productName +'</p>';
		        		row	+= 				'<p class="size"><span class="size_text">' + items.productSize +'</span></p>';		
		        		row += 			'</div>';
		        		row += 		'</div>';
		        		row	+= 		'<div class="history_status">';			
		        		row	+=			 '<div class="status_box field_price">';			
		        		row += 				'<span class="price">'+ items.productPrice +'원</span>';
		        		row += 			 '</div>';
		        		row	+= 		     '<div class="status_box field_date_purchased">'			
		        		row += 				'<span class="status">'+ items.dueDate +'</span>';
		        		row += 			 '</div>';
		        		row += 		'</div>';
		        		row += '</div>';
						
		        		$('.bid2').click(function () {
		        	 		location.href = '/shoeCream/my/sellingView?productId='+items.productId+'&salesId='+items.salesId;
		        		});
		        		
		        		$('#two').append(row);
		        	});//each문
		        	
		      $('#pagingDiv').html(data.paging.pagingHTML);	
		        	
			 },
			 error: function(err){
					alert(err);
			}		 
 		});
 		
 		
 		// 모달창
	 	$('.status_item1').click(function() {			
				$('.status_item1').addClass('item_on');	
				$('.status_item3').removeClass('item_on');
				$('.status_item2').removeClass('item_on');
				$('.layer').hide();
		});
			
			$('.status_item2').click(function() {			
				$('.status_item2').addClass('item_on');
				$('.status_item1').removeClass('item_on');
				$('.status_item3').removeClass('item_on');
				$('.layer').hide();
		});
			
			$('.status_item3').click(function() {
				$('.status_item2').removeClass('item_on');
				$('.status_item1').removeClass('item_on');
				$('.status_item3').addClass('item_on');
				$('.layer').hide();
				
		});
	
 	}
 	
 	function load2(){
 		$('#input').val(1);
 		$.ajax({
			 type: 'post',
			 url: '/shoeCream/my/getMonthSellingList',
			 data: { 'pg' : $('#searchPg').val(),
					 'option' : $(this).val(),
					 'date1' : $('#date1').val(),
					 'date2' : $('#date2').val()
				 },
			 dataType: 'JSON',
			 success: function(data){
				 $('#one').empty();
				 $('#two').empty();
				 
				 if(data.sellList.length == 0){					 
					 var row = '<div class="empty-area">';
		        		row	+= '<p>구매 입찰 내역이 없습니다.</p>';
		        		row	+= '<a href="/shoeCream/shop" class="gray_btn mypage_btn">SHOP 바로가기</a>';
		        		row += '</div>';
		        		
		        	$('#one').append(row);
				 }
				 
				
				 $.each(data.sellList, function(index, items){
					 
					 var row = '';
					    row +='<div class="purchase_item bid">';			
		        		row	+= 		'<div class="history_product">';
		        		row	+= 			'<div class="product_box">';
		        		row	+= 				'<div class="shopDetail-top_img">';
		        		row	+= 					'<img class="shopDetail-top_Realimg" src="/shoeCream/resources/images/productImg/'+items.img+'">';
		        		row += 				'</div>';
		        		row += 			'</div>';
		        		row	+= 			'<div class="product_detail">';			        
		        		row	+= 				'<p class="name">' + items.productName +'</p>';
		        		row	+= 				'<p class="size"><span class="size_text">' + items.productSize +'</span></p>';		
		        		row += 			'</div>';
		        		row += 		'</div>';
		        		row	+= 		'<div class="history_status">';			
		        		row	+=			 '<div class="status_box field_price">';			
		        		row += 				'<span class="price">'+ items.productPrice +'원</span>';
		        		row += 			 '</div>';
		        		row	+= 		     '<div class="status_box field_date_purchased">'			
		        		row += 				'<span class="status">'+ items.dueDate +'</span>';
		        		row += 			 '</div>';
		        		row += 		'</div>';
		        		row += '</div>';
						
		        		$('#two').append(row);
		        	});//each문
		        	
		      $('#pagingDiv').html(data.paging.pagingHTML);	
		        	
			 },
			 error: function(err){
					alert(err);
			}		 
		});
 	}
 	
 	// 페이지 이동하기
 	function paging(pageValue) {
 			if($('#input').val() == ''){
 				location.href = '/shoeCream/my/selling?pg='+pageValue;
			}else {
				$('#searchPg').val(pageValue);
				// 검색 
				$('.month_link').trigger('click');
				$('#searchPg').val(1);
			} 
 	 }	
 		
 	</script>
	