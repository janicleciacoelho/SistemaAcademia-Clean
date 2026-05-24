package com.sistema.academia.config.excepition.handler;

public class GlobalExceptionHandler {

    /**
     * Trata IllegalArgumentException e outras exceções de validação de negócio
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroPadraoResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {

        ErroPadraoResponse erro = new ErroPadraoResponse(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(erro);
    }

    /**
     * Trata MethodArgumentNotValidException (validações com @Valid)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacaoResponse> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, WebRequest request) {

        ErroValidacaoResponse erro = new ErroValidacaoResponse(
            422,
            "Erro de validação nos campos solicitados",
            request.getDescription(false).replace("uri=", "")
        );

        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
            erro.adicionarErro(
                fieldError.getField(),
                fieldError.getDefaultMessage()
            )
        );

        return ResponseEntity
            .status(422)
            .body(erro);
    }

    /**
     * Trata EntityNotFoundException
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroPadraoResponse> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {

        ErroPadraoResponse erro = new ErroPadraoResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(erro);
    }

    /**
     * Trata NoHandlerFoundException (endpoint não encontrado)
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErroPadraoResponse> handleNoHandlerFound(
            NoHandlerFoundException ex, WebRequest request) {

        ErroPadraoResponse erro = new ErroPadraoResponse(
            HttpStatus.NOT_FOUND.value(),
            "Endpoint não encontrado: " + ex.getRequestURL(),
            request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(erro);
    }

    /**
     * Trata NumberFormatException
     */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErroPadraoResponse> handleNumberFormatException(
            NumberFormatException ex, WebRequest request) {

        ErroPadraoResponse erro = new ErroPadraoResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Formato de número inválido: " + ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(erro);
    }

    /**
     * Trata exceções genéricas não previstas
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadraoResponse> handleGenericException(
            Exception ex, WebRequest request) {

        ErroPadraoResponse erro = new ErroPadraoResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Erro interno do servidor: " + ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(erro);
    }

    /**
     * Trata IllegalStateException
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErroPadraoResponse> handleIllegalStateException(
            IllegalStateException ex, WebRequest request) {

        ErroPadraoResponse erro = new ErroPadraoResponse(
            HttpStatus.CONFLICT.value(),
            ex.getMessage(),
            request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(erro);
    }
}

/**
 * Classe personalizada EntityNotFoundException para tratamento de entidades não encontradas
 */
class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
