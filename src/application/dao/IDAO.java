package application.dao;

import application.model.IModel;

import java.sql.SQLException;
import java.util.List;

public interface IDAO {

    void save(IModel obj) throws Exception;

    void delete(IModel obj) throws Exception;

    void update(IModel obj) throws Exception;

    List<IModel> findAll() throws Exception;

    IModel findById(long id) throws Exception;
}
