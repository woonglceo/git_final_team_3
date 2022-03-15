console.log("연동이되엇네요");

fetch("/shoeCream/style/getMyList").then((res) =>
  res.json().then((data) => {
    console.log(data);
    const MyListData = data;

    console.log(MyListData);
  })
);
