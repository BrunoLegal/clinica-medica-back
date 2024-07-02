package br.edu.imepac.controllers;

import br.edu.imepac.dtos.FuncionarioCreateRequest;
import br.edu.imepac.dtos.FuncionarioDto;
import br.edu.imepac.dtos.UsuarioCreateRequest;
import br.edu.imepac.dtos.UsuarioDto;
import br.edu.imepac.services.UsuarioService;
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

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listAllUsers(){
        List<UsuarioDto> list = usuarioService.findAll();
        logger.info("Funcionários encostrados: {}", list);
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

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
    @PostMapping
    public ResponseEntity<UsuarioDto> saveUser(@RequestBody UsuarioCreateRequest usuarioCreateRequest){
        UsuarioDto usuarioDto = usuarioService.save(usuarioCreateRequest);
        logger.info("Usúario salvo: {}", usuarioDto);
        return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
    }

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


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
        usuarioService.delete(id);
        logger.info("Usúario com id: {} deletado com sucesso", id);
    }

}
