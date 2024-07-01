package br.edu.imepac.controllers;


import br.edu.imepac.dtos.EspecialidadeCreateRequest;
import br.edu.imepac.dtos.EspecialidadeDto;
import br.edu.imepac.services.EspecialidadeService;
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
@RequestMapping("especialidade")
public class EspecialidadeController {

    private static final Logger logger = LoggerFactory.getLogger(EspecialidadeController.class);

    @Autowired
    private EspecialidadeService especialidadeService;

    @Operation(description = "exibe todas as especialidades salvas no banco de dados", tags = "Especialidade")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "retorna uma lista de todas as especialidade salvas no banco de dados"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @GetMapping
    public ResponseEntity<List<EspecialidadeDto>> listEspecialidade(){
        List<EspecialidadeDto> list = especialidadeService.findAll();
        logger.info("Especialiades encostradas: {}", list);
        if(list.isEmpty()){
            logger.info("NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            logger.info("OK");
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @Operation(description = "exibe uma especialidade salva no banco de dados", tags = "Especialidade")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "retorna uma especialidade salva no banco de dados"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EspecialidadeDto> findEspecialidade(@PathVariable Long id){
        EspecialidadeDto especialidadeDto = especialidadeService.findById(id);
        logger.info("Especialiade encostrada por id: {}", especialidadeDto);
        if(especialidadeDto != null){
            return new ResponseEntity<>(especialidadeDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Registra uma nova especialidade", tags = "Especialidade")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Especialidade salva com sucesso"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @PostMapping
    public ResponseEntity<EspecialidadeDto> saveEspecialidade(@RequestBody EspecialidadeCreateRequest especialidadeCreateRequest){
        EspecialidadeDto especialidadeDto = especialidadeService.save(especialidadeCreateRequest);
        logger.info("Especialidade salva: {}", especialidadeDto);
        return new ResponseEntity<>(especialidadeDto, HttpStatus.CREATED);
    }

    @Operation(description = "atualiza os dados de uma especialidade", tags = "Especialidade")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Especialidade atualizado com sucesso"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "404", description = "Especialidade não encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EspecialidadeDto> updateEspecialidade(@RequestBody EspecialidadeDto especialidadeData, @PathVariable Long id){
        EspecialidadeDto especialidadeDto = especialidadeService.update(id, especialidadeData);
        logger.info("Buscando especialidade de id {}",id);
        if(especialidadeDto != null){
            logger.info("Especialidade atualizada com sucesso: {}", id);

            return new ResponseEntity<>(especialidadeDto, HttpStatus.OK);
        }else{
            logger.warn("Especilidade com id {}, não encontrado", id);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "deleta uma especialidade", tags = "Especialidade")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Especialidade deletado com sucesso"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEspecialidade(@PathVariable Long id){
        especialidadeService.delete(id);
        logger.info("Especialidade com id: {} deletado com sucesso", id);

    }

}
