package application.dao;

import application.model.Convenio;
import application.model.IModel;
import application.model.Plano;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO implements IDAO {

	private final Connection connection;

	public PlanoDAO() {
		this.connection = MySingletonConnection.getInstance().connection;
	}

	@Override
	public void save(IModel obj) throws Exception {
		Plano plano = (Plano) obj;
		String sql = "insert into plano(nome, id_convenio) values (?, ?)";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, plano.getNome());
		pstm.setLong(2, plano.getConvenio().getId());
		pstm.execute();
	}

	@Override
	public void delete(IModel obj) throws Exception {
		Plano plano = (Plano) obj;
		String sql = "delete from plano where id_plano = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, plano.getId());
		pstm.execute();
	}

	@Override
	public void update(IModel obj) throws Exception {
		Plano plano = (Plano) obj;
		String sql = "update plano set nome = ?, id_convenio = ? where id_convenio = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, plano.getNome());
		pstm.setLong(2, plano.getConvenio().getId());
		pstm.setLong(3, plano.getId());
		pstm.execute();
	}

	@Override
	public List<IModel> findAll() throws Exception {
		String sql = "select * from plano order by nome";
		List<IModel> planos = new ArrayList<>();
		PreparedStatement pstm = connection.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			ConvenioDAO cdao = new ConvenioDAO();
			Convenio convenio = (Convenio) cdao.findById(rs.getLong("id_convenio"));
			planos.add(new Plano(rs.getLong("id_plano"), rs.getString("nome"), convenio));
		}
		return planos;
	}

	@Override
	public IModel findById(long id) throws Exception {
		String sql = "select * from plano where id_plano = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, id);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			return new Plano(rs.getLong("id_plano"), rs.getString("nome"),
					Convenio.builder().id(rs.getLong("id_convenio")).build());
		}
		return null;
	}

	public Plano findByNome(String nome) throws Exception {
		String sql = "select * from plano where nome = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, nome);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			return new Plano(rs.getLong("id_plano"), rs.getString("nome"),
					Convenio.builder().id(rs.getLong("id_convenio")).build());
		}
		return null;
	}

	public List<Plano> findByConvenio(Convenio convenio) throws Exception {
		String sql = "select * from plano where id_convenio = ?";
		List<Plano> planos = new ArrayList<>();
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, convenio.getId());
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			planos.add(new Plano(rs.getLong("id_plano"), rs.getString("nome"), convenio));
		}
		return planos;
	}
}
