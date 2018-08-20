package ee.bilal.dev.dataprocessor.application.services.impl;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;
import ee.bilal.dev.dataprocessor.application.mappers.FeedMapper;
import ee.bilal.dev.dataprocessor.application.services.BaseGenericService;
import ee.bilal.dev.dataprocessor.application.services.DataProcessorService;
import ee.bilal.dev.dataprocessor.configurations.ApplicationConfig;
import ee.bilal.dev.dataprocessor.domain.model.Feed;
import ee.bilal.dev.dataprocessor.domain.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Service
public class FeedDataProcessorService extends BaseGenericService<Feed, FeedDTO>
        implements DataProcessorService<FeedDTO> {

    private final RssFeedProducerService producer;
    private final RssFeedProcessorService processor;
    private final ApplicationConfig config;

    @Autowired
    public FeedDataProcessorService(
            FeedRepository repository, FeedMapper mapper, RssFeedProducerService producer,
            RssFeedProcessorService processor, ApplicationConfig config) {
        super(FeedDataProcessorService.class, repository, mapper);

        this.producer = producer;
        this.processor = processor;
        this.config = config;
    }

    @PostConstruct
    public void init(){
        final Function<List<FeedDTO>, List<FeedDTO>> success = feeds -> {
            List<FeedDTO> processed = processor.process(feeds);
            logger.info("Processed feeds '{}'", processed);
            return processed;
        };

        final Function<List<FeedDTO>, List<FeedDTO>> saveFeeds = feeds -> {
            List<FeedDTO> savedFeeds = saveAll(feeds);
            logger.info("Saved feeds '{}'", savedFeeds);
            return savedFeeds;
        };

        final Consumer<Exception> error = x -> {
            logger.error(x.getMessage());
        };

        final String feedUrl = config.getRssFeedUrl();
        final long delay = config.getFeedDelay();

        producer.produce(feedUrl, delay, success.andThen(saveFeeds), error);
    }
}
