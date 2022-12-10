package hello.controller.swagger;

import hello.config.SwaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerForController extends SwaggerConfig {

    @Bean(name = "test")
    public Docket docket() {
        return super.docket();
    }
}
