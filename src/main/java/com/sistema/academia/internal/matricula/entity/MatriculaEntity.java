package com.sistema.academia.internal.matricula.entity;

import com.sistema.academia.internal.plano.entity.PlanoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="matriculas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aluno;

    private LocalDate dataMatricula;

    private String status;

    @ManyToOne
    @JoinColumn(name="plano_id")
    private PlanoEntity plano;

}