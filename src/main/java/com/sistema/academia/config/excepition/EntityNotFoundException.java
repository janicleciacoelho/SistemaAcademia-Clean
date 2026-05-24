package com.sistema.academia.config.excepition;

/**
 * Exceção personalizada para entidades não encontradas
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

