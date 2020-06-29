package application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento implements IModel {

    private long id;

    private LocalDate data;

    private LocalTime hora;

    private int duracao;

    private Paciente paciente;

    private Medico medico;
}
