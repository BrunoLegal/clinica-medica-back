package br.edu.imepac.services;

import br.edu.imepac.dtos.PacienteCreateRequest;
import br.edu.imepac.dtos.PacienteDto;
import br.edu.imepac.models.ConvenioModel;
import br.edu.imepac.models.PacienteModel;
import br.edu.imepac.repositories.PacienteRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {

    @InjectMocks
    PacienteService pacienteService;

    @Mock
    PacienteRepository pacienteRepository;

    @Test
    public void shouldSave(){
        //parametros
        ConvenioModel convenioModel = new ConvenioModel(2L,"conveniente","12351245", "40028922");
        PacienteCreateRequest parametro = new PacienteCreateRequest();
        parametro.setNome("a");
        parametro.setNumero_RG("b");
        parametro.setOrgao_Emissor("c");
        parametro.setNumero_CPF("d");
        parametro.setNumero("e");
        parametro.setComplemento("f");
        parametro.setBairro("g");
        parametro.setCidade("h");
        parametro.setEstado("i");
        parametro.setTelefone("j");
        parametro.setCelular("k");
        parametro.setData_de_nascimento("l");
        parametro.setSexo("m");
        parametro.setTem_convenio("sim");
        parametro.setConvenio(convenioModel);
        parametro.setSenha_acesso("n");

        //Mock repository
        PacienteModel respostaRepository = new PacienteModel();
        respostaRepository.setId(1L);
        respostaRepository.setNome("a");
        respostaRepository.setNumero_RG("b");
        respostaRepository.setOrgao_Emissor("c");
        respostaRepository.setNumero_CPF("d");
        respostaRepository.setNumero("e");
        respostaRepository.setComplemento("f");
        respostaRepository.setBairro("g");
        respostaRepository.setCidade("h");
        respostaRepository.setEstado("i");
        respostaRepository.setTelefone("j");
        respostaRepository.setCelular("k");
        respostaRepository.setData_de_nascimento("l");
        respostaRepository.setSexo("m");
        respostaRepository.setTem_convenio("sim");
        respostaRepository.setConvenio(convenioModel);
        respostaRepository.setSenha_acesso("n");

        when(pacienteRepository.save(any(PacienteModel.class))).thenReturn(respostaRepository);

        //teste
        PacienteDto resultado = pacienteService.save(parametro);

        //validação
        assertEquals(parametro.getNome(), resultado.getNome());
        assertEquals(parametro.getNumero_RG(), resultado.getNumero_RG());
        assertEquals(parametro.getOrgao_Emissor(), resultado.getOrgao_Emissor());
        assertEquals(parametro.getNumero_CPF(), resultado.getNumero_CPF());
        assertEquals(parametro.getEndereco(), resultado.getEndereco());
        assertEquals(parametro.getNumero(), resultado.getNumero());
        assertEquals(parametro.getComplemento(), resultado.getComplemento());
        assertEquals(parametro.getNome(), resultado.getNome());
        assertEquals(parametro.getBairro(), resultado.getBairro());
        assertEquals(parametro.getCidade(), resultado.getCidade());
        assertEquals(parametro.getEstado(), resultado.getEstado());
        assertEquals(parametro.getTelefone(), resultado.getTelefone());
        assertEquals(parametro.getCelular(), resultado.getCelular());
        assertEquals(parametro.getData_de_nascimento(), resultado.getData_de_nascimento());
        assertEquals(parametro.getSexo(), resultado.getSexo());
        assertEquals(parametro.getTem_convenio(), resultado.getTem_convenio());
        assertEquals(parametro.getConvenio().getId(), resultado.getConvenio().getId());
        assertEquals(parametro.getConvenio().getTelefone(), resultado.getConvenio().getTelefone());
        assertEquals(parametro.getConvenio().getCnpj(), resultado.getConvenio().getCnpj());
        assertEquals(parametro.getConvenio().getEmpresa(), resultado.getConvenio().getEmpresa());
        assertEquals(parametro.getSenha_acesso(), resultado.getSenha_acesso());
    }

    @Test
    public void shouldFindById(){
        //parametro
        Long id = 12L;

        //mock repository
        ConvenioModel convenioModel = new ConvenioModel(2L,"conveniente","12351245", "40028922");
        PacienteModel respostaRepository = new PacienteModel();
        respostaRepository.setId(1L);
        respostaRepository.setNome("a");
        respostaRepository.setNumero_RG("b");
        respostaRepository.setOrgao_Emissor("c");
        respostaRepository.setNumero_CPF("d");
        respostaRepository.setNumero("e");
        respostaRepository.setComplemento("f");
        respostaRepository.setBairro("g");
        respostaRepository.setCidade("h");
        respostaRepository.setEstado("i");
        respostaRepository.setTelefone("j");
        respostaRepository.setCelular("k");
        respostaRepository.setData_de_nascimento("l");
        respostaRepository.setSexo("m");
        respostaRepository.setTem_convenio("sim");
        respostaRepository.setConvenio(convenioModel);
        respostaRepository.setSenha_acesso("n");

        //esse aqui é só para fazer ficar mais fácil e padronizar as respostas, ignore
        PacienteCreateRequest parametro = new PacienteCreateRequest();
        parametro.setNome("a");
        parametro.setNumero_RG("b");
        parametro.setOrgao_Emissor("c");
        parametro.setNumero_CPF("d");
        parametro.setNumero("e");
        parametro.setComplemento("f");
        parametro.setBairro("g");
        parametro.setCidade("h");
        parametro.setEstado("i");
        parametro.setTelefone("j");
        parametro.setCelular("k");
        parametro.setData_de_nascimento("l");
        parametro.setSexo("m");
        parametro.setTem_convenio("sim");
        parametro.setConvenio(convenioModel);
        parametro.setSenha_acesso("n");


        when(pacienteRepository.findById(id)).thenReturn(Optional.of(respostaRepository));

        //teste
        PacienteDto resultado = pacienteService.findById(id);

        //validação
        assertEquals(parametro.getNome(), resultado.getNome());
        assertEquals(parametro.getNumero_RG(), resultado.getNumero_RG());
        assertEquals(parametro.getOrgao_Emissor(), resultado.getOrgao_Emissor());
        assertEquals(parametro.getNumero_CPF(), resultado.getNumero_CPF());
        assertEquals(parametro.getEndereco(), resultado.getEndereco());
        assertEquals(parametro.getNumero(), resultado.getNumero());
        assertEquals(parametro.getComplemento(), resultado.getComplemento());
        assertEquals(parametro.getNome(), resultado.getNome());
        assertEquals(parametro.getBairro(), resultado.getBairro());
        assertEquals(parametro.getCidade(), resultado.getCidade());
        assertEquals(parametro.getEstado(), resultado.getEstado());
        assertEquals(parametro.getTelefone(), resultado.getTelefone());
        assertEquals(parametro.getCelular(), resultado.getCelular());
        assertEquals(parametro.getData_de_nascimento(), resultado.getData_de_nascimento());
        assertEquals(parametro.getSexo(), resultado.getSexo());
        assertEquals(parametro.getTem_convenio(), resultado.getTem_convenio());
        assertEquals(parametro.getConvenio().getId(), resultado.getConvenio().getId());
        assertEquals(parametro.getConvenio().getTelefone(), resultado.getConvenio().getTelefone());
        assertEquals(parametro.getConvenio().getCnpj(), resultado.getConvenio().getCnpj());
        assertEquals(parametro.getConvenio().getEmpresa(), resultado.getConvenio().getEmpresa());
        assertEquals(parametro.getSenha_acesso(), resultado.getSenha_acesso());
    }

    @Test
    public void findByIdShouldReturnNull(){
        //parametro
        var id = 1L;

        //mock repository
        when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

        //test
        PacienteDto resultado = pacienteService.findById(id);

        //validação
        assertNull(resultado);
        verify(pacienteRepository, times(1)).findById(id);
    }

    @Test
    public void shouldDelete(){
        //parametro
        var id = 1L;

        //mock repository
        doNothing().when(pacienteRepository).deleteById(id);

        //teste
        pacienteService.delete(id);

        //validação
        verify(pacienteRepository, times(1)).deleteById(id);
    }

    @Test
    public void shouldFindAll(){
        //parametro
        //mock repository
        ConvenioModel convenio1 = new ConvenioModel(2L,"conveniente","12351245", "40028922");
        PacienteModel paciente1 = new PacienteModel();
        paciente1.setId(1L);
        paciente1.setNome("a");
        paciente1.setNumero_RG("b");
        paciente1.setOrgao_Emissor("c");
        paciente1.setNumero_CPF("d");
        paciente1.setNumero("e");
        paciente1.setComplemento("f");
        paciente1.setBairro("g");
        paciente1.setCidade("h");
        paciente1.setEstado("i");
        paciente1.setTelefone("j");
        paciente1.setCelular("k");
        paciente1.setData_de_nascimento("l");
        paciente1.setSexo("m");
        paciente1.setTem_convenio("sim");
        paciente1.setConvenio(convenio1);
        paciente1.setSenha_acesso("n");

        ConvenioModel convenio2 = new ConvenioModel(21L,"inconveniente","54321", "08002101210");
        PacienteModel paciente2 = new PacienteModel();
        paciente2.setId(43L);
        paciente2.setNome("n");
        paciente2.setNumero_RG("m");
        paciente2.setOrgao_Emissor("l");
        paciente2.setNumero_CPF("k");
        paciente2.setNumero("j");
        paciente2.setComplemento("i");
        paciente2.setBairro("h");
        paciente2.setCidade("g");
        paciente2.setEstado("f");
        paciente2.setTelefone("e");
        paciente2.setCelular("d");
        paciente2.setData_de_nascimento("c");
        paciente2.setSexo("b");
        paciente2.setTem_convenio("sim");
        paciente2.setConvenio(convenio2);
        paciente2.setSenha_acesso("a");

        ConvenioModel convenio3 = new ConvenioModel(62L,"konbini","0192834", "5785874920");
        PacienteModel paciente3 = new PacienteModel();
        paciente3.setId(156L);
        paciente3.setNome("1");
        paciente3.setNumero_RG("b1");
        paciente3.setOrgao_Emissor("c2");
        paciente3.setNumero_CPF("d4");
        paciente3.setNumero("e4");
        paciente3.setComplemento("f5");
        paciente3.setBairro("g2");
        paciente3.setCidade("h4");
        paciente3.setEstado("i7");
        paciente3.setTelefone("j8");
        paciente3.setCelular("k9");
        paciente3.setData_de_nascimento("l7");
        paciente3.setSexo("m6");
        paciente3.setTem_convenio("não");
        paciente3.setConvenio(convenio3);
        paciente3.setSenha_acesso("nas");

        List<PacienteModel> respostaRepository = Arrays.asList(paciente1,paciente2,paciente3);

        when(pacienteRepository.findAll()).thenReturn(respostaRepository);

        //test
        List<PacienteDto> resultado = pacienteService.findAll();

        //validação
        assertEquals(3,resultado.size());
        assertEquals(paciente1.getNome(), resultado.get(0).getNome());
        assertEquals(paciente1.getNumero_RG(), resultado.get(0).getNumero_RG());
        assertEquals(paciente1.getOrgao_Emissor(), resultado.get(0).getOrgao_Emissor());
        assertEquals(paciente1.getNumero_CPF(), resultado.get(0).getNumero_CPF());
        assertEquals(paciente1.getEndereco(), resultado.get(0).getEndereco());
        assertEquals(paciente1.getNumero(), resultado.get(0).getNumero());
        assertEquals(paciente1.getComplemento(), resultado.get(0).getComplemento());
        assertEquals(paciente1.getNome(), resultado.get(0).getNome());
        assertEquals(paciente1.getBairro(), resultado.get(0).getBairro());
        assertEquals(paciente1.getCidade(), resultado.get(0).getCidade());
        assertEquals(paciente1.getEstado(), resultado.get(0).getEstado());
        assertEquals(paciente1.getTelefone(), resultado.get(0).getTelefone());
        assertEquals(paciente1.getCelular(), resultado.get(0).getCelular());
        assertEquals(paciente1.getData_de_nascimento(), resultado.get(0).getData_de_nascimento());
        assertEquals(paciente1.getSexo(), resultado.get(0).getSexo());
        assertEquals(paciente1.getTem_convenio(), resultado.get(0).getTem_convenio());
        assertEquals(paciente1.getConvenio().getId(), resultado.get(0).getConvenio().getId());
        assertEquals(paciente1.getConvenio().getTelefone(), resultado.get(0).getConvenio().getTelefone());
        assertEquals(paciente1.getConvenio().getCnpj(), resultado.get(0).getConvenio().getCnpj());
        assertEquals(paciente1.getConvenio().getEmpresa(), resultado.get(0).getConvenio().getEmpresa());
        assertEquals(paciente1.getSenha_acesso(), resultado.get(0).getSenha_acesso());

        assertEquals(paciente2.getNome(), resultado.get(1).getNome());
        assertEquals(paciente2.getNumero_RG(), resultado.get(1).getNumero_RG());
        assertEquals(paciente2.getOrgao_Emissor(), resultado.get(1).getOrgao_Emissor());
        assertEquals(paciente2.getNumero_CPF(), resultado.get(1).getNumero_CPF());
        assertEquals(paciente2.getEndereco(), resultado.get(1).getEndereco());
        assertEquals(paciente2.getNumero(), resultado.get(1).getNumero());
        assertEquals(paciente2.getComplemento(), resultado.get(1).getComplemento());
        assertEquals(paciente2.getNome(), resultado.get(1).getNome());
        assertEquals(paciente2.getBairro(), resultado.get(1).getBairro());
        assertEquals(paciente2.getCidade(), resultado.get(1).getCidade());
        assertEquals(paciente2.getEstado(), resultado.get(1).getEstado());
        assertEquals(paciente2.getTelefone(), resultado.get(1).getTelefone());
        assertEquals(paciente2.getCelular(), resultado.get(1).getCelular());
        assertEquals(paciente2.getData_de_nascimento(), resultado.get(1).getData_de_nascimento());
        assertEquals(paciente2.getSexo(), resultado.get(1).getSexo());
        assertEquals(paciente2.getTem_convenio(), resultado.get(1).getTem_convenio());
        assertEquals(paciente2.getConvenio().getId(), resultado.get(1).getConvenio().getId());
        assertEquals(paciente2.getConvenio().getTelefone(), resultado.get(1).getConvenio().getTelefone());
        assertEquals(paciente2.getConvenio().getCnpj(), resultado.get(1).getConvenio().getCnpj());
        assertEquals(paciente2.getConvenio().getEmpresa(), resultado.get(1).getConvenio().getEmpresa());
        assertEquals(paciente2.getSenha_acesso(), resultado.get(1).getSenha_acesso());

        assertEquals(paciente3.getNome(), resultado.get(2).getNome());
        assertEquals(paciente3.getNumero_RG(), resultado.get(2).getNumero_RG());
        assertEquals(paciente3.getOrgao_Emissor(), resultado.get(2).getOrgao_Emissor());
        assertEquals(paciente3.getNumero_CPF(), resultado.get(2).getNumero_CPF());
        assertEquals(paciente3.getEndereco(), resultado.get(2).getEndereco());
        assertEquals(paciente3.getNumero(), resultado.get(2).getNumero());
        assertEquals(paciente3.getComplemento(), resultado.get(2).getComplemento());
        assertEquals(paciente3.getNome(), resultado.get(2).getNome());
        assertEquals(paciente3.getBairro(), resultado.get(2).getBairro());
        assertEquals(paciente3.getCidade(), resultado.get(2).getCidade());
        assertEquals(paciente3.getEstado(), resultado.get(2).getEstado());
        assertEquals(paciente3.getTelefone(), resultado.get(2).getTelefone());
        assertEquals(paciente3.getCelular(), resultado.get(2).getCelular());
        assertEquals(paciente3.getData_de_nascimento(), resultado.get(2).getData_de_nascimento());
        assertEquals(paciente3.getSexo(), resultado.get(2).getSexo());
        assertEquals(paciente3.getTem_convenio(), resultado.get(2).getTem_convenio());
        assertEquals(paciente3.getConvenio().getId(), resultado.get(2).getConvenio().getId());
        assertEquals(paciente3.getConvenio().getTelefone(), resultado.get(2).getConvenio().getTelefone());
        assertEquals(paciente3.getConvenio().getCnpj(), resultado.get(2).getConvenio().getCnpj());
        assertEquals(paciente3.getConvenio().getEmpresa(), resultado.get(2).getConvenio().getEmpresa());
        assertEquals(paciente3.getSenha_acesso(), resultado.get(2).getSenha_acesso());
    }

    @Test
    public void shouldUpdate(){
        //parametros
        var id = 1L;

        ConvenioModel convenioModel = new ConvenioModel(2L,"conveniente","12351245", "40028922");
        PacienteDto parametro = new PacienteDto();
        parametro.setId(43L);
        parametro.setNome("a");
        parametro.setNumero_RG("b");
        parametro.setOrgao_Emissor("c");
        parametro.setNumero_CPF("d");
        parametro.setNumero("e");
        parametro.setComplemento("f");
        parametro.setBairro("g");
        parametro.setCidade("h");
        parametro.setEstado("i");
        parametro.setTelefone("j");
        parametro.setCelular("k");
        parametro.setData_de_nascimento("l");
        parametro.setSexo("m");
        parametro.setTem_convenio("sim");
        parametro.setConvenio(convenioModel);
        parametro.setSenha_acesso("n");

        //mock repository
        ConvenioModel convenio2 = new ConvenioModel(21L,"inconveniente","54321", "08002101210");
        PacienteModel respostaRepository = new PacienteModel();
        respostaRepository.setId(43L);
        respostaRepository.setNome("n");
        respostaRepository.setNumero_RG("m");
        respostaRepository.setOrgao_Emissor("l");
        respostaRepository.setNumero_CPF("k");
        respostaRepository.setNumero("j");
        respostaRepository.setComplemento("i");
        respostaRepository.setBairro("h");
        respostaRepository.setCidade("g");
        respostaRepository.setEstado("f");
        respostaRepository.setTelefone("e");
        respostaRepository.setCelular("d");
        respostaRepository.setData_de_nascimento("c");
        respostaRepository.setSexo("b");
        respostaRepository.setTem_convenio("sim");
        respostaRepository.setConvenio(convenio2);
        respostaRepository.setSenha_acesso("a");

        when(pacienteRepository.findById(id)).thenReturn(Optional.of(respostaRepository));
        when(pacienteRepository.save(any(PacienteModel.class))).thenReturn(respostaRepository);

        //teste
        PacienteDto resultado = pacienteService.update(id, parametro);

        //validação
        assertEquals(parametro.getNome(), resultado.getNome());
        assertEquals(parametro.getNumero_RG(), resultado.getNumero_RG());
        assertEquals(parametro.getOrgao_Emissor(), resultado.getOrgao_Emissor());
        assertEquals(parametro.getNumero_CPF(), resultado.getNumero_CPF());
        assertEquals(parametro.getEndereco(), resultado.getEndereco());
        assertEquals(parametro.getNumero(), resultado.getNumero());
        assertEquals(parametro.getComplemento(), resultado.getComplemento());
        assertEquals(parametro.getNome(), resultado.getNome());
        assertEquals(parametro.getBairro(), resultado.getBairro());
        assertEquals(parametro.getCidade(), resultado.getCidade());
        assertEquals(parametro.getEstado(), resultado.getEstado());
        assertEquals(parametro.getTelefone(), resultado.getTelefone());
        assertEquals(parametro.getCelular(), resultado.getCelular());
        assertEquals(parametro.getData_de_nascimento(), resultado.getData_de_nascimento());
        assertEquals(parametro.getSexo(), resultado.getSexo());
        assertEquals(parametro.getTem_convenio(), resultado.getTem_convenio());
        assertEquals(parametro.getConvenio().getId(), resultado.getConvenio().getId());
        assertEquals(parametro.getConvenio().getTelefone(), resultado.getConvenio().getTelefone());
        assertEquals(parametro.getConvenio().getCnpj(), resultado.getConvenio().getCnpj());
        assertEquals(parametro.getConvenio().getEmpresa(), resultado.getConvenio().getEmpresa());
        assertEquals(parametro.getSenha_acesso(), resultado.getSenha_acesso());

    }

    @Test
    public void updateShouldReturnNull(){
        //parametro
        var id = 1L;

        ConvenioModel convenioModel = new ConvenioModel(2L,"conveniente","12351245", "40028922");
        PacienteDto parametro = new PacienteDto();
        parametro.setId(43L);
        parametro.setNome("a");
        parametro.setNumero_RG("b");
        parametro.setOrgao_Emissor("c");
        parametro.setNumero_CPF("d");
        parametro.setNumero("e");
        parametro.setComplemento("f");
        parametro.setBairro("g");
        parametro.setCidade("h");
        parametro.setEstado("i");
        parametro.setTelefone("j");
        parametro.setCelular("k");
        parametro.setData_de_nascimento("l");
        parametro.setSexo("m");
        parametro.setTem_convenio("sim");
        parametro.setConvenio(convenioModel);
        parametro.setSenha_acesso("n");

        //mock repository
        when(pacienteRepository.findById(id)).thenReturn(Optional.empty());

        //teste
        PacienteDto resultado = pacienteService.update(id, parametro);

        //validação
        assertNull(resultado);
        verify(pacienteRepository, times(1)).findById(id);
        verify(pacienteRepository, times(0)).save(any(PacienteModel.class));
    }
}
