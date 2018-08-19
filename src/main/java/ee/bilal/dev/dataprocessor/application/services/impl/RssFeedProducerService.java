package ee.bilal.dev.dataprocessor.application.services.impl;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;
import ee.bilal.dev.dataprocessor.application.services.ProducerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Service
public class RssFeedProducerService implements ProducerService<List<FeedDTO>, List<FeedDTO>> {
    @Override
    public void produce(Function<List<FeedDTO>, List<FeedDTO>> success, Consumer<Exception> error) {

    }
}
