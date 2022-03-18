<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel='stylesheet' type='text/css' media='screen'
	href='/shoeCream/resources/css/shopSalesView.css'>


<div class="shopDetail-top" id="shop-detail-top">
	<div class="shopDetail-top-product">
		<div class="shopDetail-top_img">
			<img class="shopDetail-top_Realimg" src="">
		</div>
		<div class="shopDetail-product_text">
			<p class="product_modelId"></p>
			<p class="product_name"></p>
			<p class="product_name_translate"></p>
		</div>
	</div>
	<!--shopDetail-top-product-->
</div>
<div class="shopDetail-content"></div>
<div class="shopDetail-content2"></div>
<div class="shopDetail-content3"></div>


<div class="layer_delivery layer">
	<div class="layer_container">
		<div class="layer_header">
			<h2 class="title"></h2>
		</div>
		<div class="layer_content">
			<div class="delivery_bind">
				<div class="delivery_input">
					<div class="input_box">
						<h5 class="input_title">수령인</h5>
						<input type="text" class="input_txt" id="input_recipient" autocomplete="off" placeholder="수령인의 이름">
						<p class="input_err">올바른 이름을 입력해주세요. (2-4자)</p>
					</div>
					<div class="input_box">
						<h5 class="input_title">우편번호</h5>
						<input type="text" class="input_txt" id="input_zipcode" autocomplete="off" placeholder="우편 번호를 검색하세요" readonly>
						<a href="#" class="btn zipcode_btn"> 우편번호 </a>
					</div>
					<div class="input_box">
						<h5 class="input_title">주소</h5>
						<input type="text" class="input_txt" id="input_addr1" autocomplete="off" placeholder="우편 번호 검색 후, 자동입력 됩니다" readonly>
					</div>
					<div class="input_box">
						<h5 class="input_title">상세 주소</h5>
						<input type="text" class="input_txt" id="input_addr2" autocomplete="off" placeholder="건물, 아파트, 동/호수 입력">
					</div>
				</div>
				<div class="delivery_check">
					<div class="checkbox_item">
						<label for="check1" class="check_label">
							<input type="checkbox" id="check1">
							<span class="label_txt">기본 배송지로 설정</span>
						</label>
					</div>
				</div>
			</div>
			<div class="layer_btn">
				<a href=javascript:; class="btn cancel_btn"> 취소 </a>
				<a href=javascript:; class="btn save_btn"> 저장 </a>
			</div>
		</div>
		<div>
			<a href=javascript:; class="layer_close_btn"><i class="fa-solid fa-xmark"></i></a>
		</div>
	</div>
</div><!-- .layer_delivery layer -->
 

<script type="text/javascript"src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="/shoeCream/resources/js/shopSalesView.js"></script>



