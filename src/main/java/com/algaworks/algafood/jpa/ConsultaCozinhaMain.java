package com.algaworks.algafood.jpa;

import java.util.List;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;

/**
 * ConsultaCozinhaMain
 */
public class ConsultaCozinhaMain {

    public static void main(String[] args) {
        // o AlgaFoodApiApplication.class é a fonte primária de configurações
        // poderia usar esta própria classe anotada com @SpringBootApplication
        ApplicationContext appContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE) // roda sem o web para rodar e encerrar
                .run(args);

        // pego o bean direto da aplicação
        CadastroCozinha cadastroCozinha = appContext.getBean(CadastroCozinha.class);

        List<Cozinha> cozinhas = cadastroCozinha.listar();

        System.out.println("inicio");
        cozinhas.forEach(c -> System.out.println(c.getNome()));
        System.out.println("fim");
    }

}
