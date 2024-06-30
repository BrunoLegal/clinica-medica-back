package br.edu.imepac.dtos;

import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.models.PacienteModel;
import br.edu.imepac.models.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaMapperDto {
    private Long id;
    private UsuarioModel usuario;
    private PacienteModel paciente;
    private MedicoModel medico;
    private String hora;
    private Boolean retorno;
    private Boolean cancelado;
    private String motivoCancelamento;
}
