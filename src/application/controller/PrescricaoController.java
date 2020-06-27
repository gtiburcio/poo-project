package application.controller;

import application.dao.PrescricaoDAO;
import application.model.Prescricao;

import java.sql.SQLException;
import java.util.List;

public class PrescricaoController {

    public void salvar(List<Prescricao> prescricoes) throws SQLException {
        PrescricaoDAO prescricaoDAO = new PrescricaoDAO();
        for (Prescricao prescricao : prescricoes) {
            prescricaoDAO.salvar(prescricao);
        }
    }
}
