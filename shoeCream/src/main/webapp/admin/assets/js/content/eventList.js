// 이벤트 목록 불러오기
$(load);
$('#option').change(load);	 

function load(){
$('#eventListTable tr:gt(0)').remove();
console.log('aaaaaa');
	$.ajax({
		 type: 'post',
		 url: '/shoeCream/adminViews/content/getEventList',
		 data: {'pg':$('#pg').val(),
				'option':$('#option').val()},
		 dataType: 'JSON',
		 success: function(data){ 
			var result = '마감';
			$.each(data.list, function(index, items){
					if(items.status == 0){ result = '진행중'}else{result};

					$('<tr/>')					
					.append($('<td/>')
							.append($('<input/>', {type: 'checkbox', name:'check', value: items.eventId})))
		 			.append($('<td/>', {text: items.eventId}))
		 			.append($('<td/>')
			 				.append($('<img/>', {src: '/shoeCream/admin/assets/storage/' + items.img1, style: 'width: 50px; height: 50px; cursor: pointer;', class: 'img' }))
			 				)
			 		.append($('<td/>', {text: items.title}))
		 			.append($('<td/>', {text: result}))
			 		.append($('<td/>')
		 					.append($('<a/>', {href: '/shoeCream/adminViews/content/updateEventView?pg=' + $('#pg').val() + '&eventId=' + items.eventId, text: '수정', class: 'linkA subjectMoim_' + items.eventId})))
			 		.append($('<td/>')
		 					.append($('<a/>', {href: '/shoeCream/adminViews/content/getAnnounceView' + items.announceId, text: '삭제', class: 'linkA subjectMoim_' + items.seq})))
			 	.appendTo($('#eventListTable'));
			 }); // each문
			 			 
		
			// 페이징처리
			$('#eventPagingDiv').html(data.eventPaging.pagingHTML);
			 
		 },
		 error: function(err){
				alert(err);
		}		 
	 });

};

// 검색		
$('.input-group-append').click(function(){
			
	if($('#inputWord').val() == ''){
		 alert('검색어를 입력하세요.');
	 }else{
		 $.ajax({
			 type: 'post',
			 url: '/shoeCream/adminViews/content/searchEvent',
			 data: {'searchOption':$('#searchOption').val(),
				    'inputWord':$('#inputWord').val(),
				    'searchPg' : $('#searchPg').val(),
				    'option':$('#option').val()},			   
			 dataType: 'JSON',
			 success: function(data){
			 
			 	// 현재 생성되어 있던 테이블 지우기
				$('#eventListTable tr:gt(0)').remove();
				 
				$.each(data.list, function(index, items){
				if(items.status == 0){ result = '진행중'}else{result};
				
					$('<tr/>')
					.append($('<td/>')
							.append($('<input/>', {type: 'checkbox', name:'check', value: items.announceId})))
		 			.append($('<td/>', {text: items.announceId}))
		 			.append($('<td/>')
			 				.append($('<img/>', {src: '/shoeCream/admin/assets/storage/' + items.img1, style: 'width: 50px; height: 50px; cursor: pointer;', class: 'img' }))
			 				)
			 		.append($('<td/>', {text: items.title}))
		 			.append($('<td/>', {text: items.result}))
			 		.append($('<td/>')
		 					.append($('<a/>', {href: '/shoeCream/adminViews/content/updateAnnounceView?pg=' + $('#pg').val() + '&announceId=' + items.announceId, text: '수정', class: 'linkA subjectMoim_' + items.announceId})))
			 		.append($('<td/>')
		 					.append($('<a/>', {href: '/shoeCream/adminViews/content/getAnnounceView' + items.announceId, text: '삭제', class: 'linkA subjectMoim_' + items.seq})))
			 		.appendTo($('#eventListTable'));
				 }); // each문
				 
				 // 페이징처리
				 $('#eventPagingDiv').html(data.eventPaging.pagingHTML);
				 
			 },
			 error: function(err){
					alert(err);
			}		 
	 	}); 
	 	
	 	
		 
	 } // else
			
	});

// 체크박스 처리
$('#all').click(function(){
	
	if($('#all').prop('checked')){
		$('input[name=check]').prop('checked', true);
	}else{
		$('input[name=check]').prop('checked', false);
	}
	

});

// 페이지 이동하기
function boardPaging(pageValue) {	
	
	var inputWord = $('#inputWord').val();
	if(inputWord == ''){
		location.href = '/shoeCream/adminViews/content/eventList?pg='+pageValue;
	}else {
		$('#searchPg').val(pageValue);
		// 검색 
		$('.input-group-append').trigger('click');
		$('#searchPg').val(1)
	}		
}

