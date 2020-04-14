/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import entity.Album;
import entity.Artist;
import java.util.List;
import java.util.Optional;
import repo.AlbumRepository;
import repo.ArtistRepository;

/**
 *
 * @author Mally
 */
public class AlbumManager {

    public static void main(String[] args) {
        ArtistRepository artistRepository = new ArtistRepository();
        AlbumRepository albumRepository = new AlbumRepository();
        Artist artist = new Artist("Harry Styles", "UK");
        Optional<Artist> savedArtist = artistRepository.create(artist);
        System.out.println("Saved artist: " + savedArtist.get().getName());
        Album album = new Album("Sign of the times", 2017, savedArtist.get());
        Optional<Album> savedAlbum = albumRepository.create(album);
        System.out.println("Saved album: " + savedAlbum.get().getName());
        List<Album> artistAlbums = albumRepository.findByArtist(savedArtist.get().getId());
        System.out.println("Album of artist " + savedArtist.get().getName() + " : " + artistAlbums.get(0).getName());
    }
}
