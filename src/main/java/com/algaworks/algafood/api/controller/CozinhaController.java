package com.algaworks.algafood.api.controller;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.algaworks.algafood.api.model.CozinhasXMLWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

// a anotação @RestController contém a anaotação @Controller e @ResponseBody
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;


    // o produces faz entrar nesse método quando solicitar json
    // @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.listar();
    }

    // o produces faz entrar nesse método quando solicitar xml
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXMLWrapper listarXml() {
        return new CozinhasXMLWrapper(cozinhaRepository.listar());
    }

    // @ResponseStatus(HttpStatus.OK) // o status para retornar se não tiver responseEntity
    @GetMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {
        Cozinha cozinha = cozinhaRepository.buscar(id);
        // return ResponseEntity.status(HttpStatus.OK).body(cozinha);
        if (cozinha != null) {
            return ResponseEntity.ok(cozinha); // atalho para a linha anterior
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable("cozinhaId") Long cozinhaId, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaDb = cozinhaRepository.buscar(cozinhaId);

        if (cozinhaDb != null) {
            // cozinhaDb.setNome(cozinha.getNome());
            BeanUtils.copyProperties(cozinha, cozinhaDb, "id"); // ignora o atributo id. Do terceiro parâmetro para frente são parametros para ignorar
            cozinhaDb = cozinhaRepository.salvar(cozinhaDb);
            return ResponseEntity.ok(cozinhaDb);
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable("cozinhaId") Long cozinhaId) {
        try {
            Cozinha cozinhaDb = cozinhaRepository.buscar(cozinhaId);
            if (cozinhaDb != null) {
                cozinhaRepository.remover(cozinhaDb);
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            // cliente tentando excluir algo que não pode ser excluido é um erro do cliente
            // no caso excluir uma cozinha que já é utilizada por um restaurante (tem chave estrangeira)
            // tem o código 400 (bad request) que também pode ser utilizado mas é mais abrangente
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
