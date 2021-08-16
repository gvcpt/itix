package itix.rest.spark.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"itix.core", "itix.rest.spark"})
public class RestAppConfiguration {

}
