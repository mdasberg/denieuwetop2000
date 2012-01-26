package nl.jpoint.top2k.service;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.inject.Provider;
import nl.jpoint.top2k.domain.Artist;

/**
 * Artist service.
 */
public class ArtistService {

    @Inject
    private Provider<EntityManager> provider;

    public void create(final Artist artist) {
        provider.get().persist(artist);
    }

    public List<Artist> getAll() {
        return provider.get().createNamedQuery("Artist.findAll").getResultList();
    }
}
