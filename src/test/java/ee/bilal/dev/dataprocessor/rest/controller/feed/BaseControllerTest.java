package ee.bilal.dev.dataprocessor.rest.controller.feed;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static java.util.Collections.singletonList;
import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * Created by bilal90 on 8/22/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public abstract class BaseControllerTest {

    private static final String BASE_HOST = "http://localhost";
    private static final String BASE_PATH = "/api/1.0";

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_HOST;
        RestAssured.basePath = BASE_PATH;

        RestAssured.filters(singletonList(new ResponseLoggingFilter()));
        RestAssured.filters(singletonList(new RequestLoggingFilter()));

        RestAssured.port = port;
    }

    @After
    public void tearDown() {

    }

    ValidatableResponse doGetThen(String path) {
        return prepareGetWhen()
                .get(path)
                .then();
    }

    RequestSpecification prepareGetWhen() {
        return given()
                .contentType(String.valueOf(APPLICATION_JSON))
                .when();
    }

}
