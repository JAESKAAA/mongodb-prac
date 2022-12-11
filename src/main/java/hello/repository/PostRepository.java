package hello.repository;

import hello.domain.entity.Post;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, String> , MongoRepository<Post, String>{

    default long countAll() {
        return count();
    }

    @Query("{'subject' : {'$eq' : ?0 }}")
    List<Post> searchPosts(String subject);

}

