<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/shoeCream/resources/css/myAddress.css">
<script src="https://kit.fontawesome.com/c32a0a7a55.js" crossorigin="anonymous"></script>
<body>
<div class="myAddress">
	<div class="content_title">
		<h3 id="title_name">주소록</h3>
		<div class="btn_box">
			<a href=javascript:; class="btn add_btn">
				<span class="txt_btn">+ 새 배송지 추가</span>
			</a>
		</div>
	</div>
	<div class="myAddress_info">
		<c:forEach var="item" items='${addressList}'>
		<c:if test="${item.defaultAddr=='Y'}">
		<div class="basic">
			<div class="basic_item" default-mark="기본 배송지">
				<div class="info_bind">
					<div class="name_box">
						<span class="name">${item.recipient}</span>
						<span class="mark">기본 배송지</span>
					</div>
					<div class="address_box">
						<span class="zipcode">${item.zipcode}</span>
						<span class="address">${item.addr1} ${item.addr2}</span>
					</div>
				</div>
				<div class="btn_bind">
					<button type="button" class="btn modify_btn"> 수정 </button>
					<button type="button" class="btn delete_btn"> 삭제 </button>
				</div>
			</div>
		</div>
		</c:if>
		
		<c:if test="${item.defaultAddr=='N'}">
		<div class="other">
			<div class="other_list">
				<div class="info_bind">
					<div class="name_box">
						<span class="name">${item.recipient}</span>
					</div>
					<div class="address_box">
						<span class="zipcode">${item.zipcode}</span>
						<span class="address">${item.addr1} ${item.addr2}</span>
					</div>
				</div>
				<div class="btn_bind">
					<button type="button" class="btn default_btn"> 기본 배송지 </button>
					<button type="button" class="btn modify_btn"> 수정 </button>
					<button type="button" class="btn delete_btn"> 삭제 </button>
				</div>
			</div>
		</div>
		</c:if>
		</c:forEach>
	</div>
</div>
<div class="pagingDiv" id="pagingDiv">${paging.pagingHTML}</div>
<input type="hidden" id="pg" value="${pg}">
<input type="hidden" id="addressList" value="${addressList}">

<!-- 모달 -->
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
</div>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
$(function(){
	const addressList = $('#addressList').val();
	
	$('.add_btn').click(function(){
		$('.layer_delivery .title').text('새 주소 추가');
		$('.layer').css('display', 'flex');
		$('body').css('overflow', 'auto');
	});	
	
	$('.cancel_btn, .layer_close_btn').click(function(){
		$('.layer').css('display', 'none');
		$('body').css('overflow', 'auto');
	});
	
	$('.modify_btn').click(function(){
		$('.layer_delivery .title').text('배송지 수정');
		$('.layer').css('display', 'flex');
		$('body').css('overflow', 'hidden');
	});
	
	$('.zipcode_btn').click(function(){
		checkPost();
	});
	
	<!-- 유효성 검사 -->
	$('.input_txt').on('input', function(){
		
	});
})

	<!-- 페이징 -->
	function paging(pg) {
		location.href = '/shoeCream/my/myAddress?pg='+pg;
	}
	
	function setValidation(result, id){
		if(!result){
			$(id).attr('validation', 'false');
		}else{
			$(id).attr('validation', 'true');
		}
	}
		
	<!-- 다음 우편 번호 서비스 -->
	function checkPost() {
		new daum.Postcode({
			oncomplete : function(data) {
				var addr = '';
				
				if (data.userSelectedType === 'R') {
					addr = data.roadAddress;
					
				} else {
					addr = data.jibunAddress;
				}
	
				$('#input_zipcode').val(data.zonecode);
				$('#input_addr1').val(addr);
				$('#input_addr2').focus();
			}
		}).open();
	}
	
	<!-- 마스킹 처리 -->
	function maskingRecipient(recipient){
		return recipient.replace(/(?<=.{1})./gi, '*');
	}

</script>
</body>
</html>