package ee.bilal.dev.dataprocessor.application.mappers;

import org.mapstruct.factory.Mappers;

/**
 * Created by bilal90 on 8/19/2018.
 */
public final class FeedMappers {

  private FeedMappers()
  {
    throw new AssertionError();
  }

  public static final FeedMapper FEED_MAPPER = Mappers.getMapper(FeedMapper.class );
}
