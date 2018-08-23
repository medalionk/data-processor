package ee.bilal.dev.dataprocessor.domain.model;

import lombok.*;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Entity
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = true)
@Table(name = "feed")
@SQLInsert(sql="INSERT INTO feed (created_date_time, last_modified_date_time, contents, date_time, description, link, title) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?) " +
        "ON DUPLICATE KEY UPDATE link = link " )
public class Feed extends AuditingEntity {

    @NotEmpty
    @NonNull
    @Column(name = "link", nullable = false, unique = true)
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
    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

}
