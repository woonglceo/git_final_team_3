<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div class="row">
  <div class="col-md-12">
    <div class="card">
    
      <div class="card-header">
        <div>
        <h4 class="card-title" style="float:left;"> 회원관리</h4>
           
        <div class="input-group no-border" style="margin-right:60px;">
		   <select>
		     <option value="userId">주문번호</option>
		     <option value="userRating">등급</option>
		     <option value="userReport">누적 신고수</option>
		     <option value="sleepMem">휴먼회원</option>
		     
		   </select>
           <input type="text" value="" placeholder="Search..." 
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
                회원 아이디
              </th>
              <th width="100">
                이메일
              </th>
              <th width="100">
                회원 이름
              </th>
            </thead>
          </table>
        </div>
      </div>
      
</body>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		type: 'POST',
		url: '/shoeCream/adminViews/user/getUserForm',
		//data: 'pg=' + $('#pg').val(),	
		//dataType: 'JSON',
		success: function(data){
			console.log('data', data);
			
			$.each(data, function(index, items){
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
			
		},
		error: function(err){
			alert('에러났습니다');
			console.log(err);
		}
	});//end ajax
});//end onload

</script>




