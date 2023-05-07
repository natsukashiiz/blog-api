package com.vv2dev.restfulapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "A JWT token is required to access this API. JWT token can be obtained by providing correct username and password in the <strong>Auth API</strong>"
)
@OpenAPIDefinition(info = @Info(title = "Blogs API Documents", version = "1.0.0"))
public class RestfulApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulApiApplication.class, args);
    }

    @RestController
    public static class api {
        @GetMapping
        public ResponseEntity<?> res(HttpServletRequest request) {
            String url = request.getRequestURL().toString();

            Map<String, Object> map = new LinkedHashMap<String, Object>();
            map.put("api_name", "blogs api");
            map.put("api_version", "v1");
            map.put("api_released", "2023-04-29");
            map.put("api_documentation", url + "docs/ui");
            map.put("api_status", "active");
            return ResponseEntity.ok(map);
        }
    }


//    @OpenAPIDefinition
//    @Configuration
//    @SecurityScheme(
//            name = "Bearer Authentication",
//            type = SecuritySchemeType.HTTP,
//            bearerFormat = "JWT",
//            scheme = "bearer",
//            description = "A JWT token is required to access this API. JWT token can be obtained by providing correct username and password in the User API"
//    )
//    public static class SpringDocConfig {
//        @Bean
//        public OpenAPI baseOpenApi() {
//            return new OpenAPI().info(new Info().title("Blogs API Documents").version("1.0.0"));
//        }
//    }
}
