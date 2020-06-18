package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import application.model.Especialidade;
import application.model.IModel;

public class EspecialidadeDAO implements IDAO {

    private final Connection connection;

    public EspecialidadeDAO() {
        this.connection = MySingletonConnection.getInstance().connection;
    }

    @Override
    public void save(IModel obj) throws Exception {
    	Especialidade especialidade = (Especialidade) obj;
        String sql = "insert into especialidade(nome, descricao) values (?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, especialidade.getNome());
        pstm.setString(2, especialidade.getDescricao());
        pstm.execute();
    }
    
	public long saveAndGetId(IModel obj) throws Exception {
		Especialidade especialidade = (Especialidade) obj;
		String sql = "insert into especialidade(nome, descricao) values (?, ?)";
		PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pstm.setString(1, especialidade.getNome());
		pstm.setString(2, especialidade.getDescricao());
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
    	Especialidade especialidade = (Especialidade) obj;
        String sql = "delete from especialidade where id_especialidade = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setLong(1, especialidade.getId());
        pstm.execute();
    }

    @Override
    public void update(IModel obj) throws Exception {
    	Especialidade especialidade = (Especialidade) obj;
        String sql = "update especialidade set nome = ?, descricao = ? where id_especialidade = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, especialidade.getNome());
        pstm.setString(2, especialidade.getDescricao());
        pstm.setLong(3, especialidade.getId());
        pstm.execute();
    }

    @Override
    public List<IModel> findAll() throws Exception {
        String sql = "select * from especialidade order by nome";
        List<IModel> especialidades = new ArrayList<>();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
        	especialidades.add(new Especialidade(rs.getLong("id_especialidade"), rs.getString("nome"), rs.getString("descricao")));
        }
        return especialidades;
    }

    @Override
    public IModel findById(long id) throws Exception {
        String sql = "select * from especialidade where id_especialidade = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return new Especialidade(rs.getLong("id_especialidade"), rs.getString("nome"), rs.getString("descricao"));
        }
        return null;
    }

    public Especialidade findByNome(String nome) throws Exception {
        String sql = "select * from especialidade where nome = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, nome);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return new Especialidade(rs.getLong("id_especialidade"), rs.getString("nome"), rs.getString("descricao"));
        }
        return null;
    }

}
