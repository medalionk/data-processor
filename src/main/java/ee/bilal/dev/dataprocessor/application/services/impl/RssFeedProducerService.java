package ee.bilal.dev.dataprocessor.application.services.impl;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;
import ee.bilal.dev.dataprocessor.application.services.ProducerService;
import ee.bilal.dev.dataprocessor.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    public RssFeedProducerService() {
    }

    @Override
    public void produce(final String feedUrl, final long delay, final Function<List<FeedDTO>,List<FeedDTO>> success,
                        final Consumer<Exception> error) {

        if(!UrlUtil.isValidUrl(feedUrl)){
            throw new IllegalArgumentException(String.format("URL '%s' is not valid!!!", feedUrl));
        }

        if(delay <= 0){
            throw new IllegalArgumentException("Delay cannot be 0 or negative value");
        }

        final Runnable task = fetchRssFeedTask(feedUrl, success, error);
        final long initialDelay = 1;

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(task, initialDelay, delay, TimeUnit.SECONDS);
    }

    private Runnable fetchRssFeedTask(final String feedUrl, final Function<List<FeedDTO>,List<FeedDTO>> success,
                                      final Consumer<Exception> error){
        return () -> {
            try {
                final List<FeedDTO> feeds = fetchRssFeed(feedUrl);
                success.apply(feeds);
            }
            catch (Exception ex) {
                log.error("Error getting feeds '{}'", ex);
                error.accept(ex);
            }
        };
    }

    private List<FeedDTO> fetchRssFeed(final String url) throws IOException, FeedException {
        try {
            final List<FeedDTO> feeds = new ArrayList<>();

            log.info("Get RSS feeds from url '{}'", url);
            URL feedUrl = new URL(url);

            final SyndFeedInput input = new SyndFeedInput();
            final SyndFeed syndFeed = input.build(new XmlReader(feedUrl));
            final List<SyndEntry> entries = syndFeed.getEntries();
            log.info("'{}' RSS feeds found", entries.size());

            for (SyndEntry entry : entries) {
                final String link = entry.getLink();
                final String title = entry.getTitle();
                final Instant date = entry.getPublishedDate().toInstant();

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
            log.error("Error fetching feeds '{}'", ex);
            throw ex;
        } catch (IOException ex) {
            log.error("IO error '{}'", ex);
            throw ex;
        }
    }
}
