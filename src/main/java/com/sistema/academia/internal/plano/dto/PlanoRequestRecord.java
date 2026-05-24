package com.sistema.academia.internal.plano.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PlanoRequestRecord(
        @NotBlank(message = "Nome do plano é obrigatório")
        @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
        String nome,

        @Size(max = 500, message = "Descrição pode ter no máximo 500 caracteres")
        String descricao,

        @NotNull(message = "Valor é obrigatório")
        @Positive(message = "Valor deve ser positivo")
        Double valor,

        @NotNull(message = "Duração em meses é obrigatória")
        @Min(value = 1, message = "Duração mínima é 1 mês")
        @Max(value = 36, message = "Duração máxima é 36 meses")
        Integer duracaoMeses
) {}