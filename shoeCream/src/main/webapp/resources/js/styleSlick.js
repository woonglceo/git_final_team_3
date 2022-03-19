
const IMG_SRC_STYLE = '/shoeCream/resources/images/style_board/';

$(function() {
    $.noConflict();
    
  //style 데이터 불러오기
  $.ajax({
    type: "POST",
    url: "/shoeCream/style/getPopularList",
    data: "pg=" + 1,
    success: function (styleData) {
    	console.log('styleData', styleData);
        
        for(let i = 1; i <= 10; i++) {
		    $('.style_wrap').append(
		    	$('<div/>', {class:'style_wrap-imgbox'}).append(
		    		$('<img>', {class:'style-user-img item', src:IMG_SRC_STYLE+styleData[i].img1}),
		    		$('<div/>', {class:'style_wrap-img', text:'test'+i}).append(
		    			$('<img>', {class:'style-user-profile-img', src:''})
		    		),
		    		$('<p/>', {class:'style-user-id', text:'@'})
		    	)
		    );//end append '.style_wrap'
	    }//end for
    },
    error: function (err) {
      alert("스타일 에러났습니다");	
      console.log(err);
    },
  }); //end ajax
    
    
    $(".slider").not(".slick-initialized").slick();
    
	
    //style 슬릭 슬라이드
    $(".style_wrap").slick({
      // prevArrow : ".prev", // 이전 화살표 모양 설정
	  // nextArrow : ".next", 
      slidesToShow: 6,
      arrows: true,
      infinite: false,
      dots: true
    });
    //$(".style_wrap").slick("init");
    
    
});