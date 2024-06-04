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
@RequestMapping("especialidade")
public class EspecialidadeController {

    @Autowired
    private MedicoService medicoService;
    @GetMapping
    public ResponseEntity<List<MedicoDto>> listDoctor(){
        List<MedicoDto> list = medicoService.buscaTodosMedicos();
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<MedicoDto> saveDoctor(@RequestBody MedicoCreateRequest medicoCreateRequest){
        MedicoDto medicoDto = medicoService.salvarMedico(medicoCreateRequest);
        return new ResponseEntity<>(medicoDto, HttpStatus.CREATED);
    }

}
