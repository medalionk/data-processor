package ee.bilal.dev.dataprocessor.application.services;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;
import ee.bilal.dev.dataprocessor.application.mappers.FeedMappers;
import ee.bilal.dev.dataprocessor.application.services.impl.RssFeedProcessorService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by bilal90 on 8/22/2018.
 */
@RunWith(SpringRunner.class)
public class RssFeedProcessorServiceTest extends BaseServiceTest{

    @Autowired
    private RssFeedProcessorService processorService;

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void process_whenValidParams_shouldCallSuccessCallback() throws InterruptedException {
        final List<FeedDTO> expected = FeedMappers.FEED_MAPPER.toDTOs(feeds);
        final List<FeedDTO> actual = processorService.process(expected);

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertThat(actual.size(), is(equalTo(expected.size())));

        final int descLength = actual.get(0).getDescription().length();
        final boolean isSameLength = actual.stream()
                .allMatch(x -> x.getDescription().length() == descLength);

        assertTrue(isSameLength);
    }

    @Test
    public void produce_whenEmptyListParam_shouldReturnEmptyList() {
        // given
        final List<FeedDTO> feeds = new ArrayList<>();

        // when
        final List<FeedDTO> actual = processorService.process(feeds);

        // then
        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void produce_whenNullFeedsParam_shouldThrowIllegalArgumentException() {
        // when
        processorService.process(null);
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

}
