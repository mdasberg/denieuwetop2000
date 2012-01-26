package nl.jpoint.top2k.service;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Provider;
import nl.jpoint.top2k.domain.Artist;

/**
 * Artist service.
 */
public class ArtistService {

    private static final int PAGE_SIZE = 25;

    @Inject
    private Provider<EntityManager> provider;

    public void create(final Artist artist) {
        provider.get().persist(artist);
    }

    public List<Artist> getAll() {
        return provider.get().createNamedQuery("Artist.findAll").getResultList();       
    }
    
    public List<Artist> getPagedList(final int page) {
        Query query = provider.get().createNamedQuery("Artist.findAll");
        query.setFirstResult(page * PAGE_SIZE);
        query.setMaxResults(PAGE_SIZE);
        return query.getResultList();
    }
}
