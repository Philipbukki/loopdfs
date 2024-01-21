package org.smunyau.loopdfs;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title="Loop DFS API Documentation",
                description = "Loop DFS Account's and Card's API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Loop dfs",
                        email = "loop.dfs@gmail.com",
                        url = "loopdfs.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Learn more about springdoc-openapi",
                url = "https://springdoc.org/"
        )
)
public class LoopDfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoopDfsApplication.class, args);
    }

}
