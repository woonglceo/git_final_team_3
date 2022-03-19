<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/shoeCream/resources/css/buyingView.css">
</head>
<body>
<input type="hidden" id="pg" name="pg" value="${pg}">

	<div class="my_buying_detail bidding">
		<div class="content_title">
			<div class="title"><h3 >구매내역 &gt; 입찰 중</h3></div>
			<div class="btn_box"><a href="#" class="btn"><span class="btn_txt">삭제하기</span></a></div>
		</div>
		<div class="order_info_wrap">
			<div class="section_title order_title">
				<div class="title_box"><h3 class="title"> 주문번호 <em class="order_number"> ${info.ORDERID}</em></h3></div>
			</div>
			<div class="order_info">
				<div class="order_product">
					<div class="product_box">
						<div class="product" style="background-color: rgb(235, 240, 245);"><img src="/shoeCream/resources/images/productImg/${info.IMG1}" class="image"></div>
					</div>
					<div class="product_detail">
						<strong class="number">${info.MODELID}</strong>
						<p class="name">${info.PRODUCTNAME}</p><p class="size"><span class="size_text">${info.PRODUCTSIZE}</span></p>
					</div>
				</div>
				<div class="order_btn"><a href="#" class="btn outlinegrey small"> 상품 상세보기 </a></div>
			</div>
		</div>
		
		<br>
		<div class="purchase_history_wrap">
			<div class="section_title">
				<div class="title_box"><h3 class="title"> 구매 입찰 내역 </h3></div>
			</div>
			<div class="purchase_history bidding">
				<div class="history_detail">
					<div class="main_box">
						<dl class="main_item">
							<dt class="title">구매 희망가</dt>
								<dd class="price">
									<span class="amount"><fmt:formatNumber value="${info.PRODUCTPRICE}" pattern="###,###,###"/></span>
									<span class="unit">원</span>
								</dd>
						</dl>
					</div>
				<div class="detail_box">
					<dl class="price_addition">
						<dt class="price_title dark"><span >총 결제금액</span></dt><dd class="price_text bold buy"><fmt:formatNumber value="${info.PRODUCTPRICE}" pattern="###,###,###"/></dd>
					</dl>
				</div>
				<div item="[object Object],[object Object]" class="detail_box">
					<dl class="price_addition">
						<dt class="price_title dark"><span >입찰일</span></dt><dd class="price_text"><fmt:formatDate value="${info.REGDATE}" pattern="yy/MM/dd"/></dd>
					</dl>
					<dl class="price_addition">
						<dt class="price_title dark"><span >입찰 마감기한</span></dt><dd class="price_text"><fmt:formatDate value="${info.DUEDATE}" pattern="YY/MM/dd"/></dd>
					</dl>
				</div>
			</div>
		</div>
		<br>
		<div class="shipping_address_wrap">
			<div class="section_title">
				<div class="title_box"><h3 class="title"> 배송 주소 </h3></div><p class="noti">대기 중, 발송완료, 입고완료 상태에서만 배송지 변경이 가능합니다.</p>
			</div>
			<div class="shipping_address">
				<dl class="address_item">
					<dt class="address_title">받는 사람</dt><dd class="address_txt">{info.userName}</dd>
				</dl>
				<dl class="address_item">
					<dt class="address_title">휴대폰 번호</dt><dd class="address_txt">${info.USERNUM}</dd>
				</dl>
				<dl class="address_item">
					<dt class="address_title">주소</dt><dd class="address_txt">${info.ADDR}</dd>
				</dl>
			</div>
		</div>
		</div>
		
		<div class="detail_btn_box"><a href="#" class="btn btn_view_list outlinegrey medium" id="list"> 목록보기 </a></div>
	</div>

</body>

</html>