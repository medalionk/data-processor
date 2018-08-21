package ee.bilal.dev.dataprocessor.rest.controller;

import ee.bilal.dev.dataprocessor.application.exceptions.ServiceException;
import ee.bilal.dev.dataprocessor.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by bilal90 on 8/19/2018.
 */
@ControllerAdvice
@Slf4j
public class RestErrorHandler {

    @Autowired
    public RestErrorHandler() {

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();

        List<ObjectError> errors = result.getAllErrors();
        List<String> strErrors = errors.stream()
                .map(error -> "Field: " + error.getCode() + "; Error: " + error.getDefaultMessage())
                .collect(Collectors.toList());

        Exception exception = new IllegalArgumentException(strErrors.toString());
        return ResponseUtil.exceptionResponseBuilder(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("Invalid parameters: {}", ex.getMessage());

        return ResponseUtil.exceptionResponseBuilder(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Map<String,String>> handleIllegalStateException(IllegalStateException ex) {
        log.error("Invalid state: {}", ex.getMessage());

        return ResponseUtil.exceptionResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error("The resource was not found: {}", ex.getMessage());

        return ResponseUtil.exceptionResponseBuilder(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(MalformedURLException.class)
    public ResponseEntity<Map<String,String>> handleMalformedURLException(MalformedURLException ex) {
        log.error("Malformed URL: {}", ex.getMessage());

        return ResponseUtil.exceptionResponseBuilder(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String,String>> handleDataAccessException(DataAccessException ex) {
        log.error("Data access error: {}", ex.getMessage());

        return ResponseUtil.exceptionResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Map<String,String>> handleServiceException(ServiceException ex) {
        log.error("Service error: {}", ex.getMessage());

        return ResponseUtil.exceptionResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

}
