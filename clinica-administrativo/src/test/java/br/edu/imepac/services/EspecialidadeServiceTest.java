package br.edu.imepac.services;

import br.edu.imepac.dtos.EspecialidadeCreateRequest;
import br.edu.imepac.dtos.EspecialidadeDto;
import br.edu.imepac.models.EspecialidadeModel;
import br.edu.imepac.repositories.EspecialidadeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EspecialidadeServiceTest {

    @InjectMocks
    private EspecialidadeService especialidadeService;

    @Mock
    private EspecialidadeRepository especialidadeRepository;

    @Test
    public void shouldSave(){
        // preparação:
        //parâmetros fornecidos:
        EspecialidadeCreateRequest especialidadeCreateRequest = new EspecialidadeCreateRequest();
        especialidadeCreateRequest.setDesc_especialidade("ababa");

        //resposta do repository:
        EspecialidadeModel respostaRepository = new EspecialidadeModel();
        respostaRepository.setId(1L);
        respostaRepository.setDesc_especialidade("ababa");

        when(especialidadeRepository.save(any(EspecialidadeModel.class))).thenReturn(respostaRepository);

        //teste
        EspecialidadeDto resultado = especialidadeService.save(especialidadeCreateRequest);

        //validação
        assertEquals("ababa", resultado.getDesc_especialidade());
    }

    @Test
    public void shouldFindById(){
        //preparando parâmetro:
        var id = anyLong();

        //preparando resposta repository:
        EspecialidadeModel respostaRepository = new EspecialidadeModel();
        respostaRepository.setId(id);
        respostaRepository.setDesc_especialidade("irineu");

        when(especialidadeRepository.findById(id)).thenReturn(Optional.of(respostaRepository));

        //teste
        EspecialidadeDto resultado = especialidadeService.findById(id);

        //validação
        assertEquals(respostaRepository.getDesc_especialidade(), resultado.getDesc_especialidade());
        assertEquals(respostaRepository.getId(), resultado.getId());
    }

    @Test
    public void findByIdShouldReturnNull(){
        //preparando parâmetro:
        var id = anyLong();

        //preparando resposta do repository:
        when(especialidadeRepository.findById(id)).thenReturn(Optional.empty());

        //teste
        EspecialidadeDto resultado = especialidadeService.findById(id);

        //validação:
        assertNull(resultado);
        verify(especialidadeRepository, times(1)).findById(id);
    }

    @Test
    public void shouldFindAll(){
        //preparação do parâmetro desnecessária
        //preparação da respota do repostory
        EspecialidadeModel especialidade1 = new EspecialidadeModel(1L,"1");
        EspecialidadeModel especialidade2 = new EspecialidadeModel(2L,"2");
        EspecialidadeModel especialidade3 = new EspecialidadeModel(3L,"3");
        EspecialidadeModel especialidade4 = new EspecialidadeModel(4L,"4");
        EspecialidadeModel especialidade5 = new EspecialidadeModel(5L,"5");

        List<EspecialidadeModel> respostaRepository = Arrays.asList(
                especialidade1,
                especialidade2,
                especialidade3,
                especialidade4,
                especialidade5
        );

        when(especialidadeRepository.findAll()).thenReturn(respostaRepository);

        //teste
        List<EspecialidadeDto> resultado = especialidadeService.findAll();

        //validação
        assertEquals(5, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
        assertEquals("1", resultado.get(0).getDesc_especialidade());
        assertEquals(2L, resultado.get(1).getId());
        assertEquals("2", resultado.get(1).getDesc_especialidade());
        assertEquals(3L, resultado.get(2).getId());
        assertEquals("3", resultado.get(2).getDesc_especialidade());
        assertEquals(4L, resultado.get(3).getId());
        assertEquals("4", resultado.get(3).getDesc_especialidade());
        assertEquals(5L, resultado.get(4).getId());
        assertEquals("5", resultado.get(4).getDesc_especialidade());

    }

    @Test
    public void shouldDelete(){
        //preparando parâmetros:
        var id = 1L;

        //preparando resposta do repository:
        doNothing().when(especialidadeRepository).deleteById(id);

        //teste
        especialidadeService.delete(id);

        //validação
        verify(especialidadeRepository, times(1)).deleteById(id);
    }

    @Test
    public void shouldUpdate(){
        //preparando parâmetros:
        var id = anyLong();

        EspecialidadeDto parametro = new EspecialidadeDto(id,"me salve");

        //preparando respostas do repository:
        EspecialidadeModel achei = new EspecialidadeModel(id, "to aqui");
        EspecialidadeModel atualizei = new EspecialidadeModel(id, "me salve");

        when(especialidadeRepository.findById(id)).thenReturn(Optional.of(achei));
        when(especialidadeRepository.save(any(EspecialidadeModel.class))).thenReturn(atualizei);

        //teste
        EspecialidadeDto resultado = especialidadeService.update(id, parametro);

        //validação:
        assertEquals(parametro.getId(), resultado.getId());
        assertEquals(parametro.getDesc_especialidade(), resultado.getDesc_especialidade());

    }

    @Test
    public void updateShouldReturnNull(){
        //ppreparando parametros
        var id = anyLong();

        EspecialidadeDto parametro = new EspecialidadeDto(id, "socorro");

        //preparando resposta deo repository:
        when(especialidadeRepository.findById(id)).thenReturn(Optional.empty());

        //teste
        EspecialidadeDto resultado = especialidadeService.update(id, parametro);

        //validação
        assertNull(resultado);
        verify(especialidadeRepository, times(1)).findById(id);
        verify(especialidadeRepository, times(0)).save(any(EspecialidadeModel.class));
    }
}
