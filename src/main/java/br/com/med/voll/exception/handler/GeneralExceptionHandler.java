package br.com.med.voll.exception.handler;

import br.com.med.voll.exception.ErrorValidation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

import static org.springframework.http.HttpStatus.*;

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

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity handleBadCredentials() {
        return ResponseEntity.status(UNAUTHORIZED).body("Credenciais inválidas!");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handleAuthenticationError() {
        return ResponseEntity.status(UNAUTHORIZED).body("Falha na autenticação!");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedError() {
        return ResponseEntity.status(FORBIDDEN).body("Acesso negado!");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleInternalServerError(Exception e) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Erro: " + e.getLocalizedMessage());
    }
}
