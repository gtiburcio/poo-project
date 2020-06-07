package application.controller;

import application.dao.PerfilDAO;
import application.model.Perfil;

import java.util.List;
import java.util.stream.Collectors;

public class PerfilController {

    public List<Perfil> findAll() throws Exception {
        return new PerfilDAO().findAll().stream().map(model -> (Perfil) model).collect(Collectors.toList());
    }

    public Perfil findById(long id) throws Exception {
        return (Perfil) new PerfilDAO().findById(id);
    }

    public Perfil findByDescription(String descricao) throws Exception {
        return new PerfilDAO().findByDescription(descricao);
    }
}
