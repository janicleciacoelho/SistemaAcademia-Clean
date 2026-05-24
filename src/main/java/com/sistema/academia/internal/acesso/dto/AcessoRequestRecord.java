package com.sistema.academia.internal.acesso.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AcessoRequestRecord(

        @NotBlank(message = "Aluno obrigatório")
        String aluno,

        @NotNull(message = "Plano obrigatório")
        Long planoId

) {
}