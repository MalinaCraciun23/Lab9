/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fii.pa.lab8;

import DAO.AlbumController;
import DAO.ArtistController;
import java.sql.SQLException;

/**
 *
 * @author Mally
 */
public class Main {

    public static void main(String[] args) {
        try {
            ArtistController artist = new ArtistController();
            AlbumController album = new AlbumController();
            artist.create("Ed Sheeran", "UK");
            artist.create("Harry Styles", "UK");
            Database.commit();
            Integer artistId = artist.findByName("Ed Sheeran");
            System.err.println(artistId);
            album.create("No.6 Collaborations Project", artistId, 2019);
            Database.commit();
            String albumName = album.findByArtist(artistId);
            System.err.println(albumName);
            Database.closeConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex);
            Database.rollback();
        }
    }
}
