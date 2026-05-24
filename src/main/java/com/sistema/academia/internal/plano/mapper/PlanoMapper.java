package com.sistema.academia.internal.plano.mapper;

import com.sistema.academia.internal.plano.dto.PlanoRequestRecord;
import com.sistema.academia.internal.plano.dto.PlanoResponseRecord;
import com.sistema.academia.internal.plano.entity.PlanoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlanoMapper {

    // Converter Request → Entity
    public PlanoEntity toEntity(PlanoRequestRecord request) {
        if (request == null) {
            return null;
        }

        PlanoEntity entity = new PlanoEntity();
        entity.setNome(request.nome());
        entity.setDescricao(request.descricao());
        entity.setValor(request.valor());
        entity.setDuracaoMeses(request.duracaoMeses());
        entity.setAtivo(true); // Por padrão, novo plano é ativo

        return entity;
    }

    // Converter Entity → Response
    public PlanoResponseRecord toResponse(PlanoEntity entity) {
        if (entity == null) {
            return null;
        }

        return new PlanoResponseRecord(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getValor(),
                entity.getDuracaoMeses(),
                entity.getAtivo()
        );
    }

    // Converter Lista de Entity → Lista de Response
    public List<PlanoResponseRecord> toResponseList(List<PlanoEntity> entities) {
        if (entities == null) {
            return null;
        }

        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Atualizar Entity existente com dados do Request (para PUT)
    public void updateEntity(PlanoEntity entity, PlanoRequestRecord request) {
        if (entity == null || request == null) {
            return;
        }

        entity.setNome(request.nome());
        entity.setDescricao(request.descricao());
        entity.setValor(request.valor());
        entity.setDuracaoMeses(request.duracaoMeses());
        // Não alterar o ID e nem o ativo (a menos que queira)
    }
}