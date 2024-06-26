package br.edu.imepac.controllers;

import br.edu.imepac.dtos.AgendaConsultaCreateRequest;
import br.edu.imepac.dtos.AgendaConsultaDto;
import br.edu.imepac.services.AgendaService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/agendamento")
public class AgendaController {
    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public ResponseEntity<List<AgendaConsultaDto>> findAll(){
        List<AgendaConsultaDto> list = agendaService.findAll();
        if(list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaConsultaDto> findById(@PathVariable Long id){
        AgendaConsultaDto agendaConsultaDto = agendaService.findById(id);
        if(agendaConsultaDto != null){
            return new ResponseEntity<>(agendaConsultaDto, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AgendaConsultaDto> createConsulta(@RequestBody AgendaConsultaCreateRequest agendaConsultaCreateRequest){
        AgendaConsultaDto agendaConsultaDto = agendaService.createConsulta(agendaConsultaCreateRequest);
        if(agendaConsultaDto != null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(agendaConsultaDto, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaConsultaDto> updateConsulta(@RequestBody AgendaConsultaDto agendaData, @PathVariable Long id){
        AgendaConsultaDto agendaConsultaDto = agendaService.update(id, agendaData);
        if(agendaConsultaDto != null){
            return new ResponseEntity<>(agendaConsultaDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<AgendaConsultaDto> cancelConsulta(@PathVariable Long id, @RequestBody String motivo){
        AgendaConsultaDto agendaConsultaDto = agendaService.cancelConsulta(id, motivo);
        if(agendaConsultaDto != null){
            return new ResponseEntity<>(agendaConsultaDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/retorno")
    public ResponseEntity<AgendaConsultaDto> scheduleReturn(@RequestBody AgendaConsultaCreateRequest agendaConsultaCreateRequest){
        AgendaConsultaDto agendaConsultaDto = agendaService.setReturn(agendaConsultaCreateRequest);
        if(agendaConsultaDto != null){
            return new ResponseEntity<>(agendaConsultaDto, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteConsulta(@PathVariable Long id){
        agendaService.deleteConsulta(id);
    }



}
