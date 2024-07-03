package br.edu.imepac.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "prontuario_paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_agenda")
    private AgendaConsulta idAgenda;
    @NotNull
    private String historico;

    private String receituario;

    private String exames;

}
