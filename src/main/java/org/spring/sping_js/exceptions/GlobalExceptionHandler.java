package org.spring.sping_js.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<JsFrontAppError> catchItemNotFoundException(ItemNotFoundException e) {
        log.error("Resource not found", e);
        return new ResponseEntity<>(new JsFrontAppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<FieldValidationError> catchValidationException(ValidationException e) {
        log.error("Validation failed", e);
        return new ResponseEntity<>(new FieldValidationError(e.getErrors()), HttpStatus.BAD_REQUEST);
    }
}
