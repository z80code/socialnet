package com.z80.DAO.Core;

import java.io.Serializable;
import java.sql.SQLException;

public interface CrudRepositoryAddon<T, ID extends Serializable> {

    public T findByHash(ID hash) throws SQLException, ClassNotFoundException;
    public T findByName(ID Name) throws SQLException, ClassNotFoundException;
}
