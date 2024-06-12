package br.edu.imepac.dtos;

import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.models.PacienteModel;
import br.edu.imepac.models.UsuarioModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendaConsultaCreateRequest {
    private UsuarioModel usuario;
    private PacienteModel paciente;
    private MedicoModel medico;
    private Date data;
    private LocalTime hora;
    private Boolean retorno = false;
    private Boolean cancelado = false;
}
