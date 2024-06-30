package br.edu.imepac.services;

import br.edu.imepac.dtos.ConvenioCreateRequest;
import br.edu.imepac.dtos.ConvenioDto;
import br.edu.imepac.models.ConvenioModel;
import br.edu.imepac.repositories.ConvenioRepository;

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
class ConvenioServiceTest {

    /*
     Guia rápido e resumido de como fazer um teste unitário

     Recomendo assistir esses vídeos:
     https://www.youtube.com/watch?v=vZm0lHciFsQ
     https://www.youtube.com/watch?v=R3ItceaMwnw

     Primeiro, é preciso criar uma instância da classe que será testada e usar a anotação @InjectMocks
     nela, para o mockito saber pra quem ele vai simular.

     Depois instancia uma classe que será mockada com a anotação @Mock, no caso o ConvenioRepository.
     Isso significa que o mockito vai "fingir" que é esse objeto e permite que você manipule livremente
     qualquer dado que sairá de um retorno de um método dele.

     Olha aí em baixo como eu fiz:
     */

    @InjectMocks
    private ConvenioService convenioService;

    @Mock
    private ConvenioRepository convenioRepository;


    @Test
    void shouldDelete() {
        //simulações pre-condições
        /*
         O when é usado para toda vez que será usado um método do objeto mockado

         funcniona assim:

         when(objeto_mockado).método_usado.thenReturn(retorno_criado_artificialmente_por_voce);

         no caso, como o convenioRepository.deleteById não retorna nada, eliminamos o thenReturn e
         colocamos o doNothing antes do when
         */

        doNothing().when(convenioRepository).deleteById(1L);

        //o que será testado
        convenioService.delete(1L);

        //condições/afirmações validadas

        /*
        Aqui é que se coloca a parte do código que realmente verifica se tudo está rodando certo
        um dos comandos usados pra isso é o verify, que verifica se um método foi usado um número específico de vezes

        verify(objeto,times(nºde vezes q vc espera q o método seja acionado)).método(parâmetros do método);

        há outros, vou mostrar nos testes seguintes
         */
        verify(convenioRepository, times(1)).deleteById(1L);
    }


    @Test
    void shouldFindAll() {
        /*
        No inicio do teste é bom já criar objetos de tudo que será simulado,
        nesse caso o convenioRepository retornaria mais de um convenioModel, então eu criei dois ConvenioModel pra
        simular o retorno do convenioRepository
        */
        ConvenioModel convenio1 = new ConvenioModel();
        convenio1.setId(1L);
        convenio1.setEmpresa("Empresa 1");
        convenio1.setCnpj("00.000.000/0000-00");
        convenio1.setTelefone("123456789");

        ConvenioModel convenio2 = new ConvenioModel();
        convenio2.setId(2L);
        convenio2.setEmpresa("Empresa 2");
        convenio2.setCnpj("11.111.111/1111-11");
        convenio2.setTelefone("987654321");

        //o convenio repository.findAll retorna uma lista, então colquei os dois convênios em uma lista
        List<ConvenioModel> mockConvenios = Arrays.asList(convenio1, convenio2);

        when(convenioRepository.findAll()).thenReturn(mockConvenios);


        List<ConvenioDto> result = convenioService.findAll();

        // Verifica o resultado
        /*
        aqui eu uso o assertEquals. é bem fácil de usar:

        assertEquals(resultadoEsperado, resultadoReal);

        tem mais alguns depois...
         */
        assertEquals(2, result.size()); //verifica quantos resultados foram obtidos
        assertEquals("Empresa 1", result.get(0).getEmpresa());
        assertEquals("00.000.000/0000-00", result.get(0).getCnpj());
        assertEquals("123456789", result.get(0).getTelefone());
        assertEquals("Empresa 2", result.get(1).getEmpresa());
        assertEquals("11.111.111/1111-11", result.get(1).getCnpj());
        assertEquals("987654321", result.get(1).getTelefone());
    }

    @Test
    void shouldUpdateFoundConvenio() {//é o convencional, o padrão do comercial colocaros nomes das classes em inglês
        //também é bom costume colocar o nome de cada teste unitário ter um nome bem autoexplicativo, nesse caso: "deve atualizar o convênio encontrado"
        Long id = 1L;

        ConvenioDto convenioDto = new ConvenioDto();
        convenioDto.setId(id);
        convenioDto.setEmpresa("novaempresa");
        convenioDto.setCnpj("333.333.333/3333-33");
        convenioDto.setTelefone("1111-1111");

        ConvenioModel convenioModel = new ConvenioModel();
        convenioModel.setId(id);
        convenioModel.setEmpresa("empresa teste");
        convenioModel.setTelefone("0000-0000");
        convenioModel.setCnpj("22.222.222/2222-22");

        when(convenioRepository.findById(id)).thenReturn(Optional.of(convenioModel));
        when(convenioRepository.save(any(ConvenioModel.class))).thenReturn(convenioModel);

        ConvenioDto resultado = convenioService.update(id, convenioDto);
        /*
        assertNull é bem simples: ele verifica se o que tá dentro dos parêntesis é nulo;

        também existe o assertNotNull, que verifica se o que está dentro do parêntesis não é nulo;
         */
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        assertEquals("novaempresa", resultado.getEmpresa());
        assertEquals("333.333.333/3333-33", resultado.getCnpj());
        assertEquals("1111-1111", resultado.getTelefone());
    }

    @Test
    void updateShouldReturnNull() {
        Long id = 1L;
        ConvenioModel enviarParaAtualizar = new ConvenioModel();
        enviarParaAtualizar.setId(1L);

        when(convenioRepository.findById(id)).thenReturn(Optional.empty());

        ConvenioDto response = convenioService.findById(id);

        assertNull(response);

        verify(convenioRepository, times(1)).findById(id);
        verify(convenioRepository, times(0)).save(enviarParaAtualizar);
    }

    @Test
    void shouldSave() {
        ConvenioCreateRequest convenioCreateRequest = new ConvenioCreateRequest();
        convenioCreateRequest.setCnpj("111.111.111/1111-11");
        convenioCreateRequest.setEmpresa("desempresa");
        convenioCreateRequest.setTelefone("4002-8922");

        ConvenioModel savedConvenio =new ConvenioModel();
        savedConvenio.setId(1L);
        savedConvenio.setCnpj("111.111.111/1111-11");
        savedConvenio.setEmpresa("desempresa");
        savedConvenio.setTelefone("4002-8922");

        when(convenioRepository.save(any(ConvenioModel.class))).thenReturn(savedConvenio);

        ConvenioDto resultado = convenioService.save(convenioCreateRequest);

        assertEquals(1L, resultado.getId());
        assertEquals("111.111.111/1111-11", resultado.getCnpj());
        assertEquals("desempresa", resultado.getEmpresa());
        assertEquals("4002-8922", resultado.getTelefone());

    }

    @Test
    void shouldFindById() {
        ConvenioModel convenioModel = new ConvenioModel();
        convenioModel.setId(1L);
        convenioModel.setEmpresa("ababa");
        convenioModel.setCnpj("123.123.123/1234-12");
        convenioModel.setTelefone("08002101210");

        when(convenioRepository.findById(anyLong())).thenReturn(Optional.of(convenioModel));

        ConvenioDto resultado = convenioService.findById(1L);

        assertEquals("ababa", resultado.getEmpresa());
        assertEquals("123.123.123/1234-12", resultado.getCnpj());
        assertEquals("08002101210", resultado.getTelefone());
    }

    @Test
    void findByIdShouldReturnNull(){
        when(convenioRepository.findById(anyLong())).thenReturn(Optional.empty());

        ConvenioDto resposta = convenioService.findById(anyLong());

        assertNull(resposta);
        verify(convenioRepository, times(1)).findById(anyLong());
    }
}