package ee.bilal.dev.dataprocessor.domain.repository;

import ee.bilal.dev.dataprocessor.domain.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Repository
public interface FeedRepository extends JpaRepository<Feed, String> {
    /**
     * Get the last 10 feed entries
     * @return 10 feeds
     */
    @Query(nativeQuery = true, value = "select * from Feed s ORDER BY id desc LIMIT 10")
    List<Feed> findLast10Feeds();
}