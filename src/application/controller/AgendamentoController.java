package application.controller;

import application.dao.AgendamentoDAO;
import application.model.Agendamento;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AgendamentoController {

    private int horaInicial = 7;

    private int minutoInicial = 0;

    private int intervalos = 30;

    public void salvar(Agendamento agendamento) throws SQLException {
        new AgendamentoDAO().salvar(agendamento);
    }

    public List<Agendamento> findAgendamentosDiaMedico(LocalDate data, long idMedico) throws SQLException {
        return new AgendamentoDAO().findAgendamentosDiaMedico(data, idMedico)
                .stream().map(a -> (Agendamento) a).collect(toList());
    }

    public List<LocalTime> montarHorariosAgendamento(LocalDate data, long idMedico) throws SQLException {
        List<LocalTime> horarios = new ArrayList<>();

        LocalTime hora = LocalTime.of(horaInicial, minutoInicial);

        List<LocalTime> horariosAgendados = findAgendamentosDiaMedico(data, idMedico).stream()
                .map(Agendamento::getHora).collect(toList());

        while (hora.isBefore(LocalTime.of(19, 30))) {
            if (!horariosAgendados.contains(hora)) {
                horarios.add(hora);
            }
            hora = hora.plusMinutes(intervalos);
        }

        return horarios;
    }
}
