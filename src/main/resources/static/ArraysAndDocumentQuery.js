//배열과 내장 Document 다루는 법

//내장 다큐먼트를 조회할 때, 해당 다큐먼트의 필드값을 전부 쿼리해줘야 값을 찾아 올 수 있음 (순서도 동일 해야 한다.)
db.sales.findOne({
  customer: {
    gender: "M",
    age : 61,
    email : "ucikosusu@sid.uz",
    satisfaction: 1
  }
});

//상기 방안처럼 조회하면 번거롭기 때문에, 내장다큐먼트 내 필드값을 이용해 쿼리하곤 한다.
db.sales.findOne({
  "customer.email" : "ucikosusu@sid.uz"
})

//연산자도 사용 가능
db.sales.find({
  "customer.age" : {$lt: 20}
})

//테스트 데이터 삽입
db.inventory.insertMany([
  { item: "journal", qty: 25, tags: ["blank", "red"], dim_cm: [ 14, 21 ] },
  { item: "notebook", qty: 50, tags: ["red", "blank"], dim_cm: [ 14, 21 ] },
  { item: "paper", qty: 100, tags: ["red", "blank", "plain"], dim_cm: [ 14, 21 ] },
  { item: "planner", qty: 75, tags: ["blank", "red"], dim_cm: [ 22.85, 30 ] },
  { item: "postcard", qty: 45, tags: ["blue"], dim_cm: [ 10, 15.25 ] },
  { item: "postcard", qty: 45, tags: ["blue, red"], dim_cm: [ 13, 14 ] }
]);

// 배열도 내장 다큐먼트와 마찬가지로 값이 전부 일치해야 find로 찾아올 수 있음
db.inventory.find({
  tags : [ 'red']
})

//$all 연산자로 쿼리하면, 해당 값이 포함된 다큐먼트 전부를 찾아줌
db.inventory.find({
  tags : {$all : ["blank"]}
})

//이런식으로도 쿼리 가능함
db.inventory.find({
  tags : 'blank'
})

//Between 연산을 기대하고 쿼리했지만, 하기 연산은 Or조건으로 묶이게 됨
//따라서, 15 초과인 값 전부 + 20 미만에 해당되는 값 모두를 쿼리하게됨
db.inventory.find({
  dim_cm : {$gt : 15, $lt: 20}
})

//두조건이 모두 true인 값들만 쿼리하게 된다.
db.inventory.find({
  dim_cm : {$elemMatch : {$gt: 15, $lt: 20}}
})

//배열의 인덱스 번호로 조회하는 경회
db.inventory.find({
  "dim_cm.0" : {$lt : 20} //배열 첫번쨰 인덱스값이 20 미만인 것
})

//배열의 사이즈 기준으로 조회

db.inventory.find({
  tags : {$size : 2} //tags의 사이즈가 2인 다큐먼트 조
})

//쿼리 프로젝션을 통해, 배열에서 쿼리 조건에 값에 만족하는 배열 값만 리턴
db.sales.find(
    {
      items: {
        $elemMatch: {
          name: 'binder',
          quantity: {$gte: 10}
        }
      }
    },
    {
      saleDate: 1,
      "items.$": 1, //쿼리 프로젝션부분
      storeLocation: 1,
      customer: 1
    }
)
