$(function(){
	let IMG_SRC = '/shoeCream/resources/images/productImg/';
	
	//getShopList
	$.ajax({
		type: 'POST',
		url: '/shoeCream/shop/getShopList',
		//data: 'pg='+1,	
		//dataType: 'JSON',
		success: function(data){
			console.log(data);

		$.each(data.list, function(index, items){

			$('<div/>',{class: 'product_list'}).append( 
				$('<input/>',{
					type: 'hidden',	id: 'productId', value: items.productId}),
				$('<div/>', {class: 'product_list-img'}).append(
					$('<img>', {
					src: IMG_SRC + items.img1 })), 
				$('<div/>', {class: 'product_list-infobox'}).append( 
					$('<div/>', {
						class: 'brand-text',
						text: items.brandname
						}), 
					$('<p/>', {
						class: 'product_name',
						text: items.productName }), 
					$('<p/>', {
						class: 'product_name_translate',
						text: items.productNameKor }), 
					
					$('<div/>', {class: 'amount-lg'}).append(
						$('<span/>', {
							class: 'num',
							text: items.todaysPrice
								//오늘 날짜 시세 구하기
								//SELECT * FROM price_table WHERE TO_CHAR(regDate, 'YYYYMMDD') = TO_CHAR(sysdate, 'YYYYMMDD');
							})), 
					$('<div/>', {class: 'product-icon-box'}).append(
						$('<i/>', {class: 'fa-brands fa-wirsindhandwerk'}), 
						$('<span/>', {
							class: 'product-like',
							text: items.wishListCount
								//SELECT COUNT(*) AS wishListCount FROM wish_list WHERE productId = #{productId}
							}), 
						$('<i/>', {class: 'fa-solid fa-indent'}), 
						$('<span/>', {
							class: 'product-dashboard',
							text: items.styleCount
								//style_table에서 product count
								//SELECT COUNT(*) AS styleCount FROM style_board WHERE productId = #{productId}
							})
					) 
				)
			).appendTo($('.product_wrap'));

			/*
			document.querySelector('.brand-text').addEventListener("click", () =>
				      urlFunction('/shoeCream/shop/shopView?productId=', items.dealId)
			);
			*/
		});//end each
			$(document).on("click", ".product_list-infobox", function(){
				urlFunction('/shoeCream/shop/shopView?productId=', $(this).siblings().get(0).value)
			});
			$(document).on("click", ".product_list-img", function(){
				urlFunction('/shoeCream/shop/shopView?productId=', $(this).siblings().get(0).value)
			});

		},
		error: function(err){
			alert('샵페이지 에러났습니다');
			console.log(err);
		}
	});//end ajax
	
});//end onload

function urlFunction(url, id){
	location.href = url+id;
}

$('.shop-search-order').click(function(){
	//$('.sorting_list').attr('style', 'display: none;');
	$('.sorting_list').toggle();
});//end click
