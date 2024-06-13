package br.edu.imepac.services;

import br.edu.imepac.dtos.AgendaConsultaCreateRequest;
import br.edu.imepac.dtos.AgendaConsultaDto;
import br.edu.imepac.models.AgendaConsulta;
import br.edu.imepac.repositories.AgendaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendaService {
    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private ModelMapper mapper;
    public AgendaConsultaDto createConsulta(AgendaConsultaCreateRequest agendaConsultaCreateRequest){
        if(isAvailable(agendaConsultaCreateRequest.getData(), agendaConsultaCreateRequest.getHora())) {
            AgendaConsulta agendaConsulta = mapper.map(agendaConsultaCreateRequest, AgendaConsulta.class);

            AgendaConsulta savedAgenda = agendaRepository.save(agendaConsulta);
            AgendaConsultaDto agendaConsultaDto = mapper.map(savedAgenda, AgendaConsultaDto.class);

            return agendaConsultaDto;
        }else{
            return null;
        }
    }

    public Boolean isAvailable(Date date, String hora){
        Optional<AgendaConsulta> optional = agendaRepository.findByDataAndHora(date, hora);
        return optional.isEmpty();
    }

    public List<AgendaConsultaDto> findAll(){
        List<AgendaConsulta> list = agendaRepository.findAll();
        return list.stream().map(agenda -> {
            AgendaConsultaDto agendaConsultaDto = mapper.map(agenda, AgendaConsultaDto.class);
            return agendaConsultaDto;
        }).collect(Collectors.toList());
    }

    public AgendaConsultaDto findById(Long id){
        Optional<AgendaConsulta> optional = agendaRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }else{
            AgendaConsultaDto agendaConsultaDto = mapper.map(optional.get(), AgendaConsultaDto.class);
            return agendaConsultaDto;
        }
    }

    public AgendaConsultaDto update(Long id, AgendaConsultaDto agendaData){
        Optional<AgendaConsulta> optional = agendaRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }else{
            AgendaConsulta agendaConsulta = optional.get();
            AgendaConsultaDto agendaConsultaDto = mapper.map(agendaConsulta, AgendaConsultaDto.class);
            agendaConsultaDto = mapper.map(agendaData, AgendaConsultaDto.class);
            return null;
        }
    }
}
