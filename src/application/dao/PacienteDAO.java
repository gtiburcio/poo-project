package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import application.model.IModel;
import application.model.Paciente;
import application.model.Plano;
import application.model.enums.Estados;
import application.model.enums.Genero;

public class PacienteDAO implements IDAO {

	private final Connection connection;

	public PacienteDAO() {
		this.connection = MySingletonConnection.getInstance().connection;
	}

	@Override
	public void save(IModel obj) throws Exception {
		Paciente paciente = (Paciente) obj;
		String sql = "insert into paciente(nome, dataNasc, genero, cpf, rg, nCarteirinha, id_plano, email, telResid, telCelular, logradouro, cep, complemento, numero, bairro, cidade, uf) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, paciente.getNome());
		pstm.setDate(2, java.sql.Date.valueOf(paciente.getDataNasc()));
		pstm.setString(3, paciente.getGenero().name());
		pstm.setString(4, paciente.getCpf());
		pstm.setString(5, paciente.getRg());
		pstm.setString(6, paciente.getNCarteirinha());
		pstm.setLong(7, paciente.getPlano().getId());
		pstm.setString(8, paciente.getEmail());
		pstm.setString(9, paciente.getTelResid());
		pstm.setString(10, paciente.getTelCelular());
		pstm.setString(11, paciente.getLogradouro());
		pstm.setString(12, paciente.getCep());
		pstm.setString(13, paciente.getComplemento());
		pstm.setString(14, paciente.getNumero());
		pstm.setString(15, paciente.getBairro());
		pstm.setString(16, paciente.getCidade());
		pstm.setString(17, paciente.getUf().name());
		pstm.execute();
	}

	@Override
	public void delete(IModel obj) throws Exception {
		Paciente paciente = (Paciente) obj;
		String sql = "delete from paciente where id_paciente = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, paciente.getId());
		pstm.execute();
	}

	@Override
	public void update(IModel obj) throws Exception {
		Paciente paciente = (Paciente) obj;
		String sql = "update paciente set nome = ?, dataNasc = ?, genero = ?, cpf = ?, rg = ?, nCarteirinha = ?, id_plano = ?, email = ?, telResid = ?, telCelular = ?, logradouro = ?, cep = ?, complemento = ?, numero = ?, bairro = ?, cidade = ?, uf = ? where id_paciente = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, paciente.getNome());
		pstm.setDate(2, java.sql.Date.valueOf(paciente.getDataNasc()));
		pstm.setString(3, paciente.getGenero().name());
		pstm.setString(4, paciente.getCpf());
		pstm.setString(5, paciente.getRg());
		pstm.setString(6, paciente.getNCarteirinha());
		pstm.setLong(7, paciente.getPlano().getId());
		pstm.setString(8, paciente.getEmail());
		pstm.setString(9, paciente.getTelResid());
		pstm.setString(10, paciente.getTelCelular());
		pstm.setString(11, paciente.getLogradouro());
		pstm.setString(12, paciente.getCep());
		pstm.setString(13, paciente.getComplemento());
		pstm.setString(14, paciente.getNumero());
		pstm.setString(15, paciente.getBairro());
		pstm.setString(16, paciente.getCidade());
		pstm.setString(17, paciente.getUf().name());
		pstm.setLong(18, paciente.getId());
		pstm.execute();
	}

	@Override
	public List<IModel> findAll() throws Exception {
		String sql = "select * from paciente order by nome";
		List<IModel> pacientes = new ArrayList<>();
		PreparedStatement pstm = connection.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			pacientes.add(new Paciente(rs.getLong("id_paciente"), rs.getString("nome"),
					rs.getDate("dataNasc").toLocalDate(), Genero.valueOf(rs.getString("genero")), rs.getString("cpf"),
					rs.getString("rg"), rs.getString("nCarteirinha"),
					Plano.builder().id(rs.getLong("id_plano")).build(), rs.getString("email"), rs.getString("telResid"),
					rs.getString("telCelular"), rs.getString("logradouro"), rs.getString("cep"),
					rs.getString("complemento"), rs.getString("numero"), rs.getString("bairro"), rs.getString("cidade"),
					Estados.valueOf(rs.getString("uf"))));
		}
		return pacientes;
	}

	@Override
	public IModel findById(long id) throws Exception {
		String sql = "select * from paciente where id_paciente = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, id);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			return new Paciente(rs.getLong("id_paciente"), rs.getString("nome"), rs.getDate("dataNasc").toLocalDate(),
					Genero.valueOf(rs.getString("genero")), rs.getString("cpf"), rs.getString("rg"),
					rs.getString("nCarteirinha"), Plano.builder().id(rs.getLong("id_plano")).build(),
					rs.getString("email"), rs.getString("telResid"), rs.getString("telCelular"),
					rs.getString("logradouro"), rs.getString("cep"), rs.getString("complemento"),
					rs.getString("numero"), rs.getString("bairro"), rs.getString("cidade"),
					Estados.valueOf(rs.getString("uf")));
		}
		return null;
	}
}
