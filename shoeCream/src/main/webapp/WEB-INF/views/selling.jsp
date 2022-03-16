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
	<div class="title"><div class="title"><h3>�Ǹ� ����</h3></div></div>
		<div class="purchase_list_tab detail_tab">
			<div class="tab_item tab_on tab_item1">
				<a href="/shoeCream/my/selling" class="tab_link">
					<dl class="tab_box">
						<dt class="title">�Ǹ� ����</dt>
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
						<dt class="title">�ŷ� ��</dt>
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
						<dt class="title">�ŷ��Ϸ�</dt>
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
				<li class="month_item"><input type="button" class="month_link" value="�ֱ� 2����"></li>
				<li class="month_item"><input type="button" class="month_link" value="4����"></li>
				<li class="month_item"><input type="button" class="month_link" value="6����"></li>
			
				<li class="month_item"><input type="text" disabled="disabled" class="month_link link1" id="date1"></li>
				<li><span class="swung_dash">~</span></li>
				<li class="month_item"><input type="text" disabled="disabled" class="month_link link1" id="date2"></li>				
				<li class="month_item"><div class="period_btn_box"><button class="btn_search is_active">��ȸ</button></div></li>
			</ul>
		</div>
	</div>
	
	<ul class="search_info">
		<li class="info_item"><p>�� ���� ��ȸ ������ �Ⱓ�� �ִ� 6�����Դϴ�.</p></li>
		<li class="info_item"><p>�Ⱓ�� ��ȸ ����� ������ �������� ����˴ϴ�.</p></li>
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
 		
 		// ���â ���� / ����
 	 	$j351('.btn_filter').click(function(){$('.layer').show()});
 	 	$j351('.btn_layer_close').click(function(){$('.layer').hide()}); 	  
 	    
 		$j351("#date1 , #date2").datepicker({	 	 
 	 	     showOn:"button", 
 	 	     buttonImage: "/shoeCream/resources/storage/calendar.png",
 	 	     buttonImageOnly: true,
 	 	     changeMonth:true,
 	 	     dateFormat:"yy-mm-dd",
 	 	     dayNames : ['������','ȭ����','������','�����','�ݿ���','�����','�Ͽ���'],
 	 	     dayNamesMin : ['��','ȭ','��','��','��','��','��'],
 	 	     monthNamesShort:  [ "1��", "2��", "3��", "4��", "5��", "6��","7��", "8��", "9��", "10��", "11��", "12��" ],
 	 	     minDate: "-6M", 
             maxDate: "0D" // 6������ �Ѱ踦 �ɾ����.
 	 	 });
 		
 		$j351('#date2').datepicker('setDate', 'today');  
 		$j351('#date1').datepicker('setDate', '-6M');
	});	
 	</script>
 	</script>
</body>
</html>