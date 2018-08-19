package ee.bilal.dev.dataprocessor.application.services;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;

import java.util.List;

/**
 * Created by bilal90 on 8/19/2018.
 */
public interface Processor {
    List<FeedDTO> process(List<FeedDTO> list);
}
