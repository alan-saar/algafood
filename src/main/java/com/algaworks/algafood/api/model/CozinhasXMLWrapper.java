package com.algaworks.algafood.api.model;

import java.util.List;
import com.algaworks.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.NonNull;

@JacksonXmlRootElement(localName = "cozinhas") // faz o xml apresentar cozinhas no lugar do nome da classe
@Data // data gera construtuores para parametros obrigatorios
public class CozinhasXMLWrapper {

    @NonNull // diz que é obrigatório para o @Data gerar o construtor
    @JacksonXmlElementWrapper(useWrapping = false) // desabilita o embrulho porque vai ser a própria classe
    @JsonProperty("cozinha") // para cozinha ficar dentro de cozinhas
    private List<Cozinha> cozinhas;
}
