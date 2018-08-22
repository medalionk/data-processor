package ee.bilal.dev.dataprocessor.application.services.impl;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;
import ee.bilal.dev.dataprocessor.application.services.ProcessorService;
import ee.bilal.dev.dataprocessor.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Service
@Slf4j
public class RssFeedProcessorService implements ProcessorService<List<FeedDTO>, List<FeedDTO>> {

    @Override
    public List<FeedDTO> process(List<FeedDTO> feeds) {
        ValidationUtil.validatePropertyNotNull(feeds, "feeds");

        final int maxDescLength = 50;
        feeds.forEach(x -> {
            final String abbreviatedDesc = StringUtils.abbreviate(x.getDescription(), maxDescLength);
            x.setDescription(abbreviatedDesc);
        });

        return feeds;
    }
}
