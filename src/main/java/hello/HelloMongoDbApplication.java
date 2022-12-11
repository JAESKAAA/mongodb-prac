package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class HelloMongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloMongoDbApplication.class, args);
    }
}
