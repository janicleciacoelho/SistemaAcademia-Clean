package com.sistema.academia.internal.cliente.service;

import com.sistema.academia.internal.cliente.dto.ClienteRequestRecord;
import com.sistema.academia.internal.cliente.dto.ClienteResponseRecord;
import com.sistema.academia.internal.cliente.entity.ClienteEntity;
import com.sistema.academia.internal.cliente.mapper.ClienteMapper;
import com.sistema.academia.internal.cliente.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public ClienteResponseRecord criar(ClienteRequestRecord request) {
        validarDadosCliente(request);

        if (clienteRepository.existsByCpf(request.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado no sistema");
        }

        if (request.email() != null && clienteRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email já cadastrado no sistema");
        }

        ClienteEntity entity = clienteMapper.toEntity(request);
        ClienteEntity salvo = clienteRepository.save(entity);

        return clienteMapper.toResponse(salvo);
    }

    public ClienteResponseRecord buscarPorId(Long id) {
        validarId(id);

        ClienteEntity entity = clienteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));

        return clienteMapper.toResponse(entity);
    }

    public List<ClienteResponseRecord> listarTodos() {
        return clienteRepository.findAll()
            .stream()
            .map(clienteMapper::toResponse)
            .toList();
    }

    public List<ClienteResponseRecord> listarAtivos() {
        return clienteRepository.findByAtivoTrue()
            .stream()
            .map(clienteMapper::toResponse)
            .toList();
    }

    public ClienteResponseRecord atualizar(Long id, ClienteRequestRecord request) {
        validarId(id);
        validarDadosCliente(request);

        ClienteEntity entity = clienteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));

        // Validar CPF se foi alterado
        if (!entity.getCpf().equals(request.cpf()) && clienteRepository.existsByCpf(request.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado no sistema");
        }

        // Validar email se foi alterado
        if (request.email() != null && !request.email().equals(entity.getEmail()) 
            && clienteRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email já cadastrado no sistema");
        }

        clienteMapper.updateEntity(request, entity);
        ClienteEntity atualizado = clienteRepository.save(entity);

        return clienteMapper.toResponse(atualizado);
    }

    public void deletar(Long id) {
        validarId(id);

        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente não encontrado com ID: " + id);
        }

        clienteRepository.deleteById(id);
    }

    public void inativar(Long id) {
        validarId(id);

        ClienteEntity entity = clienteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));

        entity.setAtivo(false);
        clienteRepository.save(entity);
    }

    public void ativar(Long id) {
        validarId(id);

        ClienteEntity entity = clienteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));

        entity.setAtivo(true);
        clienteRepository.save(entity);
    }

    // Métodos auxiliares de validação

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID deve ser um número positivo");
        }
    }

    private void validarDadosCliente(ClienteRequestRecord request) {
        if (request == null) {
            throw new IllegalArgumentException("Dados do cliente não podem ser nulos");
        }

        if (request.nome() == null || request.nome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório");
        }

        if (request.nome().trim().length() < 3) {
            throw new IllegalArgumentException("Nome deve ter no mínimo 3 caracteres");
        }

        if (request.cpf() == null || request.cpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório");
        }

        if (!validarFormatoCpf(request.cpf())) {
            throw new IllegalArgumentException("CPF inválido. Deve conter apenas números");
        }

        if (request.cpf().length() != 11) {
            throw new IllegalArgumentException("CPF deve conter 11 dígitos");
        }

        if (request.email() != null && !request.email().trim().isEmpty()) {
            if (!validarFormatoEmail(request.email())) {
                throw new IllegalArgumentException("Email inválido");
            }
        }
    }

    private boolean validarFormatoCpf(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    private boolean validarFormatoEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
