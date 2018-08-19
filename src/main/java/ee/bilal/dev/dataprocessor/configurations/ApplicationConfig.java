package ee.bilal.dev.dataprocessor.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Component
@Configuration
@PropertySource(value = "classpath:application.properties", encoding="UTF-8")
public class ApplicationConfig {
    @Getter
    @Value("${rss-feed-url}")
    private String rssFeedUrl;

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+3"));;
    }
}
