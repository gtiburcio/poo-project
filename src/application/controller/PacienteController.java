package application.controller;

import java.util.List;
import java.util.stream.Collectors;

import application.dao.PacienteDAO;
import application.model.Paciente;

public class PacienteController {

	public void save(Paciente paciente) throws Exception {
		new PacienteDAO().save(paciente);
	}

	public void delete(Paciente paciente) throws Exception {
		new PacienteDAO().delete(paciente);
	}

	public void update(Paciente paciente) throws Exception {
		new PacienteDAO().update(paciente);
	}

	public List<Paciente> findAll() throws Exception {
		return new PacienteDAO().findAll().stream().map(model -> (Paciente) model).peek(paciente -> {
			try {
				paciente.setPlano(new PlanoController().findById(paciente.getPlano().getId()));
				paciente.getPlano().setConvenio(new ConvenioController().findById(paciente.getPlano().getConvenio().getId()));
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}).collect(Collectors.toList());
	}

	public Paciente findById(long id) throws Exception {
		return (Paciente) new PacienteDAO().findById(id);
	}
}
