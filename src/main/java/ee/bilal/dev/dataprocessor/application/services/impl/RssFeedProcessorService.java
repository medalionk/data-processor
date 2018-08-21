package ee.bilal.dev.dataprocessor.application.services.impl;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;
import ee.bilal.dev.dataprocessor.application.services.ProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RssFeedProcessorService implements ProcessorService<List<FeedDTO>, List<FeedDTO>> {

    @Override
    public List<FeedDTO> process(List<FeedDTO> feeds) {
        return feeds;
    }

}
