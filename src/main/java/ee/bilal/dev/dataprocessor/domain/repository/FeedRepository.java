package ee.bilal.dev.dataprocessor.domain.repository;

import ee.bilal.dev.dataprocessor.domain.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Repository
public interface FeedRepository extends JpaRepository<Feed, String> {

}
