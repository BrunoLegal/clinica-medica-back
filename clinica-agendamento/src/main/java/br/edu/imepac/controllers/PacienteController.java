package br.edu.imepac.controllers;

import br.edu.imepac.dtos.PacienteCreateRequest;
import br.edu.imepac.dtos.PacienteDto;
import br.edu.imepac.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("paciente")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;
    @GetMapping
    public ResponseEntity<List<PacienteDto>> listPaciente(){
        List<PacienteDto> list = pacienteService.findAll();
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PacienteDto> findPaciente(@PathVariable Long id){
        PacienteDto pacienteDto = pacienteService.findById(id);
        if(pacienteDto != null){
            return new ResponseEntity<>(pacienteDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<PacienteDto> savePaciente(@RequestBody PacienteCreateRequest medicoCreateRequest){
        PacienteDto pacienteDto = pacienteService.save(medicoCreateRequest);
        return new ResponseEntity<>(pacienteDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PacienteDto> updatePaciente(@RequestBody PacienteDto pacienteData, @PathVariable Long id){
        PacienteDto pacienteDto = pacienteService.update(id, pacienteData);
        if(pacienteDto != null){
            return new ResponseEntity<>(pacienteDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePaciente(@PathVariable Long id){
        pacienteService.delete(id);
    }
}
