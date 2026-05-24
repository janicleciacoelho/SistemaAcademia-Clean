package com.sistema.academia.internal.matricula.repository;

import com.sistema.academia.internal.matricula.entity.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository
        extends JpaRepository<MatriculaEntity,Long>{

    boolean existsByAluno(String aluno);

}