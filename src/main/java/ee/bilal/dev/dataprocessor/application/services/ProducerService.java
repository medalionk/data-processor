package ee.bilal.dev.dataprocessor.application.services;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by bilal90 on 8/19/2018.
 */
public interface ProducerService<T, R> {
    void produce(Function<T, R> success, Consumer<Exception> error);
}
