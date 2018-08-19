package ee.bilal.dev.dataprocessor.application.services.impl;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;
import ee.bilal.dev.dataprocessor.application.mappers.FeedMapper;
import ee.bilal.dev.dataprocessor.application.services.BaseGenericService;
import ee.bilal.dev.dataprocessor.application.services.DataProcessorService;
import ee.bilal.dev.dataprocessor.domain.model.Feed;
import ee.bilal.dev.dataprocessor.domain.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Service
public class FeedDataProcessorService extends BaseGenericService<Feed, FeedDTO>
        implements DataProcessorService<FeedDTO> {

    @Autowired
    public FeedDataProcessorService(FeedRepository repository, FeedMapper mapper) {
        super(FeedDataProcessorService.class, repository, mapper);
    }
}
