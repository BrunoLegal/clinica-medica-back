package br.edu.imepac.services;

import br.edu.imepac.dtos.AgendaConsultaCreateRequest;
import br.edu.imepac.dtos.AgendaConsultaDto;
import br.edu.imepac.dtos.AgendaMapperDto;
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
            AgendaMapperDto agendaMapperDTO = mapper.map(agendaConsultaCreateRequest, AgendaMapperDto.class);
            System.out.println("agendaMapper: "+ agendaMapperDTO.getClass());
            AgendaConsulta agendaConsulta = mapper.map(agendaMapperDTO, AgendaConsulta.class);
            agendaConsulta.setData(DateUtil.convertStringToSqlDate(agendaConsultaCreateRequest.getData()));

            AgendaConsulta savedAgenda = agendaRepository.save(agendaConsulta);
            AgendaMapperDto savedAgendaMapperDto = mapper.map(savedAgenda, AgendaMapperDto.class);
            AgendaConsultaDto agendaConsultaDto = mapper.map(savedAgendaMapperDto, AgendaConsultaDto.class);
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
            if(optional.isPresent()){
                List<AgendaConsulta> list = optional.get();
                System.out.println(list.isEmpty());
                return list.isEmpty();
            }else{
                return true;
            }
        }catch (ParseException e){
            return null;
        }
    }

    public List<AgendaConsultaDto> findAll(){
        List<AgendaConsulta> list = agendaRepository.findAll();
        return list.stream().map(agenda -> {
            AgendaMapperDto agendaMapperDto = mapper.map(agenda, AgendaMapperDto.class);
            AgendaConsultaDto agendaConsultaDto = mapper.map(agendaMapperDto, AgendaConsultaDto.class);
            agendaConsultaDto.setData(DateUtil.convertSqlDateToString(agenda.getData()));
            return agendaConsultaDto;
        }).collect(Collectors.toList());
    }

    public AgendaConsultaDto findById(Long id){
        Optional<AgendaConsulta> optional = agendaRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }else{
            AgendaMapperDto agendaMapperDto = mapper.map(optional.get(), AgendaMapperDto.class);
            AgendaConsultaDto agendaConsultaDto = mapper.map(agendaMapperDto, AgendaConsultaDto.class);
            agendaConsultaDto.setData(DateUtil.convertSqlDateToString(optional.get().getData()));
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
                AgendaMapperDto agendaMapperDTO = mapper.map(savedAgenda, AgendaMapperDto.class);
                AgendaConsultaDto agendaConsultaDto = mapper.map(agendaMapperDTO, AgendaConsultaDto.class);
                agendaConsultaDto.setData(DateUtil.convertSqlDateToString(savedAgenda.getData()));
                return agendaConsultaDto;
            }else {
                throw new Exception("Essa data e horário estão indisponiveis");
            }
        }
    }

    public AgendaConsultaDto setReturn(AgendaConsultaCreateRequest agendaConsultaCreateRequest) throws Exception{
        if(isAvailable(agendaConsultaCreateRequest.getData(), agendaConsultaCreateRequest.getHora())){
            AgendaMapperDto agendaMapperDto = mapper.map(agendaConsultaCreateRequest, AgendaMapperDto.class);
            AgendaConsulta agendaConsulta = mapper.map(agendaMapperDto, AgendaConsulta.class);
            agendaConsulta.setData(DateUtil.convertStringToSqlDate(agendaConsultaCreateRequest.getData()));
            agendaConsulta.setRetorno(true);
            AgendaConsulta savedConsulta = agendaRepository.save(agendaConsulta);
            AgendaMapperDto savedMapperDto = mapper.map(savedConsulta, AgendaMapperDto.class);
            AgendaConsultaDto agendaConsultaDto = mapper.map(savedMapperDto, AgendaConsultaDto.class);
            agendaConsultaDto.setData(DateUtil.convertSqlDateToString(savedConsulta.getData()));
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
            AgendaMapperDto agendaMapperDto = mapper.map(savedAgenda,AgendaMapperDto.class);
            AgendaConsultaDto agendaConsultaDto = mapper.map(agendaMapperDto, AgendaConsultaDto.class);
            agendaConsultaDto.setData(DateUtil.convertSqlDateToString(savedAgenda.getData()));
            return agendaConsultaDto;
        }
    }
    public void deleteConsulta(Long id){
        agendaRepository.deleteById(id);
    }
}
