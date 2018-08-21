package ee.bilal.dev.dataprocessor.application.services;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by bilal90 on 8/19/2018.
 */
public interface ProducerService<T, R> {
    /**
     * Read input data from any http source
     * @param url to read data
     * @param delay in seconds between read
     * @param success function to call on success
     * @param error consumer on error
     */
    void produce(final String url, final long delay, Function<T, R> success, Consumer<Exception> error);
}
