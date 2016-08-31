package com.z80.DAO.Core;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.z80.DAO.Core.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Singleton
public class ConnectionManager {

    private Connection connection;
    private DataSource dataSource;

    @Inject
    public ConnectionManager(DataSource dataSource) {
        //System.out.println("constructor: connection manager");
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {

        // TODO add forwarding exception
            if(connection==null||connection.isClosed()) {
                Class.forName(dataSource.getDriver());
                connection
                        = DriverManager
                        .getConnection(
                                dataSource.getUrl(),
                                dataSource.getUser(),
                                dataSource.getPassword());
            }

        return connection;
    }
}