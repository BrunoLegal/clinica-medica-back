package br.edu.imepac.controllers;

import br.edu.imepac.dtos.UsuarioCreateRequest;
import br.edu.imepac.dtos.UsuarioDto;
import br.edu.imepac.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listAllUsers(){
        List<UsuarioDto> list = usuarioService.findAll();
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findUserById (@PathVariable Long id){
        UsuarioDto usuarioDto = usuarioService.findById(id);
        if(usuarioDto != null){
            return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> saveUser(@RequestBody UsuarioCreateRequest usuarioCreateRequest){
        UsuarioDto usuarioDto = usuarioService.save(usuarioCreateRequest);
        return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUser(@RequestBody UsuarioDto usuarioData, @PathVariable Long id){
        UsuarioDto usuarioDto = usuarioService.update(usuarioData, id);
        if(usuarioDto != null){
            return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
        usuarioService.delete(id);
    }

}
