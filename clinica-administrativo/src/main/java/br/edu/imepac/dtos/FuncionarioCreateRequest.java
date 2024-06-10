package br.edu.imepac.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioCreateRequest {

    private String nome_Funcionario;
    private String numero_Rg;
    private String orgao_Emissor;
    private String numero_Cpf;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;
    private String celular;
    private String numero_Ctps;
    private String numero_Pis;
    private String Data;
}
