package br.edu.imepac.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "funcionarios")
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome_Funcionario;
    @NotNull
    private String numero_Rg;
    @NotNull
    private String orgao_Emissor;
    @NotNull
    private String numero_Cpf;
    @NotNull
    private String endereco;
    @NotNull
    private String numero;
    @NotNull
    private String complemento;
    @NotNull
    private String bairro;
    @NotNull
    private String cidade;
    @NotNull
    private String estado;
    @NotNull
    private String telefone;
    @NotNull
    private String celular;
    @NotNull
    private String numero_Ctps;
    @NotNull
    private String numero_Pis;
    @NotNull
    private String Data;
}
