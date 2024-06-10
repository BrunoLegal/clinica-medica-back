package br.edu.imepac.models;

import br.edu.imepac.models.ConvenioModel;
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

    @NotNull
    private String nome;
    @NotNull
    private String numero_RG;
    @NotNull
    private String orgao_Emissor;
    @NotNull
    private String numero_CPF;
    @NotNull
    private String endereco;
    @NotNull
    private String numero;
    private String complemento;
    @NotNull
    private String bairro;
    @NotNull
    private String cidade;
    @NotNull
    private String estado;
    private String telefone;
    @NotNull
    private String celular;
    @NotNull
    private String data_de_nascimento;
    @NotNull
    private String sexo;
    @NotNull
    private String tem_convenio;
    @ManyToOne
    @JoinColumn(name = "id_convenio")
    private ConvenioModel convenio;
    @NotNull
    private String senha_acesso;

}
