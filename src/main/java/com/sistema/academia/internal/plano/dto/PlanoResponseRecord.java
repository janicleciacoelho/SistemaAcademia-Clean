package com.sistema.academia.internal.plano.dto;

public record PlanoResponseRecord(
        Long id,
        String nome,
        String descricao,
        Double valor,
        Integer duracaoMeses,
        Boolean ativo
) {}