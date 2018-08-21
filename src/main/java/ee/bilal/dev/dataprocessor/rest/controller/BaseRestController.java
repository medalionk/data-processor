package ee.bilal.dev.dataprocessor.rest.controller;

import ee.bilal.dev.dataprocessor.application.dtos.DTO;
import ee.bilal.dev.dataprocessor.application.services.GenericService;
import ee.bilal.dev.dataprocessor.util.ResponseUtil;
import ee.bilal.dev.dataprocessor.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by bilal90 on 8/19/2018.
 */
@Slf4j
public class BaseRestController<T extends DTO> implements Rest<T> {
    
    protected final GenericService<T> service;

    protected <U extends BaseRestController> BaseRestController(GenericService<T> service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<T>> getAll() {
        log.info("Get all entities");

        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<T> get(String id) {
        log.info("Get all entity with id: '{}'", id);

        ValidationUtil.validateIdentity(id);

        return ResponseUtil.wrapOrNotFound(service.findOne(id));
    }

    @Override
    public ResponseEntity<T> create(T entity) {
        log.info("Persist entity {} in db", entity);

        ValidationUtil.validateEntity(entity);

        return new ResponseEntity<>(service.create(entity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<T> update(T entity) {
        log.info("Update '{}' entity", entity);

        ValidationUtil.validateEntity(entity);

        return ResponseUtil.wrapOrNotFound(service.update(entity));
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        ValidationUtil.validateIdentity(id);

        log.info("Delete entity with id: '{}'", id);
        service.delete(id);

        return ResponseEntity.ok().build();
    }
    
}
