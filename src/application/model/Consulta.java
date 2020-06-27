package application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Consulta implements IModel {

    private long id;

    private LocalDate data;

    private LocalTime hora;

    private Agendamento agendamento;

    private List<Prescricao> prescricoes;
}
