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
@Table(name = "usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String login;
    @NotNull
    private String senha;
    @NotNull
    @Column(name = "cadastro_paciente")
    private Boolean cadastroPaciente = false;
    @NotNull
    @Column(name = "cadastro_funcionario")
    private Boolean cadastroFuncionario = false;
    @NotNull
    @Column(name = "cadastro_usuario")
    private Boolean cadastroUsuario = false;
    @NotNull
    @Column(name = "cadastro_especialidade")
    private Boolean cadastroEspecialidade = false;
    @NotNull
    @Column(name = "cadastro_convenio")
    private Boolean cadastroConvenio = false;
    @NotNull
    @Column(name = "cadastro_medico")
    private Boolean cadastroMedico = false;
    @NotNull
    @Column(name = "agendamento_consulta")
    private Boolean agendamentoConsulta = false;
    @NotNull
    @Column(name = "cancelamento_consulta")
    private Boolean cancelamentoConsulta = false;
    @NotNull
    @Column(name = "modulo_administrativo")
    private Boolean moduloAdmnistrativo = false;
    @NotNull
    @Column(name = "modulo_agendamento")
    private Boolean moduloAgendamento = false;
    @NotNull
    @Column(name = "modulo_atendimento")
    private Boolean moduloAtendimento = false;
}
