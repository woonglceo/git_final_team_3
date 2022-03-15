console.log("3");

const styleEntireBox = document.querySelector(".style-want-center");
const stylePerLink = "/shoeCream/style/styleView";

const dataArr = [];

window.onload = function () {
  getData();
}; //시작하자마자 일단 데이터를 받아와줍니다

function getData() {
  //실행시켜줍니다
  fetch("/shoeCream/style/getPopularList").then((res) =>
    res.json().then((data) => {
      const styleData = data;

      styleData.sort(function (a, b) {
        return b.like - a.like;
      });
      console.log(styleData);

      makeStyleList(styleData);
    })
  );
}

window.addEventListener("scroll", (e) => {
  const entireHeight = window.innerHeight + window.scrollY;

  if (entireHeight > styleEntireBox.clientHeight) {
    getData();
  }
});

function makeStyleList(objArr) {
  objArr.forEach((styleDatas) => {
    const styleWantDisplay = document.createElement("div");
    styleWantDisplay.setAttribute("class", "style-want-display");

    const styleFeedBox = document.createElement("div");
    styleFeedBox.setAttribute("class", ".style-feed_box");

    const styleFeed = document.createElement("div");
    styleFeed.setAttribute("class", "style-feed");
    styleFeed.setAttribute("href", stylePerLink); //추가

    const styleImg = document.createElement("div");
    styleImg.setAttribute("class", "style-img");

    const styleImgReal = document.createElement("img");
    styleImgReal.setAttribute("class", "style-img_real");
    styleImgReal.setAttribute("src", "img/1.png");

    styleImg.appendChild(styleImgReal);
    styleFeed.appendChild(styleImg);

    //피드

    // document.body.appendChild(styleFeed);

    //피드디테일
    const styleFeedDetail = document.createElement("div");
    styleFeedDetail.setAttribute("class", "style-feed_detail");

    const styleFeedHref = document.createElement("a");

    styleFeedHref.setAttribute("href", stylePerLink);
    const styleFeedPtag = document.createElement("p");
    const styleUserBox = document.createElement("div");
    styleUserBox.setAttribute("class", "style_user-box");
    const styleImgProfile = document.createElement("img");
    styleImgProfile.setAttribute("class", "style_img-profile");

    styleImgProfile.addEventListener("click", () =>
      urlFunction("/shoeCream/style/styleView?userId=", styleDatas.userId)
    );

    styleImgReal.addEventListener("click", () =>
      urlFunction("/shoeCream/style/styleView?userId=", styleDatas.userId)
    );

    styleUserBox.addEventListener("click", () =>
      urlFunction("/shoeCream/style/styleView?userId=", styleDatas.userId)
    );
    // styleImgProfile.setAttribute(
    //   "onclick",
    //   urlFunction("/shoeCream/style/styleView?userId=", styleDatas.userId)
    // );

    const styleTextBox = document.createElement("p");
    styleTextBox.setAttribute("class", "style_text-box");

    //피드디테일

    //피드버튼

    const styleBtnBox = document.createElement("div");
    styleBtnBox.setAttribute("class", "style_btn-box");

    const smileLink = document.createElement("a");
    smileLink.setAttribute("class", "style_smile-link");

    const smileI = document.createElement("i");
    smileI.setAttribute("class", "fa-solid fa-face-smile");

    smileI.addEventListener("click", changeHeart);

    const styleLikeCount = document.createElement("span");
    styleLikeCount.setAttribute("class", "style_like-count");

    const smileComentLink = document.createElement("a");
    smileComentLink.setAttribute("class", "style_comment-link");
    smileComentLink.setAttribute("href", stylePerLink);

    const commentI = document.createElement("i");
    commentI.setAttribute("class", "fa-solid fa-message");

    const styleCommentCount = document.createElement("span");
    styleCommentCount.setAttribute("class", "style_comment-count");

    //피드버튼

    //프로덕트랩//

    const productWrap = document.createElement("div");
    productWrap.setAttribute("class", "product-wrap");

    const productImgBox = document.createElement("div");
    productImgBox.setAttribute("class", "product");

    const ProductImg = document.createElement("img");
    ProductImg.setAttribute("class", 'style_product-img"');
    ProductImg.setAttribute("src", "img/4.png");

    const productDesc = document.createElement("div");
    productDesc.setAttribute("class", "product_desc");

    const productName = document.createElement("p");
    productName.setAttribute("class", "product_name");

    const priceBox = document.createElement("div");
    priceBox.setAttribute("class", "price-box");

    const amount = document.createElement("span");
    amount.setAttribute("class", "amount");

    const won = document.createElement("span");
    won.setAttribute("class", "won");

    //프로덕트랩//

    //붙여넣기

    //테스트

    const testLink = document.createElement("a");
    testLink.setAttribute("href", stylePerLink);

    //테스트

    productImgBox.appendChild(ProductImg);
    productDesc.appendChild(productName);
    productDesc.appendChild(priceBox);
    productWrap.appendChild(productImgBox);
    productWrap.appendChild(productDesc);

    priceBox.appendChild(amount);
    priceBox.appendChild(won);

    smileLink.appendChild(smileI);
    smileLink.appendChild(styleLikeCount);

    smileComentLink.appendChild(commentI);
    smileComentLink.appendChild(styleCommentCount);

    styleBtnBox.appendChild(smileLink);
    styleBtnBox.appendChild(smileComentLink);

    styleUserBox.appendChild(styleImgProfile);
    styleUserBox.appendChild(styleFeedPtag);

    styleFeedHref.appendChild(styleUserBox);
    styleFeedHref.appendChild(styleTextBox);

    styleFeedDetail.appendChild(styleFeedHref);
    styleFeedDetail.appendChild(styleBtnBox);

    styleFeedBox.appendChild(styleFeed);
    styleFeedBox.appendChild(styleFeedDetail);
    styleFeedBox.appendChild(productWrap);
    styleWantDisplay.appendChild(styleFeedBox);

    styleEntireBox.appendChild(styleWantDisplay);

    //데이터 삽입부

    styleTextBox.innerText = styleDatas.contents;
    styleFeedPtag.innerText = styleDatas.username;
    productName.innerText = styleDatas.productName;
    amount.innerText = styleDatas.price.toLocaleString("en");
    won.innerText = "원";
    styleLikeCount.innerText = styleDatas.like;
    styleCommentCount.innerText = styleDatas.commentCount;

    //데이터 삽입부

    function changeHeart() {
      console.log("요기까지들어와써");
      if (smileI.classList.contains("fa-face-smile"))
        smileI.classList.replace("fa-face-smile", "fa-heart");
      else smileI.classList.replace("fa-heart", "fa-face-smile");

      // smileI.classList.toggle("fa-face-smile");
      // smileI.classList.toggle("fa-heart");
    }
  });
}

function urlFunction(url, userId) {
  location.href = url + userId;
}
