<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="row">
  <div class="col-md-12">
    <div class="card">
    
      <div class="card-header">
        <div>
        <h4 class="card-title" style="float:left;">검수관리</h4>
           
        <div class="input-group no-border" style="margin-right:60px;">
		   <select id="selectBtn1">
		     <option value="0">검사전</option>		   
		     <option value="1">검수중</option>
		     <option value="2">검수완료</option>
		     
		   </select>
		   
		   <select id="selectBtn2">
		     <option value="0">불합격</option>		   
		     <option value="1">합격</option>
		     		     
		   </select>
      
           <div class="input-group-append">
             <div class="input-group-text" id="searchBtn">
               <i class="nc-icon nc-zoom-split"></i>
             </div>
           </div>
        </div>
        </div>
      </div>


	
	<div class="col-md-12">
            <div class="card card-plain">
              <div class="card-header">
              </div>
              <div class="card-body">
                <div class="table-responsive">
                  <table class="table" id="checkListTable">
                    <thead class=" text-primary">
                      <tr><th>
                        검수번호
                      </th>
                      <th>
                        상품번호
                      </th>
                      <th>
                        회원번호
                      </th>
                       <th>
                       	검수현황
                      </th>
                       <th>
                        검수결과
                      </th>
                       
                    </tr></thead>
                
                  </table>
                </div>
              </div>
            </div>
          </div>

</body>
</html>


<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		type: 'POST',
		url: '/shoeCream/adminViews/check/getCheckForm',
		//data: 'pg=' + $('#pg').val(),	
		//dataType: 'JSON',
		success: function(data){
			console.log('data', data);
			
			//$('#orderListTable tr:gt(0)').remove(); 
			

			$.each(data, function(index, items){
				
				var ds = '';
				var vs = '';
				if(items.checkState == 0) {
					ds = '검수 전';
				} else if(items.checkState == 1) {
					ds = '검수 중';
				} else if(items.checkState == 2) {
					ds = '검수 완료';
				} 
			
				if(items.checkResult == 0) {
					vs = '불합격';
				} else if(items.checkResult == 1) {
					vs = '합격';
				} 
				
				 $('<tr/>')			
					.append($('<td/>', {    // 유저아이디
						text: items.checkId
					})).append($('<td/>', {
						text: items.productId						
					})).append($('<td/>', {
						text: items.userId
					})).append($('<td/>', {
						text: ds
					})).append($('<td/>', {
						text: vs
					})).appendTo($('#checkListTable'));
			
			});//end each
			
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


$('#searchBtn').click(function(){
	$.ajax({
		type: 'POST',
		url: '/shoeCream/adminViews/check/searchBtnForm',
		//data: 'pg=' + $('#pg').val(),	
		//dataType: 'JSON',
		success: function(data){
			console.log('data', data);
			
			'searchOption1' : $('#searchBtn1').val(),
			'searchOption2' : $('#searchBtn2').val()
			
			console.log('searchOption1', searchOption1);
			console.log('searchOption2', searchOption2);

			
			});//end each
			
		},
		error: function(err){
			alert('에러났습니다');
			console.log(err);
		}
	});//end ajax
});





</script>