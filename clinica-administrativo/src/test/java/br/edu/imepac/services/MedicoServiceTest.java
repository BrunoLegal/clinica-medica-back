package br.edu.imepac.services;

import br.edu.imepac.dtos.EspecialidadeDto;
import br.edu.imepac.dtos.MedicoCreateRequest;
import br.edu.imepac.dtos.MedicoDto;
import br.edu.imepac.models.EspecialidadeModel;
import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.repositories.MedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicoServiceTest {

    @InjectMocks
    MedicoService medicoService;

    @Mock
    MedicoRepository medicoRepository;

    @BeforeEach
    void setUp(){}

    @Test
    public void shouldSave(){
        //parametro
        var id = 1L;
        EspecialidadeModel especialidade1 = new EspecialidadeModel(1L,"b");
        MedicoCreateRequest parametro = new MedicoCreateRequest("a", "a", especialidade1);

        //mocks
        MedicoModel respostaRepository = new MedicoModel(id, "a", "a", especialidade1);

        when(medicoRepository.save(any(MedicoModel.class))).thenReturn(respostaRepository);

        //teste
        MedicoDto resultado = medicoService.save(parametro);

        //validação
        assertEquals(parametro.getNome(), resultado.getNome());
        assertEquals(parametro.getCrm(), resultado.getCrm());
        assertEquals(parametro.getEspecialidade().getId(), resultado.getEspecialidade().getId());
        assertEquals(parametro.getEspecialidade().getDesc_especialidade(), resultado.getEspecialidade().getDesc_especialidade());
    }

    @Test
    public void shouldFindAll(){
        //parametros=nenhum
        //mock repository
        EspecialidadeModel especialidade1 = new EspecialidadeModel(24L, "ababa");
        MedicoModel medico1 = new MedicoModel(1L, "a", "a", especialidade1);

        EspecialidadeModel especialidade2 = new EspecialidadeModel(234L, "scatman");
        MedicoModel medico2 = new MedicoModel(2L, "b", "b", especialidade2);

        EspecialidadeModel especialidade3 = new EspecialidadeModel(1L, "ringaradum");
        MedicoModel medico3 = new MedicoModel(3L, "c", "c", especialidade3);

        List<MedicoModel> respostaRepository = Arrays.asList(medico1,medico2,medico3);

        when(medicoRepository.findAll()).thenReturn(respostaRepository);

        //teste
        List<MedicoDto> resultado = medicoService.findAll();

        //validação
        assertEquals(medico1.getId(), resultado.get(0).getId());
        assertEquals(medico1.getCRM(), resultado.get(0).getCrm());
        assertEquals(medico1.getNome(), resultado.get(0).getNome());
        assertEquals(medico1.getEspecialidade().getId(), resultado.get(0).getEspecialidade().getId());
        assertEquals(medico1.getEspecialidade().getDesc_especialidade(), resultado.get(0).getEspecialidade().getDesc_especialidade());

        assertEquals(medico2.getId(), resultado.get(1).getId());
        assertEquals(medico2.getCRM(), resultado.get(1).getCrm());
        assertEquals(medico2.getNome(), resultado.get(1).getNome());
        assertEquals(medico2.getEspecialidade().getId(), resultado.get(1).getEspecialidade().getId());
        assertEquals(medico2.getEspecialidade().getDesc_especialidade(), resultado.get(1).getEspecialidade().getDesc_especialidade());

        assertEquals(medico3.getId(), resultado.get(2).getId());
        assertEquals(medico3.getCRM(), resultado.get(2).getCrm());
        assertEquals(medico3.getNome(), resultado.get(2).getNome());
        assertEquals(medico3.getEspecialidade().getId(), resultado.get(2).getEspecialidade().getId());
        assertEquals(medico3.getEspecialidade().getDesc_especialidade(), resultado.get(2).getEspecialidade().getDesc_especialidade());
    }

    @Test
    public void shouldFindById(){
        //parametro
        Long id = 1L;

        //mock repository
        EspecialidadeModel especialidadeModel = new EspecialidadeModel(34L, "virose");
        MedicoModel respostaRepository = new MedicoModel(1L, "nome", "crm", especialidadeModel);

        when(medicoRepository.findById(id)).thenReturn(Optional.of(respostaRepository));

        //teste
        MedicoDto resultado = medicoService.findById(id);

        //Validação

        assertEquals(1L, resultado.getId());
        assertEquals("nome", resultado.getNome());
        assertEquals("crm", resultado.getCrm());
        assertEquals(especialidadeModel.getId(), resultado.getEspecialidade().getId());
        assertEquals(especialidadeModel.getDesc_especialidade(), resultado.getEspecialidade().getDesc_especialidade());
    }

    @Test
    public void findByIdShouldReturnNull(){
        //parametro
        Long id = 34L;

        //mock
        when(medicoRepository.findById(id)).thenReturn(Optional.empty());

        //test
        MedicoDto resultado = medicoService.findById(id);

        //validação
        assertNull(resultado);
    }

    @Test
    public void ShouldDelete(){
        //parametro
        Long id = 69L;

        //mock repository
        doNothing().when(medicoRepository).deleteById(id);

        //teste
        medicoService.delete(id);

        //validação
        verify(medicoRepository,times(1)).deleteById(id);
    }

    @Test
    public void shouldUpdate(){
        //parametro
        Long id = 420L;

        EspecialidadeModel especialidadeModel = new EspecialidadeModel(32L, "exame de prostata");
        MedicoDto parametro = new MedicoDto(id, "dedoson", "CRiMe", especialidadeModel);

        //mock repository
        MedicoModel beforeUpdate = new MedicoModel(id, "cotoveloson", "CPiFe", especialidadeModel);
        MedicoModel respostaRepository = new MedicoModel(id, "dedoson", "CRiMe", especialidadeModel);
        when(medicoRepository.findById(id)).thenReturn(Optional.of(beforeUpdate));
        when(medicoRepository.save(any(MedicoModel.class))).thenReturn(respostaRepository);

        //teste
        MedicoDto resultado = medicoService.update(id, parametro);

        //validação
        assertEquals(parametro.getId(), resultado.getId());
        assertEquals(parametro.getNome(), resultado.getNome());
        assertEquals(parametro.getCrm(), resultado.getCrm());
        assertEquals(parametro.getEspecialidade().getId(), resultado.getEspecialidade().getId());
        assertEquals(parametro.getEspecialidade().getDesc_especialidade(), resultado.getEspecialidade().getDesc_especialidade());
    }

    @Test
    public void updateShoudReturnNull(){
        //parametros
        Long id = 32L;

        EspecialidadeModel especialidadeModel = new EspecialidadeModel(3L, "dipirona");
        MedicoDto parametro = new MedicoDto(id, "batata", "carne de porco cozinhada com manjericão", especialidadeModel);
        //mock Repository
        when(medicoRepository.findById(id)).thenReturn(Optional.empty());

        //testes
        MedicoDto resultado = medicoService.update(id, parametro);

        //validação
        assertNull(resultado);
        verify(medicoRepository, times(1)).findById(id);
        verify(medicoRepository, times(0)).save(any(MedicoModel.class));
    }
}
