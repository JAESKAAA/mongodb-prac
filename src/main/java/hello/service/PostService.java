package hello.service;

import hello.domain.dto.PostDto;
import hello.domain.entity.Post;
import hello.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository repository;

    public Post save(PostDto.SaveRequestDto request) {

        Post post = new Post(request);

        Post savedEntity = repository.save(post);

        log.info("saved entity ={}", savedEntity);

        return savedEntity;
    }

    public Post findById(Long id) throws NotFoundException {

        Post post = repository.findById(id).orElseThrow(NotFoundException::new);

        log.info("find entity ={}", post);

        return post;
    }
}
