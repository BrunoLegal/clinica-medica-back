package br.edu.imepac.controllers;

import br.edu.imepac.dtos.PacienteCreateRequest;
import br.edu.imepac.dtos.PacienteDto;
import br.edu.imepac.services.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("paciente")
public class PacienteController {
    private static final Logger logger = LoggerFactory.getLogger(PacienteController.class);

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
        logger.info("Pacientes encostrados: {}", list);

        if(list.isEmpty()){
            logger.info("NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            logger.info("OK");
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
        logger.info("Paciente encostrado por id: {}", pacienteDto);

        if(pacienteDto != null){
            logger.info("OK", pacienteDto);
            return new ResponseEntity<>(pacienteDto, HttpStatus.OK);
        }else{
            logger.info("NOT FOUND", pacienteDto);
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
        logger.info("Paciente salvo: {}", pacienteDto);
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
        logger.info("Buscando paciente de id {}",id);
        if(pacienteDto != null){
            logger.info("Paciente atualizado com sucesso: {}", id);
            return new ResponseEntity<>(pacienteDto, HttpStatus.OK);
        }else{
            logger.warn("Paciente com id {}, não encontrado", id);
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
        logger.info("Paciente com id: {} deletado com sucesso", id);
        pacienteService.delete(id);
    }
}
