package ee.bilal.dev.dataprocessor.domain.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Entity
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = true)
@Table(name = "feed")
public class Feed extends AuditingEntity {
    @NotEmpty
    @NonNull
    @Column(name = "link", nullable = false)
    private String link;

    @NotEmpty
    @NonNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "contents")
    private String contents;

    @NonNull
    @Column(name = "date", nullable = false)
    private Date date;
}
