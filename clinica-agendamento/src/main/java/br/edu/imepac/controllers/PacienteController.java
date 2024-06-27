package br.edu.imepac.controllers;

import br.edu.imepac.dtos.PacienteCreateRequest;
import br.edu.imepac.dtos.PacienteDto;
import br.edu.imepac.services.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @Operation(description = "exibe todos os pacientes salvos no banco de dados", tags = "Paciente")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "retorna uma lista de todos os pacientes salvos no banco de dados"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @GetMapping
    public ResponseEntity<List<PacienteDto>> listPaciente(){
        List<PacienteDto> list = pacienteService.findAll();
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @Operation(description = "exibe um paciente salvo no banco de dados", tags = "Paciente")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "retorna um paciente salvo no banco de dados"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PacienteDto> findPaciente(@PathVariable Long id){
        PacienteDto pacienteDto = pacienteService.findById(id);
        if(pacienteDto != null){
            return new ResponseEntity<>(pacienteDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Registra um novo Paciente", tags = "Paciente")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Paciente salvo com sucesso"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @PostMapping
    public ResponseEntity<PacienteDto> savePaciente(@RequestBody PacienteCreateRequest medicoCreateRequest){
        PacienteDto pacienteDto = pacienteService.save(medicoCreateRequest);
        return new ResponseEntity<>(pacienteDto, HttpStatus.CREATED);
    }

    @Operation(description = "atualiza os dados de um paciente", tags = "Paciente")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Paciente atualizado com sucesso"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "404", description = "Paciente não encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PacienteDto> updatePaciente(@RequestBody PacienteDto pacienteData, @PathVariable Long id){
        PacienteDto pacienteDto = pacienteService.update(id, pacienteData);
        if(pacienteDto != null){
            return new ResponseEntity<>(pacienteDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "deleta um paciente", tags = "Paciente")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "paciente deletado com sucesso"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePaciente(@PathVariable Long id){
        pacienteService.delete(id);
    }
}
