package br.edu.imepac.service;

import br.edu.imepac.dtos.AtendimentoCreateRequest;
import br.edu.imepac.dtos.AtendimentoDto;
import br.edu.imepac.models.*;
import br.edu.imepac.repository.AtendimentoRepository;
import br.edu.imepac.services.AtendimentoService;
import br.edu.imepac.util.DateUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.*;

import java.sql.Date;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AtendimentoServiceTest {

    @InjectMocks
    AtendimentoService atendimentoService;

    @Mock
    AtendimentoRepository atendimentoRepository;

    ModelMapper modelMapper = new ModelMapper();

    //parametros
    Date data1;
    EspecialidadeModel especialidade1;
    MedicoModel medico1;
    ConvenioModel convenio1;
    PacienteModel paciente1;
    UsuarioModel usuario1;
    AgendaConsulta agenda1;
    AtendimentoDto parametro1;

    Date data2;
    EspecialidadeModel especialidade2;
    MedicoModel medico2;
    ConvenioModel convenio2;
    PacienteModel paciente2;
    UsuarioModel usuario2;
    AgendaConsulta agenda2;
    AtendimentoDto parametro2;

    AtendimentoCreateRequest parametroCR;

    List<AtendimentoModel> respostaLista;

    //mock do repository
    AtendimentoModel resposta1;
    AtendimentoModel resposta2;

    AtendimentoDto resultado;
    List<AtendimentoDto> resultadoLista;

    @BeforeEach
    public void setUp() throws ParseException {
        //parametros
        data1 = DateUtil.convertStringToSqlDate("14/08/2004");
        especialidade1 = new EspecialidadeModel(1L, "virilias");
        medico1 = new MedicoModel(1L, "Áuzio", "CRIME", especialidade1);
        convenio1 = new ConvenioModel(1L, "chawpapa", "35", "40028922");
        paciente1 = new PacienteModel(1L, "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",convenio1, "p");
        usuario1 = new UsuarioModel(1L, "loguinho", "senha", true, true, true, true, true, true, true, true, true, true, true);
        agenda1 = new AgendaConsulta(1L, usuario1, paciente1, medico1, data1, "23:12", false, false, "N.A.");
        parametro1 = new AtendimentoDto(1L, agenda1 , "ababa", "bolo de chocolate com cobertura de cenoura", "psicotécnico");


        data2 = DateUtil.convertStringToSqlDate("14/08/2004");
        especialidade2 = new EspecialidadeModel(2L, "cansaço");
        medico2 = new MedicoModel(2L, "Awzee", "fbi,open thedoor", especialidade2);
        convenio2 = new ConvenioModel(2L, "bruno's agiotas", "12345678", "08002101210");
        paciente2 = new PacienteModel(2L, "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",convenio2, "z");
        usuario2 = new UsuarioModel(2L, "abullulubula", "password", false, true, false, true, true, false, true, false, false, true, false);
        agenda2 = new AgendaConsulta(2L, usuario2, paciente2, medico2, data2, "11:11", true, true, "um agiota do bruno matsequestrou o paciente");
        parametro2 = new AtendimentoDto(2L, agenda2 , "ababa", "bolo de chocolate com cobertura de cenoura", "psicotécnico");

        parametroCR = new AtendimentoCreateRequest(agenda1 , "ababa", "bolo de chocolate com cobertura de cenoura", "psicotécnico");

        respostaLista = Arrays.asList(resposta1,resposta2);

        //mock do repository
        resposta1 = modelMapper.map(parametro1, AtendimentoModel.class);
        resposta2 = modelMapper.map(parametro2, AtendimentoModel.class);

        resultado = new AtendimentoDto();
    }

    @Test
    public void shouldCreate(){
        //mock
        when(atendimentoRepository.save(any(AtendimentoModel.class))).thenReturn(resposta1);

        //test
        AtendimentoDto resultado = atendimentoService.createAtendimento(parametroCR);

        //validação
        assertEquals(parametroCR, modelMapper.map(resultado, AtendimentoCreateRequest.class));
    }
    @Test
    public void shouldDelete(){
        //mock
        doNothing().when(atendimentoRepository).deleteById(anyLong());

        //teste
        atendimentoService.deleteAtendimento(1L);

        //validação
        verify(atendimentoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void shouldFindAll(){
        //parametros
        //mock repository
        when(atendimentoRepository.findAll()).thenReturn(respostaLista);

        //teste
        resultadoLista = atendimentoService.getAllAtendimentos();
        //validação
        assertEquals(resultadoLista.get(0), respostaLista.get(0));
        assertEquals(resultadoLista.get(1), respostaLista.get(1));
    }

    @Test
    public void shouldGetById(){
        //parametro
        var id = 1L;

        //mock repository
        when(atendimentoRepository.findById(id)).thenReturn(Optional.of(resposta1));

        //test
        Optional<AtendimentoDto> resultadoop = atendimentoService.getAtendimentoById(id);

        //validação
        assertEquals(resultadoop, Optional.of(modelMapper.map(resposta1, AtendimentoDto.class)));
    }

    @Test
    public void shouldUpdate(){
        //parametros
        var id = 1L;

        //mock
        when(atendimentoRepository.findById(id)).thenReturn(Optional.of(resposta1));
        when(atendimentoRepository.save(any(AtendimentoModel.class))).thenReturn(resposta1);

        //test
        resultado = atendimentoService.updateAtendimento(id, parametroCR);

        //Validação
        assertEquals(resultado, modelMapper.map(parametroCR, AtendimentoDto.class));
    }

    @Test
    public void uodateShouldreturnOptionalEmpty(){
        //parametros
        var id = 1L;

        //mock
        when(atendimentoRepository.findById(id)).thenReturn(Optional.empty());

        //test
        resultado = atendimentoService.updateAtendimento(id, parametroCR);

        //validação
        assertNull(resultado);

    }
}