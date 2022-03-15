<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<input type="hidden" id="userIdHidden" value="${param.userId}">

      <div class="content">
        <div class="row">
          <div class="col-md-4">
            <div class="card card-user">
              <div class="image">
              </div>
            
              <div class="card-body">
                <div class="author">
                  <a href="#">
                    <h5 class="title"></h5>
                  </a>
                  <p class="description">
                    
                  </p>
                </div>
                <p class="description text-center">
                </p>
              
              
              </div>
              <div class="card-footer">
                <hr>
               
              </div>
            </div>
            <div class="card">
                <h5 class="card-title"></h4>
              <div class="card-body">
    	
    	
             		<div class="dropdown">
					  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					 	거래내역 조회</button>
					  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					    <a class="dropdown-item" href="/shoeCream/adminViews/user/purchaseForm">크림 즉시거래 내역</a>
   					    <a class="dropdown-item" href="/shoeCream/adminViews/user/bidPurchaseForm">입찰구매 내역</a>	   
   					    <a class="dropdown-item" href="/shoeCream/adminViews/user/sellForm">판매 내역</a>	   
					    	   
					  
					  </div>
					  
					</div>
   	
              </div>
            </div>
          </div>
          <div class="col-md-8">
            <div class="card card-user">
              <div class="card-header">
                <h5 class="card-title" >회원정보</h5>
              </div>
              <div class="card-body" id="userTable">
                <form>
                  <div class="row" >
                    <div class="col-md-2 pr-1" >
                      <div class="form-group">
                        <label>회원번호 </label>
                        <input type="text" class="form-control" value="1" id="userId" readonly>
                      </div>
                    </div>
                    <div class="col-md-4 pl-1">
                      <div class="form-group">
                        <label>이메일</label>
                        <input type="text" class="form-control" readonly>
                      </div>
                    </div>
                    <div class="col-md-3 pl-1">
                      <div class="form-group">
                        <label for="exampleInputEmail1">회원 이름</label>
                        <input type="email" class="form-control" readonly>
                      </div>
                    </div>
                    
                    <div class="col-md-3 pl-1">
                      <div class="form-group">
                        <label for="exampleInputEmail1">닉네임</label>
                        <input type="email" class="form-control" readonly>
                      </div>
                    </div>
                                       
                    
                  </div>
                  <div class="row">
                    <div class="col-md-4 pr-1" >
                      <div class="form-group" style="margin-top: 30px; " >
                        <label>회원 등급</label>
                        
						  <select name="ratings" id="ratings">
 							<option >등급선택</option>
						    <option value="1">일반회원</option>
						    <option value="2">관리자</option>
						    <option value="3">블랙리스트</option>
						    <option value="4">휴먼회원</option>
						    <option value="5">탈퇴회원</option>
						    
						  </select>
						  <input type="button" value="수정" id="replaceBtn">
                        
                      </div>
                    </div>
               
                    
                      <div class="col-md-2 pl-1">
                      <div class="form-group">
                        <label for="exampleInputEmail1">현재 회원등급</label>
                        <input type="email" class="form-control" readonly>
                      </div>
                    </div>
                                
                      <div class="col-md-3 pl-1">
                      <div class="form-group">
                        <label for="exampleInputEmail1">휴대폰 번호</label>
                        <input type="email" class="form-control" readonly>
                      </div>
                    </div>
                    
                     <div class="col-md-2 pl-1">
                      <div class="form-group">
                        <label for="exampleInputEmail1">누적 신고수</label>
                        <input type="email" class="form-control" readonly>
                      </div>
                    </div>
                    
                                                                      
                  </div>
                  
                  <div class="row">
                    <div class="col-md-4 pr-1">
                      <div class="form-group">
                        <label>마지막 접속일</label>
                        <input type="text" class="form-control"  readonly>
                      </div>
                    </div>
          	          <div class="col-md-4 px-1">
                      <div class="form-group">
                        <label>회원 등록일</label>
                        <input type="text" class="form-control"  readonly>
                      </div>
                      
                    </div>
                    <div class="col-md-4 pl-1">
                      <div class="form-group">
                        <label>인스타</label>
                        <input type="text" class="form-control" >
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                       
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="update ml-auto mr-auto">
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
      <footer class="footer footer-black  footer-white ">
        <div class="container-fluid">
          <div class="row">
                    
          </div>
        </div>
      </footer>
    </div>
  </div>

</body></html>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

$(function(){
	$.ajax({
		type: 'POST',
		url: '/shoeCream/adminViews/user/getAdminUserId',
		data: 'userId=' + $('#userIdHidden').val(),	
		//dataType: 'JSON',
		success: function(data){
			
				var ds = '';
				if(data.auth == 1) {
					ds = '일반회원';
				} else if(data.auth == 2) {
					ds = '관리자';
				}else if(data.auth == 3) {
					ds = '블랙리스트';
				} else if(data.auth == 4) {
					ds = '휴먼회원';
				} else if(data.auth == 5) {
					ds = '탈퇴회원';
				} 
			
	
			console.log('data', data);
			let input=$('#userTable input');
			input[0].setAttribute('value',data.userId);
			input[1].setAttribute('value',data.email);
			input[2].setAttribute('value',data.username);
			input[3].setAttribute('value',data.nickName); 
			/* input[4].setAttribute('value',data.auth); */
			input[5].setAttribute('value',ds);
			input[6].setAttribute('value',data.phoneNum);
			input[7].setAttribute('value',data.reportCount);
			input[8].setAttribute('value',data.regDate);
			input[9].setAttribute('value',data.lastDate);

						 
				/* $('<tr/>')			
					.append($('<td/>', {    // 유저아이디
						text: data.userId
					})).append($('<td/>', {
						text: data.email
					})).append($('<td/>', {
						text: data.username
					})).append($('<td/>', {
						text: data.nickname
					})).append($('<td/>', {
						text: data.auth
					})).append($('<td/>', {
						text: data.phonenum
					})).append($('<td/>', {
						text: data.intromsg
					})).append($('<td/>', {
						text: data.reportcount
					})
					).appendTo($('#userTable')); */
						
					// 회원번호 , 아이디, 회원이름, 회원등급, 누적신고수
					// 구매내역, 판매내역, ㄴ인스타, 문의, 사진
						
					// 구매내역, 판매내역, 인스타, 문의, 사진 필요								
			
		},
		error: function(err){
			alert('에러났습니다');
			console.log(err);
		}
	});//end ajax
});//end onload

	
</script>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">	
	
$('#replaceBtn').click(function(){
	
	 $.ajax({
		 type: 'post',
		 url: '/shoeCream/adminViews/user/ratingChange',
	     data: { 'ratings': $('#ratings').val(),
	    		 'userId': $('#userIdHidden').val() }	     	
	     ,
		 success: function(){			
				alert('회원등급이 수정되었습니다.');
		 },
		 error: function(err){
				alert(err);
		}	
	
});
	});



</script>


















