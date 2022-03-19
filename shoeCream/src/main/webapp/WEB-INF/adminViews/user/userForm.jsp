<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
	<link href="/shoeCream/admin/assets/css/user/user.css" rel="stylesheet" />
</head>
 <input type="hidden" id="pg" name="pg" value="${pg}">

<div class="row">
  <div class="col-md-12">
    <div class="card">
    
      <div class="card-header">
        <div>
        <h4 class="card-title" style="float:left;"> 회원관리</h4>
           
        <div class="input-group no-border" style="margin-right:60px;">
		   <select name="searchOption" id="searchOption">
		     <option value="userId">회원번호</option>
		     <option value="userEmail">이메일</option>
		     <option value="userName">회원이름</option>
		     <option value="nickname">닉네임</option>
   		     <option value="Rating">등급</option>
   		     <option value="PhoneNum">휴대폰번호</option>
		     
		     
		   </select>
           <input type="text" value="" placeholder="검색어 입력" id="inputWord"
           style="width:250px;float:right;">
           <div class="input-group-append">
             <div class="input-group-text">
               <i class="nc-icon nc-zoom-split"></i>
             </div>
           </div>
        </div>
        </div>
      </div>

 <div class="card-body">
        <div class="table-responsive">
          <table class="table" id="userListTable">
            <thead class=" text-primary">
              <th width="100">
                회원 번호
              </th>
              <th width="100">
                이메일
              </th>
              <th width="100">
                회원 이름
              </th>
            </thead>
          </table>
          
        	  <input type="hidden" id="searchPg" name="searchPg" value="1"> 	                  
              <div id="userPagingDiv"></div>
        </div>
      </div>
      
</body>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

$(function(){
	$.ajax({
		type: 'POST',
		url: '/shoeCream/adminViews/user/getUserForm',
		data: 'pg=' + $('#pg').val(),	
		dataType: 'JSON',
		success: function(data){
			console.log('data', data);
			
			// 회원정보 불러오기
			$.each(data.list, function(index, items){
				$('<tr/>')			
					.append($('<td/>', {    // 유저아이디
						text: items.userId
					})).append($('<td/>', {
						text: items.email
						
					})).append($('<td/>', {						
					}).append($('<a/>',{
						href: '/shoeCream/adminViews/user/userView?userId='+items.userId ,
						text: items.username, 
						class:'subjectA		subject_' + items.seq			
					}))
					).appendTo($('#userListTable'));	
				
			});//end each
			
			// 페이징처리
			$('#userPagingDiv').html(data.userPaging.pagingHTML); // 이걸 어디서 받아오는지 
			
		},
		error: function(err){
			alert('에러났습니다');
			console.log(err);
		}
	});//end ajax
});//end onload



$('.input-group-append').click(function(){
	
	if($('#inputWord').val() == ''){
		 alert('검색어를 입력하세요.');
	 }else{
		 $.ajax({
			 type: 'post',
			 url: '/shoeCream/adminViews/user/searchUser',
			 data: {'searchOption':$('#searchOption').val(),
				    'inputWord':$('#inputWord').val(),
				    'searchPg' : $('#searchPg').val() },			   
			 dataType: 'JSON',
			 success: function(data){
			 
			 	// 현재 생성되어 있던 테이블 지우기
				$('#productListTable tr:gt(0)').remove();
				 
				 $.each(data.list, function(index, items){
						$('<tr/>')			
							.append($('<td/>', {    // 유저아이디
								text: items.userId
							})).append($('<td/>', {
								text: items.email
								
							})).append($('<td/>', {						
							}).append($('<a/>',{
								href: '/shoeCream/adminViews/user/userView?userId='+items.userId ,
								text: items.username, 
								class:'subjectA		subject_' + items.seq			
							}))
							).appendTo($('#userListTable'));	
						
					});//end each
				 
				// 페이징처리
				$('#productPagingDiv').html(data.productPaging.pagingHTML);
				 
			 },
			 error: function(err){
					alert(err);
			}		 
	 	}); 
	 	
	 	
		 
	 } // else
			
	});
	

	
//페이지 이동하기
function boardPaging(pageValue) {	

	var inputWord = $('#inputWord').val();
	if(inputWord == ''){
		location.href = '/shoeCream/adminViews/user/userForm?pg='+pageValue;
	}else {
		$('#searchPg').val(pageValue);
		// 검색 
		$('.input-group-append').trigger('click');
		$('#searchPg').val(1)
	}
}	




</script>




