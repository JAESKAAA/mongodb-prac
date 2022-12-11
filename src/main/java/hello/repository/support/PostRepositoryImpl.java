package hello.repository.support;

import static hello.domain.entity.QPost.post;

import hello.domain.entity.Post;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl {

    private final PostRepositoryCustom repository;

    public List<Post> findAllBySubject(String subject) {
        return repository.findAllBySubject(post.subject.eq(subject), post.createdAt.desc());

    }
}
