<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<link
  rel="stylesheet"
  type="text/css"
  media="screen"
  href="/shoeCream/resources/css/styleSlick.css"
/>

<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link
  rel="stylesheet"
  href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"
/>
<link
  rel="stylesheet"
  href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"
/>

<script>
  $(function () {
    $.noConflict();
    $(".slider").not(".slick-initialized").slick();

    //style 슬릭 슬라이드
    $(".style_wrap").slick({
      /* prevArrow : ".prev", // 이전 화살표 모양 설정
		nextArrow : ".next", */
      slidesToShow: 6,
      arrows: true,
      infinite: false,
      dots: true,
    });
    $(".style_wrap").slick("init");
  });

  console.log("1");
</script>

<div class="style_wrap">
  <div class="style_wrap-imgbox">
    <img src ="#" class="style-uesr-img">
    <div class="style_wrap-img">test1</div>
    <p class="style-user-id">@</p>
  </div>

  <div class="style_wrap-imgbox">
    <img src ="#" class="style-uesr-img">
    <div class="style_wrap-img">test</div>
    <p class="style-user-id">@s</p>
  </div>

  <div class="style_wrap-imgbox">
    <img src ="#" class="style-uesr-img">
    <div class="style_wrap-img">test</div>
    <p class="style-user-id">@</p>
  </div>

  <div class="style_wrap-imgbox">
    <img src ="#" class="style-uesr-img">
    <div class="style_wrap-img">test</div>
    <p class="style-user-id">@seorim_fighting</p>
  </div>
  <div class="style_wrap-imgbox">
    <img src ="#" class="style-uesr-img">
    <div class="style_wrap-img">test</div>
    <p class="style-user-id">@seorim_fighting</p>
  </div>
  <div class="style_wrap-imgbox">
    <img src ="#" class="style-uesr-img">
    <div class="style_wrap-img">test</div>
    <p class="style-user-id">@seorim_fighting</p>
  </div>
  <div class="style_wrap-imgbox">
    <img src ="#" class="style-uesr-img">
    <div class="style_wrap-img">test</div>
    <p class="style-user-id">@seorim_fighting</p>
  </div>
  <div class="style_wrap-imgbox">
    <img src ="#" class="style-uesr-img">
    <div class="style_wrap-img">test</div>
    <p>@seorim_fighting</p>
  </div>
  <div class="style_wrap-imgbox">
    <img src ="#" class="style-uesr-img">
    <div class="style_wrap-img">test</div>
    <p class="style-user-id">@seorim_fighting</p>
  </div>
  <div class="style_wrap-imgbox">
    <img src ="#" class="style-uesr-img">
    <div class="style_wrap-img">test</div>
    <p class="style-user-id">@seorim_fighting</p>
  </div>
</div>
<!-- 
<div class="button">
	<div class="next">
		<img
			src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FTWq2J%2FbtqEBdA2ene%2FhGRW5eH7EFHllWqfIGDFr1%2Fimg.png"
			alt="" />
	</div>
	<div class="prev">
		<img
			src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FcK32wf%2FbtqEBnQ2bSV%2FRUnYwxbL6CANymmbJNtVk0%2Fimg.png"
			alt="" />
	</div>
</div>
	  -->
