<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<head>
	<link rel="stylesheet" href="/shoeCream/resources/css/selling.css">
</head>
</head>
<body>

<div class="my_purchase">
	<div class="title"><div class="title"><h3>판매 내역</h3></div></div>
		<div class="purchase_list_tab detail_tab">
			<div class="tab_item tab_on tab_item1">
				<a href="/shoeCream/my/selling" class="tab_link">
					<dl class="tab_box">
						<dt class="title">판매 입찰</dt>
						<dd class="count">
							<c:if test = "${empty totalCount}">	
								0
							</c:if>
							<c:if test = "${!empty totalCount}">	
								${totalCount}
							</c:if>
						</dd>
					</dl>
				</a>
			</div>
			<div class="tab_item tab_item2">
				<a href="/shoeCream/my/ingSelling" class="tab_link" id="ingBuying">
					<dl class="tab_box">
						<dt class="title">거래 중</dt>
						<dd class="count">
							<c:if test = "${empty ingCount}">	
								0
							</c:if>
							<c:if test = "${!empty ingCount}">	
								${ingCount}
							</c:if>
						</dd>
					</dl>
				</a>
			</div>
			<div class="tab_item tab_item3">
				<a href="/shoeCream/my/endSelling" class="tab_link">
					<dl class="tab_box">
						<dt class="title">거래완료</dt>
						<dd class="count">
							<c:if test = "${empty endCount}">	
								0
							</c:if>
							<c:if test = "${!empty endCount}">	
								${endCount}
							</c:if>
						</dd>
					</dl>
				</a>
			</div>
			</div>		
	</div>
			 
	<div class="period_search">
		<div class="period_month">
			<ul class="month_list">		
				<li class="month_item"><input type="button" class="month_link" value="최근 2개월"></li>
				<li class="month_item"><input type="button" class="month_link" value="4개월"></li>
				<li class="month_item"><input type="button" class="month_link" value="6개월"></li>
			
				<li class="month_item"><input type="text" disabled="disabled" class="month_link link1" id="date1"></li>
				<li><span class="swung_dash">~</span></li>
				<li class="month_item"><input type="text" disabled="disabled" class="month_link link1" id="date2"></li>				
				<li class="month_item"><div class="period_btn_box"><button class="btn_search is_active">조회</button></div></li>
			</ul>
		</div>
	</div>
	
	<ul class="search_info">
		<li class="info_item"><p>한 번에 조회 가능한 기간은 최대 6개월입니다.</p></li>
		<li class="info_item"><p>기간별 조회 결과는 입찰일 기준으로 노출됩니다.</p></li>
	</ul>
	
	<div class="purchase_list bidding bid">
		<jsp:include page="${display2}"></jsp:include>
	</div>
	
 	<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 	<script type="text/javascript"> 
 	var $j351 = jQuery.noConflict();
 	
 	$j351(function() {
 		$j351('.layer').hide();
 		
 		// 모달창 열기 / 끄기
 	 	$j351('.btn_filter').click(function(){$('.layer').show()});
 	 	$j351('.btn_layer_close').click(function(){$('.layer').hide()}); 	  
 	    
 		$j351("#date1 , #date2").datepicker({	 	 
 	 	     showOn:"button", 
 	 	     buttonImage: "/shoeCream/resources/storage/calendar.png",
 	 	     buttonImageOnly: true,
 	 	     changeMonth:true,
 	 	     dateFormat:"yy-mm-dd",
 	 	     dayNames : ['월요일','화요일','수요일','목요일','금요일','토요일','일요일'],
 	 	     dayNamesMin : ['월','화','수','목','금','토','일'],
 	 	     monthNamesShort:  [ "1월", "2월", "3월", "4월", "5월", "6월","7월", "8월", "9월", "10월", "11월", "12월" ],
 	 	     minDate: "-6M", 
             maxDate: "0D" // 6개월로 한계를 걸어놓음.
 	 	 });
 		
 		$j351('#date2').datepicker('setDate', 'today');  
 		$j351('#date1').datepicker('setDate', '-6M');
	});	
 	</script>
 	</script>
</body>
</html>