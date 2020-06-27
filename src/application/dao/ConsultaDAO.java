package application.dao;

import application.model.Consulta;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class ConsultaDAO {

    private final Connection connection;

    public ConsultaDAO() {
        this.connection = MySingletonConnection.getInstance().connection;
    }

    public long salvar(Consulta consulta) throws SQLException {
        String sql = "insert into consulta(data, hora, id_agendamento) values (?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pstm.setDate(1, Date.valueOf(consulta.getData()));
        pstm.setTime(2, Time.valueOf(consulta.getHora()));
        pstm.setLong(3, consulta.getAgendamento().getId());
        pstm.execute();
        ResultSet rs = pstm.getGeneratedKeys();
        long lastInsertedId = 0;
        if (rs.next()) {
            lastInsertedId = rs.getLong(1);
        }
        return lastInsertedId;
    }
}
