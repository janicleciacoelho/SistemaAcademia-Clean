package com.sistema.academia.internal.plano.service;

import com.sistema.academia.config.exception.BusinessException;
import com.sistema.academia.config.exception.EntityNotFoundException;
import com.sistema.academia.internal.plano.dto.PlanoRequestRecord;
import com.sistema.academia.internal.plano.dto.PlanoResponseRecord;
import com.sistema.academia.internal.plano.entity.PlanoEntity;
import com.sistema.academia.internal.plano.mapper.PlanoMapper;
import com.sistema.academia.internal.plano.repository.PlanoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanoService {

    private final PlanoRepository repository;
    private final PlanoMapper planoMapper;

    public PlanoResponseRecord salvar(PlanoRequestRecord request) {
        if (repository.existsByNome(request.nome())) {
            throw new BusinessException("Plano já cadastrado com este nome");
        }

        if (request.valor() == null || request.valor() <= 0) {
            throw new BusinessException("Valor do plano deve ser maior que zero");
        }

        PlanoEntity plano = planoMapper.toEntity(request);
        PlanoEntity salvo = repository.save(plano);

        return planoMapper.toResponse(salvo);
    }

    public List<PlanoResponseRecord> listarTodos() {
        return repository.findAll()
                .stream()
                .map(planoMapper::toResponse)
                .toList();
    }

    public PlanoResponseRecord buscarPorId(Long id) {
        PlanoEntity plano = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado com ID: " + id));

        return planoMapper.toResponse(plano);
    }

    public PlanoResponseRecord atualizar(Long id, PlanoRequestRecord request) {
        PlanoEntity plano = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado com ID: " + id));

        if (!plano.getNome().equals(request.nome()) && repository.existsByNome(request.nome())) {
            throw new BusinessException("Já existe um plano com este nome");
        }

        plano.setNome(request.nome());
        plano.setDescricao(request.descricao());
        plano.setValor(request.valor());
        plano.setDuracaoMeses(request.duracaoMeses());

        PlanoEntity atualizado = repository.save(plano);
        return planoMapper.toResponse(atualizado);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Plano não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }
}