<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="/shoeCream/resources/css/styleList.css" />
<link rel="stylesheet" href="/shoeCream/resources/css/modal.css">
<link rel="stylesheet" href="/shoeCream/resources/css/styleWrite.css">
<script src="https://kit.fontawesome.com/054564de47.js" crossorigin="anonymous"></script>

<div class="style-tab-list">
  <a href="#" class="sylte-tab_item_tab_on">인기</a>
  <a href="/shoeCream/style/newest" class="sylte-tab_item">최신</a>
  <a href="#" class="sylte-tab_item">팔로잉</a>
</div>
  <a href="#" class="sylte-tab_item" id="writeModalBtn">작성모달TEST</a>

<!-- modal test -->
<div id="writeModal" class="modal">
    <div class="modal-window modal-window-styleWrite">
        <div class="modal-title">
            <h2>스타일 작성</h2>
        	<a href="#" class="close-area"><i class="fa-solid fa-xmark fa-lg"></i></a>
        </div>
   		<div class="modal-content">
        	<div class="write-modal-content">
        		<form name="styleWriteForm" id="styleWriteForm">
	            	<h4>사진 첨부하기</h4>
	            	<div class="style-file"><input type="file" multiple></div>
	            	<div id='thumbnail_zone' data-placeholder='파일 선택 버튼을 클릭하거나 파일을 드래그앱드롭 하세요'></div>
	            	<h4>내용 작성하기</h4>
	            	<div class="styleContents">
	              		<textarea class="contentsTextArea" name="contentsTextArea" placeholder="공유하고 싶은 정보를 작성하세요"></textarea>
	              	</div>
	              	<h4>상품 태그하기</h4>
	              	<p>태그할 상품을 검색하세요</p>
	              	<p>리스트 뜨면 추가, 삭제 만들기</p>
	              	<div class="inputDiv">
						<input type="text" class="searchProudct" name="searchProudct">
						<i class="fa-solid fa-magnifying-glass"></i>
					</div>
					<div class="selectProduct">
						<!-- 선택한 상품 append해주기 (관심상품의 a상품태그 사용) -->
					</div>
					<div class="btn_center_div">
						<a href="#" class="styleWrite_btn black_btn" id="register_btn">등록</a>
					</div>		  
				</form>              
        	</div>
      	</div>
    </div>
</div>

<div class="style-want-center"></div>
<!--style-want-center-->

<script src="/shoeCream/resources/js/styleList.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		//json데이터 테스트
		$.ajax({
			type: 'get',
	        url: '/shoeCream/style/getPopularList', //getMyList?userId=7 //getPopularList //getRecentList
	        success: function(data){
	        	console.log(JSON.stringify(data));
	        },
	        error: function(err){
	        	console.log(err);
	        }
		});
		
		// 모달 오픈
		$('#writeModalBtn').click(function(){
			$('.modal').css('display', 'flex');
			$('body').css('overflow', 'hidden');
		});
		 
		// 모달 클로즈 (x버튼 클릭)
		$('.close-area').click(function(){
			$('.modal').css('display', 'none');
			$('body').css('overflow', 'auto');
		});
		
		/* //파일첨부시 썸네일 표시
		function imagePreview($('#thumbnail_zone'), $('#delete_btn')){
			var sel_files = [];
			
			
		} */
		
	})	
</script>
