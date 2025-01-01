package com.algaworks.algafood.jpa;

import java.util.List;
import org.springframework.stereotype.Component;
import com.algaworks.algafood.domain.model.Cozinha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * CadastroCozinha
 */
@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> listar() {
        // TypedQuery<Cozinha> query = manager.createQuery("from cozinha", Cozinha.class);
        // return query.getResultList();
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    public Cozinha buscar(Long id) {
        return manager.find(Cozinha.class, id);
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }
}
