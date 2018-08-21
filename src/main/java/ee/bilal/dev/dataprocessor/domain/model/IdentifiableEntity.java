package ee.bilal.dev.dataprocessor.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by bilal90 on 8/19/2018.
 */
@ToString
@RequiredArgsConstructor
@MappedSuperclass
public abstract class IdentifiableEntity extends BaseEntity{

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        } else if (obj == null){
            return false;
        } else if (!(obj instanceof IdentifiableEntity)) {
            return false;
        }

        IdentifiableEntity other = (IdentifiableEntity) obj;

        return getId().equals(other.getId());
    }

}
