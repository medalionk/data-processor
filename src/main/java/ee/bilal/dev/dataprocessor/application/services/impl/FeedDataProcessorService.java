package ee.bilal.dev.dataprocessor.application.services.impl;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;
import ee.bilal.dev.dataprocessor.application.mappers.FeedMapper;
import ee.bilal.dev.dataprocessor.application.services.BaseGenericService;
import ee.bilal.dev.dataprocessor.application.services.DataProcessorService;
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
    private final FeedRepository feedRepository;

    @Autowired
    public FeedDataProcessorService(
            FeedRepository repository, FeedMapper mapper, RssFeedProducerService producer,
            RssFeedProcessorService processor) {
        super(FeedDataProcessorService.class, repository, mapper);

        this.producer = producer;
        this.processor = processor;
        this.feedRepository = repository;
    }

    @PostConstruct
    public void init(){
        final Function<List<FeedDTO>, List<FeedDTO>> success = feeds -> {
            List<FeedDTO> processed = processor.process(feeds);
            return processed;
        };

        final Function<List<FeedDTO>, List<FeedDTO>> saveFeeds = feeds -> {
            List<FeedDTO> savedFeeds = saveAll(feeds);
            return savedFeeds;
        };

        final Consumer<Exception> error = x -> {
            logger.info(x.getMessage());
        };
        producer.produce(success.andThen(saveFeeds), error);
        //System.out.println(feeds);
    }
}
