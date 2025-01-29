package com.algaworks.algafood.domain.repository;

import java.util.List;
import com.algaworks.algafood.domain.model.Cozinha;

// repositório orientado a persistência
// por isso o salvar, em geral tem que imitar uma coleção, no lugar seria add
// os repositórios são por agregados
// os que são por coleção estão comentados, eles melhoram a legibilidade
public interface CozinhaRepository {

    // List<Cozinha> todas();
    List<Cozinha> listar();


    // Cozinha porId(Long id);
    Cozinha buscar(Long id);


    // Cozinha adicionar(Cozinha cozinha);
    Cozinha salvar(Cozinha cozinha);

    void remover(Long id);


}
