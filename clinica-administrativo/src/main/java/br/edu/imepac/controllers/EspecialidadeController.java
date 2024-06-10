package br.edu.imepac.controllers;


import br.edu.imepac.dtos.EspecialidadeCreateRequest;
import br.edu.imepac.dtos.EspecialidadeDto;
import br.edu.imepac.services.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("especialidade")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;
    @GetMapping
    public ResponseEntity<List<EspecialidadeDto>> listEspecialidade(){
        List<EspecialidadeDto> list = especialidadeService.findAll();
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EspecialidadeDto> findEspecialidade(@PathVariable Long id){
        EspecialidadeDto especialidadeDto = especialidadeService.findById(id);
        if(especialidadeDto != null){
            return new ResponseEntity<>(especialidadeDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<EspecialidadeDto> saveEspecialidade(@RequestBody EspecialidadeCreateRequest especialidadeCreateRequest){
        EspecialidadeDto especialidadeDto = especialidadeService.save(especialidadeCreateRequest);
        return new ResponseEntity<>(especialidadeDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EspecialidadeDto> updateEspecialidade(@RequestBody EspecialidadeDto especialidadeData, @PathVariable Long id){
        EspecialidadeDto especialidadeDto = especialidadeService.update(id, especialidadeData);
        if(especialidadeDto != null){
            return new ResponseEntity<>(especialidadeDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEspecialidade(@PathVariable Long id){
        especialidadeService.delete(id);
    }

}
