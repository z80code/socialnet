package com.z80.DAO;

import com.z80.Models.User;
import com.google.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final ConnectionManager connectionManager;
    private Connection conn;

    @Inject
    public UserDaoImpl(ConnectionManager connectionManager) {
        //System.out.println("constructor: userdao");
        this.connectionManager = connectionManager;
    }

    @Override
    public User findByName(String name) throws SQLException, ClassNotFoundException {
        conn = connectionManager.getConnection();
        // TODO
        try (Statement statement = conn.createStatement()) {

            ResultSet result = statement.executeQuery("select * from users");
            while (result.next()) {
                String _name = result.getString("user_name");
                if (_name.equals(name)) return (
                        new User(
                                result.getInt("user_id"),
                                result.getString("user_name"),
                                result.getString("user_password"),
                                result.getString("user_hashpass"),
                                result.getString("user_hashsession"),
                                result.getString("user_hashcookie"),
                                result.getString("user_hashid"),
                                result.getInt("user_role"),
                                result.getInt("user_data")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public User findByHash(String hash) throws SQLException, ClassNotFoundException {
        conn = connectionManager.getConnection();
        // TODO
        try (Statement statement = conn.createStatement()) {

            ResultSet result = statement.executeQuery("select * from users");

            while (result.next()) {
                String _hash = result.getString("user_hashcookie");
                if (_hash.equals(hash)) return (
                        new User(
                                result.getInt("user_id"),
                                result.getString("user_name"),
                                result.getString("user_password"),
                                result.getString("user_hashpass"),
                                result.getString("user_hashsession"),
                                result.getString("user_hashcookie"),
                                result.getString("user_hashid"),
                                result.getInt("user_role"),
                                result.getInt("user_data")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        Connection conn = connectionManager.getConnection();
        List<User> users = new ArrayList<>();

        // TODO
        try (Statement statement = conn.createStatement()) {

            ResultSet result = statement.executeQuery("select * from users");

            while (result.next()) {

                int id = result.getInt("id");
                String name = result.getString("name");
                String password = result.getString("password");

                users.add(new User(id, name, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    // TODO id generation
    @Override
    public void save(User user) throws SQLException, ClassNotFoundException {
        Connection conn = connectionManager.getConnection();
        try (PreparedStatement statement
                     = conn.prepareStatement(
                "INSERT INTO Users(name, password) VALUES(?,?)")) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());

            // TODO
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(User user) {

    }

    @Override
    public void update(User user) {

    }
}
