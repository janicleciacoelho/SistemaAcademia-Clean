package com.sistema.academia.internal.plano.repository;

import com.sistema.academia.internal.plano.entity.PlanoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PlanoRepository extends JpaRepository<PlanoEntity, Long> {
    boolean existsByNome(String nome);
    Optional<PlanoEntity> findByNome(String nome);
}