db.bulk.bulkWrite(
    [
      {insertOne: {doc:1, order:1}},
      {insertOne: {doc:2, order:2}},
      {insertOne: {doc:3, order:3}},
      {insertOne: {doc:4, order:4}},
      {insertOne: {doc:5, order:5}},
          {
                deleteOne: {
                      filter:{doc:3}
                }
          },
          {
                updateOne: {
                      filter: {
                            doc:2
                      },
                      update: {
                            $set: {doc:12}
                      }
                }
          }
    ],
    {ordered: false} //순서 상관없이 성능 최적화되어 쿼리 실행
)

//실제 쿼리를 날려 카운트하게 됨
db.bulk.countDoucuments();

//예상되는 카운트 수를 반환
db.sample_data.estimatedDocumentCount();


//query값을 먼저 찾아 반환해 준후 , update에 있는 연산을 수행함
//몽고DB에는 auto_increment 같은 기능을 제공하지 않기 떄문에, 아래 쿼리문을 활용하여 구현하곤 함
//max()함수로 마지막 값 +1 하는 방법도 있지만, 해당 방법은 동시성 이슈가 있어서 잘 사용하지 않음
db.bulk.findAndModify({
  query : {doc:5},
  sort : {order: -1},
  update : {$inc: {doc:1}}
});

//autoIncrement 활용 예시

db.sequence.insertOne({
  sec : 0 //최초 컬렉션 생성 및 시퀀스를 0으로 초기화
})

db.sequence.findAndModify({
  query:{}, //모든 다큐먼트 대상
  sort : {sec: -1}, //seq의 마지막 값을 반환
  update : {$inc: {sec : 1}} //반환 받은 sec 필드값을 +1
})