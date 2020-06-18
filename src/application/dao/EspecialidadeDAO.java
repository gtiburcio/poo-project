package application.dao;

import application.model.Especialidade;
import application.model.IModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
