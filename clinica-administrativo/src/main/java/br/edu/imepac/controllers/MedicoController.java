package br.edu.imepac.controllers;

import br.edu.imepac.dtos.MedicoCreateRequest;
import br.edu.imepac.dtos.MedicoDto;
import br.edu.imepac.services.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medico")
public class MedicoController {
    @Autowired
    private MedicoService medicoService;
    @GetMapping
    public ResponseEntity<List<MedicoDto>> listDoctors(){
        List<MedicoDto> list = medicoService.findAll();
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MedicoDto> findDoctor(@PathVariable Long id){
        MedicoDto medicoDto = medicoService.findById(id);
        if(medicoDto != null){
            return new ResponseEntity<>(medicoDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<MedicoDto> saveDoctor(@RequestBody MedicoCreateRequest medicoCreateRequest){
        MedicoDto medicoDto = medicoService.save(medicoCreateRequest);
        return new ResponseEntity<>(medicoDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MedicoDto> updateDoctor(@RequestBody MedicoDto medicoData, @PathVariable Long id){
        MedicoDto medicoDto = medicoService.update(id, medicoData);
        if(medicoDto != null){
            return new ResponseEntity<>(medicoDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDoctor(@PathVariable Long id){
        medicoService.delete(id);
    }

}
