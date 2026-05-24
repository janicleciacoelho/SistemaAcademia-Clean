package com.sistema.academia.config.exception.response;

import java.time.LocalDateTime;

public record ErroPadraoResponse(
        int status,
        String titulo,
        String mensagem,
        LocalDateTime timestamp
) {}