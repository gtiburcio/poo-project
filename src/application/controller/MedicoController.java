package application.controller;

import java.util.List;
import java.util.stream.Collectors;

import application.dao.MedicoDAO;
import application.model.Medico;

public class MedicoController {

	public void save(Medico medico) throws Exception {
		new MedicoDAO().save(medico);
	}

	public void delete(Medico medico) throws Exception {
		new MedicoDAO().delete(medico);
	}

	public void update(Medico medico) throws Exception {
		new MedicoDAO().update(medico);
	}

	public List<Medico> findAll() throws Exception {
		return new MedicoDAO().findAll().stream().map(model -> (Medico) model).peek(medico -> {
			try {
				medico.setEspecialidade(new EspecialidadeController().findById(medico.getEspecialidade().getId()));
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}).collect(Collectors.toList());
	}

	public Medico findById(long id) throws Exception {
		return (Medico) new MedicoDAO().findById(id);
	}

	public Medico findByEspecialidade(long id_especialidade) throws Exception {
		return (Medico) new MedicoDAO().findByEspecialidade(id_especialidade);
	}
}
