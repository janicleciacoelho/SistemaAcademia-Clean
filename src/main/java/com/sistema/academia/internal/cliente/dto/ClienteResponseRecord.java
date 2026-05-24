package com.sistema.academia.internal.cliente.dto;

import java.time.LocalDateTime;

public record ClienteResponseRecord(
    Long id,
    String nome,
    String cpf,
    String email,
    String telefone,
    Boolean ativo,
    LocalDateTime dataCadastro
) {
}
