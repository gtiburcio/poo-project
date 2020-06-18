package application.controller;

import java.util.List;
import java.util.stream.Collectors;

import application.dao.PacienteDAO;
import application.model.IModel;
import application.model.Paciente;

public class PacienteController {

	public void save(Paciente paciente) throws Exception {
		new PacienteDAO().saveAndGetId(paciente);
	}

	public long save(Paciente paciente, boolean withId) throws Exception {
		if(withId) {
			return new PacienteDAO().saveAndGetId(paciente);
		}else {
			new PacienteDAO().save(paciente);
			return 0;
		}
	}

	public void delete(Paciente paciente) throws Exception {
		new PacienteDAO().delete(paciente);
	}

	public void update(Paciente paciente) throws Exception {
		new PacienteDAO().update(paciente);
	}

	public List<Paciente> findAll() throws Exception {
		return new PacienteDAO().findAll().stream().map(model -> (Paciente) model).collect(Collectors.toList());
	}

	public Paciente findById(long id) throws Exception {
		return (Paciente) new PacienteDAO().findById(id);
	}

	public Paciente findByCpf(String cpf) throws Exception {
		return (Paciente) new PacienteDAO().findByCpf(cpf);
	}
}
