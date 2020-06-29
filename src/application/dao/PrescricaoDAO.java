package application.dao;

import application.model.Prescricao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrescricaoDAO {

    private final Connection connection;

    public PrescricaoDAO() {
        this.connection = MySingletonConnection.getInstance().connection;
    }

    public void salvar(Prescricao prescricao) throws SQLException {
        String sql = "insert into prescricao(descricao, id_consulta) values (?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, prescricao.getDescricao());
        pstm.setLong(2, prescricao.getConsulta().getId());
        pstm.execute();
    }
}
