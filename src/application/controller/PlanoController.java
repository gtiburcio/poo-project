package application.controller;

import application.dao.PlanoDAO;
import application.model.Convenio;
import application.model.Plano;

import java.util.List;
import java.util.stream.Collectors;

public class PlanoController {

    public List<Plano> findAll() throws Exception {
        return new PlanoDAO().findAll().stream().map(model -> (Plano) model).peek(plano -> {
			try {
				plano.setConvenio(new ConvenioController().findById(plano.getConvenio().getId()));
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}).collect(Collectors.toList());
    }

    public Plano findById(long id) throws Exception {
        return (Plano) new PlanoDAO().findById(id);
    }

    public Plano findByNome(String nome) throws Exception {
        return new PlanoDAO().findByNome(nome);
    }

    public List<Plano> findByConvenio(Convenio convenio) throws Exception {
        return new PlanoDAO().findByConvenio(convenio);
    }
}
