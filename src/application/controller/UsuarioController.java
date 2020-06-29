package application.controller;

import application.dao.UsuarioDAO;
import application.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioController {

    public void save(Usuario usuario) throws Exception {
        new UsuarioDAO().save(usuario);
    }

    public long saveAndGetId(Usuario usuario) throws Exception {
        return new UsuarioDAO().saveAndGetId(usuario);
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

    private Usuario findByLogin(String login) throws Exception {
        try {
            Usuario usuario = (Usuario) new UsuarioDAO().findByLogin(login);
            usuario.setPerfil(new PerfilController().findById(usuario.getPerfil().getId()));
            return usuario;
        } catch (Exception e) {
            return null;
        }
    }

    public Usuario authenticate(String login, String senha) throws Exception {
        Usuario usuario = findByLogin(login);
        if (usuario == null) {
            return null;
        }
        if (!usuario.getSenha().equals(senha)) {
            return null;
        }
        return usuario;
    }
}
