package com.sistema.academia.internal.cliente.mapper;

import com.sistema.academia.internal.cliente.dto.ClienteRequestRecord;
import com.sistema.academia.internal.cliente.dto.ClienteResponseRecord;
import com.sistema.academia.internal.cliente.entity.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteEntity toEntity(ClienteRequestRecord request) {
        ClienteEntity entity = new ClienteEntity();
        entity.setNome(request.nome());
        entity.setCpf(request.cpf());
        entity.setEmail(request.email());
        entity.setTelefone(request.telefone());
        return entity;
    }

    public ClienteResponseRecord toResponse(ClienteEntity entity) {
        return new ClienteResponseRecord(
            entity.getId(),
            entity.getNome(),
            entity.getCpf(),
            entity.getEmail(),
            entity.getTelefone(),
            entity.getAtivo(),
            entity.getDataCadastro()
        );
    }

    public void updateEntity(ClienteRequestRecord request, ClienteEntity entity) {
        entity.setNome(request.nome());
        entity.setCpf(request.cpf());
        entity.setEmail(request.email());
        entity.setTelefone(request.telefone());
    }
}
