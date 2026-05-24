package com.sistema.academia.internal.acesso.repository;

import com.sistema.academia.internal.acesso.entity.AcessoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcessoRepository
        extends JpaRepository<AcessoEntity,Long>{

}