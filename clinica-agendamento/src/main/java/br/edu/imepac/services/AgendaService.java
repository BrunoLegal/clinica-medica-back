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

//TODO: Testar todos os metodos, foco no update

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
        Optional<List<AgendaConsulta>> optional = agendaRepository.findByDataAndHoraAndCanceladoFalse(date, hora);
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
        if(optional.isEmpty() || !isAvailable(agendaData.getData(), agendaData.getHora())){
            return null;
        }else{
            AgendaConsulta agendaConsulta = optional.get();
            AgendaConsultaDto agendaConsultaDto = new AgendaConsultaDto();
            agendaConsultaDto.setId(agendaConsulta.getId());
            agendaConsultaDto.setUsuario(agendaConsulta.getUsuario());
            agendaConsultaDto.setMedico(agendaConsulta.getMedico());
            agendaConsultaDto.setRetorno(agendaConsulta.getRetorno());
            agendaConsultaDto.setData(agendaConsulta.getData());
            agendaConsultaDto.setHora(agendaConsulta.getHora());
            agendaConsultaDto.setCancelado(agendaConsulta.getCancelado());
            agendaConsultaDto.setPaciente(agendaConsulta.getPaciente());
            agendaConsultaDto.setMotivoCancelamento(agendaConsulta.getMotivoCancelamento());
            return agendaConsultaDto;
        }
    }

    public AgendaConsultaDto setReturn(AgendaConsultaCreateRequest agendaConsultaCreateRequest){
        if(isAvailable(agendaConsultaCreateRequest.getData(), agendaConsultaCreateRequest.getHora())){
            AgendaConsulta agendaConsulta = mapper.map(agendaConsultaCreateRequest, AgendaConsulta.class);
            agendaConsulta.setRetorno(true);
            AgendaConsulta savedConsulta = agendaRepository.save(agendaConsulta);

            AgendaConsultaDto agendaConsultaDto = mapper.map(savedConsulta, AgendaConsultaDto.class);
            return agendaConsultaDto;
        }else{
            return null;
        }
    }

    public AgendaConsultaDto cancelConsulta(Long id, String motivo){
        Optional<AgendaConsulta> optional = agendaRepository.findById(id);
        if(optional.isEmpty() || !isAvailable(optional.get().getData(), optional.get().getHora())){
            return null;
        }else{
            AgendaConsulta agendaConsulta = optional.get();
            agendaConsulta.setCancelado(true);
            agendaConsulta.setMotivoCancelamento(motivo);
            AgendaConsultaDto savedAgenda = mapper.map(agendaConsulta, AgendaConsultaDto.class);
            AgendaConsultaDto agendaConsultaDto = update(agendaConsulta.getId(), savedAgenda);
            return agendaConsultaDto;
        }
    }
    public void deleteConsulta(Long id){
        agendaRepository.deleteById(id);
    }
}
