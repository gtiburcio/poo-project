package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import application.model.IModel;
import application.model.Paciente;
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
		String sql = "insert into paciente(nome, dataNasc, genero, cpf, rg, nCarteirinha, email, telResid, telCelular, logradouro, cep, complemento, numero, bairro, cidade, uf) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, paciente.getNome());
		pstm.setDate(2, java.sql.Date.valueOf(paciente.getDataNasc()));
		pstm.setString(3, paciente.getGenero().name());
		pstm.setString(4, paciente.getCpf());
		pstm.setString(5, paciente.getRg());
		pstm.setString(6, paciente.getNCarteirinha());
		pstm.setString(7, paciente.getEmail());
		pstm.setString(8, paciente.getTelResid());
		pstm.setString(9, paciente.getTelCelular());
		pstm.setString(10, paciente.getLogradouro());
		pstm.setString(11, paciente.getCep());
		pstm.setString(12, paciente.getComplemento());
		pstm.setString(13, paciente.getNumero());
		pstm.setString(14, paciente.getBairro());
		pstm.setString(15, paciente.getCidade());
		pstm.setString(16, paciente.getUf().name());
		pstm.execute();
	}

	public long saveAndGetId(IModel obj) throws Exception {
		Paciente paciente = (Paciente) obj;
		String sql = "insert into paciente(nome, dataNasc, genero, cpf, rg, nCarteirinha, email, telResid, telCelular, logradouro, cep, complemento, numero, bairro, cidade, uf) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pstm.setString(1, paciente.getNome());
		pstm.setDate(2, java.sql.Date.valueOf(paciente.getDataNasc()));
		pstm.setString(3, paciente.getGenero().name());
		pstm.setString(4, paciente.getCpf());
		pstm.setString(5, paciente.getRg());
		pstm.setString(6, paciente.getNCarteirinha());
		pstm.setString(7, paciente.getEmail());
		pstm.setString(8, paciente.getTelResid());
		pstm.setString(9, paciente.getTelCelular());
		pstm.setString(10, paciente.getLogradouro());
		pstm.setString(11, paciente.getCep());
		pstm.setString(12, paciente.getComplemento());
		pstm.setString(13, paciente.getNumero());
		pstm.setString(14, paciente.getBairro());
		pstm.setString(15, paciente.getCidade());
		pstm.setString(16, paciente.getUf().name());
		pstm.execute();
		ResultSet rs = pstm.getGeneratedKeys();
		long lastInsertedId = 0;
		if (rs.next()) {
			lastInsertedId = rs.getLong(1);
		}
		return lastInsertedId;
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
		String sql = "update paciente set nome = ?, dataNasc = ?, genero = ?, cpf = ?, rg = ?, nCarteirinha = ?, email = ?, telResid = ?, telCelular = ?, logradouro = ?, cep = ?, complemento = ?, numero = ?, bairro = ?, cidade = ?, uf = ? where id_paciente = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, paciente.getNome());
		pstm.setDate(2, java.sql.Date.valueOf(paciente.getDataNasc()));
		pstm.setString(3, paciente.getGenero().name());
		pstm.setString(4, paciente.getCpf());
		pstm.setString(5, paciente.getRg());
		pstm.setString(6, paciente.getNCarteirinha());
		pstm.setString(7, paciente.getEmail());
		pstm.setString(8, paciente.getTelResid());
		pstm.setString(9, paciente.getTelCelular());
		pstm.setString(10, paciente.getLogradouro());
		pstm.setString(11, paciente.getCep());
		pstm.setString(12, paciente.getComplemento());
		pstm.setString(13, paciente.getNumero());
		pstm.setString(14, paciente.getBairro());
		pstm.setString(15, paciente.getCidade());
		pstm.setString(16, paciente.getUf().name());
		pstm.setLong(17, paciente.getId());
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
					rs.getString("rg"), rs.getString("nCarteirinha"), rs.getString("email"), rs.getString("telResid"),
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
					rs.getString("nCarteirinha"), rs.getString("email"), rs.getString("telResid"),
					rs.getString("telCelular"), rs.getString("logradouro"), rs.getString("cep"),
					rs.getString("complemento"), rs.getString("numero"), rs.getString("bairro"), rs.getString("cidade"),
					Estados.valueOf(rs.getString("uf")));
		}
		return null;
	}

	public IModel findByCpf(String cpf) throws Exception {
		String sql = "select * from paciente where cpf = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, cpf);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			return new Paciente(rs.getLong("id_paciente"), rs.getString("nome"), rs.getDate("dataNasc").toLocalDate(),
					Genero.valueOf(rs.getString("genero")), rs.getString("cpf"), rs.getString("rg"),
					rs.getString("nCarteirinha"), rs.getString("email"), rs.getString("telResid"),
					rs.getString("telCelular"), rs.getString("logradouro"), rs.getString("cep"),
					rs.getString("complemento"), rs.getString("numero"), rs.getString("bairro"), rs.getString("cidade"),
					Estados.valueOf(rs.getString("uf")));
		}
		return null;
	}
}
