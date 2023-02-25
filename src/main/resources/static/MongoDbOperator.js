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
    ]
)