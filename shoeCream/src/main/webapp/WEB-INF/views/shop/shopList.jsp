<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<link rel="stylesheet" type="text/css" media="screen" href="/shoeCream/resources/css/shopList.css" />


<div class="shop-title">
	<h2>SHOP</h2>
</div>

<div class="search-brand_list">
	<ul class="search-brand_list_ul">
		<li class="brand_item"><a href="#"><img
				src="" class="search-brand_img" />
				<p>ssss</p></a></li>
		<li class="brand_item"><a href="#"><img
				src="" class="search-brand_img" />
				<p>test</p></a></li>
		<li class="brand_item"><a href="#"><img
				src="" class="search-brand_img" />
				<p>test</p></a></li>
		<li class="brand_item"><a href="#"><img
				src="" class="search-brand_img" />
				<p>test</p></a></li>
		<li class="brand_item"><a href="#"><img
				src="" class="search-brand_img" />
				<p>test</p></a></li>
		<li class="brand_item"><a href="#"><img
				src="" class="search-brand_img" />
				<p>test</p></a></li>
		<li class="brand_item"><a href="#"><img
				src="" class="search-brand_img" />
				<p>test</p></a></li>
		<li class="brand_item"><a href="#"><img
				src="" class="search-brand_img" />
				<p>test</p></a></li>
	</ul>
</div>
<!--search-brand_list 상품리스트 ㅁ ㅁ ㅁ ㅁ 이렇게 되어있는 곳까지-->

<div class="shop-content-box">

	<div class="shop-filter-box">
		<div class="filter-status">
			<span class="filter-text">필터</span>
		</div>
		<!--shop-filter-box-->

		<div class="filter-box">
			<div class="filter-category">
				<span class="filter-category-title">카테고리</span><span
					class="filter-category-content">모든 카테고리</span>
			</div>
			<div class="filter-show-category-list">
				<i class="fa-solid fa-plus"></i>
			</div>
		</div>
	</div>
	<!--shop-filter-box-->

	<div class="product-conent-box">

		<div class="shop-search-option">
			<div class="shop-search-order">
				<button type="button" class="shop-search-title" 
				style="cursor:pointer;">인기순</button>
				<i class="fa-solid fa-arrow-trend-up"></i>
			</div>
			<ul class="sorting_list" style="display: none;">  <!-- style="display: none;" -->
			    <li class="sorting_item item_on"><a href="#" class="sorting_link">
			            <div  class="sorting_desc">
			                <p class="main_desc">인기순</p>
			                <p class="sub_desc">많이 판매된 순서대로 정렬합니다.</p>
			            </div>
			        </a></li>
			    <li class="sorting_item"><a href="#" class="sorting_link">
			            <div class="sorting_desc">
			                <p class="main_desc">프리미엄순</p>
			                <p class="sub_desc">발매가 대비 가격이 높은 순서대로 정렬합니다.</p>
			            </div>
			        </a></li>
			    <li class="sorting_item"><a href="#" class="sorting_link">
			            <div class="sorting_desc">
			                <p class="main_desc">즉시 구매가순</p>
			                <p class="sub_desc">즉시 구매가가 낮은 순서대로 정렬합니다.</p>
			            </div>
			        </a></li>
			    <li class="sorting_item"><a href="#" class="sorting_link">
			            <div class="sorting_desc">
			                <p class="main_desc">즉시 판매가순</p>
			                <p class="sub_desc">즉시 판매가가 높은 순서대로 정렬합니다.</p>
			            </div>
			        </a></li>
			    <li class="sorting_item">
			    	<a href="#" class="sorting_link">
			            <div class="sorting_desc">
			                <p class="main_desc">발매일순</p>
			                <p class="sub_desc">최신 상품 순서대로 정렬합니다.
			                    아직 발매 전인 상품이 노출될 수 있습니다.</p>
			            </div>
			        </a>
			    </li>
			</ul>
			
		</div><!-- shop-search-option -->

		<div class="product-content-list">
			<!--여기서부터 ㅈㄴ헷갈림-->


			<div class="product_wrap">
				<!--프로덕트 랩 제외하고 복사-->
				<div class="product_list">
					<div class="product_list-img"></div>
					
					<div class="product_list-infobox">
						<div class="brand-text">Jordan</div>
						<p class="product_name">Jordan 1 Mid Light Smoke GreyAnthracite</p>
						<p class="product_name_translate">조던 1 미드 라이트 스모크 그레이 앤트러사이트</p>
						<div class="amount-lg">
							<span class="num">1000</span><span class="won">원</span>
						</div>
						<div class="product-icon-box">
							<i class="fa-brands fa-wirsindhandwerk"></i><span
								class="product-like">5.2만</span> <i class="fa-solid fa-indent"></i><span
								class="product-dashboard">3081</span>
						</div>
					</div>
				</div><!-- product_list -->
			</div><!-- product_wrap -->



		</div>
		<!--product-conent-box"-->
		</div>
	</div>
	<!--shop-content-box-->
	
<script type="text/javascript"
	src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="/shoeCream/resources/js/shopList.js"></script>