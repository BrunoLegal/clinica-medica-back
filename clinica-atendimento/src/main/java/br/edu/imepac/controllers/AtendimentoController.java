package br.edu.imepac.controllers;

import br.edu.imepac.dtos.AtendimentoCreateRequest;
import br.edu.imepac.dtos.AtendimentoDto;
import br.edu.imepac.services.AtendimentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("atendimentos")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @PostMapping
    public ResponseEntity<AtendimentoDto> createAtendimento(@Valid @RequestBody AtendimentoCreateRequest atendimentoCreateRequest) {
        AtendimentoDto atendimentoDto = atendimentoService.createAtendimento(atendimentoCreateRequest);
        return ResponseEntity.ok().body(atendimentoDto);
    }

    @GetMapping
    public ResponseEntity<List<AtendimentoDto>> getAllAtendimento() {
        List<AtendimentoDto> atendimentos = atendimentoService.getAllAtendimentos();
        if (atendimentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(atendimentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoDto> getAtendimentoById(@PathVariable Long id) {
        Optional<AtendimentoDto> atendimento = atendimentoService.getAtendimentoById(id);
        return atendimento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoDto> updateAtendimento(@PathVariable Long id, @Valid @RequestBody AtendimentoCreateRequest atendimentoCreateRequest) {
        AtendimentoDto updateAtendimento = atendimentoService.updateAtendimento(id, atendimentoCreateRequest);
        return ResponseEntity.ok(updateAtendimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtendimento(@PathVariable Long id) {
        atendimentoService.deleteAtendimento(id);
        return ResponseEntity.noContent().build();
    }

}
