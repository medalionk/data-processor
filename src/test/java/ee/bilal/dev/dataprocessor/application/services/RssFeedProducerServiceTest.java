package ee.bilal.dev.dataprocessor.application.services;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;
import ee.bilal.dev.dataprocessor.application.services.impl.RssFeedProducerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

/**
 * Created by bilal90 on 8/22/2018.
 */
@RunWith(SpringRunner.class)
public class RssFeedProducerServiceTest extends BaseServiceTest{

    private final String url = "http://feeds.bbci.co.uk/news/world/rss.xml";
    private final long delay = 5;

    @Mock
    private Function<List<FeedDTO>, List<FeedDTO>> success;

    @Mock
    private Consumer<Exception> error;

    @Autowired
    private RssFeedProducerService producerService;

    @Before
    public void setUp() {

    }

    @Test
    public void produce_whenValidParams_shouldCallSuccessCallback() throws InterruptedException {
        // when
        producerService.produce(url, delay, success, error);

        final long sleepDelay = 2000;
        final int invocationCount = 1;

        Thread.sleep(sleepDelay);

        // then
        verify(success, atLeast(invocationCount)).apply(any(List.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void produce_whenEmptyUrlString_shouldThrowIllegalArgumentException() {
        // given
        final String emptyUrl = "";

        // when
        producerService.produce(emptyUrl, delay, success, error);
    }

    @Test(expected = IllegalArgumentException.class)
    public void produce_whenInvalidUrl_shouldThrowIllegalArgumentException() {
        // given
        final String invalidUrl = "feeds.bbci.co.uk/news/world/rss.xml";

        // when
        producerService.produce(invalidUrl, delay, success, error);
    }

    @Test(expected = IllegalArgumentException.class)
    public void produce_whenNullUrlParam_shouldThrowIllegalArgumentException() {
        // when
        producerService.produce(null, delay, success, error);
    }

    @Test(expected = IllegalArgumentException.class)
    public void produce_whenInvalidDelayParam_shouldThrowIllegalArgumentException() {
        // when
        producerService.produce(url, 0, success, error);
    }

    @Test(expected = IllegalArgumentException.class)
    public void produce_whenNullSuccessParam_shouldThrowIllegalArgumentException() {
        // when
        producerService.produce(url, delay, null, error);
    }

    @Test(expected = IllegalArgumentException.class)
    public void produce_whenNullErrorParam_shouldThrowIllegalArgumentException() {
        // when
        producerService.produce(url, delay, success, null);
    }

    @After
    public void tearDown() {

    }

}
