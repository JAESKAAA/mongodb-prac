package hello.domain.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

public abstract class BaseHistoryInfo {

    //랜덤한 String으로 저장되는 것으로 보임
    @Id
    @Field(value = "_id", targetType = FieldType.OBJECT_ID)
    protected String id;

    /**
     * EnableMongoAuditing 활성화 되어야 적용되는점 참고
     */
    @Field("created_at")
    @CreatedDate
    protected LocalDateTime createdAt;

    //TODO  수정 시간 적용이 안되는 것으로 보이는데, 추후에 찾아보기
    @Field("updated_at")
    @LastModifiedDate
    protected LocalDateTime updatedAt;

}
