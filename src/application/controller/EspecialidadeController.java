package application.controller;

import application.dao.EspecialidadeDAO;
import application.model.Especialidade;

import java.util.List;
import java.util.stream.Collectors;

public class EspecialidadeController {

	public List<Especialidade> findAll() throws Exception {
		return new EspecialidadeDAO().findAll().stream().map(model -> (Especialidade) model)
				.collect(Collectors.toList());
	}

	public Especialidade findById(long id) throws Exception {
		return (Especialidade) new EspecialidadeDAO().findById(id);
	}

	public Especialidade findByNome(String nome) throws Exception {
		return new EspecialidadeDAO().findByNome(nome);
	}

	public void delete(Especialidade especialidade) throws Exception {
		new EspecialidadeDAO().delete(especialidade);
	}

	public void update(Especialidade especialidade) throws Exception {
		new EspecialidadeDAO().update(especialidade);
	}

}
