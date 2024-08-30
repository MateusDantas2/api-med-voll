package br.com.med.voll.exception;

import org.springframework.validation.FieldError;

/**
 * @author Mateus Dantas
 */
public record ErrorValidation(String campo, String mensagem) {
    public ErrorValidation(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
