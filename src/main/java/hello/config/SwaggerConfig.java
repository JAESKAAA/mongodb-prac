package hello.config;

import io.swagger.v3.oas.models.OpenAPI;
import javax.servlet.http.HttpServletRequest;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SwaggerConfig implements WebMvcOpenApiTransformationFilter {

    private static final String API_NAME = "몽고DB 테스트 스웨거";
    private static final String API_VERSION = "V1";

    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(getBasePackagePath()))
            .paths(PathSelectors.any())
            .build().groupName(API_NAME);
    }

    public String getBasePackagePath() {
        return this.getClass().getPackage().getName().replace(".swagger", "");
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(API_NAME)
            .version(API_VERSION)
            .build();
    }

    @Override
    public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
        return null;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return delimiter.equals(DocumentationType.OAS_30);
    }
}
