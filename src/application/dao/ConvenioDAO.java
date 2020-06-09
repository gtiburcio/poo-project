package application.dao;

import application.model.Convenio;
import application.model.IModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConvenioDAO implements IDAO {

    private final Connection connection;

    public ConvenioDAO() {
        this.connection = MySingletonConnection.getInstance().connection;
    }

    @Override
    public void save(IModel obj) throws Exception {
        Convenio convenio = (Convenio) obj;
        String sql = "insert into convenio(nome) values (?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, convenio.getNome());
        pstm.execute();
    }

    @Override
    public void delete(IModel obj) throws Exception {
    	Convenio convenio = (Convenio) obj;
        String sql = "delete from convenio where id_convenio = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setLong(1, convenio.getId());
        pstm.execute();
    }

    @Override
    public void update(IModel obj) throws Exception {
    	Convenio convenio = (Convenio) obj;
        String sql = "update convenio set nome = ? where id_convenio = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, convenio.getNome());
        pstm.setLong(2, convenio.getId());
        pstm.execute();
    }

    @Override
    public List<IModel> findAll() throws Exception {
        String sql = "select * from convenio order by nome";
        List<IModel> planos = new ArrayList<>();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            planos.add(new Convenio(rs.getLong("id_convenio"), rs.getString("nome")));
        }
        return planos;
    }

    @Override
    public IModel findById(long id) throws Exception {
        String sql = "select * from convenio where id_convenio = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return new Convenio(rs.getLong("id_convenio"), rs.getString("nome"));
        }
        return null;
    }

    public Convenio findByNome(String nome) throws Exception {
        String sql = "select * from convenio where nome = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, nome);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return new Convenio(rs.getLong("id_convenio"), rs.getString("nome"));
        }
        return null;
    }
}
