package br.com.med.voll.exception.handler;

import br.com.med.voll.exception.ErrorValidation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Mateus Dantas
 */
@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleEmptyNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBadRequest(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream()
                .map(ErrorValidation::new).toList()
        );
    }
}
