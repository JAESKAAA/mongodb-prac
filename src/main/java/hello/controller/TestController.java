package hello.controller;

import hello.domain.dto.PostDto;
import hello.domain.entity.Post;
import hello.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final PostService postService;

    @PostMapping
    public String save(@RequestBody PostDto.SaveRequestDto request) {

        Post entity = postService.save(request);

        return entity.getSubject();
    }
}
