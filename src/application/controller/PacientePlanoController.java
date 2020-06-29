package application.controller;

import application.dao.PacientePlanoDAO;
import application.model.PacientePlano;

import java.util.List;
import java.util.stream.Collectors;

public class PacientePlanoController {

	public void save(PacientePlano pacientePlano) throws Exception {
		new PacientePlanoDAO().save(pacientePlano);
	}

	public void delete(PacientePlano pacientePlano) throws Exception {
		new PacientePlanoDAO().delete(pacientePlano);
	}

	public void update(PacientePlano pacientePlano) throws Exception {
		new PacientePlanoDAO().update(pacientePlano);
	}

	public List<PacientePlano> findAll() throws Exception {
		return new PacientePlanoDAO().findAll().stream().map(model -> (PacientePlano) model).peek(pacientePlano -> {
			try {
				pacientePlano.setPaciente(new PacienteController().findById(pacientePlano.getPaciente().getId()));
				pacientePlano.setPlano(new PlanoController().findById(pacientePlano.getPlano().getId()));
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}).collect(Collectors.toList());
	}

	public PacientePlano findById(long id) throws Exception {
		PacientePlano pacientePlano = (PacientePlano) new PacientePlanoDAO().findById(id);
		pacientePlano.setPaciente(new PacienteController().findById(pacientePlano.getPaciente().getId()));
		pacientePlano.setPlano(new PlanoController().findById(pacientePlano.getPlano().getId()));
        return pacientePlano;
	}

	public PacientePlano findByPaciente(long id) throws Exception {
		PacientePlano pacientePlano = (PacientePlano) new PacientePlanoDAO().findByPaciente(id);
		pacientePlano.setPaciente(new PacienteController().findById(pacientePlano.getPaciente().getId()));
		pacientePlano.setPlano(new PlanoController().findById(pacientePlano.getPlano().getId()));
        return pacientePlano;
	}
}
