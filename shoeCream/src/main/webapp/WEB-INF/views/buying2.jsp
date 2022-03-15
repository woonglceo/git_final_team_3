<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



		<div class="purchase_head">
			<div class="head_product"><a href="#" class="btn_filter"> 전체 <svg xmlns="http://www.w3.org/2000/svg" class="ico-arr-dir-down-circle icon sprite-icons"><use href="/_nuxt/3eb827d04a461ab3eecd387024737978.svg#i-ico-arr-dir-down-circle" xlink:href="/_nuxt/3eb827d04a461ab3eecd387024737978.svg#i-ico-arr-dir-down-circle"></use></svg></a></div>
			
			<div class="head_status">
				<div class="status_box field_status ascending"><a href="#" class="status_link"><span class="status_txt">상태</span></a></div>
			</div>
		</div>
	
		<div id="one"></div>
		<ul id="two"></ul>	
	
		<%-- <c:if test="${empty buyList}">		
			<div class="empty-area">				
				<p>거래 내역이 없습니다.</p>
			</div>
		</c:if>
		
		<c:if test="${!empty buyList}">		
			<ul>			
				<c:forEach var="i" items="${buyList}">		
				
				<div class="purchase_item bid">
					<div class="history_product">
						<div class="product_box">
							<div class="shopDetail-top_img">
							        <img class="shopDetail-top_Realimg" src='/shoeCream/resources/storage/11.jpg'>
							 </div>
						</div>
						<div class="product_detail">
							<p class="name">${i.productName}</p>
							<p class="size"><span class="size_text">${i.productSize}</span></p>
						</div>
					</div>	
						<div class="history_status">						
							<div class="status_box field_date_purchased">
								<span class="price"> ${i.productPrice}</span>
							</div>							
						</div>
				</div>
					
				</c:forEach>
			</ul>
		</c:if> --%>
		
		<div class="pagingDiv" id="pagingDiv"></div>
		<input type="hidden" id="pg" value="${pg}">
		
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
 	<script type="text/javascript">
 	$(function() {
 		$('.tab_item1').removeClass('tab_on');
		$('.tab_item2').addClass('tab_on');	
		$('.tab_item3').removeClass('tab_on');
		
		
		$.ajax({
			 type: 'post',
			 url: '/shoeCream/my/getIngBuyingList',
			 data: {'pg' : $('#pg').val()},
			 dataType: 'JSON',
			 success: function(data){
				 console.log(data);
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
		        		row	+= 					'<img class="shopDetail-top_Realimg" src="/shoeCream/resources/storage/"' + items.img1 + '>';
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
	