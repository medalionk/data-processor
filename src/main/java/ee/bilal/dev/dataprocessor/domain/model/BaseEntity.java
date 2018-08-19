package ee.bilal.dev.dataprocessor.domain.model;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

}