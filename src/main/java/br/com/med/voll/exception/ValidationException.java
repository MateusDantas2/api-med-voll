package br.com.med.voll.exception;

/**
 * @author Mateus Dantas
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
