package com.sistema.academia.internal.matricula.mapper;

import com.sistema.academia.internal.matricula.dto.MatriculaResponseRecord;
import com.sistema.academia.internal.matricula.entity.MatriculaEntity;

public class MatriculaMapper {

    public static MatriculaResponseRecord toResponse(
            MatriculaEntity matricula){

        return new MatriculaResponseRecord(

                matricula.getId(),
                matricula.getAluno(),
                matricula.getDataMatricula(),
                matricula.getStatus(),
                matricula.getPlano().getNome()

        );
    }

}