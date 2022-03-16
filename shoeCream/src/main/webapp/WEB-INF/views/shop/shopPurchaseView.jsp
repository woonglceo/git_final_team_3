<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel='stylesheet' type='text/css' media='screen'
	href='/shoeCream/resources/css/shopView.css'>

<script src="https://kit.fontawesome.com/7f651fee1e.js"
	crossorigin="anonymous"></script>

<!--  모달 css - 프로젝트에 따라 경로만 수정  -->
<link rel="stylesheet" href="/shoeCream/resources/css/modal.css">
 
<!--  닫힘버튼(x) 아이콘 이미지 cdn  -->
<script src="https://kit.fontawesome.com/c32a0a7a55.js" crossorigin="anonymous"></script>

<div class="shopDetail-box">
	<!--전체를 감싸는 박스-->

	<div class="shopDetail-top tophidden" id="shop-detail-top">
		<div class="shopDetail-top-product">
			<div class="shopDetail-top_img">
				<img class="shopDetail-top_Realimg" src="#" />
			</div>
			<div class="shopDetail-product_text">
				<p class="product_name">Nike x Diamond Supply Co. SB Dunk Low
					Canary Diamond (Friends & Family)</p>
				<p class="product_name_translate">나이키 x 다이아몬드 서플라이 SB 덩크 로우 카나리
					다이아몬드 (프렌즈 앤 패밀리)</p>
			</div>
		</div>
		<!--shopDetail-top-product-->

		<div class="shopDetail-top-button">
			<div class="shopDetail-like">
				<a href="#" class="shopDetail-like-link"> <i
					class="fa-brands fa-wirsindhandwerk"></i><span class="like-count">89</span></a>
			</div>
			<!--첫번째버튼-->

			<div class="shopDetail-buy">
				<a href="#" class="shopDetail-buy-link">
					<div class="button-title">
						<strong>구매</strong>
					</div>
					<div class="price">
						<span class="amount"><em class="num">-
						</em><span class="won">원</span></span><span class="desc">즉시구매가</span>
					</div>
				</a>
			</div>
			<!--두번째버튼-->

			<div class="shopDetail-cell">
				<a href="#" class="shopDetail-cell-link">
					<div class="button-title">
						<strong>판매</strong>
					</div>
					<div class="price">
						<span class="amount"><em class="num">-
						</em><span class="won">원</span></span><span class="desc">즉시구매가</span>
					</div>
				</a>
			</div>
			<!--세번째버튼-->
		</div>
	</div>
	<!--shopDetail-top-->

	<div class="shopDetail-product-content">
		<div class="fix-product nonfix-product">
			<img id="productImg" src="" style="width:50vh;height:50vh;" />
		</div>

		<div class="product-detail">
			<div class="product-main-title-box">
				<a class="product-brand" href="#">Nike</a>
				<p class="procut-title">Nike x Sacai VaporWaffle Black and Gum</p>
				<p class="product-subtitle">나이키 x 사카이 베이퍼와플 블랙 앤 검</p>
			</div>
			<!--product-main-title-box-->

			<div class="product-size-box">
				<span class="product-size-txt">사이즈</span><span
					class="produxt-size-detail">모든 사이즈<i
					class="fa-solid fa-angle-down"></i></span>
			</div>
			<div class="product-price-box">
				<div class="product-price-txt-box">
					<span class="product-price-txt">최근거래가</span>
				</div>
				<!--product-price-txt-box-->

				<div class="product-prict-deatil-box">
					<div class="amount">
						<span class="produxt-price-detail">500000</span><span
							class="price-won">원</span>
					</div>
					<div class="fluctuation-decrease-increse">2000원</div>
				</div>
				<!--product-prict-deatil-box-->
			</div>

			<!--버튼버튼버튼-->

			<div class="shopDetail-content-button2">
				<div class="shopDetail-buy">
					<a href="#" class="shopDetail-buy-link">
						<div class="button-title">
							<strong>구매</strong>
						</div>
						<div class="price">
							<span class="amount"><em class="num">-
							</em><span class="won">원</span></span><span class="desc">즉시구매가</span>
						</div>
					</a>
				</div>
				<!--빨간버튼-->

				<div class="shopDetail-cell">
					<a href="#" class="shopDetail-cell-link">
						<div class="button-title">
							<strong>판매</strong>
						</div>
						<div class="price">
							<span class="amount"><em class="num">-
							</em><span class="won">원</span></span><span class="desc">즉시구매가</span>
						</div>
					</a>
				</div>
				<!--초록버튼-->
			</div>
			<!--구매,판매-->

			<div class="shopDetail-like">
				<a href="#" class="shopDetail-like-link"> <i
					class="fa-brands fa-wirsindhandwerk"></i><span>관심상품</span><span
					class="like-count">89</span></a>
			</div>
			<!--첫번째버튼-->

			<!--버튼버튼버튼-->

			<!--상품정보-->

			<div class="product-info-box">
				<h3>상품정보</h3>
				<div class="product-deatil-wrap">
					<div class="product-detail-info">
						<span class="model-number-title">모델번호</span><span
							class="model-number">DD9315-002</span>
					</div>
					<div class="product-detail-info">
						<span class="model-release-date-title">출시일</span><span
							class="model-release-date">DD9315-002</span>
					</div>
					<div class="product-detail-info">
						<span class="model-color-title">컬러</span><span class="model-color">WOLF
							GREY/BLACK-PHOTON DUST-WHITE</span>
					</div>
					<div class="product-detail-info-remove">
						<span class="model-release-price-title">발매가</span><span
							class="model-release-price">179,000원</span>
					</div>
				</div>
			</div>

			<!--상품정보-->
			<div class="delivery-way-wrap">
				<h3>배송정보</h3>

				<div class="delivery-way">
					<img src="#" />
					<div class="delivery-express-text">
						<p>
							<strong>빠른배송</strong> <span class="won">5,000원</span><i
								class="fa-solid fa-circle-question"></i>
						</p>
						<p class="delivery-way-text-content">
							지금 결제시 <span class="delivery-way-color">내일
								3/11(금) 도착 예정</span>
						</p>
					</div>
				</div>

				<div class="delivery-way">
					<img src="#" />
					<div class="delivery-way-text">
						<p>
							<strong>일반배송</strong> <span class="won">2,500원</span>
						</p>
						<p class="delivery-way-text-content">검수 후 배송 ・ 5-7일 내 도착 예정</p>
					</div>
				</div>
			</div>
			<!--delivery-way-wrap-->

			<div class="goto-event-page">
				<img src="#" class="goto-event-img" />
			</div>
			<!--goto-event-pag-->

			<div class="confirm-wrap">
				<h3 class="confirm-title">구매 전 꼭 확인해주세요!</h3>


				<div class="drop-down-head" id="que-1">
					<p class="confirm-title">배송 기간 안내</p>
					<i class="fa-solid fa-angle-down"></i>
				</div>

				<div class="drop-down-content chang-style" id="ans-2">하나</div>



				<div class="drop-down-head" id="que-2">
					<p class="confirm-title">검수안내</p>
					<i class="fa-solid fa-angle-down"></i>
				</div>

				<div class="drop-down-content chang-style">둘</div>



				<div class="drop-down-head" id="que-3">
					<p class="confirm-title">구매환불/취소/교환안내</p>
					<i class="fa-solid fa-angle-down"></i>
				</div>

				<div class="drop-down-content chang-style">셋</div>

			</div>
			<!--confirm-wrap-->

			<div class="point-guide-line">
				<ul>
					<li class="guide-list"><d.qiv class="guide-wrap">
						<div class="guide-img">
							<img src="#" cass="guid-img-real" />
						</div>
						<div class="guide-text-area">
							<p class="guide-title">100%정품보증</p>
							<p class="guide-text">KREAM에서 검수한 상품이 정품이 아닐 경우, 구매가의 3배를
								보상합니다.</p>
						</div>
						</d.qiv>
						<div class="guide-wrap">
							<div class="guide-img">
								<img src="#" cass="guid-img-real" />
							</div>
							<div class="guide-text-area">
								<p class="guide-title">엄격한 다중 검수</p>
								<p class="guide-text">모든 상품은 검수센터에 도착한 후, 상품별 전문가 그룹의 체계적인
									시스템을 거쳐 검수를 진행합니다.</p>
							</div>
						</div>
						<div class="guide-wrap">
							<div class="guide-img">
								<img src="#" cass="guid-img-real" />
							</div>
							<div class="guide-text-area">
								<p class="guide-title">정품 인증 패키지</p>
								<p class="guide-text">검수에 합격한 경우에 한하여 KREAM의 정품 인증 패키지가 포함된
									상품이 배송됩니다.</p>
							</div>
						</div></li>
				</ul>
			</div>
			<!--point-guide-line-->

			<div class="product-notice">
				<p>크림(주)는 통신판매 중개자로서 통신판매의 당사자가 아닙니다. 본 상품은 개별판매자가 등록한 상품으로 상품,
					상품정보, 거래에 관한 의무와 책임은 각 판매자에게 있습니다. 단, 거래과정에서 검수하고 보증하는 내용에 대한 책임은
					크림(주)에 있습니다.</p>
			</div>
		</div>
		<!--product-detail-->
	</div>
	<!--shopDetail-product-content-->
</div>
<!--shopDetail-box-->
 
<div id="modal-sell" class="modal">
    <div class="modal-window">
        <div class="modal-title">
            <h2 class="modal-title-text">모달제목</h2>
        	<a href="#" class="close-area"><i class="fa-solid fa-xmark fa-lg"></i></a>
        </div>
        <div class="modal-content">
            <p>모달 안에 넣을 내용</p>
        </div>
        <div class="modal-footer">
            <p>모달 풋터</p>
        </div>
    </div>
</div><!-- modal-sell -->
 

<script type="text/javascript"src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/shoeCream/resources/js/shopView.js"></script>



