package application.dao;

import java.util.List;

import application.model.IModel;

public interface IDAO {

    void save(IModel obj) throws Exception;

    void delete(IModel obj) throws Exception;

    void update(IModel obj) throws Exception;

    List<IModel> findAll() throws Exception;

    IModel findById(long id) throws Exception;
}
