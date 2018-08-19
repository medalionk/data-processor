package ee.bilal.dev.dataprocessor.application.mappers;

import ee.bilal.dev.dataprocessor.domain.model.BaseEntity;

import java.util.List;

/**
 * Created by bilal90 on 8/19/2018.
 */
public interface BaseMapper<T, U extends BaseEntity> {
    /**
     * Convert DTO to Entity
     * @param dto to convert
     * @return converted DTO as entity
     */
    U toEntity(T dto);

    /**
     * Convert Entity to DTO
     * @param entity to convert
     * @return converted Entity as DTO
     */
    T toDTO(U entity);

    /**
     * Convert list of DTOs to Entities
     * @param entities to convert
     * @return converted DTOs as entities
     */
    List<T> toDTOs(List<U> entities);

    /**
     * Convert DTOs to Entities
     * @param dtos to convert
     * @return converted Entities as DTOs
     */
    List<U> toEntities(List<T> dtos);
}
