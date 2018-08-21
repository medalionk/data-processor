package ee.bilal.dev.dataprocessor.application.services.impl;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;
import ee.bilal.dev.dataprocessor.application.mappers.FeedMapper;
import ee.bilal.dev.dataprocessor.application.mappers.FeedMappers;
import ee.bilal.dev.dataprocessor.application.services.BaseGenericService;
import ee.bilal.dev.dataprocessor.application.services.FeedService;
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
public class RssFeedService extends BaseGenericService<Feed, FeedDTO> implements FeedService {

    private final RssFeedProducerService producer;
    private final RssFeedProcessorService processor;
    private final ApplicationConfig config;
    private final FeedRepository feedRepository;

    @Autowired
    public RssFeedService(
            FeedRepository repository, FeedMapper mapper, RssFeedProducerService producer,
            RssFeedProcessorService processor, ApplicationConfig config) {
        super(RssFeedService.class, repository, mapper);

        this.producer = producer;
        this.processor = processor;
        this.config = config;
        this.feedRepository = repository;
    }

    @PostConstruct
    public void init(){
        final String feedUrl = config.getRssFeedUrl();
        final long delay = config.getFeedDelay();

        producer.produce(feedUrl, delay, onSuccess().andThen(saveFeeds()), onError());
    }

    @Override
    public List<FeedDTO> getFeeds() {
        List<Feed> feeds = feedRepository.findLast10Feeds();
        return FeedMappers.FEED_MAPPER.toDTOs(feeds);
    }

    private Function<List<FeedDTO>, List<FeedDTO>> onSuccess(){
        return feeds -> {
            List<FeedDTO> processed = processor.process(feeds);
            logger.info("Processed feeds '{}'", processed);

            return processed;
        };
    }

    private Function<List<FeedDTO>, List<FeedDTO>> saveFeeds(){
        return feeds -> {
            List<FeedDTO> savedFeeds = saveAll(feeds);
            logger.info("Saved feeds '{}'", savedFeeds);

            return savedFeeds;
        };
    }

    private Consumer<Exception> onError(){
        return x -> {
            logger.error(x.getMessage());
        };
    }
}
