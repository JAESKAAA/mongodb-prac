package hello.domain.entity;

import hello.domain.dto.PostDto;
import hello.domain.dto.PostDto.ResponseDto;
import hello.domain.dto.PostDto.UpdateRequestDto;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.StringUtils;

@Document(collection = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseHistoryInfo {

    private String subject;

    private String content;

    private String writer;

    public Post(PostDto.SaveRequestDto request) {
        this.subject = request.getSubject();
        this.content = request.getContent();
        this.writer = request.getWriter();
    }

    public String getId() {
        return super.id;
    }

    public LocalDateTime getCreatedAt() {
        return super.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return super.updatedAt;
    }

    public ResponseDto toResponse() {
        return ResponseDto.builder()
            .id(getId())
            .subject(this.subject)
            .content(this.content)
            .writer(this.writer)
            .createdAt(getCreatedAt())
            .updatedAt(getUpdatedAt())
            .build();
    }

    public void update(UpdateRequestDto request) {
        if (StringUtils.hasText(request.getSubject())) {
            this.subject = request.getSubject();
        }

        if (StringUtils.hasText(request.getContent())) {
            this.content = request.getContent();
        }

        if (StringUtils.hasText(request.getWriter())) {
            this.writer = request.getWriter();
        }
    }
}
