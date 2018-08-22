package ee.bilal.dev.dataprocessor.application.services;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;
import ee.bilal.dev.dataprocessor.application.mappers.FeedMappers;
import ee.bilal.dev.dataprocessor.application.services.impl.RssFeedService;
import ee.bilal.dev.dataprocessor.domain.repository.FeedRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by bilal90 on 8/22/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class RssFeedServiceTest extends BaseServiceTest{

    @Mock
    private FeedRepository feedRepository;

    @InjectMocks
    private RssFeedService feedService;

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getFeeds_whenCall_shouldReturnRepositoryFeeds() {
        // given
        when(feedRepository.findLast10Feeds()).thenReturn(feeds);

        // when
        final List<FeedDTO> expected = FeedMappers.FEED_MAPPER.toDTOs(feeds);
        final List<FeedDTO> actual = feedService.getFeeds();

        // then
        assertFalse(actual.isEmpty());
        assertThat(actual.size(), is(equalTo(expected.size())));
        assertThat(actual, is(equalTo(expected)));
    }

    @After
    public void tearDown() {
        super.tearDown();
    }

}
