package application.dao;

import application.model.IModel;
import application.model.Perfil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PerfilDAO implements IDAO {

    private final Connection connection;

    public PerfilDAO() {
        this.connection = MySingletonConnection.getInstance().connection;
    }

    @Override
    public void save(IModel obj) throws Exception {
        Perfil perfil = (Perfil) obj;
        String sql = "insert into perfil(descricao) values (?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, perfil.getDescricao());
        pstm.execute();
    }

    @Override
    public void delete(IModel obj) throws Exception {
        Perfil perfil = (Perfil) obj;
        String sql = "delete from perfil where id_perfil = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setLong(1, perfil.getId());
        pstm.execute();
    }

    @Override
    public void update(IModel obj) throws Exception {
        Perfil perfil = (Perfil) obj;
        String sql = "update perfil set descricao = ? where id_perfil = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, perfil.getDescricao());
        pstm.setLong(2, perfil.getId());
        pstm.execute();
    }

    @Override
    public List<IModel> findAll() throws Exception {
        String sql = "select * from perfil order by descricao";
        List<IModel> perfis = new ArrayList<>();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            perfis.add(new Perfil(rs.getLong("id_perfil"), rs.getString("descricao")));
        }
        return perfis;
    }

    @Override
    public IModel findById(long id) throws Exception {
        String sql = "select * from perfil where id_perfil = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return new Perfil(rs.getLong("id_perfil"), rs.getString("descricao"));
        }
        return null;
    }

    public Perfil findByDescription(String descricao) throws Exception {
        String sql = "select * from perfil where descricao = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, descricao);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return new Perfil(rs.getLong("id_perfil"), rs.getString("descricao"));
        }
        return null;
    }
}
