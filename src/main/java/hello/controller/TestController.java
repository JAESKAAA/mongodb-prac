package hello.controller;

import hello.domain.dto.PostDto;
import hello.domain.dto.PostDto.ResponseDto;
import hello.domain.entity.Post;
import hello.service.PostService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto save(@RequestBody PostDto.SaveRequestDto request) {

        return postService.save(request).toResponse();
    }

    @GetMapping("/{id}")
    public ResponseDto find(@PathVariable String id) {

        return postService.findById(id).toResponse();
    }

    @GetMapping
    public List<ResponseDto> findAll() {

        return postService.findAll()
            .stream()
            .map(Post::toResponse)
            .collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    public ResponseDto update(@PathVariable String id,
                              @RequestBody PostDto.UpdateRequestDto request) {

        return postService.update(id, request).toResponse();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {

        postService.delete(id);
    }
}