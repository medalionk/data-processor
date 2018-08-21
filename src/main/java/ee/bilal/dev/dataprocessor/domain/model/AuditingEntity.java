package ee.bilal.dev.dataprocessor.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingEntity extends IdentifiableEntity {
    @CreationTimestamp
    @Column(name = "created_date_time", nullable = false)
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    @Column(name = "last_modified_date_time")
    private LocalDateTime lastModifiedDateTime;
}
