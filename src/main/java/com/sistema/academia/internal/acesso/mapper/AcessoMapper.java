package com.sistema.academia.internal.acesso.mapper;

import com.sistema.academia.internal.acesso.dto.AcessoResponseRecord;
import com.sistema.academia.internal.acesso.entity.AcessoEntity;

public class AcessoMapper {

    public static AcessoResponseRecord toResponse(
            AcessoEntity acesso){

        return new AcessoResponseRecord(

                acesso.getId(),
                acesso.getAluno(),
                acesso.getDataEntrada(),
                acesso.getStatus(),
                acesso.getPlano().getNome()
        );
    }
}