package com.sistema.academia.internal.matricula.dto;

import java.time.LocalDate;

public record MatriculaResponseRecord(

        Long id,
        String aluno,
        LocalDate dataMatricula,
        String status,
        String plano

) {
}
