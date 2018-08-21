package ee.bilal.dev.dataprocessor.application.services;

import ee.bilal.dev.dataprocessor.application.dtos.DTO;
import ee.bilal.dev.dataprocessor.application.mappers.TMapper;
import ee.bilal.dev.dataprocessor.domain.model.BaseEntity;
import ee.bilal.dev.dataprocessor.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Slf4j
public abstract class BaseGenericService<U extends BaseEntity, T extends DTO<U>> implements GenericService<T> {

    protected final JpaRepository<U, String> repository;
    protected final TMapper<T, U> mapper;

    protected <S extends BaseGenericService>BaseGenericService(
            JpaRepository<U, String> repository, TMapper<T, U> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public T create(T entity) {
        log.info("Create entity: '{}'", entity);

        ValidationUtil.validateEntity(entity);
        U savedEntity = repository.saveAndFlush(entity.asEntity());

        return mapper.toDTO(savedEntity);
    }

    @Override
    public Optional<T> update(T entity){
        log.info("Update entity: '{}'", entity);

        ValidationUtil.validateEntity(entity);

        return Optional.of(repository.saveAndFlush(entity.asEntity()))
                .map(mapper::toDTO);
    }

    @Override
    public Optional<T> findOne(String id) {
        log.info("Fetch one entity with id: '{}'", id);

        ValidationUtil.validateIdentity(id);
        U entity = repository.getOne(id);

        return Optional.of(entity).map(mapper::toDTO);
    }

    @Override
    public List<T> findAll(){
        log.info("Fetch all entities found");

        List<U> results = repository.findAll();
        if(results.isEmpty()) {
            log.info("No entities found");
            return new ArrayList<>();
        }

        return mapper.toDTOs(results);
    }

    @Override
    public void delete(String id){
        log.info("Delete entity with id: '{}'", id);

        ValidationUtil.validateIdentity(id);

        Optional<T> t = findOne(id);
        if(!t.isPresent()){
            throw new IllegalArgumentException("Invalid Id");
        }
        repository.deleteById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<T> saveAll(List<T> dtos) {
        log.info("Save all entities: '{}'", dtos);
        ValidationUtil.validateEntity(dtos);

        List<U> entities = dtos.stream().map(DTO::asEntity).collect(Collectors.toList());
        List<U> savedEntities = repository.saveAll(entities);

        return mapper.toDTOs(savedEntities);
    }

}
