package br.edu.imepac.services;

import br.edu.imepac.dtos.UsuarioCreateRequest;
import br.edu.imepac.dtos.UsuarioDto;
import br.edu.imepac.models.UsuarioModel;
import br.edu.imepac.repositories.UsuarioRepository;
import net.bytebuddy.matcher.StringMatcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootApplication
public class UsuarioServiceTest {
    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private ModelMapper modelMapper;

    @Test
    public void shouldDelete(){
        //variaveis
        final Long id = 1L;

        //mock do repo
        doNothing().when(usuarioRepository).deleteById(id);

        //o que sera testado
        usuarioService.delete(id);

        //verifica se esta rodando certo
        verify(usuarioRepository, times(1)).deleteById(id);
    }

    @Test
    public void shouldFindAll(){
        //variaveis
        UsuarioModel usuarioModel1, usuarioModel2;

        usuarioModel1 = newExampleUsuarioModel(1);
        usuarioModel2 = newExampleUsuarioModel(2);

        //mock repository
        List<UsuarioModel> mockUsuarios = Arrays.asList(usuarioModel1, usuarioModel2);
        when(usuarioRepository.findAll()).thenReturn(mockUsuarios);


        //mock modelmapper
        when(modelMapper.map(usuarioModel1, UsuarioDto.class)).thenReturn(newExampleUsuarioDto(1));
        when(modelMapper.map(usuarioModel2, UsuarioDto.class)).thenReturn(newExampleUsuarioDto(2));


        //verificação

        List<UsuarioDto> result = usuarioService.findAll();
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("teste1", result.get(0).getLogin());
        assertEquals("1234", result.get(0).getSenha());
        assertEquals(true, result.get(0).getCadastroPaciente());
        assertEquals(true, result.get(0).getCadastroFuncionario());
        assertEquals(true, result.get(0).getCadastroUsuario());
        assertEquals(true, result.get(0).getCadastroEspecialidade());
        assertEquals(true, result.get(0).getCadastroConvenio());
        assertEquals(true, result.get(0).getCadastroMedico());
        assertEquals(true, result.get(0).getAgendamentoConsulta());
        assertEquals(true, result.get(0).getCancelamentoConsulta());
        assertEquals(true, result.get(0).getModuloAdministrativo());
        assertEquals(true, result.get(0).getModuloAgendamento());
        assertEquals(true, result.get(0).getModuloAtendimento());

        assertEquals(2L, result.get(1).getId());
        assertEquals("teste2", result.get(1).getLogin());
        assertEquals("4321", result.get(1).getSenha());
        assertEquals(false, result.get(1).getCadastroPaciente());
        assertEquals(false, result.get(1).getCadastroFuncionario());
        assertEquals(false, result.get(1).getCadastroUsuario());
        assertEquals(false, result.get(1).getCadastroEspecialidade());
        assertEquals(false, result.get(1).getCadastroConvenio());
        assertEquals(false, result.get(1).getCadastroMedico());
        assertEquals(false, result.get(1).getAgendamentoConsulta());
        assertEquals(false, result.get(1).getCancelamentoConsulta());
        assertEquals(false, result.get(1).getModuloAdministrativo());
        assertEquals(false, result.get(1).getModuloAgendamento());
        assertEquals(false, result.get(1).getModuloAtendimento());
    }

    @Test
    public void shouldUpdateFoundUsuario(){
        //variavel
        Long id = 1L;
        UsuarioDto usuarioDto = newExampleUsuarioDto(1);
        UsuarioModel usuarioModel = newExampleUsuarioModel(2);

        //mock

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioModel));
        when(usuarioRepository.save(any(UsuarioModel.class))).thenReturn(usuarioModel);

        when(modelMapper.map(usuarioModel, UsuarioDto.class)).thenReturn(newExampleUsuarioDto(1));

        UsuarioDto result = usuarioService.update(usuarioDto, id);

        //result
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("teste1", result.getLogin());
        assertEquals("1234", result.getSenha());
        assertEquals(true, result.getCadastroPaciente());
        assertEquals(true, result.getCadastroFuncionario());
        assertEquals(true, result.getCadastroUsuario());
        assertEquals(true, result.getCadastroEspecialidade());
        assertEquals(true, result.getCadastroConvenio());
        assertEquals(true, result.getCadastroMedico());
        assertEquals(true, result.getAgendamentoConsulta());
        assertEquals(true, result.getCancelamentoConsulta());
        assertEquals(true, result.getModuloAdministrativo());
        assertEquals(true, result.getModuloAgendamento());
        assertEquals(true, result.getModuloAtendimento());

    }

    @Test
    public void updateShouldReturnNull(){
        //variaveis
        Long id = 1L;
        UsuarioModel enviarParaAtualizar = new UsuarioModel();
        enviarParaAtualizar.setId(1L);
        //mock
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        UsuarioDto result = usuarioService.findById(id);

        assertNull(result);

        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(0)).save(enviarParaAtualizar);

    }

    @Test
    public void shouldSave(){
        UsuarioCreateRequest usuarioCreateRequest = newExampleCreateRequest(1);

        UsuarioModel savedUsuario = newExampleUsuarioModel(1);

        when(usuarioRepository.save(any(UsuarioModel.class))).thenReturn(savedUsuario);
        when(modelMapper.map(usuarioCreateRequest, UsuarioModel.class)).thenReturn(newExampleUsuarioModel(1));
        when(modelMapper.map(savedUsuario, UsuarioDto.class)).thenReturn(newExampleUsuarioDto(1));

        UsuarioDto result = usuarioService.save(usuarioCreateRequest);

        assertEquals(1L, result.getId());
        assertEquals("teste1", result.getLogin());
        assertEquals("1234", result.getSenha());
        assertEquals(true, result.getCadastroPaciente());
        assertEquals(true, result.getCadastroFuncionario());
        assertEquals(true, result.getCadastroUsuario());
        assertEquals(true, result.getCadastroEspecialidade());
        assertEquals(true, result.getCadastroConvenio());
        assertEquals(true, result.getCadastroMedico());
        assertEquals(true, result.getAgendamentoConsulta());
        assertEquals(true, result.getCancelamentoConsulta());
        assertEquals(true, result.getModuloAdministrativo());
        assertEquals(true, result.getModuloAgendamento());
        assertEquals(true, result.getModuloAtendimento());

    }

    @Test
    public void shouldFindById(){
        UsuarioModel usuarioModel = newExampleUsuarioModel(1);

        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuarioModel));
        when(modelMapper.map(usuarioModel, UsuarioDto.class)).thenReturn(newExampleUsuarioDto(1));

        UsuarioDto result = usuarioService.findById(1L);

        assertEquals(1L, result.getId());
        assertEquals("teste1", result.getLogin());
        assertEquals("1234", result.getSenha());
        assertEquals(true, result.getCadastroPaciente());
        assertEquals(true, result.getCadastroFuncionario());
        assertEquals(true, result.getCadastroUsuario());
        assertEquals(true, result.getCadastroEspecialidade());
        assertEquals(true, result.getCadastroConvenio());
        assertEquals(true, result.getCadastroMedico());
        assertEquals(true, result.getAgendamentoConsulta());
        assertEquals(true, result.getCancelamentoConsulta());
        assertEquals(true, result.getModuloAdministrativo());
        assertEquals(true, result.getModuloAgendamento());
        assertEquals(true, result.getModuloAtendimento());
    }
    @Test
    public void findByIdShouldReturnNull(){
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        UsuarioDto result = usuarioService.findById(anyLong());

        assertNull(result);
        verify(usuarioRepository, times(1)).findById(anyLong());
    }



    private UsuarioDto newExampleUsuarioDto(int id){
        UsuarioDto usuario;
        switch (id){
            case 1:
                usuario = new UsuarioDto();
                usuario.setId(1L);
                usuario.setLogin("teste1");
                usuario.setSenha("1234");
                usuario.setCadastroPaciente(true);
                usuario.setCadastroFuncionario(true);
                usuario.setCadastroUsuario(true);
                usuario.setCadastroEspecialidade(true);
                usuario.setCadastroConvenio(true);
                usuario.setCadastroMedico(true);
                usuario.setAgendamentoConsulta(true);
                usuario.setCancelamentoConsulta(true);
                usuario.setModuloAdministrativo(true);
                usuario.setModuloAgendamento(true);
                usuario.setModuloAtendimento(true);
                return usuario;
            case 2:
                usuario = new UsuarioDto();
                usuario.setId(2L);
                usuario.setLogin("teste2");
                usuario.setSenha("4321");
                usuario.setCadastroPaciente(false);
                usuario.setCadastroFuncionario(false);
                usuario.setCadastroUsuario(false);
                usuario.setCadastroEspecialidade(false);
                usuario.setCadastroConvenio(false);
                usuario.setCadastroMedico(false);
                usuario.setAgendamentoConsulta(false);
                usuario.setCancelamentoConsulta(false);
                usuario.setModuloAdministrativo(false);
                usuario.setModuloAgendamento(false);
                usuario.setModuloAtendimento(false);
                return usuario;
            default:
                return new UsuarioDto();
        }
    }

    private UsuarioModel newExampleUsuarioModel(int id){
        UsuarioModel usuario;
        switch (id){
            case 1:
                usuario = new UsuarioModel();
                usuario.setId(1L);
                usuario.setLogin("teste1");
                usuario.setSenha("1234");
                usuario.setCadastroPaciente(true);
                usuario.setCadastroFuncionario(true);
                usuario.setCadastroUsuario(true);
                usuario.setCadastroEspecialidade(true);
                usuario.setCadastroConvenio(true);
                usuario.setCadastroMedico(true);
                usuario.setAgendamentoConsulta(true);
                usuario.setCancelamentoConsulta(true);
                usuario.setModuloAdministrativo(true);
                usuario.setModuloAgendamento(true);
                usuario.setModuloAtendimento(true);
                return usuario;
            case 2:
                usuario = new UsuarioModel();
                usuario.setId(2L);
                usuario.setLogin("teste2");
                usuario.setSenha("4321");
                usuario.setCadastroPaciente(false);
                usuario.setCadastroFuncionario(false);
                usuario.setCadastroUsuario(false);
                usuario.setCadastroEspecialidade(false);
                usuario.setCadastroConvenio(false);
                usuario.setCadastroMedico(false);
                usuario.setAgendamentoConsulta(false);
                usuario.setCancelamentoConsulta(false);
                usuario.setModuloAdministrativo(false);
                usuario.setModuloAgendamento(false);
                usuario.setModuloAtendimento(false);
                return usuario;
            default:
                return new UsuarioModel();
        }
    }
    private UsuarioCreateRequest newExampleCreateRequest(int id){
        UsuarioCreateRequest usuario;
        switch (id){
            case 1:
                usuario = new UsuarioCreateRequest();
                usuario.setLogin("teste1");
                usuario.setSenha("1234");
                usuario.setCadastroPaciente(true);
                usuario.setCadastroFuncionario(true);
                usuario.setCadastroUsuario(true);
                usuario.setCadastroEspecialidade(true);
                usuario.setCadastroConvenio(true);
                usuario.setCadastroMedico(true);
                usuario.setAgendamentoConsulta(true);
                usuario.setCancelamentoConsulta(true);
                usuario.setModuloAdministrativo(true);
                usuario.setModuloAgendamento(true);
                usuario.setModuloAtendimento(true);
                return usuario;
            case 2:
                usuario = new UsuarioCreateRequest();
                usuario.setLogin("teste2");
                usuario.setSenha("4321");
                usuario.setCadastroPaciente(false);
                usuario.setCadastroFuncionario(false);
                usuario.setCadastroUsuario(false);
                usuario.setCadastroEspecialidade(false);
                usuario.setCadastroConvenio(false);
                usuario.setCadastroMedico(false);
                usuario.setAgendamentoConsulta(false);
                usuario.setCancelamentoConsulta(false);
                usuario.setModuloAdministrativo(false);
                usuario.setModuloAgendamento(false);
                usuario.setModuloAtendimento(false);
                return usuario;
            default:
                return new UsuarioCreateRequest();
        }
    }

}
