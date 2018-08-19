package ee.bilal.dev.dataprocessor.application.mappers;

import ee.bilal.dev.dataprocessor.application.dtos.FeedDTO;
import ee.bilal.dev.dataprocessor.domain.model.Feed;
import org.mapstruct.*;

import java.util.List;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Mapper(componentModel = "spring")
public interface FeedMapper extends BaseMapper<FeedDTO, Feed> {
    @Mappings({
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "lastModifiedDate", ignore = true),
    })
    @Named("toEntity")
    Feed toEntity(FeedDTO dto);

    @Named("toDTO")
    FeedDTO toDTO(Feed entity);

    @IterableMapping(qualifiedByName="toEntity")
    List<Feed> toEntities(List<FeedDTO> dtos);

    @IterableMapping(qualifiedByName="toDTO")
    List<FeedDTO> toDTOs(List<Feed> entities);
}
