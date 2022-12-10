package hello.domain.entity;

import hello.domain.dto.PostDto;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@ToString
public class Post {

    @Id
    private Long id;

    private String subject;

    private String content;

    private String writer;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public Post(Long id, String subject, String content, String writer) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.writer = writer;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Post(PostDto.SaveRequestDto request) {
        this.id = request.getId();
        this.subject = request.getSubject();
        this.content = request.getContent();
        this.writer = request.getWriter();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

    }
}
