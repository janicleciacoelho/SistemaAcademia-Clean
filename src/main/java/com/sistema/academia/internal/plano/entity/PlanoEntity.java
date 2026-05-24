package com.sistema.academia.internal.plano.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "planos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @Column(nullable = false)
    private Double valor;

    @Column(name = "duracao_meses", nullable = false)
    private Integer duracaoMeses;

    @Column(nullable = false)
    private Boolean ativo = true;
}