package br.edu.imepac.controllers;

import br.edu.imepac.dtos.MedicoCreateRequest;
import br.edu.imepac.dtos.MedicoDto;
import br.edu.imepac.services.MedicoService;
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
@RequestMapping("medico")
public class MedicoController {
    private static final Logger logger = LoggerFactory.getLogger(MedicoController.class);

    @Autowired
    private MedicoService medicoService;
    @Operation(description = "Lista todos os medicos", tags = "Médico")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "medicos listados com sucesso"),
            @ApiResponse( responseCode = "404", description = "medico nao encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @GetMapping
    public ResponseEntity<List<MedicoDto>> listDoctors(){
        List<MedicoDto> list = medicoService.findAll();
        logger.info("Medicos encontrados: {}", list);

        if(list.isEmpty()){
            logger.info("NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            logger.info("OK");
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @Operation(description = "Lista um médico por id", tags = "Médico")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "medico encontrado com sucesso"),
            @ApiResponse( responseCode = "404", description = "medico nao encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MedicoDto> findDoctor(@PathVariable Long id){
        MedicoDto medicoDto = medicoService.findById(id);
        logger.info("Medico encontrado por id: {}", medicoDto);
        if(medicoDto != null){
            return new ResponseEntity<>(medicoDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Operation(description = "Registra um medico no banco de dados", tags = "Médico")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "medico salvo com sucesso"),
            @ApiResponse( responseCode = "404", description = "medico nao encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @PostMapping
    public ResponseEntity<MedicoDto> saveDoctor(@RequestBody MedicoCreateRequest medicoCreateRequest){
        MedicoDto medicoDto = medicoService.save(medicoCreateRequest);
        logger.info("Medico salvo: {}", medicoDto);
        return new ResponseEntity<>(medicoDto, HttpStatus.CREATED);
    }
    @Operation(description = "Atualiza um medico no banco de dados", tags = "Médico")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "medico atualizado com sucesso"),
            @ApiResponse( responseCode = "404", description = "medico nao encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MedicoDto> updateDoctor(@RequestBody MedicoDto medicoData, @PathVariable Long id){
        MedicoDto medicoDto = medicoService.update(id, medicoData);
        logger.info("Buscando medico de id {}",id);
        if(medicoDto != null){
            logger.info("Medico atualizado com sucesso: {}", id);
            return new ResponseEntity<>(medicoDto, HttpStatus.OK);
        }else{
            logger.warn("Medico com id {}, não encontrado", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Deleta um medico do banco de dados por id", tags = "Médico")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "medico deletado com sucesso"),
            @ApiResponse( responseCode = "404", description = "medico nao encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDoctor(@PathVariable Long id){
        medicoService.delete(id);
        logger.info("Medico com id: {} deletado com sucesso", id);

    }

}
