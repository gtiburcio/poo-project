package application.dao;

import application.model.IModel;
import application.model.Perfil;
import application.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IDAO {

    private final Connection connection;

    public UsuarioDAO() {
        this.connection = MySingletonConnection.getInstance().connection;
    }

    @Override
    public void save(IModel obj) throws Exception {
        String sql = "insert into usuario(nome, login, senha, id_perfil) values (?, ?, ?, ?)";
        Usuario usuario = (Usuario) obj;
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, usuario.getNome());
        pstm.setString(2, usuario.getLogin());
        pstm.setString(3, usuario.getSenha());
        pstm.setLong(4, usuario.getPerfil().getId());
        pstm.execute();
    }

    @Override
    public void delete(IModel obj) throws Exception {
        String sql = "delete from usuario where id_usuario = ?";
        Usuario usuario = (Usuario) obj;
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setLong(1, usuario.getId());
        pstm.execute();
    }

    @Override
    public void update(IModel obj) throws Exception {
        String sql = "update usuario set nome = ?, login = ?, senha = ?, id_perfil = ? where id_usuario = ?";
        Usuario usuario = (Usuario) obj;
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, usuario.getNome());
        pstm.setString(2, usuario.getLogin());
        pstm.setString(3, usuario.getSenha());
        pstm.setLong(4, usuario.getPerfil().getId());
        pstm.setLong(5, usuario.getId());
        pstm.execute();
    }

    @Override
    public List<IModel> findAll() throws SQLException {
        String sql = "select * from usuario order by nome";
        List<IModel> usuarios = new ArrayList<>();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            Usuario usuario = Usuario.builder()
                    .id(rs.getLong("id_usuario"))
                    .nome(rs.getString("nome"))
                    .login(rs.getString("login"))
                    .senha(rs.getString("senha"))
                    .perfil(Perfil.builder().id(rs.getLong("id_perfil")).build())
                    .build();
            usuarios.add(usuario);
        }
        return usuarios;
    }

    @Override
    public IModel findById(long id) throws Exception {
        String sql = "select * from usuario where id_usuario = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        if (rs.next()) {
            return Usuario.builder()
                    .id(rs.getLong("id_usuario"))
                    .nome(rs.getString("nome"))
                    .login(rs.getString("login"))
                    .senha(rs.getString("senha"))
                    .perfil(Perfil.builder().id(rs.getLong("id_perfil")).build())
                    .build();
        }
        return null;
    }

    public boolean isDuplicateLogin(String login) throws Exception {
        String sql = "select * from usuario where login = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, login);
        ResultSet rs = pstm.executeQuery();
        return rs.next();
    }
}
