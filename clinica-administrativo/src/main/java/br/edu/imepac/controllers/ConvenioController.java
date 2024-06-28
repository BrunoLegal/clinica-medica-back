package br.edu.imepac.controllers;
import br.edu.imepac.dtos.ConvenioCreateRequest;
import br.edu.imepac.dtos.ConvenioDto;
import br.edu.imepac.services.ConvenioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
/*
guia de como usar o swagger: https://www.youtube.com/watch?v=vHL02dlRv9I&t=55s

para acessar é só digitar localhost:8080/swagger-ui.html

lembre-se de colocar em todos os operations a tag correspondente à classe (veja como foi feito abaixo)
 */
@RestController
@RequestMapping("convenio")
public class ConvenioController {

    private static final Logger logger = LoggerFactory.getLogger(ConvenioController.class);

    @Autowired
    private ConvenioService convenioService;

    @Operation(description = "Registra um novo convênio", tags = "Convênio")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "convênio salvo com sucesso"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @PostMapping
    public ResponseEntity<ConvenioDto> saveConvenio(@RequestBody ConvenioCreateRequest convenioCreateRequest){
        ConvenioDto saveConvenio = convenioService.save(convenioCreateRequest);
        logger.info("Convênio salvo: {}", saveConvenio);
        return new ResponseEntity<>(saveConvenio, HttpStatus.CREATED);
    }

    @Operation(description = "exibe todos os convênios salvos no banco de dados", tags = "Convênio")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "retorna uma lista de todos os convênios salvos no banco de dados"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @GetMapping
    public ResponseEntity<List<ConvenioDto>> listAllConvenios(){
        List<ConvenioDto> convenios = convenioService.findAll();
        logger.info("Convênios encostrados: {}", convenios);
        return new ResponseEntity<>(convenios, HttpStatus.OK);
    }

    @Operation(description = "atualiza os dados de um convênio", tags = "Convênio")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "convênio atualizado com sucesso"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "404", description = "convênio não encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ConvenioDto> updateConvenio(@PathVariable Long id, @RequestBody ConvenioDto convenioDetails){
        ConvenioDto updateConvenio = convenioService.update(id, convenioDetails);
        logger.info("Buscando convênio de id {}",id);
        if(updateConvenio != null){
            logger.info("Convenio atualizado com sucesso: {}", id);

            return new ResponseEntity<>(updateConvenio, HttpStatus.OK);
        }else{
            logger.warn("Convenio com id {}, não encontrado", id);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "exibe as informações de um convênio", tags = "Convênio")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "informações do convênio encontradas"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "404", description = "convênio não encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ConvenioDto> getConvenioById(@PathVariable Long id){
        ConvenioDto convenioDto = convenioService.findById(id);
        logger.info("Buscando convenio de id: {}", id);
        if(convenioDto != null){
            logger.info("Buscando convênio de id {}",id);
            return new ResponseEntity<>(convenioDto, HttpStatus.OK);
        }else{
            logger.warn("Convenio com id {}, não encontrado", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "", tags = "Convênio")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "convênio deletado com sucesso"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteConvenio(@PathVariable Long id) {
        convenioService.delete(id);
        logger.info("Convenio com id: {} deletado com sucesso", id);
    }
}

