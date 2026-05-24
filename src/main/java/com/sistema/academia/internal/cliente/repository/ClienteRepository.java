package com.sistema.academia.internal.cliente.repository;

import com.sistema.academia.internal.cliente.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByCpf(String cpf);

    Optional<ClienteEntity> findByEmail(String email);

    List<ClienteEntity> findByAtivoTrue();

    List<ClienteEntity> findByAtivoFalse();

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
