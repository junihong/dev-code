package me.tamsil.hibernatevalidatorlist;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

@RestControllerAdvice
public class ConstraintViolationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handle(ConstraintViolationException constraintViolationException) {
        Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
        StringBuilder sb = new StringBuilder();
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<?> violation : constraintViolations) {
                sb.append(violation.getMessage()).append("\n");
            }
        }

        return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    }
}
