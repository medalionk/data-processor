package ee.bilal.dev.dataprocessor.rest.controller.feed;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static ee.bilal.dev.dataprocessor.application.constants.Paths.FEEDS;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by bilal90 on 8/22/2018.
 */
public class FeedRestControllerTest extends BaseControllerTest {

    @BeforeClass
    public static void init() {

    }

    @Test
    public void whenInValidEndpoint_shouldReturnNotFoundHttpCode() {
        prepareGetWhen()
                .get("/some-endpoint")
                .then().statusCode(SC_NOT_FOUND);
    }

    @Test
    public void fetchFeeds_whenInvalidMethod_shouldReturnMethodNotAllowedHttpCode() {
        prepareGetWhen()
                .delete(FEEDS)
                .then().statusCode(SC_METHOD_NOT_ALLOWED);
    }

    @Test
    public void fetchFeeds_whenValidCall_shouldReturnOkHttpCode() {
        prepareGetWhen()
                .get(FEEDS)
                .then().statusCode(SC_OK);
    }

    @Test
    public void fetchFeeds_whenValidCall_shouldReturnLast10Feeds() {
        List<FeedDTO> actual = prepareGetWhen()
                .get(FEEDS)
                .then().statusCode(SC_OK).extract().jsonPath().get("");

        final int feedCount = 10;

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertThat(actual.size(), is(equalTo(feedCount)));
    }

    @After
    public void tearDown() {

    }

}