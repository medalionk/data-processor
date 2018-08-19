package ee.bilal.dev.dataprocessor.application.services;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by bilal90 on 8/19/2018.
 */
public interface Producer {
    void produce(Function<List<FeedDTO>, List<FeedDTO>> success, Consumer<Exception> error);
}
