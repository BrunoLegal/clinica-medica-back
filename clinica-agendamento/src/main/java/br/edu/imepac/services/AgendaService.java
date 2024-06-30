package br.edu.imepac.services;

import br.edu.imepac.dtos.AgendaConsultaCreateRequest;
import br.edu.imepac.dtos.AgendaConsultaDto;
import br.edu.imepac.models.AgendaConsulta;
import br.edu.imepac.repositories.AgendaRepository;
import br.edu.imepac.util.DateUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
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
    public AgendaConsultaDto createConsulta(AgendaConsultaCreateRequest agendaConsultaCreateRequest) throws Exception{

        if(isAvailable(agendaConsultaCreateRequest.getData(), agendaConsultaCreateRequest.getHora())) {
            AgendaConsulta agendaConsulta = mapper.map(agendaConsultaCreateRequest, AgendaConsulta.class);
            agendaConsulta.setData(DateUtil.convertStringToSqlDate(agendaConsultaCreateRequest.getData()));

            AgendaConsulta savedAgenda = agendaRepository.save(agendaConsulta);
            AgendaConsultaDto agendaConsultaDto = mapper.map(savedAgenda, AgendaConsultaDto.class);
            agendaConsultaDto.setData(DateUtil.convertSqlDateToString(savedAgenda.getData()));

            return agendaConsultaDto;
        }else{
            throw new Exception("Essa data ou hora está indisponível");
        }
    }

    public Boolean isAvailable(String date, String hora){
        try {
            Date sqlDate = DateUtil.convertStringToSqlDate(date);
            Optional<List<AgendaConsulta>> optional = agendaRepository.findByDataAndHoraAndCanceladoFalse(sqlDate, hora);
            return optional.isEmpty();
        }catch (ParseException e){
            return null;
        }
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

    public AgendaConsultaDto update(Long id, AgendaConsultaDto agendaData) throws Exception{
        Optional<AgendaConsulta> optional = agendaRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }else{
            if (isAvailable(agendaData.getData(), agendaData.getHora())){
                AgendaConsulta agendaConsulta = optional.get();

                agendaConsulta.setUsuario(agendaData.getUsuario());
                agendaConsulta.setMedico(agendaData.getMedico());
                agendaConsulta.setRetorno(agendaData.getRetorno());
                agendaConsulta.setData(DateUtil.convertStringToSqlDate(agendaData.getData()));
                agendaConsulta.setHora(agendaConsulta.getHora());
                agendaConsulta.setCancelado(agendaData.getCancelado());
                agendaConsulta.setPaciente(agendaData.getPaciente());
                agendaConsulta.setMotivoCancelamento(agendaData.getMotivoCancelamento());

                AgendaConsulta savedAgenda = agendaRepository.save(agendaConsulta);

                AgendaConsultaDto agendaConsultaDto = mapper.map(savedAgenda, AgendaConsultaDto.class);
                return agendaConsultaDto;
            }else {
                throw new Exception("Essa data e horário estão indisponiveis");
            }
        }
    }

    public AgendaConsultaDto setReturn(AgendaConsultaCreateRequest agendaConsultaCreateRequest) throws Exception{
        if(isAvailable(agendaConsultaCreateRequest.getData(), agendaConsultaCreateRequest.getHora())){
            AgendaConsulta agendaConsulta = mapper.map(agendaConsultaCreateRequest, AgendaConsulta.class);
            agendaConsulta.setRetorno(true);
            AgendaConsulta savedConsulta = agendaRepository.save(agendaConsulta);

            AgendaConsultaDto agendaConsultaDto = mapper.map(savedConsulta, AgendaConsultaDto.class);
            return agendaConsultaDto;
        }else{
            throw new Exception("Data inserida não é possível");
        }
    }

    public AgendaConsultaDto cancelConsulta(Long id, String motivo){
        Optional<AgendaConsulta> optional = agendaRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }else{
            AgendaConsulta agendaConsulta = optional.get();

            agendaConsulta.setCancelado(true);
            agendaConsulta.setMotivoCancelamento(motivo);

            AgendaConsulta savedAgenda = agendaRepository.save(agendaConsulta);

            AgendaConsultaDto agendaConsultaDto = mapper.map(savedAgenda, AgendaConsultaDto.class);
            return agendaConsultaDto;
        }
    }
    public void deleteConsulta(Long id){
        agendaRepository.deleteById(id);
    }
}
