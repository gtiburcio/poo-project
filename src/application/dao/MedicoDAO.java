package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import application.model.Especialidade;
import application.model.IModel;
import application.model.Medico;
import application.model.enums.Estados;
import application.model.enums.Genero;

public class MedicoDAO implements IDAO {

	private final Connection connection;

	public MedicoDAO() {
		this.connection = MySingletonConnection.getInstance().connection;
	}

	@Override
	public void save(IModel obj) throws Exception {
		Medico medico = (Medico) obj;
		String sql = "insert into medico(nome, cpf, rg, dataNasc, crm, email, logradouro, cep, complemento, numero, bairro, cidade, uf, telResid, telCelular, genero, id_especialidade) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, medico.getNome());
		pstm.setString(2, medico.getCpf());
		pstm.setString(3, medico.getRg());
		pstm.setDate(4, java.sql.Date.valueOf(medico.getDataNasc()));
		pstm.setString(5, medico.getCrm());
		pstm.setString(6, medico.getEmail());
		pstm.setString(7, medico.getLogradouro());
		pstm.setString(8, medico.getCep());
		pstm.setString(9, medico.getComplemento());
		pstm.setString(10, medico.getNumero());
		pstm.setString(11, medico.getBairro());
		pstm.setString(12, medico.getCidade());
		pstm.setString(13, medico.getUf().name());
		pstm.setString(14, medico.getTelResid());
		pstm.setString(15, medico.getTelCelular());
		pstm.setString(16, medico.getGenero().name());
		pstm.setLong(17, medico.getEspecialidade().getId());
		pstm.execute();
	}

	@Override
	public void delete(IModel obj) throws Exception {
		Medico medico = (Medico) obj;
		String sql = "delete from medico where id_medico = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, medico.getId());
		pstm.execute();
	}

	@Override
	public void update(IModel obj) throws Exception {
		Medico medico = (Medico) obj;
		String sql = "update medico set nome = ?, cpf = ?, rg = ?, dataNasc = ?, crm = ?, email = ?, logradouro = ?, cep = ?, complemento = ?, numero = ?, bairro = ?, cidade = ?, uf = ?, telResid = ?, telCelular = ?, genero = ?, id_especialidade = ? where id_medico = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, medico.getNome());
		pstm.setString(2, medico.getCpf());
		pstm.setString(3, medico.getRg());
		pstm.setDate(4, java.sql.Date.valueOf(medico.getDataNasc()));
		pstm.setString(5, medico.getCrm());
		pstm.setString(6, medico.getEmail());
		pstm.setString(7, medico.getLogradouro());
		pstm.setString(8, medico.getCep());
		pstm.setString(9, medico.getComplemento());
		pstm.setString(10, medico.getNumero());
		pstm.setString(11, medico.getBairro());
		pstm.setString(12, medico.getCidade());
		pstm.setString(13, medico.getUf().name());
		pstm.setString(14, medico.getTelResid());
		pstm.setString(15, medico.getTelCelular());
		pstm.setString(16, medico.getGenero().name());
		pstm.setLong(17, medico.getEspecialidade().getId());
		pstm.setLong(18, medico.getId());
		pstm.execute();
	}

	@Override
	public List<IModel> findAll() throws Exception {
		String sql = "select * from medico order by nome";
		List<IModel> medicos = new ArrayList<>();
		PreparedStatement pstm = connection.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			medicos.add(new Medico(rs.getLong("id_medico"), rs.getString("nome"), rs.getString("cpf"),
					rs.getString("rg"), rs.getDate("dataNasc").toLocalDate(), rs.getString("crm"),
					rs.getString("email"), rs.getString("logradouro"), rs.getString("cep"), rs.getString("complemento"),
					rs.getString("numero"), rs.getString("bairro"), rs.getString("cidade"),
					Estados.valueOf(rs.getString("uf")), rs.getString("telResid"), rs.getString("telCelular"),
					Genero.valueOf(rs.getString("genero")),
					Especialidade.builder().id(rs.getLong("id_especialidade")).build()));
		}
		return medicos;
	}

	@Override
	public IModel findById(long id) throws Exception {
		String sql = "select * from medico where id_medico = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, id);
		ResultSet rs = pstm.executeQuery();
		if (rs.next()) {
			return new Medico(rs.getLong("id_medico"), rs.getString("nome"), rs.getString("cpf"),
					rs.getString("rg"), rs.getDate("dataNasc").toLocalDate(), rs.getString("crm"),
					rs.getString("email"), rs.getString("logradouro"), rs.getString("cep"), rs.getString("complemento"),
					rs.getString("numero"), rs.getString("bairro"), rs.getString("cidade"),
					Estados.valueOf(rs.getString("uf")), rs.getString("telResid"), rs.getString("telCelular"),
					Genero.valueOf(rs.getString("genero")),
					Especialidade.builder().id(rs.getLong("id_especialidade")).build());
		}
		return null;
	}

	public List<IModel> findByEspecialidade(long id) throws Exception {
		String sql = "select * from medico where id_especialidade = ? order by nome";
		List<IModel> medicos = new ArrayList<>();
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setLong(1, id);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			medicos.add(new Medico(rs.getLong("id_medico"), rs.getString("nome"), rs.getString("cpf"),
					rs.getString("rg"), rs.getDate("dataNasc").toLocalDate(), rs.getString("crm"),
					rs.getString("email"), rs.getString("logradouro"), rs.getString("cep"), rs.getString("complemento"),
					rs.getString("numero"), rs.getString("bairro"), rs.getString("cidade"),
					Estados.valueOf(rs.getString("uf")), rs.getString("telResid"), rs.getString("telCelular"),
					Genero.valueOf(rs.getString("genero")),
					Especialidade.builder().id(rs.getLong("id_especialidade")).build()));
		}
		return medicos;
	}
}
