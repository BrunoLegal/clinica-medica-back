package br.edu.imepac.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Pacientes")
public class PacienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne
    @JoinColumn(name = "id_convenio")
    private ConvenioModel convenio;
    private String senha_acesso;

}
