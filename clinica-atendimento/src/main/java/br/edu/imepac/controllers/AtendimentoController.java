package br.edu.imepac.controllers;

import br.edu.imepac.dtos.AtendimentoCreateRequest;
import br.edu.imepac.dtos.AtendimentoDto;
import br.edu.imepac.services.AtendimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;


    @Operation(description = "Cria um novo registro de atendimento", tags = "Atendimento")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "atendimento criado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @PostMapping
    public ResponseEntity<AtendimentoDto> createAtendimento(@Valid @RequestBody AtendimentoCreateRequest atendimentoCreateRequest) {
        AtendimentoDto atendimentoDto = atendimentoService.createAtendimento(atendimentoCreateRequest);
        return ResponseEntity.ok().body(atendimentoDto);
    }

    @Operation(description = "Busca todos os atendimentos do banco de dados", tags = "Atendimento")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "atendimentos listados com sucesso"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @GetMapping
    public ResponseEntity<List<AtendimentoDto>> getAllAtendimento() {
        List<AtendimentoDto> atendimentos = atendimentoService.getAllAtendimentos();
        if (atendimentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(atendimentos);
    }

    @Operation(description = "Busca um atendimento no bando de dados por id", tags = "Atendimento")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "atendimento buscado com sucesso"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoDto> getAtendimentoById(@PathVariable Long id) {
        Optional<AtendimentoDto> atendimento = atendimentoService.getAtendimentoById(id);
        return atendimento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(description = "Atualiza um atendimento no banco de dados por id", tags = "Atendimento")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "atendimento atualizado com sucesso"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoDto> updateAtendimento(@PathVariable Long id, @Valid @RequestBody AtendimentoCreateRequest atendimentoCreateRequest) {
        AtendimentoDto updateAtendimento = atendimentoService.updateAtendimento(id, atendimentoCreateRequest);
        return ResponseEntity.ok(updateAtendimento);
    }
    @Operation(description = "Remove um atendimento no banco de dados por id", tags = "Atendimento")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "atendimento deletado com sucesso"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtendimento(@PathVariable Long id) {
        atendimentoService.deleteAtendimento(id);
        return ResponseEntity.noContent().build();
    }

}
