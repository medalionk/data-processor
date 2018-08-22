package ee.bilal.dev.dataprocessor.domain.repository;

import ee.bilal.dev.dataprocessor.domain.model.Feed;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by bilal90 on 8/22/2018.
 */
@Sql({"classpath:schema.sql", "classpath:data.sql" })
public class FeedRepositoryTest extends BaseRepositoryTest<Feed>{

    @Autowired
    private FeedRepository feedRepository;

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void findLast10Feeds_whenEmptyDb_shouldReturnEmptyList() {
        // given
        feedRepository.deleteAll();

        // when
        List<Feed> actual = feedRepository.findLast10Feeds();

        // then
        assertTrue(actual.isEmpty());
    }

    @Test
    public void findLast10Feeds_whenMoreThan10FeedsInDb_shouldReturnLast10Feeds() {
        // given
        List<Feed> feeds = feedRepository.findAll();

        final int feedCount = 10;
        final int size = feeds.size();

        List<Feed> expected = feeds.subList(size - feedCount, size);

        // when
        List<Feed> actual = feedRepository.findLast10Feeds();

        // then
        assertFalse(actual.isEmpty());
        assertThat(actual.size(), is(equalTo(feedCount)));
        assertTrue(expected.containsAll(actual));
    }

    @Test
    @Sql({ "classpath:partial_data.sql" })
    public void findLast10Feeds_whenLessThan10FeedsInDb_shouldReturnAllFeeds() {
        // given
        List<Feed> expected = feedRepository.findAll();

        final int feedCount = expected.size();

        // when
        List<Feed> actual = feedRepository.findLast10Feeds();

        // then
        assertFalse(actual.isEmpty());
        assertThat(actual.size(), is(equalTo(feedCount)));
        assertTrue(expected.containsAll(actual));
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

}
