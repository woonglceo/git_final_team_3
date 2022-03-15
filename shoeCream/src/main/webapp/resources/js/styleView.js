console.log("1");

const styleViewBox = document.querySelector(".styleViewBox");

window.onload = function () {
  makeTag();
}; //

function makeTag() {
  styleViewBox.innerHTML = `<div class="want-margin">
    
    
    <div class="user-statebox">
    
        <div class="user-statebox_info">
            <div class="user-img">
                <img src="/shoeCream/resources/images/3.png" class="user-img_real" />
            </div>
    
            <div class="user-state_info">
                <p class="user_id">tjfla6417</p>
                <p class="user-upload_time">2월 20일</p>
            </div>
        </div>
    
        <div class="user-follow_button">
            <button class="follow_button">팔로우</button>
        </div>
    
    </div>
    <!--user-statebox-->
    
    
    <!--게시글 이미지-->
    <div class="img-box">
        <img src="/shoeCream/resources/images/1.png" class="img-box_img">
    </div>
    <!--게시글 이미지-->
    
    
    
    <div class="product_title">
        <p class="procut_tag">
            상품 태그<strong class="num">1</strong>개
        </p>
    </div>
    
    
    <div class="product_title">
        <div class=""></div>
        <a href="#" class="share"></a>
    </div>
    
    
    <div class="product-wrap">
    
        <div class="product">
            <img src="/shoeCream/resources/images/4.png" class="style_product-img">
        </div>
        <div class="product_desc">
            <p class="product_name">Nike x Peaceminusone Kwondo1 White
                Kwondo1</p>
            <div class="price-box">
                <sapn class="amount">12345</sapn>
                <span class="won">원</span>
            </div>
        </div>
    
    </div>
    <!--product-wrap-->
    
    <div class="product-svg_wrap">
        <div class="product-svg">
            <i class="fa-solid fa-face-smile"></i><i class="fa-solid fa-message"></i>
        </div>
        <a href="#" class="share"><i
            class="fa-solid fa-arrow-up-from-bracket"></i></a>
    </div>
    <!--product-svg_wrap-->
    
    
    <div class="social-count">
        <p>
            공감 <strong>1999</strong>개
        </p>
    </div>
    <!--social-count-->
    
    <div class="social-text">
        <span class="text">내용ㅅㄷㄴㅅtesttㅇㅇㅇ내</span><span class="view-more">..더보기</span>
    </div>
    
    <div class="comment-area">
        <a class="comment_count">댓글 <strong>65</strong>개
        </a>
    
    </div>
    <!--commet-area-->
    <div class="comment-statebox">
    
        <div class="comment-statebox_info">
            <div class="comment-userimg">
                <img src="/shoeCream/resources/images/1.png" class="comment-userimg-real" />
            </div>
    
            <div class="comment-state_info">
                <div class="comment-idntext">
                    <span class="comment-user_id">tjfla6417</span> <span
                        class="comment-text">댓글길이테스트댓글길이테스트댓글길이테스트댓글길이테스트댓글길이테스트댓글길이테스트</span>
                </div>
                <span class="comment-uspanload_time">2월 20일</span>
            </div>
        </div>
    
        <div class="comment-like_button">
            <i class="fa-solid fa-face-smile" class="comment-like"></i>
        </div>
    
    </div>
    <!--comment-statebox-->
    
    <a class="show-comment"> 댓글 더 보기...</a>
    
    </div>
    <!--want-margin-->`;

  const changeView = document.querySelector(".user-statebox_info");

  changeView.addEventListener("click", urlFunction);

  function urlFunction() {
    location.href = "/shoeCream/style/styleMyList";
  }
}
