package com.sistema.academia.internal.cliente.dto;

public record ClienteRequestRecord(
    String nome,
    String cpf,
    String email,
    String telefone
) {
}
