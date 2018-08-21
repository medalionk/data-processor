package ee.bilal.dev.dataprocessor.application.services;

/**
 * Created by bilal90 on 8/19/2018.
 */
public interface ProcessorService<T, R> {
    /**
     * Process data with any rules
     * @param t to process
     * @return processed data
     */
    R process(T t);
}
