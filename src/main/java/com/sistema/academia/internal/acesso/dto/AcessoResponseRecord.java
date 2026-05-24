package com.sistema.academia.internal.acesso.dto;

import java.time.LocalDate;

public record AcessoResponseRecord(

        Long id,
        String aluno,
        LocalDate dataEntrada,
        String status,
        String plano

) {
}