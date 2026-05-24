package com.sistema.academia.internal.matricula.service;

import com.sistema.academia.config.exception.BusinessException;
import com.sistema.academia.config.exception.RecursoNaoEncontradoException;
import com.sistema.academia.internal.matricula.dto.MatriculaRequestRecord;
import com.sistema.academia.internal.matricula.dto.MatriculaResponseRecord;
import com.sistema.academia.internal.matricula.entity.MatriculaEntity;
import com.sistema.academia.internal.matricula.mapper.MatriculaMapper;
import com.sistema.academia.internal.matricula.repository.MatriculaRepository;
import com.sistema.academia.internal.plano.entity.PlanoEntity;
import com.sistema.academia.internal.plano.repository.PlanoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatriculaService {

    private final MatriculaRepository repository;
    private final PlanoRepository planoRepository;

    public MatriculaResponseRecord salvar(
            MatriculaRequestRecord request){

        if(repository.existsByAluno(
                request.aluno())){

            throw new BusinessException(
                    "Aluno já possui matrícula");
        }

        PlanoEntity plano =
                planoRepository.findById(
                                request.planoId())
                        .orElseThrow(() ->
                                new RecursoNaoEncontradoException(
                                        "Plano não encontrado"));

        MatriculaEntity matricula =
                new MatriculaEntity();

        matricula.setAluno(
                request.aluno());

        matricula.setDataMatricula(
                LocalDate.now());

        matricula.setStatus(
                "ATIVA");

        matricula.setPlano(
                plano);

        repository.save(matricula);

        return MatriculaMapper
                .toResponse(matricula);
    }

    public List<MatriculaResponseRecord> listar(){

        return repository.findAll()
                .stream()
                .map(MatriculaMapper::toResponse)
                .toList();
    }

}