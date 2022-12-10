package hello.service;

import hello.domain.dto.PostDto;
import hello.domain.dto.PostDto.UpdateRequestDto;
import hello.domain.entity.Post;
import hello.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository repository;

    public Post save(PostDto.SaveRequestDto request) {
        return repository.save(new Post(request));
    }

    public Post findById(String id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found Id => {}" + id));
    }

    public List<Post> findAll() {
        return repository.findAll();
    }

    public Post update(String id, UpdateRequestDto request) {
        Post post = findById(id);
        post.update(request);
        return post;
    }

    public void delete(String id) {
        Post post = findById(id);
        repository.delete(post);
    }
}
