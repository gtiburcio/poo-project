package application.dao;

import application.model.Agendamento;
import application.model.IModel;
import application.model.Medico;
import application.model.Paciente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    private final Connection connection;

    public AgendamentoDAO() {
        this.connection = MySingletonConnection.getInstance().connection;
    }

    public void salvar(Agendamento agendamento) throws SQLException {
        String sql = "insert into agendamento(data, hora, id_paciente, id_medico) values (?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setDate(1, Date.valueOf(agendamento.getData()));
        pstm.setTime(2, Time.valueOf(agendamento.getHora()));
        pstm.setLong(3, agendamento.getPaciente().getId());
        pstm.setLong(4, agendamento.getMedico().getId());
        pstm.execute();
    }

    public List<IModel> findAgendamentosDiaMedico(LocalDate date, long idMedico) throws SQLException {
        String sql = "select * from agendamento where data = ? and id_medico = ?";
        List<IModel> agendamentos = new ArrayList<>();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setDate(1, Date.valueOf(date));
        pstm.setLong(2, idMedico);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            agendamentos.add(new Agendamento(rs.getLong("id_agendamento"), rs.getDate("data").toLocalDate(),
                    rs.getTime("hora").toLocalTime(), rs.getInt("duracao"),
                    Paciente.builder().id(rs.getLong("id_paciente")).build(),
                    Medico.builder().id(rs.getLong("id_medico")).build()));
        }
        return agendamentos;
    }
}
