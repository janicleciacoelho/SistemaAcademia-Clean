package com.sistema.academia.config.exception.handler;

import com.sistema.academia.config.exception.BusinessException;
import com.sistema.academia.config.exception.EntityNotFoundException;
import com.sistema.academia.config.exception.response.ErroPadraoResponse;
import com.sistema.academia.config.exception.response.ErroValidacaoResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErroPadraoResponse> handleBusinessException(BusinessException ex) {
        ErroPadraoResponse erro = new ErroPadraoResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de negócio",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroPadraoResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErroPadraoResponse erro = new ErroPadraoResponse(
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroPadraoResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErroPadraoResponse erro = new ErroPadraoResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Dados inválidos",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacaoResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                erros.put(error.getField(), error.getDefaultMessage())
        );

        ErroValidacaoResponse response = new ErroValidacaoResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                erros,
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErroPadraoResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        ErroPadraoResponse erro = new ErroPadraoResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroPadraoResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErroPadraoResponse erro = new ErroPadraoResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro na requisição",
                "Corpo da requisição inválido ou mal formatado",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErroPadraoResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErroPadraoResponse erro = new ErroPadraoResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Erro nos parâmetros",
                "O parâmetro '" + ex.getName() + "' tem valor inválido",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadraoResponse> handleGenericException(Exception ex) {
        ErroPadraoResponse erro = new ErroPadraoResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor",
                "Ocorreu um erro inesperado. Contate o administrador.",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
