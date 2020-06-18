package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import application.model.IModel;
import application.model.Paciente;
import application.model.PacientePlano;
import application.model.Plano;

public class PacientePlanoDAO implements IDAO {

	private final Connection connection;

	public PacientePlanoDAO() {
		this.connection = MySingletonConnection.getInstance().connection;
	}

	@Override
	public void save(IModel obj) throws Exception {
		PacientePlano pacientePlano = (PacientePlano) obj;
		String sql = "insert into paciente_plano(id_paciente, id_plano) values (?, ?)";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, pacientePlano.getPaciente().getId());
		pstm.setLong(2, pacientePlano.getPlano().getId());
		pstm.execute();
	}

	@Override
	public void delete(IModel obj) throws Exception {
		PacientePlano pacientePlano = (PacientePlano) obj;
		String sql = "delete from paciente_plano where id_paciente_plano = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, pacientePlano.getId());
		pstm.execute();
	}

	@Override
	public void update(IModel obj) throws Exception {
		PacientePlano pacientePlano = (PacientePlano) obj;
		String sql = "update paciente_plano set id_paciente = ?, id_plano = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, pacientePlano.getPaciente().getId());
		pstm.setLong(2, pacientePlano.getPlano().getId());
		pstm.execute();
	}

	@Override
	public List<IModel> findAll() throws Exception {
		String sql = "select * from paciente_plano order by id_paciente_plano";
		List<IModel> paciente_plano = new ArrayList<>();
		PreparedStatement pstm = connection.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			paciente_plano.add(new PacientePlano(rs.getLong("id_paciente_plano"),
					Paciente.builder().id(rs.getLong("id_paciente")).build(),
					Plano.builder().id(rs.getLong("id_plano")).build()));
		}
		return paciente_plano;
	}

	@Override
	public IModel findById(long id) throws Exception {
		String sql = "select * from paciente_plano where id_paciente_plano = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, id);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			return new PacientePlano(rs.getLong("id_paciente_plano"),
					Paciente.builder().id(rs.getLong("id_paciente")).build(),
					Plano.builder().id(rs.getLong("id_plano")).build());
		}
		return null;
	}

	public IModel findByPaciente(long id) throws Exception {
		String sql = "select * from paciente_plano where id_paciente = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, id);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			return new PacientePlano(rs.getLong("id_paciente_plano"),
					Paciente.builder().id(rs.getLong("id_paciente")).build(),
					Plano.builder().id(rs.getLong("id_plano")).build());
		}
		return null;
	}
}
