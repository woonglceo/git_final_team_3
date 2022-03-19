<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



		<div class="purchase_head">
			<div class="head_product"><a href="#" class="btn_filter"> 전체 <div style="float: right;"><img src="/shoeCream/resources/images/down.png"></div></a></div>
			
			
			<div class="head_status">
				<div class="status_box field_price"><a href="#" class="status_link"><span class="status_txt">판매가</span></a></div>			
				<div class="status_box field_expires_at"><a  href="#" class="status_link"><span class="status_txt">상태</span></a></div>
				
				
				<!-- 모달창 부분 -->
				<div class="layer lg">
					<div class="layer_container">
						<div class="layer_header"><h2 class="title">선택한 상태 보기</h2></div>
						<div lass="layer_content">
							<div class="select_status">
								<ul class="status_list">
									<li class="status_item item_on status_item1"><a href="#" class="status_link2">전체</a></li>
									<li class="status_item status_item2"><a href="#" class="status_link2">배송</a></li>
									<li class="status_item status_danger status_item3"><a href="#" class="status_link2">검수</a></li>
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
		<input type="hidden" id="searchPg" value="${pg}">
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
 	<script type="text/javascript">
 	$(load);	
	$('.status_link2').click(load);
 	
 	function load(){
 		$.ajax({
			 type: 'post',
			 url: '/shoeCream/my/getIngSellingList',
			 data: {'pg' : $('#pg').val(),
				 	'option' : $(this).text()
				 	},
			 dataType: 'JSON',
			 success: function(data){
				 console.log(data);
				 $('#one').empty();
				 $('#two').empty();
				 
				 if(data.sellList.length == 0){					 
					 var row = '<div class="empty-area">';
		        		row	+= '<p>구매 입찰 내역이 없습니다.</p>';
		        		row += '</div>';
		        		
		        	$('#one').append(row);
				 }
				 
				
				 $.each(data.sellList, function(index, items){
					 
					 var row = '';
					    row +='<div class="purchase_item bid">';			
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
		        		row += 				'<span class="status">'+ items.status +'</span>';
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
 		
 		$('.tab_item1').removeClass('tab_on');	
		$('.tab_item3').removeClass('tab_on');
		$('.tab_item2').addClass('tab_on');	
		
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
 		
 	};
 		
 	 // 페이지 이동하기
 	function paging(pageValue) {	
 			if(inputWord == ''){
 				location.href = '/shoeCream/my/ingSelling?pg='+pageValue;
			}else {
				$('#searchPg').val(pageValue);
				// 검색 
				$('.input-group-append').trigger('click');
				$('#searchPg').val(1);
			}
 	}	
 	 
 	
 	</script>
	