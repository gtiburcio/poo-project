package application.controller;

import application.dao.UsuarioDAO;
import application.model.Usuario;
import application.utils.UsuarioUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioController {

    public void save(Usuario usuario) throws Exception {
        usuario.setSenha(UsuarioUtils.defaultPassword);
        new UsuarioDAO().save(usuario);
    }

    public void delete(Usuario usuario) throws Exception {
        new UsuarioDAO().delete(usuario);
    }

    public void update(Usuario usuario) throws Exception {
        new UsuarioDAO().update(usuario);
    }

    public List<Usuario> findAll() throws Exception {
        return new UsuarioDAO().findAll().stream().map(model -> (Usuario) model)
                .peek(usuario -> {
                    try {
                        usuario.setPerfil(new PerfilController().findById(usuario.getPerfil().getId()));
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }).collect(Collectors.toList());
    }

    public Usuario findById(long id) throws Exception {
        Usuario usuario = (Usuario) new UsuarioDAO().findById(id);
        usuario.setPerfil(new PerfilController().findById(id));
        return usuario;
    }

    public boolean isDuplicateLogin(String login) throws Exception {
        return new UsuarioDAO().isDuplicateLogin(login);
    }
}
