package com.z80.DAO;

import com.google.inject.Inject;
import com.z80.DAO.Core.ConnectionManager;
import com.z80.DAO.Core.CrudRepository;
import com.z80.DAO.Core.CrudRepositoryAddon;

import com.z80.Models.UserData;
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

    //USERDATA_ID  	USERDATA_FIRSTNAME  	USERDATA_LASTNAME  	USERDATA_EMAIL  	USERDATA_HOBBY
    private static final String SQL_FIND_ALL = "select * from " + UserData.TABLE_NAME;
    private static final String SQL_FIND_COUNT = "select count(*) as count from " + UserData.TABLE_NAME;
    private static final String SQL_FIND_BY_ID = SQL_FIND_ALL + " where " + UserData.USERDATA_ID + "=? limit 1";
    private static final String SQL_FIND_BY_HASH = SQL_FIND_ALL + " where " + UserData.USERDATA_HASH + "=? limit 1";

    private static final String SQL_FIND_BY_FIRSTNAME = SQL_FIND_ALL + " where " + UserData.USERDATA_FIRSTNAME + "=?";
    private static final String SQL_FIND_BY_LASTNAME = SQL_FIND_ALL + " where " + UserData.USERDATA_LASTNAME + "=?";
    private static final String SQL_FIND_BY_HOBBY = SQL_FIND_ALL + " where " + UserData.USERDATA_HOBBY + "=?";

    private static final String SQL_INSERT_USERDATA =
            "insert into " + UserData.TABLE_NAME + "(" +
                    UserData.USERDATA_FIRSTNAME + ", " + UserData.USERDATA_LASTNAME + ", " +
                    UserData.USERDATA_EMAIL + ", " + UserData.USERDATA_HOBBY + ") " +
                    "values (?,?,?,?)";

    public static final String SQL_UPDATE =
            "update " + UserData.TABLE_NAME +
                    " set (" +
                    UserData.USERDATA_FIRSTNAME + "=?" + ", " + UserData.USERDATA_LASTNAME + "=?" + ", " +
                    UserData.USERDATA_EMAIL + "=?" + ", " + UserData.USERDATA_HOBBY + "=?" + ") " +
                    "where " + UserData.USERDATA_ID + "=?";

    public static final String SQL_DELETE =
            "delete " +
                    "from " + UserData.TABLE_NAME + " " +
                    "where " + UserData.USERDATA_ID + "=?";

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
        List<UserData> userDatas = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement(SQL_FIND_ALL);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            userDatas.add(getFromResult(result));
        }
        return userDatas;
    }

    @Override
    public Object findOne(Serializable id) throws SQLException, ClassNotFoundException {
        conn = connectionManager.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_FIND_BY_ID);
        statement.setString(1, String.format("%d", id));
        ResultSet result = statement.executeQuery();
        return (result.next()) ? getFromResult(result) : null;
    }

    private void saveOne(UserData userData) throws SQLException {

        PreparedStatement statement = conn.prepareStatement(SQL_INSERT_USERDATA);
        statement.setString(1, userData.getUserdata_firstname());
        statement.setString(2, userData.getUserdata_lastname());
        statement.setString(3, userData.getUserdata_email());
        statement.setString(4, userData.getUserdata_hobby());
        statement.executeUpdate();
    }

    @Override
    public Object save(Object entity) throws ClassNotFoundException, SQLException {
        Connection conn = connectionManager.getConnection();
        UserData userData = (UserData) entity;
        saveOne(userData);
        return findByHash(userData.getUserdata_hash());
    }

    @Override
    public Iterable save(Iterable entities) throws SQLException, ClassNotFoundException {
        List<UserData> list = new ArrayList<>();
        Connection conn = connectionManager.getConnection();
        for (Object obj : entities) {
            UserData userData = (UserData) obj;
            saveOne(userData);
            userData = (UserData) findByHash(userData.getUserdata_hash());
            list.add(userData);
        }
        return list;
    }

    @Override
    public Object findByHash(Serializable hash) throws SQLException, ClassNotFoundException {
        conn = connectionManager.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_FIND_BY_HASH);
        statement.setString(1, (String) hash);
        ResultSet result = statement.executeQuery();
        return (result.next()) ? getFromResult(result) : null;
    }

    @Override
    public Object findByName(Serializable firstName) throws SQLException, ClassNotFoundException {
        conn = connectionManager.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_FIND_BY_FIRSTNAME);
        statement.setString(1, (String) firstName);
        ResultSet result = statement.executeQuery();
        return (result.next()) ? getFromResult(result) : null;
    }

    public Object findByLastName(Serializable lastName) throws SQLException, ClassNotFoundException {
        conn = connectionManager.getConnection();
        PreparedStatement statement = conn.prepareStatement(SQL_FIND_BY_LASTNAME);
        statement.setString(1, (String) lastName);
        ResultSet result = statement.executeQuery();
        return (result.next()) ? getFromResult(result) : null;
    }

    private UserData getFromResult(ResultSet result) throws SQLException {
        return new UserData(
                result.getInt("userdata_id"),
                result.getString("userdata_hash"),
                result.getString("userdata_firstname"),
                result.getString("userdata_lastname"),
                result.getString("userdata_email"),
                result.getString("userdata_hobby")
        );
    }
}
