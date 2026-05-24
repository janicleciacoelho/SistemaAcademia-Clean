package com.sistema.academia.internal.acesso.service;

import com.sistema.academia.config.exception.RecursoNaoEncontradoException;
import com.sistema.academia.internal.acesso.dto.AcessoRequestRecord;
import com.sistema.academia.internal.acesso.dto.AcessoResponseRecord;
import com.sistema.academia.internal.acesso.entity.AcessoEntity;
import com.sistema.academia.internal.acesso.mapper.AcessoMapper;
import com.sistema.academia.internal.acesso.repository.AcessoRepository;
import com.sistema.academia.internal.plano.entity.PlanoEntity;
import com.sistema.academia.internal.plano.repository.PlanoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AcessoService {

    private final AcessoRepository repository;
    private final PlanoRepository planoRepository;

    public AcessoResponseRecord salvar(
            AcessoRequestRecord request){

        PlanoEntity plano =
                planoRepository.findById(
                                request.planoId())
                        .orElseThrow(() ->
                                new RecursoNaoEncontradoException(
                                        "Plano não encontrado"));

        AcessoEntity acesso =
                new AcessoEntity();

        acesso.setAluno(
                request.aluno());

        acesso.setDataEntrada(
                LocalDate.now());

        acesso.setStatus(
                "ATIVO");

        acesso.setPlano(plano);

        repository.save(acesso);

        return AcessoMapper
                .toResponse(acesso);
    }

    public List<AcessoResponseRecord> listar(){

        return repository.findAll()
                .stream()
                .map(AcessoMapper::toResponse)
                .toList();
    }
}