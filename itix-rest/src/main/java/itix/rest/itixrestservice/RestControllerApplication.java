package itix.rest.itixrestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"itix.core.service", "itix.rest.itixrestservice"})
public class RestControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestControllerApplication.class, args);
    }

}
