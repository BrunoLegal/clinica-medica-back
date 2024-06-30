package br.edu.imepac.dtos;

import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.models.PacienteModel;
import br.edu.imepac.models.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaConsultaCreateRequest {
    private UsuarioModel usuario;
    private PacienteModel paciente;
    private MedicoModel medico;
    private String data;
    private String hora;
    private Boolean retorno;
    private Boolean cancelado;
}
