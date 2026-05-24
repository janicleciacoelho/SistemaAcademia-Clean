package com.sistema.academia.config.exception.response;

import java.time.LocalDateTime;
import java.util.Map;

public record ErroValidacaoResponse(
        int status,
        String titulo,
        Map<String, String> erros,
        LocalDateTime timestamp
) {}