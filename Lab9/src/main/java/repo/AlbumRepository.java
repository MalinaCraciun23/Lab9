/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repo;

import entity.Album;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import util.PersistenceUtil;

/**
 *
 * @author Mally
 */
public class AlbumRepository {

    public Optional<Album> create(Album album) {
        try {
            EntityManager entityManager = PersistenceUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(album);
            entityManager.getTransaction().commit();
            PersistenceUtil.closeEntityManager();
            return Optional.of(album);
        } catch (Exception e) {
            System.err.println(e);
        }
        return Optional.empty();
    }

    public Optional<Album> findById(Integer id) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        Album album = entityManager.find(Album.class, id);
        PersistenceUtil.closeEntityManager();
        return album != null ? Optional.of(album) : Optional.empty();
    }

    public List<Album> findByName(String name) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        List<Album> albums = entityManager.createNamedQuery("Album.findByName", Album.class).setParameter("name", name)
                .getResultList();
        PersistenceUtil.closeEntityManager();
        return albums;
    }

    public List<Album> findByArtist(Integer artistId) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        List<Album> albums = entityManager.createNamedQuery("Album.findByArtist", Album.class).setParameter("artistId", artistId)
                .getResultList();
        PersistenceUtil.closeEntityManager();
        return albums;
    }
}
