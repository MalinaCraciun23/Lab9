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
public class AlbumController {

    public void create(String name, int artistId, int releaseYear) throws SQLException, ClassNotFoundException {
        Connection conn = Database.getConnection();
        try ( PreparedStatement pstmt = conn.prepareStatement("insert into albums (name, artist_id, release_year) values (?, ?, ?)")) {
            pstmt.setString(1, name);
            pstmt.setInt(2, artistId);
            pstmt.setInt(3, releaseYear);
            pstmt.executeUpdate();
        }
    }

    public String findByArtist(int artistId) throws SQLException, ClassNotFoundException {
        Connection conn = Database.getConnection();
        try ( PreparedStatement pstmt = conn.prepareStatement("select name from albums where artist_id=?")) {
            pstmt.setInt(1, artistId);
            try ( ResultSet res = pstmt.executeQuery()) {
                if (res.next()) {
                    return res.getString(1);
                }
                return null;
            }
        }
    }
}
