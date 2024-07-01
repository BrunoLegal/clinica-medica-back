package br.edu.imepac.controllers;

import br.edu.imepac.dtos.ConvenioCreateRequest;
import br.edu.imepac.dtos.ConvenioDto;
import br.edu.imepac.dtos.FuncionarioCreateRequest;
import br.edu.imepac.dtos.FuncionarioDto;
import br.edu.imepac.services.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;


@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

    private static final Logger logger = LoggerFactory.getLogger(FuncionarioController.class);
    @Autowired
    private FuncionarioService funcionarioService;

    @Operation(description = "Registra um novo funcionário", tags = "Funcionário")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Funcionário salvo com sucesso"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @PostMapping
    public ResponseEntity<FuncionarioDto> saveFuncionario(@RequestBody FuncionarioCreateRequest funcionarioCreateRequest){
        FuncionarioDto saveFuncionario = funcionarioService.save(funcionarioCreateRequest);
        logger.info("Funcionário salvo: {}", saveFuncionario);
        return new ResponseEntity<>(saveFuncionario, HttpStatus.CREATED);
    }

    @Operation(description = "exibe todos os funcionários salvos no banco de dados", tags = "Funcionário")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "retorna uma lista de todos os funcionários salvos no banco de dados"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @GetMapping
    public ResponseEntity<List<FuncionarioDto>> listAllFuncionarios(){
        List<FuncionarioDto> funcionarios = funcionarioService.findAll();
        logger.info("Funcionários encostrados: {}", funcionarios);
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
        }

    @Operation(description = "atualiza os dados de um funcionário", tags = "Funcionário")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Funcionário atualizado com sucesso"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "404", description = "funcionário não encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FuncionarioDto> updateFuncionario(@PathVariable Long id, @RequestBody FuncionarioDto funcionarioDetails){
        FuncionarioDto updateFuncionario = funcionarioService.update(id, funcionarioDetails);
        logger.info("Buscando funcionario de id {}",id);
        if(updateFuncionario != null){
            logger.info("Funcionrio atualizado com sucesso: {}", id);

            return new ResponseEntity<>(updateFuncionario, HttpStatus.OK);
        }else{
            logger.warn("Funcionario com id {}, não encontrado", id);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

            @Operation(description = "exibe as informações de um Funcionário", tags = "Funcionário")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "informações do funcionário encontradas"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "404", description = "funcionário não encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FuncionarioDto> getFuncionarioById(@PathVariable Long id){
        FuncionarioDto funcionarioDto = funcionarioService.findById(id);
        logger.info("Buscando funcionario de id: {}", id);
        if(funcionarioDto != null){
            logger.info("Buscando funcionário de id {}",id);
            return new ResponseEntity<>(funcionarioDto, HttpStatus.OK);
        }else{
            logger.warn("Funcionario com id {}, não encontrado", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "deleta um funcionário", tags = "Funcionário")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "funcionário deletado com sucesso"),
            @ApiResponse( responseCode = "400", description = "erro do cliente"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFuncionario(@PathVariable Long id){
        funcionarioService.delete(id);
        logger.info("Funcionário com id: {} deletado com sucesso", id);
    }
    }


