<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nav Bar</title>
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link rel="stylesheet" 
href="https://fonts.googleapis.com/css2?family=Archivo+Black&family=Bangers&family=Crete+Round&display=swap"/>
<!-- CSS -->
<link rel="stylesheet" href="/shoeCream/resources/css/top.css">
<link rel="stylesheet" type="text/css" media="screen" href="/shoeCream/resources/css/main.css" />

<script src="https://kit.fontawesome.com/c32a0a7a55.js" crossorigin="anonymous"></script>
</head>
<body>
	<!-- <header> -->
		<div class="header">
			<ul class="list_menu">
				<li><a href="/shoeCream/serviceCenter/announcement">고객센터</a></li>
				<li><a href="/shoeCream/my/wish">관심상품</a></li>
				<li><a href="/shoeCream/my/myProfile">마이페이지</a></li>
				<li><a href="/shoeCream/admin/adminIndex.jsp">관리자페이지</a></li>
				<c:if test="${ssUserId==null}">
					<li><a href="/shoeCream/user/login">로그인</a></li>
				</c:if>
				<c:if test="${ssUserId!=null}">
					<li><a href="/shoeCream/user/logout">로그아웃</a></li>
				</c:if>
			</ul>
			<hr>
		
			<nav class="navbar">
				<!-- <div class="navbar__logo">
					<a href="/shoeCream/index.jsp"><img alt="logo" src="/shoeCream/resources/images/KREAM_logo.png"></a>
				</div> -->
		        <div class="kream-header__title">
		        	<span><a href="/shoeCream/index.jsp">ShoeCREAM</a></span>
		        </div>
				
				<ul class="navbar__menu">
					<li><a href="/shoeCream/style/trending">STYLE</a></li>
					<li><a href="/shoeCream/shop/shopList">SHOP</a></li>
					<li><a href=#>ABOUT</a></li>
					<li><a href=#><i class="fa-solid fa-magnifying-glass fa-lg"></i></a></li>
				</ul>
			</nav>
			<hr>
		</div>
	<!-- </header> -->
	
</body>
</html>