package com.algaworks.algafood.infrastructures.repository;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class RestauranteRepositoryJPATest {

    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    CozinhaRepository cozinhaRepository;

    @Test
    void deveRetornarUmaListaDeRestaurantesNaoNula() {
        List<Restaurante> restaurantes = restauranteRepository.listar();
        assertThat(restaurantes).isNotNull();
    }

    @Test
    void deveRetornarAoMenosUmRestaurante() {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Copo Sujo");
        restauranteRepository.salvar(restaurante);

        List<Restaurante> restaurantes = restauranteRepository.listar();
        assertThat(restaurantes.size()).isGreaterThan(0);
    }

    @Test
    void deveSerPossivelAdicionarUmaCozinha() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Tailandesa");
        cozinha = cozinhaRepository.salvar(cozinha);
        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Copo Sujo");
        restaurante.setCozinha(cozinha);
        restauranteRepository.salvar(restaurante);
        assertThat(restaurante.getCozinha().getId()).isNotNull();
        System.out.printf("%s - %s\n", restaurante.getNome(),
                restaurante.getCozinha().getNome());
    }

}
