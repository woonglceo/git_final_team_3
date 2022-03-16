console.log("9");
let url = new URLSearchParams(location.search);
let styleId = url.get("styleId");

const styleBox = document.querySelector(".styleViewBox");
const ImgUrl = "/shoeCream/resources/images/";

////////////////////////////////

///////////////////////////////

window.onload = function () {
  getData();
}; //시작하자마자 일단 데이터를 받아와줍니다

function getData() {
  console.log("데이터를 부러왓읍니다");
  //실행시켜줍니다
  fetch("/shoeCream/style/getPopularList").then((res) =>
    res.json().then((styleData) => {
      console.log(styleData);
      makeTag(styleData);
    })
  );
}

function makeTag(data) {
  data.forEach((datas) => {
    styleBox.innerHTML += `<div class="want-margin">

    <div class="user-statebox">

        <div class="user-statebox_info" id=${datas.styleId}>
            <div class="user-img">
                <img src="/shoeCream/resources/images/3.png" class="user-img_real" />
            </div>

            <div class="user-state_info">
                <p class="user_id">${datas.username}</p>
                <p class="user-upload_time">${datas.regDate}</p>
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
            <p class="product_name">${datas.productName}</p>
            <div class="price-box">
                <sapn class="amount">${datas.price.toLocaleString("en")}</sapn>
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
            공감 <strong>${datas.likeCount}</strong>개
        </p>
    </div>
    <!--social-count-->

    <div class="social-text">
        <span class="text">${
          datas.contents
        }</span><span class="view-more">..더보기</span>
    </div>

    <div class="comment-area">
        <a class="comment_count">댓글 <strong>${datas.replyCount}</strong>개
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
                    <span class="comment-user_id">
                   
                    </span> <span
                        class="comment-text">
                     
                        </span>
                        
                </div>
                <span class="comment-uspanload_time">
            
                </span>
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
  });

  document.getElementById(styleId).scrollIntoView();
  console.log("여기까지왓ㅇ면아이디서택된건데");

  //   const changeView = document.querySelector(".user-statebox_info");
  //   changeView.addEventListener("click", urlFunction);
  // function urlFunction() {
  //   location.href = "/shoeCream/style/styleMyList";
  // }
}
