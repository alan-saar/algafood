package com.algaworks.algafood.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Cozinha
 */
@Entity
@Table(name = "cozinha")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public @Data class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nome", length = 50)
    private String nome;


}
