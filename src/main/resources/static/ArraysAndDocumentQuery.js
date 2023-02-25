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

//배열 다큐먼트 수정

db.students.updateOne(
    {_id: 1, grades: 80}, //_id 1번의 80이라는 데이터
    {$set: {"grades.$": 82}} // 쿼리된 데이터값을 82로 변경
)

db.students.updateMany(
    {}, //모든 다큐먼트
    {$inc : {"grades.$[]" : 10}} // grades 배열을 값을 전부 10씩 증가시킴
)

//테스트 배열 삽입
db.students.insertMany([
      {
        _id :4,
        grades : [
          {grade : 90, mean : 75, std : 8},
          {grade : 87, mean : 90, std : 6},
          {grade : 85, mean : 85, std : 8},
        ]
      }
    ]
)

//필드내 배열의 특정값이 87이상인 데이터만 100으로 변경
db.students.updateMany(
    {_id:4},
    {$set: {"grades.$[element].grade" : 100}}, //아래 필터에 걸린 데이터를 100으로 변경
    {arrayFilters : [{"element.grade": {$gte: 87}}]} //배열안에 grade라는 필드값이 87이상인 배열 값에만 적용
)

//카트 데이터 삽입
db.students.insertOne({
  _id: 5,
  cart : ["banana", "apple", "mango"]
});

//배열에 특정 요소 삽입 (값이 없는 경우만 들어가게 됨)
db.students.updateOne(
    {_id:5},
    {$addToSet: {cart : "beer"}}
)


//배열에서 특정 요소만 제거
db.students.updateOne(
    {_id:5},
    {$pull : {cart : "banana"}}
)

//pop을 1로주면 가장 뒤에 있는 값을 제거함
db.students.updateOne(
    {_id: 5},
    {$pop : {cart : -1}} // -1의 경우 역순이라 맨 앞의 값을 제거
)

//push의 경우도 제일 마지막에 값을 추가하는 연산자
db.students.updateOne(
    {_id: 5},
    {$push : {cart : "popcorn"}}
)

