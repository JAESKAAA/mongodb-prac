package hello.repository.support;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import hello.domain.entity.Post;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepositoryCustom extends MongoRepository<Post, String>, QuerydslPredicateExecutor<Post> {


    List<Post> findAllBySubject(Predicate predicate, OrderSpecifier<?>... orders);
}
