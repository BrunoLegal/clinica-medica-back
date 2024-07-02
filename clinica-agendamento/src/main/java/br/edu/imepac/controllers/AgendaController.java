package br.edu.imepac.controllers;

import br.edu.imepac.dtos.AgendaConsultaCreateRequest;
import br.edu.imepac.dtos.AgendaConsultaDto;
import br.edu.imepac.services.AgendaService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/agendamento")
public class AgendaController {

    private static final Logger logger = LoggerFactory.getLogger(AgendaController.class);
    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public ResponseEntity<List<AgendaConsultaDto>> findAll(){
        List<AgendaConsultaDto> list = agendaService.findAll();
        logger.info("Agendamentos encontrados: {}", list);
        if(list.isEmpty()){
            logger.info("NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            logger.info("OK");
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaConsultaDto> findById(@PathVariable Long id){
        AgendaConsultaDto agendaConsultaDto = agendaService.findById(id);
        logger.info("Agendamento encontrado por id: {}", agendaConsultaDto);
        if(agendaConsultaDto != null){
            logger.info("OK");
            return new ResponseEntity<>(agendaConsultaDto, HttpStatus.OK);
        }else {
            logger.info("NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AgendaConsultaDto> createConsulta(@RequestBody AgendaConsultaCreateRequest agendaConsultaCreateRequest){
        try {
            AgendaConsultaDto agendaConsultaDto = agendaService.createConsulta(agendaConsultaCreateRequest);
            if (agendaConsultaDto != null) {
                logger.info("Agendamento salvo: {}", agendaConsultaDto);
                return new ResponseEntity<>(agendaConsultaDto, HttpStatus.CREATED);
            } else {
                logger.warn("agendaConsultaDto retornou nulo");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            logger.warn("Erro de conflito: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AgendaConsultaDto> updateConsulta(@RequestBody AgendaConsultaDto agendaData, @PathVariable Long id){
        try {
            AgendaConsultaDto agendaConsultaDto = agendaService.update(id, agendaData);
            if (agendaConsultaDto != null) {
                logger.info("Agendamento atualizado: {}", agendaConsultaDto);
                return new ResponseEntity<>(agendaConsultaDto, HttpStatus.OK);
            } else {
                logger.warn("agendaConsultaDto retornou nulo");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            logger.warn("Erro de conflito: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<AgendaConsultaDto> cancelConsulta(@PathVariable Long id, @RequestBody String motivo){
        AgendaConsultaDto agendaConsultaDto = agendaService.cancelConsulta(id, motivo);
        if(agendaConsultaDto != null){
            logger.info("Agendamento cancelado");
            return new ResponseEntity<>(agendaConsultaDto, HttpStatus.OK);
        }else{
            logger.warn("Cancelamento nao foi possivel");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/retorno")
    public ResponseEntity<AgendaConsultaDto> scheduleReturn(@RequestBody AgendaConsultaCreateRequest agendaConsultaCreateRequest){
        try {
            AgendaConsultaDto agendaConsultaDto = agendaService.setReturn(agendaConsultaCreateRequest);
            if (agendaConsultaDto != null) {
                logger.info("Retorno marcado: {}", agendaConsultaDto);
                return new ResponseEntity<>(agendaConsultaDto, HttpStatus.CREATED);
            } else {
                logger.warn("agendaConsultaDto retornou nulo");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            logger.warn("Erro de conflito: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteConsulta(@PathVariable Long id){
        logger.info("Deletando Agendamento de id: {}", id);
        agendaService.deleteConsulta(id);
        logger.info("Agendamento de id {} deletado", id);
    }





}
