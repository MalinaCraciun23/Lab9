/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repo;

import entity.Artist;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import util.PersistenceUtil;

/**
 *
 * @author Mally
 */
public class ArtistRepository {

    public Optional<Artist> create(Artist artist) {
        try {
            EntityManager entityManager = PersistenceUtil.getEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(artist);
            entityManager.getTransaction().commit();
            PersistenceUtil.closeEntityManager();
            return Optional.of(artist);
        } catch (Exception e) {
            System.err.println(e);
        }
        return Optional.empty();
    }

    public Optional<Artist> findById(Integer id) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        Artist artist = entityManager.find(Artist.class, id);
        PersistenceUtil.closeEntityManager();
        return artist != null ? Optional.of(artist) : Optional.empty();
    }

    public List<Artist> findByName(String name) {
        EntityManager entityManager = PersistenceUtil.getEntityManager();
        List<Artist> artists = entityManager.createNamedQuery("Artist.findByName", Artist.class).setParameter("name", name)
                .getResultList();
        PersistenceUtil.closeEntityManager();
        return artists;
    }
}
