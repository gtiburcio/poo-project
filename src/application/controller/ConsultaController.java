package application.controller;

import application.dao.ConsultaDAO;
import application.model.Consulta;

import java.sql.SQLException;

public class ConsultaController {

    public long salvar(Consulta consulta) throws SQLException {
        return new ConsultaDAO().salvar(consulta);
    }
}
