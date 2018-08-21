package ee.bilal.dev.dataprocessor.application.services;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;

import java.util.List;

/**
 * Created by bilal90 on 8/19/2018.
 */
public interface FeedService extends GenericService<FeedDTO>{
    /**
     * Fetch last 10 feed entries
     * @return 10 feeds
     */
    List<FeedDTO> getFeeds();
}
