
//테스트 데이터 삽입
db.orders.insertMany([
  {_id:0, name:"Pepperoni", size:"small", price: 19, quantity : 10, date: ISODate("2021-03-13T08:14:30Z")},
  {_id:1, name:"Pepperoni", size:"medium", price: 20, quantity : 20, date: ISODate("2021-03-13T09:13:24Z")},
  {_id:2, name:"Pepperoni", size:"large", price: 21, quantity : 30, date: ISODate("2021-03-17T09:22:27Z")},
  {_id:3, name:"Cheese", size:"small", price: 12, quantity : 15, date: ISODate("2021-03-13T11:21:30Z")},
  {_id:4, name:"Cheese", size:"medium", price: 13, quantity : 50, date: ISODate("2022-01-12T21:23:30Z")},
  {_id:5, name:"Cheese", size:"large", price: 14, quantity : 10, date: ISODate("2022-01-12T05:08:13Z")},
  {_id:6, name:"Vegan", size:"small", price: 17, quantity : 10, date: ISODate("2021-01-13T05:08:30Z")},
  {_id:7, name:"Vegan", size:"medium", price: 18, quantity : 10, date: ISODate("2021-01-13T05:10:30Z")},
])


// 사이즈가 "medium"인 다큐먼트를 "name" 필드 기준으로 그룹핑하여, totalQuantity를 구하기
db.orders.aggregate([
  {
    $match : {
      size : "medium"
    }
  },
  {
    $group : {
      _id: {$getField: "name"},
      totalQuantity: {
        $sum : {$getField: "quantity"}
      }
    }
  }
])

//위 쿼리는 아래와같이 축약하여 사용가능함
db.orders.aggregate([
  {
    $match : {
      size : "medium"
    }
  },
  {
    $group : {
      _id: "$name",
      totalQuantity: {
        $sum : "$quantity"
      }
    }
  }
])

//2년간 주문 내역중, 날짜별로 그룹핑하여, 전체 매출, 평균 주문 수량 출력하며, 매출을 내림차순으로 정렬
db.orders.aggregate([
  {
    $match: {
      date : {
        $gte : new ISODate("2020-01-30"),
        $lt : new ISODate("2022-01-30")
      }
    }
  },
  {
    $group : {
      _id: {
        $dateToString:{
          format : "%Y-%m-%d",
          date: "$date"
        }
      },
      totalSalesValue : {
        $sum: {
          $multiply: ["$price", "$quantity"]
        }
      },
      averageQuantity: {
        $avg : "$quantity"
      }
    }
  },
  {
    $sort : {
      totalSalesValue : -1
    }
  }
])

