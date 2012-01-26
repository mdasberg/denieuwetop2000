package nl.jpoint.top2k.service;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Provider;
import nl.jpoint.top2k.domain.Track;

/**
 * Track Service.
 */
public class TrackService implements ITrackService {

    private static final int PAGE_SIZE = 25;

    @Inject
    private Provider<EntityManager> provider;

    @Override
    public void create(final Track track) {
        provider.get().persist(track);
    }

    @Override
    public List<Track> getAll() {
        return provider.get().createNamedQuery("Track.findAll").getResultList();
    }

    @Override
    public List<Track> getPagedList(final int page) {
        Query query = provider.get().createNamedQuery("Track.findAll");
        query.setFirstResult(page * PAGE_SIZE);
        query.setMaxResults(PAGE_SIZE);
        return query.getResultList();
    }

    @Override
    public Track getById(final long id) {
        return provider.get().find(Track.class, id);
    }

    @Override
    public List<Long> getValidTrackIds() {
        return provider.get().createQuery("SELECT t.id FROM Track t WHERE t.artist IS NOT NULL").getResultList();
    }
}
