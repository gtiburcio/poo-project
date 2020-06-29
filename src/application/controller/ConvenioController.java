package application.controller;

import application.dao.ConvenioDAO;
import application.model.Convenio;

import java.util.List;
import java.util.stream.Collectors;

public class ConvenioController {

    public List<Convenio> findAll() throws Exception {
        return new ConvenioDAO().findAll().stream().map(model -> (Convenio) model).collect(Collectors.toList());
    }

    public Convenio findById(long id) throws Exception {
        return (Convenio) new ConvenioDAO().findById(id);
    }

    public Convenio findByNome(String nome) throws Exception {
        return new ConvenioDAO().findByNome(nome);
    }
}
