package br.edu.imepac.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreateRequest {
    private String login;
    private String senha;
    private Boolean cadastroPaciente = false;
    private Boolean cadastroFuncionario = false;
    private Boolean cadastroUsuario = false;
    private Boolean cadastroEspecialidade = false;
    private Boolean cadastroConvenio = false;
    private Boolean cadastroMedico = false;
    private Boolean agendamentoConsulta = false;
    private Boolean cancelamentoConsulta = false;
    private Boolean moduloAdmnistrativo = false;
    private Boolean moduloAgendamento = false;
    private Boolean moduloAtendimento = false;
}
