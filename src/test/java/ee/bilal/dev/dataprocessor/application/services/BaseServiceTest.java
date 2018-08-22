package ee.bilal.dev.dataprocessor.application.services;

import ee.bilal.dev.dataprocessor.domain.model.Feed;
import org.junit.After;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bilal90 on 8/22/2018.
 */
@SpringBootTest
public abstract class BaseServiceTest {

    final List<Feed> feeds = new ArrayList<>();

    @Before
    public void setUp() {
        addMockData();
    }

    @After
    public void tearDown() {
        feeds.clear();
    }

    private void addMockData(){
        final String link = "https://www.bbc.co.uk/news/entertainment-arts-45258601";
        final String title = "Uber to pay $1.9m for sexual harassment claims";
        final String description = "The payout will cover former and current...";
        final String contents = "";

        feeds.add(Feed.of(link, title, description, contents, LocalDateTime.now()));
        feeds.add(Feed.of(link, title, description, contents, LocalDateTime.now()));
        feeds.add(Feed.of(link, title, description, contents, LocalDateTime.now()));
    }

}
