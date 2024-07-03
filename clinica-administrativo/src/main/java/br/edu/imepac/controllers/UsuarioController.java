package br.edu.imepac.controllers;

import br.edu.imepac.dtos.FuncionarioCreateRequest;
import br.edu.imepac.dtos.FuncionarioDto;
import br.edu.imepac.dtos.UsuarioCreateRequest;
import br.edu.imepac.dtos.UsuarioDto;
import br.edu.imepac.services.UsuarioService;
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
@RequestMapping("usuario")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private UsuarioService usuarioService;

    @Operation(description = "Lista todos os Usuarios", tags = "Usuário")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "usuarios listados com sucesso"),
            @ApiResponse( responseCode = "404", description = "usuario nao encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listAllUsers(){
        List<UsuarioDto> list = usuarioService.findAll();
        logger.info("Usuários encostrados: {}", list);
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @Operation(description = "Lista um unico usuario por id", tags = "Usuário")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "usuario encontrado com sucesso"),
            @ApiResponse( responseCode = "404", description = "usuario nao encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findUserById (@PathVariable Long id){
        UsuarioDto usuarioDto = usuarioService.findById(id);
        logger.info("Buscando usúario de id: {}", id);
        if(usuarioDto != null){
            logger.info("Buscando usúario de id {}",id);
            return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
        }else{
            logger.warn("Usúario com id {}, não encontrado", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Registra um usuario no banco de dados", tags = "Usuário")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "usuario salvo com sucesso"),
            @ApiResponse( responseCode = "404", description = "usuario nao encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @PostMapping
    public ResponseEntity<UsuarioDto> saveUser(@RequestBody UsuarioCreateRequest usuarioCreateRequest){
        UsuarioDto usuarioDto = usuarioService.save(usuarioCreateRequest);
        logger.info("Usúario salvo: {}", usuarioDto);
        return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
    }

    @Operation(description = "Atualiza um usuario no banco de dados", tags = "Usuário")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "usuario atualizado com sucesso"),
            @ApiResponse( responseCode = "404", description = "usuario nao encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsuarioDto> updateUser(@RequestBody UsuarioDto usuarioData, @PathVariable Long id){
        UsuarioDto usuarioDto = usuarioService.update(usuarioData, id);
        logger.info("Buscando usúario de id {}",id);
        if(usuarioDto != null){
            logger.info("Usúario atualizado com sucesso: {}", id);
            return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
        }else{
            logger.warn("Usúario com id {}, não encontrado", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(description = "Remove um usuario do banco de dados", tags = "Usuário")
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "usuario deletado com sucesso"),
            @ApiResponse( responseCode = "404", description = "usuario nao encontrado"),
            @ApiResponse( responseCode = "500", description = "erro do servidor")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
        usuarioService.delete(id);
        logger.info("Usúario com id: {} deletado com sucesso", id);
    }

}
