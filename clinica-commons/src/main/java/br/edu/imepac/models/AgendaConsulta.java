package br.edu.imepac.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "agenda_consulta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaConsulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioModel usuario;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private PacienteModel paciente;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private MedicoModel medico;
    @NotNull
    private Date data;
    @NotNull
    private String hora;
    @NotNull
    private Boolean retorno;
    @NotNull
    private Boolean cancelado;
    @Column(name = "motivo_cancelamento")
    private String motivoCancelamento;
}
