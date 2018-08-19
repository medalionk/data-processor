package ee.bilal.dev.dataprocessor.application.services;

/**
 * Created by bilal90 on 8/19/2018.
 */
public interface ProcessorService<T, R> {
    R process(T t);
}
