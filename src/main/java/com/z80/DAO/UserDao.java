package com.z80.DAO;

import com.z80.Models.User;

import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public interface UserDao {

    User findByName(String name) throws SQLException, ClassNotFoundException;
    User findByHash(String name) throws SQLException, ClassNotFoundException;
    User findById(int id);
    List<User> getAll() throws SQLException, ClassNotFoundException;

    void save(User user) throws SQLException, ClassNotFoundException;
    void remove(User user);
    void update(User user);
}