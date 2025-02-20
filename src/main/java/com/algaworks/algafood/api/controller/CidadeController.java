package com.algaworks.algafood.api.controller;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroCidadeService cadastroCidade;


    @GetMapping
    public List<Cidade> listar() {
        return cidadeRepository.listar();
    }

    @GetMapping("/{idCidade}")
    public Cidade buscar(@PathVariable("idCidade") Long idCidade) {
        return cidadeRepository.buscar(idCidade);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cidade cidade) {
        Long idEstado = cidade.getEstado().getId();
        Estado estado = estadoRepository.buscar(idEstado);
        if (estado != null) {
            cidade.setEstado(estado);
            cidade = cadastroCidade.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        }
        return ResponseEntity.badRequest().body("Estado inválido");
    }

    @PutMapping("/{idCidade}")
    public ResponseEntity<?> atualizar(@RequestBody Cidade cidade, @PathVariable("idCidade") Long idCidade) {
        Cidade cidadeDb = cidadeRepository.buscar(idCidade);
        if (cidadeDb != null) {
            BeanUtils.copyProperties(cidade, cidadeDb, "id");
            try {
                cidadeDb = cadastroCidade.salvar(cidadeDb);
                return ResponseEntity.ok().body(cidadeDb);
            } catch (EntidadeNaoEncontradaException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idCidade}")
    public ResponseEntity<?> remover(@PathVariable("idCidade") Long idCidade) {
        Cidade cidade = cidadeRepository.buscar(idCidade);
        if (cidade != null) {
            cidadeRepository.remover(cidade);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
