package br.edu.imepac.services;


import br.edu.imepac.dtos.FuncionarioCreateRequest;
import br.edu.imepac.dtos.FuncionarioDto;
import br.edu.imepac.models.FuncionarioModel;
import br.edu.imepac.repositories.FuncionarioRepository;

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

class FuncionarioServiceTest {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Test
    void shouldDelete() {

        doNothing().when(funcionarioRepository).deleteById(1L);

        funcionarioService.delete(1L);

        verify(funcionarioRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldFindAll() {

        FuncionarioModel funcionarioModel1 = new FuncionarioModel();
        funcionarioModel1.setId(1L);
        funcionarioModel1.setNome_Funcionario("Henrique");
        funcionarioModel1.setNumero_Rg("12845632");
        funcionarioModel1.setOrgao_Emissor("MG");
        funcionarioModel1.setNumero_Cpf("324234242");
        funcionarioModel1.setEndereco("Rua Sao simao");
        funcionarioModel1.setNumero("24312431");
        funcionarioModel1.setComplemento("Casa");
        funcionarioModel1.setBairro("Bosque");
        funcionarioModel1.setCidade("Araguari");
        funcionarioModel1.setEstado("Minas Gerais");
        funcionarioModel1.setTelefone("23423424");
        funcionarioModel1.setCelular("234234234");
        funcionarioModel1.setNumero_Ctps("234234234");
        funcionarioModel1.setNumero_Pis("23423424");
        funcionarioModel1.setData("27082000");

        FuncionarioModel funcionarioModel2 = new FuncionarioModel();
        funcionarioModel2.setId(2L);
        funcionarioModel2.setNome_Funcionario("Matheus");
        funcionarioModel2.setNumero_Rg("5345345");
        funcionarioModel2.setOrgao_Emissor("SP");
        funcionarioModel2.setNumero_Cpf("6456456");
        funcionarioModel2.setEndereco("Rua Borges");
        funcionarioModel2.setNumero("4534534");
        funcionarioModel2.setComplemento("Casa");
        funcionarioModel2.setBairro("Santa Helena");
        funcionarioModel2.setCidade("São Paulo");
        funcionarioModel2.setEstado("São Paulo");
        funcionarioModel2.setTelefone("23423145");
        funcionarioModel2.setCelular("89784564");
        funcionarioModel2.setNumero_Ctps("4353452");
        funcionarioModel2.setNumero_Pis("453453672");
        funcionarioModel2.setData("07051999");


        List<FuncionarioModel> mockFuncionarios = Arrays.asList(funcionarioModel1, funcionarioModel2);

        when(funcionarioRepository.findAll()).thenReturn(mockFuncionarios);

        List<FuncionarioDto> result = funcionarioService.findAll();


        assertEquals(2, result.size());
        assertEquals("Henrique", result.get(0).getNome_Funcionario());
        assertEquals("12845632", result.get(0).getNumero_Rg()); // Valor esperado
        assertEquals("MG", result.get(0).getOrgao_Emissor());
        assertEquals("324234242", result.get(0).getNumero_Cpf());
        assertEquals("Rua Sao simao", result.get(0).getEndereco());
        assertEquals("24312431", result.get(0).getNumero());
        assertEquals("Casa", result.get(0).getComplemento());
        assertEquals("Bosque", result.get(0).getBairro());
        assertEquals("Araguari", result.get(0).getCidade());
        assertEquals("Minas Gerais", result.get(0).getEstado());
        assertEquals("23423424", result.get(0).getTelefone());
        assertEquals("234234234", result.get(0).getCelular());
        assertEquals("234234234", result.get(0).getNumero_Ctps());
        assertEquals("23423424", result.get(0).getNumero_Pis());
        assertEquals("27082000", result.get(0).getData());

        assertEquals("Matheus", result.get(1).getNome_Funcionario());
        assertEquals("5345345", result.get(1).getNumero_Rg());
        assertEquals("SP", result.get(1).getOrgao_Emissor());
        assertEquals("6456456", result.get(1).getNumero_Cpf());
        assertEquals("Rua Borges", result.get(1).getEndereco());
        assertEquals("4534534", result.get(1).getNumero());
        assertEquals("Casa", result.get(1).getComplemento());
        assertEquals("Santa Helena", result.get(1).getBairro());
        assertEquals("São Paulo", result.get(1).getCidade());
        assertEquals("São Paulo", result.get(1).getEstado());
        assertEquals("23423145", result.get(1).getTelefone());
        assertEquals("89784564", result.get(1).getCelular());
        assertEquals("4353452", result.get(1).getNumero_Ctps());
        assertEquals("453453672", result.get(1).getNumero_Pis());
        assertEquals("07051999", result.get(1).getData());

    }

    @Test
    void shouldUpdateFoundFuncionario() {

        Long id = 1L;

        FuncionarioDto funcionarioDto = new FuncionarioDto();
        funcionarioDto.setId(id);
        funcionarioDto.setNome_Funcionario("Henrique");
        funcionarioDto.setNumero_Rg("45245345");
        funcionarioDto.setOrgao_Emissor("MG");
        funcionarioDto.setNumero_Cpf("324234242");
        funcionarioDto.setEndereco("Rua Sao simao");
        funcionarioDto.setNumero("24312431");
        funcionarioDto.setComplemento("Casa");
        funcionarioDto.setBairro("Bosque");
        funcionarioDto.setCidade("Araguari");
        funcionarioDto.setEstado("Minas Gerais");
        funcionarioDto.setTelefone("23423424");
        funcionarioDto.setCelular("234234234");
        funcionarioDto.setNumero_Ctps("234234234");
        funcionarioDto.setNumero_Pis("23423424");
        funcionarioDto.setData("27082000");

        FuncionarioModel funcionarioModel = new FuncionarioModel();
        funcionarioModel.setId(id);
        funcionarioModel.setNome_Funcionario("Matheus");
        funcionarioModel.setNumero_Rg("5345345");
        funcionarioModel.setOrgao_Emissor("SP");
        funcionarioModel.setNumero_Cpf("6456456");
        funcionarioModel.setEndereco("Rua Borges");
        funcionarioModel.setNumero("4534534");
        funcionarioModel.setComplemento("Casa");
        funcionarioModel.setBairro("Santa Helena");
        funcionarioModel.setCidade("São Paulo");
        funcionarioModel.setEstado("São Paulo");
        funcionarioModel.setTelefone("23423145");
        funcionarioModel.setCelular("89784564");
        funcionarioModel.setNumero_Ctps("4353452");
        funcionarioModel.setNumero_Pis("453453672");
        funcionarioModel.setData("07051999");

        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(funcionarioModel));
        when(funcionarioRepository.save(any(FuncionarioModel.class))).thenReturn(funcionarioModel);

        FuncionarioDto resultado = funcionarioService.update(id, funcionarioDto);

        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("Henrique", resultado.getNome_Funcionario());
        assertEquals("45245345", resultado.getNumero_Rg());
        assertEquals("MG", resultado.getOrgao_Emissor());
        assertEquals("324234242", resultado.getNumero_Cpf());
        assertEquals("Rua Sao simao", resultado.getEndereco());
        assertEquals("24312431", resultado.getNumero());
        assertEquals("Casa", resultado.getComplemento());
        assertEquals("Bosque", resultado.getBairro());
        assertEquals("Araguari", resultado.getCidade());
        assertEquals("Minas Gerais", resultado.getEstado());
        assertEquals("23423424", resultado.getTelefone());
        assertEquals("234234234", resultado.getCelular());
        assertEquals("234234234", resultado.getNumero_Ctps());
        assertEquals("23423424", resultado.getNumero_Pis());
        assertEquals("27082000", resultado.getData());
    }

    @Test
    void updateShouldReturnNull() {
        Long id = 1L;
        FuncionarioModel enviarParaAtualizar = new FuncionarioModel();
        enviarParaAtualizar.setId(1L);

        when(funcionarioRepository.findById(id)).thenReturn(Optional.empty());

        FuncionarioDto response = funcionarioService.findById(id);

        assertNull(response);

        verify(funcionarioRepository, times(1)).findById(id);
        verify(funcionarioRepository, times(0)).save(enviarParaAtualizar);
    }

    @Test
    void shouldSave() {

        FuncionarioCreateRequest funcionarioCreateRequest = new FuncionarioCreateRequest();
        funcionarioCreateRequest.setNome_Funcionario("Guilherme");
        funcionarioCreateRequest.setNumero_Rg("5645754");
        funcionarioCreateRequest.setOrgao_Emissor("RJ");
        funcionarioCreateRequest.setNumero_Cpf("463456932");
        funcionarioCreateRequest.setEndereco("Rua Alvaro Santos");
        funcionarioCreateRequest.setNumero("78567345");
        funcionarioCreateRequest.setComplemento("Condominio");
        funcionarioCreateRequest.setBairro("Alvaro");
        funcionarioCreateRequest.setCidade("Rio de Janeiro");
        funcionarioCreateRequest.setEstado("Rio de Janeiro");
        funcionarioCreateRequest.setTelefone("46354647");
        funcionarioCreateRequest.setCelular("235234235");
        funcionarioCreateRequest.setNumero_Ctps("457457457");
        funcionarioCreateRequest.setNumero_Pis("457457457");
        funcionarioCreateRequest.setData("10082000");

        FuncionarioModel savedFuncionario = new FuncionarioModel();
        savedFuncionario.setId(1L);
        savedFuncionario.setNome_Funcionario("Guilherme");
        savedFuncionario.setNumero_Rg("5645754");
        savedFuncionario.setOrgao_Emissor("RJ");
        savedFuncionario.setNumero_Cpf("463456932");
        savedFuncionario.setEndereco("Rua Alvaro Santos");
        savedFuncionario.setNumero("78567345");
        savedFuncionario.setComplemento("Condominio");
        savedFuncionario.setBairro("Alvaro");
        savedFuncionario.setCidade("Rio de Janeiro");
        savedFuncionario.setEstado("Rio de Janeiro");
        savedFuncionario.setTelefone("46354647");
        savedFuncionario.setCelular("235234235");
        savedFuncionario.setNumero_Ctps("457457457");
        savedFuncionario.setNumero_Pis("457457457");
        savedFuncionario.setData("10082000");

        when(funcionarioRepository.save(any(FuncionarioModel.class))).thenReturn(savedFuncionario);

        FuncionarioDto resultado = funcionarioService.save(funcionarioCreateRequest);

        assertEquals(1L, resultado.getId());
        assertEquals("Guilherme", resultado.getNome_Funcionario());
        assertEquals("5645754", resultado.getNumero_Rg());
        assertEquals("RJ", resultado.getOrgao_Emissor());
        assertEquals("463456932", resultado.getNumero_Cpf());
        assertEquals("Rua Alvaro Santos", resultado.getEndereco());
        assertEquals("78567345", resultado.getNumero());
        assertEquals("Condominio", resultado.getComplemento());
        assertEquals("Alvaro", resultado.getBairro());
        assertEquals("Rio de Janeiro", resultado.getCidade());
        assertEquals("Rio de Janeiro", resultado.getEstado());
        assertEquals("46354647", resultado.getTelefone());
        assertEquals("235234235", resultado.getCelular());
        assertEquals("457457457", resultado.getNumero_Ctps());
        assertEquals("457457457", resultado.getNumero_Pis());
        assertEquals("10082000", resultado.getData());
    }

        @Test
        void shouldFindById() {
            FuncionarioModel funcionarioModel = new FuncionarioModel();
            funcionarioModel.setId(1L);
            funcionarioModel.setNome_Funcionario("Carlos");
            funcionarioModel.setNumero_Rg("98765432");
            funcionarioModel.setOrgao_Emissor("SP");
            funcionarioModel.setNumero_Cpf("12345678901");
            funcionarioModel.setEndereco("Rua das Flores");
            funcionarioModel.setNumero("123");
            funcionarioModel.setComplemento("Apto 45");
            funcionarioModel.setBairro("Centro");
            funcionarioModel.setCidade("São Paulo");
            funcionarioModel.setEstado("São Paulo");
            funcionarioModel.setTelefone("1122334455");
            funcionarioModel.setCelular("9988776655");
            funcionarioModel.setNumero_Ctps("11223344");
            funcionarioModel.setNumero_Pis("55667788");
            funcionarioModel.setData("01011990");

            when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(funcionarioModel));

            FuncionarioDto resultado = funcionarioService.findById(1L);

            assertEquals("Carlos", resultado.getNome_Funcionario());
            assertEquals("98765432", resultado.getNumero_Rg());
            assertEquals("SP", resultado.getOrgao_Emissor());
            assertEquals("12345678901", resultado.getNumero_Cpf());
            assertEquals("Rua das Flores", resultado.getEndereco());
            assertEquals("123", resultado.getNumero());
            assertEquals("Apto 45", resultado.getComplemento());
            assertEquals("Centro", resultado.getBairro());
            assertEquals("São Paulo", resultado.getCidade());
            assertEquals("São Paulo", resultado.getEstado());
            assertEquals("1122334455", resultado.getTelefone());
            assertEquals("9988776655", resultado.getCelular());
            assertEquals("11223344", resultado.getNumero_Ctps());
            assertEquals("55667788", resultado.getNumero_Pis());
            assertEquals("01011990", resultado.getData());
        }

    @Test
    void findByIdShouldReturnNull(){
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        FuncionarioDto resposta = funcionarioService.findById(anyLong());

        assertNull(resposta);
        verify(funcionarioRepository, times(1)).findById(anyLong());
    }
    }





