package br.edu.imepac.services;

import br.edu.imepac.dtos.AgendaConsultaCreateRequest;
import br.edu.imepac.dtos.AgendaConsultaDto;
import br.edu.imepac.dtos.AgendaMapperDto;
import br.edu.imepac.models.*;
import br.edu.imepac.repositories.AgendaRepository;
import br.edu.imepac.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgendaServiceTest {
    @InjectMocks
    private AgendaService agendaService;
    @Mock
    private AgendaRepository agendaRepository;
    @Mock
    private ModelMapper modelMapper;
    @BeforeEach
    void setUp(){}

    private AgendaConsulta newAgendaConsultaExample(int id) {
        switch (id){
            case 1:
                AgendaConsulta agendaConsulta1 = new AgendaConsulta();
                agendaConsulta1.setId(1L);
                agendaConsulta1.setUsuario(new UsuarioModel(1L, "logina", "1234", true, true, true, true, true, true, true, true, true, true,true));
                agendaConsulta1.setPaciente(new PacienteModel(1L, "Josivaldo", "asda", "as","1232141234", "asdasd", "123455", "asdasd", "asdasd", "sadasd", "as", "1234455", "1234", "123123", "m", "s", new ConvenioModel(1L,"asd", "asdas", "sdasd"), "asdasd"));
                agendaConsulta1.setMedico(new MedicoModel(1L, "Ciclano", "1213214", new EspecialidadeModel(1L, "pediatra")));
                try {
                    agendaConsulta1.setData(DateUtil.convertStringToSqlDate("10/10/2020"));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                agendaConsulta1.setHora("10");
                agendaConsulta1.setRetorno(false);
                agendaConsulta1.setCancelado(false);
                agendaConsulta1.setMotivoCancelamento("nada");
                return agendaConsulta1;
            case 2:
                AgendaConsulta agendaConsulta2 = new AgendaConsulta();
                agendaConsulta2.setId(2L);
                agendaConsulta2.setUsuario(new UsuarioModel(2L, "loginb", "4321", false, false, false, false,false, false, false, false,false, false,false));
                agendaConsulta2.setPaciente(new PacienteModel(2L, "Regina", "dsadsa", "sa","456456456", "fghgfh", "54321", "fghfghfg", "asdasd", "sasa", "sa", "34534", "3211", "123123", "f", "s", new ConvenioModel(2L,"dsa", "asdas", "sdasd"), "asdasd"));
                agendaConsulta2.setMedico(new MedicoModel(2L, "Fulano", "asdasd", new EspecialidadeModel(2L, "otorrino")));
                try {
                    agendaConsulta2.setData(DateUtil.convertStringToSqlDate("20/12/2022"));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                agendaConsulta2.setHora("20");
                agendaConsulta2.setRetorno(false);
                agendaConsulta2.setCancelado(false);
                agendaConsulta2.setMotivoCancelamento("tudo");
                return agendaConsulta2;
            default:
                return null;

        }
    }

    private AgendaConsultaDto newAgendaDtoExample (int id){
        switch (id){
            case 1:
                AgendaConsultaDto agendaConsulta1 = new AgendaConsultaDto();
                agendaConsulta1.setId(1L);
                agendaConsulta1.setUsuario(new UsuarioModel(1L, "logina", "1234", true, true, true, true, true, true, true, true, true, true,true));
                agendaConsulta1.setPaciente(new PacienteModel(1L, "Josivaldo", "asda", "as","1232141234", "asdasd", "123455", "asdasd", "asdasd", "sadasd", "as", "1234455", "1234", "123123", "m", "s", new ConvenioModel(1L,"asd", "asdas", "sdasd"), "asdasd"));
                agendaConsulta1.setMedico(new MedicoModel(1L, "Ciclano", "1213214", new EspecialidadeModel(1L, "pediatra")));
                agendaConsulta1.setData("10/10/2020");
                agendaConsulta1.setHora("10");
                agendaConsulta1.setRetorno(false);
                agendaConsulta1.setCancelado(false);
                agendaConsulta1.setMotivoCancelamento("nada");
                return agendaConsulta1;
            case 2:
                AgendaConsultaDto agendaConsulta2 = new AgendaConsultaDto();
                agendaConsulta2.setId(2L);
                agendaConsulta2.setUsuario(new UsuarioModel(2L, "loginb", "4321", false, false, false, false,false, false, false, false,false, false,false));
                agendaConsulta2.setPaciente(new PacienteModel(2L, "Regina", "dsadsa", "sa","456456456", "fghgfh", "54321", "fghfghfg", "asdasd", "sasa", "sa", "34534", "3211", "123123", "f", "s", new ConvenioModel(2L,"dsa", "asdas", "sdasd"), "asdasd"));
                agendaConsulta2.setMedico(new MedicoModel(2L, "Fulano", "asdasd", new EspecialidadeModel(2L, "otorrino")));
                agendaConsulta2.setData("20/12/2022");
                agendaConsulta2.setHora("20");
                agendaConsulta2.setRetorno(false);
                agendaConsulta2.setCancelado(false);
                agendaConsulta2.setMotivoCancelamento("tudo");
                return agendaConsulta2;
            default:
                return null;

        }
    }

    private AgendaConsultaCreateRequest newAgendaCreateRequestExample (int id){
        switch (id){
            case 1:
                AgendaConsultaCreateRequest agendaConsulta1 = new AgendaConsultaCreateRequest();
                agendaConsulta1.setUsuario(new UsuarioModel(1L, "logina", "1234", true, true, true, true, true, true, true, true, true, true,true));
                agendaConsulta1.setPaciente(new PacienteModel(1L, "Josivaldo", "asda", "as","1232141234", "asdasd", "123455", "asdasd", "asdasd", "sadasd", "as", "1234455", "1234", "123123", "m", "s", new ConvenioModel(1L,"asd", "asdas", "sdasd"), "asdasd"));
                agendaConsulta1.setMedico(new MedicoModel(1L, "Ciclano", "1213214", new EspecialidadeModel(1L, "pediatra")));
                agendaConsulta1.setData("10/10/2020");
                agendaConsulta1.setHora("10");
                agendaConsulta1.setRetorno(false);
                agendaConsulta1.setCancelado(false);
                return agendaConsulta1;
            case 2:
                AgendaConsultaCreateRequest agendaConsulta2 = new AgendaConsultaCreateRequest();
                agendaConsulta2.setUsuario(new UsuarioModel(2L, "loginb", "4321", false, false, false, false,false, false, false, false,false, false,false));
                agendaConsulta2.setPaciente(new PacienteModel(2L, "Regina", "dsadsa", "sa","456456456", "fghgfh", "54321", "fghfghfg", "asdasd", "sasa", "sa", "34534", "3211", "123123", "f", "s", new ConvenioModel(2L,"dsa", "asdas", "sdasd"), "asdasd"));
                agendaConsulta2.setMedico(new MedicoModel(2L, "Fulano", "asdasd", new EspecialidadeModel(2L, "otorrino")));
                agendaConsulta2.setData("20/12/2022");
                agendaConsulta2.setHora("20");
                agendaConsulta2.setRetorno(false);
                agendaConsulta2.setCancelado(false);
                return agendaConsulta2;
            default:
                return null;

        }
    }
    private AgendaMapperDto newAgendaMapperExample (int id){
        switch (id){
            case 1:
                AgendaMapperDto agendaConsulta1 = new AgendaMapperDto();
                agendaConsulta1.setId(1L);
                agendaConsulta1.setUsuario(new UsuarioModel(1L, "logina", "1234", true, true, true, true, true, true, true, true, true, true,true));
                agendaConsulta1.setPaciente(new PacienteModel(1L, "Josivaldo", "asda", "as","1232141234", "asdasd", "123455", "asdasd", "asdasd", "sadasd", "as", "1234455", "1234", "123123", "m", "s", new ConvenioModel(1L,"asd", "asdas", "sdasd"), "asdasd"));
                agendaConsulta1.setMedico(new MedicoModel(1L, "Ciclano", "1213214", new EspecialidadeModel(1L, "pediatra")));
                agendaConsulta1.setHora("10");
                agendaConsulta1.setRetorno(false);
                agendaConsulta1.setCancelado(false);
                agendaConsulta1.setMotivoCancelamento("nada");
                return agendaConsulta1;
            case 2:
                AgendaMapperDto agendaConsulta2 = new AgendaMapperDto();
                agendaConsulta2.setId(2L);
                agendaConsulta2.setUsuario(new UsuarioModel(2L, "loginb", "4321", false, false, false, false,false, false, false, false,false, false,false));
                agendaConsulta2.setPaciente(new PacienteModel(2L, "Regina", "dsadsa", "sa","456456456", "fghgfh", "54321", "fghfghfg", "asdasd", "sasa", "sa", "34534", "3211", "123123", "f", "s", new ConvenioModel(2L,"dsa", "asdas", "sdasd"), "asdasd"));
                agendaConsulta2.setMedico(new MedicoModel(2L, "Fulano", "asdasd", new EspecialidadeModel(2L, "otorrino")));
                agendaConsulta2.setHora("20");
                agendaConsulta2.setRetorno(false);
                agendaConsulta2.setCancelado(false);
                agendaConsulta2.setMotivoCancelamento("tudo");
                return agendaConsulta2;
            default:
                return null;

        }
    }

    @Test
    public void shouldSave(){
        try {
            AgendaConsultaCreateRequest agendaConsultaCreateRequest = newAgendaCreateRequestExample(1);
            when(agendaRepository.save(any(AgendaConsulta.class))).thenReturn(newAgendaConsultaExample(1));
            when(modelMapper.map(any(AgendaConsultaCreateRequest.class), eq(AgendaMapperDto.class))).thenReturn(newAgendaMapperExample(1));
            when(modelMapper.map(any(AgendaMapperDto.class), eq(AgendaConsulta.class))).thenReturn(newAgendaConsultaExample(1));
            when(modelMapper.map(any(AgendaConsulta.class), eq(AgendaMapperDto.class))).thenReturn(newAgendaMapperExample(1));
            when(modelMapper.map(any(AgendaMapperDto.class), eq(AgendaConsultaDto.class))).thenReturn(newAgendaDtoExample(1));



            AgendaConsultaDto agendaConsultaDto = agendaService.createConsulta(agendaConsultaCreateRequest);

            assertEquals(agendaConsultaCreateRequest.getUsuario(), agendaConsultaDto.getUsuario());
            assertEquals(agendaConsultaCreateRequest.getPaciente(), agendaConsultaDto.getPaciente());
            assertEquals(agendaConsultaCreateRequest.getMedico(), agendaConsultaDto.getMedico());
            assertEquals(agendaConsultaCreateRequest.getData(), agendaConsultaDto.getData());
            assertEquals(agendaConsultaCreateRequest.getHora(), agendaConsultaDto.getHora());
            assertEquals(agendaConsultaCreateRequest.getRetorno(), agendaConsultaDto.getRetorno());
            assertEquals(agendaConsultaCreateRequest.getCancelado(), agendaConsultaDto.getCancelado());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void shouldFindAll(){
        AgendaConsulta agendaConsulta1 = newAgendaConsultaExample(1);
        AgendaConsulta agendaConsulta2 = newAgendaConsultaExample(2);

        List<AgendaConsulta> list = Arrays.asList(agendaConsulta1, agendaConsulta2);

        when(agendaRepository.findAll()).thenReturn(list);
        when(modelMapper.map(newAgendaConsultaExample(1), AgendaMapperDto.class)).thenReturn(newAgendaMapperExample(1));
        when(modelMapper.map(newAgendaConsultaExample(2), AgendaMapperDto.class)).thenReturn(newAgendaMapperExample(2));
        when(modelMapper.map(newAgendaMapperExample(1), AgendaConsultaDto.class)).thenReturn(newAgendaDtoExample(1));
        when(modelMapper.map(newAgendaMapperExample(2), AgendaConsultaDto.class)).thenReturn(newAgendaDtoExample(2));


        List<AgendaConsultaDto> result = agendaService.findAll();

        assertEquals(2, result.size());
        assertEquals(agendaConsulta1.getMedico(), result.get(0).getMedico());
        assertEquals(agendaConsulta1.getUsuario(), result.get(0).getUsuario());
        assertEquals(agendaConsulta1.getPaciente(), result.get(0).getPaciente());
        try {
            assertEquals(agendaConsulta1.getData(), DateUtil.convertStringToSqlDate(result.get(0).getData()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        assertEquals(agendaConsulta1.getHora(), result.get(0).getHora());
        assertEquals(agendaConsulta1.getRetorno(), result.get(0).getRetorno());
        assertEquals(agendaConsulta1.getCancelado(), result.get(0).getCancelado());
        assertEquals(agendaConsulta1.getMotivoCancelamento(), result.get(0).getMotivoCancelamento());


    }

    @Test
    public void shoulFindById(){
        Long id = 1L;

        AgendaConsulta agendaConsulta = newAgendaConsultaExample(1);

        when(agendaRepository.findById(id)).thenReturn(Optional.of(agendaConsulta));
        when(modelMapper.map(any(AgendaConsulta.class), eq(AgendaMapperDto.class))).thenReturn(newAgendaMapperExample(1));
        when(modelMapper.map(any(AgendaMapperDto.class), eq(AgendaConsultaDto.class))).thenReturn(newAgendaDtoExample(1));

        AgendaConsultaDto result = agendaService.findById(id);

        assertEquals(agendaConsulta.getId(), result.getId());
        assertEquals(agendaConsulta.getUsuario(), result.getUsuario());
        assertEquals(agendaConsulta.getPaciente(), result.getPaciente());
        assertEquals(agendaConsulta.getMedico(), result.getMedico());
        try {
            assertEquals(agendaConsulta.getData(), DateUtil.convertStringToSqlDate(result.getData()));
        }catch (ParseException e){

        }
        assertEquals(agendaConsulta.getHora(), result.getHora());
        assertEquals(agendaConsulta.getRetorno(), result.getRetorno());
        assertEquals(agendaConsulta.getCancelado(), result.getCancelado());
        assertEquals(agendaConsulta.getMotivoCancelamento(), result.getMotivoCancelamento());

    }
    @Test
    public void findByIdShouldReturnNull(){
        Long id = 1L;

        when(agendaRepository.findById(id)).thenReturn(Optional.empty());

        AgendaConsultaDto result = agendaService.findById(id);

        assertNull(result);
        verify(agendaRepository, times(1)).findById(id);
    }

    @Test
    public void shouldDelete(){
        Long id = 1L;

        doNothing().when(agendaRepository).deleteById(id);

        agendaService.deleteConsulta(id);

        verify(agendaRepository, times(1)).deleteById(id);
    }

    @Test
    public void shouldUpdate(){
        Long id = 1L;

        AgendaConsultaDto parametro = newAgendaDtoExample(2);

        AgendaConsulta respostaRepository = newAgendaConsultaExample(1);

        when(agendaRepository.findById(id)).thenReturn(Optional.of(respostaRepository));
        when(agendaRepository.save(any(AgendaConsulta.class))).thenReturn(respostaRepository);
        when(modelMapper.map(any(AgendaConsulta.class), eq(AgendaMapperDto.class))).thenReturn(newAgendaMapperExample(2));
        when(modelMapper.map(any(AgendaMapperDto.class), eq(AgendaConsultaDto.class))).thenReturn(newAgendaDtoExample(2));


        try {
            AgendaConsultaDto result = agendaService.update(id, parametro);
            assertEquals(parametro.getUsuario(), result.getUsuario());
            assertEquals(parametro.getPaciente(), result.getPaciente());
            assertEquals(parametro.getMedico(), result.getMedico());
            assertEquals(parametro.getData(), result.getData());
            assertEquals(parametro.getHora(), result.getHora());
            assertEquals(parametro.getRetorno(), result.getRetorno());
            assertEquals(parametro.getCancelado(), result.getCancelado());
            assertEquals(parametro.getMotivoCancelamento(), result.getMotivoCancelamento());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

    @Test
    public void updateShouldReturnNull(){
        Long id = 1L;

        AgendaConsultaDto parametro = newAgendaDtoExample(2);

        when(agendaRepository.findById(id)).thenReturn(Optional.empty());
        try {
            AgendaConsultaDto result = agendaService.update(id, parametro);
            assertNull(result);
            verify(agendaRepository, times(1)).findById(id);
            verify(agendaRepository, times(0)).save(any(AgendaConsulta.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    public void isAvailableReturnsTrue() throws ParseException{


        Date dataParam = DateUtil.convertStringToSqlDate("10/10/2020");
        String horaParam = "10";


        when(agendaRepository.findByDataAndHoraAndCanceladoFalse(dataParam, horaParam)).thenReturn(Optional.empty());

        Boolean result = agendaService.isAvailable("10/10/2020", "10");
        assertTrue(result);
        verify(agendaRepository, times(1)).findByDataAndHoraAndCanceladoFalse(dataParam, horaParam);

    }

    @Test
    public void isAvailableReturnsFalse() throws ParseException{
        Date dataParam = DateUtil.convertStringToSqlDate("10/10/2020");
        String horaParam = "10";

        List<AgendaConsulta> list = List.of(newAgendaConsultaExample(1));

        when(agendaRepository.findByDataAndHoraAndCanceladoFalse(dataParam, horaParam)).thenReturn(Optional.of(list));

        Boolean result = agendaService.isAvailable("10/10/2020", "10");

        assertFalse(result);
        verify(agendaRepository, times(1)).findByDataAndHoraAndCanceladoFalse(dataParam, horaParam);

    }

    @Test
    public void shouldReturn(){
        try {
            AgendaConsultaDto agendaConsultaDto = newAgendaDtoExample(1);
            agendaConsultaDto.setRetorno(true);
            AgendaConsultaCreateRequest agendaConsultaCreateRequest = newAgendaCreateRequestExample(1);
            when(agendaRepository.save(any(AgendaConsulta.class))).thenReturn(newAgendaConsultaExample(1));
            when(modelMapper.map(any(AgendaConsultaCreateRequest.class), eq(AgendaMapperDto.class))).thenReturn(newAgendaMapperExample(1));
            when(modelMapper.map(any(AgendaMapperDto.class), eq(AgendaConsulta.class))).thenReturn(newAgendaConsultaExample(1));
            when(modelMapper.map(any(AgendaConsulta.class), eq(AgendaMapperDto.class))).thenReturn(newAgendaMapperExample(1));
            when(modelMapper.map(any(AgendaMapperDto.class), eq(AgendaConsultaDto.class))).thenReturn(agendaConsultaDto);



            AgendaConsultaDto result = agendaService.setReturn(agendaConsultaCreateRequest);

            assertEquals(agendaConsultaCreateRequest.getUsuario(), result.getUsuario());
            assertEquals(agendaConsultaCreateRequest.getPaciente(), result.getPaciente());
            assertEquals(agendaConsultaCreateRequest.getMedico(), result.getMedico());
            assertEquals(agendaConsultaCreateRequest.getData(), result.getData());
            assertEquals(agendaConsultaCreateRequest.getHora(), result.getHora());
            assertEquals(true, result.getRetorno());
            assertEquals(agendaConsultaCreateRequest.getCancelado(), result.getCancelado());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void shouldCancel(){
        try {
            Long id = 1L;
            String motivo = "teste";
            AgendaConsultaDto agendaConsultaDto = newAgendaDtoExample(1);
            agendaConsultaDto.setCancelado(true);
            agendaConsultaDto.setMotivoCancelamento(motivo);
            AgendaConsultaCreateRequest agendaConsultaCreateRequest = newAgendaCreateRequestExample(1);
            when(agendaRepository.findById(id)).thenReturn(Optional.of(newAgendaConsultaExample(1)));
            when(agendaRepository.save(any(AgendaConsulta.class))).thenReturn(newAgendaConsultaExample(1));

            when(modelMapper.map(any(AgendaConsulta.class), eq(AgendaMapperDto.class))).thenReturn(newAgendaMapperExample(1));
            when(modelMapper.map(any(AgendaMapperDto.class), eq(AgendaConsultaDto.class))).thenReturn(agendaConsultaDto);



            AgendaConsultaDto result = agendaService.cancelConsulta(id, motivo);

            assertEquals(agendaConsultaCreateRequest.getUsuario(), result.getUsuario());
            assertEquals(agendaConsultaCreateRequest.getPaciente(), result.getPaciente());
            assertEquals(agendaConsultaCreateRequest.getMedico(), result.getMedico());
            assertEquals(agendaConsultaCreateRequest.getData(), result.getData());
            assertEquals(agendaConsultaCreateRequest.getHora(), result.getHora());
            assertEquals(agendaConsultaCreateRequest.getRetorno(), result.getRetorno());
            assertEquals(true, result.getCancelado());
            assertEquals("teste", result.getMotivoCancelamento());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
