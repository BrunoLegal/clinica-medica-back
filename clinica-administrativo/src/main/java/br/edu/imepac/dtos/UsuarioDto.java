package br.edu.imepac.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private Long id;
    private String login;
    private String senha;
    private Boolean cadastroPaciente;
    private Boolean cadastroFuncionario;
    private Boolean cadastroUsuario;
    private Boolean cadastroEspecialidade;
    private Boolean cadastroConvenio;
    private Boolean cadastroMedico;
    private Boolean agendamentoConsulta;
    private Boolean cancelamentoConsulta;
    private Boolean moduloAdministrativo;
    private Boolean moduloAgendamento;
    private Boolean moduloAtendimento;
}
