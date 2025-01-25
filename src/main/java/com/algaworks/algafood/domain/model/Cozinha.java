package com.algaworks.algafood.domain.model;

import com.fasterxml.jackson.annotation.JsonRootName;
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
@JsonRootName("cozinha") // no jml o nome da entidade será cozinha (e não o nome da classe que tem C maiusculo)
public @Data class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    // @JsonIgnore // nao mostra a propriedade na representação
    // @JsonProperty("titulo") // vai fazer o nome da representação mudar para título
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;


}
