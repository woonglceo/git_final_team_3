let url = new URLSearchParams(location.search);
let styleUrl = url.get("styleId");

console.log("로드되었습니다bb");

const id = document.getElementById("sessionId").value;
console.log(id);

const styleBox = document.querySelector(".styleViewBox");
const ImgUrl = "/shoeCream/resources/images/";

/////////////댓글창 띄우기///////////////////
const modal = document.querySelector(".big-reply-box");
const reply = document.querySelector(".modal-hidden");
const xButton = document.querySelector(".xbutton");
const blackBox = document.querySelector(".modal-bg");
//////////////////////////////////////////

xButton.addEventListener("click", removeClass);
blackBox.addEventListener("click", removeClass);

function showComment() {
  modal.classList.remove("modal-hidden");
  reply.classList.remove("modal-hidden");
}

function removeClass() {
  console.log("ㅇㅋㅇㅋ");

  modal.classList.add("modal-hidden");
  reply.classList.add("modal-hidden");
  blackBox.classList.add("modal-hidden");
}

///////////////////////////////

//////////// 게시글 모달에  띄우기///////////////////
const Content = document.querySelector(".reply-writer_content");
const writerId = document.querySelector(".reply-writer_id");
const updatetime = document.querySelector(".reply-update_time");
const likeCount = document.querySelector(".reply-like_count");
//////////////////////////////////////////

//////////// 댓글 모달에  띄우기///////////////////
const replyContent = document.querySelector(".reply-writer_content");
const replywriterId = document.querySelector(".comment-user_id");
const replyupdatetime = document.querySelector(".comment-uspanload_time");
const replylikeCount = document.querySelector(".reply-like_count");
//////////////////////////////////////////

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
} //겟데이터

console.log("휴힘들다");

function makeContent(data, styleId) {
  data.forEach((datas, styleId) => {
    const styleNumber = datas.styleId;
    console.log(styleNumber);
    console.log(datas);

    Content.innerText = datas.contents;
    writerId.innerText = datas.username;

    updatetime.innerText = datas.regDate;
    datas.regDate ? datas.regDate : "";
    likeCount.innerText = datas.likeCount + "개";
  });
}

function makeReply(data) {
  data.forEach((datas) => {});
}

function makeTag(data) {
  data.forEach((datas) => {
    const styleId = datas.styleId;

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
            <i class="likeButton fa-solid ${
              datas.likeOnOff == "off" ? "fa-face-smile" : "fa-heart"
            } smile-icon"></i><i class="fa-solid fa-message"></i>
        </div>
        <a href="#" class="share"><i
            class="fa-solid fa-arrow-up-from-bracket"></i></a>
    </div>
    <!--product-svg_wrap-->

    <div class="social-count">
        <p>
            공감 <strong class="like-num" id="likeCount${datas.styleId}">${
      datas.likeCount
    }</strong>개
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
                    ${datas.replyList ? datas.replyList[0].username : ""}
                    </span> <span
                        class="comment-text">
                        ${datas.replyList ? datas.replyList[0].contents : ""}
                        </span>
                        
                </div>
                <span class="comment-uspanload_time">
                ${datas.replyList ? datas.replyList[0].regDate : ""}
                </span>
            </div>
        </div>

        <div class="comment-like_button">
            <i class="fa-solid fa-face-smile" class="comment-like"></i>
        </div>

    </div>
    <!--comment-statebox-->
    

    <button class="show-comment" onclick="showComment(this)"> 댓글 더 보기...</ㅠ>

    </div>
    <!--want-margin-->
    <input type="hidden" class="test" value=${datas.styleId}>
    `;
  }); //포이치 돌려돌려 여기 끝나면 끝 다 만들어짐

  const smileIcon = document.querySelectorAll(".likeButton");
  const likeElements = document.querySelectorAll(".like-num"); //공감 수 엘리먼트 배열
  const styleIdElements = document.querySelectorAll(".user-statebox_info");
  // const dataStyleId = datas.styleId;
  // smileIcon.forEach((icons,styldId)=>{
  //   icons.addEventListener("click", () => {
  //     changeIcon(icons, styleId);
  //   });
  // })

  makeContent(data);
  makeReply(data);



  //공감 배열을 반복하면서
  likeElements.forEach((likeNum, idx) => {
    //n번째 스마일 버튼부터 천천히 이벤트를 할당한다.
    smileIcon[idx].addEventListener("click", () => {
      //함수에는 n번째 버튼과, n번쨰 공감엘리먼트를 넘긴다

      if (id == "") location.href = "/shoeCream/user/login";
      else {
        const styleNumber = styleIdElements[idx].id;
        changeIcon(smileIcon[idx], likeNum, styleNumber);
      }
    });
  });

  ////////////////////////////스크롤 효과////////////////////////////////////

  document.getElementById(styleUrl).scrollIntoView();

  ////////////////////////////스크롤 효과////////////////////////////////////

  ////////////////////////////삭제////////////////////////////////////
  wantRemove = document.querySelectorAll(".view-more");

  wantRemove.forEach((removeButton) => {
    //다 가져오는거까지 함

    removeButton.addEventListener("click", () => removeAddButton(removeButton));
  });

  ////////////////////////////삭제////////////////////////////////////

  ////////////////////////////함수모음집////////////////////////////////////

  function changeIcon(icons, likeElements, styleNumber) {
    icons.classList.toggle("fa-face-smile");
    icons.classList.toggle("fa-heart");
    //인자로 n번째 아이콘과 n번째 엘리먼트를 받아서 넘김
    addNumber(icons, likeElements, styleNumber);

    // addNumber(icons, likeNum);
  } //클래스 바꿔주는 함수

  function addNumber(icons, likeElements, styleNumber) {
    //n번째 공감 엘리먼트에서 숫자를 받아서 담음
    const likeNum = likeElements.innerText;

    if (icons.classList.contains("fa-heart")) {
      likeElements.innerText = Number(likeNum) + 1;

      fetch(`/shoeCream/style/switchLike?StyleId=${styleNumber}`).then(
        (res) => {
          console.log(res);
          res.text().then((styleData) => {
            console.log(styleData);
          });
        }
      );
    } else {
      likeElements.innerText = Number(likeNum) - 1;

      fetch(`/shoeCream/style/switchLike?StyleId=${styleNumber}`).then(
        (res) => {
          console.log(res);
          res.text().then((styleData) => {
            console.log(styleData);
          });
        }
      );
    }
  } //하트수 계산.. 힘들다

  function removeAddButton(removeButton) {
    removeButton.classList.add("comment-hidden");
  }
} //더보기 삭제버튼

//   const changeView = document.querySelector(".user-statebox_info");
//   changeView.addEventListener("click", urlFunction);
// function urlFunction() {
//   location.href = "/shoeCream/style/styleMyList";

// }

//////////////////////////댓글할래요//////////////////////////
