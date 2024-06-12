package br.edu.imepac.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import br.edu.imepac.models.ConvenioModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDto {
    private Long id;
    private String nome;
    private String numero_RG;
    private String orgao_Emissor;
    private String numero_CPF;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;
    private String celular;
    private String data_de_nascimento;
    private String sexo;
    private String tem_convenio;
    private ConvenioModel convenio;
    private String senha_acesso;

}
