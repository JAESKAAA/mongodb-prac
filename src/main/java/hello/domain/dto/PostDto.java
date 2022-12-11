package hello.domain.dto;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class PostDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SaveRequestDto {
        private String subject;
        private String content;
        private String writer;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UpdateRequestDto {
        private String subject;
        private String content;
        private String writer;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseDto {
        private String id;
        private String subject;
        private String content;
        private String writer;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SearchFilter {
        private String subject;
        private String content;
        private String writer;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
