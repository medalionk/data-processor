package ee.bilal.dev.dataprocessor.application.services.impl;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;
import ee.bilal.dev.dataprocessor.application.services.ProducerService;
import ee.bilal.dev.dataprocessor.util.DateUtil;
import ee.bilal.dev.dataprocessor.util.UrlUtil;
import ee.bilal.dev.dataprocessor.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Service
@Slf4j
public class RssFeedProducerService implements ProducerService<List<FeedDTO>, List<FeedDTO>> {

    private ScheduledExecutorService executorService = null;

    @Autowired
    public RssFeedProducerService() {
    }

    @Override
    public void produce(final String url, final long delay, final Function<List<FeedDTO>,List<FeedDTO>> success,
                        final Consumer<Exception> error) {

        ValidationUtil.validatePropertyNotNull(success, "Success callback");
        ValidationUtil.validatePropertyNotNull(error, "Error callback");

        if(!UrlUtil.isValidUrl(url)){
            throw new IllegalArgumentException(String.format("URL '%s' is not valid!!!", url));
        }

        if(delay <= 0){
            throw new IllegalArgumentException("Delay cannot be 0 or negative value");
        }

        final Runnable task = fetchRssFeedTask(url, success, error);
        final long initialDelay = 1;

        try {
            executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleWithFixedDelay(task, initialDelay, delay, TimeUnit.SECONDS);
        } catch (Exception ex) {
            log.error("Error running ExecutorService '{}'", ex);
        }
    }

    /**
     *
     * @param service ExecutorService
     * @param timeout in seconds
     */
    private void shutdownAndAwaitTermination(ExecutorService service, int timeout) {
        ValidationUtil.validatePropertyNotNull(service, "ExecutorService");

        service.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!service.awaitTermination(timeout, TimeUnit.SECONDS)) {
                service.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!service.awaitTermination(timeout, TimeUnit.SECONDS))
                    log.info("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            service.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Create a runnable task to fetch RSS Feeds
     * @param feedUrl to fetch
     * @param success callback on success
     * @param error callback on error
     * @return created task
     */
    private Runnable fetchRssFeedTask(final String feedUrl, final Function<List<FeedDTO>,List<FeedDTO>> success,
                                      final Consumer<Exception> error){
        return () -> {
            try {
                final List<FeedDTO> feeds = fetchRssFeed(feedUrl);
                success.apply(feeds);
            }
            catch (Exception ex) {
                log.error("Error getting feed '{}'", ex);
                error.accept(ex);
            }
        };
    }

    /**
     * Connect to feed url and fetch feed using Rome library
     * @param url of RSS Feed
     * @return list of feed
     * @throws IOException on IO error when creating XMLReader
     * @throws FeedException on error when fetching feed
     */
    private List<FeedDTO> fetchRssFeed(final String url) throws IOException, FeedException {
        try {
            final List<FeedDTO> feeds = new ArrayList<>();

            log.info("Get RSS feed from url '{}'", url);
            URL feedUrl = new URL(url);

            final SyndFeedInput input = new SyndFeedInput();
            final SyndFeed syndFeed = input.build(new XmlReader(feedUrl));
            final List<SyndEntry> entries = syndFeed.getEntries();
            log.info("'{}' RSS feed found", entries.size());

            for (SyndEntry entry : entries) {
                final String link = entry.getLink();
                final String title = entry.getTitle();
                final LocalDateTime date = DateUtil.toLocalDateTime(entry.getPublishedDate());

                final FeedDTO feed = FeedDTO.of(link, title, date);

                final SyndContent content = entry.getDescription();
                if(content != null){
                    feed.setDescription(content.getValue());
                }

                final List<SyndContent> contents = entry.getContents();
                if(contents != null && !contents.isEmpty()){
                    feed.setContents(contents.toString());
                }

                feeds.add(feed);
            }

            return feeds;
        }
        catch (MalformedURLException ex) {
            log.error("Malformed url '{}'", ex);
            throw ex;
        } catch (FeedException ex) {
            log.error("Error fetching feed '{}'", ex);
            throw ex;
        } catch (IOException ex) {
            log.error("IO error '{}'", ex);
            throw ex;
        }
    }

    @PreDestroy
    public void destroy() {
        log.info("Shutting down ExecutorService");

        final int shutdownTimeout = 30;
        shutdownAndAwaitTermination(executorService, shutdownTimeout);
    }
}
