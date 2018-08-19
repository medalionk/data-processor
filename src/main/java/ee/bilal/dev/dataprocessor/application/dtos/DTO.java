package ee.bilal.dev.dataprocessor.application.dtos;

import ee.bilal.dev.dataprocessor.domain.model.BaseEntity;

/**
 * Created by bilal90 on 8/19/2018.
 */
public interface DTO<T extends BaseEntity> {
    /**
     * Convert this DTO to Entity
     * @return converted DTO as Entity
     */
    T asEntity();
}
