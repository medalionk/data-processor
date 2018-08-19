package ee.bilal.dev.dataprocessor.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Component
@Configuration
@PropertySource(value = "classpath:application.properties", encoding="UTF-8")
public class ApplicationConfig {

}
