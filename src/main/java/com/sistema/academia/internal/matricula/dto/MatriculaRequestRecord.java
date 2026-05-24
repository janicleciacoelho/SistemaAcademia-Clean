package com.sistema.academia.internal.matricula.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MatriculaRequestRecord(

        @NotBlank(message="Aluno obrigatório")
        String aluno,

        @NotNull(message="Plano obrigatório")
        Long planoId

) {
}