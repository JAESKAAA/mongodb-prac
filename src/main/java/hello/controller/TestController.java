package hello.controller;

import hello.domain.dto.PostDto;
import hello.domain.dto.PostDto.ResponseDto;
import hello.domain.dto.PostDto.SearchFilter;
import hello.domain.entity.Post;
import hello.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = "몽고DB CRUD")
@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final PostService postService;

    @PostMapping
    @ApiOperation("저장")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto save(@RequestBody PostDto.SaveRequestDto request) {

        return postService.save(request).toResponse();
    }

    @GetMapping("/{id}")
    @ApiOperation("단건 조회")
    public ResponseDto find(@PathVariable String id) {

        return postService.findById(id).toResponse();
    }

    @GetMapping
    @ApiOperation("리스트 조회")
    public List<ResponseDto> findAll() {

        return postService.findAll()
            .stream()
            .map(Post::toResponse)
            .collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    @ApiOperation("수정")
    public ResponseDto update(@PathVariable String id,
                              @RequestBody PostDto.UpdateRequestDto request) {

        return postService.update(id, request).toResponse();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("삭제")
    public void delete(@PathVariable String id) {

        postService.delete(id);
    }


    @GetMapping("/count/all")
    @ApiOperation("전체 갯수 조회")
    public Long getCount() {

        return postService.getAllCount();
    }

    @GetMapping("/subject")
    @ApiOperation("주제별 전체 쿼리")
    public List<ResponseDto> getAllBySubject(SearchFilter filter) {

        return postService.findAllBySubject(filter.getSubject())
            .stream()
            .map(Post::toResponse)
            .collect(Collectors.toList());
    }
}
