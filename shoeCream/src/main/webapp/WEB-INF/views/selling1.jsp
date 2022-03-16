<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



		<div class="purchase_head">
			<div class="head_product"><a href="#" class="btn_filter"> 전체 </a></div>
			
			
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
					<a class="btn_layer_close"><img src="/shoeCream/resources/storage/xxx.png" style="width: 20px; height: 20px"></a></div>
				</div>
				
			</div>
		</div>
	
		<div id="one"></div>
		<ul id="two"></ul>	

		<div class="pagingDiv" id="pagingDiv"></div>
		<input type="hidden" id="pg" value="${pg}">
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
 	<script type="text/javascript">
 	$(function() {
 		
 		console.log($('.month_link').val());
 		$('#ingSelling').click(function() {
 			$('.tab_item1').removeClass('tab_on');
 			$('.tab_item2').addClass('tab_on');	
 		});
 		
 		
 		// 모달창
	 	$('.status_item1').click(function() {			
				$('.status_item1').addClass('tab_on');	
				$('.status_item3').removeClass('tab_on');
				$('.status_item2').removeClass('tab_on');
				$('.layer').hide();
		});
			
			$('.status_item2').click(function() {			
				$('.status_item2').addClass('tab_on');
				$('.status_item1').removeClass('tab_on');
				$('.status_item3').removeClass('tab_on');
				$('.layer').hide();
		});
			
			$('.status_item3').click(function() {
				$('.status_item2').removeClass('tab_on');
				$('.status_item1').removeClass('tab_on');
				$('.status_item3').addClass('tab_on');
				$('.layer').hide();
				
		});
 		
 		
 		
 	</script>
	