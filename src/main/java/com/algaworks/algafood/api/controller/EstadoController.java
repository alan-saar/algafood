package com.algaworks.algafood.api.controller;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    @GetMapping
    public List<Estado> listar() {
        return estadoRepository.listar();
    }

    @GetMapping("/{idEstado}")
    public ResponseEntity<?> buscar(@PathVariable("idEstado") Long idEstado) {
        Estado estado = estadoRepository.buscar(idEstado);
        if (estado != null) {
            return ResponseEntity.ok().body(estado);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Estado> salvar(@RequestBody Estado estado) {
        estado = cadastroEstado.salvar(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(estado);
    }

    @PutMapping("/{idEstado}")
    public ResponseEntity<?> atualizar(@RequestBody Estado estado, @PathVariable Long idEstado) {
        Estado estadoDb = estadoRepository.buscar(idEstado);
        if (estadoDb != null) {
            BeanUtils.copyProperties(estado, estadoDb, "id");
            return ResponseEntity.ok().body(estadoDb);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("{idEstado}")
    public ResponseEntity<?> excluir(@PathVariable("idEstado") Long idEstado) {
        Estado estado = estadoRepository.buscar(idEstado);
        if (estado != null) {
            estadoRepository.remover(estado);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
