/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import fii.pa.lab8.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mally
 */
public class ArtistController {

    public void create(String name, String country) throws SQLException, ClassNotFoundException {
        Connection conn = Database.getConnection();
        try ( PreparedStatement pstmt = conn.prepareStatement("insert into artists (name, country) values (?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setString(2, country);
            pstmt.executeUpdate();
        }
    }

    public Integer findByName(String name) throws SQLException, ClassNotFoundException {
        Connection conn = Database.getConnection();
        try ( PreparedStatement pstmt = conn.prepareStatement("select id from artists where name=?")) {
            pstmt.setString(1, name);
            try ( ResultSet res = pstmt.executeQuery()) {
                if (res.next()) {
                    return res.getInt(1);
                }
                return null;
            }
        }
    }
}
