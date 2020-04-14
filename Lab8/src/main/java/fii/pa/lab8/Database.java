/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fii.pa.lab8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mally
 */
public class Database {

    private static final String URL = "jdbc:postgresql://localhost:2325/MusicAlbums";
    private static final String USER = "dba";
    private static final String PASSWORD = "sql";
    private static Connection connection = null;

    private static final Database INSTANCE = new Database();

    public static Database getInstance() {
        return INSTANCE;
    }

    private Database() {
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    public static void commit() throws SQLException {
        connection.commit();
    }

    public static void rollback() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }
}
