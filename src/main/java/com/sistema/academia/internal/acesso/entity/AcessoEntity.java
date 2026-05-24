package com.sistema.academia.internal.acesso.entity;

import com.sistema.academia.internal.plano.entity.PlanoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="acessos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcessoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aluno;

    private LocalDate dataEntrada;

    private String status;

    @ManyToOne
    @JoinColumn(name="plano_id")
    private PlanoEntity plano;

}