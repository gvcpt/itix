package itix.batch.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"itix.core", "itix.batch"})
public class AppConfiguration {

}
