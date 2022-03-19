<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="/shoeCream/resources/css/styleUserFeed.css" />

<div class="userFeedContainer">
	<div class="userFeed_header">
		<img src="#" class="userProfile-img" />
		<div class="profile-infobox"><span class="userFeed-username header-username"></span></div>
		<div class="profile-sub"><span class="profile-sub_text"></span></div>
		<div class="mystyle-tablist">
			<ul class="mystyle-tablist_ul">
				<li class="tabitem-list">
				  <a class="tab-item">
				  	<span class="tab-menu">게시물</span>
				  	<span class="tab-count styleCount"></span>
				  </a>
				</li>
			</ul>
		</div>
	</div>
	
	<div class="styleboard-content">
	  <%-- <c:forEach var="i" items="${list}"> --%>
		  <div class="card-box">
		      <img src="/shoeCream/resources/images/1.png" class="style-img_real" />
		      <div class="style-feed_detail">
			        <a href="#">
				          <div class="style_user-box">
					            <img src="#" class="userProfile-img profile-img-sm" />
					            <span class="userFeed-username"></span>
				          </div>
				          <span class="style_text-box">
				            #데일리룩 #크림스타일 #크림챌린지 #무채색코디 #조던코디 #울프그레이 
				            #조던1엘리베이트 #패션유튜버
				          </span>
			        </a>
			        <div class="style_btn-box">
				          <a href="#" class="style_smile-link">
				          		<i class="fa-solid fa-face-smile"></i>
				          </a>
				          <a href="#" class="style_comment-link">
				          		<span class="style_like-count">978</span>
				          		<i class="fa-solid fa-message"></i>
				          		<span class="style_comment-count">12</span>
				          </a>
			        </div>
		        	<div class="style_procuet-list"></div>
		      </div>
		
		      <div class="product-wrap">
		        <div class="product">
		          <img
		            src="/shoeCream/resources/images/4.png"
		            class="style_product-img"
		          />
		        </div>
		        <div class="product_desc">
		          <p class="product_name">Nike x Peaceminusone Kwondo1 White Kwondo1</p>
		          <div class="price-box">
		            <sapn class="amount">12345</sapn><span class="won">원</span>
		          </div>
		        </div>
		      </div>
		      <!--product-wrap-->
		  </div>
	  <%-- </c:forEach> --%>
	</div>
</div>

<input type="hidden" id="username" value="${username}">
<script src="/shoeCream/resources/js/styleMyList.js"></script>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$.ajax({
		type: 'post',
	    url: '/shoeCream/style/getUserFeed', 
	    data: 'username='+$('#username').val(),
	    success: function(data){
	    	console.log(JSON.stringify(data));
	    	// header부분 데이터
	    	$('.userProfile-img').attr('src','/shoeCream/resources/images/userProfile/'+data.userProfileImg);
	    	$('.userFeed-username').text(data.username);
	    	$('.profile-sub_text').text(data.userIntroMsg);
	    	$('.styleCount').text(data.totalStyleCount);
	    	
	    	
	    	
	    	
	    },
	    error: function(err){
	    	console.log(err);
	    }
	});
</script>