package ee.bilal.dev.dataprocessor.application.dtos;

import ee.bilal.dev.dataprocessor.application.mappers.FeedMapper;
import ee.bilal.dev.dataprocessor.application.mappers.FeedMappers;
import ee.bilal.dev.dataprocessor.domain.model.Feed;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.Instant;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Data
@NoArgsConstructor(force = true)
public class FeedDTO implements DTO<Feed> {
    private String id;

    @NotEmpty
    @NotNull
    private String link;

    @NotEmpty
    @NonNull
    private String title;

    private String description;

    private String contents;

    @NonNull
    private Date date;

    private Instant createdDate;

    private Instant lastModifiedDate;

    public static FeedDTO of(String link, String title, Date date){
        FeedDTO feed = new FeedDTO();
        feed.setLink(link);
        feed.setTitle(title);
        feed.setDate(date);

        return feed;
    }

    @Override
    public Feed asEntity() {
        return FeedMappers.FEED_MAPPER.toEntity(this);
    }
}