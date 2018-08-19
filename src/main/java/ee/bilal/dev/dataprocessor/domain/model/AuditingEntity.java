package ee.bilal.dev.dataprocessor.domain.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingEntity extends IdentifiableEntity {
    @CreatedDate
    @Column(name = "created_date", nullable = false)
    protected transient Instant createdDate = Instant.now();

    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected transient Instant lastModifiedDate = Instant.now();
}
