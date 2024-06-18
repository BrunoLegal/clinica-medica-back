package br.edu.imepac.controllers;

import br.edu.imepac.dtos.FuncionarioCreateRequest;
import br.edu.imepac.dtos.FuncionarioDto;
import br.edu.imepac.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;
    @GetMapping
    public ResponseEntity<List<FuncionarioDto>> listAllFuncionarios(){
        List<FuncionarioDto> list = funcionarioService.findAll();
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FuncionarioDto> findFuncionario(@PathVariable Long id){
        FuncionarioDto funcionarioDto = funcionarioService.findById(id);
        if(funcionarioDto != null){
            return new ResponseEntity<>(funcionarioDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<FuncionarioDto> saveFuncionario(@RequestBody FuncionarioCreateRequest funcionarioCreateRequest){
        FuncionarioDto funcionarioDto = funcionarioService.save(funcionarioCreateRequest);
        return new ResponseEntity<>(funcionarioDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FuncionarioDto> updateFuncionario(@RequestBody FuncionarioDto funcionarioData, @PathVariable Long id){
        FuncionarioDto funcionarioDto = funcionarioService.update(funcionarioData, id);
        if(funcionarioDto != null){
            return new ResponseEntity<>(funcionarioDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFuncionario(@PathVariable Long id){
        funcionarioService.delete(id);
    }
    }

