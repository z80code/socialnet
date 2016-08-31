package com.z80.DAO;

import com.google.inject.Inject;
import com.z80.DAO.Core.ConnectionManager;
import com.z80.DAO.Core.CrudRepository;
import com.z80.DAO.Core.CrudRepositoryAddon;
import com.z80.Models.User;
import com.z80.services.Security;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataRepository<T, ID> implements CrudRepository, CrudRepositoryAddon {

    private final ConnectionManager connectionManager;
    private Connection conn;

    @Inject
    public UserDataRepository(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    private static final String SQL_FIND_ALL = "select * from " + User.TABLE_NAME;
    private static final String SQL_FIND_COUNT = "select count(*) as count from " + User.TABLE_NAME;
    private static final String SQL_FIND_BY_ID = SQL_FIND_ALL + " where " + User.USER_ID + "=? limit 1";
    private static final String SQL_FIND_BY_NAME = SQL_FIND_ALL + " where " + User.USER_NAME + "=? limit 1";
    private static final String SQL_FIND_BY_HASHCOOKIE = SQL_FIND_ALL + " where " + User.USER_HASHCOOKIE + "=? limit 1";

    private static final String SQL_INSERT_USER =
            "insert into " + User.TABLE_NAME + "(" +
                    User.USER_NAME + ", " + User.USER_PASSWORD + ", " + User.USER_HASHPASS + ", " +
                    User.USER_HASHSESSION + ", " + User.USER_HASHCOOKIE + ", " + User.USER_HASHID + ", " +
                    User.USER_ROLE + ", " + User.USER_DATA + ") " +
                    "values (?,?,?,?,?,?,?,?)";

    public static final String SQL_UPDATE =
            "update " + User.TABLE_NAME +
                    " set (" +
                    User.USER_NAME + ", " + User.USER_PASSWORD + ", " + User.USER_HASHPASS + ", " +
                    User.USER_HASHSESSION + ", " + User.USER_HASHCOOKIE + ", " + User.USER_HASHID + ", " +
                    User.USER_ROLE + ", " + User.USER_DATA + ") " +
                    "where " + User.USER_ID + "=?";

    public static final String SQL_DELETE =
            "delete " +
                    "from " + User.TABLE_NAME + " " +
                    "where " + User.USER_ID + "=?";

    @Override
    public long count() throws SQLException, ClassNotFoundException {
        conn = connectionManager.getConnection();
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(SQL_FIND_COUNT);
        return (result.next()) ? result.getLong("count") : 0;
    }

    @Override
    public void delete(Serializable serializable) {

    }

    @Override
    public void delete(Iterable entities) {

    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public boolean exists(Serializable id) throws SQLException, ClassNotFoundException {
        return findOne(id) != null;
    }

    @Override
    public Iterable findAll() throws SQLException, ClassNotFoundException {
        Connection conn = connectionManager.getConnection();
        List<User> users = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement(SQL_FIND_ALL);
        statement.setString(1, "users");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            users.add(getFromResult(result));
        }
        return users;
    }

    @Override
    public Object findOne(Serializable id) throws SQLException, ClassNotFoundException {
        conn = connectionManager.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_FIND_BY_ID);
        statement.setString(1, String.format("%d", id));
        ResultSet result = statement.executeQuery();
        return (result.next()) ? getFromResult(result) : null;
    }

    private void saveOne(User user) throws SQLException {

        PreparedStatement statement = conn.prepareStatement(SQL_INSERT_USER);
        statement.setString(1, user.getName());
        statement.setString(2, user.getPassword());
        statement.setString(3, Security.MD5(user.getPassword()));
        statement.setString(4, Security.MD5(Security.randString()));
        statement.setString(5, Security.MD5(Security.randString()));
        statement.setString(6, Security.MD5(Security.randString()));
        statement.setString(7, String.format("%d", user.getRole()));
        statement.setString(8, String.format("%d", user.getData()));
        statement.executeUpdate();
    }

    @Override
    public Object save(Object entity) throws ClassNotFoundException, SQLException {
        Connection conn = connectionManager.getConnection();
        User user = (User) entity;
        saveOne(user);
        return findByName(user.getName());
    }

    @Override
    public Iterable save(Iterable entities) throws SQLException, ClassNotFoundException {
        List<User> list = new ArrayList<>();
        Connection conn = connectionManager.getConnection();
        for (Object obj : entities) {
            User user = (User) obj;
            saveOne(user);
            user = (User) findByName(user.getName());
            list.add(user);
        }
        return list;
    }

    @Override
    public Object findByHash(Serializable hash) throws SQLException, ClassNotFoundException {
        conn = connectionManager.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_FIND_BY_HASHCOOKIE);
        statement.setString(1, (String) hash);
        ResultSet result = statement.executeQuery();
        return (result.next()) ? getFromResult(result) : null;
    }

    @Override
    public Object findByName(Serializable name) throws SQLException, ClassNotFoundException {
        conn = connectionManager.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_FIND_BY_NAME);
        statement.setString(1, (String) name);
        ResultSet result = statement.executeQuery();
        return (result.next()) ? getFromResult(result) : null;
    }

    private User getFromResult(ResultSet result) throws SQLException {
        return new User(
                result.getInt("user_id"),
                result.getString("user_name"),
                result.getString("user_password"),
                result.getString("user_hashpass"),
                result.getString("user_hashsession"),
                result.getString("user_hashcookie"),
                result.getString("user_hashid"),
                result.getInt("user_role"),
                result.getInt("user_data")
        );
    }
}
