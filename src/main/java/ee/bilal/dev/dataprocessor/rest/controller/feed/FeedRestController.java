package ee.bilal.dev.dataprocessor.rest.controller.feed;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;
import ee.bilal.dev.dataprocessor.application.services.FeedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static ee.bilal.dev.dataprocessor.application.constants.Paths.FEEDS;
import static ee.bilal.dev.dataprocessor.application.constants.Paths.VERSION;


/**
 * Created by bilal90 on 8/21/2018.
 */
@CrossOrigin
@RestController
@Api(value = VERSION + FEEDS)
@RequestMapping(value = VERSION + FEEDS)
@Slf4j
public class FeedRestController {

    private final FeedService feedService;

    @Autowired
    public FeedRestController(FeedService feedService)   {
        this.feedService = feedService;
    }

    /**
     * Get last 10 entries
     * @return feed list
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get last 10 entries")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<FeedDTO>> fetchFeeds() {
        log.info("Get last 10 feed entries...");

        List<FeedDTO> jobReports = feedService.getFeeds();

        return ResponseEntity.ok(jobReports);
    }

}