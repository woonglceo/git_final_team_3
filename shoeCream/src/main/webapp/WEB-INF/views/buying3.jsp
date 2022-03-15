<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



		<div class="purchase_head">
			<div class="head_product"><a href="#" class="btn_filter"> 전체 <svg xmlns="http://www.w3.org/2000/svg" class="ico-arr-dir-down-circle icon sprite-icons"><use href="/_nuxt/3eb827d04a461ab3eecd387024737978.svg#i-ico-arr-dir-down-circle" xlink:href="/_nuxt/3eb827d04a461ab3eecd387024737978.svg#i-ico-arr-dir-down-circle"></use></svg></a></div>
			
			<div class="head_status">
				<div class="status_box field_price"><a href="#" class="status_link"><span class="status_txt">구매가</span></a></div>
				<div class="status_box field_date_purchased"><a href="#" class="status_link""><span class="status_txt">거래일</span></a></div>
			</div>
		</div>
		
		<div id="one"></div>
		<ul id="two"></ul>	
		
		<div class="pagingDiv" id="pagingDiv"></div>
		<input type="hidden" id="pg" value="${pg}">
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
 	<script type="text/javascript">
 	$(function() {
 		$('.tab_item1').removeClass('tab_on');
 		$('.tab_item2').removeClass('tab_on');
		$('.tab_item3').addClass('tab_on');	
		
		$.ajax({
			 type: 'post',
			 url: '/shoeCream/my/getEndBuyingList',
			 data: {'pg' : $('#pg').val()},
			 dataType: 'JSON',
			 success: function(data){
				 console.log(data.buyList);

				 if(data.buyList.length == 0){	
					 var row = "";
					    row += '<div class="empty-area">';
		        		row	+= '<p>거래 내역이 없습니다.</p>';
		        		row += '</div>';
		        		
		        	$('#one').append(row);
				 }
				 
				
				 $.each(data.buyList, function(index, items){
					 var row = '';
					    row +='<div class="purchase_item bid">';			
		        		row	+= 		'<div class="history_product">';
		        		row	+= 			'<div class="product_box">';
		        		row	+= 				'<div class="shopDetail-top_img">';
		        		row	+= 					'<img class="shopDetail-top_Realimg" src="/shoeCream/resources/storage/"' + items.img + '>';
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
		        		row	+=			 '<div class="status_box field_price">';			
		        		row += 				'<span class="date">'+ items.tradeDate +'</span>';
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
	});
 	</script>
	