package com.z80.DAO.Core;

import java.io.Serializable;
import java.sql.SQLException;

public interface CrudRepository<T, ID extends Serializable> extends Repository<T,ID> {

    long count() throws SQLException, ClassNotFoundException;

    void delete(ID id);

    void delete(Iterable<? extends T> entities);

    void delete(T entity);

    void deleteAll();

    boolean exists(ID id) throws SQLException, ClassNotFoundException;

    Iterable<T> findAll() throws SQLException, ClassNotFoundException;

    T findOne(ID id) throws SQLException, ClassNotFoundException;

    <S extends T> Iterable<S> save(Iterable<S> entities) throws SQLException, ClassNotFoundException;

    <S extends T> S save(S entity) throws SQLException, ClassNotFoundException;
}
